<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="guomanwang.domain.Block,java.util.List,java.util.ArrayList,guomanwang.domain.User,guomanwang.domain.TimeTransformUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="background-color: #e2e2e2;">
<head>
  <meta charset="utf-8">
  <meta name="keywords" content="{{ lay.base.keywords }}">
  <meta name="description" content="{{ lay.base.description }}">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>论坛</title>
  <script src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
  <link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>'>
  <style>
  .header{border-bottom: 1px solid #404553; border-right: 1px solid #404553;}
  </style>
</head>
<body class="fly-full">
<jsp:include page="menu_header.jsp"/>

<div class="fly-case-header">
  <p class="fly-case-year">纵横国漫社区欢迎你！</p>
</div>

<div class="fly-main" style="overflow: hidden;">
  <div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
    	<c:choose>
			<c:when test="${status==0 }">
				<li class="layui-this"><a href="/guomanwang/common/luntan">按默认排名</a></li>
        		<li><a href="/guomanwang/common/luntan_hot" id="selectbythreadnum">按热度排行</a></li>
			</c:when>
			<c:otherwise> 
				<li><a href="/guomanwang/common/luntan">按默认排名</a></li>
        		<li class="layui-this"><a href="/guomanwang/common/luntan_hot" id="selectbythreadnum">按热度排行</a></li>
			</c:otherwise>
		</c:choose> 
    </ul>
  </div>
          <ul class="fly-case-list">
             <c:forEach items="${blocks }" var="item"  varStatus="status">
					<li data-id="123">
			         <a  class="fly-case-img" href="/guomanwang/thread/block_index?blockId=${item.getId() }" > 
				        <img src='<c:url value="${item.getphoto() }"></c:url>' alt="${item.getName() }">
				        <cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>
				     </a>
				     <h2><a href="http://fly.layui.com/" target="_blank" ><c:out value="${block.name}" /><c:out value="${block.id}" />${item.getName() }</a></h2>
				     <p class="fly-case-desc">
					     	<c:choose>
						    	<c:when test="${!empty item.getAbstracts()}">
						    		${item.getAbstracts() }
						    	</c:when>
						    	<c:otherwise> 
							     	该主题板块暂无简介，等待你的探索发现！
							   	</c:otherwise>
						    </c:choose>
					   </p>
					    
				     <div class="fly-case-info">
					        <!--  <a href="../user/home.html" class="fly-case-user" target="_blank"><img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
					        <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">最近发帖</span> 
					        <c:choose>
						    	<c:when test="${!empty item.getlast_time()}">
						    		${item.getlast_time() }
						    	</c:when>
						    	<c:otherwise> 
							     	暂无发帖！
							   	</c:otherwise>
						    </c:choose>
					        </p>
					        <p>总共<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise" style=" padding:0 5px; color: #01AAED;">${item.getThreadnum() }</a>个贴子</p>
				     </div>
				  </li> 
			</c:forEach>
		</ul>
		 当前第：${page.pageNum}页，共${page.pages}页，共有${page.total}个主题版块
  <!-- <blockquote class="layui-elem-quote layui-quote-nm">暂无数据</blockquote> -->
   <div style="text-align: center;">
    <div class="laypage-main">
    <c:choose>
		<c:when test="${status==0 }">
			 <c:if test="${page.pageNum!=1}">
		    	<a href="/guomanwang/common/luntan?pn=${page.pageNum-1 }">上一页</a>
		     </c:if>
		     <c:forEach items="${page.navigatepageNums }" var="page_num" varStatus="status">
		        <a href="/guomanwang/common/luntan?pn=${ page_num}" class='<c:if test="${page_num==page.pageNum }">laypage-curr</c:if>'>${ page_num}</a>
		     </c:forEach>
			 <c:if test="${page.pageNum!=page.pages}">
		    	<a href="/guomanwang/common/luntan?pn=${page.pageNum+1 }">下一页</a>
		     </c:if> 
		</c:when>
		<c:otherwise> 
			 <c:if test="${page.pageNum!=1}">
		    	<a href="/guomanwang/common/luntan_hot?pn=${page.pageNum-1 }">上一页</a>
		     </c:if>
		     <c:forEach items="${page.navigatepageNums }" var="page_num" varStatus="status">
		        <a href="/guomanwang/common/luntan_hot?pn=${ page_num}" class='<c:if test="${page_num==page.pageNum }">laypage-curr</c:if>'>${ page_num}</a>
		     </c:forEach>
			 <c:if test="${page.pageNum!=page.pages}">
		    	<a href="/guomanwang/common/luntan_hot?pn=${page.pageNum+1 }">下一页</a>
		     </c:if> 
		</c:otherwise>
	</c:choose>
    </div>
  </div>
</div>
<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
</body>
</html>