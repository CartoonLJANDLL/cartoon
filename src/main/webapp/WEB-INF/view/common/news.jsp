<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资讯</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href="resources/layuicms/css/index.css">
</head>
<body class="main_body">
<jsp:include page="menu_header.jsp"/>
	<div class="layui-layout layui-layout-admin">
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div style="margin:20px auto;text-align:center;">
				<h2>动画公司 | 网站</h2>
			</div>
			<!-- 搜索 -->
			<div class="layui-form component">
				<input type="text" name="serchnews" id="newskey" required lay-verify="required" placeholder="请输入资讯关键字搜索" autocomplete="off" class="layui-input"> 
				<button lay-submit lay-filter="searchnews">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-this">
						<a href='news'><cite>资讯首页</cite></a>
					</li>
					<c:forEach items="${companies }" var="item"  varStatus="status">
						<li class="layui-nav-item">
							<a href="samecompany?companyid=${item.getId()}" target="newsbody">
								<cite>${item.getName()}</cite>
							</a>
						</li>   
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- 移动导航 -->
		<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
		<div class="site-mobile-shade"></div>
		<!-- 资讯主体内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe name="newsbody" src="shownews"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p><span>copyright @2018 纵横国漫网</span>　　
			<a id="contectus" class="layui-btn layui-btn-danger layui-btn-sm">联系我们</a></p>
		</div>
	</div>
	<script type="text/javascript" src="/guomanwang/resources/js/news.js"></script>
</body>
</html>