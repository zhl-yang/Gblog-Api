package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhloong
 * @date 2022/4/2
 */
@TableName("wallpaper_tag")
@Data
public class WallpaperTagEntity implements Serializable {

    private static final long serialVersionUID = 4437613182985255326L;

    private Integer id;
    private String name = "其他分类";
    private String order_num;
    private String tag;
    private Date create_time;

}
