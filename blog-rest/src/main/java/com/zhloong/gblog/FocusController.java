package com.zhloong.gblog;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.entity.BlogArticleEntity;
import com.zhloong.blog.service.IBlogArticleService;
import com.zhloong.gblog.rsp.FocusRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/focus")
@ResponseBody
@Slf4j
public class FocusController {

    @Resource
    private IBlogArticleService articleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PassToken
    public Result<FocusRsp> getList() {
        List<BlogArticleEntity> pageList = articleService.list(new LambdaQueryWrapper<BlogArticleEntity>()
                .eq(BlogArticleEntity::getDeleteTime, 0)
                .orderByDesc(BlogArticleEntity::getViewNum)
                .last(" limit 0,3"));
        List<FocusRsp> rspList = pageList.stream().map(article -> {
            FocusRsp rsp = new FocusRsp();
            BeanUtils.copyProperties(article, rsp);
            rsp.setId(article.getId());
            rsp.setTitle(article.getTitle());
            rsp.setImg(article.getBanner());
            return rsp;
        }).collect(Collectors.toList());
        return Result.ok(rspList);
    }
}
