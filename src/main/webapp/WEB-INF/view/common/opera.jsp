<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番剧</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="referrer" content="no-referrer"/>
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/css/opera.css"></c:url>' rel="stylesheet" />
</head>
	<body>
	<jsp:include page="menu_header.jsp"/>
		<div class="layui-carousel" id="test1">
		  <div carousel-item>
			<div>
				<a href="https://www.iqiyi.com/v_19rsnl31fc.html"> <img src="http://pic0.iqiyipic.com/common/lego/20190522/1a4735422da84294b8d2eef18533a443.jpg?src=focustat_4_20130527_15"></a>
			</div>
			<div>
				<a href="https://www.iqiyi.com/v_19rsniyfck.html"><img src="http://pic1.iqiyipic.com/common/lego/20190523/fa2f64014254423ca2537378f82a50f0.jpg?src=focustat_4_20130527_14"></a>
			</div>
			<div>
				<a href="https://www.iqiyi.com/v_19rsm8l7e8.html"><img src="http://pic1.iqiyipic.com/common/lego/20190524/f213f1e9a55d4a59b50779d6466d4bc4.jpg?src=focustat_4_20130527_10"></a>
			</div>
		  </div>
		</div>
		<!-- 条目中可以是任意内容，如：<img src=""> -->
		<div>
			<blockquote class="layui-elem-quote main_btn">
				<div class="layui-row layui-form" >
					<div class="layui-col-lg3 sort">
						<button class="layui-btn layui-btn-sm layui-btn-danger">排序</button>
						<input type="radio" name="sort" value="" title="默认" checked>
						<input type="radio" name="sort" value="最新" title="最新">
						<input type="radio" name="sort" value="最热" title="最热">
					</div>
					<div class="layui-col-lg4 type">
						<button class="layui-btn layui-btn-sm layui-btn-danger">类型</button>
						<input type="radio" name="type" value="" title="全部" checked>
						<input type="radio" name="type" value="玄幻" title="玄幻">
						<input type="radio" name="type" value="武侠" title="武侠">
						<input type="radio" name="type" value="冒险" title="冒险">
						<input type="radio" name="type" value="搞笑" title="搞笑">
					</div>
					<div class="layui-col-lg3 status">
						<button class="layui-btn layui-btn-sm layui-btn-danger">状态</button>
						<input type="radio" name="status" value="-1" title="全部" checked>
						<input type="radio" name="status" value="1" title="已完结">
						<input type="radio" name="status" value="0" title="未完结">
					</div>
					<div class="layui-col-lg2 layui-form">
						<div class="layui-inline">
							<input placeholder="输入番剧关键字搜索" required lay-verify="required" name="keywords" autocomplete="off" id="searchinput" class="layui-input">
						</div>
						<button class="layui-btn" lay-submit lay-filter="search">
							<i class="layui-icon layui-icon-search"></i>
						</button>
					</div>
				</div>
			</blockquote>
		</div>
		<div class="layui-bg-green">
			<marquee class="noticeText ng-binding" direction="left" behavior="alternate" scrollamount="100" scrolldelay="1000" width="100%" onmouseover="this.stop();" onmouseout="this.start();"  style="width: 100%;">
				<p>本站声明：本网站下所有的番剧皆来源网络，仅作学习用途。</p>
			</marquee>
		</div>
		<div class="layui-container" style="width:90%;height:100%;">
			<div class="layui-row layui-col-space20 " id="result"></div>
			<div id="pager"></div>
		</div>
		<%@ include file="../common/footer.html"%>
		<script src="/guomanwang/resources/js/opera.js"></script>
	</body>
</html>
