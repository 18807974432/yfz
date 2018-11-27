package com.ht.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

/**
 * Created by rainbow on 2018/11/8.
 */
public class test {
    public static void main(String[] args) {
        Date date=new Date();
        JSONObject object=new JSONObject();
        JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        System.out.println(object.toJSONString(date));
    }
}
