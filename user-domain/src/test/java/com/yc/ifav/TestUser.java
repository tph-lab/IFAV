package com.yc.ifav;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.yc.ifav.entity.User;
import com.yc.ifav.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})       //扫描的配置类
public class TestUser {

    private static final Logger logger = Logger.getLogger(TestUser.class.getName());

    @Autowired
    private UserService userService;

    @Test
    public void register() {
        User user=new User();
        user.setUEmail("2378868011@qq.com");
        user.setUName("tph");
        user.setUPwd("123456");
        logger.info("用户注册..........");
        userService.register(user);
        System.out.println("用户ID:"+user.getUId());
    }

    @Test
    public void login() {
        User user=new User();
      //  user.setUEmail("2378868011@qq.com");
        user.setUName("tph");
        user.setUPwd("123456");
        logger.info("用户登录..........");
        User user01=userService.login(user);
      // logger.info(user01.toString()+"1111111111111111");
        System.out.println("用户登录的ID:"+user01.getUId());
    }
}
