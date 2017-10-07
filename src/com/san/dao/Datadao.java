package com.san.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.san.model.User;

public class Datadao {
	/**
	 * 登录时查询用户
	 * @throws SQLException 
	 */
	public ResultSet chaUser(String userName,String password,Connection conn) throws SQLException{
		String sql="select * from db_user where userName='"+userName+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	/**
	 * 注册时添加用户
	 * @throws SQLException 
	 */
	public int addUser(String userName,String password,String mailbox,int phone,Connection conn) throws SQLException{
		String sql="select * from db_user where userName='"+userName+"'";
		String sql2="select * from db_user where mailbox='"+mailbox+"'";
		String sql3="select * from db_user where phone='"+phone+"'";
		Statement stmt1=conn.createStatement();
		Statement stmt2=conn.createStatement();
		Statement stmt3=conn.createStatement();
		ResultSet rs=stmt1.executeQuery(sql);
		ResultSet rs2=stmt2.executeQuery(sql2);
		ResultSet rs3=stmt3.executeQuery(sql3);
		if(rs.next()!=false){
			//用户名已存在不能注册
			return -1;
		}else{
			//
			if(rs3.next()!=false){
				//手机号已存在不能注册
				return -2;
			}
			if(rs2.next()!=false){
				//邮箱已存在不能注册
				return -3;
			}
			else{
				//可以注册
				String sql1="insert into db_user values('"+userName+"','"+password+"','"+mailbox+"',"+phone+",2,null);";
				stmt1.executeUpdate(sql1);
				return 0;
			}
		}
	}
	/**
	 *通过手机号或者邮箱找回密码
	 * @throws SQLException 
	 */
	public String chaByPhone(String phone,int i,Connection conn) throws SQLException{
		String sql="";
		int m=i;
		Statement stmt=conn.createStatement();
		String password="";
		if(m==1){
			//邮箱找回
			sql="select * from db_user where mailbox='"+phone+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()!=false){
				password=rs.getString(3);
				return password;
			}
			return "";
		}else{
			//手机号找回
			sql="select * from db_user where phone="+phone+"";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()!=false){
				password=rs.getString(3);
				return password;
			}
			return "";
		}
	}
	/**
	 *查询所有用户
	 *便于管理员操作
	 */
	public ArrayList<User> allUsers(int pageSize,int pageNow,Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="select top "+pageSize+" * from db_user where id not in (select top " +
				""+(pageNow-1)*pageSize+" id from db_user order by id asc) order by id asc";
		ResultSet rs=stmt.executeQuery(sql);
		ArrayList<User> list=new ArrayList<User>();
		while(rs.next()){
			User user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			list.add(user);
		}
		return list;
	}
	/**
	 *查询所有用户的数量
	 */
	public ResultSet numberUser(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="select count(*) from db_user";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	/**
	 * 根据id删除用户
	 */
	public int delete(Connection conn,int id) throws SQLException{
		//String sql="delete from db_user where id=?";
		PreparedStatement ps=conn.prepareStatement("delete from db_user where id=?");
		ps.setString(1,id+"");
		int n=ps.executeUpdate();
		return n;
	}
	/**
	 *精确查询用户
	 * @throws SQLException 
	 */
	public ArrayList<User> jingCha(String userName,Connection conn) throws SQLException{
		String sql="select * from db_user where userName='"+userName+"'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		ArrayList<User> list=new ArrayList<User>();
		while(rs.next()){
			User user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			list.add(user);
		}
		return list;
	}
	/**
	 *模糊查询用户
	 */
	public ArrayList<User> moCha(String userName,Connection conn) throws SQLException{
		String sql="select * from db_user where userName like '%"+userName+"%'";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		ArrayList<User> list1=new ArrayList<User>();
		while(rs.next()){
			User user=new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			list1.add(user);
		}
		return list1;
	}
	/**
	 *
	 */
	public ResultSet chaById(String id,Connection conn) throws SQLException{
		String sql="select * from db_user where id=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1,Integer.parseInt(id));
		return ps.executeQuery();
	}
	/**
	 * 管理员添加用户
	 * @param id
	 * @param userName
	 * @param password
	 * @param mailbox
	 * @param phone
	 * @param grade
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public int modify(String id,String userName,String password,String mailbox,String phone,String grade,Connection conn) throws SQLException{
		int i=0;
		String sql="update db_user set userName=?,password=?,mailbox=?,phone=?,grade=? where id=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1,userName);
		ps.setString(2,password);
		ps.setString(3,mailbox);
		ps.setInt(4,Integer.parseInt(phone));
		ps.setInt(5,Integer.parseInt(grade));
		ps.setInt(6,Integer.parseInt(id));
		i=ps.executeUpdate();
		return i;
	}
	/**
	 * 用户修改密码
	 */
	public int xiugaiPwd(String pwd,String id,Connection conn) throws SQLException{
		String sql="update db_user set password='"+pwd+"' where id="+Integer.parseInt(id)+"";
		Statement stmt=conn.createStatement();
		return stmt.executeUpdate(sql);
	}
}











