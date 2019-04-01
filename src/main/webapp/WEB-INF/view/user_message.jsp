<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>我的消息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
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
    <li class="layui-nav-item">
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
    <li class="layui-nav-item layui-this">
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
	  <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
	    <button class="layui-btn layui-btn-danger" id="LAY_delallmsg">清空全部消息</button>
	    <div  id="LAY_minemsg" style="margin-top: 10px;">
        <!--<div class="fly-none">您暂时没有最新消息</div>-->
        <ul class="mine-msg">
        <c:if test="${empty mymessages|| mymessages.size()==0}">
			<li>
	  			<p style="text-align:left;">暂无消息和通知！</p>
	        </li>
		</c:if>
        <c:forEach items="${mymessages }" var="item"  varStatus="status">
        <c:choose>
        	<c:when test="${item.getType()=='帖子'}">
        		<li data-id="123">
		            <blockquote class="layui-elem-quote">
		            	<div class="layui-row">
		            	<div class="layui-col-md8">
		            		<a href="user_home?userId=${item.getSenderid() }" target="_blank">
				              	<cite>${item.getUsername() }</cite>
				            </a>${item.getContent() } | 
				            ${item.getTime() }
		            	</div>
		              	<div class="layui-col-md4" >
		            		<a href="javascript:;" id="deletemessage" data-id="${item.getId() }" style="float:right;">
				              	<cite>删除</cite>
				              </a>
		            	</div>
		            	</div>
		            </blockquote>
		          </li>
        	</c:when>
        	<c:when test="${item.getType()=='私信'}">
        		<li data-id="123">
		            <blockquote class="layui-elem-quote">
		            	<div class="layui-row">
		            	<div class="layui-col-md8">
		            		<a href="user_home?userId=${item.getSenderid() }" target="_blank">
				              	<cite>${item.getUsername() }</cite>
				            </a>给你发来私信 ：<cite>${item.getContent() }</cite>
				            | ${item.getTime() }
		            	</div>
		              	<div class="layui-col-md4" >
		            		<a href="javascript:;" id="deletemessage" data-id="${item.getId() }" style="float:right;">
				              	<cite>删除</cite>
				             </a>
				             <a href="javascript:;" id="reply" data-id="${item.getSenderid() }" style="float:right;">
				              	<cite>回复</cite>
				             </a>
		            	</div>
		            	</div>
		            </blockquote>
		          </li>
        	</c:when>

        </c:choose>
          
          </c:forEach>
        </ul>
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
<script>
layui.use(['element','form','layer'], function(){
	var $ = layui.jquery,
	element = layui.element,
	form = layui.form,
	layer = parent.layer === undefined ? layui.layer : top.layer;
	
	$(document).on('click', '#deletemessage', function(data) {
        var messageid = $(this).attr('data-id');
        layer.confirm('确认删除该消息吗？该操作无法撤销', {icon: 3, title: '提示信息'}, function (index) {
		  $.ajax({
			    url:'/guomanwang/message/deletemessage',
			    type: 'post',
			    data: {
				id:messageid
			    },
			    success: function (info) {
			       layer.msg(info.msg);
			  		if(info.code==1){
			  			location.reload();
			  		}
			    }
			  });
		  return false;
        });
    })
	//给好友回复私信
	    $(document).on('click', '#reply', function(data) {
	        var receiverid = $(this).attr('data-id');
	        var senderid=${user.getUserid()};
	        layer.prompt({
	        	  formType: 2,
	        	  value: '',
	        	  title: '请输入回复内容',
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