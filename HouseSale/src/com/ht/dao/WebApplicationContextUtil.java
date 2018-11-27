package com.ht.dao;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebApplicationContextUtil {
	public static Object createService(HttpServletRequest request,String beanid){
		WebApplicationContext wac = null;
		if (wac == null)
			wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		return wac.getBean(beanid);
	}
	//读取web.xml文件中的配置参数contextConfigLocation
	//web方式运行才能使用
	public static Object createService(String beanid){
		WebApplicationContext wac = null;
		if (wac == null)
			wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getRequest().getSession().getServletContext());
		return wac.getBean(beanid);
	}
	//应用程序运行要调用该函数

	public static Object createAppService(String beanid){
		ApplicationContext ctx=null;
		if(ctx==null)
			ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		return ctx.getBean(beanid);
	}

    //应用程序运行要调用该函数
    public static Object getBean(String beanid){
        ApplicationContext ctx=null;
        if(ctx==null)
            ctx = new ClassPathXmlApplicationContext("/myBean.xml");
        return ctx.getBean(beanid);
    }
}
