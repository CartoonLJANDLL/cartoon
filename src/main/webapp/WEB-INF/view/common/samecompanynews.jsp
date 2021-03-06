<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList,java.util.List,guomanwang.domain.Thread,guomanwang.domain.UserThread,guomanwang.domain.TimeTransformUtil" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>同一发布者资讯页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href="<c:url value='/resources/layui/css/layui.css'></c:url>">
<style type="text/css">
	.layui-row{padding-left: 20px;margin-top: 20px;}
	.layui-row li{line-height: 30px;list-style-type:circle;margin-left:16px;}
    .flow-default li:hover{box-shadow:0px 5px 3px #777;}
</style>
</head>
	<body>
		<div class="layui-row layui-form">
			<blockquote class="site-text layui-elem-quote">
				<span>
					<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe60a;</i>
					<label style="font-size: 20px;">${company.getName()}的资讯</label>
				</span>
			</blockquote>
			<ul class="flow-default" id="LAY_demo"></ul>
		</div>
		<script type="text/javascript" src="resources/layui/layui.js"></script>
		<script src="resources/js/shownews.js"></script>
		<script>
		layui.use(['flow','util'], function(){
			  var $ = layui.jquery,
			  	flow = layui.flow,
			  	util = layui.util;
			  
			  //右下角小工具
			  util.fixbar({
				    css:{bottom: 50}
				  });
			  
			  flow.load({
				elem: '#LAY_demo', //指定列表容器
				isAuto:false,
				done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
				  var lis = [];
				  var companyid="${company.getId()}";
				  //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
				  $.post('samecompanynews?companyid='+companyid+'&page='+page, function(res){
					//假设你的列表返回在data集合中
					layui.each(res.data, function(index, item){
					  if(item.company=='娃娃鱼动画'||item.company=='玄机科技'){
						  lis.push('<li><div class="layui-col-md9"><a class="newslink" data-id="'+item.id+'" href="'+item.url+'" target="_blank">'+item.title +
								  '</a></div><div class="layui-col-md3"><span style="text-align: right;">时间：'+item.time+'</span></div><hr/></li>');
					  }
					  else{
						  lis.push('<li><div class="layui-col-md9"><a class="newslink" data-id="'+item.id+'" href="'+item.url+'" target="newsbody">'+item.title +
								  '</a></div><div class="layui-col-md3"><span style="text-align: right;">时间：'+item.time+'</span></div><hr/></li>');
					  }
					}); 
					//执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
					//pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
					next(lis.join(''), page < res.pages);    
				  });
			}
		});
	});
	</script>
	</body>
</html>