layui.use('laydate',function(){
	var laydate = layui.laydate;
	
	Highcharts.setOptions({
	    lang:{
	       contextButtonTitle:"图表导出菜单",
	       decimalPoint:".",
	       downloadJPEG:"下载JPEG图片",
	       downloadPDF:"下载PDF文件",
	       downloadPNG:"下载PNG文件",
	       downloadSVG:"下载SVG文件",
	       drillUpText:"返回 {series.name}",
	       loading:"加载中",
	       months:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	       noData:"没有数据",
	       numericSymbols: [ "千" , "兆" , "G" , "T" , "P" , "E"],
	       printChart:"打印图表",
	       resetZoom:"恢复缩放",
	       resetZoomTitle:"恢复图表",
	       shortMonths: [ "Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"],
	       thousandsSep:",",
	       weekdays: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期天"]
	    }
	});
	
	//发帖量最多的十个用户
	var chart0 = Highcharts.chart('container0', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '用户发帖量排行'
	    },
	    subtitle: {
	        text: '来源:纵横国漫网'
	    },
	    xAxis: {
	    	type: 'category',
	        labels: {
	            rotation: -45  // 设置轴标签旋转角度
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '帖子量(个)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '帖子量: <b>{point.y} 个</b>'
	    },
	    series: [{
	        name: '帖子量',
	        data: [],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y}', // :.1f 为保留 1 位小数
	            y: 10
	        }
	    }]
	});
	//登陆签到量前十用户
	var chart1 = Highcharts.chart('container1', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '用户登陆签到量排行'
	    },
	    subtitle: {
	        text: '来源:纵横国漫网'
	    },
	    xAxis: {
	    	type: 'category',
	        labels: {
	            rotation: -45  // 设置轴标签旋转角度
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '签到量(天)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '签到量: <b>{point.y} 天</b>'
	    },
	    series: [{
	        name: '签到量',
	        data: [],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y}', // :.1f 为保留 1 位小数
	            y: 10
	        }
	    }]
	});
	//获得最热的前十个帖子
	var chart2 = Highcharts.chart('container2', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '帖子热度排行'
	    },
	    subtitle: {
	        text: '来源:纵横国漫网'
	    },
	    xAxis: {
	    	type: 'category',
	        labels: {
	            rotation: -45  // 设置轴标签旋转角度
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '评论量(条)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '评论量: <b>{point.y} 条</b>'
	    },
	    series: [{
	        name: '评论量',
	        data: [],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y}', // :.1f 为保留 1 位小数
	            y: 10
	        }
	    }]
	});
	  //主题板块帖子量排行
	var chart3 = Highcharts.chart('container3', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '板块帖子量排行'
	    },
	    subtitle: {
	        text: '来源:纵横国漫网'
	    },
	    xAxis: {
	    	type: 'category',
	        labels: {
	            rotation: -45  // 设置轴标签旋转角度
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '帖子量(个)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '帖子量: <b>{point.y} 个</b>'
	    },
	    series: [{
	        name: '帖子量',
	        data: [],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y}', // :.1f 为保留 1 位小数
	            y: 10
	        }
	    }]
	});

//发帖量最多的十个用户
  $.ajax({
	    url: '../thread/hotesttoptenUser',
	    type:"post",
	    success: function(data) {
	    	chart0.xAxis[0].setCategories(data.name);
	    	chart0.series[0].setData(data.y);
	    },
	    cache: false
	  });
	//签到量前十用户
  $.ajax({
	    url: '../thread/toptensignusers',
	    type:"post",
	    success: function(data) {
	    	chart1.xAxis[0].setCategories(data.name);
	    	chart1.series[0].setData(data.y);
	    },
	    cache: false
	  });
  //获得最热的前十个帖子
  $.ajax({
	    url: '../thread/hotesttoptenthread',
	    type:"post",
	    success: function(data) {
	    	chart2.xAxis[0].setCategories(data.name);
	    	chart2.series[0].setData(data.y);
	    },
	    cache: false
	  });
  //主题板块帖子量排行
  $.ajax({
	    url: '../block/threadnumranking',
	    type:"post",
	    success: function(data) {
	    	chart3.xAxis[0].setCategories(data.name);
	    	chart3.series[0].setData(data.y);
	    },
	    cache: false
	  });
});
