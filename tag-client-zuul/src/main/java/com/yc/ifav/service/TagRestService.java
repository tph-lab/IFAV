package com.yc.ifav.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.ifav.client.TagClient;
import com.yc.ifav.domain.TagDomain;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层 ：调用PiclibClient,实现断路由功能
public class TagRestService {
    @Autowired
    private TagClient tagClient;


    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll() {
        return tagClient.findAll();
    }

    private String findAllFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "updateFallback")
    public String update(TagDomain tagDomain){
        return tagClient.update(tagDomain);
    }

    private String updateFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(TagDomain tagDomain) {
        return tagClient.create(tagDomain);
    }

    private String createFallback(TagDomain tagDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加"+tagDomain.getTname());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return tagClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }
}
