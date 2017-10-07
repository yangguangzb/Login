package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.san.dao.Datadao;
import com.san.utils.DBUtil;

public class LoginYanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String userName="";
		String pwd="";
		String code1="";
		Datadao db=new Datadao();
		Connection conn=DBUtil.getconn();
		boolean b=false;
		//获得产生的随机数
		String code=session.getAttribute("suiName").toString();
		//获取表单数据
		if(req.getParameter("userName")!=null&&req.getParameter("pwd")!=null&&req.getParameter("code1")!=null){
		 	userName=req.getParameter("userName");
			pwd=req.getParameter("pwd");
			code1=req.getParameter("code1");
			ResultSet rs;
			try {
				rs = db.chaUser(userName,pwd,conn);
				if(rs.next()!=false){
					//用户名正确
					String password=rs.getString(3);
					if(!pwd.equals(password)){
						//密码不正确
						out.write("密码不正确");
					}else{
						//密码正确
						if(!code1.equals(code)){
							//验证码不正确
							out.write("验证码不正确");
						}else{
							//登录成功,管理员
							if(rs.getInt(6)==1){
								resp.sendRedirect("manageMain.jsp");
							}else{
								//登录成功,普通用户
								resp.sendRedirect("commonFace.jsp");
								HttpSession session2 = req.getSession();
								session2.setAttribute("id",rs.getString(1));
							}		
						}
					}
					b=true;
				}
				if(b==false){
					out.write("用户名不正确!");
				}
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
