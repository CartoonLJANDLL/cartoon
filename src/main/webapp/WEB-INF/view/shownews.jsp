<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资讯页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href="<c:url value='/resources/layuicms/layui/css/layui.css'></c:url>">
<style type="text/css">
	.layui-row{padding-left: 20px;margin-top: 20px;}
	.layui-row li{line-height: 30px;list-style-type:circle;margin-left:16px;}
</style>
</head>
	<body>
		<div class="layui-row">
			<blockquote class="site-text layui-elem-quote">
				<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe60a;</i>
				<label style="font-size: 20px;">最新资讯TOP20</label>
			</blockquote>
			<ul class="flow-default"  id="LAY_demo">
				<c:forEach items="${news }" var="item"  varStatus="status">
				    <li>
					<div class="layui-col-md9">
					<c:choose>
						<c:when test="${item.getCompanyname()=='玄机科技' or item.getCompanyname()=='娃娃鱼动画' }">
							<a href="${item.getUrl() }" target="_blank">${item.getTitle() }</a>
						</c:when>
						<c:otherwise>
							<a href="${item.getUrl() }" target="newsbody">${item.getTitle() }</a>
						</c:otherwise>
					</c:choose>
						
					</div>
					<div class="layui-col-md3">
						<span style="text-align: right;">>>${item.getCompanyname() } |</span>
						<span id="newstime">${item.getTime() }</span>
					</div>
					<hr/>
				</li>
				</c:forEach>
			</ul>
		</div>
	</body>
	<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
	<script>
	layui.use(['util', 'laydate', 'layer'], function(){
	  var util = layui.util
	  ,laydate = layui.laydate
	  ,layer = layui.layer;
	  
	})
	</script>
</html>