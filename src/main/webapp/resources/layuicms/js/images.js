layui.config({
	base : "../../js/"
}).use(['flow','form','layer','upload'],function(){
    var flow = layui.flow,
        form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        $ = layui.jquery;

    //设置图片的高度
    $(window).resize(function(){
        $("#Images li img").height($("#Images li img").width());
    })
    

    //弹出层
    $("body").on("click","#Images img",function(){
        parent.showImg();
    })

    //删除单张图片
    /*$("body").on("click",".img_del",function(){
        var _this = $(this);
        layer.confirm('确定删除该图片吗？',{icon:3, title:'提示信息'},function(index){
            $.ajax({
    	        type : "post",
    	        url : '/guomanwang/common/admin/deletedefaulthead', 
    	        data :{
    	        	imageid:2,//获取图片id
    	        },
    	        success : function(res)
    	        {
    	        	layer.msg(res.msg);	
    	        },
    	        error : function(xhr, status, errMsg)
    	        {
    	        	layer.msg("数据传输失败!");
    	        }
            })
            setTimeout(function(){_this.parents("li").remove();},950);
            _this.parents("li").hide(1000);
            layer.close(index);
        });
    })
*/
    //全选
    form.on('checkbox(selectAll)', function(data){
        var child = $("#Images li input[type='checkbox']");
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });

    //通过判断是否全部选中来确定全选按钮是否选中
    form.on("checkbox(choose)",function(data){
        var child = $(data.elem).parents('#Images').find('li input[type="checkbox"]');
        var childChecked = $(data.elem).parents('#Images').find('li input[type="checkbox"]:checked');
        if(childChecked.length == child.length){
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = true;
        }else{
            $(data.elem).parents('#Images').siblings("blockquote").find('input#selectAll').get(0).checked = false;
        }
        form.render('checkbox');
    })

    

})