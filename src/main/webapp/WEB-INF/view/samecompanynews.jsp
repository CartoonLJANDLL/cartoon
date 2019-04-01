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
	.layui-row .flow-default li{line-height: 30px;list-style-type:circle;margin-left:16px;}
</style>
</head>
	<body>
		<div class="layui-row layui-form">
			<blockquote class="site-text layui-elem-quote">
				<span>
					<i class="layui-icon" style="font-size: 30px; color: #1E9FFF;">&#xe60a;</i>
					<label style="font-size: 20px;">来自${company.getName()}的资讯</label>
				</span>
				<div style="float:right;margin-right:12%;">
					<button class="layui-btn layui-btn-sm layui-btn-primary">升序 <i class="layui-icon layui-icon-up"></i></button>
					<button class="layui-btn layui-btn-sm layui-btn-danger">降序 <i class="layui-icon layui-icon-down"></i></button>
				</div>
			</blockquote>
			<ul class="flow-default" id="LAY_demo">
				<!--<c:forEach items="${news }" var="item"  varStatus="status">
					<li>
						<div class="layui-col-md9">
							<c:choose>
								<c:when test="${item.getCompanyid()==4 }">
									<a href="${item.getUrl() }" target="_blank">${item.getTitle() }</a>
								</c:when>
								<c:otherwise>
									<a href="${item.getUrl() }" target="newsbody">${item.getTitle() }</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="layui-col-md3">
							<span style="text-align: right;">>${item.getTime() }</span>
						</div>
					</li>
				</c:forEach>-->
			</ul>
		</div>
		<script type="text/javascript" src="<c:url value='/resources/layui/layui.js'></c:url>"></script>
		<script>
		layui.use(['flow','util'], function(){
			  var $ = layui.jquery, //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
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
				  $.get('/guomanwang/common/samecompanynews?companyid='+companyid+'&page='+page, function(res){
					//假设你的列表返回在data集合中
					layui.each(res.data, function(index, item){
					  if(item.company=='娃娃鱼动画'||item.company=='玄机科技'){
						  lis.push('<li><div class="layui-col-md9"><a href="'+item.url+'" target="_blank">'+item.title +
								  '</a></div><div class="layui-col-md3"><span style="text-align: right;">时间：'+item.time+'</span></div></li><hr/>');
					  }
					  else{
						  lis.push('<li><div class="layui-col-md9"><a href="'+item.url+'" target="newsbody">'+item.title +
								  '</a></div><div class="layui-col-md3"><span style="text-align: right;">时间：'+item.time+'</span></div></li><hr/>');
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