package com.zhloong.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.BlogTagDao;
import com.zhloong.blog.entity.BlogTagEntity;
import com.zhloong.blog.service.IBlogTagService;
import com.zhloong.blog.vo.BlogTagVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author zhloong
 * @since 2022-01-20
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagDao, BlogTagEntity> implements IBlogTagService {

    /**
     * 获取标签详情
     *
     * @param tagIds
     * @return
     */
    @Override
    public List<BlogTagEntity> queryHotTagDetails(Integer[] tagIds){
        if (tagIds == null || tagIds.length <= 0){
            return this.baseMapper.selectList(null);
        } else {
            return this.baseMapper.queryHotTagDetails(tagIds);
        }
    }

    /**
     * 查询标签详情
     *
     * @return
     */
    @Override
    public List<BlogTagVo> queryTagDetails() {
        return this.baseMapper.queryTagDetails();
    }

    /**
     * 查询单条标签详情
     *
     * @return
     */
    @Override
    public BlogTagVo queryOneTagDetail(Integer tagId) {
        return this.baseMapper.queryOneTagDetail(tagId);
    }

}
