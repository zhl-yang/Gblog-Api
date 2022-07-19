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
 * 标签表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_tag")
@ApiModel(value = "BlogTagEntity对象", description = "标签表")
public class BlogTagEntity implements Serializable {

    private static final long serialVersionUID = 4330966525934520692L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty("标签名字")
    @TableField("tag_name")
    private String tagName;

    @ApiModelProperty("标签图标")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;


}
