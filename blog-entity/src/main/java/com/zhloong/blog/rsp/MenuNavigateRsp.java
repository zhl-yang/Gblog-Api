package com.zhloong.blog.rsp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/4/15
 */
@Data
public class MenuNavigateRsp implements Serializable {

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

    @ApiModelProperty("是否固定")
    @TableField("affix")
    private Integer affix;

    @ApiModelProperty("是否一直显示当前节点")
    @TableField("always_show")
    private Integer alwaysShow;

    @ApiModelProperty("是否无缓存")
    @TableField("noKeep_alive")
    private Integer nokeepAlive;

    @ApiModelProperty("标签")
    @TableField("badge")
    private String badge;

    @ApiModelProperty("父级route")
    @TableField("parent_rotue_id")
    private Integer parentRotueId;

    private MetaRsp meta;

    private List<ChildrenRsp> children;

    @Data
    public static class MetaRsp {
        @ApiModelProperty("标题")
        @TableField("title")
        private String title;

        @TableField("icon")
        private String icon;

        private List<String> permissions;
    }

    @Data
    public static class ChildrenRsp {
        @ApiModelProperty("路径")
        @TableField("path")
        private String path;

        @ApiModelProperty("相对路径")
        @TableField("component")
        private String component;

        @TableField("name")
        private String name;

        @ApiModelProperty("标题")
        @TableField("title")
        private String title;

        private MetaRsp meta;
    }

}
