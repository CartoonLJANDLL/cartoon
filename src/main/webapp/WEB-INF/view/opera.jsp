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
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href="<c:url value='/resources/layuicms/layui/css/layui.css'></c:url>">
	</head>
	<style type="text/css">
		.layui-bg-green,.layui-bg-red{margin:10px auto;width: 100%;height: 50px;text-align: center;padding-top:5px ;}
		.layui-row img{width: 100%;border-radius: 25px;}
		.layui-row img:hover{box-shadow:5px 5px 5px #888888;}
		.layui-row{margin-top: 20px;}
		.layui-col-md2{text-align: center;}
		.layui-carousel img{width:100%;height:100%;}
		h3{margin-top: 5px;}
	</style>
	<body>
	<jsp:include page="menu_header.jsp"/>
		<div class="layui-carousel" id="test1">
		  <div carousel-item>
			<div><img src="<c:url value='/resources/img/tianming.jpg'></c:url>"></div>
			<div><img src="<c:url value='/resources/img/tianyu.png'></c:url>"></div>
			<div><img src="<c:url value='/resources/img/wgj.jpg'></c:url>"></div>
			<div><img src="<c:url value='/resources/img/qsmy.jpg'></c:url>"></div>
		  </div>
		</div>
<!-- 条目中可以是任意内容，如：<img src=""> -->
		<div class="layui-bg-green">
			<h1>热门国番&nbsp;&nbsp;<i class="layui-icon">&#xe669;</i></h1>
		</div>
		<div class="layui-row layui-col-space20 ">
		  <div class="layui-col-md2 layui-col-md-offset1">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		  </div>
		  <div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		  </div>
		  <div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		  </div>
		  <div class="layui-col-md2">
			  <a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			  </a>
		  </div>
		  <div class="layui-col-md2">
		  	<a href="#">
		  	<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
		  	<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
		  	</a>
		  </div>
		</div>
		<div class="layui-row layui-col-space20 ">
		<div class="layui-col-md2 layui-col-md-offset1">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
			<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
			<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-lg6 layui-col-lg-offset6">
				<a href="#" style="font-size:20px;">>more<</a>
			</div>
		</div>
		<br />
		<div class="layui-bg-red">
			<h1>正在热播&nbsp;&nbsp;<i class="layui-icon">&#xe669;</i></h1>
			
		</div>
		<div class="layui-row layui-col-space20 ">
		<div class="layui-col-md2 layui-col-md-offset1">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
			<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
			<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		</div>
		<div class="layui-row layui-col-space20 ">
		<div class="layui-col-md2 layui-col-md-offset1">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
				<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
				<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		<div class="layui-col-md2">
			<a href="#">
			<img src="<c:url value='/resources/img/qsmy.jpg'></c:url>">
			<h3>秦时明月之君临天下&nbsp;&nbsp;<i class="layui-icon" style="font-size: 30px;">&#xe600; | &#xe641;</i></h3>
			</a>
		</div>
		</div>
		<div class="layui-row">
			<div class="layui-col-lg6 layui-col-lg-offset6">
				<a href="#" style="font-size:20px;">>more<</a>
			</div>
		</div>
		<div class="fly-footer" style="text-align:center;width:100%;height:70px;margin-top:20px;">
			<p style="margin:10px auto; "><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a></p> 
			<a href="javascript:;" target="_blank">2018 &copy;刘江 and 李林</a>
		    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
		    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
		    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
		</div>
<script type="text/javascript" src="<c:url value='/resources/layuicms/layui/layui.js'></c:url>"></script>
	<script>
	layui.use('carousel', function(){
	  var carousel = layui.carousel;
	  //建造实例
	  carousel.render({
		elem: '#test1'
		,width: '100%' //设置容器宽度
		,arrow: 'always' //始终显示箭头
		//,anim: 'fade' //切换动画方式
	  });
	});
	</script>
	</body>
</html>
