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
			v.setUsername("��������ʮ���һ�");
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
			v.setUsername("��������ʮ���һ�");
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
		//���õ�ǰҳ
		pager.setCur_page(Integer.parseInt(curPage));
		//��һ���������ܼ�¼��
		int cnt =dao.findcount("finance");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		
		
		//��ѯ�б�
		List<financeVo> financeList = dao.financeList(pager.getStartRow(), pager.getPageRow());
		//����request��������jspҳ����ʹ��
		request.setAttribute("financeList", financeList);
		//��ҳ����
		request.setAttribute("pager", pager);
		//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
		request.getRequestDispatcher("main/finance/financeList.jsp").forward(request, response);
	}
}
