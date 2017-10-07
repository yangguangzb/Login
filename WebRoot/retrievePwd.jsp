<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="com.san.model.User"%>
<%@page import="java.util.Map"%>
<%@page import="com.san.dao.Datadao"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.pwd{
		width:300px;
		height:400px;
		margin:auto;
		border:1px solid #999;
		padding-left:20px;
	}
	.a{
		height:50px;
		margin-top:230px;
		padding-left:150px;
	}
	.a a{
		color:red;
		text-decoration:none;
		font-size:15px;
	}
	
</style>
</head>
<body>
	<!-- 找回密码 -->
	 <div class="pwd">
	 	<div class="a">
	 		<a href="login.jsp" >返回登录界面</a>
	 	</div>
	 </div>
</body>
</html>








