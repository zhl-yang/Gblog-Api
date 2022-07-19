package com.zhloong.blog.rsp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/1/19
 */
@Data
public class CaptchaRsp implements Serializable {
    private static final long serialVersionUID = 8196636030865909476L;

    private String captcha;

    private String uuid;
}
