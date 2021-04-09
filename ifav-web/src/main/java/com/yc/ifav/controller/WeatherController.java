package com.yc.ifav.controller;


import com.yc.ifav.util.RedisClient;
import com.yc.ifav.zuul.WetherClient;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
@Api(value = "iFav接口", tags = "天气接口")
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Resource
    private WetherClient weatherFegin;


    @ApiOperation(value="根据用户当前城市获取该地天气",  httpMethod = "POST",  produces = "application/json")//必须要
    @Async
    @RequestMapping(method = RequestMethod.POST, value = "/getWeather")
    public CompletableFuture<String> get(@Param("province") String province, @Param("city") String city){
        return CompletableFuture.supplyAsync(() -> {
            String data= RedisClient.getInstance().getJedis().get("weather:cityaprovince:"+city+"a"+province);
            if("".equals(data) || data==null){
                String s=weatherFegin.get(province,city);
                RedisClient.getInstance().getJedis().set("weather:cityaprovince:"+city+"a"+province,s);
                return s;
            }
            System.out.println(data+"==============================<");
                return data;

        });
    }

}
