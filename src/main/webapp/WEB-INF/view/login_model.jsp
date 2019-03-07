<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='<c:url value="/resources/css/bootstrap.min.css"></c:url>' rel="stylesheet" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<script type="text/javascript" src='<c:url value="/resources/js/bootstrap.min.js"></c:url>'></script>
<link href='<c:url value="/resources/css/menu-header.css"></c:url>' rel="stylesheet" />
<title>登录窗口</title>
			<script>
			$(document).on("focus","#password,#telnumber",function(){
				$("#err_message").hide();
			});
			$(document).on("keyup","#password",function(){
				var password=$("#password").val();
				var reg=/^[0-9a-zA-Z]{6,15}$/;
				if(password==""||password==null){
					$("#psd_err").html("请输入密码！");
					$("#psd_err").show();
					}
				else if(!reg.test(password)){
					$("#psd_err").html("密码格式不正确，请重新输入！");
					$("#psd_err").show();
					}
				else
					$("#psd_err").html("");
				});
			$(document).on("blur","#telnumber",function(){
				var telphone_number=$("#telnumber").val();
				var reg1=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if(telphone_number==""||telphone_number==null){
					$("#telphone_number_err").html("请输入注册时的手机号！");
					$("#telphone_number_err").show();
				}
				else if(!reg1.test(telphone_number)){
					$("#telphone_number_err").html("手机号格式不正确，请重新输入！");
					$("#telphone_number_err").show();
				}
				else
					$("#telphone_number_err").html("");
				});
			$(document).on("focus","#tenumber",function(){
				var telphone_number=$("#telnumber").val();
				var reg1=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if(telphone_number==""||telphone_number==null){
					$("#telphone_number_err").html("请输入手机号！");
					$("#telphone_number_err").show();
				}
				else if(!reg1.test(telphone_number)){
					$("#telphone_number_err").html("手机号格式不正确，请重新输入！");
					$("#telphone_number_err").show();
				}
				else
					$("#telphone_number_err").html("");
				});
		<!--	$(document).on("keyup","#telphone_number",function(){
					var telphone_number=$("#telphone_number").val();
					var reg1=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
					if(telphone_number==""||telphone_number==null){
						$("#telphone_number_err").html("请输入手机号！");
						$("#telphone_number_err").show();
					}
					else if(!reg1.test(telphone_number)){
						$("#telphone_number_err").html("手机号格式不正确，请重新输入！");
						$("#telphone_number_err").show();
					}
					else
						$("#telphone_number_err").html("");
					});
					$(document).on("click","#denglu",function(){
					  var telphone_number=$("#telphone_number").val().trim();
					  var psd=$("#psd").val().trim();
					  alert(telphone_number+psd);
					  var telphone_number_err=$("#telphone_number_err").text();
					  var psd_err=$("#psd_err").text();
					  if(telphone_number!=""&&psd!=""&&telphone_number_err==""&&psd_err=="")  
						  $.ajax({
						        //直接"post"或者"get",不需要"doPost","doGet"，该函数到后端接收缓冲区会自动匹配
						        type : "get",      
						        //servlet文件名为Calculator，需要提前在web.xml里面注册
						        url : "login", 
						        data :{
						        	"telphone_number":telphone_number,
						            "psd":psd,    
						        },
						        success : function(Result)
						        {
						        	$("#err_message").html(Result);
						        	$("#err_message").show();
						        },
						        error : function(xhr, status, errMsg)
						        {
						             alert("数据传输失败!");
						        }
						    }); 
					});-->	
		</script>
</head>
<body>
	<!-- 登录模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog text-center model-sm">
						<div class="modal-content text-center">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
								</button>
								<h4 class="modal-title" id="myModalLabel">
									登录窗口
								</h4>
							</div>
							<form id="form" action="/guomanwang/user/islogin" method="post">
							<div class="modal-body">
									<img src='<c:url value="/resources/img/LOGO.png"></c:url>' width="150px" height="50px"/>
								<div class="row">
									<div class="col-md-4 col-md-offset-4"><input type="text" id="telnumber" class="form-control" name="telnumber" placeholder="请输入手机号"/></div>
									<div class="col-md-4 text-left"><label id="telphone_number_err" class="alert alert-warning"></label></div>
								</div><br />
								<div class="row">
									<div class="col-md-4 col-md-offset-4"><input type="password" id="password" class="form-control" name="password" placeholder="请输入密码"/></div>
									<div class="col-md-4 text-left"><label id="psd_err" class="alert alert-warning"></label></div>
								</div>
								<div class="row">
									<div class="col-md-6 col-md-offset-3"><input type="checkbox"/> 下次自动登录&nbsp;&nbsp;&nbsp;<a href="#">忘记密码?</a></div>
								</div>
								<label  id="err_message" class="alert alert-warning"></label>
							</div>
							<div class="modal-footer" style="text-align:center;">
								<button type="reset" class="btn btn-default">
									清空
								</button>
								<button type="submit" class="btn btn-primary" id="denglu">
									登录
								</button>
							</div>
							</form>
						</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			<script>
			$(function () { $('#myModal').modal('hide')});
			</script>
</body>
</html>