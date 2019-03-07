<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>默认头像管理</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
	<link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>' >
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' >
    <style type="text/css">
    	#Images li img{width:100%;height:200px;}
    </style>
</head>
<body class="childrenBody">
<form class="layui-form">
	<blockquote class="layui-elem-quote news_search">
		
		<div class="layui-upload">
		  <div class="layui-inline">
			<button type="button" class="layui-btn layui-btn-normal" id="testList"><i class="layui-icon">&#xe67c;</i>图片上传(可多选)</button>
		  </div>
		  <div class="layui-inline">
			<button type="button" class="layui-btn" id="testListAction">开始上传</button>
			<span class="layui-word-aux">建议上传正方形的图片</span> 
		  </div>
		  <div class="layui-upload-list">
		    <table class="layui-table">
		      <thead>
		        <tr><th>文件名</th>
		        <th>大小</th>
		        <th>状态</th>
		        <th>操作</th>
		      </tr></thead>
		      <tbody id="demoList"></tbody>
		    </table>
		  </div>
		</div>
		<div class="layui-inline">
			<input type="checkbox" name="selectAll" id="selectAll" lay-filter="selectAll" lay-skin="primary" title="全选">
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel">批量删除</a>
		</div>
	</blockquote>
	<ul class="layer-photos-demo" id="Images">
 	<c:forEach items="${defaultheadlist }" var="item"  varStatus="status">
		<li>
			<img id="defaultimage" layer-src="<c:url value='${item.getUrl() }'></c:url>" alt="${item.getId() }" src="<c:url value='${item.getUrl() }'></c:url>">
			<div class="operate">
				<div class="check">
						<span>
							<input type="checkbox" class="layui-input-inline" name="belle" lay-filter="choose" lay-skin="primary" value="${item.getId() }" title="${item.getId() }">
						</span>	
				</div>	      
				<i class="layui-icon img_del">&#xe640;</i>
			</div>            
        </li>
	</c:forEach>	
	</ul>
</form>
<script type="text/javascript" src="<c:url value='/resources/layuicms/layui/layui.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/resources/layuicms/js/images.js'></c:url>"></script>
<script>
layui.use(['flow','form','layer','upload'],function(){
    var flow = layui.flow,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        $ = layui.jquery;
//删除单张图片
$("body").on("click",".img_del",function(){
    var _this = $(this);
    layer.confirm('确定删除该图片吗？',{icon:3, title:'提示信息'},function(index){
    	$.get('<c:url value="/admin/deleteimage"></c:url>',{
            imageid : _this.siblings().find("input").attr("title")  //将需要删除的Id作为参数传入
        },function(data){
           layer.msg(data.msg);
           layer.close(index);
           _this.parents("li").remove();
        })
        return false;
    })
});
//多文件列表示例
var demoListView = $('#demoList')
,uploadListIns = upload.render({
  elem: '#testList'
  ,url: 'uploadimages'
  ,accept: 'file'
  ,multiple: true
  ,auto: false
  ,bindAction: '#testListAction'
  ,choose: function(obj){   
    var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
    //读取本地文件
    obj.preview(function(index, file, result){
      var tr = $(['<tr id="upload-'+ index +'">'
        ,'<td>'+ file.name +'</td>'
        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
        ,'<td>等待上传</td>'
        ,'<td>'
          ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
          ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
        ,'</td>'
      ,'</tr>'].join(''));
      
      //单个重传
      tr.find('.demo-reload').on('click', function(){
        obj.upload(index, file);
      });
      
      //删除
      tr.find('.demo-delete').on('click', function(){
        delete files[index]; //删除对应的文件
        tr.remove();
        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
      });
      
      demoListView.append(tr);
    });
  }
  ,done: function(res, index, upload){
    if(res.code == 0){ //上传成功
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
      tds.eq(3).html(''); //清空操作
      return delete this.files[index]; //删除文件队列已经上传成功的文件
      setTimeout(function(){windows.location.reload();},950);
    }
    this.error(index, upload);
  }
  ,error: function(index, upload){
    var tr = demoListView.find('tr#upload-'+ index)
    ,tds = tr.children();
    tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
    tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
  }
});
//批量删除
$(".batchDel").click(function(data){
    var $checkbox = $('#Images li input[type="checkbox"]');
    var ids=[];
    $checked=$('#Images li input[type="checkbox"]:checked');
    $checked.each(function(){
    	ids.push($(this).val());
    });
    if($checkbox.is(":checked")){
        layer.confirm('确定删除选中的图片？',{icon:3, title:'提示信息'},function(index){
            var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
            $.get('<c:url value="/admin/deletemoreimages"></c:url>',{
           	 traditional :true,
           	 ids : ids.toString()  //将需要删除的ids数组作为参数传入
            },function(data){
           layer.msg(data.msg);
           layer.close(index);
           if(data.code==1){
        	   $checked.each(function(){
                   $(this).parents("li").hide(1000);
                   setTimeout(function(){$(this).parents("li").remove();},950);
               })
               $('#Images li input[type="checkbox"],#selectAll').prop("checked",false);
               form.render();
           }
           layer.close(index);
            })
        })
    }else{
        layer.msg("请选择需要删除的图片");
    }
});
})
</script>
</body>
</html>