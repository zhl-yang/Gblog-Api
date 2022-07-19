package com.zhloong.gblog;


import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.service.IOperationLogService;
import com.zhloong.gblog.rsp.SiteRsp;
import com.zhloong.gblog.rsp.SocialRsp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * <p>
 *     网站信息表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping()
public class SiteController {

    @Resource
    private IOperationLogService operationLogService;

    @RequestMapping("/site")
    @PassToken
    public Result<SiteRsp> site() {
        SiteRsp siteRsp = new SiteRsp();
        siteRsp.setUv(this.operationLogService.getUv());
        return Result.ok(siteRsp);
    }

    @RequestMapping("/social")
    @PassToken
    public Result<SocialRsp> social() {
        return Result.ok(new ArrayList<SocialRsp>());
    }

}
