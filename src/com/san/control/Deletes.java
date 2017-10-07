package com.san.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.san.dao.AjaxDatadao;
import com.san.dao.Datadao;
import com.san.model.User;
import com.san.utils.DBUtil;

public class Deletes extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		Datadao db=new Datadao();
		String flag="";
		Connection conn=null;
		PrintWriter out=resp.getWriter();
		flag=req.getParameter("flag");
		/**
		 *管理员删除用户信息
		 */
		if("delete".equals(flag)){
			if(req.getParameter("id")!=null){
				//Connection conn=null;
				conn=DBUtil.getconn();
				int id=Integer.parseInt(req.getParameter("id"));
				try {
					int n=db.delete(conn, id);
					if(n==1){
						int pageNow=Integer.parseInt(req.getParameter("pageNow"));
						int countPage=Integer.parseInt(req.getParameter("countPage"));
						int countLine=Integer.parseInt(req.getParameter("countLine"));
						//最后一页的最后一条记录
						if(req.getParameter("countLine")!=null&&((pageNow-1)*3+1==countLine)&&countPage==pageNow){
							pageNow--;
						}
						req.getRequestDispatcher("manageFace.jsp?pageNow="+pageNow+"").forward(req, resp);
					}else{
						System.out.println("删除失败");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("删除失败");
				}finally{
					DBUtil.closeConn(conn);
				}
			}
		}
		/**
		 * 管理员查询用户信息
		 */
		if("chaxun".equals(flag)){
			if(req.getParameter("userName")!=null){
				String userName=req.getParameter("userName");
				HttpSession session=req.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("radio",req.getParameter("mo"));
				Connection conn1=null;
				conn1=DBUtil.getconn();
				if(req.getParameter("mo")!=null&&"1".equals(req.getParameter("mo"))){
					//模糊查询
					try {
						ArrayList<User> moCha = db.moCha(userName, conn1);
						session.setAttribute("moCha", moCha);
						resp.sendRedirect("checkUsers.jsp");
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						DBUtil.closeConn(conn1);
					}
				}
				else if(req.getParameter("mo")!=null&&"0".equals(req.getParameter("mo"))){
					//精确查询
					try {
						ArrayList<User> jingCha = db.jingCha(userName, conn1);
						session.setAttribute("jingCha", jingCha);
						resp.sendRedirect("checkUsers.jsp");
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						DBUtil.closeConn(conn1);
					}
				}
			}
		}
		/**
		 * 管理员修改用户信息
		 */
		if("modify".equals(flag)){
			String id=req.getParameter("id");
			String userName=req.getParameter("userName");
			String password=req.getParameter("password");
			String mailbox=req.getParameter("mailbox");
			String phone=req.getParameter("phone");
			String grade=req.getParameter("grade");
			try {
				conn=DBUtil.getconn();
				int i=db.modify(id, userName, password, mailbox, phone, grade, conn);
				if(i==1){
					out.write("修改成功");
				}else{
					out.write("修改失败");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		/**
		 * 用户修改密码时,验证输入的旧密码是否正确
		 */
		if("checkOld".equals(flag)){
			conn=DBUtil.getconn();
			String pwd=req.getParameter("oldPwd");
			AjaxDatadao adb=new AjaxDatadao();
			try {
				out.write(adb.checkOld(pwd, conn)+"");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeConn(conn);
			}
		}
		/**
		 * 用户修改密码
		 */
		String newpassword="";
		String newPwd1="";
		if(req.getParameter("newpassword")!=null){
			newpassword=req.getParameter("newpassword");
		}
		if(req.getParameter("newPwd1")!=null&&"xiugai".equals(newpassword)){
			newPwd1=req.getParameter("newPwd1");
			String id=req.getSession().getAttribute("id").toString();
			try {
				Datadao db1=new Datadao();
				conn=DBUtil.getconn();
				int j=db1.xiugaiPwd(newPwd1, id, conn);
				req.getRequestDispatcher("commonFace.jsp?cheng="+j+"").forward(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeConn(conn);
			}
			
		}
		
		
		
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
}
