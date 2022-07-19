package com.zhloong.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhloong.blog.dal.dao.FriendDao;
import com.zhloong.blog.service.IFriendService;
import com.zhloong.gblog.entity.FriendEntity;
import org.springframework.stereotype.Service;

/**
 * @author zhloong
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendDao, FriendEntity> implements IFriendService {
}
