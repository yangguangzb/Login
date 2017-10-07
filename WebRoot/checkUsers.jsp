<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.san.model.User"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.san.dao.Datadao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function change(){
		var userName=document.form1.userName.value;
		var radio=document.form1.mo.value;
		if(userName==""||radio==""){
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
	table{
		border-collapse:collapse;
	}
	table td{
		width:50px;
		height:20px;
	}
</style>
</head>
<body>
	<%--查询用户 --%>
	<%
		request.setCharacterEncoding("UTF-8");
		String userName="";
		String m="";
		if(session.getAttribute("userName")!=null){
			userName=session.getAttribute("userName").toString();
		}
		if(session.getAttribute("radio")!=null){
			 m=session.getAttribute("radio").toString();
		}
	 %>
	<center>
		<form action="/Login/deletes?flag=chaxun" method="post" name="form1" onsubmit="return change()">
			<input placeholder="用户名" name="userName" value="<%=userName %>"/>
			<input type="radio" name="mo" value="0" <%="0".equals(m) ? "checked":"" %>/>精确查询
			<input type="radio" name="mo" value="1" <%="1".equals(m) ? "checked":"" %>/>模糊查询
			<input type="submit" value="查询"/> 
		</form>
		<br/>
		 <table border=1px>
		 	<tr>
				<td>编号</td><td>用户名</td><td>密码</td><td>邮箱</td><td>手机</td><td>等级</td>
			</tr>
			 <%
			 	int pageSize=3;				//每一页的大小
				int countPage=0;			//有多少页
				int pageNow=1;				//显示第几页
				if(session.getAttribute("radio")!=null){
					m=session.getAttribute("radio").toString();
					if(session.getAttribute("moCha")!=null){
						ArrayList<User> listMo=(ArrayList<User>)session.getAttribute("moCha");
						//session不为空时,值可能为0
						if("1".equals(m)&&listMo.size()>0){
							//模糊查询的显示
							int countLine=listMo.size(); //有多少条记录
							if(countLine>0){
								countPage=(countLine%pageSize==0 ? countLine/pageSize : countLine/pageSize+1);
								if(request.getParameter("pageNow")!=null){
									pageNow=Integer.parseInt(request.getParameter("pageNow"));
								}
								//显示用户详细信息
								if(countPage!=pageNow){
									//如果显示的不是最后一页
									for(int i=(pageNow-1)*pageSize;i<pageNow*pageSize;i++){
										%><tr><td><%=listMo.get(i).getId() %></td><td><%=listMo.get(i).getUserName() %></td><td><%=listMo.get(i).getPwd() %></td>
										<td><%=listMo.get(i).getMailbox() %></td><td><%=listMo.get(i).getPhone() %></td><td><%=listMo.get(i).getGrade() %></td></tr><%
									}
								}
								else if(countPage==pageNow){
									//显示最后一页
									for(int i=(pageNow-1)*pageSize;i<countLine;i++){
										%><tr><td><%=listMo.get(i).getId() %></td><td><%=listMo.get(i).getUserName() %></td><td><%=listMo.get(i).getPwd() %></td>
										<td><%=listMo.get(i).getMailbox() %></td><td><%=listMo.get(i).getPhone() %></td><td><%=listMo.get(i).getGrade() %></td></tr><%
									}
								}
							}
						}
					}
					if("0".equals(m)&&session.getAttribute("jingCha")!=null){
						//精确查询显示
						ArrayList<User> listJing=(ArrayList<User>)session.getAttribute("jingCha");
						if(listJing.size()>0){
							%><tr><td><%=listJing.get(0).getId() %></td><td><%=listJing.get(0).getUserName() %></td><td><%=listJing.get(0).getPwd() %></td>
							<td><%=listJing.get(0).getMailbox() %></td><td><%=listJing.get(0).getPhone() %></td><td><%=listJing.get(0).getGrade() %></td></tr><%
						}
					}	
				}
			  %>
		 </table>
		 <% 
		 	out.write("<br/>");
		 	
		 	for(int i=1;i<=countPage;i++){
				//打印页码
				%><a href="checkUsers.jsp?pageNow=<%=i%>">[<%=i%>]</a><%
			}
		 
		 %>
	</center>
</body>
</html>