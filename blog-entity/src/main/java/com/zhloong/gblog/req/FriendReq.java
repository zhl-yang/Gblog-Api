package com.zhloong.gblog.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author zhloong
 */
@Data
public class FriendReq {
    @NotBlank(message = "名称不能为空")
    @Length(min = 1, max = 30, message = "名称长度必须在1-30之间")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Length(min = 1, max = 100, message = "邮箱长度必须在1-100之间")
    private String email;

    @NotBlank(message = "站点地址不能为空")
    @URL(message = "站点地址不合法")
    @Length(min = 1, max = 500, message = "站点地址长度必须在1-500之间")
    private String domain;

    @NotBlank(message = "内容不能为空")
    @Length(min = 1, max = 500, message = "内容长度必须在1-500之间")
    private String content;
}
