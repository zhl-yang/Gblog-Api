package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.entity.BlogTagEntity;

import java.util.List;

/**
 * <p>
 * 文章标签表 Mapper 接口
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface BlogArticleTagDao extends BaseMapper<BlogArticleTagEntity> {

    /**
     * 获取文章所有标签
     *
     * @param articleId
     * @return
     */
    List<BlogTagEntity> queryArticleTags(Integer articleId);
}
