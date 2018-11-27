package yfzservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yfzDAO.BaseDAO;
import common.Page;

import bean.DepVo;

public class DepServlet extends HttpServlet{
	private BaseDAO dao=new BaseDAO();
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
			doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
			String action=request.getParameter("action");
			if(action==null){
				action="list";
			}
			if(action.equals("add")){
				String depname=request.getParameter("depname");
				String chairman=request.getParameter("chairman");
				String desc=request.getParameter("description");
				DepVo d=new DepVo();
				d.setDepname(depname);
				d.setChairman(chairman);
				d.setDescription(desc);
				dao.save(d);
				list(request,response);
			}else if(action.equals("list")){
				list(request,response);
			}else if(action.equals("del")){
				String depid=request.getParameter("depid");
				dao.del(depid);
				list(request,response);
			}else if(action.equals("update")){
				String depid=request.getParameter("depid");
				DepVo dep=dao.findByStudId(depid);
				request.setAttribute("dep",dep);
				request.getRequestDispatcher("/main/dep/depUpdate.jsp").forward(request,response);
			}else if(action.equals("updateSave")){
				String depid=request.getParameter("depid");
				String depname=request.getParameter("depname");
				String chairman=request.getParameter("chairman");
				String description=request.getParameter("description");
				
				
				DepVo d=new DepVo();
				d.setDepid(Integer.parseInt(depid));
				d.setDepname(depname);
				d.setChairman(chairman);
				d.setDescription(description);
				dao.update(d);
				list(request,response);
			}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			Page pager = new Page();
			String curPage = request.getParameter("pager.cur_page");
			String pageRow = request.getParameter("pager.pageRow");
	
			if(curPage==null){
				curPage="1";
			}
			
			if(pageRow !=null){
				pager.setPageRow(Integer.parseInt(pageRow));
			}
			//设置当前页
			pager.setCur_page(Integer.parseInt(curPage));
			//第一步：计算总记录数
			int cnt =dao.findCount("Dep");
			//自动计算总页数
			pager.setTotalRows(cnt);
			
			//查询列表
			List<DepVo> depList =dao.findByPage(pager.getStartRow(), pager.getPageRow());
			//定义request变量，在jsp页面中使用
			request.setAttribute("depList", depList);
			//分页参数
			request.setAttribute("pager",pager);
			//转向到列表页面,这个语句相当于jsp:forward动作元素
			request.getRequestDispatcher("/main/dep/depList.jsp").forward(request, response);
			}
}
