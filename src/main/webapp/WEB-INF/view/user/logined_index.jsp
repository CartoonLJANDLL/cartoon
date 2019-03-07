<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
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
<script type="text/javascript" src='<c:url value="/resources/js/layui.js"></c:url>'></script>
<link href='<c:url value="/resources/css/layui.css"></c:url>' rel="stylesheet" />
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
			.layui-nav-child{top:0;}		
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
	$(".fly-nav-avatar").hover(function(){
		$(".layui-nav-child").show();	
	});
	$(".fly-nav-user").mouseleave(function(){
		$(".layui-nav-child").hide();	
	});
});
</script>
</head>
<body onload="checklogin();">
<script type="text/javascript">
function checklogin(){
	var user=${ sessionScope.username};
	alsert(user)
	if(user==null||user==""){
		location="index";
	}
}
</script>
<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="/">
      <img src='<c:url value="/resources/img/LOGO.png"></c:url>' alt="layui">
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
      <li class="layui-nav-item layui-this">
        <a href="index"><i class="iconfont icon-jiaoliu"></i>首页</a>
      </li>
      <li class="layui-nav-item">
        <a href="case/case.html"><i class="iconfont icon-iconmingxinganli"></i>资讯</a>
      </li>
      <li class="layui-nav-item">
        <a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>番剧</a>
      </li>
			<li class="layui-nav-item">
				<a href="luntan" target="_blank"><i class="iconfont icon-ui"></i>论坛</a>
			</li>
			<li class="layui-nav-item">
				<a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>个人中心</a>
			</li>
    </ul>
    
    <ul class="layui-nav fly-nav-user">
      <!-- 登入后的状态 -->
      <li class="layui-nav-item">
        <a class="fly-nav-avatar" href="javascript:;">
          <cite class="layui-hide-xs" id="username">${ sessionScope.username }</cite>
          <i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>
          <i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>
          <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="../user/set.html"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="../user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;">&#xe68e;</i>我的消息</a></dd>
          <dd><a href="../user/home.html"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <hr style="margin: 5px 0;">
          <dd><a href="/user/logout" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
    </ul>
  </div>
</div>

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
<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
<script>
layui.config({
  version: "3.0.0"
  ,base: '/resources/js/' //这里实际使用时，建议改成绝对路径
}).extend({
  fly: 'index'
}).use('fly');
</script>

<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

</body>
</html>