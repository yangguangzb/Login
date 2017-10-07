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
			ph.innerHTML="手机号不能为空"
			ph.style.color="red";
		}
		else if(!reg.test(phone)){
			ph.innerHTML="手机号只能是数字"
			ph.style.color="red";
		}
		else{
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
			mail.innerHTML="邮箱不能为空"
			mail.style.color="red";
		}
		else if(!reg1.test(mailbox)){
			mail.innerHTML="邮箱格式不对"
			mail.style.color="red";
		}
		else{
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