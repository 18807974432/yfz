package com.ht.controller;

import com.ht.service.SysAccessService;
import com.ht.service.UserAccessService;
import com.ht.service.UsersService;
import com.ht.vo.SysAccessVo;
import com.ht.vo.UserAccessVo;
import com.ht.vo.UsersVo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/acc")
public class AccessController {

    @Resource
    SysAccessService sysAccessService;
    @Resource
    UserAccessService userAccessService;
    @Resource
    UsersService usersService;

    @RequestMapping(value = "/userAcc")
    public String userAcc(int userId, ModelMap modelMap){
        List<UserAccessVo> userAccessList=userAccessService.findByUserId(userId);
        List<SysAccessVo> sysAccessList=sysAccessService.findAll();
        List<UsersVo> usersList=usersService.listAll();

        modelMap.addAttribute("userAccessList",userAccessList);
        modelMap.addAttribute("sysAccessList",sysAccessList);
        modelMap.addAttribute("usersList",usersList);
        modelMap.addAttribute("userId",userId);
        return "userAccess";
    }
    //授予权限
    @Transactional
    @RequestMapping(value="grant")
    public String grant(int userId,int[] sysId,ModelMap modelMap ){
        if(sysId!=null){
            for (int i : sysId) {
                UserAccessVo userAcc = new UserAccessVo();
                userAcc.setSysId(i);
                userAcc.setUserId(userId);
                userAccessService.add(userAcc);
            }
        }


        List<UserAccessVo> userAccessVoList=userAccessService.findByUserId(userId);
        List<SysAccessVo> sysAccessVoList=sysAccessService.findAll();
        List<UsersVo> usersList=usersService.listAll();

        modelMap.addAttribute("userAcclist",userAccessVoList);
        modelMap.addAttribute("sysAcclist",sysAccessVoList);
        modelMap.addAttribute("usersList",usersList);
        modelMap.addAttribute("userId",userId);
        return "userAccess";
    }

}

