package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.entity.BlogTagEntity;
import com.zhloong.blog.vo.BlogTagVo;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
public interface IBlogTagService extends IService<BlogTagEntity> {

    /**
     * 获取标签详情
     *
     * @param tagIds
     * @return
     */
    List<BlogTagEntity> queryHotTagDetails(Integer[] tagIds);

    /**
     * 列表
     * @return
     */
    List<BlogTagVo> queryTagDetails();

    /**
     * 查询单个标签详情
     * @param tagId
     * @return
     */
    BlogTagVo queryOneTagDetail(Integer tagId);
}
