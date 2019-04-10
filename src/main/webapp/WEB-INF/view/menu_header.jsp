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
	<a class="fly-logo" href="/guomanwang/common/index">
      <img src='<c:url value="/resources/img/LOGO.png"></c:url>' alt="layui">
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item layui-this">
        <a href="/guomanwang/common/index"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>首页</a>
      </li>
      <li class="layui-nav-item">
        <a href="/guomanwang/common/news"><i class="iconfont icon-iconmingxinganli"></i>资讯</a>
      </li>
      <li class="layui-nav-item">
        <a href="/guomanwang/common/opera"><i class="layui-icon" style="font-size: 24px;">&#xe7ae;</i>番剧</a>
      </li>
			<li class="layui-nav-item">
				<a href="/guomanwang/common/luntan"><i class="iconfont icon-jiaoliu"></i>论坛</a>
			</li>
			<li class="layui-nav-item">
				<a href="/guomanwang/user/user_index"><i class="iconfont icon-touxiang"></i>个人中心</a>
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
        <a href="/guomanwang/common/login">登入</a>
      </li>
      <li class="layui-nav-item">
        <a href="/guomanwang/common/register">注册</a>
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
          <%if(user.getHonor()>=2){%><i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：管理员"></i><%}%>
          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP${user.getGrade()}</i>
          <img src="<c:url value='${user.getHeadurl()}'></c:url>">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="/guomanwang/user/user_setting"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="/guomanwang/user/user_message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息<span class="layui-badge-dot"></span></a></dd>
          <dd><a href="/guomanwang/user/user_home?userId=${user.getUserid() }"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <% if(user.getHonor()>=3){ %>
		  <dd><a href="/guomanwang/common/admin"><i class="layui-icon" style=" font-size: 22px;">&#xe857;</i>后台管理</a></dd>
          <%} %>
          <hr style="margin: 5px 0;">
          <dd><a href="javascript:;" class="loginout" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
      <%} %>
    </ul>
  </div>
  <script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
  <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script> 
  <script>
	layui.config({
	  base: '<c:url value="/resources/res/mods/"></c:url>' //你存放新模块的目录，注意，不是layui的模块目录
	}).use('<c:url value="index"></c:url>'); //加载入口
	
	layui.use(['element','form','layer'], function(){
		var $ = layui.jquery,
		element = layui.element,
		form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer;
		
		var websocket = null;  
	    if ('WebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new WebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//WebSocket对应的地址  
	    }  
	    else if ('MozWebSocket' in window) {  
	        //Websocket的连接  
	        websocket = new MozWebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//SockJS对应的地址  
	    }  
	    else {  
	        //SockJS的连接  
	        websocket = new SockJS("http://localhost:8080/guomanwang/sockjs/socketServer");    //SockJS对应的地址  
	    }  
	    websocket.onopen = onOpen;  
	    websocket.onmessage = onMessage;  
	    websocket.onerror = onError;  
	    websocket.onclose = onClose;  
	  
	    function onOpen(openEvt) {  
	        //alert(openEvt.Data);
	        console.log(location.pathname);
	    }  
	    function onMessage(evt) {
	    	if(location.pathname=="/guomanwang/user/chat"||location.pathname.equals=="/guomanwang/user/user_message"){
	    		$(".chat").first().append('<div class="bubble you">'+evt.data+'</div>');	    		
	    	}
	    	else{
	    		layer.open({
		            type: 1
		            ,offset: 'b'
		            ,id: 'layerDemob'
		            ,content: '<div style="padding: 20px 100px;">'+evt.data+'</div>'
		            ,btn: '关闭'
		            ,btnAlign: 'c' //按钮居中
		            ,shade: 0 //不显示遮罩
		            ,yes: function(){
		              layer.closeAll("page");
		            }
		          }); 
	    	}

	    }  
	    function onError() {  
	    }  
	    function onClose() {  
	    }   
	  
	    window.close = function () {  
	        websocket.onclose();  
	    }
		$(".loginout").click(function(){
        	layer.confirm('确定退出登录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get('/guomanwang/user/logout/',function(data){
                })
                setTimeout(function(){
                    location.reload();
                },500);
                return false;
        	});
		})
	})
  </script>
</body>
</html>