package com.ht.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class DateUtil  implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        //实现将字符串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat dateFormat = null;
        if(!StringUtils.isEmpty(source)){
            if(source.indexOf(":") > 0){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }else{
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
        }


        try {
            return dateFormat.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果参数绑定失败返回null
        return null;
    }

}
