package com.zhloong.blog.vo;

import com.zhloong.blog.entity.BlogLeaveCommentsEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class BlogLeaveCommentsVo extends BlogLeaveCommentsEntity implements Serializable {

    private static final long serialVersionUID = 3644567958380508408L;

    private String avatar;
    private String nickname;

}
