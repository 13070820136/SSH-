<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>

<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>


<div class="login">
    <div class="box png">
		<%--<div class="logo png"></div>--%>
			<div class="input">
				<div style="text-align: center;font-size: 30px;color:#CCCCCC;padding-top: 40px;">洗浴后台管理</div>
				<form action="mgrlogin" method="post" name="form1" id="form1">
			<div class="log">
				<div class="name">
					<label>用户名</label><input type="text" class="text" id="value_1" placeholder="用户名" name="name"  tabindex="1"/>
				</div>
				<div class="pwd">
					<label>密码 </label><input type="password" class="text" id="value_2" placeholder="密码" name="password"  tabindex="2"/>
					<input type="submit" class="submit" tabindex="3" value="登录"/>
					<div class="check"></div>
				</div>
				<div class="tip"></div>
			</div>
			</form>
		</div>
	</div>
    <div class="air-balloon ab-1 png"></div>
	<div class="air-balloon ab-2 png"></div>
    <div class="footer"></div>
</div>

<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/fun.base.js"></script>
<script type="text/javascript" src="js/script.js"></script>


<!--[if IE 6]>
<script src="js/DD_belatedPNG.js" type="text/javascript"></script>
<script>DD_belatedPNG.fix('.png')</script>
<![endif]-->

</body>
</html>