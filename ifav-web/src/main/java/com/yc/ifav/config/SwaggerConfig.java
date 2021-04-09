package com.yc.ifav.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //Controller所在包(必须新建包)
                .apis(RequestHandlerSelectors.basePackage("com.yc.ifav.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //接口文档的名字
                .title("iFav api")
                //接口文档的描述
                .description("iFav api接口文档")
                //服务条款网址
                .termsOfServiceUrl("http://localhost/")
                //接口文档的版本
                .version("1.0.0")
                // 接口文档维护联系信息
                .contact(new Contact("AKTeam", "", "3208027182@qq.com"))
                .build();
    }
}
