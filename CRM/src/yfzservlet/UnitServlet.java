package yfzservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UnitVo;

import yfzDAO.BaseDAO;
import common.Page;



public class UnitServlet extends HttpServlet{
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
			//新增
			if(action.equals("add")){
				String unitName=request.getParameter("unitName");
				dao.saveUnit(unitName);
				list(request,response);
			}else if(action.equals("list")){
				list(request,response);
			}else if(action.equals("delunit")){//删除
				String unitId=request.getParameter("unitId");
				dao.delunit(unitId);
				list(request,response);
			}else if(action.equals("update")){//修改
				String 	unitId=request.getParameter("unitId");
				System.out.println(unitId);
				//查询需要修改的单位资料
				UnitVo unit=dao.findByUnitId(unitId);
				request.setAttribute("unit", unit);
				//转向到列表页面,这个语句相当于jsp:forward动作元素
				request.getRequestDispatcher("/main/Unit/unitUpdate.jsp").forward(request, response);
			}else if(action.equals("updateSave")){
				String unitId=request.getParameter("unitId");
				String unitName=request.getParameter("unitName");
				dao.updateUnit(Integer.parseInt(unitId),unitName);
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
			int cnt =dao.findCount("Unit");
			//自动计算总页数
			pager.setTotalRows(cnt);
			
			//查询列表
			List<UnitVo> unitList =dao.findUnitByPage(pager.getStartRow(), pager.getPageRow());
			//定义request变量，在jsp页面中使用
			request.setAttribute("unitList", unitList);
			//分页参数
			request.setAttribute("pager",pager);
			//转向到列表页面,这个语句相当于jsp:forward动作元素
			request.getRequestDispatcher("/main/Unit/unitList.jsp").forward(request, response);
			}
}
