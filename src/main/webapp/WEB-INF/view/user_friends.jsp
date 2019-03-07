<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>我的好友</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
	.avatar img{display: block; width: 45px; height: 45px; margin-left:20px;padding:0; border-radius: 2px;}
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
    <li class="layui-nav-item ">
      <a href="user_index">
        <i class="layui-icon">&#xe857;</i>
        	用户中心
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="user_setting">
        <i class="layui-icon">&#xe620;</i>
       	 基本设置
      </a>
    </li>
    <li class="layui-nav-item layui-this">
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
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" lay-id="index" class="layui-this">我的好友</li>
        <li data-type="dianzan" data-url="/collection/find/" lay-id="dianzan">申请列表
        <c:if test="${!empty askrequests and !empty requestfriends}"><span class="layui-badge">${askrequests.size()+requestfriends.size()}</span></c:if>
        </li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <ul class="mine-view jie-row">    
			<c:if test="${empty friends|| friends.size()==0}">
				<li>
	  				<p>你还没有添加任何人为好友哦！</p>
	            </li>
			</c:if>
          <c:forEach items="${friends }" var="item"  varStatus="status">
		    <li>
              <a href="/guomanwang/user/user_home?userId=${item.getUserid() }" class="avatar">
              <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
              </a>
              <span style="background-color:red;margin-left:5px;">${item.getUsername() }</span>
              <a class="mine-edit" href="javascript:;" data-id="${item.getUserid() }" id="chat"><i class="layui-icon" style="color:white;">&#xe63a;</i>私信</a>
              <a class="mine-edit" href="javascript:;" data-id="${item.getUserid() }" id="deletefriend"><i class="layui-icon" style="color:white;">&#xe640;</i>删除</a>
            </li>
		</c:forEach>
          </ul>
          <div id="LAY_page"></div>
        </div>
        <div class="layui-tab-item">
        <button class="layui-btn layui-btn-sm layui-btn-danger">我发送的好友申请</button>
          <ul class="mine-view jie-row" style="margin-top:20px;">
          	<c:if test="${empty requestfriends|| requestfriends.size()==0}">
				<li>
	  				<p>你还未向任何用户发送好友申请！或申请已被处理了</p>
	            </li>
			</c:if>
	        <c:forEach items="${requestfriends }" var="item"  varStatus="status">
			    <li>
	              <a href="/guomanwang/user/user_home?userId=${item.getUserid() }" class="avatar">
	              <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
	              </a>
	              <a class="mine-edit" href="javascript:;" style="background-color:red;margin-left:5px;">${item.getUsername() }</a>
	              <i class="mine-edit">对方还未通过</i>
	            </li>
			</c:forEach>
          </ul>
          <button class="layui-btn layui-btn-sm layui-btn-danger">我接收的好友申请</button>
          <ul class="mine-view jie-row" style="margin-top:20px;">
            <c:if test="${empty askrequests|| askrequests.size()==0}">
				<li>
	  				<p>暂无用户给你发送好友申请！</p>
	            </li>
			</c:if>
          
	        <c:forEach items="${askrequests }" var="item"  varStatus="status">
			    <li>
	              <a href="/guomanwang/user/user_home?userId=${item.getUserid() }" class="avatar">
	              	<img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
	              </a>
	              <a class="mine-edit" href="javascript:;" style="background-color:red;margin-left:5px;">${item.getUsername() }</a>
	              <a class="mine-edit" href="javascript:;" data-method="agree" data-id="${item.getUserid() }" id="agree"><i class="layui-icon" style="color:white;">&#xe605;</i>同意</a>
              	  <a class="mine-edit" href="javascript:;" data-method="offset" data-id="${item.getUserid() }" id="refuse"><i class="layui-icon" style="color:white;">&#x1006;</i>拒绝</a>
	            </li>
			</c:forEach>
          </ul>
          <div id="LAY_page1"></div>
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
<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script src='<c:url value="/resources/js/haha.js"></c:url>'></script>
<script>
layui.config({
  base: '<c:url value="/resources/res/mods/"></c:url>' //你存放新模块的目录，注意，不是layui的模块目录
}).use('<c:url value="index"></c:url>'); //加载入口
</script>
<script>
layui.use(['element','form','layer'], function(){
	var $ = layui.jquery,
	element = layui.element,
	form = layui.form,
	layer = parent.layer === undefined ? layui.layer : top.layer;
	
	$(document).on('click', '#agree', function(data) {
        var friendid = $(this).attr('data-id');
            $.get('<c:url value="/user/passfriend"></c:url>',{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           if(data.code==1){
        	  $(this).parents("li").hide(1000);
        	  $(this).parents("li").remove();
           }
         })
    });
	 //删除好友与拒绝好友申请共用同一事件
    $(document).on('click', '#deletefriend,#refuse', function(data) {
        var friendid = $(this).attr('data-id');
        var link=$(this).attr('id');
        if(link=='deletefriend'){
        	message='确定与该用户解除好友关系吗？该操作是双向的！';
        }
        else{
        	message='确认拒绝该好友请求吗？'
        }
		layer.confirm(message, {icon: 3, title: '提示信息'}, function (index) {
            $.get('<c:url value="/user/deletefriend"></c:url>',{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           setTimeout(function(){
               top.layer.close(index);
               if(data.code==1){
            	   location.reload();
               }
           },1500);
            })
           
            return false;
    	});
    })
		//给好友发送私信
	    $(document).on('click', '#chat', function(data) {
	        var receiverid = $(this).attr('data-id');
	        var senderid=${user.getUserid()};
	        layer.prompt({
	        	  formType: 2,
	        	  value: '',
	        	  title: '请输入私信内容',
	        	  shadeClose:true,
	        	  btn: ['发送', '取消'],
	        	  btnAlign: 'c',
	        	  area: ['300px', '200px'] //自定义文本域宽高
	        	}, function(value, index, elem){
	  			  $.ajax({
					    url:'/guomanwang/message/postmessage',
					    type: 'post',
					    data: {
						senderid:senderid,
						receiverid:receiverid,
						content:value,	
				  		type:'私信',
					    },
					    success: function (info) {
					       layer.msg(info.msg);
					    }
					  });
	        	});
    })
});	
</script>
</body>
</html>