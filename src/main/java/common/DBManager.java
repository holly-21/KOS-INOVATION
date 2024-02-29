package common;
/**
 * JDBC를 위한 로드, 연결, 닫기
 * */

import properties.DbProperties;

import java.sql.*;

public class DBManager {

	/**
	 * 로드
	 * */
	static {
		try {
		  Class.forName(DbProperties.DRIVER_NAME);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 연결
	 * */
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DbProperties.URL,
				DbProperties.USER_ID, DbProperties.USER_PASS);
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





