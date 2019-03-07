<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户中心</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu_header.jsp"/>
	<div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="user_home?userId=${user.getUserid() }">
        <i class="layui-icon">&#xe609;</i>
       	 我的主页
      </a>
    </li>
    <li class="layui-nav-item layui-this">
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
    <li class="layui-nav-item">
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
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖<span class="layui-badge">${threadlist.size()}</span></li>
       
        <li data-type="dianzan" data-url="/collection/find/" lay-id="dianzan">我点赞的帖<span class="layui-badge">${threadlist.size()}</span></li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <ul class="mine-view jie-row">    
			<c:if test="${empty threadlist|| threadlist.size()==0}">
				<li>
	  				<p>你还没有发帖哦！</p>
	  				<a class="mine-edit" href="/guomanwang/common/add">发帖</a>
	            </li>
			</c:if>
          <c:forEach items="${threadlist }" var="item"  varStatus="status">
		    <li>
			    <c:choose>  
				    <c:when test="${item.getStatus()==1}">  
				         <span class="layui-badge layui-bg-red">精华</span>
				    </c:when>  
				    <c:when test="${item.getStatus()==2}">  
				       <span class="layui-badge layui-bg-red">热帖</span>
				    </c:when>  
				    <c:when test="${item.getStatus()==3}">  
				       <span class="layui-badge layui-bg-red">公告</span>
				    </c:when>
				    <c:otherwise>
				    	<span class="layui-badge layui-bg-red">新帖</span>
				    </c:otherwise>   
				</c:choose>  
              <a class="jie-title" href="/guomanwang/commit/allusercommits?threadId=${item.getId()}">${item.getTitle()}</a>
              <i>${item.getTime()}</i>
              <a class="mine-edit" href="">编辑</a>
              <a class="mine-edit" href="javascript:;" style="background-color:red;" onclick="layer.confirm('确认删除?此操作无法撤销', {icon: 3, title:'提示'}, 
            		  function(index){layer.close(index); location.href='/guomanwang/user/deletethread?id=${item.getId()}';  })">删除</a>
              <em>回复数：${item.getCommitNumber()} | 浏览量：${item.getGreatNum()}</em>
            </li>
		</c:forEach>
          </ul>
          <div id="LAY_page"></div>
        </div>
        <div class="layui-tab-item">
          <ul class="mine-view jie-row">
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">的凯撒后来看到</a>
              <i>收藏于23小时前</i>  </li>
          </ul>
          <div id="LAY_page1"></div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script src='<c:url value="/resources/js/haha.js"></c:url>'></script>
<script>
layui.config({
  base: '<c:url value="/resources/res/mods/"></c:url>' //你存放新模块的目录，注意，不是layui的模块目录
}).use('<c:url value="index"></c:url>'); //加载入口
</script>
</body>
</html>