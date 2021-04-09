package com.yc.ifav.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.ifav.zuul.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserRestService {

    @Autowired
    public  UserClient userClient;

    @HystrixCommand(fallbackMethod = "loginFallback")
    public String login(String uName,String uPwd, String uEmail) {

        return userClient.login(uName, uPwd, uEmail);
    }

    private String loginFallback(String uName,String uPwd, String uEmail) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "登录服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "registerFallback")
    public String register(String uName, String uPwd, String vkey) {
        return userClient.register(uName, uPwd, vkey);
    }

    private String registerFallback(String uName, String uPwd, String vkey) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "注册服务异常");
        return new Gson().toJson(map);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/user-proxy/user/vcode",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    void sendVcode(@RequestParam("uEmail") String uEmail) ;
//
//    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/user-proxy/user/uploadPic",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public void uploadPic(@RequestParam("multipartFile") MultipartFile multipartFile, @RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) ;








    @HystrixCommand(fallbackMethod = "uploadPicFallback")
    public String uploadPic( MultipartFile multipartFile, int uid, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("UserRestServicezzzzzzzzzzzzzzzzz     "+multipartFile);
        return userClient.uploadPic(multipartFile,uid, request, response);
    }







    private String uploadPicFallback(MultipartFile multipartFile,int uid, HttpServletRequest request, HttpServletResponse response) {

        System.out.println("yyyyyyyyyyyyyyyyy:       "+multipartFile);
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "头像修改服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "sendVcodeFallback")
    public String sendVcode(String uEmail) {
        userClient.sendVcode(uEmail);
        return "vcodeok";
    }

    private String sendVcodeFallback(String uEmail) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "发送验证码服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "selectByIdFallback")
    public String selectById(int uid) {
        return userClient.selectById(uid);

    }

    private String selectByIdFallback(int uid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "根据id用户异常！！！");
        return new Gson().toJson(map);
    }
}
