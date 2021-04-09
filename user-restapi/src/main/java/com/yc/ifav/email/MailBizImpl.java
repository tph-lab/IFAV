package com.yc.ifav.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class MailBizImpl implements IMailBiz{



    //从yml文件获取值
    @Value("${mail.fromMail.addr}")
    private String from;

    /*Spring提供了非常好用的JavaMailSender接口实现邮件发送。
	在Spring Boot中也提供了相应的自动化配置。

     * 在引入了spring-boot-starter-mail依赖之后，
     * 会根据配置文件中的内容去创建JavaMailSender实例，
     * 因此我们可以直接在需要使
     * 用的地方直接@Autowired来引入 JavaMailSender 邮件发送对象。
     */
    @Autowired
    private JavaMailSender mailSender;

    //发送邮件
    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message=new SimpleMailMessage();
        System.out.println("aaaaaaaaaa");
        //设置发送者
        message.setFrom(from);
        //设置接收者
        message.setTo(to);
        //设置主题
        message.setSubject(subject);
        //设置内容
        message.setText(content);
        //发送邮件
        mailSender.send(message);

    }


    //随机生成四个数的验证码
    @Override
    public String getCheckCode() {
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            // java Random.nextInt()方法 lic int nextInt(int n) 该方法的作用是生成一个随机的int值,该值介于[0,n)的区间,
            // 也就是0到n之间的随机int值,包含0而不包
            int code01 = random.nextInt(10);
            Integer code02=new Integer(code01);//生成Integer类
            String strcode=code02.toString();
            result += strcode;
        }
        return result;
    }




}
