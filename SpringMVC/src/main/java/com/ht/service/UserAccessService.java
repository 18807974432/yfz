package com.ht.service;

import com.ht.mapper.UserAccessDAO;
import com.ht.vo.UserAccessVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//加入服务注解，表示这个类是一个服务层，操作数据库的类
@Service
public class UserAccessService {
    //注入Mybatis的数据库操作类
    @Resource
    UserAccessDAO userAccessDAO;

    //查询用户权限
    public List<UserAccessVo> findAll(){
        return userAccessDAO.findAll();
    }

    //通过用户ID查询权限
    public List<UserAccessVo> findByUserId(int userId){
        return userAccessDAO.findByUserId(userId);
    }

    //新增用户权限
    public void add(UserAccessVo userAccessVo){
        userAccessDAO.add(userAccessVo);
    }

    //删除用户权限
    public void deleteById(UserAccessVo userAccessVo){
        userAccessDAO.deleteById(userAccessVo);
    }
}
