package com.zhloong.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhloong.blog.entity.OperationLogEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">Erwin Feng</a>
 * @since 2022-04-04
 */
public interface IOperationLogService extends IService<OperationLogEntity> {

    /**
     * 获取UV
     * @return
     */
    Integer getUv();
}
