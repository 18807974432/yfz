package com.ht.service;

import com.ht.mapper.UploadDAO;
import com.ht.vo.UploadVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//加入服务注解，表示这个类是一个服务层，操作数据库的类
@Service
public class UploadService {
   //注入MyBatis的数据库操作类
    @Resource
    UploadDAO uploadDAO;

    //保存
    @Transactional
    public void add(UploadVo uploadVo){
        uploadDAO.add(uploadVo);
    }

    @Transactional
    public List<UploadVo> list(){
        List<UploadVo> uploadList=uploadDAO.listAllUpload();
        return uploadList;
    }
}
