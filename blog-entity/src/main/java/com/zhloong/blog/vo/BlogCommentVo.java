package com.zhloong.blog.vo;

import com.zhloong.blog.entity.BlogCommentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogCommentVo extends BlogCommentEntity {
    private String avatar;
    private String nickname;
}
