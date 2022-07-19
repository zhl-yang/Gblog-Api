package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/4/15
 */
@Data
public class BlogUserInfoRsp implements Serializable {

    private static final long serialVersionUID = -4413646106798021918L;

    private String userName;
    private List<String> permissions;
    private Integer userId;
    @ApiModelProperty("是否admin，0不是，1是")
    private Integer admin;
}
