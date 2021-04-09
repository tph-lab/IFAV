package com.yc.ifav.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.ifav.zuul.RecommandClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecommandRestService {

//    @Autowired
//    public RecommandClient userClient;
//
//    @HystrixCommand(fallbackMethod = "loginFallback")
//    public String login(String uName,String uPwd, String uEmail) {
//
//        return userClient.login(uName, uPwd, uEmail);
//    }
//
//    private String loginFallback(String uName,String uPwd, String uEmail) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "登录服务异常");
//        return new Gson().toJson(map);
//    }
//
//    @HystrixCommand(fallbackMethod = "registerFallback")
//    public String register(String uName, String uPwd, String vkey) {
//        return userClient.register(uName, uPwd, vkey);
//    }
//
//    private String registerFallback(String uName, String uPwd, String vkey) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "注册服务异常");
//        return new Gson().toJson(map);
//    }
//
////    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/user-proxy/user/vcode",
////            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
////            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    void sendVcode(@RequestParam("uEmail") String uEmail) ;
////
////    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/user-proxy/user/uploadPic",
////            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
////            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
////    public void uploadPic(@RequestParam("multipartFile") MultipartFile multipartFile, @RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) ;
//
//
//
//
//
//
//
//
//    @HystrixCommand(fallbackMethod = "uploadPicFallback")
//    public String uploadPic( MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("UserRestServicezzzzzzzzzzzzzzzzz     "+multipartFile);
//        userClient.uploadPic(multipartFile, request, response);
//        return "uploadok";
//    }
//
//
//
//
//
//
//
//    private String uploadPicFallback(MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) {
//
//        System.out.println("yyyyyyyyyyyyyyyyy:       "+multipartFile);
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "头像修改服务异常");
//        return new Gson().toJson(map);
//    }
//
//    @HystrixCommand(fallbackMethod = "sendVcodeFallback")
//    public String sendVcode(String uEmail) {
//        userClient.sendVcode(uEmail);
//        return "vcodeok";
//    }
//
//    private String sendVcodeFallback(String uEmail) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "发送验证码服务异常");
//        return new Gson().toJson(map);
//    }
}
