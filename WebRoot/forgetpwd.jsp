<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.san.utils.DBUtil"%>
<%@page import="com.san.model.User"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.for{
		width:600px;
		margin:0 auto;
		text-align:center;
		
	}
</style>
<script type="text/javascript"><!--
	function forget(){
		//获得输入的id或者是邮箱地址
		var idormail=document.form1.Id.value;
		
		if(idormail==""||idormail==null){
			alert("请输入手机号(邮箱地址)!");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="for">
		<form action="<%=request.getContextPath()%>/retrievePwdServlet" method="post" name="form1" onsubmit="return forget()">
			<a>请输入您的手机号或邮箱地址：</a><br/><br/>
			<input type="text" name="Id" id="Id" /><br/><br/>
			<input type="submit" name="idormail" value="查看密码信息"/><br/><br/>
		</form>
	</div>
</body>
</html>