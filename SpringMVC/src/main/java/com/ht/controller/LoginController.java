package com.ht.controller;


import com.ht.service.DepService;
import com.ht.service.UsersService;
import com.ht.vo.UsersVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller

@RequestMapping(value = "/user/")
public class LoginController {
    @Resource
    UsersService usersService;
    @Resource
    DepService depService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map){
        Cookie cookies[]=request.getCookies();
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("userName")){
                    map.addAttribute("userName", URLDecoder.decode(cookies[i].getValue()));
                }
                if(cookies[i].getName().equals("password")){
                    map.addAttribute("password",URLDecoder.decode(cookies[i].getValue()));
                }
            }
        }
        return "login";
    }

    public String login(UsersVo users){
        if(usersService.login(users)){
            return "mainFrame";
        }else {
            return "login";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response){
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String rememberPwd=request.getParameter("rememberPwd");
        System.out.println("userName"+userName+"password"+password+"rememberPwd"+rememberPwd);
        UsersVo usersVo=new UsersVo();
        usersVo.setUserName(userName);
        usersVo.setPassword(password);
        if(usersService.login(usersVo)){
            if(rememberPwd!=null){
                Cookie ckUserName=new Cookie("userName", URLEncoder.encode(userName));
                ckUserName.setMaxAge(60*60*24);
                ckUserName.setPath(request.getContextPath());
                Cookie ckPassword=new Cookie("password",URLEncoder.encode(password));
                ckPassword.setMaxAge(60*60*24);
                ckUserName.setPath(request.getContextPath());
                response.addCookie(ckUserName);
                response.addCookie(ckPassword);
            }else {
                Cookie cookies[]=request.getCookies();
                if(cookies!=null){
                    for(int i=0;i<cookies.length;i++){
                        if(cookies[i].getName().equals("userName")){
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookies[i]);
                        }
                        if(cookies[i].getName().equals("password")){
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookies[i]);
                        }
                    }
                }
            }
            return "mainFrame";
        }else {
            //定义登录失败的request提示变量
            request.setAttribute("msg","用户名或密码错误，登陆失败");
            return "login";
        }
    }

    //进入主界面的top页面
    @RequestMapping(value="/top")
    public String top(){

        return "top";
    }
    //	进入主界面的menu页面
    @RequestMapping(value="/menu")
    public String menu(){

        return "menu";
    }

    @RequestMapping(value="/exit")
    public String exit(HttpServletRequest request,HttpServletResponse response){
        request.getSession().invalidate();
        return "login";
    }
}