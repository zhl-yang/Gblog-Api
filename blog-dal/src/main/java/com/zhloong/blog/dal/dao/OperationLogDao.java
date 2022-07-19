package com.zhloong.blog.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhloong.blog.entity.OperationLogEntity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">Erwin Feng</a>
 * @since 2022-04-04
 */
public interface OperationLogDao extends BaseMapper<OperationLogEntity> {

    /**
     * 获取UV
     * @return
     */
    Integer getUv();
}
