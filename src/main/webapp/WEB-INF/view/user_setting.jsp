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
	<jsp:include page="menu_header.jsp"/>
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
	    <li class="layui-nav-item layui-this">
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
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">我的资料</li>
        <li lay-id="avatar">头像</li>
        <li lay-id="pass">密码</li>
        <li lay-id="bind">帐号绑定</li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
          <form class="layui-form">
            <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">手机号</label>
              <div class="layui-input-inline">
                <input type="text" id="L_phone" name="cellphone" required lay-verify="phone" value="${sessionScope.user.getPhone()}" autocomplete="off" value="" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">变更手机号，需<a href="activate.html" style="font-size: 12px; color: #4f99cf;">重新验证手机号</a>。</div>
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
                <img id="mylogo" src='<c:url value="${user.getHeadurl()}"></c:url>'>
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
							<img id="defaultimage" layer-src="<c:url value='${item.getUrl() }'></c:url>" alt="${item.getId() }" src="<c:url value='${item.getUrl() }'></c:url>">
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
          
          <div class="layui-form layui-form-pane layui-tab-item">
            <ul class="app-bind">
              <li class="fly-msg app-havebind">
                <i class="iconfont icon-qq"></i>
                <span>已成功绑定，您可以使用QQ帐号直接登录社区，当然，您也可以</span>
                <a href="javascript:;" class="acc-unbind" type="qq_id">解除绑定</a>
                
                <!-- <a href="" onclick="layer.msg('正在绑定微博QQ', {icon:16, shade: 0.1, time:0})" class="acc-bind" type="qq_id">立即绑定</a>
                <span>，即可使用QQ帐号登录Fly社区</span> -->
              </li>
              <li class="fly-msg">
                <i class="iconfont icon-weibo"></i>
                <!-- <span>已成功绑定，您可以使用微博直接登录Fly社区，当然，您也可以</span>
                <a href="javascript:;" class="acc-unbind" type="weibo_id">解除绑定</a> -->
                
                <a href="" class="acc-weibo" type="weibo_id"  onclick="layer.msg('正在绑定微博', {icon:16, shade: 0.1, time:0})" >立即绑定</a>
                <span>，即可使用微博帐号登录社区</span>
              </li>
            </ul>
          </div>
        </div>

      </div>
    </div>
  </div>
<div class="fly-footer">
  <p><a href="http://fly.layui.com/" target="_blank">纵横国漫社区</a> 2017 &copy; <a href="http://www.layui.com/" target="_blank">刘江 and 李林</a></p>
  <p>
    <a href="http://fly.layui.com/jie/3147/" target="_blank">信息反馈</a>
    <a href="http://www.layui.com/template/fly/" target="_blank">联系我们</a>
    <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
  </p>
</div>
<script type="text/javascript">
layui.use(['upload','layer','form'], function(){
	  var $ = layui.jquery
	  ,upload = layui.upload
	  ,form = layui.form
      ,layer = parent.layer === undefined ? layui.layer : top.layer
      ,$ = layui.jquery;
	  
	  //添加验证规则
	    form.verify({
	    	password: [
	  	      /^[\S]{6,12}$/
	  	      ,'密码必须6到12位，且不能出现空格'
	  	    ],
	        newpassword : [
		  	      /^[\S]{6,12}$/
		  	      ,'新密码必须6到12位，且不能出现空格'
		  	    ],
		  	  confirmpassword: function(value){
	              if(value.length == 0){
	                  return '请再次输入密码！';
	              }
	              else if(value!=$("#L_pass").val()){
	            	  return '两次密码输入不一致！';
	              }
	          }
	    })
	  //普通图片上传
	  var uploadInst = upload.render({
	    elem: '#upload'
	    ,method:'post'
	    ,url: '/guomanwang/user/uploadHeadImage'    
	    ,done: function(res){
	      //如果上传失败
	      if(res.code > 0){
	        return layer.msg('上传失败');
	      }
	      //上传成功 
	      if(res.code==0){
	        $('#mylogo').attr('src',"<c:url value='.."+res.src+"'></c:url>");
	        $('.upload-img').text("重新上传");
	        $('#cancel').show();
	        $('#resetheadimage').show();
	        return layer.msg("图片上传成功！",{time:5000});
	        
	      }
	      
	    }
	  });
	  //点击取消头像重新显示上一张头像
	    $("#cancel").click(function(){
	    	layer.confirm('确认取消头像更改吗？',{icon:3, title:'提示信息'},function(index){
	    		$('#mylogo').attr('src',"<c:url value='${user.getHeadurl()}'></c:url>");
	    		setTimeout(function(){
	    			$('#resetheadimage').css('margin-left','135px');
		    		$('#cancel').hide();
		    		$('#resetheadimage').hide();
		    		$('.upload-img').text("上传头像");
		    		layer.close(index);
	            },500)
           });
	    	
	   });
	  form.on('submit(resetinfo)', function(data){
		  $.ajax({
			    url:'/guomanwang/user/updateuserinfo',
			    type: 'post',
			    data: {
				cellphone:$("#L_phone").val(),
		  		username:$("#L_username").val(),
		  		sex: $('#mysex input[name="sex"]:checked ').val(),
		  		introduce:$("#L_sign").val()
			    },
			    success: function (info) {
			         setTimeout(function () {
			         location.reload();
			         }, 1000);
			    layer.msg(info.msg);
			        }
			  });
			 return false;
			});
	  form.on('submit(changeheadimage)', function(data){
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		$.ajax({
		    url:'/guomanwang/user/changeheadimage',
		    type: 'post',
		    data: {
		    headimage:$("#mylogo").attr('src')
		    },
		    success: function (info) {
		         setTimeout(function () {
		         location.reload();
		         }, 1000);
		    layer.msg(info.msg);
		        }
		  });
		 return false;
		});
	  form.on('submit(changepassword)', function(data){
		  $.ajax({
			    url:'/guomanwang/user/changepassword',
			    type: 'post',
			    data: {
				  password:$("#L_nowpass").val(),
		  		  newpassword:$("#L_pass").val()
			    },
			    success: function (info) {
			    if(info.code==1){
				  setTimeout(function () {
				  location.href="/guomanwang/common/login";
				  }, 1500);
			  	}
			  layer.msg(info.msg);
			    }
			  });
			 return false;
			});
	  $("a.defaultimage").click(function(){
		    var imageurl=$(this).parent().find(".urladdress").val();
		    layer.confirm('确定使用该头像吗？',{icon:3, title:'提示信息'},function(index){
		    	$.get('/guomanwang/user/selectdefaultheadimage',{
		            imageurl :imageurl
		        },function(data){
		        	if(data.code==1){
						  setTimeout(function () {
							  location.reload();
						  }, 1000);
					  	}
		           layer.msg(data.msg);
		           layer.close(index); 
		        })
		        return false;
		    })
		});
	 });
</script>
<script>
layui.config({
  base: '<c:url value="/resources/res/mods/"></c:url>' //你存放新模块的目录，注意，不是layui的模块目录
}).use('<c:url value="index"></c:url>'); //加载入口
</script>	
</body>
</html>