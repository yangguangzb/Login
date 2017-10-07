<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.san.model.User"%>
<%@page import="com.san.dao.Datadao"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var time=4;
	var closer;
	closer=setInterval(
		function(){
			var five=document.getElementById("five");
			if(time>0){
				five.innerHTML=time;
				time--;
			}else{
				//关闭定时器
				clearInterval(closer);
				location.href="login.jsp";
			}	
		},
		1000
	);
</script>
</head>
<body>
	<!-- 注册成功,跳转到该页面 -->
	
	<%
		request.setCharacterEncoding("UTF-8");
		%>恭喜您,注册成功! <span id="five" style="color:red">5</span>秒后跳回到登录界面.<%
	 %>
</body>
</html>