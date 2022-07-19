package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.entity.BlogCategoryEntity;

import java.util.List;

/**
 * <p>
 * 文章类别表 服务类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface IBlogCategoryService extends IService<BlogCategoryEntity> {

    /**
     * 文章分类详情
     *
     * @return
     */
    List<BlogCategoryEntity> queryCategoryDetails();

    /**
     * 单条文章分类详情
     *
     * @return
     */
    BlogCategoryEntity queryOneCategoryDetail(Integer categoryId);
}
