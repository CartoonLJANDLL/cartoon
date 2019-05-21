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
	var chart = Highcharts.chart('container', {
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
				text: '资讯来源网站（公司）占比'
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
	    url: '../admin/getcompanycount',
	    type:"post",
	    success: function(data){
	    	console.log(data);
	    	chart.series[0].setData(data);
	    }
	  });
	$.ajax({
	    url: '../admin/getinformationtitle',
	    type:"post",
	    success: function(res){
	    	var text=JSON.stringify(res.data).substring(0,5000);
	    	
	    	var data = text.split(/[,/.《》，：“”！？~【】 、]+/g)
	    	.reduce(function (arr, word) {
	    		var obj = arr.find(function (obj) {
	    			return obj.name === word;
	    		});
	    		if (obj) {
	    			obj.weight += 1;
	    		} else {
	    			obj = {
	    				name: word,
	    				weight: 1
	    			};
	    			arr.push(obj);
	    		}
	    		return arr;
	    	}, []);
	    	var str=[];
	    	for(var i in data){
	    		if(data[i].weight>1&data[i].name.length>1){
	    			str.push(data[i]);
	    		}
	    	}
	    	var chart0 = Highcharts.chart('container0', {
	    		credits:{
	   		     enabled: false // 禁用版权信息
	    		},
	    		chart: {
	    		    zoomType: 'x'
	    		},
	    	    tooltip: {
	    	        pointFormat: '频率: <b>{point.y:.1f} 次</b>'
	    	    },
	    		series: [{
	    			name: '频率',
					colorByPoint: true,
	    			type: 'wordcloud',
	    			data: str
	    		}],
	    		title: {
	    			text: '资讯词云图'
	    		}
	    	});
	    }
	  });
	var chart1 = Highcharts.chart('container1', {
	    chart: {
	        type: 'column'
	    },
	    credits:{
  		     enabled: false // 禁用版权信息
   		},
	    title: {
	        text: '资讯用户点击量排行'
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
	        pointFormat: '点击量: <b>{point.y:.1f} 次点击</b>'
	    },
	    series: [{
	        name: '点击量',
	        data: [
	            ['上海', 24.25],
	            ['卡拉奇', 23.50],
	            ['北京', 21.51],
	            ['德里', 16.78],
	            ['拉各斯', 16.06],
	            ['天津', 15.20],
	            ['伊斯坦布尔', 14.16],
	            ['东京', 13.51],
	            ['广州', 13.08],
	            ['孟买', 12.44],
	            ['莫斯科', 12.19],
	            ['圣保罗', 12.03],
	            ['深圳', 10.46],
	            ['雅加达', 10.07],
	            ['拉合尔', 10.05],
	            ['首尔', 9.99],
	            ['武汉', 9.78],
	            ['金沙萨', 9.73],
	            ['开罗', 9.27],
	            ['墨西哥', 8.87]
	        ],
	        dataLabels: {
	            enabled: true,
	            rotation: -90,
	            color: '#FFFFFF',
	            align: 'right',
	            format: '{point.y:.1f}', // :.1f 为保留 1 位小数
	            y: 10
	        }
	    }]
	});
	
});