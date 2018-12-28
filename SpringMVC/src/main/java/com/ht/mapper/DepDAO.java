package com.ht.mapper;

import com.ht.vo.DepVo;

import java.util.List;

public interface DepDAO {
    //查询所有部门信息
    List<DepVo> findAllDep();
    //查询部门及员工信息
    DepVo findDepAndUsers(int depId);
    void add(DepVo dep);
    //修改员工的性别和工作岗位
    void update(DepVo dep);
    //删除员工资料
    void deleteById(int id);

    //带一个参数的查询条件
    DepVo findById(int id);
}
