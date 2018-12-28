package com.ht.mapper;

import com.ht.vo.UploadVo;

import java.util.List;

public interface UploadDAO {
    List<UploadVo> listAllUpload();
    void add(UploadVo uploadVo);
}
