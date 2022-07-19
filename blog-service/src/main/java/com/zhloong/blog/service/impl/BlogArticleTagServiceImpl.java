package com.zhloong.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.BlogArticleTagDao;
import com.zhloong.blog.entity.BlogArticleTagEntity;
import com.zhloong.blog.entity.BlogTagEntity;
import com.zhloong.blog.service.IBlogArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagDao, BlogArticleTagEntity> implements IBlogArticleTagService {


    /**
     * 获取文章所有标签
     *
     * @param articleId
     * @return
     */
    @Override
    public List<BlogTagEntity> queryArticleTags(Integer articleId) {
        return this.baseMapper.queryArticleTags(articleId);
    }
}
