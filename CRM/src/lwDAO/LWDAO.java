package lwDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ContextUtils;

import bean.ContactVo;
import bean.CustomerInfoVo;
import bean.DepVo;
import bean.OrdersVo;
import bean.PaidTypeVo;
import bean.ProductVo;
import bean.UsersVo;
import bean.degreesVo;
import bean.financeVo;

import db.DBConn;

public class LWDAO {
	
	//登录
	public int login(String username,String password){
		int s = -2;
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users where username="+username;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				s = -1;
				sql = "select * from users where password="+password;
				rs = stmt.executeQuery(sql);
				if(rs.next()){
					s = rs.getInt("userid");
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	//用户------------------------------------------------------------------
	//查询列表
	public List<UsersVo> userfindlist(int startRow,int pageSize){
		List<UsersVo> list = new ArrayList<UsersVo>();
		Connection conn = DBConn.openDB();
		//计算起始位置
		try {
			Statement stmt = conn.createStatement();
			String sql = "select top "+pageSize+" * from users where userid not in(";
			sql += "select top "+startRow+" userid from users)";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UsersVo user = new UsersVo();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username").trim());
				user.setPassword(rs.getString("password").trim());
				user.setDepid(rs.getInt("depid"));
				user.setJobname(rs.getString("jobname").trim());
				user.setManagerType(rs.getInt("ManagerType"));
				user.setMobile(rs.getString("mobile").trim());
				user.setEmail(rs.getString("email").trim());
				user.setQqcode(rs.getString("qqcode").trim());
				user.setWeixin(rs.getString("Weixin").trim());
				user.setCardno(rs.getString("Cardno").trim());
				user.setBankName(rs.getString("BankName").trim());
				user.setBankCardNo(rs.getString("BankCardNo").trim());
				Date date = rs.getDate("JoinDate");
				if(date ==null)
					user.setJoinDate("");
				else{
					user.setJoinDate(ContextUtils.dateToStrShort(date));
				}
				
				date = rs.getDate("WorkDate");
				if(date ==null)
					user.setWorkDate("");
				else{
					user.setWorkDate(ContextUtils.dateToStrShort(date));
				}
				date = rs.getDate("LevelDate");
				if(date ==null)
					user.setLevelDate("");
				else{
					user.setLevelDate(ContextUtils.dateToStrShort(date));
				}
				user.setBaseSalary(rs.getString("BaseSalary"));
				user.setDegreeSalary(rs.getString("DegreeSalary"));
				user.setAddr(rs.getString("addr").trim());
				user.setStatus(rs.getInt("status"));
				user.setRemark(rs.getString("remark").trim());
				String depname = finddepName(rs.getInt("depid"));
				user.setDepName(depname);
				list.add(user);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//通过depid查询部门名称
	public String finddepName(int depid){
		String depname = "";
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select depname from dep where depid="+depid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				depname = rs.getString("depname");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return depname;
	}
	
	//通过degreeid查询岗位名称
	public String finddegreeName(int degreeid){
		String degreename = "";
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select degreename from degrees where degreeid="+degreeid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				degreename = rs.getString("degreename");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return degreename;
	}
	
	//查询记录总数
	public int findcount(String table){
		int s = 0;
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from "+table;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				s++;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//新增用户的部门下拉框内容
	public List<DepVo> findepAll(){
		List<DepVo> list = new ArrayList<DepVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from dep";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				DepVo s = new DepVo();
				s.setDepid(rs.getInt("depid"));
				s.setDepname(rs.getString("depname"));
				s.setChairman(rs.getString("chairman"));
				s.setDescription(rs.getString("description"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//新增用户的职位下拉框内容
	public List<degreesVo> findegreesAll(){
		List<degreesVo> list = new ArrayList<degreesVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from degrees";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				degreesVo s = new degreesVo();
				s.setDegreeid(rs.getInt("degreeid"));
				s.setDegreename(rs.getString("degreename"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询用户名称是否同名
	public Boolean checkUser(String username){
		Boolean s = true;
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users where username='"+username+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				s = false;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//用户新增
	public void saveUser(UsersVo u){
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into users(username,password,depid,degreeid,jobname,managerType,mobile,email,";
			sql += " qqcode,weixin,cardno,bankName,bankCardNo,joinDate,workDate,levelDate,";
			sql += " baseSalary,degreeSalary,addr,status,remark) values('";
			sql += u.getUsername() +"','";
			sql += u.getPassword() +"',";
			sql += u.getDepid() +",";
			sql += u.getDegreeid() + ",'";
			sql += u.getJobname() +"',";
			sql += u.getManagerType() +",'";
			sql += u.getMobile() +"','";
			sql += u.getEmail() +"','";
			sql += u.getQqcode() +"','";
			sql += u.getWeixin() +"','";
			sql += u.getCardno() +"','";
			sql += u.getBankName() +"','";
			sql += u.getBankCardNo() +"',";
			if(u.getJoinDate()==null || u.getJoinDate().equals("")){
				sql += "null,";
			}else{
				sql += "'"+u.getJoinDate() +"',";
			}
			if(u.getWorkDate()==null || u.getWorkDate().equals("")){
				sql += "null,";
			}else{
				sql += u.getWorkDate() +"',";
			}
			if(u.getLevelDate()==null || u.getLevelDate().equals("")){
				sql += "null,";
			}else{
				sql += u.getLevelDate() +"',";
			}
			sql += u.getBaseSalary() +",";
			sql += u.getDegreeSalary() +",'";
			sql += u.getAddr() +"',1,'";
			sql += u.getRemark()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	//通过用户id查询一个用户的信息
	public UsersVo finduser(String userid){
		UsersVo user = new UsersVo();
		Connection conn = DBConn.openDB();
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from users where userid="+userid;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("userName").trim());
				user.setPassword(rs.getString("password").trim());
				user.setDepid(rs.getInt("depid"));
				String depname = finddepName(rs.getInt("depid"));
				user.setDepName(depname);
				user.setDegreeid(rs.getInt("degreeid"));
				user.setJobname(rs.getString("jobname").trim());
				user.setManagerType(rs.getInt("ManagerType"));
				user.setMobile(rs.getString("mobile").trim());
				user.setEmail(rs.getString("email").trim());
				user.setQqcode(rs.getString("qqcode").trim());
				user.setWeixin(rs.getString("Weixin").trim());
				user.setCardno(rs.getString("Cardno").trim());
				user.setBankName(rs.getString("BankName").trim());
				user.setBankCardNo(rs.getString("BankCardNo").trim());
				
				Date date = rs.getDate("JoinDate");
				if(date ==null)
					user.setJoinDate("");
				else{
					user.setJoinDate(ContextUtils.dateToStrShort(date));
				}
				
				date = rs.getDate("WorkDate");
				if(date ==null)
					user.setWorkDate("");
				else{
					user.setWorkDate(ContextUtils.dateToStrShort(date));
				}
				date = rs.getDate("LevelDate");
				if(date ==null)
					user.setLevelDate("");
				else{
					user.setLevelDate(ContextUtils.dateToStrShort(date));
				}
				user.setBaseSalary(rs.getString("BaseSalary"));
				user.setDegreeSalary(rs.getString("DegreeSalary"));
				user.setAddr(rs.getString("addr").trim());
				user.setStatus(rs.getInt("status"));
				user.setRemark(rs.getString("remark").trim());
			}else{
				user=null;
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	//修改用户资料
	public void updateUser(UsersVo u){
		Connection conn = DBConn.openDB();
		try{
			Statement stmt = conn.createStatement();
			String sql = "update users set depid=";
			sql += u.getDepid() +",jobname='";
			sql += u.getJobname() +"',managerType=";
			sql += u.getManagerType() +",mobile='";
			sql += u.getMobile() +"',email='";
			sql += u.getEmail() +"',qqcode='";
			sql += u.getQqcode() +"',weixin='";
			sql += u.getWeixin() +"',cardno='";
			sql += u.getCardno() +"',bankName='";
			sql += u.getBankName() +"',bankCardNo='"+u.getBankCardNo();
			if(u.getJoinDate()==null || u.getJoinDate().equals("")){
				sql += "',joinDate=null,";
			}else{
				sql += "',joinDate='"+u.getJoinDate()+"',";
			}
			if(u.getWorkDate()==null || u.getWorkDate().equals("")){
				sql += "workDate=null,";
			}else{
				sql += "workDate='"+u.getJoinDate()+"',";
			}
			if(u.getLevelDate()==null || u.getLevelDate().equals("")){
				sql += "levelDate=null,baseSalary='";
			}else{
				sql += "levelDate='"+u.getLevelDate()+"',baseSalary='";
			}
			sql += u.getBaseSalary() +"',degreeSalary='";
			sql += u.getDegreeSalary() +"',addr='";
			sql += u.getAddr() +"',remark='";
			sql += u.getRemark()+"' where userid="+u.getUserid();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//禁用/启用状态
	public void updateUserStatus(UsersVo u){
		Connection conn = DBConn.openDB();
		try{
			Statement stmt = conn.createStatement();
			String sql = "update users set status="+ u.getStatus()+" where userid="+u.getUserid();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//客户联系表----------------------------------------------------------------------
	
	//查询列表
	public List<ContactVo> Contactfindlist(int startRow,int pageSize){
		List<ContactVo> list = new ArrayList<ContactVo>();
		Connection conn = DBConn.openDB();
		//计算起始位置
		try {
			Statement stmt = conn.createStatement();
			String sql = "select top "+pageSize+" * from Contact where Contactid not in(";
			sql += "select top "+startRow+" Contactid from Contact)";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ContactVo con = new ContactVo();
				con.setContactId(rs.getInt("contactId"));
				con.setTalkDate(rs.getString("talkDate"));
				con.setCustContact(rs.getString("custContact"));
				con.setPhone1(rs.getString("phone1"));
				con.setPhone2(rs.getString("phone2"));
				con.setCustid(rs.getInt("custid"));
				con.setCustname(findcustname(rs.getInt("custid")));
				con.setQqCode(rs.getInt("qqCode"));
				con.setEmail(rs.getString("email"));
				con.setWeixin(rs.getString("weixin"));
				con.setUserid(rs.getInt("userid"));
				con.setUsername(findusername(rs.getInt("userid")));
				con.setBirthday(rs.getString("birthday"));
				con.setHobbit(rs.getString("hobbit"));
				con.setJobName(rs.getString("jobName"));
				con.setRemark(rs.getString("remark"));
				list.add(con);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//根据userid查询username显示在Contact列表上
	public String findusername(int userid){
		String name = "";
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users where userid="+userid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				name = rs.getString("username");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	//根据custid查询custname显示在Contact列表上
	public String findcustname(int custid){
		String name = "";
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from CustomerInfo where custid="+custid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				name = rs.getString("custname");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	//查询用户下拉框
	public List<UsersVo> finduserAll(){
		List<UsersVo> list = new ArrayList<UsersVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UsersVo v = new UsersVo();
				v.setUserid(rs.getInt("userid"));
				v.setUsername(rs.getString("username"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询客户下拉框
	public List<CustomerInfoVo> findcustAll(){
		List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from customerInfo";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CustomerInfoVo v = new CustomerInfoVo();
				v.setCustId(rs.getInt("custid"));
				v.setCustname(rs.getString("custname"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//新增
	public void addcontact(ContactVo v){
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into contact values('";
			sql += v.getTalkDate() + "','";
			sql += v.getCustContact() + "','";
			sql += v.getPhone1() + "','";
			sql += v.getPhone2() + "',";
			sql += v.getCustid() + ",";
			sql += v.getQqCode() + ",'";
			sql += v.getEmail() + "','";
			sql += v.getWeixin() + "',";
			sql += v.getUserid() + ",'";
			sql += v.getBirthday() + "','";
			sql += v.getHobbit() + "','";
			sql += v.getJobName() + "','";
			sql += v.getRemark() + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//通过id查询Contact表
	public ContactVo contUpdatefind(String contid){
		ContactVo v = new ContactVo();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from Contact where contactId="+contid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				v.setContactId(rs.getInt("contactId"));
				v.setTalkDate(rs.getString("talkDate"));
				v.setCustContact(rs.getString("custContact"));
				v.setPhone1(rs.getString("phone1"));
				v.setPhone2(rs.getString("phone2"));
				v.setCustid(rs.getInt("custid"));
				v.setQqCode(rs.getInt("qqCode"));
				v.setEmail(rs.getString("email"));
				v.setWeixin(rs.getString("weixin"));
				v.setUserid(rs.getInt("userid"));
				v.setBirthday(rs.getString("birthday"));
				v.setHobbit(rs.getString("hobbit"));
				v.setJobName(rs.getString("jobName"));
				v.setRemark(rs.getString("remark"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//修改
	public void updateSaveContact(ContactVo v,String contactId){
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "update Contact set TalkDate='";
			sql += v.getTalkDate() + "',CustContact='";
			sql += v.getCustContact() + "',Phone1='";
			sql += v.getPhone1() + "',Phone2='";
			sql += v.getPhone2() + "',Custid=";
			sql += v.getCustid() + ",QqCode=";
			sql += v.getQqCode() + ",Email='";
			sql += v.getEmail() + "',Weixin='";
			sql += v.getWeixin() + "',Userid=";
			sql += v.getUserid() + ",Birthday='";
			sql += v.getBirthday() + "',Hobbit='";
			sql += v.getHobbit() + "',JobName='";
			sql += v.getJobName() + "',Remark='";
			sql += v.getRemark() + "' where contactId="+contactId+"";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//收款管理------------------------------------------------------------
	//分页查询
	public List<financeVo> financeList(int startRow,int pageSize){
		Connection conn = DBConn.openDB();
		List<financeVo> list = new ArrayList<financeVo>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select top "+pageSize+" * from finance where financeid not in(";
			sql += "select top "+startRow+" financeid from finance)";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				financeVo v = new financeVo();
				v.setFinanceId(rs.getInt("financeid"));
				v.setOrderId(rs.getString("orderId"));
				v.setProdid(rs.getInt("prodid"));
				v.setPaidtypeid(rs.getString("paidtypeid"));
				v.setRemainMoney(rs.getString("remainMoney"));
				v.setPaidMoney(rs.getString("paidMoney"));
				v.setOrderMoney(rs.getString("orderMoney"));
				v.setPaidPerson(rs.getString("paidPerson"));
				v.setInbank(rs.getString("inbank"));
				v.setBankAccount(rs.getString("bankAccount"));
				v.setOutbank(rs.getString("outbank"));
				v.setWarrant(rs.getString("warrant"));
				v.setPaidTime(rs.getString("paidTime"));
				v.setPaidinTime(rs.getString("paidinTime"));
				v.setInvalid(rs.getString("invalid"));
				v.setUsername(rs.getString("username"));
				v.setOprtime(rs.getString("oprtime"));
				v.setOprType(rs.getString("oprType"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询订单下拉框
	public List<OrdersVo> findOrdersAll(){
		List<OrdersVo> list = new ArrayList<OrdersVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from Orders";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				OrdersVo v = new OrdersVo();
				v.setOrderId(rs.getString("orderId"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询产品下拉框
	public List<ProductVo> findProductAll(){
		List<ProductVo> list = new ArrayList<ProductVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from Product";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ProductVo v = new ProductVo();
				v.setProdid(rs.getString("Prodid"));
				v.setProdname(rs.getString("Prodname"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询产品下拉框
	public List<PaidTypeVo> findPaidTypeAll(){
		List<PaidTypeVo> list = new ArrayList<PaidTypeVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from PaidType";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				PaidTypeVo v = new PaidTypeVo();
				v.setPaidtypeid(rs.getInt("paidtypeid"));
				v.setPaidtypename(rs.getString("paidtypename"));
				list.add(v);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//新增
	public void addfinance(financeVo v){
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into finance values('";
			sql += v.getOrderId() + "',";
			sql += v.getProdid() + ",'";
			sql += v.getPaidtypeid() + "','";
			sql += v.getRemainMoney() + "','";
			sql += v.getPaidMoney() + "','";
			sql += v.getOrderMoney() + "','";
			sql += v.getPaidPerson() + "','";
			sql += v.getInbank() + "','";
			sql += v.getBankAccount() + "','";
			sql += v.getOutbank() + "','";
			sql += v.getWarrant() + "','";
			sql += v.getPaidTime() + "','";
			sql += v.getPaidinTime() + "','";
			sql += v.getInvalid() + "','";
			sql += v.getUsername() + "',getdate(),'";
			sql += v.getOprType() + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//通过id查询收款管理的信息
	public financeVo financeUpdatefind(String financeid){
		financeVo v = new financeVo();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "select * from finance where financeid="+financeid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				v.setFinanceId(rs.getInt("financeid"));
				v.setOrderId(rs.getString("orderId"));
				v.setProdid(rs.getInt("prodid"));
				v.setPaidtypeid(rs.getString("paidtypeid"));
				v.setRemainMoney(rs.getString("remainMoney"));
				v.setPaidMoney(rs.getString("paidMoney"));
				v.setOrderMoney(rs.getString("orderMoney"));
				v.setPaidPerson(rs.getString("paidPerson"));
				v.setInbank(rs.getString("inbank"));
				v.setBankAccount(rs.getString("bankAccount"));
				v.setOutbank(rs.getString("outbank"));
				v.setWarrant(rs.getString("warrant"));
				v.setPaidTime(rs.getString("paidTime"));
				v.setPaidinTime(rs.getString("paidinTime"));
				v.setInvalid(rs.getString("invalid"));
				v.setUsername(rs.getString("username"));
				v.setOprtime(rs.getString("oprtime"));
				v.setOprType(rs.getString("oprType"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public void updatefinance(financeVo v,String financeid){
		Connection conn = DBConn.openDB();
		try {
			Statement stmt = conn.createStatement();
			String sql = "update finance set OrderId='";
			sql += v.getOrderId() + "',Prodid=";
			sql += v.getProdid() + ",Paidtypeid='";
			sql += v.getPaidtypeid() + "',RemainMoney='";
			sql += v.getRemainMoney() + "',PaidMoney='";
			sql += v.getPaidMoney() + "',OrderMoney='";
			sql += v.getOrderMoney() + "',PaidPerson='";
			sql += v.getPaidPerson() + "',Inbank='";
			sql += v.getInbank() + "',BankAccount='";
			sql += v.getBankAccount() + "',Outbank='";
			sql += v.getOutbank() + "',Warrant='";
			sql += v.getWarrant() + "',PaidTime='";
			sql += v.getPaidTime() + "',PaidinTime='";
			sql += v.getPaidinTime() + "',Invalid='";
			sql += v.getInvalid() + "',Username='";
			sql += v.getUsername() + "',oprtime=getdate(),OprType='";
			sql += v.getOprType() + "' where financeid="+financeid;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
