<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>未登录首页</title>
<link href='<c:url value="/resources/css/bootstrap.min.css"></c:url>' rel="stylesheet" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
<link href='<c:url value="/resources/css/menu-header.css"></c:url>' rel="stylesheet" />
<style type="text/css">
			body {
			  padding: 0;
			  margin: 0;
			}

			.row img{width: 100%;height: 100%;}
			#pirture{position:relative;cursor:pointer;}
			#myCarousel{margin:0;padding:0;}
/* 			li:focus{background-color: #00CCFF;} */
			#textlayer{width:92%;height:25%;background:rgba(0,0,0,0.5);/*透明背景色，不透明其文字内容*/position:absolute;left:10;bottom:0;color:#fff;font-family:"微软雅黑";}
			#textlayer h3{font-size:20px;font-weight:100;height:40px;text-align:center;line-height:40x;}
			#textlayer p{font-size:14px;text-align:center;}			
</style>
<script>
$(document).ready(function(){
	$(".row div").hover(function(){
		$(this).find("#textlayer").stop().animate({height:"100%"},400);
		$(this).find("#textlayer h3").stop().animate({paddingTop:"25px"},400);
		
	},function(){
		$(this).find("#textlayer").stop().animate({height:"45px"},400);
		$(this).find("#textlayer h3").stop().animate({paddingTop:"0px"},400);
	});
});
</script>
</head>
<body>
		<jsp:include page="header.jsp"/>
		<!--图片轮播 -->
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>   
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src='<c:url value="/resources/img/tianyu.png"></c:url>' alt="First slide">
					</div>
					<div class="item">
						<img src='<c:url value="/resources/img/qsmy.png"></c:url>' alt="Second slide">
					</div>
					<div class="item">
						<img src='<c:url value="/resources/img/wgj.png"></c:url>' alt="Third slide">
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev" data-interval="1500">
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<br />
			<div class="text-center">
				<h2>扛鼎推荐</h2>
				<hr />
			</div>
			<div class="container"> 
				<div class="row">
					<div class="col-lg-4 col-sm-4">
						<img src='<c:url value="/resources/img/qsmy.jpg"></c:url>'>
						<div id="textlayer">
							<h3>秦时明月</h3><br>
							<p>华语动漫|巅峰力作</p>
						</div>
					</div>
					<div class="col-lg-4 col-sm-4">
						<img src='<c:url value="/resources/img/wgj.jpg"></c:url>'">
						<div id="textlayer">
							<h3>武庚纪</h3><br>
							<p>超强脑洞|封神演义</p>
						</div>
					</div>
					<div class="col-lg-4 col-sm-4">
						<img src='<c:url value="/resources/img/ckwlq.jpg"></c:url>'>
						<div id="textlayer">
							<h3>刺客伍六七</h3><br>
							<p>另类职业|一个神秘刺客的生活</p>
						</div>
					</div>	
				</div>
			</div>
			<br />
			<div class="panel-footer text-center">
				<p>商务合作|关于我们|服务条款|信息反馈|联系我们</p>
				<p>©2018 纵横国漫网 版权所有</p>
			</div>
			
	</body>
</html>