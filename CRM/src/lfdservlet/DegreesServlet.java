package lfdservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import common.Page;
import common.PageObject;

import bean.UsersVo;
import bean.degreesVo;

import lfdDAO.lfdBaseDAO;
import lwDAO.LWDAO;

public class DegreesServlet extends HttpServlet {
	//操作数据库
	lfdBaseDAO base=new lfdBaseDAO();
	Page pager = new Page();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取action的值
		String action=request.getParameter("action");
		if(action==null || action.equals("list")){
			List(request,response);
		}else if(action.equals("add")){   //新增岗位
			String degreename=request.getParameter("degreename").trim();
			
			degreesVo d=new degreesVo();
			d.setDegreename(degreename);
			System.out.println(d.degreeid);
			base.Degreessave(d);
			List(request,response);
			//request.getRequestDispatcher("/degreesAdd.jsp").forward(request, response);
		}else if(action.equals("del")){  //删除岗位
			String degreeid=request.getParameter("degreeid");
			base.Degreesdel(degreeid);
			List(request,response);
		}else if(action.equals("update")){  //修改岗位
			String degreeid = request.getParameter("degreeid");
			degreesVo dep = base.DegreesfindByStudId(degreeid);
			request.setAttribute("dep", dep);
			//转向到列表页面,这个语句相当于jsp:forward动作元素
			request.getRequestDispatcher("main/degrees/degreesUpdate.jsp").forward(request, response);
			
		}else if(action.equals("updateSave")){
			String degreeid = request.getParameter("degreeid");
			String degreename = request.getParameter("degreename").trim();
			degreesVo d = new degreesVo();
			d.setDegreeid(Integer.parseInt(degreeid));
			d.setDegreename(degreename);
			base.Degreesupdate(d);
			List(request,response);
		} 
	}

		//分页查询
		private void List(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
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
			int cnt =base.findCount("Degrees");
			//自动计算总页数
			pager.setTotalRows(cnt);
			
			//查询列表
			List<degreesVo> degreesList = base.DegreesfindByPage(pager.getStartRow(), pager.getPageRow());
			//定义request变量 在jsp页面使用
			request.setAttribute("degreesList", degreesList);
			//分页参数
			request.setAttribute("pager", pager);
			//跳转页面
			request.getRequestDispatcher("main/degrees/degreesList.jsp").forward(request, response);
		}

}
