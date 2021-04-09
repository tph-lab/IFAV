package com.yc.ifav;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

//专用的测试配置类
@Configuration
@ComponentScan("com.yc")
@EnableTransactionManagement   //开启事务管理（里面要配置事务管理器）
public class DaoConfiguration {

    @Bean   //事件源
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://123.57.20.138:3306/db_ifav?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong\n");
        dataSource.setUsername("root");
        dataSource.setPassword("wgf123lxl");
        return dataSource;
    }


    //产生的对象名是sqlSessionFactoryBean
    @Bean   //联接工厂
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //可以加入各种拦截器
       // PageInterceptor pi = new PageInterceptor();
        //   可以加入其它的配置
        //加入分页的插件的拦截器（分页时）
       // bean.setPlugins(pi);
        return bean.getObject();
    }


    //事务管理器
    @Bean(name = "transactionManager")
    public PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    //扫描加载mapper配置
    @Configuration
    @AutoConfigureAfter(DaoConfiguration.class)  //有顺序      表示在扫描DaoConfiguration类之后扫描该类
    public static class MyBatisMapperScannerConfig {
        @Bean
        public MapperScannerConfigurer mapperScannerConfig() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            //创建mapperbean时，要注入  sqlsessionfactorybean
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
            //扫描   针对每个表的mapper位置
            mapperScannerConfigurer.setBasePackage("com.yc.ifav.dao.impl");


            //配置通用mappers
            Properties properties = new Properties();
            //   指定通用mapper的位置，一定不能在   com.yc.piclib.dao.impl
            properties.setProperty("mappers", "com.yc.ifav.dao.MisBaseMapper");
            properties.setProperty("notEmpty", "false");
            // 请注意数据库中的主键类型
            properties.setProperty("IDENTITY", "MYSQL");


            mapperScannerConfigurer.setProperties(properties);

            return mapperScannerConfigurer;
        }

    }


}