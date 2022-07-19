package com.zhloong.blog.rsp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/4/15
 */
@Data
public class PublicKeyValueRsp implements Serializable {
    private static final long serialVersionUID = 8196636030865909476L;

    private Boolean mockServer = Boolean.TRUE;
    private String publicKey;

}
