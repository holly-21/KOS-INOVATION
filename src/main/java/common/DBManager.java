package common;
/**
 * JDBC를 위한 로드, 연결, 닫기
 * */


import java.sql.*;
import java.util.Properties;

public class DBManager {

	private static Properties properties = new Properties();

	/**
	 * 로드
	 * */
	static {
		try {
		  Class.forName(properties.getProperty("driverName"));
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 연결
	 * */
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(
				properties.getProperty("url"),
				properties.getProperty("userName"),
				properties.getProperty("userPass"));
		}
	
	/**
	 * 닫기(DML전용)
	 * */
	public static void releaseConnection(Connection con, Statement st) {
		try {
			if(st!=null) st.close();
			if(con!=null)con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 닫기(select전용)
	 * */
    public static void DbClose(Connection con, Statement st, ResultSet rs) {
    	try {
    		if(rs!=null)rs.close();
    	}catch (SQLException e) {
			e.printStackTrace();
		}
    	releaseConnection(con, st);
	}
	
}





