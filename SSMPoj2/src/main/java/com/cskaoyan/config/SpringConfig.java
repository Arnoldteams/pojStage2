package com.cskaoyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration // 声明配置类
@Component("com.cskaoyan")
@ComponentScan(value = "com.cskaoyan",
        //excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class))
        //excludeFilters = @ComponentScan.Filter(Controller.class)) //type属性默认值、value=可以省略
        // // mvc配置类也在扫描包范围，排除
        excludeFilters = @ComponentScan.Filter({Controller.class, EnableWebMvc.class}))
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {
    @Bean  /*DataSource*/
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8";
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean  /*SqlSessionFactoryBean → 注册组件 sqlSessionFactory*/
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean /*MapperScannerConfigurer*/
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        // sqlSessionFactory组件id
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // mapper接口的包目录
        configurer.setBasePackage("com.cskaoyan.mapper");
        return configurer;
    }

    @Bean /*注册事务管理器*/
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
