package com.zhloong.blog.req;

import lombok.Data;

/**
 * @author zhloong
 * @date 2022/4/17
 */
@Data
public class UserManagementPageReq {
    private int pageNumber;
    private int pageSize;
}
