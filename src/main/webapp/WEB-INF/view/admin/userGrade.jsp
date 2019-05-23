<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户等级</title>
<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/layui/css/layui.css"></c:url>'>
    <link rel="stylesheet" href='<c:url value="/resources/layuicms/css/public.css"></c:url>'>
    <link href='<c:url value="/resources/css/global.css"></c:url>' rel="stylesheet" />
</head>
<body class="childrenBody">
<form class="layui-form" onkeydown="if(event.keyCode==13) return false;">
	<blockquote class="layui-elem-quote">
		<a class="layui-btn layui-btn-sm addGrade">新增等级</a>　<span class="layui-word-aux">直接在表格里修改数据,新增等级后请点击提交按钮，否则新增无效</span>
	</blockquote>
	<table id="userGrade" lay-filter="userGrade"></table>
	<script type="text/html" id="gradeIcon">
		<i class="layui-badge fly-badge-vip layui-hide-xs">{{ d.gradeIcon}}</i>
	</script>
	<script type="text/html" id="gradeBar">
		{{#  if(d.gradeStatus == "1"){ }}
		<input type="checkbox" name="gradeStatus" value='{{d.id}}' lay-filter="gradeStatus" lay-event="edit" lay-skin="switch" lay-text="启用|禁用" checked>
		{{# } else if(d.gradeStatus == "0"){ }}
		<input type="checkbox" name="gradeStatus" value='{{d.id}}' lay-filter="gradeStatus" lay-event="edit" lay-skin="switch" lay-text="启用|禁用">
		{{#  }}}
	</script>
	<div class="layui-form-item layui-row layui-col-xs12" id="addbutton" style="margin-top:20px;display:none;">
		<div class="layui-input-block layui-center">
			<button class="layui-btn layui-btn-sm" id="addUserGrade">确认增加</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src='<c:url value="/resources/layui/layui.js"></c:url>'></script>
<script type="text/javascript">
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;


    //用户等级
    table.render({
        elem: '#userGrade',
        url : '<c:url value="../admin/getusergrade"></c:url>',
        cellMinWidth : 95,
        cols : [[
            {field:"id", title: 'ID', width: 60, fixed:"left",sort:"true", align:'center', edit: 'text'},
            {field: 'gradeIcon', title: '图标展示', templet:'#gradeIcon', align:'center'},
            {field: 'gradeName', title: '等级名称', edit: 'text', align:'center'},
            {field: 'gradeValue', title: '等级经验值', edit: 'text',sort:"true", align:'center'},
            {title: '当前状态',minWidth:100, templet:'#gradeBar',fixed:"right",align:"center"}
        ]]
    });
    form.on('switch(gradeStatus)', function(data){
        var tipText = '确定禁用当前会员等级？';
        var gradeStatus=0;
        if(data.elem.checked){
        	gradeStatus=1;
            tipText = '确定启用当前会员等级？'
        }
        layer.confirm(tipText,{
            icon: 3,
            title:'系统提示',
            cancel : function(index){
                data.elem.checked = !data.elem.checked;
                form.render();
                layer.close(index);
            }
        },function(index){
        	$.get('<c:url value="/admin/updateusergrade"></c:url>',{
              	 id : data.value,
              	gradeStatus:gradeStatus,
               },function(data){
              table.reload();
              layer.msg("等级状态"+data.msg);
              layer.close(index);
               });
        })
        
    });
    //新增等级
    $(".addGrade").click(function(){
        var $tr = $(".layui-table-body.layui-table-main tbody tr:last");
        if($tr.data("index") < 9) {
            var newHtml = '<tr data-index="' + ($tr.data("index") + 1) + '">' +
                '<td data-field="id" data-edit="text" align="center"><div class="layui-table-cell laytable-cell-1-id" id="id">' + ($tr.data("index") + 2) + '</div></td>' +
                '<td data-field="gradeIcon" align="center" id="gradeIcon" data-content="vip' + ($tr.data("index") + 2) + '"><div class="layui-table-cell laytable-cell-1-gradeIcon"><i class="layui-badge fly-badge-vip layui-hide-xs">VIP'+($tr.data("index") + 2)+'</i></div></td>' +
                '<td data-field="gradeName" data-edit="text" align="center"><div class="layui-table-cell laytable-cell-1-gradeName" id="gradeName">请输入等级名称</div></td>' +
                '<td data-field="gradeValue" data-edit="text" align="center"><div class="layui-table-cell laytable-cell-1-gradeValue" id="gradeValue">0</div></td>' +
                '<td data-field="' + ($tr.data("index") + 1) + '" align="center" data-content="" data-minwidth="100"><div class="layui-table-cell laytable-cell-1-' + ($tr.data("index") + 1) + '"> <input type="checkbox" name="GradeStatus" id="GradeStatus" lay-skin="switch" lay-text="启用|禁用"  checked="checked"></div></td>' +
                '</tr>';
            $(".layui-table-body.layui-table-main tbody").append(newHtml);
            $(".layui-table-fixed.layui-table-fixed-l tbody").append('<tr data-index="' + ($tr.data("index") + 1) + '"><td data-field="id" data-edit="text" align="center"><div class="layui-table-cell laytable-cell-1-id">' + ($tr.data("index") + 2) +'</div></td></tr>');
            $(".layui-table-fixed.layui-table-fixed-r tbody").append('<tr data-index="' + ($tr.data("index") + 1) + '"><td data-field="' + ($tr.data("index") + 1) + '" align="center" data-content="" data-minwidth="100"><div class="layui-table-cell laytable-cell-1-' + ($tr.data("index") + 1) + '"> <input type="checkbox" name="gradeStatus" lay-filter="gradeStatus" lay-skin="switch" lay-text="启用|禁用" checked=""><div class="layui-unselect layui-form-switch layui-form-onswitch" lay-skin="_switch"><em>启用</em><i></i></div></div></td></tr>');
            form.render();
        }else{
            layer.alert("添加的等级数量已到顶！",{maxWidth:300});
        }
        $("#addbutton").fadeIn();
    });
    $("#addUserGrade").click(function(){
    	var GradeStatus=0;
    	var $tr = $(".layui-table-body.layui-table-main tbody tr:last");
    	if($("#GradeStatus").value="on"){GradeStatus=1;}
    	if($("#gradeValue").text()==null||$("#gradeValue").text()=="0"){
    		layer.msg("不可提交空等级信息！请点击新增按钮");
    	}
    	else{
    		layer.confirm('确定增加等级?', {icon: 3, title: '提示信息'}, function (index) {
                $.get('<c:url value="/admin/addUserGrade"></c:url>',{
                	id:$("#id").text(),
                	gradeIcon : $("#gradeIcon").text(),
                	gradeName : $("#gradeName").text(),
                	gradeValue : $("#gradeValue").text(),
                	gradeStatus :GradeStatus
                },function(data){
               layer.msg(data.msg);
               layer.close(index);
               location.reload();
                })
           })	
    	}
    	
    });
    $("button[type=reset]").on('click',function(){
    	$(".layui-table-body.layui-table-main tbody tr:last").remove();
    	$("#addbutton").fadeOut();
    })
    //控制表格编辑时文本的位置【跟随渲染时的位置】
    $("body").on("click",".layui-table-body.layui-table-main tbody tr td",function(){
        $(this).find(".layui-table-edit").addClass("layui-"+$(this).attr("align"));
    });
  //监听单元格编辑
    table.on('edit(userGrade)', function(obj){
      var value = obj.value //得到修改后的值
      ,data = obj.data //得到所在行所有键值
      ,field = obj.field; //得到字段
      var gradeStatus=0;
      if(data.gradeStatus.checked){gradeStatus=1;}
      $.get('<c:url value="/admin/updateusergrade"></c:url>',{
     	 id : data.id,
     	gradeIcon:data.gradeIcon,
     	gradeName:data.gradeName,
     	gradeValue:	data.gradeValue,
     	gradeStatus:gradeStatus,
      },function(data){
     table.reload();
     layer.msg(data.msg);
     layer.close(index);
      })
    });
})
</script>
</body>
</html>