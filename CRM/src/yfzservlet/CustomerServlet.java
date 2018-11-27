package yfzservlet;

import java.io.IOException;
import java.util.List;
import common.Page;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CustomerInfoVo;
import yfzDAO.BaseDAO;

public class CustomerServlet extends HttpServlet {
	BaseDAO dao=new BaseDAO();
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
				String custname=request.getParameter("custname");
				String custtype=request.getParameter("custtype");
				String bankAccount=request.getParameter("bankAccount");
				String bankName=request.getParameter("bankName");
				String Contact=request.getParameter("Contact");
				String Phone=request.getParameter("Phone");
				String TicketName=request.getParameter("TicketName");
				String TicketAddr=request.getParameter("TicketAddr");
				String TicketTel=request.getParameter("TicketTel");
				String TaxNo=request.getParameter("TaxNo");
				String custState=request.getParameter("custState");
				String username=request.getParameter("username");
				String source=request.getParameter("source");
				
				CustomerInfoVo c=new CustomerInfoVo();
				c.setCustname(custname);
				c.setCusttype(custtype);
				c.setBankAccount(bankAccount);
				c.setBankName(bankName);
				c.setContact(Contact);
				c.setPhone(Phone);
				c.setTicketName(TicketName);
				c.setTicketAddr(TicketAddr);
				c.setTicketTel(TicketTel);
				c.setTaxNo(TaxNo);
				c.setCustState(custState);
				c.setUsername(username);
				c.setSource(source);
				dao.saveCustomer(c);
				list(request,response);
			}else if(action.equals("list")){
				list(request,response);
			}else if(action.equals("delCustomer")){
				String custId=request.getParameter("custId");
				dao.delCustomer(custId);
				list(request,response);
			}else if(action.equals("update")){
				String custId=request.getParameter("custId");
				CustomerInfoVo c=dao.findByCustomerId(custId);
				request.setAttribute("c",c);
				request.getRequestDispatcher("/main/CustomerInfo/customerUpdate.jsp").forward(request,response);
			}else if(action.equals("updateSave")){
				String custId=request.getParameter("custId");
				String custname=request.getParameter("custname");
				String custtype=request.getParameter("custtype");
				String bankAccount=request.getParameter("bankAccount");
				String bankName=request.getParameter("bankName");
				String contact=request.getParameter("contact");
				String phone=request.getParameter("phone");
				String ticketName=request.getParameter("ticketName");
				String ticketAddr=request.getParameter("ticketAddr");
				String ticketTel=request.getParameter("ticketTel");
				String taxNo=request.getParameter("taxNo");
				String custState=request.getParameter("custState");
				String username=request.getParameter("username");
				String source=request.getParameter("source");
				
				CustomerInfoVo c=new CustomerInfoVo();
				c.setCustId(Integer.parseInt(custId));
				c.setCustname(custname);
				c.setCusttype(custtype);
				c.setBankAccount(bankAccount);
				c.setBankName(bankName);
				c.setContact(contact);
				c.setPhone(phone);
				c.setTicketName(ticketName);
				c.setTicketAddr(ticketAddr);
				c.setTicketTel(ticketTel);
				c.setTaxNo(taxNo);
				c.setCustState(custState);
				c.setUsername(username);
				c.setSource(source);
				dao.updataCustomer(c);
				list(request,response);
			}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
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
		int cnt =dao.findCount("CustomerInfo");
		//自动计算总页数
		pager.setTotalRows(cnt);
		
		//查询列表
		List<CustomerInfoVo>customerList=dao.findCustomerByPage(pager.getStartRow(), pager.getPageRow());
		request.setAttribute("customerList",customerList);
		//分页参数
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/main/CustomerInfo/customerList.jsp").forward(request,response);
	}
}
