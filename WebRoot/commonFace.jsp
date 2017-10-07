<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function change(){
		var newPwd1=document.getElementById("newPwd1").value;
		var newPwd2=document.getElementById("newPwd2").value;
		var oldPwd=document.getElementById("oldPwd").value;
		if(oldPwd==""){
			alert("请输入旧密码");
			return false;
		}
		if(newPwd2==""||newPwd1==""){
			alert("请输入新密码");
			return false;
		}
		if(newPwd2!=newPwd1){
			alert("两次输入的密码不一致!");
			return false;
		}
	}
	function checkOld(){
		//验证输入的旧密码是否正确
		var oldPwd=document.getElementById("oldPwd").value;
		if(oldPwd!=""){
			var xhr=new XMLHttpRequest();
			var checkOld=document.getElementById("checkOld");
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						if(xhr.responseText=="0"){
							checkOld.innerHTML="密码不正确";
							checkOld.style.color="red";
						}else{
							checkOld.innerHTML="";						
						}
					}				
				}
			}
			xhr.open("post","<%=request.getContextPath()%>/deletes?flag=checkOld&oldPwd="+oldPwd);
			xhr.send(null);
		}
	}
</script>
</head>
<body>
	<!-- 普通用户登录界面 -->
	<%
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("cheng")!=null){
			String m=request.getParameter("cheng");
			if("1".equals(m)){
				out.write("修改成功");
			}else if("0".equals(m)){
				out.write("修改失败");
			}
		}
		
	 %>
	<center>
		<!-- 修改密码 -->
		<form action="<%=request.getContextPath()%>/deletes?newpassword=xiugai" method="post" onsubmit="return change()">
			<input type="text" placeholder="请输入旧密码" maxlength="15" name="oldPwd" id="oldPwd" onblur="checkOld()"/>
			<span id="checkOld" style="font-size:10px;color:red;"></span>
			<br/><br/>
			<input type="password" placeholder="请输入新密码" maxlength="15" name="newPwd1" id="newPwd1"/><br/><br/>
			<input type="password" placeholder="再次输入密码" maxlength="15" name="newPwd2" id="newPwd2"/><br/><br/>
			<input type="reset" value="重置"/>
			<input type="submit" value="修改"/>
		</form>
	</center>
</body>
</html>