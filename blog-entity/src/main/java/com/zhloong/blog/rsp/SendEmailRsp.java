package com.zhloong.blog.rsp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/4/4
 */
@Data
public class SendEmailRsp implements Serializable {

    private static final long serialVersionUID = -6813284299908795445L;

    private Boolean status;
    private String msg;
}
