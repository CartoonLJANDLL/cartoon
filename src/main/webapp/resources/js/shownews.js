layui.use(['util', 'laydate', 'layer'], function(){
  var $=layui.jquery
  ,util = layui.util
  ,laydate = layui.laydate
  ,layer = layui.layer;
  
  	//点击资讯链接，点击量加一
	$(document).on('click','.newslink',function(){
		var newsid=$(this).attr("data-id");
		console.log(newsid);
		$.ajax({
			url:'addviewcount', 
			type:'post',
			data:{
				newsid:newsid
			},
			success:function(res){
				console.log(res.msg);
				}
			});
	})
})
