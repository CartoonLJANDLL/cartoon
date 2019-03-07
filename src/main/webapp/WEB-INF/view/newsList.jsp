<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>资讯列表</title>
<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>' media="all">
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' media="all">
	<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
</head>
<body class="childrenBody">
<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入标题关键字搜索" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn">添加资讯</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
			</div>
		</form>
	</blockquote>
	<table id="newsList" lay-filter="newsList"></table>
	<!--审核状态-->
	<script type="text/html" id="newsStatus">
		{{#  if(d.newsStatus == "1"){ }}
		<span class="layui-red">等待审核</span>
		{{#  } else if(d.newsStatus == "0"){ }}
		<span class="layui-blue">已存草稿</span>
		{{#  } else { }}
			审核通过
		{{#  }}}
	</script>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="look" href='{{d.url}}'>查看</a>
	</script>
	
</form>
	<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
	<script type="text/javascript">
	layui.use(['form','layer','laydate','table','laytpl'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer,
	        $ = layui.jquery,
	        laydate = layui.laydate,
	        laytpl = layui.laytpl,
	        table = layui.table;

	    //资讯列表
	    var tableIns = table.render({
	        elem: '#newsList',
	        url : '<c:url value="/admin/getallnews"></c:url>',
	        cellMinWidth : 95,
	        toolbar: true,
	        title: '资讯数据表',
	        page:true,
	        page: {
               curr: 1 //设定初始在第 1 页
             },
	        height : "full-125",
	        limit : 20,
	        limits : [10,15,20,25],
	        id : "newsListTable",
	        cols : [[
	            {type: "checkbox", fixed:"left", width:50},
	            {field: 'id', title: 'ID', width:60, align:"center"},
	            {field: 'title', title: '资讯标题', width:350},
	            {field: 'time', sort: 'true', title: '发布时间', align:'center', minWidth:110},
	            {field: 'company', title: '来源', align:'center'},
	            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
	        ]]
	    });
	    //搜索
	    $(".search_btn").on("click",function(){
	    	var index = top.layer.msg('数据查询中，请稍候',{icon: 16,time:false,shade:0.8});
	        if($(".searchVal").val() != ''){
	            table.reload("newsListTable",{
	                where: {
	                    key: $(".searchVal").val()  //搜索的关键字
	                },
	            	url:'<c:url value="/admin/searchnews"></c:url>',
	            	method:'post',
	            })
	            setTimeout(function(){
	                top.layer.close(index);
	            },600);
	        }else{
	            layer.msg("请输入搜索的内容");
	        }
	    });
	  //列表操作
	    table.on('tool(newsList)', function(obj){
	        var layEvent = obj.event,
	            data = obj.data;

	        if(layEvent === 'edit'){ //编辑
	            addNews(data);
	        } else if(layEvent === 'del'){ //删除
	            layer.confirm('确定删除此资讯？',{icon:3, title:'提示信息'},function(index){
	                 $.get('<c:url value="/admin/deletenews"></c:url>',{
	                     id : data.id  //将需要删除的newsId作为参数传入
	                 },function(data){
	                    tableIns.reload();
	                    layer.msg(data.msg);
	                    layer.close(index);
	                 })
	            });
	        }
	    });
	    //批量删除
	    $(".delAll_btn").click(function(){
	        var checkStatus = table.checkStatus('newsListTable'),
	            data = checkStatus.data,
	            ids = [];
	        if(data.length > 0) {
	            for (var i in data) {
	                ids.push(data[i].id);
	            }
	            layer.confirm('确定删除选中的资讯？'+ids, {icon: 3, title: '提示信息'}, function (index) {
	                 $.get('<c:url value="/admin/deletemorenews"></c:url>',{
	                	 traditional :true,
	                	 ids : ids.toString()  //将需要删除的ids数组作为参数传入
	                 },function(data){
	                tableIns.reload();
	                layer.msg(data.msg);
	                layer.close(index);
	                 })
	            })
	        }else{
	            layer.msg("请选择需要删除的资讯");
	        }
	    })
	  //添加资讯
	    function addNews(edit){
	        var index = layui.layer.open({
	            title : "添加资讯",
	            type : 2,
	            content : '<c:url value="/admin/addnews"></c:url>',
	            success : function(layero, index){
	                var body = layui.layer.getChildFrame('body', index);
	                if(edit){
	                    body.find(".title").val(edit.title);
	                    body.find(".abstracts").val(edit.url);
	                    body.find(".thumbImg").attr("src",edit.newsImg);
	                    body.find("#content").val(edit.content);
	                    body.find(".status select").val(edit.status);
	                    form.render();
	                }
	                setTimeout(function(){
	                    layui.layer.tips('点击此处返回资讯列表', '.layui-layer-setwin .layui-layer-close', {
	                        tips: 3
	                    });
	                },500)
	            }
	        })
	        layui.layer.full(index);
	        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
	        $(window).on("resize",function(){
	            layui.layer.full(index);
	        })
	    }
	    $(".addNews_btn").click(function(){
	        addNews();
	    })
	});
	</script>
</body>
</html>