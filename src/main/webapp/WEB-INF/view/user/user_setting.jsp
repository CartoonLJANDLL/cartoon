<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ page import="guomanwang.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>基本设置</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
	#Images li{ width:24%; margin:0.5% 0.5%; float: left; overflow:hidden;text-align:center;}
	#Images li img{ width:80px;height:80px; cursor:pointer;border-radius:100%; }
	.check{text-align:center;margin-top:6px;}
</style>
</head>

<body>
	<jsp:include page="../common/menu_header.jsp"/>
	<div class="layui-container fly-marginTop fly-user-main">
 	<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
	    <li class="layui-nav-item">
	      <a href="user_home?userId=${user.getUserid() }">
	        <i class="layui-icon">&#xe609;</i>
	       	 我的主页
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_index">
	        <i class="layui-icon">&#xe857;</i>
	        	用户中心
	      </a>
	    </li>
	    <li class="layui-nav-item  layui-this">
	      <a href="user_setting">
	        <i class="layui-icon">&#xe620;</i>
	       	 基本设置
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_friends">
	        <i class="layui-icon">&#xe770;</i>
	       	我的好友
	      </a>
	    </li>
	    <li class="layui-nav-item">
	      <a href="user_message">
	        <i class="layui-icon">&#xe611;</i>
	        	我的消息
	      </a>
	    </li>
  	</ul>
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">我的资料</li>
        <li lay-id="avatar">头像</li>
        <li lay-id="pass">密码</li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
          <form class="layui-form">
            <div class="layui-form-item">
              <label for="L_phone" class="layui-form-label">手机号</label>
              <div class="layui-input-inline" style="margin:8px;">
                <span class="myphone">${sessionScope.user.getPhone()}</span>
                <span><a style="color: cornflowerblue;" href="#" class="changephone">换绑</a></span>
              </div>
            </div>
            <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">昵称</label>
              <div class="layui-input-inline">
                <input type="text" id="L_username" name="username" required lay-verify="required" value="${sessionScope.user.getUsername()}" autocomplete="off" value="" class="layui-input">
              </div>
            </div>
            <% User user=(User)session.getAttribute("user");%>
            <div class="layui-form-item">
              <label for="L_city" class="layui-form-label">性别</label>
              <div class="layui-input-inline" id="mysex">
                  <input type="radio" name="sex" value="男" <%if(user.getSex()!=null&&user.getSex().equals("男")){ %>checked<%} %> title="男">
                  <input type="radio" name="sex" value="女" <%if(user.getSex()!=null&&user.getSex().equals("女")){ %>checked<%} %> title="女">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
              <label for="L_sign" class="layui-form-label">签名</label>
              <div class="layui-input-block">
                <textarea placeholder="" id="L_sign"  name="introduce" autocomplete="off" class="layui-textarea" style="height: 80px;">${sessionScope.user.getIntroduce()}</textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <button class="layui-btn" id="resetinfo" key="set-mine" lay-filter="resetinfo" lay-submit>确认修改</button>
              <div class="layui-form-mid layui-word-aux"></div>
            </div>
            </form>
          </div>
          <div class="layui-form layui-form-pane layui-tab-item">
          <form class="layui-form">
          <div class="layui-row layui-col-space15" style="background-color:#f0f0f0;">
          <div class="layui-col-md6">
            <div class="layui-form-item">
              <div class="avatar-add">
                <p>建议尺寸正方形，支持jpg、png、gif</p>
                <button type="button" class="layui-btn upload-img" id="upload">
                  <i class="layui-icon">&#xe67c;</i>上传头像
                </button>
                <img id="mylogo" src="/guomanwang${user.getHeadurl()}">
                <span class="loading"></span>
                <button style="margin-top:320px;margin-left:110px;display:none;" class="layui-btn" id="resetheadimage" key="set-mine" lay-filter="changeheadimage" lay-submit>确认修改</button>
                <button style="margin-top:320px;margin-left:10px;display:none;" class="layui-btn" id="cancel">取消</button>           
              </div>
            </div>
            </div>
            <div class="layui-col-md6">
            <div class="layui-form-item" style="margin-top:35px;">
            <blockquote class="site-text layui-elem-quote">
				<label style="font-size: 20px;">系统默认头像</label>
			</blockquote>
			<hr class="layui-bg-green">
            	<ul class="layer-photos-demo" id="Images">
				 	<c:forEach items="${defaultheadlist }" var="item"  varStatus="status">
						<li>
							<img id="defaultimage" layer-src="/guomanwang${item.getUrl() }" alt="${item.getId() }" src="..${item.getUrl() }">
							<div class="operate">
								<div class="check">
									<input type="hidden" class="urladdress" value="${item.getUrl() }">
									<a href="javascript:;" class="defaultimage"><span class="layui-badge layui-bg-red">选择</span></a>
								</div>
							</div>            
				        </li>
					</c:forEach>	
				</ul>
            </div>
            </div>
            </div>
            </form>
          </div>     

          <div class="layui-form layui-form-pane layui-tab-item">
            <form >
              <div class="layui-form-item">
                <label for="L_nowpass" class="layui-form-label">当前密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_nowpass" name="password" required lay-verify="password" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">新密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="newpassword" required lay-verify="newpassword" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" required lay-verify="confirmpassword" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn" id="resetpassword" key="set-mine" lay-filter="changepassword" lay-submit>确认修改</button>
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>
  </div>
<%@ include file="../common/footer.html"%>
<div id="phoneset" style="display:none;">
	<form class="layui-form" style="margin:20px;">
		<div class="layui-form-item">
           <label for="L_phone" class="layui-form-label">新手机号</label>
           <div class="layui-input-inline">
             <input type="text" id="L_phone" name="cellphone" required lay-verify="phone" autocomplete="off" class="layui-input" placeholder="11位手机号">
           </div>
        </div>
        <div class="layui-form-item">
           <label for="L_code" class="layui-form-label">验证码</label>
           <div class="layui-input-inline" style="display:flex;">
              <input type="text" id="L_code" style="width:60%;" name="code" required lay-verify="code" autocomplete="off" class="layui-input" placeholder="6位数字验证码">  
           	  <button class="layui-btn layui-btn-sm" id="getcode" style="width: 40%;margin: 3px;">获取验证码</button>
           </div>
        </div>
        <div class="layui-form-item" style="text-align:center;">
           <button class="layui-btn layui-btn-sm" lay-filter="setphone" lay-submit>提交更新</button>
        </div>
      </form>
</div>
<script type="text/javascript" src="../resources/js/user_setting.js"></script>	
</body>
</html>