package com.ht.dao;

import com.ht.vo.UserLog;
import com.ht.vo.Users;
import org.apache.struts2.ServletActionContext;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Summer on 2018/10/22.
 */
public class ContextUtils {
    private static int nmonth=3;

    public static Users getUserInfo(){
        Users usersinfo=(Users) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
        return usersinfo;
    }

    public static String getFilename()
    {
        Date d = new Date();
        int year = d.getYear() + 1900;
        int month = d.getMonth() + 1;
        int day = d.getDate();
        int hour = d.getHours();
        int minute = d.getMinutes();
        int second = d.getSeconds();
        String sdate = year+"";
        if (month < 10) {
            sdate = sdate + "0" + month;
        } else {
            sdate = sdate + month;
        }
        if (day < 10) {
            sdate = sdate + "0" + day;
        } else {
            sdate = sdate + day;
        }
        if (hour < 10) {
            sdate = sdate + "0" + hour;
        } else {
            sdate = sdate + hour;
        }
        if (minute < 10) {
            sdate = sdate + "0" + minute;
        } else {
            sdate = sdate + minute;
        }
        if (second < 10) {
            sdate = sdate + "0" + second;
        } else {
            sdate = sdate + second;
        }
        return sdate;
    }

    public static UserLog saveUserLog(Object obj, int logtype) {
        UserLog ul = null;
        String opr = "";
        switch (logtype) {
            case 1:
                opr = "新增";
                break;
            case 2:
                opr = "修改";
                break;
            case 3:
                opr = "删除";
                break;
            case 4:
                opr = "登录";
                break;
            case 5:
                opr = "作废";
                break;
            case 6:
                opr = "转签约";
                break;
            case 7:
                opr = "修改密码";
        }
        String tables = obj.getClass().getName();
        int pos = tables.lastIndexOf(',');
        tables = tables.substring(pos + 11) + ":" + opr;
        Users u = new Users();
        u.setUserid(getUserInfo().getUserid());
        ul = new UserLog();
        ul.setUserid(getUserInfo().getUserid());
        ul.setUser(u);
        ul.setTables(tables);
        ul.setIpaddr(ServletActionContext.getRequest().getRemoteAddr());
        String s = obj.toString();
        if (s.length() > 1000) {
            s = s.substring(0, 1000);
        }
        if (logtype == 4) {
            ul.setMsg(opr);
        } else {
            ul.setMsg(s);
        }
        IBaseDAO base = (IBaseDAO) WebApplicationContextUtil.createService("dao");
        base.saveOrUpdate(ul);

        return ul;
    }
}
