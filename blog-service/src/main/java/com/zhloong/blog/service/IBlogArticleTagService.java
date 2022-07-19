package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.entity.BlogTagEntity;

import java.util.List;

/**
 * <p>
 * 文章标签表 服务类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface IBlogArticleTagService extends IService<BlogArticleTagEntity> {

    /**
     * 获取文章所有标签
     * @param articleId
     * @return
     */
    List<BlogTagEntity> queryArticleTags(Integer articleId);
}
