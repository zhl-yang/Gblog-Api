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
 * 路由权限表
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">zhloong</a>
 * @since 2022-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_route_role")
@ApiModel(value = "BlogRouteRoleEntity对象", description = "路由权限表")
public class BlogRouteRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    @TableField("route_id")
    private Integer routeId;

    @TableField("role_id")
    private Integer roleId;


}
