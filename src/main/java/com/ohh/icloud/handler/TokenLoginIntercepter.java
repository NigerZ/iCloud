package com.ohh.icloud.handler;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import com.ohh.icloud.component.ThreadLocalComponent;
import com.ohh.icloud.exception.LoginFailedException;
import com.ohh.icloud.utils.JwtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenLoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前的请求路径
        String requestURI = request.getRequestURI();
        if (requestURI.contains("userLogin") || requestURI.contains("sendCode")) return true;
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) throw new LoginFailedException(ApiResultCodeEnum.LOGIN_AUTH.getCode(), ApiResultCodeEnum.LOGIN_AUTH.getMessage());
        Long userId = JwtUtil.getUserId(token);
        String username = JwtUtil.getUsername(token);
        ThreadLocalComponent.setUserIdAndUsername(userId, username);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalComponent.removeAll();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
