package com.ht.dao;

import com.ht.vo.Send;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by rainbow on 2018/10/17.
 */

public class Mail {
    /*public static void main(String[] args) throws MessagingException {
        Mail m=new Mail();
        try {
            m.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public  String qq;
    public String head;
    public  String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public  void send() throws MessagingException {


     MimeMessage message;

  //   Security.addProvider(new Provider());
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
  //QQ邮箱服务器
    String smtpHost="smtp.qq.com";
    //邮箱用户名，即QQ账号（自定义）
    final String username = "2011219479@qq.com";
  //邮箱授权码（自定义）
    final String password = "fjixeajhsjjgjacb";
  //要发送到的邮箱（自定义）
    String to = qq;
    //自己的邮箱（自定义）
    String from = "2011219479@qq.com";
    Transport transport;


    Properties props = new Properties();
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.setProperty("mail.smtp.auth", "true");
    props.put("mail.smtp.host",smtpHost);
    props.put("mail.smtp.username", username);
    props.put("mail.smtp.password", password);
    Session session = Session.getDefaultInstance(props,new Authenticator() {
        //身份认证
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    InternetAddress[] addresses = {new InternetAddress(to)};
    message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO,addresses);
    message.setSubject(head);//发送标题（自定义）
    message.setSentDate(new Date());
    message.setText(text);//发送内容（自定义）
    transport = session.getTransport("smtp");
    transport.connect(smtpHost, username, password);
    transport.send(message);
    System.out.println("email has been sent");
}

}




