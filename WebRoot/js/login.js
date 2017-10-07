// JavaScript Document

function code(){
		var code=document.getElementById("code");
		code.src="/Login/code?time="+new Date().getTime();
	}
	function getFocus(){
		var userName=document.getElementById("userName");
		userName.innerHTML="用户名不能为空!";
		userName.style.color="red";
	}
	function getFocus1(){
		var pwd=document.getElementById("pwd");
		pwd.innerHTML="密码不能为空!";
		pwd.style.color="red";
	}
	function getFocus3(){
		var yan=document.getElementById("yan");
		yan.innerHTML="验证码不能为空!";
		yan.style.color="red";
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
	}