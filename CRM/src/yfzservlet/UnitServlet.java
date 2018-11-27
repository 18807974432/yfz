package yfzservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UnitVo;

import yfzDAO.BaseDAO;
import common.Page;



public class UnitServlet extends HttpServlet{
	private BaseDAO dao=new BaseDAO();
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
			//����
			if(action.equals("add")){
				String unitName=request.getParameter("unitName");
				dao.saveUnit(unitName);
				list(request,response);
			}else if(action.equals("list")){
				list(request,response);
			}else if(action.equals("delunit")){//ɾ��
				String unitId=request.getParameter("unitId");
				dao.delunit(unitId);
				list(request,response);
			}else if(action.equals("update")){//�޸�
				String 	unitId=request.getParameter("unitId");
				System.out.println(unitId);
				//��ѯ��Ҫ�޸ĵĵ�λ����
				UnitVo unit=dao.findByUnitId(unitId);
				request.setAttribute("unit", unit);
				//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
				request.getRequestDispatcher("/main/Unit/unitUpdate.jsp").forward(request, response);
			}else if(action.equals("updateSave")){
				String unitId=request.getParameter("unitId");
				String unitName=request.getParameter("unitName");
				dao.updateUnit(Integer.parseInt(unitId),unitName);
				list(request,response);
			}
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
			Page pager = new Page();
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
			int cnt =dao.findCount("Unit");
			//�Զ�������ҳ��
			pager.setTotalRows(cnt);
			
			//��ѯ�б�
			List<UnitVo> unitList =dao.findUnitByPage(pager.getStartRow(), pager.getPageRow());
			//����request��������jspҳ����ʹ��
			request.setAttribute("unitList", unitList);
			//��ҳ����
			request.setAttribute("pager",pager);
			//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
			request.getRequestDispatcher("/main/Unit/unitList.jsp").forward(request, response);
			}
}
