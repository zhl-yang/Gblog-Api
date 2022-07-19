package com.zhloong.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 留言表
 * </p>
 *
 * @author zhloong
 * @since 2022-01-28
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("blog_leave_comments")
public class BlogLeaveCommentsEntity implements Serializable {

    private static final long serialVersionUID = -6451183710165403429L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 留言内容
     */
    @TableField("content")
    private String content;

    /**
     * 父留言Id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 留言的留言用户ID
     */
    @TableField("to_uid")
    private Long toUid;

    /**
     * 留言级别
     */
    @TableField("level_flag")
    private String levelFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除状态
     */
    @TableField("delete_status")
    private Integer deleteStatus;
}
