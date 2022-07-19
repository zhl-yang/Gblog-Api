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
 * 文章表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_article")
@ApiModel(value = "BlogArticleEntity对象", description = "文章表")
public class BlogArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("作者昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("文章摘要")
    @TableField("summary")
    private String summary;

    @ApiModelProperty("文章内容txt")
    @TableField("content")
    private String content;

    @ApiModelProperty("文章内容html")
    @TableField("content_html")
    private String contentHtml;

    @ApiModelProperty("浏览数")
    @TableField("view_num")
    private Integer viewNum;

    @ApiModelProperty("评论数")
    @TableField("comment_num")
    private Integer commentNum;

    @ApiModelProperty("权重")
    @TableField("weight")
    private Integer weight;

    @ApiModelProperty("文章标签")
    @TableField("tags")
    private String tags;

    @ApiModelProperty("图片")
    @TableField("banner")
    private String banner;

    @ApiModelProperty("文章分类ID")
    @TableField("category_id")
    private Integer categoryId;

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

    @ApiModelProperty("删除时间")
    private Integer deleteTime;

}
