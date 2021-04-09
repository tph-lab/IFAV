package com.yc.ifav.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yc.ifav.email.MailBizImpl;
import com.yc.ifav.entity.User;
import com.yc.ifav.service.UserService;
import com.yc.ifav.services.AsyncThreadPool;
import com.yc.ifav.services.FastefsClient;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/*
与之前主要的区别在于，同步改为异步，增强了性能
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController {


    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private MailBizImpl mailBiz;

    @Autowired
    private UserService userService;

    private String ema;    //邮件

    String vcode;           //验证码
    String uAvator;         //头像图片



    @Async
    @PostMapping("/login")
    public CompletableFuture<String> login(String uName,String uPwd,String uEmail ) {
        // static CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        //   Supplier就是一个接口
        //    接口中的方法:   T get();

//        return CompletableFuture.supplyAsync(new Supplier() {
//            @Override
//            public Object get() {  //回调方法,   当请求有响应，由  jvm 调用.
//                  PicDomain pic = picService.findOne(id);
        //            Map<String, Object> map = new HashMap<>();
        //            map.put("code", 1);
        //            map.put("data", pic);
        //            return new Gson().toJson(map);
//            }
//        });
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            User user=new User();
            if(uName!=null&&uName!=""){
                user.setUName(uName);
            } if(uPwd!=null&&uPwd!=""){
                user.setUPwd(uPwd);
            } if(uEmail!=null&uEmail!=""){
                user.setUEmail(uEmail);
            }
           System.out.println(user+".......................");
            User user01 = userService.login(user);
            Map<String, Object> map = new HashMap<>();
            //协议
            if(user01!=null){
                map.put("code", 1);
                map.put("data", user01);
            }else{
                map.put("code",0);
                map.put("data","用户名或密码或邮件错误！！！！！");
            }


            System.out.println(map+",,,,,,,,,,,");
            return new Gson().toJson(map);
        });
    }



    @Async
    @PostMapping("/register")
    public CompletableFuture<String>  register(String uName,String uPwd, @RequestParam("vkey") String vkey) {

        return CompletableFuture.supplyAsync(() -> {
            User user=new User();
            user.setUName(uName);

            user.setUPwd(uPwd);
            System.out.println("用户发送过来的验证码"+vkey+"现有验证码--->"+vcode);
            Map<String, Object> map = new HashMap<>();
            if(!(vkey.equals(vcode))) {

                map.put("code",0);
                map.put("data","验证码不正确...........");
                //return mav;
            }else{
                user.setUEmail(ema);
                if(userService.emreg(user)==null){
                    userService.register(user);
                    map.put("code", 1);
                    map.put("data", "用户注册成功............");
                }else{
                    map.put("code", 0);
                    map.put("data", "邮箱已注册............");
                }

            }
            return new Gson().toJson(map);
        });
    }


    //发送验证码
    @GetMapping("vcode")
    public void SendVcode(@RequestParam("uEmail") String uEmail) {
        vcode=mailBiz.getCheckCode();
        ema=uEmail;
        System.out.println("即将发送的验证码："+vcode+"to"+uEmail+"....................");
        mailBiz.sendMail(uEmail, "Hello.....", "欢迎使用iFav，您的验证码为："+vcode);
    }




    /**
     * fastdfs部分
     */


    @Value("${file.path.head:http://120.26.177.155/}")
    private String pathHead;

    @Autowired
    private FastefsClient fastefsClient;

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPic(@RequestPart("multipartFile") MultipartFile multipartFile,@RequestParam("uid") int uid,  HttpServletRequest request, HttpServletResponse response) {
        try {
            //为空
            System.out.println(multipartFile+",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
            //上传文件,为了获取路径
            String filename = fastefsClient.uplodFile(multipartFile);
            //使用了异步线程池
            AsyncThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("文件通过nginx访问的路径:" + (pathHead + filename));
                        uAvator=pathHead+filename;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
//            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
//            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("pathInfo", pathHead + filename);
//            data.put("width", image.getWidth());
//            data.put("height", image.getHeight());
//
//            ObjectMapper mapper = new ObjectMapper();
//            String ret = mapper.writeValueAsString(data);
//
//            response.setContentType("text/html;charset=utf8");
//            response.getOutputStream().write(ret.getBytes());
//            response.flushBuffer();






            //头像修改
            User user=new User();
            user.setUId(uid);
            User user01=userService.selectById(user);

            user01.setUAvator(uAvator);
            int res= userService.updateAvator(user01);
            Map map=new HashMap();
            if(res==1){
                map.put("user",user01);
                map.put("code", 1);
                map.put("data", "头像修改成功.........");
            }else{
                map.put("code",0);
                map.put("data", "头像修改失败.........");
            }


            System.out.println(map+",,,,,,,,,,,");
            return new Gson().toJson(map);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Async
    @PostMapping("/selectById")
    public CompletableFuture<String> selectById(int uid ) {

        return CompletableFuture.supplyAsync(() -> {
            User user=new User();
            user.setUId(uid);
            User user01=userService.selectById(user);
            Map map=new HashMap();
            if(user01!=null){
                map.put("data",user01);
                map.put("msg","根据id查询成功！！！！");
            }else{
                map.put("msg","根据id查询失败！！！！");
            }

            return new Gson().toJson(map);
        });
    }


}

