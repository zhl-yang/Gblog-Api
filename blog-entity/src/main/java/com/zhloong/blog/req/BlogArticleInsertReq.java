package com.zhloong.blog.req;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@ApiModel(value = "BlogArticleEntity对象", description = "文章表")
public class BlogArticleInsertReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("作者昵称")
    private String nickname;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章摘要")
    private String summary;

    @ApiModelProperty("文章内容txt")
    private String content;

    @ApiModelProperty("文章内容html")
    private String contentHtml;

    @ApiModelProperty("浏览数")
    private Integer viewNum;

    @ApiModelProperty("评论数")
    private Integer commentNum;

    @ApiModelProperty("权重")
    @NotNull(message = "权重不能为空")
    @Min(value = 0, message = "权重不能小于0")
    @Max(value = 100, message = "权重不能大于100")
    private Integer weight;

    @ApiModelProperty("文章标签")
    private List<Integer> tags;

    @ApiModelProperty("文章分类ID")
    private Integer categoryId;

    @ApiModelProperty("创建时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

}
