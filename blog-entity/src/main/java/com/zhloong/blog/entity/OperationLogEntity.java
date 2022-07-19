package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">Erwin Feng</a>
 * @since 2022-04-04
 */
@Getter
@Setter
@TableName("operation_log")
public class OperationLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("ip")
    private String ip;

    @TableField("browser_name")
    private String browserName;

    @TableField("browser_version")
    private String browserVersion;

    @TableField("browser_system")
    private String browserSystem;

    @TableField("url")
    private String url;

    @TableField("user_id")
    private Integer userId;

    @TableField("param")
    private String param;

    @TableField("create_time")
    private LocalDateTime createTime;


}
