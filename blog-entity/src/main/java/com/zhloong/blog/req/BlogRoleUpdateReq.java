package com.zhloong.blog.req;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">zhloong</a>
 * @since 2022-04-15
 */
@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "BlogRoleEntity对象", description = "")
public class BlogRoleUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String permission;


}
