package com.zhloong.blog.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@Data
public class BlogLeaveCommentReq {

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论评论")
    private Long parentId;

    @ApiModelProperty("回复某人")
    private Long toUid;

}
