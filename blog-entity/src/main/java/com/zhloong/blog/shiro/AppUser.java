package com.zhloong.blog.shiro;

import com.zhloong.blog.rsp.UserInfoRsp;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * SessionUser的上下文类，用于操作SessionUser类
 *
 * @author xiaoweiqian
 * @date 2021/09/06
 */
@Configuration
public class AppUser implements Serializable {

    private static final long serialVersionUID = -285642670937881886L;

    public static ThreadLocal<UserInfoRsp> SESSION_USER = new ThreadLocal<>();

    /**
     * 获取用户信息
     *
     * @return 企业管理员id
     * @author xiaoweiqian
     * @date 2021/09/06
     */
    public UserInfoRsp getUser() {
        UserInfoRsp sessionUser = SESSION_USER.get();
        if (null == sessionUser) {
            return null;
        }
        return sessionUser;
    }

    /**
     * 赋值SessionUser对象到ThreadLocal中
     *
     * @param userEntityRsp UserInfoRsp.UserEntityRsp对象，see {@link UserInfoRsp.UserEntityRsp}
     */
    public static void set(UserInfoRsp userEntityRsp) {
        SESSION_USER.set(userEntityRsp);
    }

    /**
     * 清空ThreadLocal中的SessionUser对象
     */
    public static void remove() {
        SESSION_USER.remove();
    }

    public Boolean isAdmin(){
        return getUser().getUserEntityRsp().getAdmin().equals(1);
    }
}
