<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加番剧|编辑番剧</title>
<link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>' media="all">
<link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' media="all">
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10">
	<div class="layui-col-md9 layui-col-xs12">
		<div class="layui-row layui-col-space10">
			<div class="layui-col-md9 layui-col-xs7">
				<div class="layui-form-item magt3">
					<label class="layui-form-label">番剧名称</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input title" lay-verify="title" placeholder="请输入番剧名称">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">链接地址</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input opurl" lay-verify="opurl" placeholder="请输入番剧来源超链接">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">视频分享通用代码</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input opiframeurl" lay-verify="opiframeurl" placeholder="请输入番剧视频分享通用代码">
					</div>
				</div>
			</div>
			<div class="layui-col-md3 layui-col-xs5">
				<div class="layui-upload-list thumbBox mag0 magt3">
					<img class="layui-upload-img thumbImg">
				</div>
			</div>
		</div>
		<div class="layui-form-item magb0">
			<label class="layui-form-label">一句话简介</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input opdesc" name="opdesc" lay-verify="opdesc">
			</div>
		</div>
		<div class="layui-center" style="margin-top:15px;">
		<a class="layui-btn layui-btn-fluid" lay-filter="addopera" lay-submit><i class="layui-icon">&#xe609;</i>确认添加</a>
		<!-- <a class="layui-btn layui-btn-primary layui-btn-sm" lay-filter="look" lay-submit>预览</a> -->
		</div>
		<input type="hidden" class="layui-input opid" name="opid" lay-verify="opdesc" value="">
	</div>
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i> 番剧类型</blockquote>
		<div class="border category">
			<div class="">
				<p><input type="checkbox" name="type" value="冒险" title="冒险" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="玄幻" title="玄幻" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="青春" title="青春" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="武侠" title="武侠" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="搞笑" title="搞笑" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="校园" title="校园" lay-skin="primary" /></p>
				<p><input type="checkbox" name="type" value="热血" title="热血" lay-skin="primary" /></p>
			</div>
		</div>		
	</div>
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

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        url: '/guomanwang/user/uploadHeadImage?src=/resources/img/opera',
        method : "post",
        done: function(res, index, upload){
  	      //如果上传失败
  	      if(res.code > 0){
  	        return layer.msg('番剧缩略图上传失败');
  	      }
  	      //上传成功 
  	      if(res.code==0){
  	        $('.thumbImg').attr('src',".."+res.src);      
  	      }
  	      
        }
    });

    form.verify({
        title : function(val){
            if(val == ''){
                return "番剧名称不能为空";
            }
        },
        opurl : function(val, index, elem){
            if(val == ''){
                return "番剧来源超链接不能为空";
            }
            else if(!/^http(s*):\/\/[\S]/.test(val)){
                return "请输入正确的番剧来源超链接！";
              }
        },
        opdesc : function(val){
            if(val == ''){
                return "番剧一句话简介内容不能为空";
            }
            else if(val.length>15){
                return "一句话简介长度不能超过15个字符";
            }
        }
    })
    form.on("submit(addopera)",function(data){
        //获得复选框选中的番剧类型
        var type =[]; 
	    $('input[name="type"]:checked').each(function(){ 
	        type.push($(this).val()); 
	    });
	    var param=JSON.stringify({
	    	'opId' : $(".opid").val(),
            'name' : $(".title").val(),
            'url' : $(".opurl").val(), 
            'iframeurl' : $(".opiframeurl").val(),  
            'desc' : $(".opdesc").val(),
            'photourl' : $(".thumbImg").attr("src"),
            'type':type
        });
	  	//弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
         $.post('/guomanwang/opera/updateopera',{
        	 param :param
         },function(res){
        	 top.layer.msg(res.msg);
         })
        setTimeout(function(){
            top.layer.close(index);
            //刷新父页面
           	layer.closeAll("iframe");
           	parent.location.reload();
            
        },500);
        return false;
    })
    //创建一个编辑器
    var editIndex = layedit.build('news_content',{
        height : 535,
        uploadImage : {
            url : "../../json/newsImg.json"
        }
    });

})
</script>
</body>
</html>