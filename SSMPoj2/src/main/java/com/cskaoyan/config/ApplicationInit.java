package com.cskaoyan.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class ApplicationInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 加载Spring的配置文件application.xml → 配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    // 加载SpringMVC的配置文件application-mvc.xml → 配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    // DispatcherServlet的映射范围
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}