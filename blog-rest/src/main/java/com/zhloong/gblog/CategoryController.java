package com.zhloong.gblog;


import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.entity.BlogCategoryEntity;
import com.zhloong.blog.entity.BlogTagEntity;
import com.zhloong.blog.service.IBlogTagService;
import com.zhloong.gblog.rsp.BlogCategoryRsp;
import com.zhloong.blog.service.IBlogCategoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章类别表 前端控制器
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@RestController
@RequestMapping
public class CategoryController {

    @Resource
    private IBlogCategoryService categoryService;

    @Resource
    private IBlogTagService blogTagService;

    /**
     * 文章分类列表
     * @return
     */
    @GetMapping("/category")
    @Cacheable(value = "gblog-category-listRsp")
    @PassToken
    public Result<BlogCategoryRsp> getCategory () {
        List<BlogCategoryEntity> categoryEntityList = categoryService.list();
        List<BlogCategoryRsp> categoryRspList = categoryEntityList.stream().map(categoryEntity -> {
            BlogCategoryRsp categoryRsp = new BlogCategoryRsp();
            categoryRsp.setId(categoryEntity.getId());
            categoryRsp.setTitle(categoryEntity.getCategoryName());
            return categoryRsp;
        }).collect(Collectors.toList());
        return Result.ok(categoryRspList);
    }

    /**
     * 文章分类列表
     * @return
     */
    @GetMapping("tags")
    @Cacheable(value = "gblog-tags")
    @PassToken
    public Result<BlogTagEntity> getTags() {
        List<BlogTagEntity> tagEntities = this.blogTagService.list();
        return Result.ok(tagEntities);
    }

}

