package lfdservlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Page;
import common.PageObject;

import lfdDAO.lfdBaseDAO;

import bean.ProductVo;
import bean.UnitVo;
import bean.supplierVo;

public class ProductServlet extends HttpServlet {

	//�������ݿ�
	lfdBaseDAO base=new lfdBaseDAO();
	Page pager = new Page();
	public ProductServlet() {
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
		if(action==null){
			action = "list";
		}
		if(action.equals("list")){
			List(request, response);
		}else if(action.equals("init")){
			List<UnitVo> unitlist = base.findunitAll();
			request.setAttribute("unitlist", unitlist);
			List<supplierVo> supplierList = base.findsupplierAll();
			request.setAttribute("unitlist", unitlist);
			request.setAttribute("supplierList", supplierList);
			request.getRequestDispatcher("/main/Product/ProductAdd.jsp").forward(request, response);
		
		}
		else if(action.equals("add")){//������Ʒ
			String prodid=request.getParameter("prodid");
			String prodname=request.getParameter("prodname").trim();
			String prodModel=request.getParameter("prodModel");
			String prodUnit=request.getParameter("prodUnit");
			String prodStyle=request.getParameter("prodStyle");
			String prodCount=request.getParameter("prodCount");
			String inPrice=request.getParameter("inPrice");
			String salePrice=request.getParameter("salePrice");
			String lowSalePrice=request.getParameter("lowSalePrice");
			String prodSerial=request.getParameter("prodSerial");
			String cdKey=request.getParameter("cdKey");
			String saleStatus=request.getParameter("saleStatus");
			String supplierId=request.getParameter("supplierId");
			String remark=request.getParameter("remark");
			ProductVo d=new ProductVo();
			d.setProdid(prodid);
			d.setProdname(prodname);
			d.setProdModel(prodModel);
			d.setProdUnit(Integer.parseInt(prodUnit));
			d.setProdStyle(prodStyle);
			d.setProdCount(Float.parseFloat(prodCount));
			d.setInPrice(Double.parseDouble(inPrice));
			d.setSalePrice(Double.parseDouble(salePrice));
			d.setLowSalePrice(Double.parseDouble(lowSalePrice));
			d.setProdSerial(prodSerial);
			d.setCdKey(cdKey);
			d.setSaleStatus(saleStatus);
			d.setSupplierId(Integer.parseInt(supplierId));
			d.setRemark(remark);
			
			//�������е����е�λ������
			List<UnitVo> unitlist = base.findunitAll();
			request.setAttribute("unitlist", unitlist);
			//�������е����й�Ӧ�̱�����
			List<supplierVo> supplierList = base.findsupplierAll();
			request.setAttribute("supplierList", supplierList);
			
			base.saveProduct(d);
			List(request,response);
		}
		else if(action.equals("update")){  //�޸���Ʒ
			String prodid=request.getParameter("prodid");
			
			//�������е����е�λ������
			List<UnitVo> unitlist = base.findunitAll();
			request.setAttribute("unitlist", unitlist);
			//�������е����й�Ӧ�̱�����
			List<supplierVo> supplierList = base.findsupplierAll();
			request.setAttribute("supplierList", supplierList);
			
			ProductVo dep = base.findByProductId(prodid);
			System.out.println(dep);
			request.setAttribute("dep", dep);
			//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
			request.getRequestDispatcher("main/Product/ProductUpdate.jsp").forward(request, response);
			
		}
		else if(action.equals("updateSave")){ //�޸ı���
			String prodid = request.getParameter("prodid");
			String prodname = request.getParameter("prodname").trim();
			String prodModel =request.getParameter("prodModel");
			String prodUnit =request.getParameter("prodUnit");
			String prodStyle =request.getParameter("prodStyle");
			String prodCount =request.getParameter("prodCount");
			String inPrice =request.getParameter("inPrice");
			String salePrice =request.getParameter("salePrice");
			String lowSalePrice =request.getParameter("lowSalePrice");
			String prodSerial =request.getParameter("prodSerial");
			String cdKey =request.getParameter("cdKey");
			String saleStatus =request.getParameter("saleStatus");
			String supplierId =request.getParameter("supplierId");
			String remark =request.getParameter("remark");
			ProductVo d = new ProductVo();
			d.setProdid((prodid));
			d.setProdname(prodname);
			d.setProdModel(prodModel);
			d.setProdUnit(Integer.parseInt(prodUnit));
			d.setProdStyle(prodStyle);
			d.setProdCount(Float.parseFloat(prodCount));
			d.setInPrice(Double.parseDouble(inPrice));
			d.setSalePrice(Double.parseDouble(salePrice));
			d.setLowSalePrice(Double.parseDouble(lowSalePrice));
			d.setProdSerial(prodSerial);
			d.setCdKey(cdKey);
			d.setSaleStatus(saleStatus);
			d.setSupplierId(Integer.parseInt(supplierId));
			d.setRemark(remark);
			base.updateProduct(d);
			List(request,response);
		} 
		
	}

	//��ҳ��ѯ
	private void List(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
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
		int cnt =base.findCount("Product");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		
		
		//��ѯ�б�
		List<ProductVo> productList = base.findByProductPage(pager.getStartRow(), pager.getPageRow());
		//����request���� ��jspҳ��ʹ��
		request.setAttribute("productList", productList);
		//��ҳ����
		request.setAttribute("pager", pager);
		//��תҳ��
		request.getRequestDispatcher("/main/Product/ProductList.jsp").forward(request, response);
		
		
	}


	public void init() throws ServletException {
		
	}

}
