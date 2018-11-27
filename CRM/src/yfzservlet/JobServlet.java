package yfzservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yfzDAO.BaseDAO;

import bean.CustomerInfoVo;
import bean.OrdersVo;
import bean.ProductVo;
import bean.UsersVo;
import bean.jobRecordVo;

import lwDAO.LWDAO;

import common.Page;

public class JobServlet extends HttpServlet {

	BaseDAO dao = new BaseDAO();
	Page pager = new Page();
	jobRecordVo jobVo = new jobRecordVo();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null){
			action="list";
		}
		if(action.equals("list")){
			List(request,response);
		}else if(action.equals("init")){
			//����
			List<OrdersVo> ordersList=dao.findOrdersAll();
			request.setAttribute("ordersList", ordersList);
			//��Ʒ
			List<ProductVo> productList=dao.findProductAll();
			request.setAttribute("productList", productList);
			//�ͻ�
			List<CustomerInfoVo> customerList=dao.findCustomerAll();
			request.setAttribute("customerList", customerList);
			//�û�
			List<UsersVo> userList=dao.findUserAll();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("main/jobRecord/jobRecordAdd.jsp").forward(request,response);
		}else if(action.equals("add")){
			String orderId=request.getParameter("orderId");
			String jobDate=request.getParameter("jobDate");
			String prodName=request.getParameter("prodName");
			String custid=request.getParameter("custid");
			String jobContent=request.getParameter("jobContent");
			String callback=request.getParameter("callback");
			String userid=request.getParameter("userid");
			String custEval=request.getParameter("custEval");
			String custSign=request.getParameter("custSign");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			String workDay=request.getParameter("workDay");
			String busMoney=request.getParameter("busMoney");
			String attachMoney=request.getParameter("attachMoney");
			
			jobRecordVo j=new jobRecordVo();
			j.setOrderId(orderId);
			j.setJobDate(jobDate);
			j.setProdName(prodName);
			j.setCustid(custid);
			j.setJobContent(jobContent);
			j.setCallback(callback);
			j.setUserid(userid);
			j.setCustEval(custEval);
			j.setCustSign(custSign);
			j.setStartTime(startTime);
			j.setEndTime(endTime);
			j.setWorkDay(workDay);
			j.setBusMoney(busMoney);
			j.setAttachMoney(attachMoney);
			dao.saveJobrecord(j);
			List(request,response);
		}else if(action.equals("update")){
			String jobId=request.getParameter("jobId");
			System.out.println(jobId);
			jobRecordVo j=dao.findByJobId(jobId);
			request.setAttribute("j",j);
			//����
			List<OrdersVo> ordersList=dao.findOrdersAll();
			request.setAttribute("ordersList", ordersList);
			//��Ʒ
			List<ProductVo> productList=dao.findProductAll();
			request.setAttribute("productList", productList);
			//�ͻ�
			List<CustomerInfoVo> customerList=dao.findCustomerAll();
			request.setAttribute("customerList", customerList);
			//�û�
			List<UsersVo> userList=dao.findUsersAll();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("main/jobRecord/jobRecordUpdate.jsp").forward(request,response);
		}else if(action.equals("updateSave")){
			String jobId=request.getParameter("jobId");
			String orderId=request.getParameter("orderId");
			String jobDate=request.getParameter("jobDate");
			String prodName=request.getParameter("prodName");
			String custid=request.getParameter("custid");
			String jobContent=request.getParameter("jobContent");
			String callback=request.getParameter("callback");
			String userid=request.getParameter("userid");
			String custEval=request.getParameter("custEval");
			String custSign=request.getParameter("custSign");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			String workDay=request.getParameter("workDay");
			String busMoney=request.getParameter("busMoney");
			String attachMoney=request.getParameter("attachMoney");
			
			jobRecordVo j=new jobRecordVo();
			j.setJobId(Integer.parseInt(jobId));
			j.setOrderId(orderId);
			j.setJobDate(jobDate);
			j.setProdName(prodName);
			j.setCustid(custid);
			j.setJobContent(jobContent);
			j.setCallback(callback);
			j.setUserid(userid);
			j.setCustEval(custEval);
			j.setCustSign(custSign);
			j.setStartTime(startTime);
			j.setEndTime(endTime);
			j.setWorkDay(workDay);
			j.setBusMoney(busMoney);
			j.setAttachMoney(attachMoney);
			dao.jobrecodeUpdate(j);
			List(request,response);
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
		int cnt =dao.findcount("users");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		
		
		//��ѯ�б�
		List<jobRecordVo> jobRecordList = dao.findJobrecordAll(pager.getStartRow(), pager.getPageRow());
		//����request��������jspҳ����ʹ��
		request.setAttribute("jobRecordList", jobRecordList);
		System.out.println(jobRecordList);
		//��ҳ����
		request.setAttribute("pager", pager);
		//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
		request.getRequestDispatcher("main/jobRecord/jobRecordList.jsp").forward(request, response);
	}
}
