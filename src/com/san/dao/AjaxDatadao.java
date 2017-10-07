package com.san.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AjaxDatadao {
	/**
	 * 只查询用户名
	 * @throws SQLException 
	 */
	public int zhiChUser(String userName,Connection conn) throws SQLException{
		String sql="select userName from db_user where userName='"+userName+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		int i=0;
		if(rs.next()){
			i=-1;
		}
		//用户名不存在
		return i;
	}
	/**
	 * 登录验证返回user
	 * @param userName
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public String zhiChUser1(String userName,Connection conn) throws SQLException{
		String sql="select * from db_user where userName='"+userName+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		String imageUri="-1";
		if(rs.next()){
			//用户名存在
			if(rs.getString(7)!=null){
				//有头像
				imageUri=rs.getString(7);
			}else{
				//没有头像
				imageUri="1";
			}
		}
		return imageUri;
	}
	/**
	 * 只查询手机号
	 * @throws SQLException 
	 */
	public int chPhone(String phone,Connection conn) throws SQLException{
		int i=0;
		String sql="select phone from db_user where phone='"+phone+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			//手机号已存在
			i=-1;
		}
		return i;
	}
	/**
	 * 只查询邮箱
	 * @throws SQLException 
	 */
	public int chMailbox(String mailbox,Connection conn) throws SQLException{
		int i=0;
		String sql="select mailbox from db_user where mailbox='"+mailbox+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			//邮箱已存在
			i=-1;
		}
		return i;
	}
	/**
	 * 用户修改密码时,验证输入的旧密码是否正确
	 */
	public int checkOld(String pwd,Connection conn) throws SQLException{
		int i=0;
		String sql="select * from db_user where password='"+pwd+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			//输入旧密码正确
			i=1;
		}
		return i;
	}
	
}
