<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="com.san.dao.Datadao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.san.model.User"%>
<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse:collapse;
	}
	table td{
		width:50px;
		height:30px;
	}
</style>
<script type="text/javascript">
	function change(){
		var isDelete=confirm("您确定要删除吗?");
		return isDelete;
	}
	function gai(){
		
	}	
</script>
</head>
<body>
	<!-- 管理员登录界面 -->
	<center>
	<table border=1px>
		<%
			//每一页的大小
			int pageSize=3;
			//有多少页
			int countPage=0;
			//有多少条记录
			int countLine=0;
			//显示第几页
			int pageNow=1;
			if(request.getParameter("pageNow")!=null){
				//获得删除时是第几页
				pageNow=Integer.parseInt(request.getParameter("pageNow"));
			}
			if(request.getParameter("name")!=null){
				//获得跳转页面是第几页
				String name=request.getParameter("name");
				pageNow=Integer.parseInt(name);
				//out.write("页数"+pageNow);
			}
			Connection conn=DBUtil.getconn();
			Datadao db=new Datadao();
			//查询用户个数
			ResultSet rs=db.numberUser(conn);
			//查询用户信息
			ArrayList<User> list=(ArrayList<User>)db.allUsers(pageSize,pageNow,conn);
			while(rs.next()){
				countLine=rs.getInt(1);
			}
			if(countLine%pageSize==0){
				countPage=countLine/pageSize;
			}else{
				countPage=countLine/pageSize+1;
			}
		 %>
		<tr>
			<td>编号</td><td>用户名</td><td>密码</td><td>邮箱</td><td>手机</td><td>等级</td><td>删除</td><td>修改</td>
		</tr>
		
			<%
				
				if(pageNow!=countPage){
					//完整页
					for(int i=0;i<pageSize;i++){
					%><tr><td><%=list.get(i).getId()%></td><td><%=list.get(i).getUserName()%></td><td><%=list.get(i).getPwd()%></td><td>
					<%=list.get(i).getMailbox()%></td><td><%=list.get(i).getPhone()%></td><td><%=list.get(i).getGrade() %>
					</td><td><a href="<%=request.getContextPath()%>/deletes?id=<%=list.get(i).getId()%>&pageNow=<%=pageNow %>&countLine=<%=countLine %>&countPage=<%=countPage %>&flag=delete" onclick="return change();">删除</a>
					</td><td><a href="modify.jsp?id=<%=list.get(i).getId()%>">修改</a></td></tr><%
					}
				}else{
					//含有缺页的
					//计算最后缺页的记录数
					int m=countLine-(countPage-1)*pageSize;
					for(int i=0;i<m;i++){
					%><tr><td><%=list.get(i).getId()%></td><td><%=list.get(i).getUserName()%></td><td><%=list.get(i).getPwd()%></td><td>
					<%=list.get(i).getMailbox()%></td><td><%=list.get(i).getPhone()%></td><td><%=list.get(i).getGrade() %>
					</td><td><a href="<%=request.getContextPath()%>/deletes?id=<%=list.get(i).getId()%>&pageNow=<%=pageNow %>&countLine=<%=countLine %>&countPage=<%=countPage %>&flag=delete" onclick="return change();">删除</a>
					</td><td><a href="modify.jsp?id=<%=list.get(i).getId()%>">修改</a></td></tr><%
					}
				}
				
				DBUtil.closeConn(conn);
			%>
	</table>
		
		<a href="manageFace.jsp?name=1">首页</a>
		<%
			if(pageNow!=1){
				%><a href="manageFace.jsp?name=<%=pageNow-1 %>">上一页</a><%
			}
		 %>
		<%
			if(countPage>=6){
				if(pageNow<4){
				//显示最开始的6页(总共页数至少6页)
				for(int i=1;i<=6;i++){
					%><a href="manageFace.jsp?name=<%=i%>">[<%=i%>]</a><%
					}
				}
				else{
				if(pageNow>countPage-3){
					//显示最后面的6页
					for(int i=countPage-5;i<=countPage;i++){
						%><a href="manageFace.jsp?name=<%=i%>" >[<%=i%>]</a><%
					}
				}
				else{
					for(int i=pageNow-2;i<=pageNow+3;i++){
						%><a href="manageFace.jsp?name=<%=i%>" >[<%=i%>]</a><%
						}
					}
				}
			}else{
				//页数小于6页的
				for(int i=1;i<=countPage;i++){
					%><a href="manageFace.jsp?name=<%=i%>">[<%=i%>]</a><%
				}
			}
				
		 %>
		 <%
		 	if(pageNow!=countPage){
		 		%><a href="manageFace.jsp?name=<%=pageNow+1 %>">下一页</a><%
		 	}
		  %>
		<a href="manageFace.jsp?name=<%=countPage%>">尾页</a>
		第<span style="color:red"><%=pageNow %></span>页
	</center>
</body>
</html>