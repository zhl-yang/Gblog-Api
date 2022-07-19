package com.zhloong.admin;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.dto.ChildCommentDto;
import com.zhloong.blog.entity.BlogCommentEntity;
import com.zhloong.blog.req.BlogCommentReq;
import com.zhloong.blog.rsp.BlogCommentRsp;
import com.zhloong.blog.service.IBlogCommentService;
import com.zhloong.blog.vo.BlogCommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/comments")
public class BlogCommentController {

    @Resource
    private IBlogCommentService commentService;

    /**
     * 获取某篇文章的评论
     */
    @RequestMapping("/article/{id}")
    @Cacheable(value = "commentsById", key = "#id")
    @PassToken
    public Result<BlogCommentRsp> info(@PathVariable("id") Long id) {
        List<BlogCommentVo> commentVos = commentService.queryArticleComments(id);
        List<BlogCommentRsp> array = new ArrayList<>();

        for (BlogCommentVo vo : commentVos) {
            BlogCommentRsp rsp = formatCommentInfo(vo);
            int childCount = commentService.count(new LambdaQueryWrapper<BlogCommentEntity>()
                    .eq(BlogCommentEntity::getArticleId, vo.getArticleId())
                    .eq(BlogCommentEntity::getParentId, vo.getId())
            );
            if (childCount > 0) {
                List<BlogCommentVo> commentVoList = commentService.queryChildrenComments(new ChildCommentDto(vo.getArticleId(), vo.getId()));
                rsp.setChildrens(formatChildCommentInfo(commentVoList));
            }
            array.add(rsp);
        }

        return Result.ok(array);
    }

    /**
     * 格式化评论数据
     *
     * @param vo
     * @return
     */
    private BlogCommentRsp formatCommentInfo(BlogCommentVo vo) {
        BlogCommentRsp rsp = new BlogCommentRsp();
        BeanUtils.copyProperties(vo, rsp);
        rsp.setCreateDate(DateUtil.format(vo.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
        return rsp;
    }

    /**
     * 获取子评论信息
     *
     * @param commentVoList
     * @return
     */
    private List<BlogCommentRsp> formatChildCommentInfo(List<BlogCommentVo> commentVoList) {
        List<BlogCommentRsp> commentRps = new ArrayList<>();
        for (BlogCommentVo vo : commentVoList) {
            BlogCommentRsp rsp = formatCommentInfo(vo);

            if (vo.getToUid() != null) {
                // TODO 这里需要添加用户逻辑
                rsp.setAvatar("");
                rsp.setUserId(0);
                rsp.setNickname("");

            }
            commentRps.add(rsp);
        }
        return commentRps;
    }

    /**
     * 发表评论
     */
    @PostMapping("/create/change")
    @CacheEvict(value = "commentsById", key = "#req.articleId")
    public Result<BlogCommentVo> save(@RequestBody BlogCommentReq req) {
        if (SensitiveWordHelper.contains(req.toString())) {
            return Result.error("请勿发布涉及政治、广告、营销、翻墙、违反国家法律法规等内容。");
        }
        BlogCommentVo object = commentService.publishArticleComment(req);
        return Result.ok(object);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        commentService.removeByIds(Arrays.asList(ids));
        return Result.ok(Boolean.TRUE);
    }

}

