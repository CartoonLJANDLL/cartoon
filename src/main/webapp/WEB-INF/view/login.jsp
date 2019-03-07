<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>登入</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />

</head>
<body>

<jsp:include page="menu_header.jsp"/>

<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li><a href="register">注册</a></li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
          	<form class="layui-form" method="post">
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_phone" name="telnumber" required lay-verify="phone" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required lay-verify="required|password" autocomplete="off" class="layui-input">
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
              </div>-->
              <div class="layui-form-item">
                <button class="layui-btn"  lay-submit lay-filter="islogin">立即登录</button>
                <span style="padding-left:20px;">
                  <a href="forget">忘记密码？</a>
                </span>
              </div>
              <div class="layui-form-item fly-form-app">
                <span>或者使用社交账号登入</span>
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
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script type="text/javascript">
layui.use(['layer','form'], function(){
	  var $ = layui.jquery
	  ,form = layui.form
      ,layer =layui.layer;
	  
	//自定义验证规则
	  form.verify({
		password: [
	      /^[\S]{6,12}$/
	      ,'密码必须6到12位，且不能出现空格'
	    ]
	  });
	  form.on('submit(islogin)', function(data){
		  $.ajax({
			    url:'/guomanwang/user/islogin',
			    type: 'post',
			    data: {
				telnumber:$("#L_phone").val(),
		  		password:$("#L_pass").val(),
			    },
			    success: function (info) {
			    if (info.code === 1) {
			         setTimeout(function () {
			         location.href = "index";
			         }, 2000);
			        }
			       layer.msg(info.msg);
			    }
			  });
			 return false;
			});
	 })
</script>
</body>
</html>