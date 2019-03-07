$(document).on("focus","#cellphone",function(){
	$(".alert-danger").css("display","none")
});
//松开键盘判断用户名输入框的值
$(document).on("keyup","#username",function(){
	var username=$("#username").val();
	var reg=/^[0-9a-zA-Z_-]{6,15}$/;
	if(reg.test(username)){
		$("#username_err").html("");
//		$.ajax({
//	        //直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
//	        type : "get",
//	        url : "zhuce", 
//	        data :{
//	        	"username":username,//获取用户名
//	        },
//	        success : function(Result)
//	        {
//	        	//Result为错误提示信息
//	        	if(Result!=null){
//	        		$("#username_err").html(Result);
//	        	}
//	        	
//	        },
//	        error : function(xhr, status, errMsg)
//	        {
//	             alert("数据传输失败!");
//	        }
//	    }); 
	}
	else if(username==""||username==null){
		$("#username_err").html("请输入用户名！");
		}
	else if(!reg.test(username)){
		$("#username_err").html("用户名格式或长度不符合要求");
		}
		
	});
//松开键盘判断密码输入框的值
$(document).on("onblur","#password",function(){
	var psd=$("#password").val();
	alert(psd);
	var reg=/^[a-zA-Z][a-zA-Z0-9_]{5,14}$/;
	if(psd==""||psd==null){
		$("#password_err").html("请输入密码！");
		}
	else if(!reg.test(psd)){
		$("#password_err").html("密码格式或长度不符合要求，请输入首位为英文字母的6-15位的密码!");
	}
	else
		$("#password_err").html("");
	});
//点击获取验证码查询手机号是否符合规范并判断是否已注册并发送验证码
$(document).on("click","#get_code",function(){
	var cellphone=$("#cellphone").val();
	alert(cellphone);
	var reg=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
	//符合手机号规范则进行交互
	if(reg.test(cellphone)){
		$.ajax({
	        //直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
	        type : "post",
	        url : "/guomanwang/user/sendMsg",
	        data :{
	        	"cellphone":cellphone//获取手机号
	        },
	        success : function(Result)
	        {
	        	//Result为错误提示信息
	        	if(Result!=null){
	        		$(".alert-danger").html(Result);
	        		$(".alert-danger").css("display","block");
	        	}
	        	
	        },
	        error : function(xhr, status, errMsg)
	        {
	             alert("数据传输失败!");
	        }
	    }); 
	}
	else if(cellphone==""||cellphone==null){
		alert("h2");
		$(".alert-danger").html("请输入手机号！");
		$(".alert-danger").css("display","block");
		}
	else
		alert("h3");
		$(".alert-danger").html("请输入正确的手机号！");
		$(".alert-danger").css("display","block");
});
//松开键盘判断二次密码输入框的值
$(document).on("keyup","#confirm_password",function(){
	var confirm_password=$("#confirm_password").val();
	var password=$("#password").val();
	alert(confirm_password+password);
	if(confirm_password==""||confirm_password==null){
		$("#confirm_password_err").html("请再此输入密码以确认！");
		}
	else if(confirm_password!=password){
		$("#confirm_password_err").html("两次密码输入不一致！");
		}
	else
		$("#confirm_password_err").html("");
	});
