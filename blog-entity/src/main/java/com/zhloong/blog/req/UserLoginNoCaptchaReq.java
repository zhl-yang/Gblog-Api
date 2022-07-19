package com.zhloong.blog.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/1/17
 */
@ApiModel("登陆对象")
@Data
public class UserLoginNoCaptchaReq implements Serializable {

    private static final long serialVersionUID = 3722007774210247761L;

    @ApiModelProperty("账号")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String passWord;
}
