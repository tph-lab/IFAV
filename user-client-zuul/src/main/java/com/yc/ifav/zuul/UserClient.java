package com.yc.ifav.zuul;


import com.yc.ifav.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name="BASE-IFAV-ZUUL-GATEWAY",
        configuration = {MultipartSupportConfig.class,FeignClientConfig.class}
)
public interface UserClient {

//    //访问的路径value要修改成zuul指定的服务路由路径
//    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/piclib-proxy/piclib/{id}")
//    String findById(@RequestParam("id") Integer id);

//    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myfavs-proxy/myfavs/findById",
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    String findAll(@RequestParam("muid") int muid);


    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/reg-proxy/user/login",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String login(@RequestParam("uName") String uName,@RequestParam("uPwd") String uPwd, @RequestParam("uEmail") String uEmail ) ;

    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/reg-proxy/user/vcode",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void sendVcode(@RequestParam("uEmail") String uEmail) ;

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/reg-proxy/user/uploadPic",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadPic(@RequestPart("multipartFile") MultipartFile multipartFile,@RequestParam("uid") int uid, @RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response) ;

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/reg-proxy/user/register",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping("/user/register")
    String  register(@RequestParam("uName")String uName,@RequestParam("uPwd") String uPwd, @RequestParam("vkey") String vkey);

    @RequestMapping(method = RequestMethod.POST,value = "/yc-api/reg-proxy/user/selectById")
    String selectById(@RequestParam("uid") int uid );
}