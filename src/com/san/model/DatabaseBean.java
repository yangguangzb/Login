package com.san.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBean {
	private static String driverClass="com.microsoft.sqlserver.jbdc.SQLServerDriver";
	private static String userName="sa";
	private static String pwd="123456";
	private static String url="jdbc:sqlserver://localhost:1433;DatabaseName=book";
	/**
	 * 连接数据库
	 */
	public static Connection getconn(){
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(url,userName,pwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 获得Statement
	 * @param conn
	 * @return
	 */
	public static Statement getstmt(Connection conn){
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	/**
	 * 关闭数据库
	 */
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
				stmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 查询
	 * @throws SQLException 
	 */
	public static ResultSet ResuleSetCha(String sql,Statement stmt) throws SQLException{
		ResultSet rs=null;
		rs=stmt.executeQuery(sql);
		return rs;
	}
	/**
	 * 更新(插入，修改)
	 */
	public static int ResultUpdate(String sql,Statement stmt) throws SQLException{
		int rs=0;
		rs=stmt.executeUpdate(sql);
		return rs;
	}
}















