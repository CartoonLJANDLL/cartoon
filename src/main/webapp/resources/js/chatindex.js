layui.use(['element','form','layer'], function(){
		var $ = layui.jquery,
		element = layui.element,
		form = layui.form,
		layer =layui.layer;
		
		var layid = location.hash.replace(/^#myfriend=/, '');
		element.tabChange('myfriends', layid);
		  //监听Tab切换，以改变地址hash值
		  element.on('tab(myfriends)', function(){
		    location.hash = 'myfriend='+ this.getAttribute('lay-id');
		    var scrollHeight = $('.chat[data-chat=person'+this.getAttribute('lay-id')+']').prop("scrollHeight");
		    $('.chat[data-chat=person'+this.getAttribute('lay-id')+']').animate({scrollTop:scrollHeight}, 400);
		  });
		var user=$(".loginid").val();
		var websocket = null;  
		if ('WebSocket' in window) {  
		    //Websocket的连接  
		    websocket = new WebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//WebSocket对应的地址  
		}  
		else if ('MozWebSocket' in window) {  
		    //Websocket的连接  
		    websocket = new MozWebSocket("ws://localhost:8080/guomanwang/websocket/socketServer");//SockJS对应的地址  
		}  
		else {  
		    //SockJS的连接  
		    websocket = new SockJS("http://localhost:8080/guomanwang/sockjs/socketServer");    //SockJS对应的地址  
		}  
		websocket.onopen = onOpen;  
		websocket.onmessage = onMessage;  
		websocket.onerror = onError;  
		websocket.onclose = onClose;  

		function onOpen(openEvt) {  
		    //alert(openEvt.Data);   
		}  
		function onMessage(evt) {
			var message=evt.data.split("@");
			if(message[1]==0){
				$('.chat[data-chat=person'+message[0]+']').find("div").last().innerHTML+='[对方已离线]';
				}
			else{
				$('.chat[data-chat=person'+message[0]+']').append('<div class="bubble you">'+message[1]+'</div>');
				var scrollHeight = $('.chat[data-chat=person'+message[0]+']').prop("scrollHeight");
				$('.chat[data-chat=person'+message[0]+']').animate({scrollTop:scrollHeight}, 400);
				}
		}  
		function onError() {  
		}  
		function onClose() {  
		}   

		window.close = function () {  
		    websocket.onclose();  
		}
		$.ajax({
			url:'/guomanwang/user/getfriendsmsgsbyuserid', 
			type:'post',
			contentType:'application/json;charset=utf-8',
			success:function(res){
			//从后端获得的列表返回在data集合中
			for(var i in res){
				var html='',show='';
				for(var j in res[i].data){
					var string='bubble you';
					if(res[i].data[j].senderid==user){string='bubble me';}
					html+='<div class="'+string+'">'+res[i].data[j].content+'</div>';
				}
				if(layid==res[i].fid||(i==0&layid=='')){show="layui-show";}
				$(".layui-tab-content").append(['<div class="layui-tab-item '+show+'" data-chat="person'+res[i].fid+'"><div class="right"><div class="chat" data-chat="person'+res[i].fid+'">'
					,html
					,'</div>'
					,'<hr>'
					,'<div class="write">'
					,'<a href="javascript:;" class="write-link smiley"></a>'
					,'<input type="text" class="messages"/>'
					,'<a href="javascript:;" data-id="'+res[i].fid+'" class="write-link send"></a>'
					,'</div>'
					,'</div></div>'].join(''));
				}
				}
			});
		//发送消息
		$(document).on('click','.send',function(){
			var content=$(this).parent().find(".messages").val(),
				fid=$(this).attr("data-id");
			$(this).parent().find(".messages").val("");
			if (websocket.readyState == websocket.OPEN) {  
		        websocket.send(fid+"@"+content);//调用后台handleTextMessage方法  
		        $(this).parent().parent().find(".chat").append('<div class="bubble me">'+content+'</div>');
		    } else {  
		    	alert("连接失败!"+websocket.readyState);  
		    } 
		})
		//鼠标悬浮在好友上方显示相关操作
		$(".person").hover(function(){
			$(this).find(".layui-nav-child").show(); 		
	  	},function(){
			$(this).find(".layui-nav-child").hide(); 
	  	});
		 //删除好友与拒绝好友申请共用同一事件
	    $(document).on('click', '#deletefriend', function(data) {
	        var friendid = $(this).attr('data-id');
			layer.confirm('确定与该用户解除好友关系吗？该操作是双向的！', {icon: 3, title: '提示信息'}, function (index) {
	            $.get("../user/deletefriend",{
	            	friendid:friendid
	            },function(data){
	           layer.msg(data.msg);
	           setTimeout(function(){
	               top.layer.close(index);
	               if(data.code==1){
	            	   location.reload();
	               }
	           },1000);
	            })
	           
	            return false;
	    	});
	    })
})