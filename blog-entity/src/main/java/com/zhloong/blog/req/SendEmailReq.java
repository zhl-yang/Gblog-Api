package com.zhloong.blog.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/4/4
 */
@Data
public class SendEmailReq implements Serializable {

    private static final long serialVersionUID = 6422259311288101101L;

    /**
     * 发件人邮箱地址
     */
    private String mail_from;

    /**
     * 发件人邮箱密码
     */
    private String password;

    /**
     * 收件人地址，多个用英文逗号隔开
     */
    private String mail_to;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件类型，不传默认为plain，要发送html请传html
     */
    private String subtype;
}
