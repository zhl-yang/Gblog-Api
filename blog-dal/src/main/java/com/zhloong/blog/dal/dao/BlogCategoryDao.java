package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.entity.BlogCategoryEntity;

import java.util.List;

/**
 * <p>
 * 文章类别表 Mapper 接口
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface BlogCategoryDao extends BaseMapper<BlogCategoryEntity> {

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
