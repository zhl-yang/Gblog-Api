package com.zhloong.blog.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 * 
 * @author liangfeihu
 * @email liangfhhd@163.com
 * @date 2018-07-04 15:00:54
 */
@Data
public class UserRemoveReq implements Serializable {

	private static final long serialVersionUID = 5698629743089574176L;

	/**
	 * 主键ID
	 */
	private Integer[] ids;

}
