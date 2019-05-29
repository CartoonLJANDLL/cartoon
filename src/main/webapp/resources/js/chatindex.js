layui.use(['element','form','layer'], function(){
		var $ = layui.jquery,
		element = layui.element,
		form = layui.form,
		layer =layui.layer;
		
		var layid = location.hash.replace(/^#myfriend=/, '');
		if(layid!=''){$('.send').attr('data-id',layid);}
		element.tabChange('myfriends', layid);
		  //监听Tab切换，以改变地址hash值
		  element.on('tab(myfriends)', function(){
		    location.hash = 'myfriend='+ this.getAttribute('lay-id');
		    var scrollHeight = $('.chat[data-chat=person'+this.getAttribute('lay-id')+']').prop("scrollHeight");
		    $('.chat[data-chat=person'+this.getAttribute('lay-id')+']').animate({scrollTop:scrollHeight}, 400);
		    $('.send').attr('data-id',this.getAttribute('lay-id'));
		  });
		var user=$(".loginid").val();
		var websocket = null;  
		if ('WebSocket' in window) {  
		    //Websocket的连接  
		    websocket = new WebSocket("ws://liujiang.space:8080/guomanwang/websocket/socketServer");//WebSocket对应的地址  
		}  
		else if ('MozWebSocket' in window) {  
		    //Websocket的连接  
		    websocket = new MozWebSocket("ws://liujiang.space:8080/guomanwang/websocket/socketServer");//SockJS对应的地址  
		}  
		else {  
		    //SockJS的连接  
		    websocket = new SockJS("http://liujiang.space:8080/guomanwang/sockjs/socketServer");    //SockJS对应的地址  
		}  
		websocket.onopen = onOpen;  
		websocket.onmessage = onMessage1;  
		websocket.onerror = onError;  
		websocket.onclose = onClose;  

		function onOpen(openEvt) {  
		    //alert(openEvt.Data);   
		}  
		function onMessage1(evt) {
			var message=evt.data.split("@");
			console.log(message[1]);
			if(message[1]==0){
				layer.msg('[对方已离线]');
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
					,'</div></div>'].join(''));
				}
			    var fid=$('.send').attr("data-id");
				var scrollHeight = $('.chat[data-chat=person'+fid+']').prop("scrollHeight");
				$('.chat[data-chat=person'+fid+']').animate({scrollTop:scrollHeight}, 400);
				}
			});
		//发送消息
		$(document).on('click','.send',function(){
			console.log($(".messages").html());
			var content=$(".messages").html().replace(/<div>/g,""),
			fid=$('.send').attr("data-id");
			if (websocket.readyState == websocket.OPEN) {  
		        websocket.send(fid+"@"+content.replace(/<\/div>/g,""));//调用后台handleTextMessage方法  
		        $('.chat[data-chat=person'+fid+']').append('<div class="bubble me">'+content+'</div>');
		        var scrollHeight = $('.chat[data-chat=person'+fid+']').prop("scrollHeight");
				$('.chat[data-chat=person'+fid+']').animate({scrollTop:scrollHeight}, 400);
		        $(".messages").empty();
			} else {  
		    	alert("连接失败!"+websocket.readyState);  
		    } 
		})
	    //回车键发送消息
		$(document).on('keyup','.messages',function(){
			var content=$(".messages").html(),
			fid=$('.send').attr("data-id");
			 if (event.keyCode == 13) {
			 websocket.send(fid+"@"+content.replace('<br>',''));//调用后台handleTextMessage方法  
		     $('.chat[data-chat=person'+fid+']').append('<div class="bubble me">'+content.replace('<br>','')+'</div>');
	        var scrollHeight = $('.chat[data-chat=person'+fid+']').prop("scrollHeight");
			$('.chat[data-chat=person'+fid+']').animate({scrollTop:scrollHeight}, 400);
		     $(".messages").empty();
			 }
		})
		//鼠标悬浮在好友上方显示相关操作
		$(".person .layui-icon").click(function(){
			$(this).parent().find(".layui-nav-child").slideToggle("slow");
	  	});
		 //删除好友与拒绝好友申请共用同一事件
	    $(document).on('click', '#deletefriend', function(data){
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