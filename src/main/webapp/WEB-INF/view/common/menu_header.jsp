<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="guomanwang.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>导航栏</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<meta name="keywords" content="纵横国漫网">
	<link href="/guomanwang/resources/layui/css/layui.css" rel="stylesheet" />
	<link href="/guomanwang/resources/css/global.css" rel="stylesheet" />
	<script src="/guomanwang/resources/layui/layui.js"></script>
	<script src="/guomanwang/resources/js/common.js"></script>
</head>
<body>
	<div class="fly-header layui-bg-black">
		<a class="fly-logo" href="/guomanwang/index">
	      <img src="/guomanwang/resources/img/LOGO.png" width="120px" height="40px" alt="纵横国漫网">
	    </a>
	    <ul class="layui-nav fly-nav layui-hide-xs" lay-filter="demo">
		    <li class="layui-nav-item index">
		        <a href="/guomanwang/index"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>首页</a>
		    </li>
		    <li class="layui-nav-item news">
		        <a href="/guomanwang/news"><i class="iconfont icon-iconmingxinganli"></i>资讯</a>
		    </li>
		    <li class="layui-nav-item opera">
		        <a href="/guomanwang/opera"><i class="layui-icon" style="font-size: 24px;">&#xe7ae;</i>番剧</a>
		    </li>
			<li class="layui-nav-item luntan">
				<a href="/guomanwang/luntan"><i class="iconfont icon-jiaoliu"></i>论坛</a>
			</li>
			<li class="layui-nav-item user">
				<a href="/guomanwang/user/user_index"><i class="iconfont icon-touxiang"></i>个人中心</a>
			</li>
	    </ul>  
	    <ul class="layui-nav fly-nav-user">
	      <% User user=(User)session.getAttribute("user");
			  if(user==null){ %>
	      <li class="layui-nav-item">
	        <a href="/guomanwang/login">登入</a>
	      </li>
	      <li class="layui-nav-item">
	        <a href="/guomanwang/register">注册</a>
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
	          <cite class="layui-hide-xs" id="username" data-id="${user.getUserid() }"><%=user.getUsername() %></cite>
	          <%if(user.getHonor()>=2){%><i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：管理员"></i><%}%>
	          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP${user.getGrade()}</i>
	          <img src="/guomanwang${user.getHeadurl()}">
	        </a>
	        <dl class="layui-nav-child">
	          <dd><a href="/guomanwang/user/user_setting"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
	          <dd><a href="/guomanwang/user/user_message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
	          <dd><a href="/guomanwang/user/user_home?userId=${user.getUserid() }"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
	          <% if(user.getHonor()>=3){ %>
			  <dd><a href="/guomanwang/admin/index"><i class="layui-icon" style=" font-size: 22px;">&#xe857;</i>后台管理</a></dd>
	          <%} %>
	          <hr style="margin: 5px 0;">
	          <dd><a href="javascript:;" class="loginout" style="text-align: center;">退出</a></dd>
	        </dl>
	      </li>
	      <%} %>
	    </ul>
  </div>
</body>
</html>