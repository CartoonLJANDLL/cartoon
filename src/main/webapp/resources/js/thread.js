layui.use(['layedit','upload','layer','form'], function(){
	  var $ = layui.jquery
	  ,form = layui.form
	  ,layedit=layui.layedit
    ,layer = parent.layer === undefined ? layui.layer : top.layer;
	  
	  layedit.set({
		  uploadImage: {
		    url: '../user/uploadHeadImage?src=/resources/img/thread' //接口url
		    ,type: '' //默认post
		  }
		});
	  var one=layedit.build('editor'); //建立编辑器
	  var userid=$("#islogin #username").attr("data-id");
	  var threadid=$(".detail-about").attr("data-id");
form.on('submit(addcommit)', function(data){
	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    		$.post("addcommit",{
    			threadId:threadid,
    			userId:userid,
    			content:layedit.getContent(one),
    			parentId:$("#parentid").val(),
    		},function(res){
            	top.layer.msg(res.msg);
            })
        setTimeout(function(){
            top.layer.close(index);
            location.reload();
        },2000);
        return false;
    });
    $(document).ready(function(){
        $("span#delcommit").click(function(){
        	var commitid=$(this).find(".commitid").val()
        	layer.confirm('确定删除该评论？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("../commit/deletecommit",{
               	 commitId :commitid
                },function(data){
               layer.msg(data.msg);
                })
                setTimeout(function(){
                    top.layer.close(index);
                    location.reload();
                },1500);
                return false;
        	});
        })
    	$("span#reply").click(function(){
            var aite = '@'+ $(this).find(".replyname").text().replace(/\s/g, '');
            $("#parentid").val($(this).attr("data-id"));
            layedit.setContent(one, aite,true);
            console.log($("#parentid").val());
        })
        //点赞
        $("span#zan").click(function(){
        	var commitid=parseInt($(this).find(".icon-zan").attr("id"));
        	var zanclass=$(this).attr("class");
        	var zannumber=parseInt($(this).find("#emoo").text())+1;
        	if(zanclass=="jieda-zan"){
        		$.post("dianzan",{
                 	 commitid :commitid,
                 	 userid:userid
                  },function(data){
                 layer.msg(data.msg);
                 if(data.code==1){
                     setTimeout(function(){
                    	 location.reload();
                     },2000);
                     };
                  })
        	}
        	else{
        		$.post("cancelzan",{
                	 commitid :commitid,
                	 userid:userid
                 },function(data){
                layer.msg(data.msg);
                if(data.code==1){
                    setTimeout(function(){
                   	 location.reload();
                    },2000);
                    };
                 })
        	}   
        })
        //设置帖子为精贴
        $("#setstatus").click(function(){
        	layer.confirm('确定将该贴子设置为精华帖吗？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("../thread/updateThread",{
               	 status :1,
               	 id:threadid
                },function(data){
               layer.msg(data.msg);
                })
                setTimeout(function(){
                    top.layer.close(index);
                    location.reload();
                },1500);
                return false;
        	});
        })
        $("#delthread").click(function(){
        	layer.confirm('确定删除该帖吗？该操作无法撤销！', {icon: 3, title: '提示信息'}, function (index) {
                $.get("../thread/deletethread",{
               	 id:threadid
                },function(data){
               layer.msg(data.msg);
               setTimeout(function(){
                   top.layer.close(index);
                   if(data.code==1){
                	   location.href="/guomanwang/thread/block_index?blockId="+$(".fly-column").attr("data-id");
                   }
               },1500);
                })
               
                return false;
        	});
        })
    });
})