package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/1/28
 */
@Data
public class BlogArticleHotOrNewRsp implements Serializable {
    private static final long serialVersionUID = -5618592616311141890L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("文章标题")
    private String title;
}
