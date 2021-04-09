package com.yc.ifav.controller;


import com.yc.ifav.entity.MyFavs;
import com.yc.ifav.future.MyFavsFuture;
import com.yc.ifav.zuul.MyFavClient;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;
@Api(value = "iFav接口", tags = "我的收藏接口")
@RestController
@RequestMapping("/myfav")
public class MyFavController {

    @Resource
    private MyFavsFuture myFavsFuture;


    /**
     * 查询某用户的所有收藏
     * @RequestParam muid
     * @return
     */

    @ApiOperation(value="查询某用户的所有收藏",  httpMethod = "POST",  produces = "application/json")//必须要
    @Async
    @RequestMapping(method = RequestMethod.POST, value = "/findById")
    public CompletableFuture<String> findAll(@RequestParam("muid") String muid, HttpServletRequest request){
        if(muid!=null&&!muid.trim().equals("")){

            request.getSession().setAttribute("userid",muid);
            System.out.println("sessionId1:"+request.getSession().getId());
        }else{
            System.out.println("sessionId2:"+request.getSession().getId());
            muid= (String) request.getSession().getAttribute("userid");
        }
        int uid=Integer.parseInt(muid);
        return myFavsFuture.findAll(uid);
    }


    /**
     * 添加收藏
     * @RequestParam muid
     * @RequestParam mfname
     * @RequestParam mfurl
     * @RequestParam mfdesc
     * @return
     */
    @ApiOperation(value="添加收藏",  httpMethod = "POST",  produces = "application/json")//必须要
    @Async
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public CompletableFuture<String> create(@RequestParam("muid") int muid,@RequestParam("mfname") String mfname,@RequestParam("mfurl") String mfurl,@RequestParam("mfdesc") String mfdesc){
        MyFavs fav=new MyFavs(muid,mfname,mfurl,mfdesc);
        CompletableFuture<String> str=myFavsFuture.create(fav);
        return str;

    }


    /**
     * 删除收藏
     * @RequestParam mid
     * @return
     */
    @ApiOperation(value="删除收藏",  httpMethod = "DELETE",  produces = "application/json")//必须要
    @Async
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public CompletableFuture<String> delete(@RequestParam("mid") Integer mid){

        return myFavsFuture.delete(mid);

    }


}