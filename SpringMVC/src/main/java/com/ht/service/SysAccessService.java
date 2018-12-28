package com.ht.service;

import com.ht.mapper.SysAccessDAO;
import com.ht.vo.SysAccessVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAccessService {
    @Resource
    SysAccessDAO sysAccessDAO;
    public List<SysAccessVo> findAll(){
        return sysAccessDAO.listAll();
    }
}
