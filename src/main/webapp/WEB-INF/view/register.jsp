<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户注册</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
</head>
<body>
<jsp:include page="menu_header.jsp"/>

<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li><a href="login">登入</a></li>
        <li class="layui-this">注册</li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form>
              <div class="layui-form-item">
                <label for="L_phone" class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_phone" name="cellphone" required lay-verify="phone" autocomplete="off" class="layui-input" placeholder="11位手机号">
                </div>
                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登录账号</div>
              </div>
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_code" name="code" required lay-verify="code" autocomplete="off" class="layui-input" placeholder="4位数字验证码">  
                </div>
                <button class="layui-btn" id="sendcode">获取验证码</button>
              </div>
              <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" required lay-verify="username" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required lay-verify="password" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="comfirm_password" required lay-verify="confirm_password" autocomplete="off" class="layui-input">
                </div>
              </div>
             <!-- <div class="layui-form-item">
                <label for="L_vercode" class="layui-form-label">人类验证</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">
                  <span style="color: #c00;">{{d.vercode}}</span>
                </div>
              </div> --> 
              <div class="layui-form-item">
                <button class="layui-btn" lay-filter="zhuce" lay-submit>立即注册</button>
              </div>
              <div class="layui-form-item fly-form-app">
                <span>或者直接使用社交账号快捷注册</span>
                <a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq" title="QQ登入"></a>
                <a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo" title="微博登入"></a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

</div>

<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2018 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>

<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script>
layui.cache.page = '<c:url value="user"></c:url>';
layui.cache.user = {
  username:'游客'
  ,uid: -1
  ,experience: 83
  ,sex:'男'
};
layui.config({
	  version: "3.0.0"
	  ,base: '<c:url value="/resources/res/mods/"></c:url>'
	}).extend({
	  fly: '<c:url value="index"></c:url>'
	}).use(['layer','form','fly'],function(){
		 var $ = layui.jquery
		  ,form = layui.form
		  ,layer = parent.layer === undefined ? layui.layer : top.layer
		 
		 $("#sendcode").click(function(){
			 var cellphone=$("#L_phone").val();
				var reg=/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
				if(reg.test(cellphone)){
					$.ajax({
						    url:'/guomanwang/user/sendmsg',
						    type: 'post',
						    data: {
							cellphone:$("#L_phone").val(),
						    },
						    success: function (info) {
							  setTimeout(function () {
								  if(info.code==2){
								location.href = "login";
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
		 form.on('submit(zhuce)', function(data){
			  $.ajax({
				    url:'/guomanwang/user/register',
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
	      
		 });
</script>
</body>
</html>