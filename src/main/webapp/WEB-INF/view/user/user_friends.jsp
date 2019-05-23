<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>我的好友</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
  <link rel="stylesheet" type="text/css" href="../resources/css/emotion.css"/>
  <link rel="stylesheet" type="text/css" href="../resources/css/user_friends.css"/>
</head>
<body>
	<jsp:include page="../common/menu_header.jsp"/>
	<input type="hidden" value="${user.getUserid() }" class="loginid">
	<div class="layui-container fly-marginTop fly-user-main">
  	  	  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
	    <li class="layui-nav-item">
	      <a href="user_home?userId=${user.getUserid() }">
	        <i class="layui-icon">&#xe609;</i>
	       	 我的主页
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_index">
	        <i class="layui-icon">&#xe857;</i>
	        	用户中心
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_setting">
	        <i class="layui-icon">&#xe620;</i>
	       	 基本设置
	      </a>
	    </li>
	    <li class="layui-nav-item layui-this">
	      <a href="user_friends">
	        <i class="layui-icon">&#xe770;</i>
	       	我的好友
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_message">
	        <i class="layui-icon">&#xe611;</i>
	        	我的消息
	      </a>
	    </li>
	  </ul>
	  <div class="site-tree-mobile layui-hide">
	    <i class="layui-icon">&#xe602;</i>
	  </div>
	  <div class="site-mobile-shade"></div>
	  <div class="site-tree-mobile layui-hide">
	    <i class="layui-icon">&#xe602;</i>
	  </div>
	  <div class="site-mobile-shade"></div>
  <div class="fly-panel fly-panel-user" pad20>
		<div class="layui-tab layui-tab-card" lay-filter="myfriends">
			<ul class="layui-tab-title" id="friends">
				<c:if test="${empty friends|| friends.size()==0}">
					<li class="layui-this">
		  				<p>你还没有添加任何人为好友哦！</p>
		            </li>
				</c:if>
            	<c:forEach items="${friends }" var="item"  varStatus="status">
	                <c:if test="${status.count==1 }">
		                <li class="person layui-this" lay-id="${item.getUserid() }">
		                    <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
		                    <span class="name">${item.getUsername() }</span>
		                    <i class="layui-icon layui-icon-set" ></i>  
		                     <dl class="layui-nav-child">
						      <dd><button id="deletefriend" class="layui-btn layui-btn-sm layui-btn-danger" data-id="${item.getUserid() }">删除好友</button></dd>
						    </dl>
		                </li>
	                </c:if>
					<c:if test="${status.count!=1 }">
		                <li class="person" lay-id="${item.getUserid() }">
		                    <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
		                    <span class="name">${item.getUsername() }</span>
		                    <i class="layui-icon layui-icon-set" ></i>
		                    <dl class="layui-nav-child">
						      <dd><button id="deletefriend" class="layui-btn layui-btn-sm layui-btn-danger" data-id="${item.getUserid() }">删除好友</button></dd>
						    </dl>
		                </li>
	                </c:if>
	            </c:forEach>
			</ul>
			<div class="layui-tab-content"></div>
			<hr>
			<div class="write">
				<a href="javascript:void(0)" class="write-link smiley"></a>
				<div class="messages" contenteditable/></div>
				<a href="javascript:;" data-id="${ friends[0].getUserid()}" class="write-link send"></a>
			</div>
		</div>
  </div>
</div>
<%@ include file="../common/footer.html"%>
<script type="text/javascript" src="https://cdn.bootcss.com/zepto/1.2.0/zepto.min.js"></script>
<script type="text/javascript" src="../resources/js/chatindex.js"></script>
<script type="text/javascript" src="../resources/js/emotion.js"></script>
<script type="text/javascript">
        $('a.smiley').emotion(); 
        $('.messages').focus();
		//鼠标点击输入框空白处获得焦点
		$('.write').click(function(){
			$('.messages').focus(); 
		})
</script>
</body>
</html>