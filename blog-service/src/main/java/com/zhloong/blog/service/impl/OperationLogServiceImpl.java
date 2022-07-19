package com.zhloong.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.OperationLogDao;
import com.zhloong.blog.entity.OperationLogEntity;
import com.zhloong.blog.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author <a href="https://www.fengwenyi.com?code">Erwin Feng</a>
 * @since 2022-04-04
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLogEntity> implements IOperationLogService {

    @Override
    public Integer getUv() {
        return baseMapper.getUv();
    }
}
