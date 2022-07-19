package com.zhloong.blog.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/1/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleSearchReq extends Page implements Serializable {

    private static final long serialVersionUID = -3836110467721633984L;

    @ApiModelProperty("查询条件")
    private BlogArticleReq article;

    @Data
    public class BlogArticleReq implements Serializable {

        private static final long serialVersionUID = -3836110467721633984L;

        @ApiModelProperty("文章标题")
        private String title;

        @ApiModelProperty("文章标签")
        private Integer tagId;

        @ApiModelProperty("文章分类ID")
        private Integer categoryId;

    }

}
