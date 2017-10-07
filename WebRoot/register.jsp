<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.san.utils.DBUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" >
	var u=0;
	var s=0;
	var y=0;
	var user;
	var ph;
	var mail;
	var pw;
	//用户名失去焦点
	function loseFocus1(){
		var userName=document.getElementById("userName").value;
		user=document.getElementById("user");
		if(userName==""||userName==null){
			user.innerHTML="用户名不能为空"
			user.style.color="red";
		}else{
			user.innerHTML="";
			//输入的用户名不为空
			var xhr=new XMLHttpRequest();
			//处理事件
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						//请求与响应成功
						if(xhr.responseText=="-1"){
							//用户名已存在
							user.innerHTML="用户名已存在";
							user.style.color="red";
							u=1;
						}else{
							//user.innerHTML="<img src='image/dui.png' width='16px'>";
							u=0;
						}					
					}
				}
			}
			//建立连接
			xhr.open("get","<%=request.getContextPath()%>/registerYanAjaxServlet?flag=register&userName="+userName);
			//发送请求
			xhr.send(null);
		}
	}
	//手机号失去焦点
	function loseFocus2(){
		var phone=document.getElementById("phone").value;
		ph=document.getElementById("ph");
		var reg=/^[\d]+$/;
		if(phone==""||phone==null){
			ph.innerHTML="手机号不能为空";
			ph.style.color="red";
		}
		else if(!reg.test(phone)&&phone!=null){
			ph.innerHTML="";
			ph.innerHTML="手机号只能是数字"
			ph.style.color="red";
		}
		else{
			ph.innerHTML="";
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						if(xhr.responseText=="-1"){
							ph.innerHTML="手机号已存在";
							ph.style.color="red";
							s=1;
						}else{
							//ph.innerHTML="<img src='image/dui.png' width='16px'>";
							ph.innerHTML="";
							s=0;
						}					
					}
				}
			}
			xhr.open("get","<%=request.getContextPath()%>/registerYanAjaxServlet1?phone="+phone);
			xhr.send(null);
		}
	}
	//密码失去焦点
	function loseFocus3(){
		var pwd=document.getElementById("pwd").value;
		pw=document.getElementById("pw");
		if(pwd==""||pwd==null){
			pw.innerHTML="密码不能为空";
			pw.style.color="red";
		}else{
			pw.innerHTML="";
			//pw.innerHTML="<img src='image/dui.png' width='16px'>";
		}
	}
	//邮箱失去焦点
	function loseFocus4(){
		var mailbox=document.getElementById("mailbox").value;
		mail=document.getElementById("mail");
		var reg1=/^[A-z0-9_-]+\@[A-z0-9]+\.[A-z]+$/;
		if(mailbox==""||mailbox==null){
			mail.innerHTML="邮箱不能为空";
			mail.style.color="red";
		}
		else if(!reg1.test(mailbox)&&mailbox!=null){
			mail.innerHTML="";
			mail.innerHTML="邮箱格式不对"
			mail.style.color="red";
		}
		else{
			mail.innerHTML="";
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						if(xhr.responseText=="-1"){
							mail.innerHTML="邮箱已存在";
							mail.style.color="red";
							y=1;
						}else{
							//mail.innerHTML="<img src='image/dui.png' width='16px'>";
							y=0;
							mail.innerHTML="";
						}					
					}
				}
			}
			xhr.open("get","<%=request.getContextPath()%>/registerYanAjaxServlet2?mailbox="+mailbox);
			xhr.send(null);
		}
	}
	function ab(){
		var phone=document.form1.phone.value;
		var pwd=document.form1.pwd.value;
		var mailbox=document.form1.mailbox.value;
		var userName=document.form1.userName.value;
		//判断手机号,用户名,等不能为空
		if(userName==""||userName==null){
			alert("用户名不为空");
			return false;
		}
		if(phone==""||phone==null){
			alert("手机号不为空");
			return false;
		}
		if(pwd==""||pwd==null){
			alert("密码不为空");
			return false;
		}
		if(mailbox==""||mailbox==null){
			alert("邮箱不为空");
			return false;
		}
		//判断手机号只能是数字,邮箱的规则
		var reg=/^[\d]+$/;
		if(!reg.test(phone)){
			//alert("手机号格式不对!");
			return false;
		}
		var reg1=/^[A-z0-9_-]+\@[A-z0-9]+\.[A-z]+$/;
		if(!reg1.test(mailbox)){
			//alert("邮箱格式不正确!");
			return false;
		}
		if(u==1||s==1||y==1){
			return false;
		}
	}
	//重置时清空span内容
	function spanNei(){
		user.innerHTML="";
		pw.innerHTML="";
		ph.innerHTML="";
		mail.innerHTML="";
	}
</script>
<link rel="stylesheet" href="css/register.css" type="text/css"/>
</head>
<body>
	<!-- 注册界面 -->
	<div class="reg">
		<div class="tou">
			<img src="image/wu.png"/>
		</div>
		<form action="<%=request.getContextPath()%>/registerYanServlet" method="post" name="form1" onsubmit="return ab()">
			<div class="user">
				<div class="userLeft">
				</div>
				<div class="clear"></div>
				<div class="userRight">
					<input type="text" placeholder="请输入用户名" name="userName" id="userName" maxlength=10 onblur="loseFocus1()"/>
					<span id="user" style="font-size:10px"></span>
				</div>
			</div>
			<div class="clear"></div>
			<!-- 手机号 -->
			<div class="phone">
				<div class="phoneLeft">
				</div>
				<div class="clear"></div>
				<div class="phoneRight">
					<input type="text" placeholder="请输入手机号" name="phone" id="phone" maxlength=15 onblur="loseFocus2()"/>
					<span id="ph" style="font-size:10px"></span>
				</div>
			</div>
			<div class="clear"></div>
			<!-- 密码 -->
			<div class="pwd">
				<div class="pwdLeft">
				</div>
				<div class="clear"></div>
				<div class="pwdRight">
					<input type="password" placeholder="请输入密码" name="pwd" maxlength=15 id="pwd" onblur="loseFocus3()"/>
					<span id="pw" style="font-size:10px"></span>
				</div>
			</div>
			<div class="clear"></div>
			<!-- 邮箱 -->
			<div class="mailbox">
				<div class="mailboxLeft">
				</div>
				<div class="clear"></div>
				<div class="mailboxRight">
					<input type="text" placeholder="请输入邮箱" name="mailbox" maxlength=15 id="mailbox" onblur="loseFocus4()"/>
					<span id="mail" style="font-size:10px"></span>
				</div>
			</div>
			<div class="registerButton">
				<input type="submit" value="注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册" style="height:35px;width:235px;background-color:#999;"/>
			</div>
			<div class="chongButton">
				<input type="reset" value="重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;置" onclick="spanNei()" style="height:35px;width:235px;background-color:#999;"/>
			</div>
		</form>
	</div>
</body>
</html>