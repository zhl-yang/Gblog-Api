package com.zhloong.blog.rsp;

import lombok.Data;

import java.util.List;

/**
 * @author zhloong
 * @date 2022/4/2
 */
@Data
public class WallpaperResultRsp {

    private String errno;
    private String errmsg;
    private String consume;
    private String total;
    private List<WallpaperRsp> data;

}
