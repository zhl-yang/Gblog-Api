package com.zhloong.admin;


import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.entity.BlogCategoryEntity;
import com.zhloong.blog.service.IBlogCategoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 文章类别表 前端控制器
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/category")
public class BlogCategoryController {

    @Resource
    private IBlogCategoryService categoryService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @Cacheable(value = "categoryList")
    public Result<BlogCategoryEntity> list() {
        List<BlogCategoryEntity> categoryEntityList = categoryService.list();
        return Result.ok(categoryEntityList);
    }

    /**
     * 分类详情
     */
    @GetMapping("/detail")
    @Cacheable(value = "categoryDetail")
    public Result<BlogCategoryEntity> detail() {
        List<BlogCategoryEntity> categoryVos = categoryService.queryCategoryDetails();
        return Result.ok(categoryVos);
    }

    /**
     * 单条分类详情
     */
    @GetMapping("/detail/{categoryId}")
    @Cacheable(value = "categoryById", key = "#categoryId")
    public Result<BlogCategoryEntity> detailById(@PathVariable("categoryId") Integer categoryId) {
        BlogCategoryEntity categoryVo = categoryService.queryOneCategoryDetail(categoryId);
        return Result.ok(categoryVo);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody BlogCategoryEntity category) {
        categoryService.save(category);
        return Result.ok(Boolean.TRUE);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody @Valid BlogCategoryEntity category) {
        //全部更新
        categoryService.updateById(category);
        return Result.ok(Boolean.TRUE);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        categoryService.removeByIds(Arrays.asList(ids));
        return Result.ok(Boolean.TRUE);
    }
}

