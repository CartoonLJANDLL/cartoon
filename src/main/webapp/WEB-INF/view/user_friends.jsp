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
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
	.avatar img{display: block; width: 45px; height: 45px; margin-left:20px;padding:0; border-radius: 2px;}
	.person img{width: 40px; height: 40px; margin-left:20px;padding:0; border-radius: 10px;}
	.person .name{color:red;}
	.person .layui-nav-child{top:42px;}
	.right{padding-left:20px;}
	.right .chat::-webkit-scrollbar {
        display: none;
    }
	.right .chat{height:500px;overflow:scroll;}
	:root {
	  --white: #fff;
	  --black: #000;
	  --bg: #f8f8f8;
	  --grey: #999;
	  --dark: #1a1a1a;
	  --light: #e6e6e6;
	  --wrapper: 1000px;
	  --blue: #00b0ff;
	}
	.right .bubble {
	  position: relative;
	  font-size: 16px;
	  display: inline-block;
	  clear: both;
	  margin-bottom: 8px;
	  padding: 13px 14px;
	  vertical-align: top;
	  border-radius: 5px;
	}
.right .bubble:before {
    position: absolute;
    top: 19px;
    display: block;
    width: 8px;
    height: 6px;
    content: '\00a0';
    -webkit-transform: rotate(29deg) skew(-35deg);
    transform: rotate(29deg) skew(-35deg);
}
.right .bubble.you {
  float: left;
  color: var(--white);
  background-color: var(--blue);
  align-self: flex-start;
  -webkit-animation-name: slideFromLeft;
          animation-name: slideFromLeft;
}
.right .bubble.you:before {
    left: -3px;
    background-color: var(--blue);
}
.right .bubble.me {
  float: right;
  color: var(--dark);
  background-color: #eceff1;
  align-self: flex-end;
  -webkit-animation-name: slideFromRight;
          animation-name: slideFromRight;
}
.right .bubble.me:before {
  right: -3px;
  background-color: #eceff1;
}
.right .write {
	display:inline-block;
    bottom: 29px;
    left: 30px;
    height: 42px;
    padding-left: 8px;
    border: 1px solid var(--light);
    background-color: #eceff1;
    width: calc(100% - 58px);
    border-radius: 5px;
}
.right .write .write-link.smiley:before {
  display: inline-block;
  float: left;
  width: 20px;
  height: 42px;
  content: '';
  background-image: url("../resources/img/smiley.png");
  background-repeat: no-repeat;
  background-position: center;
}
.right .write .write-link.send:before {
  display: inline-block;
  float: right;
  width: 20px;
  height: 42px;
  margin-right: 11px;
  content: '';
  background-image: url("../resources/img/send.png");
  background-repeat: no-repeat;
  background-position: center;
}
.right .write input {
  font-size: 16px;
  float: left;
  height: 40px;
  padding: 0 10px;
  color: var(--dark);
  border: 0;
  outline: none;
  background-color: #eceff1;
  font-family: 'Source Sans Pro', sans-serif;
  font-weight: 400;
}
</style>
</head>
<body>
	<jsp:include page="menu_header.jsp"/>
	<input type="hidden" value="${user.getUserid() }" class="loginid">
	<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="user_home?userId=${user.getUserid() }">
        <i class="layui-icon">&#xe609;</i>
       	 我的主页
      </a>
    </li>
    <li class="layui-nav-item ">
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
		                     <dl class="layui-nav-child">
						      <dd><button id="deletefriend" class="layui-btn layui-btn-sm layui-btn-danger" data-id="${item.getUserid() }">删除好友</button></dd>
						    </dl>
		                </li>
	                </c:if>
					<c:if test="${status.count!=1 }">
		                <li class="person" lay-id="${item.getUserid() }">
		                    <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
		                    <span class="name">${item.getUsername() }</span>
		                    <dl class="layui-nav-child">
						      <dd><button id="deletefriend" class="layui-btn layui-btn-sm layui-btn-danger" data-id="${item.getUserid() }">删除好友</button></dd>
						    </dl>
		                </li>
	                </c:if>
	            </c:forEach>
			</ul>
			<div class="layui-tab-content">
			</div>
		</div>
  </div>
</div>

<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2019 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
<script  src="../resources/js/chatindex.js"></script>
</body>
</html>