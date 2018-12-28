package com.ht.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadVo implements Serializable {
    private int uploadId;
    //原文件名称
    private String srcFileName;
    //上传后的文件名称
    private String descFileName;
    //上传时间
    private Date uploadTime;
    //字符串格式
    private String sTime;

    public int getUploadId() {
        return uploadId;
    }

    public void setUploadId(int uploadId) {
        this.uploadId = uploadId;
    }

    public String getSrcFileName() {
        return srcFileName;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public String getDescFileName() {
        return descFileName;
    }

    public void setDescFileName(String descFileName) {
        this.descFileName = descFileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getsTime() {
        //把日期类型转换成指定格式的字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sTime=sdf.format(uploadTime);
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }
}
