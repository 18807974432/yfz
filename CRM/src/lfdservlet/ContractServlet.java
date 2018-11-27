package lfdservlet;	

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CustomerInfoVo;

import bean.UsersVo;
import bean.contractVo;


import common.Page;

import lfdDAO.lfdBaseDAO;

public class ContractServlet extends HttpServlet {

	//操作数据库
	lfdBaseDAO base=new lfdBaseDAO();
	Page pager = new Page();
	public ContractServlet() {
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
		if(action==null){
			action = "list";
		}
		if(action.equals("list")){
			List(request, response);
		}else if(action.equals("init")){
			List<CustomerInfoVo> cusinfolist = base.findcusinfoAll();
			request.setAttribute("cusinfolist", cusinfolist);
			System.out.println("cusinfolist="+cusinfolist);
			
			List<UsersVo> userslist = base.findusersAll();
			request.setAttribute("userslist", userslist);
			System.out.println("userslist="+userslist);
			request.getRequestDispatcher("/main/contract/contractAdd.jsp").forward(request, response);
		
		}
		//新增合同信息
		else if(action.equals("add")){
			String contract_no=request.getParameter("contract_no").trim();
			String contractname=request.getParameter("contractname");
			String custId=request.getParameter("custId");
			String contract_time=request.getParameter("contract_time");
			String due_time=request.getParameter("due_time");
			String total_money=request.getParameter("total_money");
			String deposit=request.getParameter("deposit");
			String pay_type=request.getParameter("pay_type");
			String status=request.getParameter("status");
			String empid=request.getParameter("empid");
			String operator=request.getParameter("operator");
			String oprtime=request.getParameter("oprtime");
			String remark=request.getParameter("remark");
			contractVo d=new contractVo();
			d.setContract_no(contract_no);
			d.setContractname(contractname);
			d.setCustId(Integer.parseInt(custId));
			d.setContract_time(contract_time);
			d.setDue_time(due_time);
			d.setTotal_money(total_money);
			d.setDeposit(Integer.parseInt(deposit));
			d.setPay_type(pay_type);
			d.setStatus(status);
			d.setEmpid(Integer.parseInt(empid));
			d.setOperator(operator);
			d.setOprtime(oprtime);
			d.setRemark(remark);
			
			List<CustomerInfoVo> cusinfolist = base.findcusinfoAll();
			request.setAttribute("cusinfolist", cusinfolist);
			List<UsersVo> userslist = base.findusersAll();
			request.setAttribute("userslist", userslist);
			
			base.savecontract(d);
			List(request,response);
		}
		//修改合同信息
		else if(action.equals("update")){
			
			String contract_id=request.getParameter("contract_id");
			
			List<CustomerInfoVo> cusinfolist = base.findcusinfoAll();
			request.setAttribute("cusinfolist", cusinfolist);
			List<UsersVo> userslist = base.findusersAll();
			request.setAttribute("userslist", userslist);
			
			contractVo dep = base.findBycontractId(contract_id);	
			request.setAttribute("dep", dep);
			//转向到列表页面,这个语句相当于jsp:forward动作元素
			request.getRequestDispatcher("/main/contract/contractUpdate.jsp").forward(request, response);
			
		}
		else if(action.equals("updateSave")){ //修改保存
			String contract_id =request.getParameter("contract_id");
			String contract_no = request.getParameter("contract_no").trim();
			String contractname=request.getParameter("contractname");
			String custId =request.getParameter("custId");
			String contract_time =request.getParameter("contract_time");
			String due_time =request.getParameter("due_time");
			String total_money =request.getParameter("total_money");
			String deposit =request.getParameter("deposit");
			String pay_type =request.getParameter("pay_type");
			String status =request.getParameter("status");
			String empid =request.getParameter("empid");
			String operator =request.getParameter("operator");
			String oprtime =request.getParameter("oprtime");
			String remark=request.getParameter("remark");
			contractVo d = new contractVo();
			d.setContract_id(Integer.parseInt(contract_id));
			d.setContract_no(contract_no);
			d.setContractname(contractname);
			d.setCustId(Integer.parseInt(custId));
			d.setContract_time(contract_time);
			d.setDue_time(due_time);
			d.setTotal_money(total_money);
			d.setDeposit(Integer.parseInt(deposit));
			d.setPay_type(pay_type);
			d.setStatus(status);
			d.setEmpid(Integer.parseInt(empid));
			d.setOperator(operator);
			d.setOprtime(oprtime);
			d.setRemark(remark);
			base.updatecontract(d);
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
		int cnt =base.findCount("contract");
		//自动计算总页数
		pager.setTotalRows(cnt);
		//查询列表
		List<contractVo> contractList = base.findBycontractPage(pager.getStartRow(), pager.getPageRow());
		//定义request变量 在jsp页面使用
		request.setAttribute("contractList", contractList);
		//分页参数
		request.setAttribute("pager", pager);
		//跳转页面
		request.getRequestDispatcher("/main/contract/contractList.jsp").forward(request, response);
		
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
