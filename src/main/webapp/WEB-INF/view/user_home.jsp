<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${userinfo.getUsername() }的主页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<script type="text/javascript" src='<c:url value="/resources/js/jquery.min.js"></c:url>'></script>
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu_header.jsp"/>
	<div class="fly-home fly-panel" style="background-image: url();">
  <img src='<c:url value="${ userinfo.getHeadurl() }"></c:url>' alt="${ userinfo.username }">
  <i class="iconfont icon-renzheng" title="纵横国漫社区认证"></i>
  <h1>
    ${ userinfo.getUsername() }
    <c:if test="${userinfo.getSex()=='男'}">
    	<i class="iconfont icon-nan"></i>
    </c:if>
    <c:if test="${userinfo.getSex()=='女'}">
    	<i class="iconfont icon-nv"></i>
    </c:if>
    <!-- <i class="iconfont icon-nv"></i>  -->
    <i class="layui-badge fly-badge-vip">VIP${userinfo.getGrade()}</i>
    <!--
    <span style="color:#c00;">（管理员）</span>
    <span style="color:#5FB878;">（社区之光）</span>
    <span>（该号已被封）</span>
    -->
  </h1>
  <c:if test="${userinfo.getHonor()>=2}">
  	<p style="padding: 10px 0; color: #5FB878;">认证信息：管理员</p>
  </c:if>
  <c:if test="${userinfo.getHonor()!=2}">
  	<p style="padding: 10px 0; color: #5FB878;">身份信息：注册用户</p>
  </c:if>

  <p class="fly-home-info">
    <i class="iconfont icon-kiss" title="经验"></i><span style="color: #FF7200;">${userinfo.getGradeValue()} 点 经验</span>
    <i class="iconfont icon-shijian"></i><span>2018-12-16 加入</span>
  </p>
  <c:choose>
   <c:when test="${!empty userinfo.getIntroduce()}">  
   		<p class="fly-home-sign">（${userinfo.getIntroduce() }）</p>      
   </c:when>
   <c:otherwise> 
     	<p class="fly-home-sign">（这家伙很懒，什么都没写）</p> 
   </c:otherwise>
</c:choose>
  <div class="fly-sns" data-user="">
  <c:choose>
  	<c:when test="${friendstatus==0 and userinfo.getUserid()!=user.getUserid()}">
  		<a href="javascript:;" class="layui-btn layui-btn-primary layui-btn-disabled">已发送好友申请</a>
  	</c:when>
  	<c:when test="${friendstatus==2 and userinfo.getUserid()!=user.getUserid()}">
  		<a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-id="${userinfo.getUserid() }" id="addfriend">加为好友</a>
  	</c:when>
  	<c:when test="${friendstatus==1 and userinfo.getUserid()!=user.getUserid()}">
  		<a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-id="${userinfo.getUserid() }" id="addchat">发起会话</a>
  	</c:when>
  </c:choose>    
    
  </div>

</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md6 fly-home-jie">
      <div class="fly-panel">
        <h3 class="fly-panel-title">${userinfo.getUsername()}最近的发帖</h3>
        <ul class="jie-row">
			<c:if test="${empty threadlist || threadlist.size()==0}">  
			   	<li><p>${userinfo.getUsername()}最近没有发帖！</p></li>      
			</c:if>
			<c:forEach items="${threadlist }" var="item"  varStatus="status"> 
				<li>
					<c:choose>  
						<c:when test="${item.getStatus()==1}">  
						   <span class="layui-badge layui-bg-red">精华</span>
						</c:when>  
						<c:when test="${item.getStatus()==2}">  
						   <span class="layui-badge layui-bg-red">热帖</span>
						</c:when>  
						<c:when test="${item.getStatus()==3}">  
						   <span class="layui-badge layui-bg-red">公告</span>
						</c:when>
						<c:otherwise>
						    <span class="layui-badge layui-bg-red">新帖</span>
						</c:otherwise>   
					</c:choose>
			        <a href="/guomanwang/commit/allusercommits?threadId=${item.getId()}" class="jie-title"> ${item.getTitle()}</a>
			        <i>${item.getTime()}</i>
			        <em class="layui-hide-xs">${item.getGreatNum()}阅/${item.getCommitNumber()}回复</em>
			          </li> 
		    </c:forEach>
          <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div> -->
        </ul>
      </div>
    </div>
    
    <div class="layui-col-md6 fly-home-da">
      <div class="fly-panel">
        <h3 class="fly-panel-title">${userinfo.getUsername() } 最近的回帖</h3>
        <ul class="home-jieda">
        <c:choose>
		   <c:when test="${empty mycommits}">  
		   		<li><p>你还没有回帖哦！</p></li>      
		   </c:when>
		   <c:otherwise>
		   	  <c:forEach items="${mycommits}" var="item" varStatus="status"> 
			      <li>
		          <p>
		          <span>${item.getTime()}</span>
		       		  在帖子<a href="/guomanwang/commit/allusercommits?threadId=${item.getId()}" target="">${item.getTitle()}</a>中回答：   	
		          </p>
		          <div class="home-dacontent">
		           	${item.getContent()}
		          </div>
		        </li> 
	          </c:forEach>
		   </c:otherwise>
		</c:choose>
          <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
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
<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>

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
	
	$(document).on('click', '#addfriend', function(data) {
        var friendid = $(this).attr('data-id');
            $.get('<c:url value="/user/addfriend"></c:url>',{
            	friendid:friendid
            },function(data){
           layer.msg(data.msg);
           if(data.code==1){
         	  $(this).remove();
            }
         })
         return false;
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

});	
</script>	
</body>
</html>