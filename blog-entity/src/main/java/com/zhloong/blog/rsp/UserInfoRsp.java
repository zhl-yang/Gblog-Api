package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * 
 * @author liangfeihu
 * @email liangfhhd@163.com
 * @date 2018-07-04 15:00:54
 */
@ApiModel("用户信息")
@Data
public class UserInfoRsp implements Serializable {

	private static final long serialVersionUID = 2758010901801812637L;

	private UserEntityRsp userEntityRsp;

	private TokenRsp tokenRsp;

	/**
	 * 角色权限
	 */
	private List<String> permissions;

	@ApiModelProperty("过期时间")
	private Date expire;

	private String accessToken;

	@ApiModel("用户信息")
	@Data
	public static class UserEntityRsp implements Serializable {

		private static final long serialVersionUID = -7848179983162111658L;

		@ApiModelProperty("id")
		private Integer id;

		@ApiModelProperty("登录账号")
		private String userName;

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

	@Data
	public static class TokenRsp implements Serializable {

		private static final long serialVersionUID = 6107501978791535607L;
		@ApiModelProperty("过期时间")
		private Date expire;

		private String token;
	}
}
