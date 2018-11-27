package lfdDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConn;





import bean.CustomerInfoVo;
import bean.ProductVo;
import bean.UnitVo;
import bean.UsersVo;
import bean.contractVo;
import bean.degreesVo;
import bean.supplierVo;

public class lfdBaseDAO {
	
	//统计记录总数
	public int findCount(String form){
		int cnt=0;
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="select count(*) cnt from "+form;
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				cnt=rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	//岗位-----------------------------------------------------
	
	
	//新增岗位
	public void Degreessave(degreesVo d){
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into degrees values('";
			sql += d.getDegreename()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//删除岗位
	public void Degreesdel(String degreeid){
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="delete degrees where degreeid="+degreeid;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//修改岗位
	public void Degreesupdate(degreesVo d){
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql = "update degrees set degreename='";
			sql += d.degreename +"'";
			sql += "where degreeid="+d.getDegreeid();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	//分析查询岗位
	public List<degreesVo> DegreesfindByPage(int startRow,int pageSize) {
		List<degreesVo>list=new ArrayList<degreesVo>();
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from degrees where degreeid not in(";
			sql+="select top "+startRow+" degreeid from degrees";
			sql+=")";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				degreesVo s = new degreesVo();
				s.setDegreeid(rs.getInt("degreeid"));
				s.setDegreename(rs.getString("degreename").trim());
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	//通过部门id查询岗位信息
	public degreesVo DegreesfindByStudId(String degreeid) {
		Connection conn=DBConn.openDB();
		degreesVo dep = new degreesVo();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from degrees where degreeid="+degreeid;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				dep.setDegreeid(rs.getInt("degreeid"));
				dep.setDegreename(rs.getString("degreename").trim());
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dep;
	}
	
	/*
	 * 商品表--------------------------------------------------
	 */
	
	//新增
	public void saveProduct(ProductVo d) {
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into Product values('";
			sql+=d.getProdid()+"','";
			sql+=d.getProdname()+"','";
			sql+=d.getProdModel()+"',";
			sql+=d.getProdUnit()+",'";
			sql+=d.getProdStyle()+"',";
			sql+=d.getProdCount()+",";
			sql+=d.getInPrice()+",";
			sql+=d.getSalePrice()+",";
			sql+=d.getLowSalePrice()+",'";
			sql+=d.getProdSerial()+"','";
			sql+=d.getCdKey()+"','";
			sql+=d.getSaleStatus()+"',";
			sql+=d.getSupplierId()+",'";
			sql+=d.getRemark()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//根据id查询
	public ProductVo findByProductId(String prodid) {
		Connection conn=DBConn.openDB();
		ProductVo dep = new ProductVo();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from Product where prodid='"+prodid+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				dep.setProdid(rs.getString("prodid"));
				dep.setProdname(rs.getString("prodname"));
				dep.setProdModel(rs.getString("prodModel"));
				dep.setProdUnit(rs.getInt("prodUnit"));
				dep.setProdStyle(rs.getString("prodStyle"));
				dep.setProdCount(rs.getFloat("prodCount"));
				dep.setInPrice(rs.getDouble("inPrice"));
				dep.setSalePrice(rs.getDouble("salePrice"));
				dep.setLowSalePrice(rs.getDouble("lowSalePrice"));
				dep.setProdSerial(rs.getString("prodSerial"));
				dep.setCdKey(rs.getString("cdKey"));
				dep.setSaleStatus(rs.getString("saleStatus"));
				dep.setSupplierId(rs.getInt("supplierId"));
				dep.setRemark(rs.getString("remark"));
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dep;
	}
	
	//修改商品信息
	public void updateProduct(ProductVo d) {
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql = "update Product set prodname='";
			sql += d.getProdname() +"',prodModel='";
			sql += d.getProdModel() +"',prodUnit=";
			sql += d.getProdUnit() +",prodStyle='";
			sql += d.getProdStyle() +"',prodCount=";
			sql += d.getProdCount() +",inPrice=";
			sql += d.getInPrice() +",salePrice=";
			sql += d.getSalePrice() +",lowSalePrice=";
			sql += d.getLowSalePrice() +",prodSerial='";
			sql += d.getProdSerial() +"',cdKey='";
			sql += d.getCdKey() +"',saleStatus='";
			sql += d.getSaleStatus() +"',supplierId=";
			sql += d.getSupplierId() +",remark='";
			sql += d.getRemark()+"' where prodid='"+d.getProdid()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//分页查询商品信息
	public List<ProductVo> findByProductPage(int startRow,int pageSize) {
		List<ProductVo> list = new ArrayList<ProductVo>();
		Connection conn=DBConn.openDB();
		//计算起始位置
		try{
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" p .*,u.unitName,s.supplierName  from Product p inner join unit u on p.ProdUnit=u.unitId inner join supplier s on p.supplierId=s.supplierId where prodid not in(";
			sql+="select top "+startRow+" prodid from Product";
			sql+=")";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				ProductVo s = new ProductVo();
				s.setProdid(rs.getString("prodid"));
				s.setProdname(rs.getString("prodname"));
				s.setProdModel(rs.getString("prodModel"));
				s.setProdUnit(rs.getInt("prodUnit"));
				s.setProdStyle(rs.getString("prodStyle"));
				s.setProdCount(rs.getFloat("prodCount"));
				s.setInPrice(rs.getDouble("inPrice"));
				s.setSalePrice(rs.getDouble("salePrice"));
				s.setLowSalePrice(rs.getDouble("lowSalePrice"));
				s.setProdSerial(rs.getString("prodSerial"));
				s.setCdKey(rs.getString("cdKey"));
				s.setSaleStatus(rs.getString("saleStatus"));
				s.setSupplierId(rs.getInt("supplierId"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//新增下拉框单位表的数据
	public List<UnitVo> findunitAll() {
		List<UnitVo> list = new ArrayList<UnitVo>();
		//创建Connection对象
		Connection conn = DBConn.openDB();
		//计算起始位置
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from unit";
			System.out.println("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UnitVo s = new UnitVo();
				s.setUnitId(rs.getInt("unitId"));
				s.setUnitName(rs.getString("unitName").trim());
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	//新增下拉框供应商表的数据
	public List<supplierVo> findsupplierAll() {
		List<supplierVo> list = new ArrayList<supplierVo>();
		//创建Connection对象
		Connection conn = DBConn.openDB();
		//计算起始位置
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from supplier";
			System.out.println("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				supplierVo s = new supplierVo();
				s.setSupplierId(rs.getInt("supplierId"));
				s.setSupplierName(rs.getString("supplierName").trim());
				s.setBankAccount(rs.getString("bankAccount"));
				s.setBankName(rs.getString("bankName"));
				s.setContact(rs.getString("contact"));
				s.setPhone(rs.getString("phone"));
				s.setAddr(rs.getString("addr"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//供应商信息---------------------
	//根据id查询每一个供应信息
	public supplierVo findBysupplierId(String supplierId){
		Connection conn=DBConn.openDB();
		supplierVo dep = new supplierVo();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from supplier where supplierId="+supplierId;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				dep.setSupplierId(rs.getInt("supplierId"));
				dep.setSupplierName(rs.getString("supplierName").trim());
				dep.setBankAccount(rs.getString("bankAccount"));
				dep.setBankName(rs.getString("bankName"));
				dep.setContact(rs.getString("contact"));
				dep.setPhone(rs.getString("phone"));
				dep.setAddr(rs.getString("addr"));
				dep.setRemark(rs.getString("remark"));
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dep;
	}
	//新增
	public void savesupplier(supplierVo d) {
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into supplier values('";
			sql+=d.getSupplierName()+"','";
			sql+=d.getBankAccount()+"','";
			sql+=d.getBankName()+"','";
			sql+=d.getContact()+"','";
			sql+=d.getPhone()+"','";
			sql+=d.getAddr()+"','";
			sql+=d.getRemark()+"')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//删除
	public void delsupplier(String supplierId){
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="delete supplier where supplierId="+supplierId;
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//修改
	public void update(supplierVo d) {
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql = "update supplier set supplierName='";
			sql += d.getSupplierName() +"',bankAccount='";
			sql += d.getBankAccount() +"',bankName='";
			sql += d.getBankName() +"',contact='";
			sql += d.getContact() +"',phone='";
			sql += d.getPhone() +"',addr='";
			sql += d.getAddr() +"',remark='";
			sql += d.getRemark()+"' where supplierId="+d.getSupplierId();
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//统计记录总数
	public int findsupplierCount() {
		int cnt=0;
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="select count(*) cnt from supplier";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				cnt=rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}//分析查询供应商信息
	public List<supplierVo> findBysupplierPage(int startRow,int pageSize) {
		List<supplierVo>list=new ArrayList<supplierVo>();
		Connection conn=DBConn.openDB();
		
		try{
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" * from supplier where supplierId not in(";
			sql+="select top "+startRow+" supplierId from supplier";
			sql+=")";
			System.out.println(sql);
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				supplierVo s = new supplierVo();
				s.setSupplierId(rs.getInt("supplierId"));
				s.setSupplierName(rs.getString("supplierName").trim());
				s.setBankAccount(rs.getString("bankAccount"));
				s.setBankName(rs.getString("bankName"));
				s.setContact(rs.getString("contact"));
				s.setPhone(rs.getString("phone"));
				s.setAddr(rs.getString("addr"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//------------------------------------------------------------
	
	//新增合同信息
	public void savecontract(contractVo d) {
		Connection conn=DBConn.openDB();
		try {
			Statement stmt=conn.createStatement();
			String sql="insert into contract values('";
			sql+=d.getContract_no()+"','";
			sql+=d.getContractname()+"',";
			sql+=d.getCustId()+",'";
			sql+=d.getContract_time()+"','";
			sql+=d.getDue_time()+"','";
			sql+=d.getTotal_money()+"',";
			sql+=d.getDeposit()+",'";
			sql+=d.getPay_type()+"','";
			sql+=d.getStatus()+"',";
			sql+=d.getEmpid()+",'";
			sql+=d.getOperator()+"','";
			sql+=d.getOprtime()+"','";
			sql+=d.getRemark()+"'";
			sql+=")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//根据id查询
	public contractVo findBycontractId(String contract_id) {
		Connection conn=DBConn.openDB();
		contractVo dep = new contractVo();
		
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from contract where contract_id="+contract_id;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				dep.setContract_id((rs.getInt("contract_id")));
				dep.setContract_no((rs.getString("contract_no").trim()));
				dep.setContractname(rs.getString("contractname"));
				dep.setCustId(rs.getInt("custId"));
				dep.setContract_time(rs.getString("contract_time"));
				dep.setDue_time(rs.getString("due_time"));
				dep.setTotal_money(rs.getString("total_money"));
				dep.setDeposit(rs.getInt("deposit"));
				dep.setPay_type(rs.getString("pay_type"));
				dep.setStatus(rs.getString("status"));
				dep.setEmpid(rs.getInt("empid"));
				dep.setOperator(rs.getString("operator"));
				dep.setOprtime(rs.getString("oprtime"));
				dep.setRemark(rs.getString("remark"));
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dep;
	}
	//修改合同信息
	public void updatecontract(contractVo d) {
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql = "update contract set contract_no='";
			sql += d.getContract_no() +"',contractname='";  
			sql += d.getContractname()+"',custId=";
			sql += d.getCustId() +",contract_time='";
			sql += d.getContract_time() +"',due_time='";
			sql += d.getDue_time() +"',total_money='";
			sql += d.getTotal_money() +"',deposit=";
			sql += d.getDeposit() +",pay_type='";
			sql += d.getPay_type() +"',status='";
			sql += d.getStatus() +"',empid=";
			sql += d.getEmpid() +",operator='";
			sql += d.getOperator() +"',oprtime='";
			sql += d.getOprtime() +"',remark='";
			sql += d.getRemark() +"' where contract_id="+d.getContract_id();
			stmt.executeUpdate(sql);
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//统计记录总数
	public int findcontractCount() {
		int cnt=0;
		Connection conn=DBConn.openDB();
		try{
			Statement stmt=conn.createStatement();
			String sql="select count(*) cnt from contract";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				cnt=rs.getInt("cnt");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	//分页查询
	public List<contractVo> findBycontractPage(int startRow,int pageSize) {
		List<contractVo>list=new ArrayList<contractVo>();
		Connection conn=DBConn.openDB();
		//计算起始位置
		
		try{
			Statement stmt=conn.createStatement();
			String sql="select top "+pageSize+" p .*,c.custname,u.username  from contract p inner join CustomerInfo c on p.custId=c.custId inner join Users u on p.empid=u.userid where contract_id  not in(";
			sql+="select top "+startRow+" contract_id from contract";
			sql+=")";
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println("分页查询="+sql);
			while(rs.next()){
				contractVo s = new contractVo();
				s.setContract_id(rs.getInt("contract_id"));
				s.setContract_no(rs.getString("contract_no"));
				s.setContractname(rs.getString("contractname"));
				s.setCustId(rs.getInt("custId"));
				s.setContract_time(rs.getString("contract_time"));
				s.setDue_time(rs.getString("due_time"));
				s.setTotal_money(rs.getString("total_money"));
				s.setDeposit(rs.getInt("deposit"));
				s.setPay_type(rs.getString("pay_type"));
				s.setStatus(rs.getString("status"));
				s.setEmpid(rs.getInt("empid"));
				s.setOperator(rs.getString("operator"));
				s.setOprtime(rs.getString("oprtime"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//显示客户资料表的数据
	public List<CustomerInfoVo> findcusinfoAll() {
		List<CustomerInfoVo> list = new ArrayList<CustomerInfoVo>();
		//创建Connection对象
		Connection conn = DBConn.openDB();
		//计算起始位置
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from CustomerInfo";
			System.out.println("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CustomerInfoVo s = new CustomerInfoVo();
				s.setCustId(rs.getInt("custId"));
				s.setCustname(rs.getString("custname").trim());
				s.setCusttype(rs.getString("custtype"));
				s.setBankAccount(rs.getString("bankAccount"));
				s.setBankName(rs.getString("bankName"));
				s.setContact(rs.getString("contact"));
				s.setPhone(rs.getString("phone"));
				s.setTicketName(rs.getString("ticketName"));
				s.setTicketAddr(rs.getString("ticketAddr"));
				s.setTicketTel(rs.getString("ticketTel"));
				s.setTaxNo(rs.getString("taxNo"));
				s.setCustState(rs.getString("custState"));
				s.setUsername(rs.getString("username"));
				s.setSource(rs.getString("source"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	//显示用户表的数据
	public List<UsersVo> findusersAll() {
		List<UsersVo> list = new ArrayList<UsersVo>();
		//创建Connection对象
		Connection conn = DBConn.openDB();
		//计算起始位置
		try{
			Statement stmt = conn.createStatement();
			String sql = "select * from users";
			System.out.println("sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				UsersVo s = new UsersVo();
				s.setUserid(rs.getInt("userid"));
				s.setUsername(rs.getString("username").trim());
				s.setPassword(rs.getString("password"));
				s.setDegreeid(rs.getInt("degreeid"));
				s.setJobname(rs.getString("jobname"));
				s.setManagerType(rs.getInt("managerType"));
				s.setMobile(rs.getString("mobile"));
				s.setEmail(rs.getString("email"));
				s.setQqcode(rs.getString("qqcode"));
				s.setWeixin(rs.getString("weixin"));
				s.setCardno(rs.getString("cardno"));
				s.setBankName(rs.getString("bankName"));
				s.setBankCardNo(rs.getString("bankCardNo"));
				s.setJoinDate(rs.getString("joinDate"));
				s.setWorkDate(rs.getString("workDate"));
				s.setLevelDate(rs.getString("levelDate"));
				s.setBaseSalary(rs.getString("baseSalary"));
				s.setDegreeSalary(rs.getString("degreeSalary"));
				s.setAddr(rs.getString("addr"));
				s.setUsername(rs.getString("username"));
				s.setStatus(rs.getInt("status"));
				s.setRemark(rs.getString("remark"));
				list.add(s);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
