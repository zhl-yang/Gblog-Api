package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户操作日志表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_log")
@ApiModel(value = "BlogLogEntity对象", description = "用户操作日志表")
public class BlogLogEntity implements Serializable {

    private static final long serialVersionUID = -3473216643445024032L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id")
    private Long id;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty("访问Ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("操作模块")
    @TableField("module")
    private String module;

    @ApiModelProperty("访问方法")
    @TableField("method")
    private String method;

    @ApiModelProperty("方法参数")
    @TableField("params")
    private String params;

    @ApiModelProperty("操作人昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("操作事项")
    @TableField("operation")
    private String operation;

    @ApiModelProperty("操作耗时")
    @TableField("time")
    private Long time;

    @ApiModelProperty("操作用户userId")
    @TableField("user_id")
    private Long userId;


}
