package com.ht.dao;

import com.ht.common.PageObject;
import com.ht.vo.UsersVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersDAO {
    void add(UsersVo usersVo);
    List<UsersVo> userAnddep();
    List<UsersVo> searchByCondition(@Param("users") UsersVo users, @Param("pager") PageObject pager);
    int getcount(UsersVo users);

}
