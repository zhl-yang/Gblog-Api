package com.zhloong.gblog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhloong
 */
@Data
public class SiteRsp {

    private String avatar = "https://s1.ax1x.com/2022/07/15/jhlfH0.jpg";
    private String slogan = "The way up is not crowded, and most chose ease.";
    private String name = "春风而至";
    private String domain = "https://blog.zhloong.xyz";
    private String notice = "本博客的数据由个人上传编写，不代表本博客立在任何一个观点上，也不代表本博客对任何一个人的观点。";
    private String desc = "The way up is not crowded, and most chose ease.";
    @ApiModelProperty(value = "访问量")
    private Integer uv;

}
