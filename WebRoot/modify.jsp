<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="com.san.dao.Datadao"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function change(){
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
		document.getElementById("mailbox").value="";
		//document.getElementById("phone").value="";
		//document.getElementById("grade").value="";
		document.form1.phone.value="";
	}
</script>
</head>
<body>
	<%--管理员修改用户信息界面 --%>
	<%
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		Connection conn=null;
		ResultSet rs=null;
		conn=DBUtil.getconn();
		Datadao db=new Datadao(); 
		rs=db.chaById(id,conn);
		String userName="";
		String password="";
		String mailbox="";
		int phone=0;
		int grade=0;
		while(rs.next()){
			 userName=rs.getString(2);
			 password=rs.getString(3);
			 mailbox=rs.getString(4);
			 phone=rs.getInt(5);
			 grade=rs.getInt(6);
		}
	 %>
	<center>
		<h3>修改用户信息</h3>
		<form action="<%=request.getContextPath()%>/deletes?flag=modify" method="post" name="form1">
			<table>
				<tr><td>编&nbsp;&nbsp;号:</td><td><input type="text" readonly="readonly" maxlength="15" name="id" id="id" value="<%=id%>"/></td></tr>
				<tr><td>用户名:</td><td><input type="text" maxlength="15" name="userName" id="userName" value="<%=userName%>"/></td></tr>
				<tr><td>密&nbsp;&nbsp;码:</td><td><input type="text" id="password" maxlength="15" name="password" value="<%=password%>"/></td></tr>
				<tr><td>邮&nbsp;&nbsp;箱:</td><td><input type="text" id="mailbox" maxlength="15" name="mailbox" value="<%=mailbox%>"/></td></tr>
				<tr><td>手&nbsp;&nbsp;机:</td><td><input type="text" id="phone" maxlength="15" name="phone" value="<%=phone%>"/></td></tr>
				<tr><td>等&nbsp;&nbsp;级:</td><td><input type="text" id="grade" maxlength="15" name="grade" value="<%=grade%>"/></td></tr>
				<tr>
					<td><input type="reset" value="重置"/></td>
					<td><input type="submit" value="修改"/></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>