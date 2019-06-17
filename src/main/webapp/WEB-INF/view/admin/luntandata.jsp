<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>论坛数据可视化</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>'>
<link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>'>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
<script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body>
	<div id="container1" style="min-width:400px;height:400px;"></div>
	 <div id="container3" style="min-width:400px;height:400px;"></div>
	 <div class="layui-form layui-form-pane">
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label" style="color:white;background-color:#1AA094;margin-left:20px;">日期范围</label>
	      <div class="layui-input-inline">
	        <input type="text" class="layui-input" id="date1" placeholder="请选择时间段">
	      </div>
	    </div>
	  </div>
	 </div>
	 <div id="container0" style="min-width:400px;height:400px;"></div>
	 <div id="container2" style="min-width:400px;height:400px;"></div> 
</body>
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/luntandata.js"></c:url>'></script>
</html>