<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>管理员管理</title>
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
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal"style="width:250px;" placeholder="请输入用户名或手机号的关键字" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn">添加管理员</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal layui-btn-disabled delAll_btn">批量删除</a>
			</div>
		</form>
	</blockquote>
	<table id="adminList" lay-filter="adminList"></table>
	<!--操作-->
	<script type="text/html" id="adminListBar">
		{{#  if(d.status == "1"){ }}
		<a class="layui-btn layui-btn-xs layui-btn-warm" data-id='{{d.status}}' lay-event="usable">正常</a>
		{{# } else if(d.status == "0"){ }}
		<a class="layui-btn layui-btn-xs layui-btn-danger" data-id='{{d.status}}' lay-event="usable">禁用</a>
		{{#  }}}
		<a class="layui-btn layui-btn-xs" lay-event="edit">设置</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" >删除</a>
	</script>
</form>
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/layuicms/js/userList.js"></c:url>'></script>
<script>
layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#adminList',
        url : '../admin/getadminlist',
        cellMinWidth : 95,
        toolbar: true,
        title: '注册管理员数据表',
        page : true,
        page: {
            curr: 1 //设定初始在第 1 页
          },
        height : "full-125",
        limits : [10,15,20,25],
        limit : 15,
        id : "adminListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'Id',sort: 'true', title: '编号', minWidth:50, align:"center"},
            {field: 'userName',sort: 'true', title: '用户名', minWidth:100, align:"center"},
            {field: 'userPhone', title: '联系方式', minWidth:200, align:'center'},
            {field: 'userSex', title: '用户性别', align:'center'},
            {field: 'userStatus', title: '管理级别',  align:'center',templet:function(d){
                return d.userStatus ==3 ? "管理员" : "超级管理员";
            }},
            {field: 'gradeValue', title: '等级经验值', align:'center'},
            {title: '操作', minWidth:175, templet:'#adminListBar',fixed:"right",align:"center"}
        ]]
    });
    //搜索用户
    $(".search_btn").on("click",function(){
    	var index = top.layer.msg('数据查询中，请稍候',{icon: 16,time:false,shade:0.8});
        if($(".searchVal").val() != ''){
            table.reload("adminListTable",{
                page: {
                   curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                },
            url:'../admin/searchadmin',
        	method:'post',
            })
            setTimeout(function(){
                top.layer.close(index);
            },600);
        }else{
            layer.msg("请输入搜索的内容");
        }
    });
    //管理员设置
    function addUser(edit){
        var index = layui.layer.open({
            title : "管理员设置",
            type : 2,
            content : '../admin/setUser',
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".userid").val(edit.Id);
                    body.find(".userName").val(edit.userName);  //登录名
                    body.find(".userPhone").val(edit.userPhone);  //手机号
                    body.find(".userSex input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                    body.find(".gradeValue").val(edit.gradeValue);  //用户等级
                    body.find("#userStatus select option[value="+edit.userStatus+"]").prop("selected","selected");//用户身份
                    
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回管理员列表', '.layui-layer-setwin .layui-layer-close', {
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
        addUser();
    })
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            usersId = [];
        if(data.length > 0) {
            for (var i in data) {
                usersId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的管理员？该操作无法撤销！', {icon: 3, title: '提示信息'}, function (index) {
                 $.get("删除用户接口",{
                     newsId : newsId  //将需要删除的newsId作为参数传入
                 },function(data){
                tableIns.reload();
                layer.close(index);
                 })
            })
        }else{
            layer.msg("请选择需要删除的管理员");
        }
    })
	
    //列表操作
    table.on('tool(adminList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
	
        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
            status = _this.attr("data-id"),
                usableText = "是否确定禁用此管理员？",
                btnText = "禁用";
            if(_this.text()=="禁用"){
                usableText = "是否确定启用此管理员？",
                btnText = "正常";
            }
            if(status==1){
            	status=0;
            }
            else if(status==0){
            	status=1;
            }
            layer.confirm(usableText,{icon:3, title:'提示信息'},function(index){
                $.post("../admin/updateuser",{
                    userid : data.Id,
                    status : status
                },function(data){
               	 layer.msg(data.msg);
               	 if(data.code==1){
               		 tableIns.reload();
               	 }
                   layer.close(index);
                })
           });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除管理员'+data.userName+'?',{icon:3, title:'提示信息'},function(index){
                 $.post("../admin/deleteuser",{
                     userid : data.Id  //将需要删除的用户Id作为参数传入
                 },function(data){
                    tableIns.reload();
                    layer.msg(data.msg);
                    layer.close(index);
                 })
            });
        }
    });
});
</script>
</body>
</html>