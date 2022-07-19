package com.zhloong.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.BlogArticleDao;
import com.zhloong.blog.dto.BlogArchivesHzDto;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.req.BlogArticleInsertReq;
import com.zhloong.blog.service.IBlogArticleService;
import com.zhloong.blog.service.IBlogArticleTagService;
import com.zhloong.blog.shiro.AppUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleDao, BlogArticleEntity> implements IBlogArticleService {

    public static final Integer ARTICLE_ARCHIVE_LIMIT_NUM = 8;

    @Resource
    private IBlogArticleTagService articleTagService;

    @Resource
    private AppUser appUser;

    /**
     * 发布文章按年-月汇总
     *
     * @return
     */
    @Override
    public List<BlogArchivesHzDto> queryArticleArchives() {

        return baseMapper.queryArticleArchives(ARTICLE_ARCHIVE_LIMIT_NUM);
    }

    @Override
    public BlogArticleEntity insert(BlogArticleInsertReq req) {
        BlogArticleEntity archives = new BlogArticleEntity();
        BeanUtils.copyProperties(req, archives);

        archives.setCommentNum(0);
        archives.setViewNum(0);
        // TODO 需自己实现
        archives.setBanner("");
        archives.setCreateTime(DateUtil.date().toTimestamp().toLocalDateTime());
        archives.setUpdateTime(DateUtil.date().toTimestamp().toLocalDateTime());
        archives.setUserId(appUser.getUser().getUserEntityRsp().getId());
        if (StringUtils.isEmpty(appUser.getUser().getUserEntityRsp().getNickname())) {
            archives.setNickname(appUser.getUser().getUserEntityRsp().getUserName());
        } else {
            archives.setNickname(appUser.getUser().getUserEntityRsp().getNickname());
        }
        archives.setTags(StringUtils.join(req.getTags().toArray(), ","));

        save(archives);


        List<BlogArticleTagEntity> articleTagEntityList = new ArrayList<>();
        req.getTags().forEach(value -> {
            BlogArticleTagEntity articleTagEntity = new BlogArticleTagEntity();
            articleTagEntity.setArticleId(archives.getId());
            articleTagEntity.setTagId(value);
            articleTagEntity.setCreateTime(DateUtil.date().toTimestamp().toLocalDateTime());
            articleTagEntity.setUpdateTime(DateUtil.date().toTimestamp().toLocalDateTime());
            articleTagEntityList.add(articleTagEntity);
        });

        articleTagService.saveBatch(articleTagEntityList);

        return archives;

    }

    @Override
    public BlogArticleEntity update(BlogArticleInsertReq req) {
        // 检查是不是作者
        BlogArticleEntity articleEntity = getById(req.getId());
        if (ObjectUtil.isEmpty(articleEntity) || !appUser.getUser().getUserEntityRsp().getId().equals(articleEntity.getUserId())) {
            throw new RuntimeException("您不是该文章的作者！");
        }

        BlogArticleEntity archives = new BlogArticleEntity();
        BeanUtils.copyProperties(req, archives);

        archives.setUpdateTime(DateUtil.date().toTimestamp().toLocalDateTime());
        archives.setTags(StringUtils.join(req.getTags().toArray(), ","));

        /**
         * 修改文章，同步修改banner
         */
        // TODO 需自己实现
        archives.setBanner("");
        this.updateById(archives);

        articleTagService.remove(new LambdaQueryWrapper<BlogArticleTagEntity>().eq(BlogArticleTagEntity::getArticleId, archives.getId()));

        List<BlogArticleTagEntity> articleTagEntityList = new ArrayList<>();
        req.getTags().forEach(value -> {
            BlogArticleTagEntity articleTagEntity = new BlogArticleTagEntity();
            articleTagEntity.setArticleId(archives.getId());
            articleTagEntity.setTagId(value);
            articleTagEntity.setCreateTime(DateUtil.date().toTimestamp().toLocalDateTime());
            articleTagEntity.setUpdateTime(DateUtil.date().toTimestamp().toLocalDateTime());
            articleTagEntityList.add(articleTagEntity);
        });

        articleTagService.saveBatch(articleTagEntityList);

        return archives;

    }
}
