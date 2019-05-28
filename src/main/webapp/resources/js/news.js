layui.use(['element','form','layer'], function(){
		  var $ = layui.jquery,
		  	element = layui.element,
		  	form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer;
		  
		 	 //联系弹窗
		 	 $("#contectus").click(function(){
		 	 	layer.tab({
		 	 		area : ['260px', '367px'],
		 	 		tab : [{
		 	 			title : "微信",
		 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../images/wechat.jpg'></div>"
		 	 		},{
		 	 			title : "QQ",
		 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img src='../images/alipay.jpg'></div>"
		 	 		}]
		 	 	})
		 	 }
		 	 )
		  form.on('submit(searchnews)', function(data){
				 $.ajax({
					    url:'/guomanwang/searchnews',
					    type: 'post',
					    data: {
				  		  key:$("#newskey").val()
					    },
					    success: function (res) {
							if(res.code==1){
								  var lis=[];
								  layui.each(res.data, function(index, item){
									  if(item.company=='娃娃鱼动画'){
										  lis.push('<li><div class="layui-col-sm9"><a href="'+item.url+'" target="_blank">'+item.title +
												  '</a></div><div class="layui-col-sm3"><span style="text-align: right;">时间：'+item.time+'</span></div></li>');
									  }
									  else{
										  lis.push('<li><div class="layui-col-sm9"><a href="'+item.url+'" target="newsbody">'+item.title +
												  '</a></div><div class="layui-col-sm3"><span style="text-align: right;">时间：'+item.time+'</span></div></li>');
									  }
									})
								    layer.open({
								        type: 0
								        ,title: '查询结果'
								        ,area: '800px'
								        ,shade: 0.8
								        ,shadeClose: true
								        ,content: ['<div class="layui-text" style="padding: 20px;">'
								          ,'<blockquote class="layui-elem-quote">查询结果如下（'+res.msg+'）</blockquote>'
								          ,'<ul class="searchreasult">'
								          ,lis
								          ,'</ul>'
								        ,'</div>'].join('')
								      });
							}
							else{
								layer.msg(res.msg);
							}
					    }
					  });
			        return false;
			    });
		  $('body').on('click', '.searchreasult li a', function(){
			  layer.closeAll('dialog');
		  });
		 	//手机设备的简单适配
		     $('.site-tree-mobile').on('click', function(){
		 		$('body').addClass('site-mobile');
		 	});
		     $('.site-mobile-shade').on('click', function(){
		 		$('body').removeClass('site-mobile');
		 	});
		});