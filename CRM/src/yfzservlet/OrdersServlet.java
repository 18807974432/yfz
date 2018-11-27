package yfzservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Page;

import yfzDAO.BaseDAO;
import bean.CustomerInfoVo;
import bean.OrdersVo;
import bean.UsersVo;

public class OrdersServlet extends HttpServlet {
	private BaseDAO dao=new BaseDAO();
	Page pager = new Page();
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		String action = request.getParameter("action");
		
		if(action==null){
			action="list";
		}
		if(action.equals("list")){
			list(request,response);
		}else if(action.equals("init")){
			System.out.println("init");
			List<CustomerInfoVo> customerList=dao.findCustomerAll();
			request.setAttribute("customerList",customerList);
			List<UsersVo> userList=dao.findUsersAll();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/main/Orders/ordersAdd.jsp").forward(request,response);
		}else if(action.equals("add")){  //新增
			String orderId=request.getParameter("orderId");
			String custid=request.getParameter("custid");
			
			String userid=request.getParameter("userid");
			String orderType=request.getParameter("orderType");
			String orderStatus=request.getParameter("orderStatus");
			String process=request.getParameter("process");
			String totalMoney=request.getParameter("totalMoney");
			String oprtime=request.getParameter("oprtime");
			String operator=request.getParameter("operator");
			String remark=request.getParameter("remark");
			
			OrdersVo o=new OrdersVo();
			o.setOrderId(orderId);
			o.setCustid(Integer.parseInt(custid));
			
			o.setUserid(Integer.parseInt(userid));
			o.setOrderType(orderType);
			o.setOrderStatus(orderStatus);
			o.setProcess(process);
			o.setTotalMoney(totalMoney);
			o.setOprtime(oprtime);
			o.setOperator(operator);
			o.setRemark(remark);
			
			List<CustomerInfoVo> customerList=dao.findCustomerAll();
			request.setAttribute("customerList",customerList);
			List<UsersVo> userList=dao.findUsersAll();
			request.setAttribute("userList", userList);
			
			dao.saveOrders(o);
			list(request,response);
		
		}else if(action.equals("update")){ //修改
			String orderId=request.getParameter("orderId");
			//查找要修改的用户资料
			OrdersVo o=dao.findByOrderId(orderId);
			request.setAttribute("o",o);
			//下拉框中的所有客户数据
			List<CustomerInfoVo> customerList=dao.findCustomerAll();
			request.setAttribute("customerList",customerList);
			//下拉框中的所有用户数据
			List<UsersVo> userList=dao.findUsersAll();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/main/Orders/ordersUpdate.jsp").forward(request, response);
		}else if(action.equals("updateSave")){
			String orderId=request.getParameter("orderId");
			String custid=request.getParameter("custid");
			String userid=request.getParameter("userid");
			String orderType=request.getParameter("orderType");
			String orderStatus=request.getParameter("orderStatus");
			String process=request.getParameter("process");
			String totalMoney=request.getParameter("totalMoney");
			String oprtime=request.getParameter("oprtime");
			String operator=request.getParameter("operator");
			String remark=request.getParameter("remark");
			
			OrdersVo o=new OrdersVo();
			o.setOrderId(orderId);
			o.setCustid(Integer.parseInt(custid));
			o.setUserid(Integer.parseInt(userid));
			o.setOrderType(orderType);
			o.setOrderStatus(orderStatus);
			o.setProcess(process);
			o.setTotalMoney(totalMoney);
			o.setOprtime(oprtime);
			o.setOperator(operator);
			o.setRemark(remark);
			dao.updateOrder(o);
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
			int cnt =dao.findCount("Orders");
			//自动计算总页数
			pager.setTotalRows(cnt);
			
			List<OrdersVo> ordersList=dao.findOrdersBytPage(pager.getStartRow(), pager.getPageRow());
			request.setAttribute("ordersList", ordersList);
			
			//分页参数
			request.setAttribute("pager", pager);
			
			request.getRequestDispatcher("/main/Orders/ordersList.jsp").forward(request,response);
	}
}
