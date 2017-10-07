package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.san.dao.Datadao;
import com.san.utils.DBUtil;
/**
 * 找回密码
 * @author Administrator
 *
 */
public class RetrievePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String id="";
		int i=0;
		Datadao db=new Datadao();
		Connection conn=DBUtil.getconn();
		if(req.getParameter("Id")!=null){
			id=req.getParameter("Id");
			if(id.indexOf("@")!=-1){
				//是邮箱
				i=1;
				String pwd1;
				try {
					pwd1 = db.chaByPhone(id,i,conn);
					if("".equals(pwd1)){
						out.write("输入的邮箱有误!");
					}else{
						out.write("密码是:"+pwd1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//是手机号
				i=0;
				String pwd2;
				try {
					pwd2 = db.chaByPhone(id,i,conn);
					if("".equals(pwd2)){
						out.write("输入的手机号有误!");
					}else{
						out.write("密码是:"+pwd2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DBUtil.closeConn(conn);
				}
			}
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
}
