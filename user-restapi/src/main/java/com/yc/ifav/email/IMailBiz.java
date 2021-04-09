package com.yc.ifav.email;

public interface IMailBiz {

    //发送邮件
    public void sendMail(String to,String subject,String content);

    //获取验证码
    public String getCheckCode();

}
