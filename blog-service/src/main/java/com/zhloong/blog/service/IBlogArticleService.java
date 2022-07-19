package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.dto.BlogArchivesHzDto;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.req.BlogArticleInsertReq;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface IBlogArticleService extends IService<BlogArticleEntity> {

    /**
     * 发布文章按年-月汇总
     *
     * @return
     */
    List<BlogArchivesHzDto> queryArticleArchives();

    /**
     * 新增文章
     * @param req
     * @return
     */
    BlogArticleEntity insert(BlogArticleInsertReq req);

    /**
     * 更新
     * @param req
     * @return
     */
    BlogArticleEntity update(BlogArticleInsertReq req);
}
