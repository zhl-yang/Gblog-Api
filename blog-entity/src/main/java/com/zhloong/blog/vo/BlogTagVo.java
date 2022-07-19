package com.zhloong.blog.vo;

import com.zhloong.blog.entity.BlogTagEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogTagVo extends BlogTagEntity {

    @ApiModelProperty("tag详情")
    private Integer articles;
}
