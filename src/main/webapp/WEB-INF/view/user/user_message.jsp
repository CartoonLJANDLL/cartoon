<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>我的消息</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
<style type="text/css">
	.avatar img{width: 45px; height: 45px; margin-left:20px;padding:0; border-radius: 2px;}
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
	    <div  id="LAY_minemsg" style="margin-top: 10px;">
        <!--<div class="fly-none">您暂时没有最新消息</div>-->
        <div class="layui-collapse">
		  <div class="layui-colla-item">
		    <h2 class="layui-colla-title">系统消息</h2>
		    <div class="layui-colla-content">
		    	<ul class="mine-msg">
		    		<c:if test="${empty systemmessages }">
		    			<li data-id="123">
		    				<blockquote class="layui-elem-quote">暂无此类消息</blockquote>
		    			</li>
		    		</c:if>
		    		 <c:forEach items="${systemmessages }" var="item"  varStatus="status">
 		 	        	<li data-id="${item.getId() }">
				            <blockquote class="layui-elem-quote">
				            	<div class="layui-row">
				            	<div class="layui-col-md8">
				            		<a href="user_home?userId=${item.getSenderid() }" target="_blank">
						              	<cite>${item.getUsername() }</cite>
						            </a>${item.getContent() } | ${item.getTime() }
				            	</div>
				              	<div class="layui-col-md4" >
				            		<a href="javascript:;" id="deletemessage" data-id="${item.getId() }" style="float:right;">
						              	<cite>删除</cite>
						              </a>
				            	</div>
				            	</div>
				            </blockquote>
				          </li>
		    		 </c:forEach>
		    	</ul>
		    </div>
		  </div>
		  <div class="layui-colla-item">
		    <h2 class="layui-colla-title">好友私信</h2>
		    <div class="layui-colla-content">
		    	<ul class="mine-msg">
		    		<c:if test="${empty friendmessages }">
		    			<li data-id="123">
		    				<blockquote class="layui-elem-quote">暂无此类消息</blockquote>
		    			</li>
		    		</c:if>
		    		 <c:forEach items="${friendmessages }" var="item"  varStatus="status">
 		 	        	<li data-id="${item.getId() }">
				            <blockquote class="layui-elem-quote">
				            	<div class="layui-row">
				            	<div class="layui-col-md8">
				            		<a href="user_home?userId=${item.getSenderid() }" target="_blank">
						              	<cite>${item.getUsername() }</cite>
						            </a>给你发来私信 ：<cite>${item.getContent() }</cite> | ${item.getTime() }
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
		    		 </c:forEach>
		    	</ul>
		    </div>
		  </div>
		  <div class="layui-colla-item">
		    <h2 class="layui-colla-title">好友申请</h2>
		    <div class="layui-colla-content">
				<ul class="mine-msg">
		    		<c:if test="${empty requestfriends and  empty askrequests}">
		    			<li data-id="123">
		    				<blockquote class="layui-elem-quote">暂无此类消息</blockquote>
		    			</li>
		    		</c:if>					
		    		 <c:forEach items="${requestfriends }" var="item"  varStatus="status">
 		 	        	<li data-id="${status.count }">
				            <a href="/guomanwang/user/user_home?userId=${item.getUserid() }" class="avatar">
			                	<img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
			                </a>
			              	<a class="mine-edit" href="javascript:;" style="background-color:#009688;padding：5px;color:white;margin-left:5px;">${item.getUsername() }</a>
			              	<i class="mine-edit">对方还未处理你的好友申请</i>
				        </li>
		    		 </c:forEach>
		    		 <c:forEach items="${askrequests }" var="item"  varStatus="status">
					    <li data-id="${status.count }">
			              <a href="/guomanwang/user/user_home?userId=${item.getUserid() }" class="avatar">
			              	<img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
			              </a>
			              <a class="mine-edit" href="javascript:;" style="background-color:#009688;padding：5px;color:white;margin-left:5px;">${item.getUsername() }</a>
			              <button class="layui-btn layui-btn-xs" id="agree" data-id="${item.getUserid() }">同意</button>
		              	  <button class="layui-btn layui-btn-danger layui-btn-xs" id="refuse" data-id="${item.getUserid() }">拒绝</button>
			            </li>
					</c:forEach>
		    	</ul>
			</div>
		  </div>		  
		  <div class="layui-colla-item">
		    <h2 class="layui-colla-title">论坛消息</h2>
		    <div class="layui-colla-content">
				<ul class="mine-msg">
		    		<c:if test="${empty othermessages }">
		    			<li data-id="123">
		    				<blockquote class="layui-elem-quote">暂无此类消息</blockquote>
		    			</li>
		    		</c:if>					
		    		 <c:forEach items="${othermessages }" var="item"  varStatus="status">
 		 	        	<li data-id="${item.getId() }">
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
		    		 </c:forEach>
		    	</ul>
			</div>
		  </div>
		</div>
      </div>
	  </div>
	</div>
</div>
<%@ include file="../common/footer.html"%>
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
			  		$("li[data-id="+messageid+"]").remove();
			  		
			    }
			  });
		  return false;
        });
    })
	//给好友回复私信，点击进入相应好友聊天窗口
	    $(document).on('click', '#reply', function(data) {
	        var receiverid = $(this).attr('data-id');
	        location.href="user_friends#myfriend="+receiverid;
	        
    })
    $(document).on('click', '#agree', function(data) {
        var friendid = $(this).attr('data-id');
            $.post("../user/passfriend",{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           if(data.code==1){
        	   location.reload();
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
           },1000);
            })
           
            return false;
    	});
    })
});	
</script>	
</body>
</html>