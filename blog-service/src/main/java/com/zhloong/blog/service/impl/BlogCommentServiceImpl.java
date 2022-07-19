package com.zhloong.blog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.common.enums.LevelFlagEnum;
import com.zhloong.blog.dal.dao.BlogCommentDao;
import com.zhloong.blog.dto.ChildCommentDto;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.entity.BlogCommentEntity;
import com.zhloong.blog.req.BlogCommentReq;
import com.zhloong.blog.service.IBlogArticleService;
import com.zhloong.blog.service.IBlogCommentService;
import com.zhloong.blog.shiro.AppUser;
import com.zhloong.blog.vo.BlogCommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentDao, BlogCommentEntity> implements IBlogCommentService {

    @Resource
    private IBlogArticleService articleService;

    @Resource
    private AppUser appUser;

    /**
     * 获取文章评论信息
     *
     * @param articleId
     * @return
     */
    @Override
    public List<BlogCommentVo> queryArticleComments(Long articleId) {
        return this.baseMapper.queryArticleComments(articleId);
    }

    /**
     * 获取子评论信息
     *
     * @param dto
     * @return
     */
    @Override
    public List<BlogCommentVo> queryChildrenComments(ChildCommentDto dto) {
        return this.baseMapper.queryChildrenComments(dto);
    }

    /**
     * 发布文章评论
     *
     * @param commentReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogCommentVo publishArticleComment(BlogCommentReq commentReq) {
        // 1、更新文章评论数+1
        // 文章信息
        articleService.update(new UpdateWrapper<BlogArticleEntity>()
                .lambda()
                .eq(BlogArticleEntity::getId, commentReq.getArticleId())
                .setSql(" comment_num = comment_num + 1 "));

        BlogArticleEntity articleEntity = articleService.getById(commentReq.getArticleId());

        if(ObjectUtil.isEmpty(articleEntity)){

        }
        // 2、插入一条文章评论
        BlogCommentEntity comment = new BlogCommentEntity();
        comment.setArticleId(articleEntity.getId());
        comment.setContent(commentReq.getContent());
        comment.setCreateTime(DateUtil.date().toTimestamp().toLocalDateTime());
        comment.setUpdateTime(DateUtil.date().toTimestamp().toLocalDateTime());
        comment.setUserId(appUser.getUser().getUserEntityRsp().getId());

        // 3、设置文章评论级别 level_flag
        //    为0:评论文章;1:评论某人评论;2:回复某人评论
        if(ObjectUtil.isEmpty(commentReq.getParentId())){
            comment.setLevelFlag(LevelFlagEnum.COMMENT_ARTICLE.getCode());
        } else if(ObjectUtil.isEmpty(commentReq.getToUid())){
            comment.setLevelFlag(LevelFlagEnum.COMMENT_PARENT.getCode());
            comment.setParentId(commentReq.getParentId());
        } else {
            comment.setLevelFlag(LevelFlagEnum.COMMENT_TO_USER.getCode());
            comment.setToUid(commentReq.getToUid());
            comment.setParentId(commentReq.getParentId());
        }

        this.save(comment);

        BlogCommentVo commentVo = new BlogCommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        commentVo.setAvatar(appUser.getUser().getUserEntityRsp().getAvatar());
        commentVo.setUserId(appUser.getUser().getUserEntityRsp().getId());
        commentVo.setNickname(appUser.getUser().getUserEntityRsp().getNickname());

        return commentVo;
    }
}
