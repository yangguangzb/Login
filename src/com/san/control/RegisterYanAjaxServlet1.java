package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.san.dao.AjaxDatadao;
import com.san.utils.DBUtil;

public class RegisterYanAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//验证手机号是否存在
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		int i=0;
		PrintWriter out = resp.getWriter();
		Connection conn=DBUtil.getconn();
		AjaxDatadao adb=new AjaxDatadao();
		String phone=req.getParameter("phone");
		try {
			i=adb.chPhone(phone, conn);
			out.write(i+"");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConn(conn);
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
