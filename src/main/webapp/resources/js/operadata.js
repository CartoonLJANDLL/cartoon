layui.use('laydate',function(){
	var laydate = layui.laydate;
	laydate.render({
	    elem: '#date1'
	    ,range: true
	    ,value:'2019-04-01 - 2019-04-25'
	    ,done: function(value, date, endDate){
	    	var start=value.substring(0,10),end=value.substring(13);
	    	requestData(start,end);
	      }
	  });
	$(document).ready(function(){requestData('2019-04-01','2019-04-25')});
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
	//渲染指定时间段的每日新增注册用户图表
	var chart = Highcharts.chart('container0', {
		chart: {
		    zoomType: 'x'
		  },
		credits:{
		     enabled: false // 禁用版权信息
		},
		title : {
			       text: '番剧数量变化'   
			   },
	   xAxis: {
            tickInterval: 2, // 坐标轴刻度间隔为两天
            tickWidth: 0,
            gridLineWidth: 1,
            // 时间格式化字符
            // 默认会根据当前的刻度间隔取对应的值，即当刻度间隔为一周时，取 week 值
            dateTimeLabelFormats: {
                week: '%Y-%m-%d'
            }
        },
	   yAxis : {
	      title: {
	         text: '注册人数(单位/人)'
	      },
	      plotLines: [{
	         value: 0,
	         width: 1,
	         color: '#808080'
	      }]
	   },
	   tooltip : {
	      valueSuffix: '人'
	   },
	   legend : {
		  enabled: false,
	      layout: 'vertical',
	      align: 'right',
	      verticalAlign: 'middle',
	      borderWidth: 0
	   },
	    series: [{
	      name:'番剧数量',
	      data: []
	    }]
	  });
	var chart1 = Highcharts.chart('container1', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '番剧用户点击量排行'
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
	            text: '点击量(人)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '点击量: <b>{point.y} 次点击</b>'
	    },
	    series: [{
	        name: '点击量',
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
	var chart2 = Highcharts.chart('container2', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '番剧用户收藏量排行'
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
	            text: '收藏量(人)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: '收藏量: <b>{point.y} 次收藏</b>'
	    },
	    series: [{
	        name: '收藏量',
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
	var chart3 = Highcharts.chart('container', {
		credits:{
		     enabled: false // 禁用版权信息
		},
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
				text: '番剧类型占比'
		},
		tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
				pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						dataLabels: {
								enabled: true,
								format: '<b>{point.name}</b>: {point.percentage:.1f} %',
								style: {
										color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
								}
						}
				}
		},
		series: [{
				name: '占比',
				colorByPoint: true,
				data: []
		}]
	});
	//渲染注册用户性别比例图表
	$.ajax({
	    url: '../admin/operatypenumrate',
	    type:"post",
	    success: function(data){
	    	console.log(data);
	    	chart3.series[0].setData(data);
	    }
	  });
	//番剧数量变化
	function requestData(start,end) {
		  $.ajax({
		    url: '../opera/opamountchange',
		    type:"post",
		    data:{
		    	"startdate":start,
		    	"enddate":end
		    	},
		    success: function(data) {
		    	chart.xAxis[0].setCategories(data.x);
		    	chart.series[0].setData(data.y);
		    },
		    cache: false
		  });
		}
	//获得点击量前10的番剧
  $.ajax({
	    url: '../admin/clicknumsort',
	    type:"post",
	    success: function(data) {
	    	chart1.xAxis[0].setCategories(data.name);
	    	chart1.series[0].setData(data.y);
	    },
	    cache: false
	  });
  //获得收藏量前10的番剧
  $.ajax({
	    url: '../admin/collectnumsort',
	    type:"post",
	    success: function(data) {
	    	chart2.xAxis[0].setCategories(data.name);
	    	chart2.series[0].setData(data.y);
	    },
	    cache: false
	  });
});