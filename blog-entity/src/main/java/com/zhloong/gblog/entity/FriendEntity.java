package com.zhloong.gblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("gbolg_friend")
public class FriendEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String domain;
    private String content;
    private String type;
}
