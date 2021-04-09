package com.yc.ifav.controller;


import com.yc.ifav.entity.Myrecom;
import com.yc.ifav.zuul.RecommandClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/myrecom")
public class OldController {

    RecommandClient recommandClient;
    /**
     * 删除收藏
     * @RequestParam mid
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findAllTag")
    public String findAll(){
        return recommandClient.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findById")
    public String findAll(@RequestParam("g_tid") int g_tid){
        return recommandClient.findAll(g_tid);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String add(Myrecom myrecom){
        return recommandClient.add(myrecom);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/findAll")
    public String findAllG(){
        return recommandClient.findAllG();
    }



}