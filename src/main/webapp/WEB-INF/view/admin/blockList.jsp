<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>主题版块管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>'>
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>'>
</head>
<body class="childrenBody">
<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
	<div class="layui-inline">
		<a class="layui-btn layui-btn-normal addNews_btn">添加版块</a>
	</div>
	</blockquote>
	<table id="blockList" lay-filter="blockList"></table>
	<!--操作-->
	<script type="text/html" id="blockListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">设置</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</form>
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script>
layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#blockList',
        url : '../admin/getblocklist',
        cellMinWidth : 95,
        toolbar: true,
        title: '主题版块数据表',
        page : true,
        page: {
            curr: 1 //设定初始在第 1 页
          },
        height : "full-125",
        limits : [10,15,20,25],
        limit : 15,
        id : "blockListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'Id',sort: 'true', title: '主题编号', minWidth:50, align:"center"},
            {field: 'Name', title: '主题名称', minWidth:100, align:"center"},
            {field: 'Last_time', sort: 'true',title: '最近发帖', minWidth:200, align:'center'},
            {field: 'Threadnum',sort: 'true', title: '帖子数量', minWidth:100, align:'center'},
            {field: 'User', title: '版主', minWidth:100, align:'center'},
            {field: 'Abstracts', title: '简介', minWidth:100, align:'center',hide:'ture'},
            {field: 'Photo', title: '封面', minWidth:50, align:'center',hide:'ture'},
            {field: 'masterid', title: '版主编号', minWidth:50, align:'center',hide:'ture'},
            {title: '操作', minWidth:175, templet:'#blockListBar',fixed:"right",align:"center"}
        ]]
    });
    //版块设置
    function addBlock(edit){
        var index = layui.layer.open({
            title : "版块设置",
            type : 2,
            content : '../admin/setBlock',
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".blockid").val(edit.Id);
                    body.find(".title").val(edit.Name);  //主题名称
                    body.find(".abstracts").val(edit.Abstracts);  //主题描述
                    body.find(".thumbImg").attr("src",".."+edit.Photo);
                    body.find(".master option[value="+edit.masterid+"]").prop("selected","selected");//用户身份
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回版块列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addBlock();
    })
	
    //列表操作
    table.on('tool(blockList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
	
        if(layEvent === 'edit'){ //设置编辑
            addBlock(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除'+data.Name+"版块？该版块下所有帖子会被连带删除！",{icon:3, title:'提示信息'},function(index){
                 $.get("../admin/deleteblock",{
                     id : data.Id  //将需要删除的版块Id作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.close(index);
                 })
            });
        }
    });
});
</script>
</body>
</html>