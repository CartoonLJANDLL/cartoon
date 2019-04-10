<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>HTML5电脑端微信聊天窗口界面</title>
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/reset.min.css">
<link rel="stylesheet" href="../resources/css/chatstyle.css">
</head>
<body>
<div class="wrapper">
	<div class="container">
        <div class="left">
            <div class="top">
                <input type="text" placeholder="Search" />
                <a href="javascript:;" class="search"></a>
            </div>
            <ul class="people">
            	<c:forEach items="${friends }" var="item"  varStatus="status">
	                <li class="person" data-chat="person${status.count}">
	                    <img src='<c:url value="${item.getHeadurl() }"></c:url>' alt="${item.getUsername() }">
	                    <span class="name">${item.getUsername() }</span>
	                    <span class="time">2:09 PM</span>
	                    <span class="preview">我想说...</span>
	                </li>
	            </c:forEach>
                <li class="person" data-chat="person2">
                    <img src="img/dog.png" alt="" />
                    <span class="name">沃福桑</span>
                    <span class="time">1:44 PM</span>
                    <span class="preview">我已经忘记了之前的感觉了</span>
                </li>
                <li class="person" data-chat="person3">
                    <img src="img/louis-ck.jpeg" alt="" />
                    <span class="name">罗睿思</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">但是我们已经开始操作了.</span>
                </li>
                <li class="person" data-chat="person4">
                    <img src="img/bo-jackson.jpg" alt="" />
                    <span class="name">杰克斯</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">还没到最坏的地步...</span>
                </li>
                <li class="person" data-chat="person5">
                    <img src="img/michael-jordan.jpg" alt="" />
                    <span class="name">乔丹</span>
                    <span class="time">2:09 PM</span>
                    <span class="preview">沙漠地图</span>
                </li>              
            </ul>
        </div>
        <div class="right">
            <div class="top"><span>To: <span class="name">jinager</span></span></div>
            <c:forEach items="${friendmsgs }" var="item"  varStatus="status">
	            <div class="chat" data-chat="person${status.count }" data-id="${item.getReceiverid() }">
	                <div class="conversation-start">
	                    <span>Today, 6:48 AM</span>
	                </div>
	                <c:choose>
	                	<c:when test="${item.getReceiverid()==user.getUserid() }">
	                	    <div class="bubble you">
	                    		${item.getContent()}
	                		</div>
	                	</c:when>
	                	<c:otherwise>
	                		<div class="bubble me">
			                    ${item.getContent()}
			                </div>
	                	</c:otherwise>
	                </c:choose>
	            </div>
            </c:forEach>
            <div class="chat" data-chat="person2">
                <div class="conversation-start">
                    <span>Today, 5:38 PM</span>
                </div>
                <div class="bubble you">
                    Hello, can you hear me?
                </div>
                <div class="bubble you">
                    I'm in California dreaming
                </div>
                <div class="bubble me">
                    ... about who we used to be.
                </div>
                <div class="bubble me">
                    Are you serious?
                </div>
                <div class="bubble you">
                    When we were younger and free...
                </div>
                <div class="bubble you">
                    I've forgotten how it felt before
                </div>
            </div>
            <div class="chat" data-chat="person3">
                <div class="conversation-start">
                    <span>Today, 3:38 AM</span>
                </div>
                <div class="bubble you">
                    Hey human!
                </div>
                <div class="bubble you">
                    Umm... Someone took a shit in the hallway.
                </div>
                <div class="bubble me">
                    ... what.
                </div>
                <div class="bubble me">
                    Are you serious?
                </div>
                <div class="bubble you">
                    I mean...
                </div>
                <div class="bubble you">
                    It’s not that bad...
                </div>
                <div class="bubble you">
                    But we’re probably gonna need a new carpet.
                </div>
            </div>
            <div class="chat" data-chat="person4">
                <div class="conversation-start">
                    <span>Yesterday, 4:20 PM</span>
                </div>
                <div class="bubble me">
                    Hey human!
                </div>
                <div class="bubble me">
                    Umm... Someone took a shit in the hallway.
                </div>
                <div class="bubble you">
                    ... what.
                </div>
                <div class="bubble you">
                    Are you serious?
                </div>
                <div class="bubble me">
                    I mean...
                </div>
                <div class="bubble me">
                    It’s not that bad...
                </div>
            </div>
            <div class="chat" data-chat="person5">
                <div class="conversation-start">
                    <span>Today, 6:28 AM</span>
                </div>
                <div class="bubble you">
                    Wasup
                </div>
                <div class="bubble you">
                    Wasup
                </div>
                <div class="bubble you">
                    Wasup for the third time like is <br />you blind bitch
                </div>

            </div>
            <div class="chat" data-chat="person6">
                <div class="conversation-start">
                    <span>Monday, 1:27 PM</span>
                </div>
                <div class="bubble you">
                    So, how's your new phone?
                </div>
                <div class="bubble you">
                    You finally have a smartphone :D
                </div>
                <div class="bubble me">
                    Drake?
                </div>
                <div class="bubble me">
                    Why aren't you answering?
                </div>
                <div class="bubble you">
                    howdoyoudoaspace
                </div>
            </div>
            <div class="write">
                <a href="javascript:;" class="write-link attach"></a>
                <input type="text" id="messages"/>
                <a href="javascript:;" class="write-link smiley"></a>
                <a href="javascript:;" class="write-link send"></a>
            </div>
        </div>
    </div>
</div>
<script src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script  src="../resources/js/chatindex.js"></script>
</body>
</html>