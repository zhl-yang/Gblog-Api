package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/1/26
 */
@Data
public class BaiduDataRsp implements Serializable {

    private static final long serialVersionUID = -2372421388051888180L;

    @ApiModelProperty("当天剩余的可推送url条数")
    private Integer remain;

    @ApiModelProperty("成功推送的url条数")
    private Integer success;

    @ApiModelProperty("由于不是本站url而未处理的url列表")
    private List<String> not_same_site;

    @ApiModelProperty("不合法的url列表")
    private List<String> not_valid;

    @ApiModelProperty("错误码，与状态码相同")
    private Integer error;

    @ApiModelProperty("错误描述")
    private String message;


}
