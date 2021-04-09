package com.yc.ifav.zuul;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name="BASE-IFAV-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)
public interface WetherClient {


    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/weather-proxy/weather/getWeather",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String get(@RequestParam("province") String province,@RequestParam("city") String city) ;


}

