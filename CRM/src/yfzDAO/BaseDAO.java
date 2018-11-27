package yfzDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.CustomerInfoVo;
import bean.DepVo;
import bean.OrdersVo;
import bean.PaidTypeVo;
import bean.ProductVo;
import bean.UnitVo;
import bean.UsersVo;
import bean.jobRecordVo;

import db.DBConn;



public class BaseDAO {
	
	//根据部门删除部门资料
	public void del(String depid){
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="delete Dep where depid="+depid;
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//新增部门资料	
	public void save(DepVo d){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into Dep values('";
			sql+=d.getDepname()+"','";
			sql+=d.getChairman()+"','";
			sql+=d.getDescription()+"')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//修改部门资料
	public void update(DepVo d){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="update dep set depname='";
			sql+=d.getDepname()+"',chairman='";
			sql+=d.getChairman()+"',description='";
			sql+=d.getDescription()+"' where depid="+d.getDepid();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//分页查询
	public List<DepVo> findByPage(int startRow,int pageSize){
		List<DepVo> list = new ArrayList<DepVo>();
		Connection conn = DBConn.openDB();
	
		try{
			Statement stmt = conn.createStatement();
			String sql="select top "+pageSize+" * from Dep where depid not in(";
			sql+="select top "+startRow+" depid from Dep";
			sql+=")";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				DepVo s = new DepVo();
				s.setDepid(rs.getInt("depid"));
				s.setDepname(rs.getString("depname").trim());
				s.setChairman(rs.getString("chairman").trim());
				s.setDescription(rs.getString("description").trim());
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//统计记录总数
	public int findCount(String table){
		int cnt=0;
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String  sql="select count(*) cnt from "+table;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
					cnt=rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public DepVo findByStudId(String depid){
		
		Connection conn=DBConn.openDB();
		DepVo d=new DepVo();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from dep where depid="+depid;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				d.setDepid(rs.getInt("depid"));
				d.setDepname(rs.getString("depname"));
				d.setChairman(rs.getString("chairman"));
				d.setDescription(rs.getString("description"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
		
	}
	
	/*
	 * 单位管理
	 */
	
	//根据单位删除单位资料
	public void delunit(String unitId){
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="delete unit where unitId="+unitId;
			stmt.executeUpdate(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//新增单位
	public void saveUnit(String unitName){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into unit values('";
			sql += unitName+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UnitVo findByUnitId(String unitId){
		Connection conn=DBConn.openDB();
		System.out.println(unitId);
		UnitVo unit=new UnitVo();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from unit where unitId="+unitId;
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println(sql);
			if(rs.next()){
				unit.setUnitId(rs.getInt("unitId"));
				unit.setUnitName(rs.getString("unitName"));
			}
				rs.close();
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unit;
	}
	//修改单位
	public void	updateUnit(int unitId,String unitName){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="update unit set unitName='";
			sql +=unitName+"' where unitId="+unitId;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//新增的单位在下拉框中显示
	public List<UnitVo> findUnitAll(){
		List<UnitVo> list=new ArrayList<UnitVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from unit";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				UnitVo u=new UnitVo();
				u.setUnitId(rs.getInt("unitId"));
				u.setUnitName(rs.getString("unitName"));
				list.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	//分页查询
	public List<UnitVo> findUnitByPage(int startRow,int pageSize){
		List<UnitVo> list=new ArrayList<UnitVo>();
		Connection conn = DBConn.openDB();
	
		try {
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from unit where unitId not in(";
			sql+="select top "+startRow+" unitId from unit";
			sql +=")";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UnitVo u=new UnitVo();
				u.setUnitId(rs.getInt("unitId"));
				u.setUnitName(rs.getString("unitName"));
				list.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * 支付方式管理
	 */
	
	//删除记录
	public void delPaid(String paidtypeid){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="delete PaidType where paidtypeid="+paidtypeid;
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//新增记录
	public void savepaidtype(String paidtypename){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into paidtype values('";
			sql += paidtypename+"')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//修改记录
	public void updatePaidtype(int paidtypeid,String paidtypename){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="update paidtype set paidtypename='";
			sql+=paidtypename+"' where paidtypeid="+paidtypeid;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//新增的记录在下拉框中显示
	public List<PaidTypeVo> findPaidtypeAll(){
		List<PaidTypeVo> list=new ArrayList<PaidTypeVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from paidtype";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				PaidTypeVo p=new PaidTypeVo();
				p.setPaidtypeid(rs.getInt("paidtypeid"));
				p.setPaidtypename(rs.getString("paidtypename"));
				list.add(p);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} 
	public List<PaidTypeVo> findPaidTypeByPage(int StartRow,int pageSize){
		List<PaidTypeVo> list=new ArrayList<PaidTypeVo>();
		Connection conn = DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from paidtype where paidtypeid not in(";
			sql +="select top "+StartRow+" paidtypeid from paidtype";
			sql +=")";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				PaidTypeVo p=new PaidTypeVo();
				p.setPaidtypeid(rs.getInt("paidtypeid"));
				p.setPaidtypename(rs.getString("paidtypename"));
				list.add(p);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public PaidTypeVo findBypaidtypeid(String paidtypeid){
		
		Connection conn=DBConn.openDB();
		PaidTypeVo paid=new PaidTypeVo();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from paidtype where paidtypeid="+paidtypeid;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				paid.setPaidtypeid(rs.getInt("paidtypeid"));
				paid.setPaidtypename(rs.getString("Paidtypename"));
			}
				rs.close();
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paid;
		
	}
	
	/*
	 * 客户资料管理
	 */
	
	//删除客户
	public void delCustomer(String custId){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="del CustomerInfo where custId="+custId;
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//新增客户
	public void saveCustomer(CustomerInfoVo c){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into CustomerInfo values('";
			sql +=c.getCustname()+"','";
			sql +=c.getCusttype()+"','";
			sql +=c.getBankAccount()+"','";
			sql +=c.getBankName()+"','";
			sql +=c.getContact()+"','";
			sql +=c.getPhone()+"','";
			sql +=c.getTicketName()+"','";
			sql +=c.getTicketAddr()+"','";
			sql +=c.getTicketTel()+"','";
			sql +=c.getTaxNo()+"','";
			sql +=c.getCustState()+"','";
			sql +=c.getUsername()+"','";
			sql +=c.getSource()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CustomerInfoVo> findCustomerByPage(int startRow,int pageSize){
		List<CustomerInfoVo> list=new ArrayList<CustomerInfoVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from CustomerInfo where custId not in(";
			sql+="select top "+startRow+" custId from CustomerInfo";
			sql+=")";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				CustomerInfoVo c=new CustomerInfoVo();
				c.setCustId(rs.getInt("custId"));
				c.setCustname(rs.getString("custname"));
				c.setCusttype(rs.getString("custtype"));
				c.setBankAccount(rs.getString("bankAccount"));
				c.setBankName(rs.getString("bankName"));
				c.setContact(rs.getString("Contact"));
				c.setPhone(rs.getString("Phone"));
				c.setTicketName(rs.getString("TicketName"));
				c.setTicketAddr(rs.getString("TicketAddr"));
				c.setTicketTel(rs.getString("TicketTel"));
				c.setTaxNo(rs.getString("TaxNo"));
				c.setCustState(rs.getString("custState"));
				c.setUsername(rs.getString("username"));
				c.setSource(rs.getString("source"));
				list.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//根据id查询
	public CustomerInfoVo findByCustomerId(String custId){
		Connection conn=DBConn.openDB();
		CustomerInfoVo c=new CustomerInfoVo();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from CustomerInfo where custId="+custId;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				c.setCustId(rs.getInt("custId"));
				c.setCustname(rs.getString("custname"));
				c.setCusttype(rs.getString("custtype"));
				c.setBankAccount(rs.getString("bankAccount"));
				c.setBankName(rs.getString("bankName"));
				c.setContact(rs.getString("contact"));
				c.setPhone(rs.getString("Phone"));
				c.setTicketName(rs.getString("ticketName"));
				c.setTicketAddr(rs.getString("ticketAddr"));
				c.setTicketTel(rs.getString("ticketTel"));
				c.setTaxNo(rs.getString("taxNo"));
				c.setCustState(rs.getString("custState"));
				c.setUsername(rs.getString("username"));
				c.setSource(rs.getString("source"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	//客户资料修改
	public void updataCustomer(CustomerInfoVo c){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="update CustomerInfo set custname='";
			sql +=c.getCustname()+"',custtype='";
			sql +=c.getCusttype()+"',bankAccount='";
			sql +=c.getBankAccount()+"',bankName='";
			sql +=c.getBankName()+"',contact='";
			sql +=c.getContact()+"',phone='";
			sql +=c.getPhone()+"',ticketName='";
			sql +=c.getTicketName()+"',ticketAddr='";
			sql +=c.getTicketAddr()+"',ticketTel='";
			sql +=c.getTicketTel()+"',taxNo='";
			sql +=c.getTaxNo()+"',custState='";
			sql +=c.getCustState()+"',username='";
			sql +=c.getUsername()+"',source='";
			sql +=c.getSource()+"' where custId="+c.getCustId();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//订单管理0------------------------------
	public void saveOrders(OrdersVo o){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql ="insert into Orders values(";
			sql +=o.getOrderId()+",";
			sql +=o.getCustid()+",";
			sql +=o.getUserid()+",'";
			sql +=o.getOrderType()+"','";
			sql +=o.getOrderStatus()+"','";
			sql +=o.getProcess()+"','";
			sql +=o.getTotalMoney()+"','";
			sql +=o.getOprtime()+"','";
			sql +=o.getOperator()+"','";
			sql +=o.getRemark()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<OrdersVo> findOrdersBytPage(int startRow,int pageSize){
		List<OrdersVo> list = new ArrayList<OrdersVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" p .*,c.custname,u.username  from Orders p inner join CustomerInfo c on p.custId=c.custId inner join Users u on p.userid=u.userid where orderId  not in(";
			sql+="select top "+startRow+" orderId from Orders";
			sql+=")";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				OrdersVo o=new OrdersVo();
				o.setOrderId(rs.getString("orderId"));
				o.setCustname(findcustname(rs.getInt("custid")));
				o.setUserid(rs.getInt("userid"));
				o.setUsername(findusername(rs.getInt("userid")));
				o.setOrderType(rs.getString("orderType"));
				o.setOrderStatus(rs.getString("orderStatus"));
				o.setProcess(rs.getString("process"));
				o.setTotalMoney(rs.getString("totalMoney"));
				o.setOprtime(rs.getString("oprtime"));
				o.setOperator(rs.getString("operator"));
				o.setRemark(rs.getString("remark"));
				list.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public OrdersVo findByOrderId(String orderId){
		Connection conn=DBConn.openDB();
		OrdersVo o=new OrdersVo();
		try{
			Statement stmt=conn.createStatement();
			String sql ="select * from Orders where orderId="+orderId;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				o.setOrderId(rs.getString("orderId"));
				o.setCustid(rs.getInt("custid"));
				o.setCustname(findcustname(rs.getInt("custid")));
				o.setUserid(rs.getInt("userid"));
				o.setUsername(findusername(rs.getInt("userid")));
				o.setOrderType(rs.getString("orderType"));
				o.setOrderStatus(rs.getString("orderStatus"));
				o.setProcess(rs.getString("process"));
				o.setTotalMoney(rs.getString("totalMoney"));
				o.setOprtime(rs.getString("oprtime"));
				o.setOperator(rs.getString("operator"));
				o.setRemark(rs.getString("remark"));
			}
			rs.close();
			stmt.cancel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	//根据custid查询
	public String findcustname(int custId){
		Connection conn=DBConn.openDB();
		String s = "";
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from CustomerInfo where custId="+custId;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				s = rs.getString("custname");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//根据userid查询
	public String findusername(int userid){
		Connection conn=DBConn.openDB();
		String s = "";
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from users where userId="+userid;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				s = rs.getString("username");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	//修改订单
	public void updateOrder(OrdersVo o){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql ="update orders set custid=";
			sql +=o.getCustid()+",userid=";
			sql +=o.getUserid()+",orderType='";
			sql +=o.getOrderType()+"',orderStatus='";
			sql +=o.getOrderStatus()+"',process='";
			sql +=o.getProcess()+"',totalMoney='";
			sql +=o.getTotalMoney()+"',oprtime='";
			sql +=o.getOprtime()+"',operator='";
			sql +=o.getOperator()+"',remark='";
			sql +=o.getRemark()+"' where orderId="+o.getOrderId();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//显示下拉框的用户信息
	public List<UsersVo> findUsersAll(){
		
		List<UsersVo> list=new ArrayList<UsersVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from Users";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				UsersVo u=new UsersVo();
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setDepid(rs.getInt("depid"));
				u.setDegreeid(rs.getInt("degreeid"));
				u.setJobname(rs.getString("jobname"));
				u.setManagerType(rs.getInt("managerType"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setQqcode(rs.getString("qqcode"));
				u.setWeixin(rs.getString("weixin"));
				u.setCardno(rs.getString("cardno"));
				u.setBankName(rs.getString("bankName"));
				u.setBankCardNo(rs.getString("bankCardNo"));
				u.setJoinDate(rs.getString("joinDate"));
				u.setWorkDate(rs.getString("workDate"));
				u.setLevelDate(rs.getString("levelDate"));
				u.setBaseSalary(rs.getString("baseSalary"));
				u.setDegreeSalary(rs.getString("degreeSalary"));
				u.setAddr(rs.getString("addr"));
				u.setStatus(rs.getInt("status"));
				u.setRemark(rs.getString("remark"));
				list.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//显示下拉框的客户信息
	public List<CustomerInfoVo> findCustomerAll(){
		List<CustomerInfoVo> list=new ArrayList<CustomerInfoVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from CustomerInfo";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				CustomerInfoVo c=new CustomerInfoVo();
				c.setCustId(rs.getInt("custId"));
				c.setCustname(rs.getString("custname"));
				c.setCusttype(rs.getString("custtype"));
				c.setBankAccount(rs.getString("bankAccount"));
				c.setBankName(rs.getString("bankName"));
				c.setContact(rs.getString("contact"));
				c.setPhone(rs.getString("phone"));
				c.setTicketName(rs.getString("ticketName"));
				c.setTicketAddr(rs.getString("ticketAddr"));
				c.setTicketTel(rs.getString("ticketTel"));
				c.setTaxNo(rs.getString("taxNo"));
				c.setCustState(rs.getString("custState"));
				c.setUsername(rs.getString("username"));
				c.setSource(rs.getString("source"));
				list.add(c);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
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
	
	//派工单------------------------------------------------
	//查询列表
	public List<jobRecordVo> findJobrecordAll(int startRow,int pageSize){
		List<jobRecordVo> list=new ArrayList<jobRecordVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from jobRecord where jobid not in(";
			sql+="select top "+startRow+" jobId from jobRecord";
			sql+=")";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				jobRecordVo j=new jobRecordVo();
				j.setJobId(rs.getInt("jobId"));
				j.setOrderId(rs.getString("orderId"));
				j.setJobDate(rs.getString("jobDate"));
				j.setProdName(findprodname(rs.getInt("prodName")));
				j.setCustid(findcustname(rs.getInt("custid")));
				j.setJobContent(rs.getString("jobContent"));
				j.setCallback(rs.getString("callback"));
				j.setUserid(findusername(rs.getInt("userid")));
				j.setCustEval(rs.getString("custEval"));
				j.setCustSign(rs.getString("custSign"));
				j.setStartTime(rs.getString("startTime"));
				j.setEndTime(rs.getString("endTime"));
				j.setWorkDay(rs.getString("workDay"));
				j.setBusMoney(rs.getString("busMoney"));
				j.setAttachMoney(rs.getString("attachMoney"));
				list.add(j);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//根据custid查询
	public String findprodname(int prodId){
		Connection conn=DBConn.openDB();
		String s = "";
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from Product where prodId="+prodId;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				s = rs.getString("prodname");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	

	/*
	 * 派工单
	 * */
	//增加派工单
	public void saveJobrecord(jobRecordVo j){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into jobRecord values('";
			sql +=j.getOrderId()+"','";
			sql +=j.getJobDate()+"','";
			sql +=j.getProdName()+"',";
			sql +=j.getCustid()+",'";
			sql +=j.getJobContent()+"','";
			sql +=j.getCallback()+"','";
			sql +=j.getUserid()+"','";
			sql +=j.getCustEval()+"','";
			sql +=j.getCustSign()+"','";
			sql +=j.getStartTime()+"','";
			sql +=j.getEndTime()+"','";
			sql +=j.getWorkDay()+"','";
			sql +=j.getBusMoney()+"','";
			sql +=j.getAttachMoney()+"')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//修改派工单
	public void jobrecodeUpdate(jobRecordVo j){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="update jobRecord set orderId='";
			sql +=j.getOrderId()+"',jobDate='";
			sql +=j.getJobDate()+"',prodName='";
			sql +=j.getProdName()+"',custid=";
			sql +=j.getCustid()+",jobContent='";
			sql +=j.getJobContent()+"',callback='";
			sql +=j.getCallback()+"',userid='";
			sql +=j.getUserid()+"',custEval='";
			sql +=j.getCustEval()+"',custSign='";
			sql +=j.getCustSign()+"',startTime='";
			sql +=j.getStartTime()+"',endTime='";
			sql +=j.getEndTime()+"',workDay='";
			sql +=j.getWorkDay()+"',busMoney='";
			sql +=j.getBusMoney()+"',attachMoney='";
			sql +=j.getAttachMoney()+"' where jobId="+j.getJobId();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<OrdersVo> findOrdersAll(){
		List<OrdersVo> list=new ArrayList<OrdersVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from Orders";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				OrdersVo o=new OrdersVo();
				o.setOrderId(rs.getString("orderId"));
				o.setCustid(rs.getInt("custid"));
				o.setUserid(rs.getInt("userid"));
				o.setOrderType(rs.getString("orderType"));
				o.setOrderStatus(rs.getString("orderStatus"));
				o.setProcess(rs.getString("process"));
				o.setTotalMoney(rs.getString("totalMoney"));
				o.setOprtime(rs.getString("oprtime"));
				o.setOperator(rs.getString("operator"));
				o.setRemark(rs.getString("remark"));
				list.add(o);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public jobRecordVo findByJobId(String jobId){
		Connection conn=DBConn.openDB();
		jobRecordVo j=new jobRecordVo();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from jobRecord where jobId="+jobId;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				j.setJobId(rs.getInt("jobId"));
				j.setOrderId(rs.getString("orderId"));
				j.setJobDate(rs.getString("jobDate"));
				j.setProdName(rs.getString("prodName"));
				j.setCustid(rs.getString("custid"));
				j.setJobContent(rs.getString("jobContent"));
				j.setCallback(rs.getString("callback"));
				j.setUserid(rs.getString("userid"));
				j.setCustEval(rs.getString("custEval"));
				j.setCustSign(rs.getString("custSign"));
				j.setStartTime(rs.getString("startTime"));
				j.setEndTime(rs.getString("endTime"));
				j.setWorkDay(rs.getString("workDay"));
				j.setBusMoney(rs.getString("busMoney"));
				j.setAttachMoney(rs.getString("attachMoney"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return j;
	}
	
	public List<jobRecordVo> findJobrecordAll(){
		List<jobRecordVo> list=new ArrayList<jobRecordVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from jobRecord";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				jobRecordVo j=new jobRecordVo();
				j.setJobId(rs.getInt("jobId"));
				j.setOrderId(rs.getString("orderId"));
				j.setJobDate(rs.getString("jobDate"));
				j.setProdName(rs.getString("prodName"));
				j.setCustid(rs.getString("custid"));
				j.setJobContent(rs.getString("jobContent"));
				j.setCallback(rs.getString("callback"));
				j.setUserid(rs.getString("userid"));
				j.setCustEval(rs.getString("custEval"));
				j.setCustSign(rs.getString("custSign"));
				j.setStartTime(rs.getString("startTime"));
				j.setEndTime(rs.getString("endTime"));
				j.setWorkDay(rs.getString("workDay"));
				j.setBusMoney(rs.getString("busMoney"));
				j.setAttachMoney(rs.getString("attachMoney"));
				list.add(j);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/*
	 * 用户资料
	 * */
	
	public List<UsersVo> findUserAll(){
		
		List<UsersVo> list=new ArrayList<UsersVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from Users";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				UsersVo u=new UsersVo();
				u.setUserid(rs.getInt("userid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setDepid(rs.getInt("depid"));
				u.setDegreeid(rs.getInt("degreeid"));
				u.setJobname(rs.getString("jobname"));
				u.setManagerType(rs.getInt("managerType"));
				u.setMobile(rs.getString("mobile"));
				u.setEmail(rs.getString("email"));
				u.setQqcode(rs.getString("qqcode"));
				u.setWeixin(rs.getString("weixin"));
				u.setCardno(rs.getString("cardno"));
				u.setBankName(rs.getString("bankName"));
				u.setBankCardNo(rs.getString("bankCardNo"));
				u.setJoinDate(rs.getString("joinDate"));
				u.setWorkDate(rs.getString("workDate"));
				u.setLevelDate(rs.getString("levelDate"));
				u.setBaseSalary(rs.getString("baseSalary"));
				u.setDegreeSalary(rs.getString("degreeSalary"));
				u.setAddr(rs.getString("addr"));
				u.setStatus(rs.getInt("status"));
				u.setRemark(rs.getString("remark"));
				list.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/*
	 * 产品资料
	 * */
	public List<ProductVo> findProductAll(){
		List<ProductVo> list=new ArrayList<ProductVo>();
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="select * from Product";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				ProductVo p=new ProductVo();
				p.setProdid(rs.getString("prodid"));
				p.setProdname(rs.getString("prodname"));
				p.setProdModel(rs.getString("prodModel"));
				p.setProdUnit(rs.getInt("prodUnit"));
				p.setProdStyle(rs.getString("prodStyle"));
				p.setProdCount(rs.getFloat("prodCount"));
				p.setInPrice(rs.getDouble("inprice"));
				p.setSalePrice(rs.getDouble("salePrice"));
				p.setLowSalePrice(rs.getDouble("lowSalePrice"));
				p.setProdSerial(rs.getString("prodSerial"));
				p.setCdKey(rs.getString("cdKey"));
				p.setSaleStatus(rs.getString("saleStatus"));
				p.setSupplierId(rs.getInt("supplierId"));
				p.setRemark(rs.getString("remark"));
				list.add(p);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
}