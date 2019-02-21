package com.ht.servlet;

import com.ht.common.DBConn;
import com.ht.common.PageObject;
import com.ht.dao.ChecksDAO;
import com.ht.dao.DepDAO;
import com.ht.dao.UsersDAO;
import com.ht.vo.CheckVo;
import com.ht.vo.DepVo;
import com.ht.vo.UsersVo;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="usersServlet",urlPatterns ="/usersServlet")
public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        SqlSession session= DBConn.getSession();
        UsersDAO usersDAO=session.getMapper(UsersDAO.class);
        if(action==null||action.equals("")){
            action="list";
        }
        if(action.equals("init")){
            DepDAO depDAO=session.getMapper(DepDAO.class);
            List<DepVo> depList=depDAO.findAllDep();
            //设置request变量，在页面可以使用标签获取
            request.setAttribute("depList", depList);

            //转向到usersAdd.jsp页面
            request.getRequestDispatcher("usersAdd.jsp").forward(request, response);
        }else if(action.equals("add")) {
            int userId=0;
            if(!request.getParameter("userId").equals("")){
                userId=Integer.parseInt(request.getParameter("userId"));
            }
            int status=1;
            if(request.getParameter("status")!=null){
                status=Integer.getInteger(request.getParameter("status"));
            }
            String userName = request.getParameter("userName");
            int depId = Integer.parseInt(request.getParameter("depId"));
            String password = request.getParameter("password");
            int age = Integer.parseInt(request.getParameter("age"));
            String sex = request.getParameter("sex");
            String job = request.getParameter("job");
            String remark = request.getParameter("remark");
            String checkName=request.getParameter("checkName");
            float score=Float.parseFloat(request.getParameter("score"));
            UsersVo user = new UsersVo();
            user.setUserName(userName);
            user.setDepId(depId);
            user.setPassword(password);
            user.setAge(age);
            user.setSex(sex);
            user.setJob(job);
            user.setRemark(remark);
            user.setStatus(1);
            usersDAO.add(user);
            //新增账号资料，要利用MyBatis的主键回填功能，获取员工表的主键
            CheckVo check=new CheckVo();
            check.setUserId(user.getUserId());
            check.setCheckName(checkName);
            check.getScore(score);
            ChecksDAO checkDAO=session.getMapper(ChecksDAO.class);
            session.commit();
            List<UsersVo> usersList = usersDAO.userAnddep();
            request.setAttribute("usersList", usersList);
            request.getRequestDispatcher("usersList.jsp").forward(request, response);
        }else if(action.equals("list")){
            list(request);
            request.getRequestDispatcher("usersList.jsp").forward(request, response);
        }
    }

    public void list(HttpServletRequest request) {
        SqlSession session=DBConn.getSession();
        UsersDAO usersDAO=session.getMapper(UsersDAO.class);
        UsersVo users=new UsersVo();
        //没有任何条件,第一次直接从地址栏进入页面
        if(request.getParameter("userName")==null){
            users.setStatus(-1);
        }else{
            users.setUserName(request.getParameter("userName").trim());
            users.setDepId(Integer.parseInt(request.getParameter("depId")));
            users.setStatus(Integer.getInteger(request.getParameter("status")));
            users.setSex(request.getParameter("sex"));
        }
        int cnt=0;
        try{
            System.out.println(".........................xxx");
            cnt = usersDAO.getcount(users);
            System.out.println("....................."+cnt);
        }catch (Exception e){
            e.printStackTrace();
        }
        PageObject pager=new PageObject();
        pager.setTotalRows(cnt);
        int curPage=1;
        if(request.getParameter("curPage")!=null){
            curPage=Integer.parseInt(request.getParameter("curPage"));
        }
        if(curPage<1){
            curPage=1;
        }else if(curPage>pager.getPageCount()){
            curPage = pager.getPageCount();
        }
        pager.setCur_page(curPage);
        request.setAttribute("pager", pager);
        List<UsersVo> usersList = usersDAO.searchByCondition(users,pager);
        request.setAttribute("usersList", usersList);

        //初始化部门下拉框的值
        DepDAO depDAO = session.getMapper(DepDAO.class);
        List<DepVo> depList = depDAO.findAllDep();
        //设置request变量，在页面可以使用标签获取
        request.setAttribute("depList", depList);
    }
}
