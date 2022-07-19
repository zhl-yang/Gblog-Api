package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.dto.ChildCommentDto;
import com.zhloong.blog.entity.BlogCommentEntity;
import com.zhloong.blog.req.BlogCommentReq;
import com.zhloong.blog.vo.BlogCommentVo;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface IBlogCommentService extends IService<BlogCommentEntity> {

    /**
     * 获取文章评论信息
     *
     * @param articleId
     * @return
     */
    List<BlogCommentVo> queryArticleComments(Long articleId);

    /**
     * 获取子评论信息
     *
     * @param dto
     * @return
     */
    List<BlogCommentVo> queryChildrenComments(ChildCommentDto dto);

    /**
     * 发布文章评论
     *
     * @param commentReq
     * @return
     */
    BlogCommentVo publishArticleComment(BlogCommentReq commentReq);
}
