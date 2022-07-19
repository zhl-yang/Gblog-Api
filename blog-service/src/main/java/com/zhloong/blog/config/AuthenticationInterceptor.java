package com.zhloong.blog.config;

import cn.hutool.json.JSONUtil;
import com.zhloong.blog.common.component.Result;
import com.zhloong.blog.common.interfaces.PassToken;
import com.zhloong.blog.common.util.IpAndAddressUtil;
import com.zhloong.blog.common.util.IpUtils;
import com.zhloong.blog.common.util.RedissonUtil;
import com.zhloong.blog.entity.OperationLogEntity;
import com.zhloong.blog.service.IOperationLogService;
import com.zhloong.blog.shiro.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author zhloong
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private RedissonUtil redissonUtil;

    @Resource
    private IOperationLogService operationLogService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {

        // 限制爬虫
        checkIpTimes(httpServletRequest);

        // 新增访问日志
        this.setOperationLog(httpServletRequest);

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("auth-token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 执行认证
        if (token == null) {
            token = httpServletRequest.getHeader("accessToken");
        }
        if (token == null) {
            return returnJson(httpServletResponse, 401, "请登录后再操作");
        }
        // 获取 token 中的 user id
        String userStr;
        try {
            userStr = redissonUtil.get(token);
            if (StringUtils.isBlank(userStr)) {
                return returnJson(httpServletResponse, 401, "登录已过期，请重新登录");
            }
        } catch (Exception e) {
            return returnJson(httpServletResponse, 401, "登录已过期，请重新登录");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        AppUser.remove();
    }

    private Boolean returnJson(HttpServletResponse response, Integer status, String msg) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            response.getWriter().println(JSONUtil.parse(Result.error(status, msg)));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error("拦截器输出流异常:{}", e);
        } finally {
            return false;
        }
    }

    /**
     * 限制爬虫调用
     *
     * @param httpServletRequest
     */
    private void checkIpTimes(HttpServletRequest httpServletRequest) {
        String ipAddress = IpUtils.getIpAdder(httpServletRequest);
        if (StringUtils.isBlank(ipAddress)) {
            return;
        }
        ipAddress = ipAddress.replace(":", "-");
        int time = 0;
        int maxTime = 30;
        String s = this.redissonUtil.get("request_" + ipAddress);
        if (StringUtils.isNotBlank(s)) {
            time = Integer.parseInt(s);
            if (time > maxTime) {
                throw new RuntimeException("该IP操作次数超过限制,请稍后再试");
            }
        }
        this.redissonUtil.set("request_" + ipAddress, (time + 1) + "", 1, TimeUnit.SECONDS);
    }

    /**
     * 新增操作日志
     *
     * @param httpServletRequest
     * @return
     */
    private OperationLogEntity setOperationLog(HttpServletRequest httpServletRequest) {
        OperationLogEntity operationLogEntity = new OperationLogEntity();
        operationLogEntity.setIp(IpAndAddressUtil.getIp(httpServletRequest));
        operationLogEntity.setBrowserName(IpAndAddressUtil.getBrowserName(httpServletRequest));
        operationLogEntity.setBrowserVersion(IpAndAddressUtil.getBrowserVersion(httpServletRequest));
        operationLogEntity.setBrowserSystem(IpAndAddressUtil.getOsName(httpServletRequest));
        operationLogEntity.setUrl(httpServletRequest.getRequestURI());
        operationLogEntity.setParam(JSONUtil.toJsonStr(httpServletRequest.getParameterMap()));
        operationLogService.save(operationLogEntity);
        return operationLogEntity;
    }
}
