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
@ApiModel("注册用户")
@Data
public class UserRegisterReq implements Serializable {

    private static final long serialVersionUID = -609099395372809285L;

    @ApiModelProperty("账号")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String passWord;

    @ApiModelProperty("验证码ID")
    private String uuid;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    private String captcha;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

}
