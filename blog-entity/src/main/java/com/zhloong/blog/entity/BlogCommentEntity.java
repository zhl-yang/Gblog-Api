package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_comment")
@ApiModel(value = "BlogCommentEntity对象", description = "评论表")
public class BlogCommentEntity implements Serializable {

    private static final long serialVersionUID = 8230927590427068238L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("文章ID")
    @TableField("article_id")
    private Integer articleId;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("父评论Id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("评论的评论用户ID")
    @TableField("to_uid")
    private Integer toUid;

    @ApiModelProperty("评论级别")
    @TableField("level_flag")
    private String levelFlag;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;


}
