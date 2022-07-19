package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhloong
 * @date 2022/1/27
 */
@Data
public class UploadRsp {

    @ApiModelProperty("文件地址")
    private String url;
}
