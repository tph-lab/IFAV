package com.yc.ifav.zuul;


import com.yc.ifav.entity.Myrecom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name="BASE-IFAV-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)
public interface RecommandClient {
    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myrecom-proxy/myrecom/findAllTag",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll();

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myrecom-proxy/myrecom/findById",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("g_tid") int g_tid);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myrecom-proxy/myrecom/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String add(Myrecom myrecom);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myrecom-proxy/myrecom/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAllG();
}