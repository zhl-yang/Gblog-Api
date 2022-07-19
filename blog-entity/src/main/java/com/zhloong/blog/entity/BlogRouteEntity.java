package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 路由表
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">zhloong</a>
 * @since 2022-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_route")
@ApiModel(value = "BlogRouteEntity对象", description = "路由表")
public class BlogRouteEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("相对路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("是否重定向")
    @TableField("redirect")
    private String redirect;

    @TableField("name")
    private String name;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @TableField("icon")
    private String icon;

    @ApiModelProperty("是否固定")
    @TableField("affix")
    private Boolean affix;

    @ApiModelProperty("是否一直显示当前节点")
    @TableField("always_show")
    private Boolean alwaysShow;

    @ApiModelProperty("是否无缓存")
    @TableField("noKeep_alive")
    private Boolean nokeepAlive;

    @ApiModelProperty("标签")
    @TableField("badge")
    private String badge;

    @ApiModelProperty("父级route")
    @TableField("parent_rotue_id")
    private Integer parentRotueId;

    @ApiModelProperty("标签级别")
    @TableField("sort")
    private Integer sort;

    @TableField("delete_time")
    private Integer deleteTime;


}
