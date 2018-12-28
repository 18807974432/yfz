package com.ht.mapper;

import com.ht.vo.SysAccessVo;

import java.util.List;

public interface SysAccessDAO {
    //查询系统中所有的权限
    List<SysAccessVo> listAll();
}
