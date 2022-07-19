package com.zhloong.admin;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.dto.BlogArchivesHzDto;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.req.ArticleSearchReq;
import com.zhloong.blog.req.BlogArticleInsertReq;
import com.zhloong.blog.rsp.BlogArchivesInfoAuthRsp;
import com.zhloong.blog.rsp.BlogArchivesInfoRsp;
import com.zhloong.blog.rsp.BlogArticleHotOrNewRsp;
import com.zhloong.blog.rsp.BlogArticleListRsp;
import com.zhloong.blog.service.IBlogArticleService;
import com.zhloong.blog.service.IBlogArticleTagService;
import com.zhloong.blog.service.IBlogCategoryService;
import com.zhloong.blog.shiro.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/article")
@ResponseBody
@Slf4j
public class BlogArticleController {

    @Resource
    private IBlogArticleService articleService;

    @Resource
    private IBlogCategoryService categoryService;

    @Resource
    IBlogArticleTagService blogArticleTagService;

    @Resource
    AppUser appUser;

    /**
     * 汇总查询
     */
    @GetMapping("/archives")
    @Cacheable(value = "archives")
    public Result<BlogArchivesHzDto> getArticle() {
        return Result.ok(articleService.queryArticleArchives());
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    @Cacheable(value = "archives-list", key = "#page.current+'-'+#page.size+'-'+#page.article.title")
    public Result<BlogArticleListRsp> getList(@RequestBody ArticleSearchReq page) {

        IPage<BlogArticleListRsp> rspIPage = new Page<>();
        List<BlogArticleListRsp> listRsps = new ArrayList<>();


        List<Integer> articleIds = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(page.getArticle()) && ObjectUtil.isNotEmpty(page.getArticle().getTagId())) {
            List<BlogArticleTagEntity> tagEntityList = blogArticleTagService.list(new LambdaQueryWrapper<BlogArticleTagEntity>().eq(BlogArticleTagEntity::getTagId, page.getArticle().getTagId()));
            articleIds = tagEntityList.stream().map(BlogArticleTagEntity::getArticleId).collect(Collectors.toList());
        }


        LambdaQueryWrapper<BlogArticleEntity> wrapper = new LambdaQueryWrapper<BlogArticleEntity>()
                .in(ObjectUtil.isNotEmpty(articleIds), BlogArticleEntity::getId, articleIds)
                .like(ObjectUtil.isNotEmpty(page.getArticle().getTitle()), BlogArticleEntity::getTitle, page.getArticle().getTitle())
                .eq(ObjectUtil.isNotEmpty(page.getArticle().getCategoryId()), BlogArticleEntity::getCategoryId, page.getArticle().getCategoryId())
                .eq(BlogArticleEntity::getDeleteTime, 0)
                .orderByDesc(BlogArticleEntity::getViewNum, BlogArticleEntity::getCommentNum, BlogArticleEntity::getCreateTime);


        IPage<BlogArticleEntity> pageList = articleService.page(page, wrapper);
        BeanUtils.copyProperties(pageList, rspIPage);

        if (pageList.getRecords().size() > 0) {
            pageList.getRecords().forEach(blogArticleEntity -> {
                BlogArticleListRsp rsp = new BlogArticleListRsp();
                BeanUtils.copyProperties(blogArticleEntity, rsp);
                rsp.setTags(blogArticleTagService.queryArticleTags(blogArticleEntity.getId()));
                rsp.setCreateTime(DateUtil.format(blogArticleEntity.getCreateTime(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
                listRsps.add(rsp);
            });
            rspIPage.setRecords(listRsps);
        }
        return Result.ok(rspIPage);
    }

    /**
     * 最热文章
     */
    @GetMapping("/hot")
    @Cacheable(value = "archivesHot")
    public Result<BlogArticleHotOrNewRsp> getHot() {
        List<BlogArticleEntity> pageList = articleService.list(new LambdaQueryWrapper<BlogArticleEntity>()
                .eq(BlogArticleEntity::getDeleteTime, 0)
                .orderByDesc(BlogArticleEntity::getViewNum)
                .last(" limit 0,6"));
        return Result.ok(toHotOrNew(pageList));
    }

    /**
     * 最新文章
     */
    @GetMapping("/new")
    @Cacheable(value = "archivesNew")
    public Result<BlogArticleHotOrNewRsp> getNew() {
        List<BlogArticleEntity> pageList = articleService.list(new LambdaQueryWrapper<BlogArticleEntity>()
                .eq(BlogArticleEntity::getDeleteTime, 0)
                .orderByDesc(BlogArticleEntity::getCreateTime)
                .last("limit 0,6")
        );

        return Result.ok(toHotOrNew(pageList));
    }

    /**
     * 转换返回参数
     *
     * @param pageList
     * @return
     */
    public List<BlogArticleHotOrNewRsp> toHotOrNew(List<BlogArticleEntity> pageList) {
        List<BlogArticleHotOrNewRsp> hotOrNewRsps = new ArrayList<>();
        pageList.forEach(value -> {
            BlogArticleHotOrNewRsp hotOrNewRsp = new BlogArticleHotOrNewRsp();
            BeanUtils.copyProperties(value, hotOrNewRsp);
            hotOrNewRsps.add(hotOrNewRsp);
        });
        return hotOrNewRsps;
    }

    /**
     * 查看文章详情时：
     * 获取文章详情
     * 包含作者信息
     * <p>
     * 要增加文章阅读数
     */
    @GetMapping("/view/{id}")
    @PassToken
    public Result<BlogArchivesInfoAuthRsp> oneArticleInfo(@PathVariable("id") Long id) {
        BlogArchivesInfoAuthRsp archivesInfoAuthRsp = new BlogArchivesInfoAuthRsp();
        // 文章信息
        articleService.update(new UpdateWrapper<BlogArticleEntity>()
                .lambda()
                .eq(BlogArticleEntity::getId, id)
                .eq(BlogArticleEntity::getDeleteTime, 0)
                .setSql(" view_num = view_num + 1 "));

        BlogArticleEntity articleEntity = articleService.getById(id);
        if (ObjectUtil.isNotEmpty(articleEntity) && articleEntity.getDeleteTime() == 0) {
            BlogArchivesInfoAuthRsp.BlogArchives archives = new BlogArchivesInfoAuthRsp.BlogArchives();
            BeanUtils.copyProperties(articleEntity, archives);
            archives.setCreateTime(DateUtil.format(articleEntity.getCreateTime(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));
            archivesInfoAuthRsp.setArchives(archives);

            // 作者信息
            // TODO 需自己实现
            archivesInfoAuthRsp.setAuthor(null);

            // 分类信息
            archivesInfoAuthRsp.setCategory(categoryService.getById(articleEntity.getCategoryId()));

            // 标签信息
            archivesInfoAuthRsp.setTags(blogArticleTagService.queryArticleTags(articleEntity.getId()));
        }

        return Result.ok(archivesInfoAuthRsp);
    }


    /**
     * 编辑文章时：
     * 通过文章Id获取文章详情
     * 不需要用户信息
     */
    @GetMapping("/{id}")
    public Result<BlogArchivesInfoRsp> getArticleById(@PathVariable("id") Long id) {
        BlogArchivesInfoRsp archivesInfoAuthRsp = new BlogArchivesInfoRsp();
        // 文章信息
        BlogArticleEntity articleEntity = articleService.getById(id);
        articleEntity.setViewNum(articleEntity.getViewNum() + 1);
        articleService.updateById(articleEntity);

        BlogArchivesInfoRsp.BlogArchives archives = new BlogArchivesInfoRsp.BlogArchives();
        BeanUtils.copyProperties(articleEntity, archives);
        archivesInfoAuthRsp.setArchives(archives);

        // 分类信息
        archivesInfoAuthRsp.setCategory(categoryService.getById(articleEntity.getCategoryId()));

        // 标签信息
        archivesInfoAuthRsp.setTags(blogArticleTagService.queryArticleTags(articleEntity.getId()));

        return Result.ok(archivesInfoAuthRsp);
    }

    /**
     * 文章编辑与新增
     */
    @PostMapping("/publish")
    @CacheEvict(value = {"archivesNew", "archives-info", "archives-list"}, allEntries = true)
    public Result<BlogArticleEntity> save(@RequestBody BlogArticleInsertReq req) {
        BlogArticleEntity articleEntity;
        if (req.getId() != null) {
            //编辑文章
            articleEntity = articleService.update(req);
        } else {
            //新增文章
            articleEntity = articleService.insert(req);
        }
        return Result.ok(articleEntity);
    }

    /**
     * 删除文章
     */
    @GetMapping("/delete/{id}")
    @CacheEvict(value = {"archivesNew", "archives-info", "archives-list"}, allEntries = true)
    public Result<Boolean> delete(@PathVariable("id") Long id) {
        BlogArticleEntity articleEntity = articleService.getById(id);
        if (appUser.getUser().getUserEntityRsp().getId().equals(articleEntity.getUserId())) {
            articleEntity.setDeleteTime((int) System.currentTimeMillis());
            articleService.updateById(articleEntity);
        }
        return Result.ok(Boolean.TRUE);
    }

}

