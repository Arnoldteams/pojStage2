package com.cskaoyan.configuration;

// import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author stone
 * @date 2022/01/06 16:19
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * @author: 相当疲惫的 Sssd
     * @description: 注意一定要写，否则上传文件的大小会有限制
     */
    /*@Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }*/

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        HibernateValidator hibernateValidator = new HibernateValidator();
//        localValidatorFactoryBean.unwrap(HibernateValidator.class);
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        return localValidatorFactoryBean;
    }
}
