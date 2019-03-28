package com.task.common;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class MailUtils {

    //服务器地址
    private static String emailSMTPHost="smtp.qq.com";
    //账号
    private static String emailAccount="wb322@foxmail.com";
    //邮箱密码（授权码）
    private static String emailPassword="lisdkprbehewbgec";

    /**
     * 发送邮件
     * @param toEmailAddress
     * @param emailTitle
     * @param emailContent
     * @return
     * @throws Exception
     */
    public static void sendEmail(String toEmailAddress, String emailTitle, String emailContent)throws Exception{
        Properties props = new Properties();
 
        // 开启debug调试
        props.setProperty("mail.debug", "true");

        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");

        // 端口号
        props.put("mail.smtp.port", 465);

        // 设置邮件服务器主机名
        props.setProperty("mail.smtp.host", emailSMTPHost);

        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
 
        /**SSL认证，注意腾讯邮箱是基于SSL加密的，所以需要开启才可以使用**/
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        //设置是否使用ssl安全连接（一般都使用）
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        //创建会话
        Session session = Session.getInstance(props);

        //获取邮件对象
        //发送的消息，基于观察者模式进行设计的
        Message msg = new MimeMessage(session);

        //设置邮件标题
        msg.setSubject(emailTitle);

        //设置邮件内容
        //使用StringBuilder，因为StringBuilder加载速度会比String快，而且线程安全性也不错
        StringBuilder builder = new StringBuilder();

        //写入内容
        builder.append("\n" + emailContent);

        //写入我的官网
        builder.append("\n官网：" +"https://www.mfp.com（TEST）");

        //定义要输出日期字符串的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //在内容后加入邮件发送的时间
        builder.append("\n时间：" + sdf.format(new Date()));

        //设置显示的发件时间
        msg.setSentDate(new Date());

        //设置邮件内容
        msg.setText(builder.toString());

        //设置发件人邮箱
        // InternetAddress 的三个参数分别为: 发件人邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        msg.setFrom(new InternetAddress(emailAccount,"mfp", "UTF-8"));

        //得到邮差对象
        Transport transport = session.getTransport();

        //连接自己的邮箱账户
       //connect(host, user, password)
       transport.connect( emailSMTPHost, emailAccount, emailPassword);

       //发送邮件
       transport.sendMessage(msg, new Address[] { new InternetAddress(toEmailAddress) });

       /*//将该邮件保存到本地
       OutputStream out = new FileOutputStream("MyEmail.eml");
       msg.writeTo(out);
       out.flush();
       out.close();*/

       transport.close();

    }
    public static String sendKey(String toEmailAddress)throws Exception{
        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,8);
        sendEmail(toEmailAddress,"验证码(mfp.com)","您正在进行 修改密码 操作，官方绝不会索取此验证码，请勿告知他人。验证码："+uuid+"（有效期30分钟）");
        return uuid;
    }
}
