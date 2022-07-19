package com.zhloong.gblog;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.req.ArticleSearchReq;
import com.zhloong.blog.service.IBlogArticleService;
import com.zhloong.blog.service.IBlogArticleTagService;
import com.zhloong.gblog.rsp.BlogPostListRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/post")
@ResponseBody
@Slf4j
public class BlogPostController {

    @Resource
    private IBlogArticleService articleService;

    @Resource
    private IBlogArticleTagService blogArticleTagService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @PassToken
    public Result<BlogPostListRsp> getList(@RequestBody ArticleSearchReq page) {

        IPage<BlogPostListRsp> rspIPage = new Page<>();
        List<BlogPostListRsp> listRsps = new ArrayList<>();

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
                .orderByDesc(BlogArticleEntity::getWeight, BlogArticleEntity::getViewNum, BlogArticleEntity::getCommentNum, BlogArticleEntity::getCreateTime);

        IPage<BlogArticleEntity> pageList = articleService.page(page, wrapper);
        BeanUtils.copyProperties(pageList, rspIPage);

        if (pageList.getRecords().size() > 0) {
            pageList.getRecords().forEach(blogArticleEntity -> {
                BlogPostListRsp rsp = new BlogPostListRsp();
                BeanUtils.copyProperties(blogArticleEntity, rsp);
                rsp.setTags(blogArticleTagService.queryArticleTags(blogArticleEntity.getId()));
                rsp.setCreateTime(DateUtil.format(blogArticleEntity.getCreateTime(), DatePattern.NORM_DATETIME_MINUTE_PATTERN));

                rsp.setIsHot(rsp.getViewNum() > 50 ? Boolean.TRUE : Boolean.FALSE);
                rsp.setIsTop(rsp.getWeight() > 0 ? Boolean.TRUE : Boolean.FALSE);
                rsp.setCommentsCount(rsp.getCommentNum());
                rsp.setViewsCount(rsp.getViewNum());
                rsp.setPubTime(rsp.getCreateTime());
                listRsps.add(rsp);
            });
            rspIPage.setRecords(listRsps);
        }
        return Result.ok(rspIPage);
    }


}

