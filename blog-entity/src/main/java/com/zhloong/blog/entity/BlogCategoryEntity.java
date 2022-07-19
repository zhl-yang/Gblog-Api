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
 * 文章类别表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_category")
@ApiModel(value = "BlogCategoryEntity对象", description = "文章类别表")
public class BlogCategoryEntity implements Serializable {

    private static final long serialVersionUID = 6151802049986257837L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty("类别名字")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty("类别图标")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("分类描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;


}
