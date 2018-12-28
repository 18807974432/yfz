package com.ht.service;

import com.ht.mapper.DepDAO;
import com.ht.vo.DepVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//加入服务注解，表示这个类是一个服务层，操作数据库的类
@Service
public class DepService {
    @Resource
    DepDAO depDAO;
    //系统登录
    @Transactional
    public void add(DepVo dep){
        depDAO.add(dep);
    }
    @Transactional
    public void update(DepVo dep){
        depDAO.update(dep);
    }
    //列表
    @Transactional
    public List<DepVo> list(){
        return depDAO.findAllDep();
    }
    //通过Id获取一条记录
    @Transactional
    public DepVo findById(int depId){
        return depDAO.findById(depId);
    }
    //删除
    @Transactional
    public void delete(int depId){
        depDAO.deleteById(depId);
    }
    //通过Id获取当前部门的所有员工
    @Transactional
    public DepVo findUsersByDepId(int depId){
        return depDAO.findDepAndUsers(depId);
    }
}
