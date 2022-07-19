package com.zhloong.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author liangfeihu
 * @email liangfhhd@163.com
 * @date 2018-07-04 15:00:54
 */
@Data
public class UserEntityDto implements Serializable {

	private static final long serialVersionUID = -5108160935601650418L;

	@ApiModelProperty("id")
	private Long id;

	@ApiModelProperty("登录账号")
	private String account;

	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("手机号")
	private String phone;

	@ApiModelProperty("上次登录时间")
	private Date lastLoginTime;

	@ApiModelProperty("用户昵称")
	private String nickname;

	@ApiModelProperty("用户头像")
	private String avatar;

	@ApiModelProperty("是否admin，0不是，1是")
	private Integer admin;

	@ApiModelProperty("用户状态")
	private String status;

	@ApiModelProperty("创建时间")
	private Date createTime;

	@ApiModelProperty("更新时间")
	private Date updateTime;

}
