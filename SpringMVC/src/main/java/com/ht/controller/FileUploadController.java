package com.ht.controller;

import com.ht.vo.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;

@Controller
public class FileUploadController {
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/fileUploadPage",method = RequestMethod.GET)
    public ModelAndView fileUploadPage(){
        FileModel file=new FileModel();
        ModelAndView modelAndView=new ModelAndView("upload","command",file);
        return modelAndView;
    }
    @RequestMapping(value = "/fileUploadPage",method = RequestMethod.POST)
    public String fileUpload(HttpServletRequest request) throws Exception{
        String uppath=servletContext.getRealPath("/");
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while (iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                    //获取当前上传的文件名称
                    String myFileName=file.getOriginalFilename();
                    String path=uppath+"uploadFile/"+file.getOriginalFilename();
                    File filepath = new File(path, myFileName);
                    if (!filepath.getParentFile().exists()) {
                        filepath.getParentFile().mkdirs();//如果目录不存在，创建目录
                    }
                    try {
                        //上传
                        file.transferTo(new File(path));
                    }catch(Exception e){
                        e.printStackTrace();
                        return "error";
                    }
                    return "success";
                }
            }
            return "error";
        }
        return "error";
    }
}
