<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>管理员</title>
  <link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>'>
  <script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
</head>
<body>
 <ul class="layui-nav">
 <li class="layui-nav-item">
    <a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>
    <dl class="layui-nav-child">
      <dd><a href="javascript:;">修改信息</a></dd>
      <dd><a href="javascript:;">安全管理</a></dd>
      <dd><a href="javascript:;">退了</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item">
    <a href="javascript:;">系统管理</a>
    <dl class="layui-nav-child"> <!-- 二级菜单 -->
      <dd><a href="">资讯模块</a></dd>
      <dd><a href="">番剧模版</a></dd>
      <dd><a href="">论坛版块</a></dd>
    </dl>
  </li>
  <li class="layui-nav-item">
    <a href="">用户管理<span class="layui-badge-dot"></span></a>
  </li>
  <li class="layui-nav-item">
    <a href="">消息中心<span class="layui-badge">9</span></a>
  </li>
  
</ul>
<script>

layui.use('element', function(){
	  var element = layui.element;
	  
	  //…
	});
	</script>
</body>
</html>