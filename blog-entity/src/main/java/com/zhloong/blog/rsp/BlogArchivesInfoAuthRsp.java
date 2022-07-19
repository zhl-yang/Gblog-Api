package com.zhloong.blog.rsp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhloong
 * @date 2022/1/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BlogArchivesInfoAuthRsp extends BlogArchivesInfoRsp implements Serializable {

    private static final long serialVersionUID = 450825514495407497L;

    @ApiModelProperty("用户信息")
    private UserAuthor author;

    @Data
    public static class UserAuthor implements Serializable{

        private static final long serialVersionUID = -8381204165050389322L;

        private Long id;
        private String avatar;
        private String nickname;
    }

}
