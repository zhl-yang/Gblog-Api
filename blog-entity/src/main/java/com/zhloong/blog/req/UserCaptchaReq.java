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
public class UserCaptchaReq implements Serializable {

    private static final long serialVersionUID = -537765408549379331L;

    @ApiModelProperty("账号")
    @NotBlank(message = "账号不能为空")
    @Email
    private String email;
}
