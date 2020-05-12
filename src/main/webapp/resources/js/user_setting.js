/**
 * 
 */
layui.use(['upload','layer','form'], function(){
	  var $ = layui.jquery
	  ,upload = layui.upload
	  ,form = layui.form
      ,layer = parent.layer === undefined ? layui.layer : top.layer
      ,$ = layui.jquery;
	  
	  //添加验证规则
	    form.verify({
	    	password: [
	  	      /^[\S]{6,12}$/
	  	      ,'密码必须6到12位，且不能出现空格'
	  	    ],
	        newpassword : [
		  	      /^[\S]{6,12}$/
		  	      ,'新密码必须6到12位，且不能出现空格'
		  	    ],
		  	  confirmpassword: function(value){
	              if(value.length == 0){
	                  return '请再次输入密码！';
	              }
	              else if(value!=$("#L_pass").val()){
	            	  return '两次密码输入不一致！';
	              }
	          }
	    })
	  //普通图片上传
	  var uploadInst = upload.render({
	    elem: '#upload'
	    ,method:'post'
	    ,url: '/guomanwang/user/uploadHeadImage'    
	    ,done: function(res){
	      //如果上传失败
	      if(res.code > 0){
	        return layer.msg('上传失败');
	      }
	      //上传成功 
	      if(res.code==0){
	        $('#mylogo').attr('src',res.data.src);
	        $('.upload-img').text("重新上传");
	        $('#cancel').show();
	        $('#resetheadimage').show();
	        return layer.msg("图片上传成功！",{time:5000});
	        
	      }
	      
	    }
	  });
	  //点击取消头像重新显示上一张头像
	    $("#cancel").click(function(){
	    	layer.confirm('确认取消头像更改吗？',{icon:3, title:'提示信息'},function(index){
	    		$('#mylogo').attr('src',"<c:url value='${user.getHeadurl()}'></c:url>");
	    		setTimeout(function(){
	    			$('#resetheadimage').css('margin-left','135px');
		    		$('#cancel').hide();
		    		$('#resetheadimage').hide();
		    		$('.upload-img').text("上传头像");
		    		layer.close(index);
	            },500)
           });
	    	
	   });
	  form.on('submit(resetinfo)', function(data){
		  $.ajax({
			    url:'/guomanwang/user/updateuserinfo',
			    type: 'post',
			    data: {
				cellphone:$("#L_phone").val(),
		  		username:$("#L_username").val(),
		  		sex: $('#mysex input[name="sex"]:checked ').val(),
		  		introduce:$("#L_sign").val()
			    },
			    success: function (info) {
			    	if(info.code==1){
			    		var msg=info.msg;
			    	}else if(info.code==0){
			    		var msg="用户名已存在，更新失败";
			    	}
			        setTimeout(function () {
			        	location.reload();
			        	}, 1000);
			    	layer.msg(msg);
			    }
			  });
			 return false;
			});
	  form.on('submit(changeheadimage)', function(data){
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		$.ajax({
		    url:'/guomanwang/user/changeheadimage',
		    type: 'post',
		    data: {
		    headimage:$("#mylogo").attr('src').replace("/guomanwang","")
		    },
		    success: function (info) {
		         setTimeout(function () {
		         location.reload();
		         }, 1000);
		    layer.msg(info.msg);
		        }
		  });
		 return false;
		});
	  form.on('submit(changepassword)', function(data){
		  $.ajax({
			    url:'/guomanwang/user/changepassword',
			    type: 'post',
			    data: {
				  password:$("#L_nowpass").val(),
		  		  newpassword:$("#L_pass").val()
			    },
			    success: function (info) {
			    if(info.code==1){
				  setTimeout(function () {
				  location.href="/guomanwang/login";
				  }, 1500);
			  	}
			  layer.msg(info.msg);
			    }
			  });
			 return false;
			});
	  $("a.defaultimage").click(function(){
		    var imageurl=$(this).parent().find(".urladdress").val();
		    layer.confirm('确定使用该头像吗？',{icon:3, title:'提示信息'},function(index){
		    	$.get('/guomanwang/user/selectdefaultheadimage',{
		            imageurl :imageurl
		        },function(data){
		        	if(data.code==1){
						  setTimeout(function () {
							  location.reload();
						  }, 1000);
					  	}
		           layer.msg(data.msg);
		           layer.close(index); 
		        })
		        return false;
		    })
		});
	  $("a.changephone").click(function(){
		  layer.open({
			  type: 1,
			  title:['手机号换绑','text-align:center'],
			  content: $('#phoneset')
			});
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
			    code.html("<b style=\"color:red;\">"+--time+"</b>秒后重新获取");
			    }, 1000);
			    setTimeout(function(){
			    code.removeClass("layui-btn-disabled").text("重新获取验证码");
			    code.attr('disabled',false);
			    clearInterval(set);
			    }, time*1000);
			}
	  $("button#getcode").click(function(){
		  var newphone=$("#L_phone").val();
		  var oldphone=$("span.myphone").text();
		  console.log(oldphone);
		  var reg=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
		  if(reg.test(newphone)&&newphone!=oldphone){
			$.ajax({
			    url:'/guomanwang/sendmsg',
			    type: 'post',
			    data: {
			    	cellphone:newphone,
			    },
			    success: function (info) {
			    	layer.msg(info.msg);
			    	console.log(info);
			    	if(info.code==1){codeButton(60);}	  					  
				 }
			});
		}
		else if(newphone==oldphone){
			layer.msg("您已绑定该手机号！");
		}
		else layer.msg("请输入正确格式的手机号码");
	  });
	  form.on('submit(setphone)', function(data){
		  $.ajax({
			    url:'/guomanwang/setphone',
			    type: 'post',
			    data: {
					cellphone:$("#L_phone").val(),
					vali:$("#L_code").val(),	
			    },
			    success: function (info) {
			    if (info.code === 1) {
			         setTimeout(function () {
			        	 location.reload();
			         }, 1000);
			        }
			       layer.msg(info.msg);
			    }
			  });
			 return false;
			});
	 });