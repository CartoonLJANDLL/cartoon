<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">
	<head>
	<meta charset="utf-8">
	<title>首页--纵横国漫后台管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>' media="all">
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>' media="all">
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<div class="layui-row layui-col-space10 panel_box">
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="http://fly.layui.com/case/u/3198216" target="_blank">
				<div class="panel_icon layui-bg-green">
					<i class="layui-anim seraph icon-look"></i>
				</div>
				<div class="panel_word">
					<span>66666</span>
					<cite>网站流量</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="https://github.com/BrotherMa/layuicms2.0" target="_blank">
				<div class="panel_icon layui-bg-black">
					<i class="layui-anim seraph icon-good"></i>
				</div>
				<div class="panel_word">
					<span>3128</span>
					<cite>番剧总点击</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="https://gitee.com/layuicms/layuicms2.0" target="_blank">
				<div class="panel_icon layui-bg-red">
					<i class="layui-anim seraph icon-liulanqi"></i>
				</div>
				<div class="panel_word">
					<span>${newsnumber}</span>
					<cite>资讯数量</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/user/userList.html">
				<div class="panel_icon layui-bg-orange">
					<i class="layui-anim seraph icon-icon10" data-icon="icon-icon10"></i>
				</div>
				<div class="panel_word">
					<span>${usernumber}</span>
					<cite>用户总数</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/systemSetting/icons.html">
				<div class="panel_icon layui-bg-cyan">
					<i class="layui-anim layui-icon" data-icon="&#xe655;">&#xe655;</i>
				</div>
				<div class="panel_word">
					<span>${threadnumber}</span>
					<em>帖子数量</em>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;">
				<div class="panel_icon layui-bg-blue">
					<i class="layui-anim seraph icon-clock"></i>
				</div>
				<div class="panel_word">
					<span class="loginTime"></span>
					<cite>上次登录时间</cite>
				</div>
			</a>
		</div>
	</div>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-lg6 layui-col-md12">
			<blockquote class="layui-elem-quote title">系统基本参数</blockquote>
			<table class="layui-table magt0">
				<colgroup>
					<col width="150">
					<col>
				</colgroup>
				<tbody>
				<tr>
					<td>当前版本</td>
					<td class="version"></td>
				</tr>
				<tr>
					<td>开发作者</td>
					<td class="author"></td>
				</tr>
				<tr>
					<td>网站首页</td>
					<td class="homePage"></td>
				</tr>
				<tr>
					<td>服务器环境</td>
					<td class="server"></td>
				</tr>
				<tr>
					<td>数据库版本</td>
					<td class="dataBase"></td>
				</tr>
				<tr>
					<td>最大上传限制</td>
					<td class="maxUpload"></td>
				</tr>
				<tr>
					<td>当前用户权限</td>
					<td class="userRights"></td>
				</tr>
				</tbody>
			</table>
			<blockquote class="layui-elem-quote title">最新帖子 <i class="layui-icon layui-red">&#xe756;</i>
			</blockquote>
			<table class="layui-table mag0" lay-skin="line">
				<colgroup>
					<col>
					<col width="110">
				</colgroup>
				<tbody class="news"></tbody>
			</table>
		</div>
	</div>

	<script type="text/javascript" src='<c:url value="/resources/layuicms/layui/layui.js"></c:url>'></script>
	<script>
	//获取系统时间
	var newDate = '';
	getLangDate();
	//值小于10时，在前面补0
	function dateFilter(date){
	    if(date < 10){return "0"+date;}
	    return date;
	}
	function getLangDate(){
	    var dateObj = new Date(); //表示当前系统时间的Date对象
	    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
	    var month = dateObj.getMonth()+1; //当前系统时间的月份值
	    var date = dateObj.getDate(); //当前系统时间的月份中的日
	    var day = dateObj.getDay(); //当前系统时间中的星期值
	    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
	    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
	    var hour = dateObj.getHours(); //当前系统时间的小时值
	    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
	    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
	    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
	    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
	    document.getElementById("nowTime").innerHTML = "亲爱的刘江，"+timeValue+"好！ 欢迎登录纵横国漫网后台管理系统。当前时间为： "+newDate+"　"+week;
	    setTimeout("getLangDate()",1000);
	}

	layui.use(['form','element','layer','jquery'],function(){
	    var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer,
	        element = layui.element;
	        $ = layui.jquery;
	    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
	    $(".loginTime").html(newDate.split("日")[0]+"日</br>"+newDate.split("日")[1]);
	    //icon动画
	    $(".panel a").hover(function(){
	        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
	    },function(){
	        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
	    })
	    $(".panel a").click(function(){
	        parent.addTab($(this));
	    })
	    //系统基本参数
	    if(window.sessionStorage.getItem("systemParameter")){
	        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
	        fillParameter(systemParameter);
	    }else{
	        $.ajax({
	            url :'<c:url value="/resources/layuicms/json/systemParameter.json"></c:url>',
	            type : "get",
	            dataType : "json",
	            success : function(data){
	                fillParameter(data);
	            }
	        })
	    }
	    //填充数据方法
	    function fillParameter(data){
	        //判断字段数据是否存在
	        function nullData(data){
	            if(data == '' || data == "undefined"){
	                return "未定义";
	            }else{
	                return data;
	            }
	        }
	        $(".version").text(nullData(data.version));      //当前版本
	        $(".author").text(nullData(data.author));        //开发作者
	        $(".homePage").text(nullData(data.homePage));    //网站首页
	        $(".server").text(nullData(data.server));        //服务器环境
	        $(".dataBase").text(nullData(data.dataBase));    //数据库版本
	        $(".maxUpload").text(nullData(data.maxUpload));    //最大上传限制
	        $(".userRights").text(nullData(data.userRights));//当前用户权限
	    }

	    //最新帖子列表
	    $.get('<c:url value="../admin/getnewthread"></c:url>',function(data){
	        var hotNewsHtml = '';
	        for(var i=0;i<5;i++){
	            hotNewsHtml += '<tr>'
	                +'<td align="left"><a href="javascript:;">'+data.data[i].title+'</a></td>'
	                +'<td>'+data.data[i].time.substring(0,10)+'</td>'
	                +'</tr>';
	        }
	        $(".news").html(hotNewsHtml);
	        $(".userAll span").text(data.length);
	    })

	})

	</script>
</body>
</html>