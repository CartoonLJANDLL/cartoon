<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>话题中心 </title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
  <link rel="stylesheet" href='<c:url value="/resources/layui/css/layui.css"></c:url>'>
</head>
<body>
<jsp:include page="menu_header.jsp"/>
<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
      <li class="layui-hide-xs layui-this">当前位置：<a style="margin:0;padding:0;" href="/guomanwang/common/luntan">论坛</a>>>${Block.getName() }</li> 
    </ul>
    <div class="fly-column-right layui-hide-xs"> 
    <form action="">
      <div class="layui-fluid">
			<div class="layui-inline">
				<input placeholder="请输入帖子关键字搜索" required lay-verify="required" name="keywords" autocomplete="off" id="searchinput" class="layui-input">
			</div>
			<button class="layui-btn" lay-submit lay-filter="searchtiezi">
				<i class="layui-icon layui-icon-search">
				</i>
			</button>
			 <a href="/guomanwang/common/add" class="layui-btn">发表新帖</a> 
	</div>
    </form> 
    </div> 
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;"> 
      <a href="/guomanwang/common/add" class="layui-btn">发表新帖</a> 
    </div> 
  </div>
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8">

      <div class="fly-panel" style="margin-bottom: 0;">
      
        <div class="fly-panel-title fly-filter">
        <% session.setAttribute("searchstatus", 0);%>
          <a href="/guomanwang/thread/block_index?blockId=${Block.getId() }" <c:if test="${map==0 }">class="layui-this"</c:if>>默认</a>
          <span class="fly-mid"></span>
          <a href="/guomanwang/thread/animauserthread?blockId=${Block.getId() }" <c:if test="${map==1 }">class="layui-this"</c:if>>精华</a>
          <span class="fly-filter-right layui-hide-xs">
          <a href="/guomanwang/thread/newuserthread?blockId=${Block.getId()}" <c:if test="${map==3 }">class="layui-this"</c:if>>按最新</a>
          <span class="fly-mid"></span>
          <a href="/guomanwang/thread/hotuserthread?blockId=${Block.getId()}" <c:if test="${map==2 }">class="layui-this"</c:if>>按热议</a>
          </span>
        </div>
       <ul class="fly-list">  
       <!-- 热帖，最新帖子 -->
       <c:if test="${empty userThreads &&map!=1}">
        	<h3 style="margin:20px 250px;color:red;">本话题暂无人发帖，赶紧来抢沙发！</h3>
        </c:if>
        <c:if test="${empty userThreads &&map==1}">
        	<h3 style="margin:20px 250px;color:red;">本话题暂无精华帖！</h3>
        </c:if>
        <c:forEach items="${userThreads }" var="item"  varStatus="status">
        	<li>
            <a href="/guomanwang/user/user_home?userId=${item.getUserId() }" class="fly-avatar">
              <img src='<c:url value="${item.getHeadUrl() }"></c:url>' alt="${item.getUserName() }">
            </a>
            <h2>
              <a class="layui-badge">分享</a>
              <c:choose>  
				    <c:when test="${item.getStatus()==1}">  
				        <a class="layui-badge layui-bg-red">精华</a>
				    </c:when>  
				    <c:when test="${item.getStatus()==2}">  
				       <a class="layui-badge layui-bg-red">热帖</a>
				    </c:when>  
				    <c:otherwise>
				    	<a class="layui-badge layui-bg-red">新帖</a>
				    </c:otherwise>   
				</c:choose>  
              <a href="/guomanwang/commit/allusercommits?threadId=${item.getId()}">${item.getTitle()}</a>
            </h2>
            <div class="fly-list-info">
              <a href="/guomanwang/user/user_home?userId=${item.getUserId() }" link>
                <cite>${item.getUserName() }</cite>
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP${item.getGrade() }</i>
               
              </a>
              <span>${item.getTime() }</span>
              <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="评论"></i>${item.getCommitNumber() }
                <i class="iconfont" title="人气">&#xe60b;</i>${item.getGreatNum() }
              </span>
            </div>
            <div class="fly-list-badge">
              <!--<span class="layui-badge layui-bg-red">精帖</span>-->
            </div>
          </li>
        </c:forEach>       
     </ul>
        <!-- <div style="text-align: center">
          <div class="laypage-main">
            <a href="jie/index.html" class="laypage-next">更多帖子</a>
          </div>
        </div> -->
      </div>
    </div>
    <div class="layui-col-md4">
      <div class="fly-panel">
        <h3 class="fly-panel-title">
        	<i class="layui-icon">&#xe667;</i>
        	公告
        </h3>
        <ul class="fly-panel-main fly-list-static">
        <!-- 公告 -->
        	<c:if test="${empty notices }">  	
		        <li>还没有发布公告！！！</li>
		    </c:if>
        	<c:forEach items="${notices }" var="item"  varStatus="status">  	
		          	<li>
		            	<a href="/guomanwang/commit/allusercommits?threadId=${item.getId()}">${item.getTitle() }</a>
		          		<span style="font-size:13px;margin-left:150px;color:red;">发布者：<i class="layui-icon" style="font-size:14px;">&#xe66f;</i>${item.getUserName()}</span>
		          	</li>
            </c:forEach>
        </ul>
      </div>
      <div class="fly-panel fly-signin">
        <div class="fly-panel-title">
         	 签到
         <i class="fly-mid"></i> 
          <a href="javascript:;" class="fly-link" id="LAY_signinHelp">说明</a>
          <i class="fly-mid"></i> 
          <a href="javascript:;" class="fly-link" id="LAY_signinTop">活跃榜<span class="layui-badge-dot"></span></a>
          <span class="fly-signin-days">已连续签到<cite>${signdays}</cite>天</span>
        </div>
        <div class="fly-panel-main fly-signin-main">
	        <c:choose>
	        	<c:when test="${signstatus==1 }">
	          		<button class="layui-btn layui-btn-disabled">今日已签到</button>
	        	</c:when>
	        	<c:otherwise>
	        		<button class="layui-btn layui-btn-danger" id="signin">今日签到</button>
	          		<span>可获得
		          		<cite id="signvalue">
			          		<c:choose>
			          			<c:when test="${signdays lt 5 }">
			          				5
			          			</c:when>
			          			<c:when test="${signdays ge 5 and signdays lt 15}">
			          				10
			          			</c:when>
			          			<c:when test="${signdays ge 15 and signdays lt 30}">
			          				15
			          			</c:when>
			          			<c:otherwise>
			          				20
			          			</c:otherwise>
			          		</c:choose>
		          		</cite>点经验
	          		</span>
	        	</c:otherwise>
	        </c:choose>
        </div>
      </div>
      <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
        <!-- 回帖周榜用户 -->
        <h3 class="fly-panel-title">回贴周榜</h3>
        <dl>
          <!--<i class="layui-icon fly-loading">&#xe63d;</i>-->
          <c:if test="${empty users }"><dd>暂无数据</dd></c:if>
          <c:forEach items="${users }" var="item"  varStatus="status">
	          <dd>
	            <a href="/guomanwang/user/user_home?userId=${item.getUserId() }">
	              <img src='<c:url value="${ item.getUrl()}"></c:url>'><cite>${item.getUserName()}</cite><i>${item.getZanNumber() }次回答</i>
	            </a>
	          </dd>
          </c:forEach>
        </dl>
      </div>
	
     <!-- 本周热议的帖子 -->
     <dl class="fly-panel fly-list-one">
     <dt class="fly-panel-title">本周热贴TOP<i class="layui-icon" style="color:red">&#xe756;</i></dt>
     	<c:if test="${empty hotThreads}"><dd>暂无热帖</dd></c:if>
        <c:forEach items="${hotThreads }" var="item"  varStatus="status">
	        <dd>
	          <a href="/guomanwang/usercommit/allusercommits?threadId=${item.getId() }">${item.getTitle() }</a>
	          <span><i class="iconfont icon-pinglun1"></i>${item.getCommitNumber()}</span>
	        </dd>
        </c:forEach>
    </dl>
        <!-- 无数据时 -->
        <!--
        <div class="fly-none">没有相关数据</div>
        -->


      <div class="fly-panel">
        <div class="fly-panel-title">
          	这里可作为备用区域
        </div>
        <div class="fly-panel-main">
          <a href="javascript:;" target="_blank" class="fly-zanzhu" style="background-color: #5FB878;">赞助商或图片预留</a>
        </div>
      </div>
      
      <div class="fly-panel fly-link">
        <h3 class="fly-panel-title">友情链接</h3>
        <dl class="fly-panel-main">
          <dd><a href="http://www.layui.com/" target="_blank">layui</a><dd>
          <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a><dd>
          <dd><a href="http://layer.layui.com/" target="_blank">layer</a><dd>
          <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a><dd>
          <dd><a href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE" class="fly-link">申请友链</a><dd>
        </dl>
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
layui.cache.page="user";
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '<c:url value="/resources/res/images/avatar/00.jpg"></c:url>'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '<c:url value="/resources/res/mods/"></c:url>'
}).extend({
  fly: '<c:url value="index"></c:url>'
}).use('<c:url value="fly"></c:url>');
</script>
<script type="text/javascript">
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    
    form.on('submit(searchtiezi)', function(data){
		//弹出loading
		var index = top.layer.msg('正在搜索，请稍候',{icon: 16,time:false,shade:0.8});
	    		$.post('<c:url value="/thread/searchtiezi"></c:url>',{
	    			key:$("#searchinput").val()
	    		},function(res){
	            	top.layer.msg(res.msg);
	            	setTimeout(function(){
	    	            top.layer.close(index);
	    	            //刷新当前页面
	    	            if(res.code==1){
	    	            	location.href="/guomanwang/thread/block_index?blockId=${Block.getId()}&searchstatus=1";
	    	            }
	    	            
	    	        },1000);
	            })
	        
	        return false;
	    });
    $(document).ready(function(){
    	$("#signin").click(function(){
    		var userid=${user.getUserid()};
    		var signvalue=parseInt($("#signvalue").text());
    		if(userid!=null&&userid!=""){
    			$.get('<c:url value="/user/sign"></c:url>',{
                  	 userid :userid,
                  	 signvalue:signvalue
                   },function(data){
                	 layer.msg(data.msg);
   	    	            //刷新当前页面
   	    	            if(data.code==1){
   	    	            	location.reload();
   	    	            }
                   });
    		}
    		else if(userid==null||userid==""){
    			layer.msg("你尚未登录，请登录后再来签到！");
    		}     
        })
    });
})
</script>
</body>
</html>