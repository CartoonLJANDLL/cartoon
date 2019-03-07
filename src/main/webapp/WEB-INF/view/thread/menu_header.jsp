<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="guomanwang.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜单导航栏</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
			.fly-logo img{width:120px;height:40px;}
			a:hover{ text-decoration:none;important!}
			.layui-nav-child{top:60px;important!}
			
</style>
</head>
<body>
	<div class="fly-header layui-bg-black">
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item layui-this">
        <a href="index"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>首页</a>
      </li>
      <li class="layui-nav-item">
        <a href="case/case.html"><i class="iconfont icon-iconmingxinganli"></i>资讯</a>
      </li>
      <li class="layui-nav-item">
        <a href="http://www.layui.com/" target="_blank"><i class="layui-icon" style="font-size: 24px;">&#xe7ae;</i>番剧</a>
      </li>
			<li class="layui-nav-item">
				<a href="/guomanwang/common/luntan" target="_blank"><i class="iconfont icon-jiaoliu"></i>论坛</a>
			</li>
			<li class="layui-nav-item">
				<a href="/guomanwang/user/user_index" target="_blank"><i class="iconfont icon-touxiang"></i>个人中心</a>
			</li>
    </ul>
    
    <ul class="layui-nav fly-nav-user">
      <% User user=(User)session.getAttribute("user");
		  if(user==null){ %>
      <!-- 未登入的状态 -->
      <!-- <li class="layui-nav-item" id="notlogin">
        <a class="iconfont icon-touxiang layui-hide-xs" href="login"></a>
      </li> -->
      <li class="layui-nav-item">
        <a href="login">登入</a>
      </li>
      <li class="layui-nav-item">
        <a href="register">注册</a>
      </li>
      <li class="layui-nav-item layui-hide-xs">
        <a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>
      </li>
      <li class="layui-nav-item layui-hide-xs">
        <a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>
      </li>
      
      <!-- 登入后的状态 -->
	<%}else{%>
      <li class="layui-nav-item" id="islogin">
        <a class="fly-nav-avatar" href="javascript:;">
          <cite class="layui-hide-xs" id="username"><%=user.getUsername() %></cite>
          <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP${sessionScope.user.getGrade()}</i>
          <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="user_setting"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="user_message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
          <dd><a href="user_home"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <% if(user.getGrade()==2){ %>
		  <dd><a href="admin"><i class="layui-icon" style=" font-size: 22px;">&#xe857;</i>后台管理</a></dd>
          <%} %>
          <hr style="margin: 5px 0;">
          <dd><a href="/guomanwang/user/logout/" onclick="if(confirm('确认退出吗？')==false)return false;" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
      <%} %>
    </ul>
  </div>
  <script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script>
	$(".fly-nav-avatar").hover(function(){
		$(".layui-nav-child").fadeIn();	
	});
	$(".fly-nav-user").mouseleave(function(){
		$(".layui-nav-child").fadeOut();	
	});
</script>
</body>
</html>