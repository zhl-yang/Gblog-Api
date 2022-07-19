package com.zhloong.blog.common.enums;

/**
 * @author zhloong
 * @date 2022/1/21
 */
public enum LevelFlagEnum {
    COMMENT_ARTICLE("0", "为0:评论文章"),
    COMMENT_PARENT("1", "1:评论某人评论"),
    COMMENT_TO_USER("2", "2:回复某人评论"),

    ;


    private String code;
    private String des;

    LevelFlagEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }

    public String getCode() {
        return this.code;
    }

    public String getDes() {
        return this.des;
    }
}