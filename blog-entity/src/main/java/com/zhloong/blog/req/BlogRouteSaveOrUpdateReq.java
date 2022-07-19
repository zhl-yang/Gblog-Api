package com.zhloong.blog.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class BlogRouteSaveOrUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("相对路径")
    private String component;

    @ApiModelProperty("是否重定向")
    private String redirect;

    private String name;

    @ApiModelProperty("标题")
    private String title;

    private String icon;

    @ApiModelProperty("是否固定")
    private Boolean affix;

    @ApiModelProperty("是否一直显示当前节点")
    private Boolean alwaysShow;

    @ApiModelProperty("是否无缓存")
    private Boolean nokeepAlive;

    @ApiModelProperty("标签")
    private String badge;

    @ApiModelProperty("父级route")
    private Integer parentRotueId;

    @ApiModelProperty("标签级别")
    private Integer sort;

    private Integer deleteTime;


}
