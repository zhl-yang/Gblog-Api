package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.dto.BlogArchivesHzDto;
import com.zhloong.blog.entity.BlogArticleEntity;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface BlogArticleDao extends BaseMapper<BlogArticleEntity> {


    /**
     * 发布文章按年-月汇总
     *
     * @param limit
     * @return
     */
    List<BlogArchivesHzDto> queryArticleArchives(Integer limit);
}
