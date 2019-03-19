<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>番剧</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="纵横国漫网">
<meta name="referrer" content="no-referrer"/>
<meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link rel="stylesheet" href="<c:url value='/resources/layuicms/layui/css/layui.css'></c:url>">
	</head>
	<style type="text/css">
		.layui-bg-green,.layui-bg-red{margin:10px auto;width: 100%;height: 50px;text-align: center;padding-top:5px ;}
		.layui-row img{width: 100%;}
		.layuui-nav .layui-icon{color:#999;}
		.layui-col-md2{position:relative;height:370px;}
		.layui-row .layui-col-md2:hover{box-shadow:0px 8px 6px #888888;}
		.layui-col-md2 .jishu{position:absolute;z-indent:2;left:5px;bottom:5px;color:white;}
		.layui-col-md2 img{height:300px;}
		.layui-row{margin-top: 20px;}
		.layui-row .layui-btn{box-shadow:0px 3px 0px #888888;}
		.layui-carousel img{width:100%;height:100%;}
		h3{margin-top: 5px;}
		.layui-row .shoucang:hover,.layui-row .fenxiang:hover{color: #FF5722;}
		ul li{float:left;line-height:25px;overflow:clear;}
		ul .first{width:72%;}
		ul .second{width:10%;}
		ul .thred{width:15%;}
		ul .last{width:100%;}
		.layui-row .layui-col-md2 .share{position:absolute;z-indent:3;right:0px;bottom:0px;background-color:#009688;height:60px;padding:3px 6px;display:none;color:white;}
		.layui-row .layui-col-md2 .share table tr{line-height:25px;}
	</style>
	<body>
	<jsp:include page="menu_header.jsp"/>
		<div class="layui-carousel" id="test1">
		  <div carousel-item>
			<div class="layui-bg-green">宣传栏位1</div>
			<div class="layui-bg-green">宣传栏位2</div>
			<div class="layui-bg-green">宣传栏位3</div>
			<div class="layui-bg-green">宣传栏位4</div>
		  </div>
		</div>
<!-- 条目中可以是任意内容，如：<img src=""> -->
		<div>
				<blockquote class="layui-elem-quote main_btn">
					<div class="layui-row layui-form" >
						<div class="layui-col-lg3 sort">
							<button class="layui-btn layui-btn-sm layui-btn-danger">排序</button>
							<input type="radio" name="sort" value="" title="默认" checked>
							<input type="radio" name="sort" value="最新" title="最新">
							<input type="radio" name="sort" value="最热" title="最热">
						</div>
						<div class="layui-col-lg4 type">
							<button class="layui-btn layui-btn-sm layui-btn-danger">类型</button>
							<input type="radio" name="type" value="" title="全部" checked>
							<input type="radio" name="type" value="玄幻" title="玄幻">
							<input type="radio" name="type" value="武侠" title="武侠">
							<input type="radio" name="type" value="竞技" title="竞技">
							<input type="radio" name="type" value="冒险" title="冒险">
							<input type="radio" name="type" value="搞笑" title="搞笑">
						</div>
						<div class="layui-col-lg3 status">
							<button class="layui-btn layui-btn-sm layui-btn-danger">状态</button>
							<input type="radio" name="status" value="-1" title="全部" checked>
							<input type="radio" name="status" value="1" title="已完结">
							<input type="radio" name="status" value="0" title="未完结">
						</div>
						<div class="layui-col-lg2 layui-form">
							<div class="layui-inline">
								<input placeholder="请输入番剧名称关键字搜索" required lay-verify="required" name="keywords" autocomplete="off" id="searchinput" class="layui-input">
							</div>
							<button class="layui-btn" lay-submit lay-filter="search">
								<i class="layui-icon layui-icon-search"></i>
							</button>
						</div>
					</div>
				</blockquote>
		</div>
		<div class="layui-bg-green"></div>
		<div class="layui-container" style="width:90%;height:100%;">
			<div class="layui-row layui-col-space20 " id="result"></div>
			<div id="pager"></div>
		</div>
		<div class="fly-footer" style="text-align:center;width:100%;height:70px;margin-top:20px;">
			<p style="margin:10px auto; "><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a></p> 
			<a href="javascript:;" target="_blank">2018 &copy;刘江 and 李林</a>
		    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
		    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
		    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
		</div>
<script type="text/javascript" src="<c:url value='/resources/layui/layui.js'></c:url>"></script>
	<script>
	layui.use(['carousel','layer','element','form','flow','laypage'], function(){
	  var carousel = layui.carousel,
      $ = layui.jquery,
      form = layui.form,
      flow = layui.flow,
      element = layui.element,
      laypage = layui.laypage,
      layer=layui.layer;
	  
	  //建造轮播图实例
	  carousel.render({
		elem: '#test1'
		,width: '100%' //设置容器宽度
		,arrow: 'always' //始终显示箭头
		//,anim: 'fade' //切换动画方式
	  });
	  var userid="${user.getUserid()}";
	  if(userid==""||userid==null){userid=0;}
	  var param=JSON.stringify({'page':1,'userId':userid});
	  //初始化即获得默认番剧数据
	  $.post('/guomanwang/opera/alloperas',{
		  	 param :param
            }, function(res){
		//从后端获得的列表返回在data集合中
		layui.each(res.data, function(index, item){
			var html='<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size: 30px;"></i>|</li>';
			if(item.collecte==1){html='<li class="second"><i class="layui-icon layui-icon-rate-solid shoucang" style="font-size: 30px;color: #FF5722;"></i>|</li>';}
			 $("#result").append(['<div class="layui-col-md2">'
			 			,'<div style="position:relative;" class="list_item">'
						,'<img src="'+item.opPhotourl+'" height="50%">'
						,'<div class="jishu">'+item.opUpdateto+'</div>'
						,'<input type="hidden" class="opvideourl" value="'+item.opIframeurl+'">'
						,'<input type="hidden" class="opurl" value="'+item.opUrl+'">'
						,'<input type="hidden" class="operaid" value="'+item.opId+'">'
				  		,'</div>'
						,'<ul>'
				  			,'<li class="first"><h3 class="layui-elip">'+item.opName+'</h3></li>'
				  			,html
				  			,'<li class="thred">'
								,'<i class="layui-icon fenxiang" style="font-size: 30px;">&#xe641;</i>'
				  			,'</li>'	
				  			,'<li class="layui-word-aux layui-elip last">'+item.opDesc+'</li>'
				  		,'</ul>'
				  	,'<div class="share" >'
					  ,'<table >'
					  	,'<tr><td class="qq"><i class="layui-icon" style="padding-right:3px;">&#xe676;</i>QQ</td></tr>'
					  	,'<tr><td class="weibo"><i class="layui-icon" style="padding-right:3px;">&#xe675;</i>微博</td></tr>'
					  ,'</table>'
					  ,'<div align="center" class="hideshare"><i class="layui-icon">&#xe61a;</i></div>'
			  		,'</div>'
			  ,'</div>'].join(''));
		})
		//分页器相关设置
		laypage.render({
			  elem: 'pager'
			  ,count: res.page
			  ,limit:1
			  ,jump: function(obj, first){
			    
			    //首次不执行
			    if(!first){
			    	$("#result").empty();
			    	param=JSON.stringify({'page':obj.curr,'userId':userid});
			    	console.log(param);
			  	  	$.post('/guomanwang/opera/alloperas',{
			  	  		param :param
			  	  	}, function(result){
			  		//从后端获得的列表返回在data集合中
			  		layui.each(result.data, function(index, item){
						 $("#result").append(['<div class="layui-col-md2">'
					 			,'<div style="position:relative;" class="list_item">'
								,'<img src="'+item.opPhotourl+'" height="50%">'
								,'<div class="jishu">'+item.opUpdateto+'</div>'
								,'<input type="hidden" class="opvideourl" value="'+item.opIframeurl+'">'
								,'<input type="hidden" class="opurl" value="'+item.opUrl+'">'
								,'<input type="hidden" class="operaid" value="'+item.opId+'">'
						  		,'</div>'
								,'<ul>'
						  			,'<li class="first"><h3 class="layui-elip">'+item.opName+'</h3></li>'
						  			,'<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size: 30px;"></i>|</li>'
						  			,'<li class="thred">'
										,'<i class="layui-icon fenxiang" style="font-size: 30px;">&#xe641;</i>'
						  			,'</li>'	
						  			,'<li class="layui-word-aux layui-elip last">'+item.opDesc+'</li>'
						  		,'</ul>'
						  	,'<div class="share" >'
							  ,'<table >'
							  	,'<tr><td class="qq"><i class="layui-icon" style="padding-right:3px;">&#xe676;</i>QQ</td></tr>'
							  	,'<tr><td class="weibo"><i class="layui-icon" style="padding-right:3px;">&#xe675;</i>微博</td></tr>'
							  ,'</table>'
							  ,'<div align="center" class="hideshare"><i class="layui-icon">&#xe61a;</i></div>'
					  		,'</div>'
					  ,'</div>'].join(''));
			  		});
			  		
			  	  })
			  	}
			  }
		});

	  });

	  form.on('radio', function(data){
		  var sort=$("input[name='sort']:checked").val(),
		  	type=$("input[name='type']:checked").val(),
		  	status=$("input[name='status']:checked").val();
		  	$("#result").empty();
		  
		  	var paramstring={'page':1,'userId':userid};
		  	if(sort!=null){$.extend(paramstring, {'sort':sort});}
		  	if(type!=null){$.extend(paramstring, {'type':type});}
		  	if(status!=null){$.extend(paramstring, {'status':status});}
		  	param=JSON.stringify(paramstring);
		  	$.post('/guomanwang/opera/alloperas',{
	  	  		param :param
	  	  	},function(res){
			//从后端获得的列表返回在data集合中
			layui.each(res.data, function(index, item){
				 $("#result").append(['<div class="layui-col-md2">'
			 			,'<div style="position:relative;" class="list_item">'
						,'<img src="'+item.opPhotourl+'" height="50%">'
						,'<div class="jishu">'+item.opUpdateto+'</div>'
						,'<input type="hidden" class="opvideourl" value="'+item.opIframeurl+'">'
						,'<input type="hidden" class="opurl" value="'+item.opUrl+'">'
						,'<input type="hidden" class="operaid" value="'+item.opId+'">'
				  		,'</div>'
						,'<ul>'
				  			,'<li class="first"><h3 class="layui-elip">'+item.opName+'</h3></li>'
				  			,'<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size: 30px;"></i>|</li>'
				  			,'<li class="thred">'
								,'<i class="layui-icon fenxiang" style="font-size: 30px;">&#xe641;</i>'
				  			,'</li>'	
				  			,'<li class="layui-word-aux layui-elip last">'+item.opDesc+'</li>'
				  		,'</ul>'
				  	,'<div class="share" >'
					  ,'<table >'
					  	,'<tr><td class="qq"><i class="layui-icon" style="padding-right:3px;">&#xe676;</i>QQ</td></tr>'
					  	,'<tr><td class="weibo"><i class="layui-icon" style="padding-right:3px;">&#xe675;</i>微博</td></tr>'
					  ,'</table>'
					  ,'<div align="center" class="hideshare"><i class="layui-icon">&#xe61a;</i></div>'
			  		,'</div>'
			  ,'</div>'].join(''));
			})
			//分页器相关设置
			laypage.render({
				  elem: 'pager'
				  ,count: res.page
				  ,limit:1
				  ,jump: function(obj, first){
				    
				    //首次不执行
				    if(!first){
				    	$("#result").empty();
					  	var paramstring={'page':obj.curr,'userId':userid};
					  	if(sort!=null||sort!=""){$.extend(paramstring, {'sort':sort});}
					  	if(type!=null||type!=""){$.extend(paramstring, {'type':type});}
					  	if(status!=null||status!=""){$.extend(paramstring, {'status':status});}
					  	param=JSON.stringify(paramstring);
					  	$.post('/guomanwang/opera/alloperas',{
				  	  		param :param
				  	  	},function(res){
				  		//从后端获得的列表返回在data集合中
				  		layui.each(res.data, function(index, item){
							 $("#result").append(['<div class="layui-col-md2">'
						 			,'<div style="position:relative;" class="list_item">'
									,'<img src="'+item.opPhotourl+'" height="50%">'
									,'<div class="jishu">'+item.opUpdateto+'</div>'
									,'<input type="hidden" class="opvideourl" value="'+item.opIframeurl+'">'
									,'<input type="hidden" class="opurl" value="'+item.opUrl+'">'
									,'<input type="hidden" class="operaid" value="'+item.opId+'">'
							  		,'</div>'
									,'<ul>'
							  			,'<li class="first"><h3 class="layui-elip">'+item.opName+'</h3></li>'
							  			,'<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size: 30px;"></i>|</li>'
							  			,'<li class="thred">'
											,'<i class="layui-icon fenxiang" style="font-size: 30px;">&#xe641;</i>'
							  			,'</li>'	
							  			,'<li class="layui-word-aux layui-elip last">'+item.opDesc+'</li>'
							  		,'</ul>'
							  	,'<div class="share" >'
								  ,'<table >'
								  	,'<tr><td class="qq"><i class="layui-icon" style="padding-right:3px;">&#xe676;</i>QQ</td></tr>'
								  	,'<tr><td class="weibo"><i class="layui-icon" style="padding-right:3px;">&#xe675;</i>微博</td></tr>'
								  ,'</table>'
								  ,'<div align="center" class="hideshare"><i class="layui-icon">&#xe61a;</i></div>'
						  		,'</div>'
						  ,'</div>'].join(''));
				  		});
				  	  })
				  	}
				  }
			});

		  });
	});
	 //番剧搜索
    form.on('submit(search)', function(data){
		//弹出loading
		var key=$("#searchinput").val();
		param=JSON.stringify({'page':1,'name':key,'userId':userid});
		var index = top.layer.msg('正在搜索，请稍候',{icon: 16,time:false,shade:0.8});
	    		$.post('/guomanwang/opera/selectoperabyname',{
	    			param :param
	    		},function(res){
	            	top.layer.msg(res.msg);
	            	setTimeout(function(){
	    	            top.layer.close(index);
	    	            //刷新当前页面
	    	            if(res.code==1){
	    	            	$("#result").empty();
	    	            	$("#pager").fadeOut();
					  		layui.each(res.data, function(index, item){
								 $("#result").append(['<div class="layui-col-md2">'
							 			,'<div style="position:relative;" class="list_item">'
										,'<img src="'+item.opPhotourl+'" height="50%">'
										,'<div class="jishu">'+item.opUpdateto+'</div>'
										,'<input type="hidden" class="opvideourl" value="'+item.opIframeurl+'">'
										,'<input type="hidden" class="opurl" value="'+item.opUrl+'">'
										,'<input type="hidden" class="operaid" value="'+item.opId+'">'
								  		,'</div>'
										,'<ul>'
								  			,'<li class="first"><h3 class="layui-elip">'+item.opName+'</h3></li>'
								  			,'<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size: 30px;"></i>|</li>'
								  			,'<li class="thred">'
												,'<i class="layui-icon fenxiang" style="font-size: 30px;">&#xe641;</i>'
								  			,'</li>'	
								  			,'<li class="layui-word-aux layui-elip last">'+item.opDesc+'</li>'
								  		,'</ul>'
								  	,'<div class="share" >'
									  ,'<table >'
									  	,'<tr><td class="qq"><i class="layui-icon" style="padding-right:3px;">&#xe676;</i>QQ</td></tr>'
									  	,'<tr><td class="weibo"><i class="layui-icon" style="padding-right:3px;">&#xe675;</i>微博</td></tr>'
									  ,'</table>'
									  ,'<div align="center" class="hideshare"><i class="layui-icon">&#xe61a;</i></div>'
							  		,'</div>'
							  ,'</div>'].join(''));
					  		});
	    	            }
	    	            
	    	        },1000);
	            })
	        
	        return false;
	    });
	  $(".layui-row").on('click','.list_item',function(){
		  var opvideourl=$(this).find(".opvideourl").val().replace("==.html",""),
		  	  opurl=$(this).find(".opurl").val();
		  if(opvideourl==null||opvideourl==""){
			  opvideourl=opurl;
		  }
		  var string=opvideourl.replace("id_","");
		    layer.open({
		        type: 2
		        ,title: '视频播放界面'
		        ,area: ['600px','280px']
		        ,shade: 0.8
		        ,maxmin:true
		        ,shadeClose: true
		        ,content: [string,'no']
		      });
	  });
      //收藏
       $(".layui-row").on('click','.shoucang',function(){
		var operaId=$(this).parents(".layui-col-md2").find(".operaid").val();
		if(userid==null||userid==""){
			layer.msg("请先登录再来进行操作！");
		}
		else{
			param=JSON.stringify({'userId':userid,'operaId':operaId});
			$.post('/guomanwang/opera/collectopera',{
				param :param
			},function(data){
	        layer.msg(data.msg);
            setTimeout(function(){
                layer.close();
                if(data.code==1){
             	   location.reload();
                }
            },1500);
	         })

		}
            
      });
      //点击分享
      $(".layui-row").on('click','.thred',function(){
		$(this).parent().parent().find(".share").slideDown("slow");      
      });
      $(".layui-row").on('click','.hideshare',function(){
  		$(this).parent().slideUp("slow");      
        });
	  //分享
	  $(".layui-row").on('click','.qq',function(){
		  var title="纵横国漫网欢迎您！",
	  	  content=$(this).parents(".layui-col-md2").find("h3").text()+'-'+$(this).parents(".layui-col-md2").find(".last").text(),
	  	  url="liujiang.space:8080/guomanwang/common/opera",
	  	  pic="liujiang.space:8080/guomanwang/resource"+$(this).parents(".layui-col-md2").find("img").attr("src").replace("..","");
		  var sharestring="https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+url+"&sharesource=qzone&title="+title+"&pics="+pic+"&summary="+content;
		  window.open(sharestring);
	  });
	  $(".layui-row").on('click','.weibo',function(){
		  var title="[纵横国漫网欢迎您！]",
		  	  content=$(this).parents(".layui-col-md2").find("h3").text()+'-'+$(this).parents(".layui-col-md2").find(".last").text(),
		  	  url="liujiang.space:8080/guomanwang/common/opera",
		  	  pic=$(this).parents(".layui-col-md2").find("img").attr("src").replace("..","");
		  var sharestring="http://service.weibo.com/share/share.php?url="+url+"&sharesource=weibo&title="+title+content+"&pic="+pic;
		  window.open(sharestring);
	  });
	});
	</script>
	</body>
</html>
