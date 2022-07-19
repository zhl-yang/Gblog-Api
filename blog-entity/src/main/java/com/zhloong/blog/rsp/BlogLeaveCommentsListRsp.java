package com.zhloong.blog.rsp;

import com.zhloong.blog.entity.BlogLeaveCommentsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 留言表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogLeaveCommentsListRsp extends BlogLeaveCommentsEntity implements Serializable {

    private static final long serialVersionUID = -4413646106798021918L;

    private String avatar;
    private String nickname;
    private String createDate;

    private List<BlogLeaveCommentsListRsp> childrens;



}
