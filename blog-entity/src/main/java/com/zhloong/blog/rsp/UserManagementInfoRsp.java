package com.zhloong.blog.rsp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户表
 *
 * @author liangfeihu
 * @email liangfhhd@163.com
 * @date 2018-07-04 15:00:54
 */
@Data
public class UserManagementInfoRsp implements Serializable {

    private static final long serialVersionUID = 5698629743089574176L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 登录账号
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 上次登录时间
     */
    private String lastLoginTime;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 是否admin，0不是，1是
     */
    private Integer admin;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

    private List<String> permissions;

}
