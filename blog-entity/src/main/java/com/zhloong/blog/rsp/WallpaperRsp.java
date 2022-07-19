package com.zhloong.blog.rsp;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * The type Wallpaper rsp.
 *
 * @author zhloong
 * @date 2022 /4/2
 */
@Data
public class WallpaperRsp {

    private Integer id;
    private String name;
    private String order_num;
    private String tag;
    private Date create_time;


    private String class_id;
    private String resolution;
    private String url_mobile;
    private String url;
    private String url_thumb;
    private String url_mid;
    private String download_times;
    private String imgcut;

    private Date update_time;
    private String ad_id;
    private String ad_img;
    private String ad_pos;
    private String ad_url;
    private String ext_1;
    private String ext_2;
    private String utag;
    private String tempdata;
    private List<String> rdata;
    private String img_1600_900;
    private String img_1440_900;
    private String img_1366_768;
    private String img_1280_800;
    private String img_1280_1024;
    private String img_1024_768;



}