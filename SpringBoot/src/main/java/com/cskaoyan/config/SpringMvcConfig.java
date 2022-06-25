package com.cskaoyan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月24日 21:50:12
 * @version:
 * @Description:
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    // @Bean // 限定filter作用范围
    // public FilterRegistrationBean filterRegistrationBean() {
    //     FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    //     filterRegistrationBean.setFilter(new MyFilter());
    //     filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
    //     return filterRegistrationBean;
    // }
}
