package com.yc.ifav.controllers;

import com.google.gson.Gson;
import com.yc.ifav.domain.TagDomain;
import com.yc.ifav.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tag")
public class TagRestController {
    @Autowired
    private TagService tagService;




    //查询所有标签//ifav_findAllTag
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
               // map.put("data","HelloWorld");
                map.put("obj",tagService.list());
                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CompletableFuture<String> add(@Valid TagDomain tagDomain) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int result=tagService.add(tagDomain);
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

    @RequestMapping(value = "/delete/{tid}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@RequestParam("tid")int tid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int result=tagService.delete(tid);
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CompletableFuture<String> update(@RequestParam("tname") String tname,@RequestParam("tid") int tid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                int result=tagService.update(tname,tid);
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
