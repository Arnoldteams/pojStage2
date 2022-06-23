package com.cskaoyan.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月23日 21:38:39
 * @version:
 * @Description:
 */
public class loginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object name = session.getAttribute("name");

        if (name == null){
            response.getWriter().println("未登录！");
            return false;
        }

        return true;
    }
}
