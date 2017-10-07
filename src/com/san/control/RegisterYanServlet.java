package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.san.dao.Datadao;
import com.san.utils.DBUtil;

public class RegisterYanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String userName="";
		String phone="";
		String pwd="";
		String mailbox="";
		userName = req.getParameter("userName");
		if(req.getParameter("userName")!=null&&req.getParameter("phone")!=null
		&&req.getParameter("pwd")!=null&&req.getParameter("mailbox")!=null){
			Datadao db=new Datadao();
			Connection conn=DBUtil.getconn();
			userName=req.getParameter("userName");
			phone=req.getParameter("phone");
			pwd=req.getParameter("pwd");
			mailbox=req.getParameter("mailbox");
			int m;
			try {
				m = db.addUser(userName,pwd,mailbox,Integer.parseInt(phone.trim()),conn);
				if(m==-1){
					//用户名已存在
					out.write("-1");
					return ;
				}
				if(m==-2){
					//手机号已存在
					out.write("手机号已存在");
					return ;
				}
				if(m==-3){
					//邮箱已存在
					out.write("邮箱已存在");
					return ;
				}
				if(m==0){
					//注册成功
					out.write("注册成功<br/>");
					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//关闭连接
				DBUtil.closeConn(conn);
			}
			
		} 
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}

}
