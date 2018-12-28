package com.ht.controller;

import com.ht.service.DepService;
import com.ht.vo.DepVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller

@RequestMapping(value="/dep")
public class DepController {
    @Resource
    DepService depService;
    @RequestMapping(value="/init")
    public String init(Model model){
        DepVo dep=new DepVo();
        model.addAttribute("depVo",dep);
        return "depAdd";
    }

    @RequestMapping(value="/update")
    public String update(int depId,Model model){
        DepVo dep=depService.findById(depId);
        model.addAttribute("depVo",dep);
        return "depAdd";
    }

    @RequestMapping(value="/add")
    public String add(DepVo dep, Model model){
        if(dep.getDepId()>0){
            depService.update(dep);
        }else {
            depService.add(dep);
        }
        List<DepVo> depList=depService.list();
        model.addAttribute("depList",depList);
        return "depList";
    }
    /**
     * 地址栏通过get方式传递参数
     * 方法2：
     * 自动获取地址栏问号后面depId参数的值，并且转换为整形
     *
     * */
    @RequestMapping(value="/del")
    public String del(int depId, ModelMap model){
        depService.delete(depId);
        List<DepVo> depList = depService.list();
        model.addAttribute("depList", depList);
        return "depList";
    }
    @RequestMapping(value="/list")
    public String list(Model model){
        List<DepVo> depList = depService.list();
        model.addAttribute("depList", depList);
        return "depList";
    }

    /**
     * 地址栏通过get方式传递参数
     * 方法3：地址栏get方式提交参数，表现形式/SpringVVC2/dep/emp/1/
     * @PathVariable("depId") int depId
     * 获取地址栏斜杠后面depId参数的值，并且转换为整形
     *
     * */
    //查询当前部门下的所有员工
    @RequestMapping(value="/users/{depId}")
    public String emp(@PathVariable("depId") int depId, Model model){
        DepVo dep = depService.findUsersByDepId(depId);
        model.addAttribute("dep", dep);
        return "depUsersList";
    }
}