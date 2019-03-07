<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>未登录首页</title>
<link href='<c:url value="/resources/css/bootstrap.min.css"></c:url>' rel="stylesheet" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
<link href='<c:url value="/resources/css/menu-header.css"></c:url>' rel="stylesheet" />
</head>
<body>
	<jsp:include page="login_model.jsp"/>
	<nav class="nav navbar-inverse navbar-fixed-top" style="background-color:#ededed ;height: 80px;">
			<div class="container">
				<div class="navbar-header">
					<!--在移动端的时候导航条折叠起来，三横的样式出现，点击该样式可以显示或隐藏导航条上的内容-->
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="notlogin_index.html"><img src='<c:url value="/resources/img/LOGO.png"></c:url>'/></a>
				</div>
				<div id="menu" class="collapse navbar-collapse">
					<ul class="nav navbar-nav ">
						<li><a href="notlogin_index.jsp">首页</a></li>
						<li><a href="#">动画资讯</a></li>
						<li><a href="#">番剧</a></li>
						<li><a href="luntan.jsp">论坛</a></li>
						<li><a href="user.jsp">个人中心</a></li>
					</ul>
					 <div class="text-right" style="margin-top: 25px;">
						 <div id="userid_image">
							 <img src='<c:url value="/resources/img/tianyu.png"></c:url>' class="img-circle"/>
							 <a href="#">${ sessionScope.username }</a>
						 </div>
						 <div id="login">
							 <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#myModal">登录</button>
							 <a href="zhuce"><button type="button" class="btn btn-default">注册</button></a>
						 </div>
					</div>
				</div>
			</div>
		</nav>
		<br />
</body>
</html>