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
				//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
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
		//���õ�ǰҳ
		pager.setCur_page(Integer.parseInt(curPage));
		//��һ���������ܼ�¼��
		int cnt =dao.findcount("paidtype");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		
			
		List<PaidTypeVo> paidtypeList =dao.findPaidTypeByPage(pager.getStartRow(), pager.getPageRow());
		//����request��������jspҳ����ʹ��
		request.setAttribute("paidtypeList", paidtypeList);
		//��ҳ����
		request.setAttribute("pager",pager);
		//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
		request.getRequestDispatcher("main/PaidType/paidTypeList.jsp").forward(request, response);
	}
}
