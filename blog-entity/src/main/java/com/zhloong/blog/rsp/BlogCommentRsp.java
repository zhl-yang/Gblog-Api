package com.zhloong.blog.rsp;

import com.zhloong.blog.entity.BlogCommentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogCommentRsp extends BlogCommentEntity implements Serializable {

    private static final long serialVersionUID = 7102202588625641724L;

    private String avatar;
    private String nickname;
    private String createDate;
    private List<BlogCommentRsp> childrens;
}
