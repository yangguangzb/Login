package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.san.dao.AjaxDatadao;
import com.san.utils.DBUtil;

public class RegisterYanAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//异步验证注册的用户名是否存在
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		//User user=new User();
		int i;
		PrintWriter out = resp.getWriter();
		Connection conn=DBUtil.getconn();
		Connection conn1=DBUtil.getconn();
		AjaxDatadao adb=new AjaxDatadao();
		String userName=req.getParameter("userName");
		String flag=req.getParameter("flag");
		String imageUri="";
		if(flag.equals("login")){
			//登录时的处理
			try {
				imageUri=adb.zhiChUser1(userName, conn1);
				out.write(imageUri);
				HttpSession session = req.getSession();
				session.setAttribute("imageUri", imageUri);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeConn(conn1);
			}
			
		}
		if(flag.equals("register")){
			//注册时处理
			try {
				i=adb.zhiChUser(userName,conn);
				out.write(i+"");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		
		
	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
