package com.san.utils;
import java.sql.*;
public class DBUtil {
	//判断是否为空
	public static boolean isEmpty(String str){
		boolean b=false;
		if("".equals(str)||str==null){
			b=true;
		}else{
			b=false;
		}
		return b;
	}
	private static String driverClass="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://localhost:1433;DatabaseName=book";
	private static String user="sa";
	private static String pwd="123456";
	static{
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 *连接数据库
	 */
	public static Connection getconn(){
		
		try {
			return DriverManager.getConnection(url,user,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 关闭数据库
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}










