<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>注册页面</title>
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<style type="text/css">
	tr{line-height:60px;}
	th{font-size: large;}
	label{color:red;font-size: small;}
</style>
</head>
<body>
		<jsp:include page="header.jsp"/>
		<form action="/guomanwang/user/register" method="post">
		<fieldset>
			<legend>注册页面</legend>
				<table style="margin: 120px auto;border:solid balck">
					<tr>
						<th colspan="3" style="text-align: center;background-color:#f2f2f2;">欢迎注册注册纵横国漫网!</th>
					</tr>
					<tr>
						<td colspan="3">
							<div class="alert alert-danger alert-dismissable" style="display:none;bottom:0;"> 
							 <button type="button" class="close" data-dismiss="alert"
									aria-hidden="true">
								&times;
							 </button>
							 账户或者密码错误！请重新输入。
						  </div>
			  			</td>
					</tr>
					<tr>
						<td>用户名：</td>
						<td><input type="text" class="form-control" id="username" name="username" placeholder="请输入你想使用的用户名"></td>
						<td><label id="username_err">可以包含字母、数字、下划线，注册后不可修改!</label></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" class="form-control" name="password" id="password" placeholder="请输入密码"></td>
						<td><label id="password_err">第一位为英文字母，长度为6-15之间。</label></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input type="password" class="form-control" name="confirm_password" id="confirm_password" placeholder="请再次输入密码"></td>
						<td><label id="confirm_password_err">请保证两次输入的密码一致</label></td>
					</tr>
					<tr>
						<td>手机号：</td>
						<td><input type="text" class="form-control" name="cellphone" id="cellphone" placeholder="请输入注册手机号"></td>
						<td><button type="button" class="btn btn-primary" id="get_code">获取验证码</button></td>
					</tr>
					<tr>
						<td>验证码：</td>
						<td><input type="text" class="form-control" name="vali" size="5" id="vali" placeholder="请输入短信验证码"></td>
						<td><label id="code_err">请输入手机接收到的四位数字验证码</label></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="agree" checked="checked">我已经阅读并同意<a href="#">《纵横国漫论坛注册协议》</a></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><button type="submit" class="btn btn-primary btn-block">注册</button></td>
						<td></td>
					</tr>
				</table>
			</fieldset>
			</form>
			<br />
			
			
			<div class="panel-footer text-center">
				<p>商务合作|关于我们|服务条款|信息反馈|联系我们</p>
				<p>©2018 纵横国漫网 版权所有</p>
			</div>
	<script type="text/javascript" src='<c:url value="/resources/js/yanzheng.js"></c:url>'></script>
	</body>
</html>
