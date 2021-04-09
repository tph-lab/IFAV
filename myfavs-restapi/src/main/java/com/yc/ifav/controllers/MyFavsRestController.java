package com.yc.ifav.controllers;

import com.google.gson.Gson;
import com.yc.ifav.entity.MyFavs;
import com.yc.ifav.entity.ResponseResult;
import com.yc.ifav.service.MyFavsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
@RestController
@RequestMapping("/myfavs")
//@ResponseResult
public class MyFavsRestController {
    @Autowired
    private MyFavsService favService;

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public CompletableFuture<String> findAll(@RequestParam("muid") int muid) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                // map.put("data","HelloWorld");
                map.put("data",favService.list(muid));
                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CompletableFuture<String> add(@RequestBody MyFavs fav) {
        return CompletableFuture.supplyAsync(() -> {
            Map<String, Object> map = new HashMap<>();
            //int mid, int muid, String mdate, String mfname, String mfurl, String mfdesc
            String data="添加成功！";
            int result=favService.add(fav);
            if(result==0){
                data="添加失败!!";
            }
            map.put("code", 1);
            // map.put("data","HelloWorld");
            map.put("data",data);
            return new Gson().toJson(map);

        });
    }
    //
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@RequestParam("mid")int mid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int result=favService.delete(mid);
                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                // map.put("data","HelloWorld");
                map.put("data",result);
                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}
