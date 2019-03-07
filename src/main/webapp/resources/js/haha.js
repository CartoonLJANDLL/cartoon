function deletethread(id){
	layer.confirm('确认删除?此操作无法撤销', {icon: 3, title:'提示'}, function(index){
		$.ajax({
	        //直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
	        type : "post",
	        url : "/guomanwang/user/deletethread", 
	        data :{
	        	"id":id,//获取帖子id
	        },
	        success : function(Result)
	        {
	        	//Result为错误提示信息
	        	alert(Result);
	        	
	        },
	        error : function(xhr, status, errMsg)
	        {
	             alert("数据传输失败!");
	        }
	    }); 		  
		  layer.close(index);
		});
	}