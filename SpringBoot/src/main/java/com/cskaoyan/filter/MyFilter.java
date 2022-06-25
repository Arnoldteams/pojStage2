package com.cskaoyan.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月24日 21:51:20
 * @version:
 * @Description:
 */
public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {




        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
