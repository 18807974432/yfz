package lwserlvlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrdersVo;
import bean.PaidTypeVo;
import bean.ProductVo;
import bean.UsersVo;
import bean.financeVo;

import lwDAO.LWDAO;

import common.Page;

public class FinanceServlet extends HttpServlet {

	Page pager = new Page();
	LWDAO dao = new LWDAO();
	financeVo finance = new financeVo();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null){
			action = "list";
		}
		if(action.equals("list")){
			List(request, response);
		}else if(action.equals("init")){
			List<OrdersVo> orderlist = dao.findOrdersAll();
			List<ProductVo> productlist = dao.findProductAll();
			List<PaidTypeVo> paidTypelist = dao.findPaidTypeAll();

			request.setAttribute("orderlist", orderlist);
			request.setAttribute("productlist", productlist);
			request.setAttribute("paidTypelist", paidTypelist);
			request.getRequestDispatcher("main/finance/financeAdd.jsp").forward(request, response);
		}else if(action.equals("add")){
			String orderId = request.getParameter("orderId");
			String prodid = request.getParameter("prodid");
			String paidtypeid = request.getParameter("paidtypeid");
			String remainMoney = request.getParameter("remainMoney");
			String paidMoney = request.getParameter("paidMoney");
			String orderMoney = request.getParameter("orderMoney");
			String paidPerson = request.getParameter("paidPerson");
			String inbank = request.getParameter("inbank");
			String bankAccount = request.getParameter("bankAccount");
			String outbank = request.getParameter("outbank");
			String warrant = request.getParameter("warrant");
			String paidTime = request.getParameter("paidTime");
			String paidinTime = request.getParameter("paidinTime");
			String invalid = request.getParameter("invalid");
			String oprType = request.getParameter("oprType");
			
			financeVo v = new financeVo();
			v.setOrderId(orderId);
			v.setProdid(Integer.parseInt(prodid));
			v.setPaidtypeid(paidtypeid);
			v.setRemainMoney(remainMoney);
			v.setOrderMoney(orderMoney);
			v.setPaidPerson(paidPerson);
			v.setPaidMoney(paidMoney);
			v.setInbank(inbank);
			v.setBankAccount(bankAccount);
			v.setOutbank(outbank);
			v.setWarrant(warrant);
			v.setPaidTime(paidTime);
			v.setPaidinTime(paidinTime);
			v.setInvalid(invalid);
			v.setUsername("三生三世十里桃花");
			v.setOprType(oprType);
			dao.addfinance(v);
			List(request, response);
		}else if(action.equals("update")){
			String financeId = request.getParameter("financeId");
			financeVo financeList = dao.financeUpdatefind(financeId);
			List<OrdersVo> orderlist = dao.findOrdersAll();
			List<ProductVo> productlist = dao.findProductAll();
			List<PaidTypeVo> paidTypelist = dao.findPaidTypeAll();

			request.setAttribute("orderlist", orderlist);
			request.setAttribute("productlist", productlist);
			request.setAttribute("paidTypelist", paidTypelist);
			request.setAttribute("financeList", financeList);
			request.getRequestDispatcher("main/finance/financeUpdate.jsp").forward(request, response);
		}else if(action.equals("updateSave")){
			String financeid = request.getParameter("financeid");
			String orderId = request.getParameter("orderId");
			String prodid = request.getParameter("prodid");
			String paidtypeid = request.getParameter("paidtypeid");
			String remainMoney = request.getParameter("remainMoney");
			String paidMoney = request.getParameter("paidMoney");
			String orderMoney = request.getParameter("orderMoney");
			String paidPerson = request.getParameter("paidPerson");
			String inbank = request.getParameter("inbank");
			String bankAccount = request.getParameter("bankAccount");
			String outbank = request.getParameter("outbank");
			String warrant = request.getParameter("warrant");
			String paidTime = request.getParameter("paidTime");
			String paidinTime = request.getParameter("paidinTime");
			String invalid = request.getParameter("invalid");
			String oprType = request.getParameter("oprType");
			
			financeVo v = new financeVo();
			v.setFinanceId(Integer.parseInt(financeid));
			v.setOrderId(orderId);
			v.setProdid(Integer.parseInt(prodid));
			v.setPaidtypeid(paidtypeid);
			v.setRemainMoney(remainMoney);
			v.setOrderMoney(orderMoney);
			v.setPaidPerson(paidPerson);
			v.setPaidMoney(paidMoney);
			v.setInbank(inbank);
			v.setBankAccount(bankAccount);
			v.setOutbank(outbank);
			v.setWarrant(warrant);
			v.setPaidTime(paidTime);
			v.setPaidinTime(paidinTime);
			v.setInvalid(invalid);
			v.setUsername("三生三世十里桃花");
			v.setOprType(oprType);
			dao.updatefinance(v, financeid);
			List(request, response);
		}
	}
	public void List(HttpServletRequest request, HttpServletResponse response)
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
		int cnt =dao.findcount("finance");
		//自动计算总页数
		pager.setTotalRows(cnt);
		
		
		//查询列表
		List<financeVo> financeList = dao.financeList(pager.getStartRow(), pager.getPageRow());
		//定义request变量，在jsp页面中使用
		request.setAttribute("financeList", financeList);
		//分页参数
		request.setAttribute("pager", pager);
		//转向到列表页面,这个语句相当于jsp:forward动作元素
		request.getRequestDispatcher("main/finance/financeList.jsp").forward(request, response);
	}
}
