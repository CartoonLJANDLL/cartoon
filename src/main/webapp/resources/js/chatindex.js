layui.use(['element','form','layer'], function(){
		var $ = layui.jquery,
		element = layui.element,
		form = layui.form,
		layer =layui.layer;

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
			if(location.pathname=="/guomanwang/user/user_friends"||location.pathname=="/guomanwang/user/user_message"){
			if(message[1]==0){
				$('.chat[data-chat=person'+message[0]+']').find("div").last().innerHTML+='[对方已离线]';
				}
			else{
				$('.chat[data-chat=person'+message[0]+']').append('<div class="bubble you">'+message[1]+'</div>');
				}
			}
			else{
	    		layer.open({
		            type: 1
		            ,offset: 'b'
		            ,id: 'layerDemob'
		            ,content: '<div style="padding: 20px 100px;">您有新的好友消息</div>'
		            ,btn: '关闭'
		            ,btnAlign: 'c' //按钮居中
		            ,shade: 0 //不显示遮罩
		            ,yes: function(){
		              layer.closeAll("page");
		            }
		          }); 
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
				if(i==0){show="layui-show";}
				$(".layui-tab-content").append(['<div class="layui-tab-item '+show+'"><div class="right"><div class="chat" data-chat="person'+res[i].fid+'">'
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
		$(document).on('click','.send',function(){
			var content=$(this).parent().find(".messages").val(),
				fid=$(this).attr("data-id");
			$(this).parent().find(".messages").val("");
			if (websocket.readyState == websocket.OPEN) {  
		        websocket.send(fid+"@"+content);//调用后台handleTextMessage方法  
		        $(this).parent().parent().find(".chat").append('<div class="bubble me">'+content+'</div>');
		        $(".bubble me").last().focus();
		    } else {  
		    	alert("连接失败!"+websocket.readyState);  
		    } 
		})
		$(document).on('click', '#agree', function(data) {
        var friendid = $(this).attr('data-id');
            $.post("../user/passfriend",{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           if(data.code==1){
        	  location.reload();
           }
         })
    });
	 //删除好友与拒绝好友申请共用同一事件
    $(document).on('click', '#deletefriend,#refuse', function(data) {
        var friendid = $(this).attr('data-id');
        var link=$(this).attr('id');
        if(link=='deletefriend'){
        	message='确定与该用户解除好友关系吗？该操作是双向的！';
        }
        else{
        	message='确认拒绝该好友请求吗？'
        }
		layer.confirm(message, {icon: 3, title: '提示信息'}, function (index) {
            $.get('<c:url value="/user/deletefriend"></c:url>',{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           setTimeout(function(){
               top.layer.close(index);
               if(data.code==1){
            	   location.reload();
               }
           },1500);
            })
           
            return false;
    	});
    })

})