package com.yc.ifav.controller;

import com.yc.ifav.future.UserFuture;
import feign.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;
@Api(value = "iFav接口", tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserFuture userFuture;


    @ApiOperation(value="上传头像",  httpMethod = "POST",  produces = "application/json")//必须要
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public CompletableFuture<String> uploadPic(@RequestPart(value="multipartFile",required=false) MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response) {
        int uid= Integer.parseInt(request.getSession().getAttribute("userid").toString()) ;
        CompletableFuture<String> res=userFuture.uploadPic(multipartFile,uid,request,response);
        return res;
    }




    @ApiOperation(value="查询用户信息",  httpMethod = "POST",  produces = "application/json")//必须要
    @PostMapping("selectById")
    public CompletableFuture<String> selectById(@RequestParam("uid")int uid){
        return userFuture.selectById(uid);
    }












    @ApiOperation(value="用户登录",  httpMethod = "POST",  produces = "application/json")//必须要
    @Async
    @PostMapping("/login")
    public CompletableFuture<String> login(@RequestParam("uName")String uName, @RequestParam("uPwd")String uPwd, @RequestParam("uEmail") String uEmail ) {
        CompletableFuture<String> str=userFuture.login(uName,uPwd,uEmail);

        return str;

    }

    @ApiOperation(value="邮箱验证",  httpMethod = "GET",  produces = "application/json")//必须要
    @RequestMapping("/vcode")
    public void sendVcode(@RequestParam("uEmail") String uEmail) {
        userFuture.sendVcode(uEmail);
    }



    @ApiOperation(value="用户注册",  httpMethod = "POST",  produces = "application/json")//必须要
    @PostMapping("/register")
    public CompletableFuture<String> register(@RequestParam("uName")String uName,@RequestParam("uPwd") String uPwd, @RequestParam("vkey") String vkey){
            return userFuture.register(uName, uPwd, vkey);
    }

}
