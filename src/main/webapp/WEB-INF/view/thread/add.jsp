<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>发表帖子 编辑帖子</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="纵横国漫网">
  <meta name="description" content="纵横国漫网致力于为广大国漫爱好者提供一个交流分享平台">
</head>
<body>
<jsp:include page="../common/menu_header.jsp"/>
<div class="layui-container fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
    <!--<div class="fly-none">没有权限</div>-->
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form class="layui-form">
              <div class="layui-row layui-col-space15 layui-form-item">
                <div class="layui-col-md3">
                  <label class="layui-form-label">选择版块</label>
                  <div class="layui-input-block">
                    <select lay-verify="required" id="blocks" name="block_name" lay-filter="column" lay-search> 
                      <c:forEach items="${blocks }" var="item"  varStatus="status">
		   					<option value="${item.getId() }">${item.getName() }</option>
					  </c:forEach>
                    </select>
                  </div>
                </div>
                <div class="layui-col-md9">
                  <label for="L_title" class="layui-form-label">标题</label>
                  <div class="layui-input-block">
                    <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                  </div>
                </div>
              </div>
              <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                  <textarea id="L_content" name="content" required lay-verify="required" placeholder="详细描述" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                </div>
              </div>
              <!-- 悬赏经验 -->
              <!-- <div class="layui-form-item">
                <div class="layui-inline">
                  <label class="layui-form-label">悬赏经验</label>
                  <div class="layui-input-inline" style="width: 190px;">
                    <select name="experience">
                      <option value="20">20</option>
                      <option value="30">30</option>
                      <option value="50">50</option>
                      <option value="60">60</option>
                      <option value="80">80</option>
                    </select>
                  </div>
                  <div class="layui-form-mid layui-word-aux">发表后无法更改经验</div>
                </div>
              </div> -->
              <c:if test="${user.getHonor()>2}">
               <div class="layui-form-item">
			    <label class="layui-form-label">发布类型</label>
			    <div class="layui-input-block">
			      <input type="radio" name="status" value="0" title="分享" checked>
			      <input type="radio" name="status" value="3" title="公告" >
			    </div>
			    </div>
			  </c:if>
              <!-- <div class="layui-form-item">
                <label for="L_vercode" class="layui-form-label">人类验证</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_vercode" name="vercode" required lay-verify="required" placeholder="请回答后面的问题" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">
                  <span style="color: #c00;">1+1=?</span>
                </div>
              </div> -->
              <div class="layui-form-item">
                <button class="layui-btn" lay-filter="push" lay-submit>立即发布</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@ include file="../common/footer.html"%>
<script>
layui.use(['upload','layer','form'], function(){
	  var $ = layui.jquery
	  ,form = layui.form
    ,layer = parent.layer === undefined ? layui.layer : top.layer;
	  
form.on('submit(push)', function(data){
	//弹出loading
	var userid=${user.getUserid()};
	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    		$.post('<c:url value="/thread/pushthread"></c:url>',{
    			userid:userid,
    			blockid:$("#blocks").val(),
    			title:$("#L_title").val(),
    			content:$("#L_content").val(),
    			status:$("input[type='radio']:checked").val(),
    		},function(res){
            	top.layer.msg(res.msg);
            })
        setTimeout(function(){
            top.layer.close(index);
            location.reload();
        },2000);
        return false;
    });
})
</script>
</body>
</html>