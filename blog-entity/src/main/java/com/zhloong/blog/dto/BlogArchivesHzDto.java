package com.zhloong.blog.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhloong
 */
@Data
public class BlogArchivesHzDto implements Serializable {

    private static final long serialVersionUID = 5366328678841688679L;

    private String year;
    private String month;
    private Integer count;
}
