package com.zhloong.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.BlogCategoryDao;
import com.zhloong.blog.entity.BlogCategoryEntity;
import com.zhloong.blog.service.IBlogCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章类别表 服务实现类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryDao, BlogCategoryEntity> implements IBlogCategoryService {

    /**
     * 文章分类详情
     *
     * @return
     */
    @Override
    public List<BlogCategoryEntity> queryCategoryDetails() {
        return this.baseMapper.queryCategoryDetails();
    }

    /**
     * 单条文章分类详情
     *
     * @return
     */
    @Override
    public BlogCategoryEntity queryOneCategoryDetail(Integer categoryId) {
        return this.baseMapper.queryOneCategoryDetail(categoryId);
    }
}
