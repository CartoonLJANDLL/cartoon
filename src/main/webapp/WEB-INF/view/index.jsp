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
<link href="../resources/css/layui.css" rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
			.fly-logo img{width:120px;height:40px;}
			.layui-row img{width: 100%;height: 100%;}
			#pirture{position:relative;cursor:pointer;}
/* 			li:focus{background-color: #00CCFF;} */
			#textlayer{width:95%;height:18%;background:rgba(0,150,136,1);position:absolute;left:10;bottom:0;color:#fff;font-family:"微软雅黑";}
			#textlayer h3{font-size:20px;font-weight:100;padding-top:3px;height:40px;text-align:center;line-height:40x;}
			#textlayer p{font-size:14px;text-align:center;display:none;}
			blockquote p{font-size:15px;}
			.container{margin-top:15px;padding-left:220px;}
</style>
</head>
<body>

	<jsp:include page="menu_header.jsp"/>
	<!--图片轮播 -->
	<div class="layui-carousel" id="test1">
	  <div carousel-item>
		<div>
			<a href="https://v.qq.com/x/cover/hkpvlyfjca2v1rn.html"> <img src='<c:url value="/resources/img/tianyu.png"></c:url>'></a>
		</div>
		<div>
			<a href="https://v.qq.com/x/cover/ipmc5u3dwb48mv2.html"><img src='<c:url value="/resources/img/wgj.png"></c:url>'></a>
		</div>
		<div>
			<a href="https://v.youku.com/v_show/id_XODU2MTEyNjI4.html?spm=a2h0k.11417342.soresults.dselectbutton&s=9a8e9ba0605611e2a19e"><img src='<c:url value="/resources/img/qsmy.png"></c:url>'></a>
		</div>
	  </div>
	</div>
	<div class="container">
		<blockquote class="layui-elem-quote main_btn">
			<p><a href="javascript:;" target="_blank" class="layui-btn layui-btn-xs">纵横国漫网</a>是一个以社区论坛为基础的国漫二次元交流分享平台，在这里你可以畅所欲言，广交好友，祝你遇见有缘人。</p>
			<p>这里集合了各大动画公司的资讯，集合了三大视频网站的国漫番剧，可以实时跟踪国内各大动画公司的相关番剧信息，可以无跳转观看你想看的视频，只需在此！</p>
			<p style="color:#009688;">PS：纵横国漫网只提供入口，并不是资讯与番剧的二次发布，望知悉！</p>
		</blockquote>
	</div>
	<br />
	<div align="center">
		<h2>扛鼎推荐</h2>
		<hr />
	</div>
	<div class="layui-container"> 
		<div class="layui-row layui-col-space20">
			<div class="layui-col-lg4 layui-col-sm4">
				<img src='<c:url value="/resources/img/qsmy.jpg"></c:url>'>
				<div id="textlayer">
					<h3>秦时明月</h3><br>
					<p>华语动漫|巅峰力作</p>
				</div>
			</div>
			<div class="layui-col-lg4 layui-col-sm4">
				<img src='<c:url value="/resources/img/wgj.jpg"></c:url>'">
				<div id="textlayer">
					<h3>武庚纪</h3><br>
					<p>超强脑洞|封神演义</p>
				</div>
			</div>
			<div class="layui-col-lg4 layui-col-sm4">
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
	<script>
	layui.use(['carousel','layer','element'], function(){
	  var carousel = layui.carousel,
      $ = layui.jquery,
      element = layui.element,
      layer=layui.layer;
	  
	  //建造轮播图实例
	  carousel.render({
		elem: '#test1'
		,width: '100%' //设置容器宽度
		,height:'580'
		,arrow: 'always' //始终显示箭头
		//,anim: 'fade' //切换动画方式
	  });
	  
	  $(document).ready(function(){
	  	$(".layui-row div").hover(function(){
	  		$(this).find("#textlayer").stop().animate({height:"100%"},400);
	  		$(this).find("#textlayer h3").stop().animate({paddingTop:"65px"},400);
	  		$(this).find("#textlayer p").fadeIn();
	  		
	  	},function(){
	  		$(this).find("#textlayer").stop().animate({height:"45px"},400);
	  		$(this).find("#textlayer h3").stop().animate({paddingTop:"5px"},400);
	  		$(this).find("#textlayer p").fadeOut();
	  	});
	  });
	});
	</script>
</body>
</html>