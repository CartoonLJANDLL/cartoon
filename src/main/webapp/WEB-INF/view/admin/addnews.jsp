<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加资讯</title>
<link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>' media="all">
<link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' media="all">
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10">
	<div class="layui-col-md9 layui-col-xs12">
		<div class="layui-row layui-col-space10">
			<div class="layui-col-md9 layui-col-xs7">
				<div class="layui-form-item magt3">
					<label class="layui-form-label">资讯标题</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input title" lay-verify="title" placeholder="请输入文章标题">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">链接地址</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入资讯超链接" lay-verify="abstracts" class="layui-textarea abstracts"></textarea>
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
			<label class="layui-form-label">资讯内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="news_content"></textarea>
			</div>
		</div>
	</div>
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i> 分类目录</blockquote>
		<div class="border category">
			<div class="">
				<p><input type="checkbox" name="news" title="新闻" lay-skin="primary" /></p>
				<p><input type="checkbox" name="goods" title="商品" lay-skin="primary" /></p>
				<p><input type="checkbox" name="notice" title="公告" lay-skin="primary" /></p>
				<p><input type="checkbox" name="images" title="图片" lay-skin="primary" /></p>
			</div>
		</div>
		<blockquote class="layui-elem-quote title magt10"><i class="layui-icon">&#xe609;</i> 发布</blockquote>
		<div class="border">
			<div class="layui-form-item">
				<label class="layui-form-label"><i class="layui-icon">&#xe60e;</i> 状　态</label>
				<div class="layui-input-block status">
					<select name="status" lay-verify="required">
						<option value="置顶">置顶</option>
						<option value="默认">默认</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"><i class="layui-icon">&#xe609;</i> 发　布</label>
				<div class="layui-input-block">
					<input type="radio" name="release" title="立即发布" lay-skin="primary" lay-filter="release" checked />
					<input type="radio" name="release" title="定时发布" lay-skin="primary" lay-filter="release" />
				</div>
			</div>
			<div class="layui-form-item layui-hide releaseDate">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" id="release" placeholder="请选择日期和时间" readonly />
				</div>
			</div>
			<hr class="layui-bg-gray" />
			<div class="layui-right">
				<a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>发布</a>
				<!-- <a class="layui-btn layui-btn-primary layui-btn-sm" lay-filter="look" lay-submit>预览</a> -->
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
        url: '../../json/userface.json',
        method : "get",  //
        done: function(res, index, upload){
            var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('.thumbImg').attr('src',res.data[num].src);
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
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger : "click",
        done : function(value, date, endDate){
            submitTime = value;
        }
    });
    form.on("radio(release)",function(data){
        if(data.elem.title == "定时发布"){
            $(".releaseDate").removeClass("layui-hide");
            $(".releaseDate #release").attr("lay-verify","required");
        }else{
            $(".releaseDate").addClass("layui-hide");
            $(".releaseDate #release").removeAttr("lay-verify");
            submitTime = time.getFullYear()+'-'+(time.getMonth()+1)+'-'+time.getDate()+' '+time.getHours()+':'+time.getMinutes()+':'+time.getSeconds();
        }
    });

    form.verify({
        title : function(val){
            if(val == ''){
                return "资讯标题不能为空";
            }
        },
        abstracts : function(val){
            if(val == ''){
                return "资讯超链接不能为空";
            }
        },
        /*content : function(val){
            if(val == ''){
                return "资讯内容不能为空";
            }
        }*/
    })
    form.on("submit(addNews)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
         $.post('<c:url value="/admin/newsAdd"></c:url>',{
             title : $(".title").val(),  //资讯标题
             abstracts : $(".abstracts").val(),  //资讯超链接
             content : layedit.getContent(editIndex).split('<audio controls="controls" style="display: none;"></audio>')[0],  //资讯内容
             newsImg : $(".thumbImg").attr("src"),  //缩略图
             status : $('.status select').val(),    //发布状态
             time : submitTime,    //发布时间
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

    //预览
    form.on("submit(look)",function(){
        layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行资讯内容页面访问");
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