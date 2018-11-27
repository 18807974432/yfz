package lfdservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import common.Page;
import common.PageObject;

import bean.UsersVo;
import bean.degreesVo;

import lfdDAO.lfdBaseDAO;
import lwDAO.LWDAO;

public class DegreesServlet extends HttpServlet {
	//�������ݿ�
	lfdBaseDAO base=new lfdBaseDAO();
	Page pager = new Page();
	
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
		}else if(action.equals("add")){   //������λ
			String degreename=request.getParameter("degreename").trim();
			
			degreesVo d=new degreesVo();
			d.setDegreename(degreename);
			System.out.println(d.degreeid);
			base.Degreessave(d);
			List(request,response);
			//request.getRequestDispatcher("/degreesAdd.jsp").forward(request, response);
		}else if(action.equals("del")){  //ɾ����λ
			String degreeid=request.getParameter("degreeid");
			base.Degreesdel(degreeid);
			List(request,response);
		}else if(action.equals("update")){  //�޸ĸ�λ
			String degreeid = request.getParameter("degreeid");
			degreesVo dep = base.DegreesfindByStudId(degreeid);
			request.setAttribute("dep", dep);
			//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
			request.getRequestDispatcher("main/degrees/degreesUpdate.jsp").forward(request, response);
			
		}else if(action.equals("updateSave")){
			String degreeid = request.getParameter("degreeid");
			String degreename = request.getParameter("degreename").trim();
			degreesVo d = new degreesVo();
			d.setDegreeid(Integer.parseInt(degreeid));
			d.setDegreename(degreename);
			base.Degreesupdate(d);
			List(request,response);
		} 
	}

		//��ҳ��ѯ
		private void List(HttpServletRequest request, HttpServletResponse response) 
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
			int cnt =base.findCount("Degrees");
			//�Զ�������ҳ��
			pager.setTotalRows(cnt);
			
			//��ѯ�б�
			List<degreesVo> degreesList = base.DegreesfindByPage(pager.getStartRow(), pager.getPageRow());
			//����request���� ��jspҳ��ʹ��
			request.setAttribute("degreesList", degreesList);
			//��ҳ����
			request.setAttribute("pager", pager);
			//��תҳ��
			request.getRequestDispatcher("main/degrees/degreesList.jsp").forward(request, response);
		}

}
