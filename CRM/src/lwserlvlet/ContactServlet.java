package lwserlvlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lwDAO.LWDAO;

import common.Page;

import bean.ContactVo;
import bean.CustomerInfoVo;
import bean.UsersVo;

public class ContactServlet extends HttpServlet {
	
	ContactVo con = new ContactVo();
	Page pager = new Page();
	LWDAO dao = new LWDAO();
	
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
			List<UsersVo> userAll = dao.finduserAll();
			List<CustomerInfoVo> custAll = dao.findcustAll();
			
			request.setAttribute("userAll", userAll);
			request.setAttribute("custAll", custAll);
			request.getRequestDispatcher("main/Contact/ContactAdd.jsp").forward(request, response);
		}else if(action.equals("add")){
			String talkDate = request.getParameter("talkDate");
			String custContact = request.getParameter("custContact");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String custid = request.getParameter("custid");
			String qqCode = request.getParameter("qqCode");
			String email = request.getParameter("email");
			String weixin = request.getParameter("weixin");
			String userid = request.getParameter("userid");
			String birthday = request.getParameter("birthday");
			String hobbit = request.getParameter("hobbit");
			String jobName = request.getParameter("jobName");
			String remark = request.getParameter("remark");
			
			ContactVo v = new ContactVo();
			v.setTalkDate(talkDate);
			v.setCustContact(custContact);
			v.setPhone1(phone1);
			v.setPhone2(phone2);
			v.setCustid(Integer.parseInt(custid));
			v.setQqCode(Integer.parseInt(qqCode));
			v.setEmail(email);
			v.setWeixin(weixin);
			v.setUserid(Integer.parseInt(userid));
			v.setBirthday(birthday);
			v.setHobbit(hobbit);
			v.setJobName(jobName);
			v.setRemark(remark);
			
			dao.addcontact(v);
			List(request, response);
		}else if(action.equals("update")){
			String contactId = request.getParameter("contactId");
			List<UsersVo> userAll = dao.finduserAll();
			List<CustomerInfoVo> custAll = dao.findcustAll();
			ContactVo contlist = dao.contUpdatefind(contactId);
			
			request.setAttribute("contlist", contlist);
			request.setAttribute("userAll", userAll);
			request.setAttribute("custAll", custAll);
			request.getRequestDispatcher("main/Contact/ContactUpdate.jsp").forward(request, response);
		}else if(action.equals("updateSave")){
			String contactId = request.getParameter("contactId");
			String talkDate = request.getParameter("talkDate");
			String custContact = request.getParameter("custContact");
			String phone1 = request.getParameter("phone1");
			String phone2 = request.getParameter("phone2");
			String custid = request.getParameter("custid");
			String qqCode = request.getParameter("qqCode");
			String email = request.getParameter("email");
			String weixin = request.getParameter("weixin");
			String userid = request.getParameter("userid");
			String birthday = request.getParameter("birthday");
			String hobbit = request.getParameter("hobbit");
			String jobName = request.getParameter("jobName");
			String remark = request.getParameter("remark");
			
			ContactVo v = new ContactVo();
			v.setTalkDate(talkDate);
			v.setCustContact(custContact);
			v.setPhone1(phone1);
			v.setPhone2(phone2);
			v.setCustid(Integer.parseInt(custid));
			v.setQqCode(Integer.parseInt(qqCode));
			v.setEmail(email);
			v.setWeixin(weixin);
			v.setUserid(Integer.parseInt(userid));
			v.setBirthday(birthday);
			v.setHobbit(hobbit);
			v.setJobName(jobName);
			v.setRemark(remark);
			
			dao.updateSaveContact(v,contactId);
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
		int cnt =dao.findcount("contact");
		//�Զ�������ҳ��
		pager.setTotalRows(cnt);
		
		
		//��ѯ�б�
		List<ContactVo> ContactList = dao.Contactfindlist(pager.getStartRow(), pager.getPageRow());
		//����request��������jspҳ����ʹ��
		request.setAttribute("ContactList", ContactList);
		//��ҳ����
		request.setAttribute("pager", pager);
		//ת���б�ҳ��,�������൱��jsp:forward����Ԫ��
		request.getRequestDispatcher("main/Contact/ContactList.jsp").forward(request, response);
	}
	
}
