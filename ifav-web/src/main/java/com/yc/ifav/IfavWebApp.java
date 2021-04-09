package com.yc.ifav;



import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 这样feign可以扫描这个路径下的接口@Feignclient的接口，并生成动态代理对象
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients(basePackages = "com.yc.ifav.zuul")
// 这样feign可以扫描这个路径下的接口@Feignclient的接口，并生成动态代理对象
@EnableCircuitBreaker   //启用断路器
@EnableSwagger2/*开启swagger2*/
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
public class IfavWebApp {

    public static void main(String[] args) {
        SpringApplication.run(IfavWebApp.class, args);
    }


}
