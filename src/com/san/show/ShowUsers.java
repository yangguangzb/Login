/*package com.san.show;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.san.model.User;
import com.san.utils.DBUtil;

public class ShowUsers extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		String userName=req.getParameter("userName");
		String id="";
		//��ʾ��ϸ��Ϣ
		//Map<Integer, User> users = DBUtil.showAllusers();
		for(Integer i : users.keySet()){
			if(userName.equals(users.get(i).getUserName())){
				//����û���ƥ��
				out.write("����:");
				out.write(users.get(i).getUserName()+"<br/><br/>");
				out.write("����:");
				out.write(users.get(i).getPwd()+"<br/><br/>");
				out.write("����:");
				out.write(users.get(i).getMailbox()+"<br/><br/>");
				id=users.get(i).getId()+"";
			}
		}
	
		//����һ��cookie
		String browse=pinjie(id,req);
		Cookie cookie=new Cookie("browse",browse);
		cookie.setPath("/");
		cookie.setMaxAge(Integer.MAX_VALUE);
		resp.addCookie(cookie);
		Cookie[] cks=req.getCookies();
		if(cks!=null){
			for(int i=0;i<cks.length;i++){
				if("browse".equals(cks[i].getName())){
					String user=cks[i].getValue();
					String[] users1=user.split("-");
					for(int j=0;j<users1.length;j++){
						out.write(DBUtil.showUserById(Integer.parseInt(users1[j])).getUserName()+"<br/><br/>");
					}		
				}
			}
		}
	}

	private String pinjie(String id, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if(cookies==null){
			return id;
		}
		Cookie ck=null;
		for(int i=0;i<cookies.length;i++){
			//�ж��Ƿ���browse��cookie
			if("browse".equals(cookies[i].getName())){
				ck=cookies[i];
			}
		}
		if(ck==null){
			return id;
		}
		//����û�������ֵ
		String value = ck.getValue();
		//�ָ�
		String[] users = value.split("-");
		//������ת��Ϊ����
		LinkedList<String> list=new LinkedList<String>(Arrays.asList(users));
		//����,���µ�¼������ǰ��
		if(list.size()<3){
			if(list.contains(id)){
				list.remove(id);
				list.addFirst(id);
			}else{
				list.addFirst(id);
			}
		}else{
			if(list.contains(id)){
				list.remove(id);
				list.addFirst(id);
			}else{
				list.removeLast();
			}
		}
		//ƴ��
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<list.size();i++){
			if(i>0){
				sb.append("-");
			}
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
}	*/
