package com.yc.ifav.client;

import com.yc.ifav.config.FeignClientConfig;
import com.yc.ifav.domain.TagDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name = "BASE-IFAV-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface TagClient {
    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/tag-proxy/tag/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll();

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/tag-proxy/tag/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody TagDomain tagDomain);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/tag-proxy/tag/update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody TagDomain tagDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/yc-api/tag-proxy/tag/{id}")
    String delete(@RequestParam("id") Integer id);
}
