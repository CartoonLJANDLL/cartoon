<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加主题版块|编辑主题版块</title>
<link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>' media="all">
<link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' media="all">
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10">
	<div class="layui-row layui-col-space10">
		<div class="layui-col-md9 layui-col-xs7">
			<div class="layui-form-item magt3">
				<label class="layui-form-label">主题名称</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input title" lay-verify="title|required" placeholder="请输入主题名称">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">主题简介</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入主题简介,30字以内" lay-verify="abstracts" class="layui-textarea abstracts"></textarea>
				</div>
			</div>
		</div>
		<div class="layui-col-md3 layui-col-xs5">
			<div class="layui-upload-list thumbBox mag0 magt3">
				<img class="layui-upload-img thumbImg">
			</div>
		</div>
	</div>
	<div class="magb15 layui-col-md4 layui-col-xs12">
      <label class="layui-form-label">设置版主</label>
      <div class="layui-input-block">
        <select name="modules" class="master" lay-search="">
          	<option value="0">直接选择或搜索选择</option>
          	<c:forEach items="${users }" var="item"  varStatus="status">
		    	<option value="${item.getUserid() }">${item.getUsername() }</option>
			</c:forEach>
        </select>
      </div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block layui-center">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addBlock">立即提交</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
	<input type="hidden" class="blockid" value="0">
</form>
<script type="text/javascript" src='<c:url value="/resources/layuicms/layui/layui.js"></c:url>'></script>
<script type="text/javascript">
layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;


    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        url: '../user/uploadHeadImage',
        method : "post", 
        done: function(res, index, upload){
           $('.thumbImg').attr('src',".."+res.src);
           $('.thumbBox').css("background","#fff");
        }
    });

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    form.on("submit(addBlock)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
         $.post('../admin/addblock',{
        	 id : $(".blockid").val(),
             title : $(".title").val(),  //主题名称
             abstracts : $(".abstracts").val(),  //主题描述
             masterid : $('.master option:selected').val(),
             photo : $(".thumbImg").attr("src").replace("..",""),  //封面图
         },function(res){
        	 top.layer.msg(res.msg);
         })
        setTimeout(function(){
            top.layer.close(index);
            
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },500);
        return false;
    })


})
</script>
</body>
</html>