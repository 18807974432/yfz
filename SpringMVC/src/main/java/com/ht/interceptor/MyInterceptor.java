package com.ht.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  拦截器
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器（如我们上一章的 Controller 实现）；
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("--->>进入控制器前时间："+System.currentTimeMillis());
        return true;

    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），
     * 此时我们可以通过 modelAndView（模型 和视图对象）对模型数据进行处理或对视图进行处理，
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("----1--"+modelAndView.getModelMap().get("msg"));
        System.out.println("---2---"+modelAndView.getModel().get("msg"));
        System.out.println("---2---"+httpServletRequest.getAttribute("msg"));
    }

    /**
     * 整个请求处理完毕回调方法，
     * 即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间 并输出消耗时间，还可以进行一些资源清理，
     * 类似于 try-catch-finally 中的 finally，但仅调用处理器执行链中 preHandle 返回 true 的拦截器的 afterCompletion。
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("--->>渲染结束时间："+System.currentTimeMillis());
        System.out.println("-->>"+o.toString());
    }
}
