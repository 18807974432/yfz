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
	//�������ݿ�
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
		//��ȡaction��ֵ
		String action=request.getParameter("action");
		if(action==null || action.equals("list")){
			List(request,response);
		}else if(action.equals("add")){   //����������
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
		}else if(action.equals("del")){  //ɾ����Ӧ��Ϣ
			String supplierId=request.getParameter("supplierId");
			base.delsupplier(supplierId);
			List(request,response);
		}else if(action.equals("update")){  //�޸Ĺ�Ӧ��
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
			//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
			request.getRequestDispatcher("/main/supplier/supplierUpdate.jsp").forward(request, response);
			
		}else if(action.equals("updateSave")){ //�޸ı���
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

	//��ҳ��ѯ
	private void List(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//��ȡҳ�����
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
		int cnt =base.findCount("supplier");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		//��ѯ�б�
		List<supplierVo> supplierList = base.findBysupplierPage(pager.getStartRow(), pager.getPageRow());
		//����request���� ��jspҳ��ʹ��
		request.setAttribute("supplierList", supplierList);
		//��ҳ����
		request.setAttribute("pager", pager);
		//��תҳ��
		
		request.getRequestDispatcher("/main/supplier/supplierList.jsp").forward(request, response);
		
		
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
