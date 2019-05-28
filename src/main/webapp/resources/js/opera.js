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
		,height:'417'
		,interval:'5000'
		,arrow: 'always' //始终显示箭头
		//,anim: 'fade' //切换动画方式
	  });
	  function addhtml(item){
		var html='<li class="second"><i class="layui-icon layui-icon-rate shoucang" style="font-size:20px;"></i>|</li>';
		if(item.collecte==1){html='<li class="second"><i class="layui-icon layui-icon-rate-solid shoucang" style="font-size: 20px;color: #FF5722;"></i>|</li>';}
		 $("#result").append(['<div class="layui-col-lg2 layui-col-sm3 	layui-col-xs4 operaimg">'
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
							,'<i class="layui-icon fenxiang" style="font-size: 20px;">&#xe641;</i>'
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
	  }
	  var userid=$("#username").attr("data-id");
	  if(userid==""||userid==null){userid=0;}
	  var param=JSON.stringify({'page':1,'userId':userid});
	  //初始化即获得默认番剧数据
	  $.post('/guomanwang/opera/alloperas',{
		  	 param :param
            }, function(res){
		//从后端获得的列表返回在data集合中
		layui.each(res.data, function(index, item){
			addhtml(item);
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
			  			addhtml(item);
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
				addhtml(item);
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
				  			addhtml(item);
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
					  			addhtml(item);
					  		});
	    	            }
	    	            
	    	        },1000);
	            })
	        
	        return false;
	    });
	  $(".layui-row").on('click','.list_item',function(){
		  var opvideourl=$(this).find(".opvideourl").val().replace("==.html",""),
		  	  opurl=$(this).find(".opurl").val();
		      opid=$(this).find(".operaid").val();
		  if(opvideourl==null||opvideourl==""){
			  opvideourl=opurl;
		  }
		  var string=opvideourl.replace("id_","");
		    var index=layer.open({
		        type: 2
		        ,title: '视频播放界面'
		        ,area: ['600px','280px']
		        ,shade: 0
		        ,maxmin:true
		        ,shadeClose: true
		        ,content: [string,'no']
		      });
		    layui.layer.full(index);
		    console.log(opid);
		    $.post('/guomanwang/opera/playcount',{//后台对指定番剧id进行播放量的增加等操作
    			operaid:opid //传给后台的番剧id
    		},function(res){//回调函数
    			
    		});
	  });
      //收藏
       $(".layui-row").on('click','.shoucang',function(){
		var operaId=$(this).parents(".operaimg").find(".operaid").val();
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
	  	  content=$(this).parents(".operaimg").find("h3").text()+'-'+$(this).parents(".operaimg").find(".last").text(),
	  	  url="liujiang.space:8080/guomanwang/common/opera",
	  	  pic="liujiang.space:8080/guomanwang/resource"+$(this).parents(".operaimg").find("img").attr("src").replace("..","");
		  var sharestring="https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url="+url+"&sharesource=qzone&title="+title+"&pics="+pic+"&summary="+content;
		  window.open(sharestring);
	  });
	  $(".layui-row").on('click','.weibo',function(){
		  var title="[纵横国漫网欢迎您！]",
		  	  content=$(this).parents(".operaimg").find("h3").text()+'-'+$(this).parents(".operaimg").find(".last").text(),
		  	  url="liujiang.space:8080/guomanwang/common/opera",
		  	  pic=$(this).parents(".operaimg").find("img").attr("src").replace("..","");
		  var sharestring="http://service.weibo.com/share/share.php?url="+url+"&sharesource=weibo&title="+title+content+"&pic="+pic;
		  window.open(sharestring);
	  });
	});