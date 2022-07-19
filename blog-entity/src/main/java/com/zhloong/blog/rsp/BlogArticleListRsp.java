package com.zhloong.blog.rsp;

import com.zhloong.blog.entity.BlogTagEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Data
@ApiModel(value = "BlogArticleEntity列表返回", description = "文章")
public class BlogArticleListRsp implements Serializable {

    private static final long serialVersionUID = 5128132260094273988L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("作者昵称")
    private String nickname;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章摘要")
    private String summary;

    @ApiModelProperty("浏览数")
    private Integer viewNum;

    @ApiModelProperty("评论数")
    private Integer commentNum;

    @ApiModelProperty("权重")
    private Integer weight;

    @ApiModelProperty("文章标签")
    private List<BlogTagEntity> tags;

    @ApiModelProperty("创建时间")
    private String createTime;

}
