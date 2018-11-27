package com.ht.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ht.dao.IBaseDAO;
import com.ht.dao.PageObject;
import com.ht.dao.WebApplicationContextUtil;
import com.ht.vo.HourseInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rainbow on 2018/10/31.
 */
public class houseInfoAction extends ActionSupport {
    private IBaseDAO base= (IBaseDAO) WebApplicationContextUtil.createService("dao");
    private PageObject pager=new PageObject();
    private HourseInfo house;

    public PageObject getPager() {
        return pager;
    }

    public void setPager(PageObject pager) {
        this.pager = pager;
    }

    public HourseInfo getHouse() {
        return house;
    }

    public void setHouse(HourseInfo house) {
        this.house = house;
    }

    public List<HourseInfo> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HourseInfo> houseList) {
        this.houseList = houseList;
    }

    private List<HourseInfo> houseList;
    HttpServletResponse response= ServletActionContext.getResponse();
    public  String json() throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        DetachedCriteria dc1=DetachedCriteria.forClass(HourseInfo.class);
        dc1.add(Restrictions.eq("custid",house.getCustid()));
        houseList= base.findByDetach(dc1);
        for(int i=0;i<houseList.size();i++){
            Date date1=houseList.get(i).getSaleTime();
            if(date1!=null&&!date1.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.format(date1);
                houseList.get(i).setSaleTime(date1);
                System.out.println(houseList.get(i).getSaleTime());

            }
        }
        int count = houseList.size();
        JSONObject jsonObject=new JSONObject();

        jsonObject.put("total",count);
        jsonObject.put("rows",houseList);


        /*System.out.println();*/
        JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        out.print(jsonObject.toJSONString(houseList,SerializerFeature.WriteDateUseDateFormat));
        out.flush();
        out.close();
        return null;
    }

}
