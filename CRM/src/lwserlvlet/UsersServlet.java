package lwserlvlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lwDAO.LWDAO;

import common.AesUtils;
import common.Page;
import common.PageObject;

import bean.DepVo;
import bean.UsersVo;
import bean.degreesVo;

public class UsersServlet extends HttpServlet {

	UsersVo userVo = new UsersVo();
	Page pager = new Page();
	LWDAO userDAO = new LWDAO();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("action");
		System.out.println(action);
		if(action==null){
			action = "userlist";
		}
		//登录
		if(action.equals("login")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("111   ="+username);
			System.out.println("222   ="+password);
//			int s = userDAO.login(username, password);
//			if(s>-1){
//				HttpSession session = request.getSession();
//				UsersVo user = userDAO.finduser(s+""); 
//				session.setAttribute("user", user);
//			}
//			request.getRequestDispatcher("login/login.jsp").forward(request, response);
			response.sendRedirect("main/main.jsp");
		//进入用户列表页面
		}else if(action.equals("userlist")){
			list(request, response);
		//进入新增页面
		}else if(action.equals("init")){
			List<DepVo> depAll = userDAO.findepAll();
			List<degreesVo> degreesAll = userDAO.findegreesAll();
			request.setAttribute("degreesAll", degreesAll);
			request.setAttribute("depAll", depAll);
			request.getRequestDispatcher("main/user/userAdd.jsp").forward(request, response);
		}else if(action.equals("checkuser")){
			String username = request.getParameter("username");
			boolean b = userDAO.checkUser(username);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(b){
				out.println("1");
			}else{
				out.println("0");
			}
			out.flush();
			out.close();
		//新增
		}else if(action.equals("userAdd")){
			String userName = request.getParameter("username");
			String pwd = request.getParameter("password");
			String depid = request.getParameter("depid");
			//职务管连岗位表
			String degreeid = request.getParameter("jobname");
			System.out.println(degreeid);
			String jobname = userDAO.finddegreeName(Integer.parseInt(degreeid));
			
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String qqcode = request.getParameter("qqcode");
			String managerType = request.getParameter("managerType");
			String weixin = request.getParameter("weixin");
			String cardno = request.getParameter("cardno");
			String bankName = request.getParameter("bankName");
			String bankCardNo = request.getParameter("bankCardNo");
			String joinDate = request.getParameter("joinDate");
			String workDate = request.getParameter("workDate");
			String levelDate = request.getParameter("levelDate");
			String baseSalary = request.getParameter("baseSalary");
			String degreeSalary = request.getParameter("degreeSalary");
			String addr = request.getParameter("addr");
			String remark = request.getParameter("remark");
			
			userVo.setUsername(userName);
			userVo.setAddr(addr);
			userVo.setDepid(Integer.parseInt(depid));
			userVo.setDegreeid(Integer.parseInt(degreeid));
			userVo.setEmail(email);
			userVo.setJobname(jobname);
			userVo.setMobile(mobile);
			userVo.setManagerType(Integer.valueOf(managerType));
			userVo.setQqcode(qqcode);
			userVo.setWeixin(weixin);
			userVo.setCardno(cardno);
			userVo.setBankName(bankName);
			userVo.setBankCardNo(bankCardNo);
			if(!joinDate.equals("")){
				userVo.setJoinDate(joinDate);
			}
			if(!workDate.equals("")){
				userVo.setWorkDate(workDate);
			}
			if(!levelDate.equals("")){
				userVo.setLevelDate(levelDate);
			}
			userVo.setBaseSalary(baseSalary);
			userVo.setDegreeSalary(degreeSalary);
			userVo.setRemark(remark);
			userVo.setStatus(1);//1有效，0：无效
			
			//加密处理
			userVo.setPassword(AesUtils.encryptStr(pwd, AesUtils.SECRETKEY));
			userDAO.saveUser(userVo);
			list(request, response);
		}else if(action.equals("update")){
			System.out.println(action);
			String userid = request.getParameter("userid");
			UsersVo user = userDAO.finduser(userid);
			List<DepVo> depAll = userDAO.findepAll();
			List<degreesVo> degreesAll = userDAO.findegreesAll();
			request.setAttribute("degreesAll", degreesAll);
			request.setAttribute("user", user);
			request.setAttribute("depAll", depAll);
			request.getRequestDispatcher("main/user/userUpdate.jsp").forward(request, response);
		}else if(action.equals("UpdateSave")){
			
			String userName = request.getParameter("username");
			String depid = request.getParameter("depid");
			
			//职务管连岗位表
			String degreeid = request.getParameter("jobname");
			String jobname = userDAO.finddegreeName(Integer.parseInt(degreeid));
			
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String qqcode = request.getParameter("qqcode");
			String managerType = request.getParameter("managerType");
			String weixin = request.getParameter("weixin");
			String cardno = request.getParameter("cardno");
			String bankName = request.getParameter("bankName");
			String bankCardNo = request.getParameter("bankCardNo");
			String joinDate = request.getParameter("joinDate");
			String workDate = request.getParameter("workDate");
			String levelDate = request.getParameter("levelDate");
			String baseSalary = request.getParameter("baseSalary");
			if(baseSalary.equals("")){
				baseSalary="0";
			}
			
			String degreeSalary = request.getParameter("degreeSalary");
			if(degreeSalary.equals("")){
				degreeSalary="0";
			}
			String addr = request.getParameter("addr");
			String remark = request.getParameter("remark");

			UsersVo u = new UsersVo();
			String userid = request.getParameter("userid");
			u.setUserid(Integer.parseInt(userid));
			u.setUsername(userName);
			u.setAddr(addr);
			u.setDepid(Integer.parseInt(depid));
			u.setDegreeid(Integer.parseInt(degreeid));
			u.setEmail(email);
			u.setJobname(jobname);
			u.setManagerType(Integer.valueOf(managerType));
			u.setMobile(mobile);
			//加密处理
//			u.setPassword(AesUtils.encryptStr(u.getPassword(), AesUtils.SECRETKEY));
			u.setQqcode(qqcode);

			u.setWeixin(weixin);
			u.setCardno(cardno);
			u.setBankName(bankName);
			u.setBankCardNo(bankCardNo);
			if(!joinDate.equals("")){
				u.setJoinDate(joinDate);
			}
			if(!workDate.equals("")){
				u.setWorkDate(workDate);
			}
			if(!levelDate.equals("")){
				u.setLevelDate(levelDate);
			}
			u.setBaseSalary(baseSalary);
			u.setDegreeSalary(degreeSalary);
			u.setRemark(remark);
//			u.setStatus(1);//1有效，0：无效
			userDAO.updateUser(u);
			list(request,response);
		//更改状态	
		}else if(action.equals("status")){
			String userid = request.getParameter("userid");
			String status = request.getParameter("status");
			UsersVo u = new UsersVo();
			u.setUserid(Integer.parseInt(userid));
			u.setStatus(Integer.parseInt(status));
			userDAO.updateUserStatus(u);
			list(request,response);
		}
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
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
		int cnt =userDAO.findcount("users");
		//自动计算总页数
		pager.setTotalRows(cnt);
		
		
		//查询列表
		List<UsersVo> userList = userDAO.userfindlist(pager.getStartRow(), pager.getPageRow());
		//定义request变量，在jsp页面中使用
		request.setAttribute("userList", userList);
		//分页参数
		request.setAttribute("pager", pager);
		//转向到列表页面,这个语句相当于jsp:forward动作元素
		request.getRequestDispatcher("main/user/userList.jsp").forward(request, response);
	}
}
