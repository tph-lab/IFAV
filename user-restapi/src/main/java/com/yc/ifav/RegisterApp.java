package com.yc.ifav;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;


//该启动类需要扫描到domain的@Mapper
@EnableEurekaClient
@SpringBootApplication

//就可以拥有带有连接池的FastDFS Java客户端了
@Import(FdfsClientConfig.class)
public class RegisterApp {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApp.class, args);
    }
}
