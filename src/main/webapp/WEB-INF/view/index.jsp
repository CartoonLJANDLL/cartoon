<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>纵横国漫网</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">

<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
<link href="/resources/css/layui.css" rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/bootstrap.min.css"></c:url>' rel="stylesheet" />
<style type="text/css">
			.fly-logo img{width:120px;height:40px;}
			.row img{width: 100%;height: 100%;}
			#pirture{position:relative;cursor:pointer;}
			#myCarousel{margin:0;padding:0;}
/* 			li:focus{background-color: #00CCFF;} */
			#textlayer{width:92%;height:25%;background:rgba(0,0,0,0.5);/*透明背景色，不透明其文字内容*/position:absolute;left:10;bottom:0;color:#fff;font-family:"微软雅黑";}
			#textlayer h3{font-size:20px;font-weight:100;height:40px;text-align:center;line-height:40x;}
			#textlayer p{font-size:14px;text-align:center;}
			#myCarousel{z-index:1;}
			blockquote p{font-size:15px;}
			.container{margin-top:15px;}	
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

<jsp:include page="menu_header.jsp"/>
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
						<a href="https://v.qq.com/x/cover/hkpvlyfjca2v1rn.html"> <img src='<c:url value="/resources/img/tianyu.png"></c:url>' alt="First slide"></a>
					</div>
					<div class="item">
						<a href="https://v.youku.com/v_show/id_XODU2MTEyNjI4.html?spm=a2h0k.11417342.soresults.dselectbutton&s=9a8e9ba0605611e2a19e"><img src='<c:url value="/resources/img/qsmy.png"></c:url>' alt="Second slide"></a>
					</div>
					<div class="item">
						<a href="https://v.qq.com/x/cover/ipmc5u3dwb48mv2.html"><img src='<c:url value="/resources/img/wgj.png"></c:url>' alt="Third slide"></a>
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
			<div class="container">
				<blockquote class="layui-elem-quote main_btn">
					<p><a href="javascript:;" target="_blank" class="layui-btn layui-btn-xs">纵横国漫网</a>是一个以社区论坛为基础的国漫二次元交流分享平台，这里是国产热门番剧的交流共享之地。</p>
					<p>纵横国漫网集合各大动画公司的资讯，以及番剧的视频集合，在这里你可以畅所欲言，可以实时跟踪国内各大动画公司的相关番剧信息，可以观看你想看的视频。</p>
					<p class="layui-blue">PS：纵横国漫网只提供入口，并不是资讯与番剧的二次发布，望知悉！</p>
				</blockquote>
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
<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
 
<script src='<c:url value="/resources/js/layui.js"></c:url>'></script>
</body>
</html>