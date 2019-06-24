  layui.config({
	  base: '/guomanwang/resources/res/mods/' //你存放新模块的目录，注意，不是layui的模块目录
}).use('index'); //加载入
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
	 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img width='200px' src='/guomanwang/resources/img/wechat.jpg'></div>"
	 	 		},{
	 	 			title : "QQ",
	 	 			content : "<div style='padding:30px;overflow:hidden;background:#d2d0d0;'><img width='200px' src='/guomanwang/resources/img/qq.jpg'></div>"
	 	 		}]
	 	 	})
	 	 });
		if(location.pathname!="/guomanwang/user/user_friends"&$("#username").text()!=""){
			var websocket = null;  
			if ('WebSocket' in window) {  
			    //Websocket的连接  
			    websocket = new WebSocket("ws://"+window.location.host+"/guomanwang/websocket/socketServer");//WebSocket对应的地址  
			}  
			else if ('MozWebSocket' in window) {  
			    //Websocket的连接  
			    websocket = new MozWebSocket("ws://"+window.location.host+"/guomanwang/websocket/socketServer");//SockJS对应的地址  
			}  
			else {  
			    //SockJS的连接  
			    websocket = new SockJS("http://"+window.location.host+"/guomanwang/sockjs/socketServer");    //SockJS对应的地址  
			}    
			websocket.onmessage = onMessage;  
	 
			function onMessage(evt) {
				var message=evt.data.split("@");
		    		layer.open({
			            type: 1
			            ,offset: 'b'
			            ,id: 'layerDemob'
			            ,content: '<div style="padding: 20px 100px;">您有新的好友消息</div>'
			            ,btn: ['查看','关闭']
			            ,btnAlign: 'c' //按钮居中
			            ,shade: 0 //不显示遮罩
			            ,yes: function(){
			            	location.href="guomanwang/user/user_friends#myfriend="+message[0];
			            }
			    		,btn2: function(index, layero){
			    			layer.closeAll("page");
			    		  }
			          });
		    		$(".icon-tongzhi").append('<span class="layui-badge-dot"></span>');
			} 			
		}
 
		$(".fly-nav li.layui-this").removeClass("layui-this");
		if(location.pathname=="/guomanwang/index"||location.pathname=="/index"){
			$(".layui-nav li.index").addClass("layui-this");
		}
		else if(location.pathname=="/guomanwang/news"){
			$(".layui-nav li.news").addClass("layui-this");
		}
		else if(location.pathname=="/guomanwang/opera"){
			$(".layui-nav li.opera").addClass("layui-this");
		}
		else if(location.pathname=="/guomanwang/luntan"||location.pathname.substring(0,18)=="/guomanwang/thread"||location.pathname.substring(0,18)=="/guomanwang/commit"){
			$(".layui-nav li.luntan").addClass("layui-this");
		}
		else if(location.pathname.substring(0,16)=="/guomanwang/user"){
			$(".layui-nav li.user").addClass("layui-this");
		}
		else if(location.pathname=="/guomanwang/login"){
			$(".layui-nav li.login").addClass("layui-this");
		}
		else if(location.pathname=="/guomanwang/register"){
			$(".layui-nav li.register").addClass("layui-this");
		}
		
		$(".loginout").click(function(){
        	layer.confirm('确定退出登录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post('/guomanwang/user/logout',function(data){
                })
                setTimeout(function(){
                    location.reload();
                },500);
                return false;
        	});
		})
		//自定义验证规则，包括用户名。
	      form.verify({
	          username: function(value){
	        	  if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	        		  return '用户名不能有特殊字符';
	        	    }
	        	  else if(/(^\_)|(\__)|(\_+$)/.test(value)){
	        	      return '用户名首尾不能出现下划线\'_\'';
	        	    }
	        	  else if(value.length==0){
	        	      return '请输入用户名！';
	        	    }
	        	    else if(/^\d+\d+\d$/.test(value)){
	        	      return '用户名不能全为数字';
	        	    }
	        	  }
	          ,password: [
	    	      /^[\S]{6,12}$/
	    	      ,'密码必须6到12位，且不能出现空格'
	    	    ]
	          ,confirm_password: function(value){
	              if(value.length == 0){
	                  return '请再次输入密码！';
	              }
	              else if(value!=$("#L_pass").val()){
	            	  return '两次密码输入不一致！';
	              }
	          }
	          ,code: function(value){
	              if(value.length == 0){
	                  return '验证码不能为空';
	              }
	          }  
	      });
		 $("#sendcode").click(function(){
			 var cellphone=$("#L_phone").val();
				var reg=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if(reg.test(cellphone)){
					$.ajax({
						    url:'/guomanwang/sendmsg',
						    type: 'post',
						    data: {
							cellphone:$("#L_phone").val(),
						    },
						    success: function (info) {
							  setTimeout(function () {
								  if(info.code==2){
								location.href = "login";
								}
								  else if(info.code==1){
									  codeButton(60);
							  }
							}, 1000); 
						  layer.msg(info.msg);
						  }
						  });
				}
				else if(cellphone.length==0){
					layer.msg("请输入手机号再点击发送验证码");
				}
				else layer.msg("请输入正确格式的手机号码");
		 });
		//找回密码页面的发送验证码
		 $("#sendmsg").click(function(){
			 var cellphone=$("#phone").val();
				var reg=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if(reg.test(cellphone)){
					$.ajax({
						    url:'/guomanwang/sendcode',
						    type: 'post',
						    data: {
							cellphone:cellphone,
						    },
						    success: function (info) {
						    	if(info.code==1){codeButton(60);}
								  layer.msg(info.msg);					  
						  }
						  });
				}
				else if(cellphone.length==0){
					layer.msg("请输入手机号再点击发送验证码");
				}
				else layer.msg("请输入正确格式的手机号码");
		 });
		 form.on('submit(resetpassword)', function(data){
			  $.ajax({
				    url:'/guomanwang/resetpassword',
				    type: 'post',
				    data: {
					cellphone:$("#phone").val(),
					vali:$("#L_code").val(),	
			  		password:$("#L_pass").val(),
				    },
				    success: function (info) {
				    if (info.code === 1) {
				         setTimeout(function () {
				         location.href = "login";
				         }, 2000);
				        }
				       layer.msg(info.msg);
				    }
				  });
				 return false;
				});
		 form.on('submit(zhuce)', function(data){
			  $.ajax({
				    url:'/guomanwang/zhuce',
				    type: 'post',
				    data: {
					cellphone:$("#L_phone").val(),
					username:$("#L_username").val(),
					vali:$("#L_code").val(),	
			  		password:$("#L_pass").val(),
				    },
				    success: function (info) {
				    if (info.code === 1) {
				         setTimeout(function () {
				         location.href = "login";
				         }, 2000);
				        }
				       layer.msg(info.msg);
				    }
				  });
				 return false;
				});
		 //判断本地保存的验证码倒计时
		 var LocalDelay = getLocalDelay();
		    var timeLine = parseInt((new Date().getTime() - LocalDelay.time) / 1000);
		    if (timeLine > LocalDelay.delay) {
		        console.log("倒计时过期");
		    } else {
		        _delay = LocalDelay.delay - timeLine;
		        codeButton(_delay);
		    }
		 //设置setLocalDelay
		 function setLocalDelay(delay) {
		     //location.href作为页面的唯一标识，可能一个项目中会有很多页面需要获取验证码。
		     localStorage.setItem("delay_" + location.href, delay);
		     localStorage.setItem("time_" + location.href, new Date().getTime());
		 }

		 //getLocalDelay()
		 function getLocalDelay() {
		     var LocalDelay = {};
		     LocalDelay.delay = localStorage.getItem("delay_" + location.href);
		     LocalDelay.time = localStorage.getItem("time_" + location.href);
		     return LocalDelay;
		 }
		 //点击获取验证码后倒计时
			function codeButton(time){
			    var code = $("#sendcode");
			    if(location.pathname=="/guomanwang/forget"){code = $("#sendmsg");}
			    code.addClass("layui-btn-disabled");
			    code.attr('disabled',true);
			    var set=setInterval(function(){
			    setLocalDelay(time);
			    code.html("(<b>"+--time+"</b>)秒后重新获取");
			    }, 1000);
			    setTimeout(function(){
			    code.removeClass("layui-btn-disabled").text("重新获取验证码");
			    code.attr('disabled',false);
			    clearInterval(set);
			    }, time*1000);
			}
	      form.on('submit(islogin)', function(data){
			  $.ajax({
				    url:'/guomanwang/islogin',
				    type: 'post',
				    data: {
					telnumber:$("#L_phone").val(),
			  		password:$("#L_pass").val(),
				    },
				    success: function (info) {
				    if (info.code === 1) {
				         setTimeout(function () {
				         location.href = "index";
				         }, 2000);
				        }
				       layer.msg(info.msg);
				    }
				  });
				 return false;
				});
		      
	})