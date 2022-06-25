package com.cskaoyan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
