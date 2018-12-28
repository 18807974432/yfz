package com.ht.mapper;

import com.ht.base.PageObject;
import com.ht.vo.UsersVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersDAO {
    UsersVo login(UsersVo users);
    List findAllUsers();
    void update(UsersVo users);
    void add(UsersVo users);
    void delete(int userId);
    UsersVo findByName(String userName);
    UsersVo findById(int userId);
    int count();
    int countByCondition(@Param("users") UsersVo users);
    List searchByCondition(@Param("users") UsersVo users,@Param("pager") PageObject pager);
    List listByPage(@Param("pager") PageObject pager);
 }
