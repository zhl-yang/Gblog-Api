package com.zhloong.blog.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class WallpaperInfoReq implements Serializable {

    private static final long serialVersionUID = 3108886375985259785L;

    @ApiModelProperty("tagId")
    private Long classId;

    @ApiModelProperty("壁纸尺寸")
    private String resolution;

}
