package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.vo.BlogTagVo;
import com.zhloong.blog.entity.BlogTagEntity;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface BlogTagDao extends BaseMapper<BlogTagEntity> {

    /**
     * 获取标签详情
     *
     * @param tagIds
     * @return
     */
    List<BlogTagEntity> queryHotTagDetails(Integer[] tagIds);

    /**
     * 查询标签详情
     *
     * @return
     */
    List<BlogTagVo> queryTagDetails();

    /**
     * 查询单条标签详情
     *
     * @return
     */
    BlogTagVo queryOneTagDetail(Integer tagId);
}
