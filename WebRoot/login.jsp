<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css" type="text/css"/>
<!-- <script type="text/javascript" src="js/login.js"></script> -->
<script type="text/javascript">
	var u=0;
	function code(){
		var code=document.getElementById("code");
		code.src="/Login/code?time="+new Date().getTime();
	}
	function loseFocus1(){
		var pwd=document.getElementById("pwd");
		var password=document.getElementById("password").value;
		if(password==""){
			pwd.innerHTML="密码不能为空!";
			pwd.style.color="red";
		}else{
			pwd.innerHTML="";
		}
	}
	function loseFocus3(){
		var yan=document.getElementById("yan");
		var code1=document.getElementById("code1").value;
		if(code1==""){
			yan.innerHTML="验证码不能为空!";
			yan.style.color="red";
		}else{
			yan.innerHTML="";
		}
	}
	function loseFocus(id){
		var userName=document.getElementById(id);
		userName.innerHTML="";
	}
	function register(){
		location.href="register.jsp";
	}
	function isTrue(){
		var userName=document.form1.userName.value;
		var pwd=document.form1.pwd.value;
		var code1=document.form1.code1.value;
		if(userName==""){
			alert("用户名不能为空!");
			return false;
		}
		if(pwd==""){
			alert("密码不能为空!");
			return false;
		}
		if(code1==""){
			alert("验证码不能为空!");
			return false;
		}
		if(u==1){
			return false;
		}
	}
	//验证用户名是否存在
	function change(){
		var user=document.getElementById("user").value;
		var userName=document.getElementById("userName");
			if(user==""||user==null){
				userName.innerHTML="用户名不能为空!";
				userName.style.color="red";
			}else{
					userName.innerHTML="";
					var xhr=new XMLHttpRequest();
					xhr.onreadystatechange=function(){
						if(xhr.readyState==4){
							if(xhr.status==200){
								//请求响应成功
								if(xhr.responseText=="-1"){
									//没有该用户
									userName.innerHTML="用户名不存在";
									userName.style.color="red";
									u=1;
								}else{
									//有该用户,sb为1时用户没有头像，显示默认
									var sb=xhr.responseText;
									if(sb!="1"){
										location.reload();
									}
								}
							}
						}
					}
					xhr.open("get","<%=request.getContextPath()%>/registerYanAjaxServlet?flag=login&userName="+user);
					xhr.send(null);
			}
	}
	
</script>

</head>
<body>
	<!-- 登录界面 -->
	<div class="login">
		<div class="tou">
			<%
				if(request.getSession().getAttribute("imageUri")==null){
					%><img src="image/wu.png"/><%
				}else{
					%><img src="<%=request.getSession().getAttribute("imageUri")%>"/><%
					//销毁
					session.invalidate();
				}
			 %>
		</div>
		<form action="<%=request.getContextPath()%>/loginYanServlet" method="post" name="form1" onsubmit="return isTrue()">
			<div class="user">
				<div class="userLeft">
				</div>
				<div class="clear"></div>
				<div class="userRight">
					<input type="text" placeholder="请输入用户名" name="userName" maxlength="10" id="user" onblur="change()"/>
					<span id="userName" style="font-size:10px"></span>
				</div>
			</div>
			<div class="clear"></div>
			<div class="pwd">
				<div class="pwdLeft">
				</div>
				<div class="clear"></div>
				<div class="pwdRight">
					<input type="password" placeholder="请输入密码" name="pwd" maxlength="15" id="password" onblur="loseFocus1()"/>
					<span id="pwd" style="font-size:10px"></span>
				</div>
			</div>
			<div class="clear"></div>
			<div class="code">
				<div class="codeLeft">
				</div>
				<div class="clear"></div>
				<div class="codecentre">
					<input type="text" placeholder="请输入验证码"  name="code1" maxlength="4" id="code1" style="width:140px;" onblur="loseFocus3()"/>
					<span id="yan" style="font-size:10px"></span>
				</div>
				<div class="clear"></div>
				<div class="codeRight">
					<a href="javascript:code()"><img src="/Login/code" id="code"/></a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="remOrfor">
				<div class="rem">
					<input type="checkbox" name="cb" />
				</div>
				<div class="clear"></div>
				<div>
					<a style="font-size:10px;float:left;">记住密码</a>
				</div>
				<div class="clear"></div>
				<div class="register">
					<a  href="register.jsp" style="font-size:10px;text-decoration:none;">立即注册</a>
				</div>
				<div class="clear"></div>
				<div class="for">
					<a href="forgetpwd.jsp" style="font-size:10px;text-decoration:none"/>忘记密码?</a>
				</div>
			</div>
			<div class="clear"></div>	
			<div class="loginButton">
				<input type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录" style="height:35px;width:235px;background-color:#999;"/>
			</div>
			<div class="other">
				<a style="font-size:12px;">-------------使用合作账户登录--------------</a>
			</div>
			<div class="otherApp">
				<div class="appLeft">
					<a href="#"><img src="image/qq.png"/></a>
				</div>
				<div class="clear"></div>
				<div class="appCentre">
					<a href="#"><img src="image/weixin.png"/></a>
				</div>
				<div class="clear"></div>
				<div class="appRight">
					<a href="#"><img src="image/weibo.jpg" width="32px" height="32px"/></a>
				</div>
			</div>
				<!--<input type="button" value="注册" style="width:50px" onclick="register()"/>  -->
		</form>
	</div>
</body>
</html>