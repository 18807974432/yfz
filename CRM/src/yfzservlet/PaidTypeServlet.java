package yfzservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PaidTypeVo;

import common.Page;

import yfzDAO.BaseDAO;



public class PaidTypeServlet extends HttpServlet {
	
	BaseDAO dao = new BaseDAO();
	Page pager = new Page();
	
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
				String paidtypename = request.getParameter("paidtypename");
				dao.savepaidtype(paidtypename);
				list(request,response);
			}else if(action.equals("delpaid")){
				String paidtypeid = request.getParameter("paidtypeid");
				dao.delPaid(paidtypeid);
				list(request,response);
			}else if(action.equals("list")){
				list(request,response);
			}else if(action.equals("updateSave")){
				String paidtypeid=request.getParameter("paidtypeid");
				String paidtypename=request.getParameter("paidtypename");
				dao.updatePaidtype(Integer.parseInt(paidtypeid),paidtypename);
				list(request,response);
			}else if(action.equals("update")){
				String paidtypeid=request.getParameter("paidTypeid");
				PaidTypeVo paidtype = dao.findBypaidtypeid(paidtypeid);
				request.setAttribute("paidtype", paidtype);
				//转向到列表页面,这个语句相当于jsp:forward动作元素
				request.getRequestDispatcher("main/PaidType/paidTypeUpdate.jsp").forward(request, response);
			}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
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
		int cnt =dao.findcount("paidtype");
		//自动计算总页数
		pager.setTotalRows(cnt);
		
			
		List<PaidTypeVo> paidtypeList =dao.findPaidTypeByPage(pager.getStartRow(), pager.getPageRow());
		//定义request变量，在jsp页面中使用
		request.setAttribute("paidtypeList", paidtypeList);
		//分页参数
		request.setAttribute("pager",pager);
		//转向到列表页面,这个语句相当于jsp:forward动作元素
		request.getRequestDispatcher("main/PaidType/paidTypeList.jsp").forward(request, response);
	}
}
