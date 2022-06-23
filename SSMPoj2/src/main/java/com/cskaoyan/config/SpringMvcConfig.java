package com.cskaoyan.config;

import com.cskaoyan.filter.loginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月20日 20:40:53
 * @version:
 * @Description:
 */
@ComponentScan("com.cskaoyan.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/user/hello");
    }
}