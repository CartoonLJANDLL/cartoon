<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>社区帖子页面详情</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
<link href='<c:url value="/resources/layui/css/layui.css"></c:url>' rel="stylesheet" />
<link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
</head>
<body>
<jsp:include page="menu_header.jsp"/>

<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
      <li class="layui-hide-xs layui-this">
      	当前位置：<a style="margin:0;padding:0;" href="/guomanwang/common/luntan">论坛</a>>>
      	<a style="margin:0;padding:0;" href="/guomanwang/thread/block_index?blockId=${Block.getId() }">${Block.getName() }</a>
      	>>${ userThread.getTitle()}
      </li> 
      
      <!-- 用户登入后显示 -->
      <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html">我发表的贴</a></li> 
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html#collection">我收藏的贴</a></li>--> 
    </ul> 
    
    <div class="fly-column-right layui-hide-xs"> 
      <div class="layui-fluid">
			 <a href="/guomanwang/common/add" class="layui-btn">发表新帖</a> 
		</div>
     
    </div> 
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;"> 
      <a href="/guomanwang/common/add" class="layui-btn">发表新帖</a> 
    </div> 
  </div>
</div>
<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8 content detail">
      <div class="fly-panel detail-box">
        <h1>${ userThread.getTitle()}</h1>
        <div class="fly-detail-info">
			<c:choose>  
				<c:when test="${userThread.getStatus()==1}">  
					<span class="layui-badge layui-bg-red">精华</span>
				</c:when>  
				<c:when test="${userThread.getStatus()==2}">  
					<span class="layui-badge layui-bg-red">热帖</span>
				</c:when>
				<c:when test="${userThread.getStatus()==3}">  
					<span class="layui-badge layui-bg-red">公告</span>
				</c:when>  
				<c:otherwise>
					<span class="layui-badge layui-bg-red">新帖</span>
				</c:otherwise>   
			</c:choose>
          <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->
          
          <div class="fly-admin-box" data-id="123">
          	<c:if test="${user.getHonor()==2 || userThread.getUserId()==user.getUserid()}" >
            	<span class="layui-btn layui-btn-xs jie-admin" id="delthread">删除</span>
            <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->
			</c:if>
			<c:if test="${user.getHonor()==2&&userThread.getStatus()!=1&&userThread.getStatus()!=3}">
            	<span class="layui-btn layui-btn-xs jie-admin" id="setstatus" field="status" rank="1">加精</span> 
            <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
			</c:if>         
          </div>
          <span class="fly-list-nums"> 
            <i class="iconfont" title="回复数">&#xe60c;</i>${userThread.getCommitNumber() }
            <i class="iconfont" title="人气">&#xe60b;</i>${userThread.getGreatNum() }
          </span>
        </div>
        <div class="detail-about">
          <a class="fly-avatar" href="/guomanwang/user/user_home?userId=${userThread.getUserId() }">
            <img src='<c:url value="${userThread.getHeadUrl() }"></c:url>' alt="${ userThread.getUserName()}">
          </a>
          <div class="fly-detail-user">
            <a href="/guomanwang/user/user_home?userId=${userThread.getUserId() }" class="fly-link">
              <cite>${ userThread.getUserName()}</cite>
              <i class="iconfont icon-renzheng" title="认证信息：{{ rows.user.approve }}"></i>
              <i class="layui-badge fly-badge-vip">VIP${ userThread.getGrade()}</i>
            </a>
            <span>${userThread.getTime() }</span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
            <!-- <span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span> -->  
            <c:if test="userThread.getUserId()==user.getUserid()">
            	<span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
          	</c:if>
          </div>
        </div>
        <div class="detail-body photos">
          <p>
            ${ userThread.getContent()}
          </p>
      	 	 图片区域
      	  <hr>
          <p>
            <img src="../../res/images/fly.jpg" alt="Fly社区">
          </p>
        </div>
      </div>
      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
          <legend>回帖</legend>
        </fieldset>
      <ul class="jieda" id="jieda">
      	<c:forEach items="${userCommits }" var="item"  varStatus="status"> 
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="">
                <img src='<c:url value="${item.getUrl() }"></c:url>' alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="/guomanwang/user/user_home?userId=${item.getUserId() }" class="fly-link">
                  <cite>${ item.getUserName()}</cite>
                  <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                  <i class="layui-badge fly-badge-vip">VIP${item.getGrade() }</i>              
                </a>
                <c:if test="${ item.getUserId()==userThread.getUserId()}">
                	<span>(楼主)</span>
                </c:if>
                <c:if test="${ item.getHonor()==2}">
                	<span style="color:#5FB878">(管理员)</span>
                </c:if>
                <c:if test="${ item.getHonor()==0}">
                	<span style="color:#999">（该号已被禁用）</span>
                </c:if>
                <!--
                
                <span style="color:#FF9E3F">（社区之光）</span>
                <span style="color:#999">（该号已被封）</span>
                -->
              </div>

              <div class="detail-hits">
                <span>${item.getTime() }</span>
              </div>

              <!-- <i class="iconfont icon-caina" title="最佳答案"></i> -->
            </div>
            <div class="detail-body jieda-body photos">
               <p>${item.getContent()}</p>
            </div>
               <div class="jieda-reply">
               	   <span <c:if test="${item.getIsliked()==1}">class="jieda-zan zanok"</c:if>
               	   			<c:if test="${item.getIsliked()==0}">class="jieda-zan"</c:if> 
               	   id="zan">
	                    <i class="iconfont icon-zan" id="${item.getId()}"></i>
	                    <em id="emoo">${item.getZanNumber()}</em>
                    </span>
                    <c:if test="${item.getUserId()!=user.getUserid()}" >
	                    <span id="reply">
	                    <i class="iconfont icon-svgmoban53"></i>
	                                                              回复<a class="replyname" style="display:none">${ item.getUserName()}</a>
	                       <a class="replyid" style="display:none">${ item.getId()}</a>
	                    </span>
                    </c:if>
                    <c:if test="${user.getHonor()==2 || item.getUserId()==user.getUserid()}" >
			            <div class="jieda-admin">            
			                <!--  <span type="edit">编辑</span>-->
			                <span id="delcommit" >
			                	删除
			                <input type="hidden" name="" value="${ item.getId()}" class="commitid">
			                </span>
			            </div>
		            </c:if>
	           </div>
            
            </li>
            </c:forEach>
          </ul>      
          <!-- 无数据时 -->
          <!-- <li class="fly-none">消灭零回复</li> -->
        <div class="layui-form layui-form-pane">
        <c:choose>
        	<c:when test="${empty user }">
        		<div class="layui-form-item layui-form-text">
        			<h3 align="center">想要发表评论请先<a style="text-decoration:underline;" href="/guomanwang/common/login">登录</a></h3>
        		</div>
        	</c:when>
        	<c:otherwise>
	          <form class="layui-form " method="post">
	            <div class="layui-form-item layui-form-text">
	              <a name="comment"></a>
	              <div class="layui-input-block">
	                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入回复内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
	              </div>
	            </div>
	            <div class="layui-form-item">
	              <input type="hidden" name="jid" value="0" id="parentid">
	              <button class="layui-btn" lay-filter="addcommit" lay-submit>提交回复</button>
	            </div>
	          </form>	
        	</c:otherwise>
        </c:choose>    
        </div>
      </div>
    </div>
    <div class="layui-col-md4">
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
      <div class="fly-panel">
        <div class="fly-panel-title">
          	备用区域
        </div>
        <div class="fly-panel-main">
          <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"  style="background-color: #5FB878;">赞助商或图片预留</a>
        </div>
      </div>
      <div class="fly-panel" style="padding: 20px 0; text-align: center;">
        <img src="../../res/images/weixin.jpg" style="max-width: 100%;" alt="站长联系方式">
        <p style="position: relative; color: #666;">微信扫码添加站长</p>
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
layui.use(['upload','layer','form'], function(){
	  var $ = layui.jquery
	  ,form = layui.form
    ,layer = parent.layer === undefined ? layui.layer : top.layer;
	  
form.on('submit(addcommit)', function(data){
	var userid="${user.getUserid()}";
	var threadid="${userThread.getId()}";
	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    		$.post('<c:url value="/commit/addcommit"></c:url>',{
    			threadId:threadid,
    			userId:userid,
    			content:$("#L_content").val(),
    			parentId:$("#parentid").val(),
    		},function(res){
            	top.layer.msg(res.msg);
            })
        setTimeout(function(){
            top.layer.close(index);
            location.reload();
        },2000);
        return false;
    });
    $(document).ready(function(){
        $("span#delcommit").click(function(){
        	var commitid=$(this).find(".commitid").val()
        	layer.confirm('确定删除该评论？', {icon: 3, title: '提示信息'}, function (index) {
                $.get('<c:url value="/commit/deletecommit"></c:url>',{
               	 commitId :commitid
                },function(data){
               layer.msg(data.msg);
                })
                setTimeout(function(){
                    top.layer.close(index);
                    location.reload();
                },1500);
                return false;
        	});
        })
    	$("span#reply").click(function(){
        	var val = $("#L_content").val();
            var aite = '@'+ $(this).find(".replyname").text().replace(/\s/g, '');
            $("#L_content").focus();
            $("#L_content").val(val+aite +' ');
            $("#parentid").val($(this).find(".replyid").text());
        })
        $("#L_content").keyup(function(){
        	var val = $("#L_content").val();
        	if(val.length<1){
        		$("#parentid").val(0);
        	}
        })
        //点赞
        $("span#zan").click(function(){
        	var commitid=parseInt($(this).find(".icon-zan").attr("id"));
        	var zanclass=$(this).attr("class");
        	var zannumber=parseInt($(this).find("#emoo").text())+1;
        	var userid=${user.getUserid()};
        	if(zanclass=="jieda-zan"){
        		$.get('<c:url value="/commit/dianzan"></c:url>',{
                 	 commitid :commitid,
                 	 userid:userid
                  },function(data){
                 layer.msg(data.msg);
                 if(data.code==1){
                   location.reload();
                     };
                  })
        	}
        	else{
        		$.get('<c:url value="/commit/cancelzan"></c:url>',{
                	 commitid :commitid,
                	 userid:userid
                 },function(data){
                layer.msg(data.msg);
                if(data.code==1){
                  location.reload();
                    };
                 })
        	}
               
        })
        //设置帖子为精贴
        $("#setstatus").click(function(){
        	var threadid=${userThread.getId()};
        	layer.confirm('确定将该贴子设置为精华帖吗？', {icon: 3, title: '提示信息'}, function (index) {
                $.get('<c:url value="/thread/updateThread"></c:url>',{
               	 status :1,
               	 id:threadid
                },function(data){
               layer.msg(data.msg);
                })
                setTimeout(function(){
                    top.layer.close(index);
                    location.reload();
                },1500);
                return false;
        	});
        })
        $("#delthread").click(function(){
        	var threadid=${userThread.getId()};
        	layer.confirm('确定删除该帖吗？该操作无法撤销！', {icon: 3, title: '提示信息'}, function (index) {
                $.get('<c:url value="/thread/deletethread"></c:url>',{
               	 id:threadid
                },function(data){
               layer.msg(data.msg);
               setTimeout(function(){
                   top.layer.close(index);
                   if(data.code==1){
                	   location.href="/guomanwang/thread/block_index?blockId="+${userThread.getBlockId()};
                   }
               },1500);
                })
               
                return false;
        	});
        })
    });
})
</script>
</body>
</html>