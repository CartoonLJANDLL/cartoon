<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户设置</title>
<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>'>
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>'>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">登录名</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input userName" lay-verify="required" placeholder="输入你的登录名">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">手机号</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input userPhone" lay-verify="userPhone">
		</div>
	</div>
	<div class="layui-row">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="sex" value="男" title="男" >
				<input type="radio" name="sex" value="女" title="女" >
				<input type="radio" name="sex" value="保密" title="保密">
			</div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">等级经验值</label>
			<div class="layui-input-block">
			<input type="text" class="layui-input gradeValue" lay-verify="gradeValue">
		</div>
		</div>
		<!-- <div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">用户状态</label>
			<div class="layui-input-block">
				<select name="userStatus" class="userStatus" lay-filter="userStatus">
					<option value=1>正常使用</option>
					<option value=0>限制用户</option>
				</select>
			</div>
		</div> -->
	</div>
	<div class="layui-row" id="blockmaster">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">是否为版主</label>
		    <div class="layui-input-block">
		      <input type="checkbox" class="userStatus" name="userStatus" lay-skin="switch" lay-filter="userStatus" lay-text="是|否">
		    </div>
		</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">管理板块</label>
			<div class="layui-input-block">
				<select name="chargeblock" class="chargeblock" lay-filter="chargeblock">
				 <c:forEach items="${blocks }" var="item"  varStatus="status">
				    <option value="${item.getId() }">${item.getName() }</option>
				</c:forEach>	
				</select>
			</div>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block layui-center">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addUser">立即修改</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script type="text/javascript">
layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var userStatus="";
	if($(".userStatus").val()!=""){ 
	userStatus=3;
	}
	else {userStatus=1;}

    form.on("submit(addUser)",function(data){
    	//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    		$.post('<c:url value="/admin/resetUser"></c:url>',{
                userPhone : $(".userPhone").val(),  //手机号
                gradeValue : $(".gradeValue").val(),  //用户等级
                userStatus : userStatus,   //用户权限状态
                blockId :$(".chargeblock").val(),
    		},function(res){
            	top.layer.msg(res.msg);
            })
        setTimeout(function(){
            top.layer.close(index);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },2000);
        return false;
    });

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    };
})
</script>
</body>
</html>