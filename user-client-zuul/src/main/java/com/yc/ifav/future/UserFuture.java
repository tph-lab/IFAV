package com.yc.ifav.future;


import com.yc.ifav.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@Component
public class UserFuture {

    @Autowired(required = false)
    private UserRestService userRestService;


    @Async
    public CompletableFuture<String> login(String uName,String uPwd, String uEmail) {
        return CompletableFuture.supplyAsync(() -> {

           return userRestService.login(uName, uPwd, uEmail);
        });
    }
    @Async
    public CompletableFuture<String> sendVcode(String uEmail) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return userRestService.sendVcode(uEmail);
        });
    }
    @Async
    public CompletableFuture<String> uploadPic(MultipartFile multipartFile,int uid, HttpServletRequest request, HttpServletResponse response) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return userRestService.uploadPic(multipartFile,uid, request, response);
        });
    }


    @Async
    public CompletableFuture<String> register(String uName, String uPwd, String vkey) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return userRestService.register(uName, uPwd, vkey);
        });
    }

    @Async
    public CompletableFuture<String> selectById(int uid) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return userRestService.selectById(uid);
        });
    }
}
