package com.ht.controller;


import com.ht.base.PageObject;
import com.ht.service.DepService;
import com.ht.service.UsersService;
import com.ht.vo.UsersVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController {
   @Resource
    UsersService usersService;
   @Resource
    DepService depService;
    @RequestMapping(value="/listByPage")
    public String listByPage(HttpServletRequest request,int curPage,ModelMap modelMap,UsersVo usersVo){
        if(usersVo.getUserName()!=null){
            request.getSession().setAttribute("condition",usersVo);
        }
        UsersVo users=(UsersVo)request.getSession().getAttribute("condition");
        if(users==null){
            PageObject pageObject=new PageObject();
            pageObject.setCur_page(curPage);
            pageObject.setTotalRows(usersService.count());
            List list=usersService.listByPage(pageObject);
            modelMap.addAttribute("usersVo",new UsersVo());
            modelMap.addAttribute("usersList",list);
            modelMap.addAttribute("pager",pageObject);
            modelMap.addAttribute("depList",depService.list());
        }else {
            PageObject pageObject=new PageObject();
            pageObject.setCur_page(curPage);
            pageObject.setTotalRows(usersService.countByCondition(users));
            List list=usersService.searchByCondition(pageObject,users);
            modelMap.addAttribute("usersVo",users);
            modelMap.addAttribute("usersList",list);
            modelMap.addAttribute("pager",pageObject);
            modelMap.addAttribute("depList",depService.list());
        }
        return "usersList";
    }

    //清空条件
    @RequestMapping(value="/clear")
    public String searchByCondition(HttpServletRequest request,ModelMap modelMap){
        request.getSession().removeAttribute("condition");
        PageObject pageObject=new PageObject();
        pageObject.setTotalRows(usersService.count());
        List list=usersService.listByPage(pageObject);
        modelMap.addAttribute("usersVo",new UsersVo());
        modelMap.addAttribute("usersList",list);
        modelMap.addAttribute("pager",pageObject);
        modelMap.addAttribute("depList",depService.list());
        return "usersList";
    }

    @RequestMapping(value = "/addInit",method = RequestMethod.GET)
    public String addInit(ModelMap modelMap){
        modelMap.addAttribute("usersVo",new UsersVo());
        modelMap.addAttribute("depList",depService.list());
        return "usersAdd";
    }

    //检验名称是否已被占用
    @RequestMapping(value="/check")
    //返回ajax的json格式数据,利用系统的统一配置（fastjson-1.2.8.jar）,调用阿里的jar自动转换为json格式
    @ResponseBody
    public Map check(String userName){
        Map<String,Object> map=new HashMap<>();
        if(usersService.findByName(userName)){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
        return map;
    }

    @RequestMapping(value = "/add",method =RequestMethod.POST )
    public String add(UsersVo usersVo,ModelMap modelMap){
        if(usersVo.getUserId()==0){
            usersService.add(usersVo);
        }else {
            usersService.update(usersVo);
        }
        return "redirect:/users/listByPage?curPage=1";
    }

    /**
     * 地址栏通过get方式传递参数
     * 方法1：
     * 获取地址栏问号后面depId参数的值，然后赋值给depId变量，并且转换为整形
     * @RequestParam(value="depId") int depId
     *
     * */
    @RequestMapping(value="/find")
    public String find(@RequestParam(value="userId") int userId, ModelMap model){
        UsersVo usersVo=usersService.findById(userId);
        model.addAttribute("usersVo",usersVo);
        model.addAttribute("depList",depService.list());
        return "usersAdd";
    }

    // 方法2：
    //自动获取地址栏问号后面depId参数的值，并且转换为整形
//    @RequestMapping(value="/find")
    public String find2(int userId,ModelMap model){
        UsersVo usersVo=usersService.findById(userId);
        model.addAttribute("usersVo",usersVo);
        model.addAttribute("depList",depService.list());
        return "usersAdd";
    }

    //    @RequestMapping(value="/find/{stuId}")
    public String find3(@PathVariable("userId") int userId, ModelMap model){
        UsersVo usersVo=usersService.findById(userId);
        model.addAttribute("usersVo",usersVo);
        model.addAttribute("depList",depService.list());
        return "usersAdd";
    }

    @RequestMapping(value="/del")
    public String del(int userId,ModelMap modelMap){
        usersService.delete(userId);
        return "redirect:/users/listByPage?curPage=1";
    }


}
