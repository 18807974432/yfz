package com.ht.service;

import com.ht.base.PageObject;
import com.ht.mapper.UsersDAO;
import com.ht.vo.UsersVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersService {
    @Resource
    UsersDAO usersDAO;
    @Transactional
    public void add(UsersVo users){
        usersDAO.add(users);
    }
    @Transactional
    public List listAll(){

        return usersDAO.findAllUsers();
    }

    @Transactional
    public boolean findByName(String userName){
       UsersVo usersVo=usersDAO.findByName(userName);
       if(usersVo==null){
            return false;
       }else {
           return true;
       }
    }

    public UsersVo findById(int userId){
        return usersDAO.findById(userId);
    }

    @Transactional
    public void update(UsersVo users){
        usersDAO.update(users);
    }

    @Transactional
    public void delete(int userId){
        usersDAO.delete(userId);
    }

    @Transactional
    public int count(){
        return usersDAO.count();
    }

    @Transactional
    public List listByPage(PageObject pageObject){
        return usersDAO.listByPage(pageObject);
    }

    @Transactional
    public List searchByCondition(PageObject pageObject,UsersVo usersVo){
        return usersDAO.searchByCondition(usersVo,pageObject);
    }

    @Transactional
    public int countByCondition(UsersVo usersVo){
        return usersDAO.countByCondition(usersVo);
    }

    public boolean login(UsersVo users){
        boolean bRet=false;
        UsersVo user=usersDAO.login(users);
        if(user!=null){
            bRet=true;
        }
        return bRet;
    }
}
