package com.ht.controller;

import com.ht.service.UploadService;
import com.ht.vo.UploadVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "upload")
public class UploadController {
    @Resource
    UploadService uploadService;

    @RequestMapping(value = "uploadSingle",method = RequestMethod.GET)
    public String toUpload(){
        return "upload";
    }
    /**
     * 文件上传控制器
     * @param file
     * @param request
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/uploadSingle",method=RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, ModelMap modelMap)throws  Exception{
        // 把文件保存到upload目录下
        // 保存文件，这个文件名有的时候文件名可能会重复，你保存多了会把原来的图片给覆盖掉，这就不太合适了。
        // 所以为每个文件生成一个新的文件名
        UploadVo upload=new UploadVo();
        //UUID是jdk里面专门用来生成唯一字符串的类
        String picName= UUID.randomUUID().toString();
        //截取文件全名（XXX.jpg）
        String oriName=file.getOriginalFilename();
        upload.setSrcFileName(oriName);
        System.out.println("----上传文件名--》"+oriName);
        //获取文件拓展名（.jpg）
        String extName = oriName.substring(oriName.lastIndexOf("."));//去掉.前面的字符

        String path=request.getSession().getServletContext().getRealPath("\\");//获取项目路径
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String sdate=sdf.format(new Date());
        String dirName="upload"+sdate;
        System.out.println("dirName="+path+"/"+dirName);

        //上传后的文件名称及路径
        //创建文件夹
        File dirFile = new File(path+"/"+dirName);
        if(!dirFile.exists()){
            dirFile.mkdir();
            System.out.println("ok............");
        }
        //新文件名
        String newFileName = picName + extName;
        //新文件名称
        upload.setDescFileName(newFileName);
        File targetFile = new File(path+"/"+dirName , newFileName);
        // 保存文件
        file.transferTo(targetFile);
        /* 把文件名保存到数据库 */
        uploadService.add(upload);

        //查询所有的图片数据
        List<UploadVo> uploadList = uploadService.list();
        modelMap.addAttribute("uploadList", uploadList);

        return "uploadList";
    }
    @RequestMapping("list")
    public String list(ModelMap map){
        //查询所有的图片数据
        List<UploadVo> uploadList = uploadService.list();
        map.addAttribute("uploadList", uploadList);
        return "uploadList";
    }
}
