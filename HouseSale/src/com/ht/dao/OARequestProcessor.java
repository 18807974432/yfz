package com.ht.dao;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ht.vo.AccModule;
import com.ht.vo.Module;
import com.ht.vo.Users;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

public class OARequestProcessor extends AbstractInterceptor
{
    List<Module> quanxian;

    public String intercept(ActionInvocation i)
            throws Exception
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        ServletContext application = session.getServletContext();

        String className = i.getAction().getClass().getName();

        String method = i.getProxy().getMethod();
        DetachedCriteria dc;
        if (application.getAttribute("quanxian") == null)
        {
            IBaseDAO base = (IBaseDAO)WebApplicationContextUtil.createService("dao");
            dc = DetachedCriteria.forClass(Module.class);
            this.quanxian = base.findByDetach(dc);
            application.setAttribute("quanxian", this.quanxian);
        }
        this.quanxian = ((List)application.getAttribute("quanxian"));
        for (Module list : this.quanxian) {
            if ((className.equals(list.getClassName())) &&
                    (method.equals(list.getMethod())))
            {
                Users userinfo = ContextUtils.getUserInfo();
                if (userinfo != null)
                {
                    List<AccModule> userin = userinfo.getRight();
                    for (AccModule user : userin) {
                        if (list.getModuleid() == user.getModule().getModuleid())
                        {
                            i.invoke();
                            return null;
                        }
                    }
                }
                else
                {
                    response.sendRedirect("login.jsp");
                    return null;
                }
                response.sendRedirect("/mistake.jsp");
                return null;
            }
        }
        if ((method.equals("login")) || (method.equals("release")))
        {
            i.invoke();
            return null;
        }
        if (ContextUtils.getUserInfo() == null)
        {
            response.sendRedirect("login.jsp");
            return null;
        }
        i.invoke();
        return null;
    }
}
