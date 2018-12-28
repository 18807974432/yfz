package com.ht.mapper;

import com.ht.vo.UserAccessVo;

import java.util.List;

public interface UserAccessDAO {
    //查询用户权限
    List<UserAccessVo> findAll();
    //通过员工ID查询用户权限
    List<UserAccessVo> findByUserId(int userId);
    //新增用户权限
    void add(UserAccessVo accessVo);
    //删除用户权限
    void deleteById(UserAccessVo acc);

    UserAccessVo isUserAcc(UserAccessVo acc);

}
