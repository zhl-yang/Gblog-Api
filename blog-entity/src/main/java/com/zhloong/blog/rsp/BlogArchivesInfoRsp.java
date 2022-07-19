package com.zhloong.blog.rsp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zhloong.blog.entity.BlogCategoryEntity;
import com.zhloong.blog.entity.BlogTagEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@Data
public class BlogArchivesInfoRsp implements Serializable {

    private static final long serialVersionUID = -2257874995584120918L;

    @ApiModelProperty("文章信息")
    private BlogArchives archives;

    @ApiModelProperty("分类信息")
    private BlogCategoryEntity category;

    @ApiModelProperty("标签信息")
    private List<BlogTagEntity> tags;

    /**
     * 文章详情
     */
    @Data
    public static class BlogArchives implements Serializable{

        private static final long serialVersionUID = -15212510102223534L;

        private Integer id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("文章摘要")
        private String summary;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("浏览数")
        private Integer viewNum;

        @ApiModelProperty("评论数")
        private Integer commentNum;

        @ApiModelProperty("创建时间")
        private String createTime;

        @ApiModelProperty("文章内容html")
        private String contentHtml;

        @ApiModelProperty("图片")
        private String banner;

    }

}
