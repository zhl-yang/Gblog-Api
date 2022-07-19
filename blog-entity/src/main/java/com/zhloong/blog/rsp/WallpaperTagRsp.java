package com.zhloong.blog.rsp;

import com.zhloong.blog.entity.WallpaperTagEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/4/10
 */
@Data
public class WallpaperTagRsp implements Serializable {

    private static final long serialVersionUID = 6284606067505053530L;

    private List<WallpaperTagEntity> tags;

    private List<WallpaperSizeRsp> sizes;

    @Data
    public static class  WallpaperSizeRsp implements Serializable{

        private static final long serialVersionUID = -5209218403716976432L;

        private String code;
        private String des;
    }

}
