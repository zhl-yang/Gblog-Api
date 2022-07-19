package com.zhloong.blog.rsp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 *  壁纸结果返回
 * </p>
 *
 * @author zhloong
 * @since 2022-04-02
 */
@Getter
@Setter
public class WallpaperInfoRsp implements Serializable {

    private static final long serialVersionUID = 3108886375985259785L;

    private Long id;

    private Long classId;

    private String resolution;

    private String urlMobile;

    private String url;

    private String urlThumb;

    private String urlMid;

    private String downloadTimes;

    private String imgcut;

    private String tag;

    private LocalDate createTime;

    private LocalDate updateTime;

    private String adId;

    private String adImg;

    private String adPos;

    private String adUrl;

    private String ext1;

    private String ext2;

    private String utag;

    private String tempdata;

    private String img1600900;

    private String img1440900;

    private String img1366768;

    private String img1280800;

    private String img12801024;

    private String img1024768;


}
