package com.zhloong.gblog;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.service.IFriendService;
import com.zhloong.gblog.entity.FriendEntity;
import com.zhloong.gblog.req.FriendReq;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhloong
 */
@RestController
@RequestMapping("/friend")
@ResponseBody
@Slf4j
public class FriendController {
    @Resource
    private IFriendService friendService;

    @GetMapping
    @ApiOperation(value = "获取所有友链", httpMethod = "GET", notes = "获取所有友链")
    @PassToken
    public Result<FriendEntity> getList(){
        Wrapper<FriendEntity> wrapper = new LambdaQueryWrapper<>(new FriendEntity()).eq(FriendEntity::getType, 0);
        return Result.ok(friendService.list(wrapper));
    }

    @PutMapping
    @ApiOperation(value = "新增友链", httpMethod = "PUT", notes = "获取所有友链")
    @PassToken
    public Result<FriendEntity> add(@Validated @RequestBody FriendReq friend){
        FriendEntity friendEntity = new FriendEntity();
        BeanUtil.copyProperties(friend, friendEntity);
        return Result.ok(friendService.save(friendEntity));
    }
}
