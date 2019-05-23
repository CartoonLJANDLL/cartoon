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
                  <input type="text" id="L_code" name="code" required lay-verify="code" autocomplete="off" class="layui-input" placeholder="6位数字验证码">  
                </div>
                <button class="layui-btn" id="sendcode">获取验证码</button>
              </div>
              <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" required lay-verify="username" autocomplete="off" class="layui-input"  placeholder="命名你独一无二的ID名称">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required lay-verify="password" autocomplete="off" class="layui-input" placeholder="6到16个数字/字母组成">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="comfirm_password" required lay-verify="confirm_password" autocomplete="off" class="layui-input"  placeholder="请与上一栏密码保持一致">
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
<%@ include file="../common/footer.html"%>
</body>
</html>