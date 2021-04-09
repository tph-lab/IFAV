package com.yc.ifav.controllers;


import com.google.gson.Gson;
import com.yc.ifav.domain.TagDomain;
import com.yc.ifav.entity.Myrecom;
import com.yc.ifav.service.MyrecomService;
import com.yc.ifav.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/myrecom")
public class RecomRestController {

    @Autowired
    private MyrecomService recomservice;

    @Autowired
    private TagService tagservice;


    //查询所有标签//ifav_findAllTag
    @RequestMapping(value = "/findAllTag", method = RequestMethod.POST)
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                // map.put("data","HelloWorld");
                map.put("obj",tagservice.list());
                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    //2.根据标签名查询网址(包括未分类，全部)
    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public CompletableFuture<String> findAll(@RequestParam("g_tid") int g_tid){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Map<String,Object> map = new HashMap<>();
                map.put("code",1);
                map.put("obj",recomservice.list(g_tid));
                return new Gson().toJson(map);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        });
    }

    //3.添加新网址
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public CompletableFuture<String> add(@Valid Myrecom myrecom){
        return CompletableFuture.supplyAsync(() ->{
            Map<String,Object>  map = new HashMap<>();
            String data = "添加成功";
            try{
                URL url;
                try {
                    url = new URL(myrecom.getG_furl());
                    InputStream in = url.openStream();
                }catch (Exception e3){
                    url = null;
                    map.put("code",0);
                    map.put("msg","网址输入有误或者链接不存在");

                }

                TagDomain  r2=tagservice.findByTag(myrecom.getGname());
                if(r2==null){
                    tagservice.add(new TagDomain(myrecom.getGname()));
                }
                TagDomain  r3 =tagservice.findByTag(myrecom.getGname());
                myrecom.setG_tid(r3.getTid());
                int result = recomservice.add(myrecom);
                if(result == 0){
                    data="添加失败";
                }
                map.put("code",1);
                map.put("obj",data);
                return new Gson().toJson(map);

            }catch (Exception e4){
                e4.printStackTrace();

            }
            return null;
        });

    }

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public CompletableFuture<String> findAllG(){
        return CompletableFuture.supplyAsync(() ->{
            try {
                Map<String,Object> map = new HashMap<>();
                map.put("code",1);
                map.put("obj",recomservice.findAll());
                return new Gson().toJson(map);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        });
    }






    @RequestMapping(value = "/delete/{g_id}",method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@RequestParam("g_id") int g_id){
        return CompletableFuture.supplyAsync(() ->{
           try{
               int result = recomservice.delete(g_id);
               Map<String,Object>map = new HashMap<>();
               map.put("code",1);
               map.put("data",result);
               return new Gson().toJson(map);
           }catch (Exception e2){
               e2.printStackTrace();
           }
           return null;
        });
    }


}
