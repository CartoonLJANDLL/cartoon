<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资讯</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
    <link rel="stylesheet" href="<c:url value='/resources/layui/css/layui.css'></c:url>">
    <link rel="stylesheet" href="<c:url value='/resources/layuicms/css/index.css'></c:url>">
</head>
<body class="main_body">
<jsp:include page="menu_header.jsp"/>
	<div class="layui-layout layui-layout-admin">
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div style="margin:20px auto;text-align:center;">
				<h2>动画公司 | 网站</h2>
			</div>
			<!-- 搜索 -->
			<div class="layui-form component">
				<input type="text" name="serchnews" id="newskey" required lay-verify="required" placeholder="请输入资讯关键字搜索" autocomplete="off" class="layui-input"> 
				<button lay-submit lay-filter="searchnews">
					<i class="layui-icon">&#xe615;</i>
				</button>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-this">
						<a href='news'><cite>资讯首页</cite></a>
					</li>
					<c:forEach items="${companies }" var="item"  varStatus="status">
						<li class="layui-nav-item">
							<a href="../common/samecompany?companyid=${item.getId()}" target="newsbody">
								<cite>${item.getName()}</cite>
							</a>
						</li>   
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- 资讯主体内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe name="newsbody" src="../common/shownews"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p><span>copyright @2018 纵横国漫网</span>　　
			<a id="contectus" class="layui-btn layui-btn-danger layui-btn-sm">联系我们</a></p>
		</div>
	</div>

	<!-- 移动导航 -->
	<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
	<div class="site-mobile-shade"></div>
	<script>
	layui.use(['element','form','layer'], function(){
		  var $ = layui.jquery,
		  	element = layui.element,
		  	form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer;
		  
		 	 //联系弹窗
		 	 $("#contectus").click(function(){
		 	 	layer.tab({
		 	 		area : ['260px', '367px'],
		 	 		tab : [{
		 	 			title : "微信",
		 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../images/wechat.jpg'></div>"
		 	 		},{
		 	 			title : "QQ",
		 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../images/alipay.jpg'></div>"
		 	 		}]
		 	 	})
		 	 }
		 	 )
		  form.on('submit(searchnews)', function(data){
				 $.ajax({
					    url:'/guomanwang/common/searchnews',
					    type: 'post',
					    data: {
				  		  key:$("#newskey").val()
					    },
					    success: function (res) {
							if(res.code==1){
								  var lis=[];
								  layui.each(res.data, function(index, item){
									  if(item.company=='娃娃鱼动画'){
										  lis.push('<li><div class="layui-col-sm9"><a href="'+item.url+'" target="_blank">'+item.title +
												  '</a></div><div class="layui-col-sm3"><span style="text-align: right;">时间：'+item.time+'</span></div></li>');
									  }
									  else{
										  lis.push('<li><div class="layui-col-sm9"><a href="'+item.url+'" target="newsbody">'+item.title +
												  '</a></div><div class="layui-col-sm3"><span style="text-align: right;">时间：'+item.time+'</span></div></li>');
									  }
									})
								    layer.open({
								        type: 0
								        ,title: '查询结果'
								        ,area: '800px'
								        ,shade: 0.8
								        ,shadeClose: true
								        ,content: ['<div class="layui-text" style="padding: 20px;">'
								          ,'<blockquote class="layui-elem-quote">查询结果如下（'+res.msg+'）</blockquote>'
								          ,'<ul class="searchreasult">'
								          ,lis
								          ,'</ul>'
								        ,'</div>'].join('')
								      });
							}
							else{
								layer.msg(res.msg);
							}
					    }
					  });
			        return false;
			    });
		  $('body').on('click', '.searchreasult li a', function(){
			  layer.closeAll('dialog');
		  });
		 	//手机设备的简单适配
		     $('.site-tree-mobile').on('click', function(){
		 		$('body').addClass('site-mobile');
		 	});
		     $('.site-mobile-shade').on('click', function(){
		 		$('body').removeClass('site-mobile');
		 	});
		});
	</script>
</body>
</html>