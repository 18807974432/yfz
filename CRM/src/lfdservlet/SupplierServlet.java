package lfdservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Page;

import lfdDAO.lfdBaseDAO;


import bean.supplierVo;

public class SupplierServlet extends HttpServlet {
	//操作数据库
	lfdBaseDAO base=new lfdBaseDAO();
	Page pager = new Page();
	
	public SupplierServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
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
		}else if(action.equals("add")){   //新增供货商
			String supplierName=request.getParameter("supplierName").trim();
			String bankAccount=request.getParameter("bankAccount");
			String bankName=request.getParameter("bankName");
			String contact=request.getParameter("contact");
			String phone=request.getParameter("phone");
			String addr=request.getParameter("addr");
			String remark=request.getParameter("remark");
			supplierVo d=new supplierVo();
			d.setSupplierName(supplierName);
			d.setBankAccount(bankAccount);
			d.setBankName(bankName);
			d.setContact(contact);
			d.setPhone(phone);
			d.setAddr(addr);
			d.setRemark(remark);
			base.savesupplier(d);
			List(request,response);
			//request.getRequestDispatcher("/degreesAdd.jsp").forward(request, response);
		}else if(action.equals("del")){  //删除供应信息
			String supplierId=request.getParameter("supplierId");
			base.delsupplier(supplierId);
			List(request,response);
		}else if(action.equals("update")){  //修改供应商
			String supplierId=request.getParameter("supplierId");
			String supplierName=request.getParameter("supplierName");
			String bankAccount=request.getParameter("bankAccount");
			String bankName=request.getParameter("bankName");
			String contact=request.getParameter("contact");
			String phone=request.getParameter("phone");
			String addr=request.getParameter("addr");
			String remark=request.getParameter("remark");
			supplierVo dep = base.findBysupplierId(supplierId);
			request.setAttribute("dep", dep);
			//转向到列表页面,这个语句相当于jsp:forward动作元素
			request.getRequestDispatcher("/main/supplier/supplierUpdate.jsp").forward(request, response);
			
		}else if(action.equals("updateSave")){ //修改保存
			String supplierId = request.getParameter("supplierId");
			String supplierName = request.getParameter("supplierName").trim();
			String bankAccount =request.getParameter("bankAccount");
			String bankName =request.getParameter("bankName");
			String contact =request.getParameter("contact");
			String phone =request.getParameter("phone");
			String addr =request.getParameter("addr");
			String remark =request.getParameter("remark");
			supplierVo d = new supplierVo();
			d.setSupplierId(Integer.parseInt(supplierId));
			d.setSupplierName(supplierName);
			d.setBankAccount(bankAccount);
			d.setBankName(bankName);
			d.setContact(contact);
			d.setPhone(phone);
			d.setAddr(addr);
			d.setRemark(remark);
			base.update(d);
			List(request,response);
		} 
		
	}

	//分页查询
	private void List(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//获取页面参数
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
		int cnt =base.findCount("supplier");
		//自动计算总页数
		pager.setTotalRows(cnt);
		//查询列表
		List<supplierVo> supplierList = base.findBysupplierPage(pager.getStartRow(), pager.getPageRow());
		//定义request变量 在jsp页面使用
		request.setAttribute("supplierList", supplierList);
		//分页参数
		request.setAttribute("pager", pager);
		//跳转页面
		
		request.getRequestDispatcher("/main/supplier/supplierList.jsp").forward(request, response);
		
		
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
