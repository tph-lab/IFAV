package com.yc.ifav;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WeatherRestApiApp {
    public static void main(String[] args) {
        SpringApplication.run(WeatherRestApiApp.class,args);
    }
}
