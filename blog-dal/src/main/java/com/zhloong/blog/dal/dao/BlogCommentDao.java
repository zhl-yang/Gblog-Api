package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.dto.ChildCommentDto;
import com.zhloong.blog.vo.BlogCommentVo;
import com.zhloong.blog.entity.BlogCommentEntity;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface BlogCommentDao extends BaseMapper<BlogCommentEntity> {

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
}
