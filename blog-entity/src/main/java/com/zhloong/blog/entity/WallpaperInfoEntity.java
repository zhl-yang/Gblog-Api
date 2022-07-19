package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">Erwin Feng</a>
 * @since 2022-04-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("wallpaper_info")
public class WallpaperInfoEntity implements Serializable {

    private static final long serialVersionUID = 3108886375985259785L;

    @TableId(value = "id")
    private Long id;

    @TableField("class_id")
    private Long classId;

    @TableField("resolution")
    private String resolution;

    @TableField("url_mobile")
    private String urlMobile;

    @TableField("url")
    private String url;

    @TableField("url_thumb")
    private String urlThumb;

    @TableField("url_mid")
    private String urlMid;

    @TableField("download_times")
    private String downloadTimes;

    @TableField("imgcut")
    private Integer imgcut;

    @TableField("tag")
    private String tag;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("update_time")
    private LocalDate updateTime;

    @TableField("ad_id")
    private String adId;

    @TableField("ad_img")
    private String adImg;

    @TableField("ad_pos")
    private String adPos;

    @TableField("ad_url")
    private String adUrl;

    @TableField("ext_1")
    private String ext1;

    @TableField("ext_2")
    private String ext2;

    @TableField("utag")
    private String utag;

    @TableField("tempdata")
    private String tempdata;

    @TableField("img_1600_900")
    private String img1600900;

    @TableField("img_1440_900")
    private String img1440900;

    @TableField("img_1366_768")
    private String img1366768;

    @TableField("img_1280_800")
    private String img1280800;

    @TableField("img_1280_1024")
    private String img12801024;

    @TableField("img_1024_768")
    private String img1024768;


}
