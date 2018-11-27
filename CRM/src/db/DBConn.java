package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost:1433;databasename=CRM";
	private static String username = "sa";
	private static String password = "123456";
	private static Connection conn = null;
	static{
		try {
			Class.forName(driver);
			System.out.println("数据库连接成功!");
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//连接数据库
	public static Connection openDB(){
		try {
			if(conn == null || conn.isClosed()){
				conn = DriverManager.getConnection(url,username,password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}