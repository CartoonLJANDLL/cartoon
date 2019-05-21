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
	var chart = Highcharts.chart('container', {
		chart: {
		    zoomType: 'x'
		  },
		credits:{
		     enabled: false // 禁用版权信息
		},
		title : {
			       text: '每日新增注册用户'   
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
	      name:'新增注册用户',
	      data: []
	    }]
	  });
	var chart0=null;
	chart0 = Highcharts.chart('container0', {
		credits:{
		     enabled: false // 禁用版权信息
		},
		chart: {
		    zoomType: 'x'
		  },
		title: {
					text: '纵横国漫网日常访问量(近一月)'
			},
			subtitle: {
					text: '数据来源：liujiang.space'
			},
			xAxis: {
	            tickInterval: 7, // 坐标轴刻度间隔为一星期
	            // 时间格式化字符
	            // 默认会根据当前的刻度间隔取对应的值，即当刻度间隔为一周时，取 week 值
	            dateTimeLabelFormats: {
	                week: '%Y-%m-%d'
	            }
	        },
			yAxis: [{ // 第一个 Y 轴，放置在左边（默认在坐标）
				title: {
					text: '访问人数（单位/人）'
				},
				labels: {
					align: 'left',
					x: 3,
					y: 16,
					format: '{value:.,0f}'
				},
				showFirstLabel: false
			}, {    // 第二个坐标轴，放置在右边
				linkedTo: 0,
				gridLineWidth: 0,
				opposite: true,  // 通过此参数设置坐标轴显示在对立面
				title: {
					text: null
				},
				labels: {
					align: 'right',
					x: -3,
					y: 16,
					format: '{value:.,0f}'
				},
				showFirstLabel: false
			}],
			legend: {
				align: 'left',
				verticalAlign: 'top',
				y: 20,
				floating: true,
				borderWidth: 0
			},
			tooltip: {
				shared: true,
				crosshairs: true,
				// 时间格式化字符
				// 默认会根据当前的数据点间隔取对应的值
				// 当前图表中数据点间隔为 1天，所以配置 day 值即可
				dateTimeLabelFormats: {
					day: '%Y-%m-%d'
				}
			},
			series: [{
				name:'网站访问量（PV）',
				data: []
			},
			{
				name:'用户访问（UV）',
				data: []
			}],
			responsive: {
					rules: [{
							condition: {
									maxWidth: 500
							},
							chartOptions: {
									legend: {
											layout: 'horizontal',
											align: 'center',
											verticalAlign: 'bottom'
									}
							}
					}]
			}
	});
	//渲染近一月的网站pv和uv
  $.ajax({
	    url: '../admin/getmonthpvuv',
	    type:"post",
	    success: function(data) {
	    	chart0.xAxis[0].setCategories(data.date);
	    	chart0.series[0].setData(data.pv);
	    	chart0.series[1].setData(data.uv);
	    },
	    cache: false
	  });
	function requestData(start,end) {
		  $.ajax({
		    url: '../admin/getregisterdata',
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
	//渲染注册用户性别比例图表
	$.ajax({
	    url: '../admin/getsexcount',
	    type:"post",
	    success: function(data) {
	    	var chart1 = Highcharts.chart('container1', {
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
	    				text: '注册用户性别比例'
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
	    				name: '性别',
	    				colorByPoint: true,
	    				data: [{
	    						name: '男',
	    						y: data.male
	    				}, {
	    						name: '女',
	    						y: data.female
	    				}, {
	    						name: '其他',
	    						y: data.others
	    				}]
	    		}]
	    	});
	    },
	    cache: false
	  });
	//渲染用户活跃时间图表
	$.ajax({
	    url: '../admin/getuseractive',
	    type:"post",
	    success: function(data) {
	    	var chart2 = Highcharts.chart('container2', {
	    		credits:{
		   		     enabled: false // 禁用版权信息
		    	},
	    		chart: {
	    	        type: 'scatter',
	    	        zoomType: 'xy'
	    	    },
	    	    title: {
	    	        text: '用户活跃时长和时间段分布'
	    	    },
	    	    subtitle: {
	    	        text: '数据来源: 纵横国漫网'
	    	    },
	    	    xAxis: {
	    	        type: 'datetime',
	                dateTimeLabelFormats: {
	                    millisecond: '%H:%M:%S.%L',
	                    second: '%H:%M:%S',
	                    minute: '%H:%M',
	                    hour: '%H:%M',
	                }
	    	    },
	            tooltip: {
	                dateTimeLabelFormats: {
	                    millisecond: '%H:%M:%S.%L',
	                    second: '%H:%M:%S',
	                    minute: '%H:%M',
	                    hour: '%H:%M',
	                    day: '%Y-%m-%d',
	                    week: '%m-%d',
	                    month: '%Y-%m',
	                    year: '%Y'
	                }
	            },
	    	    yAxis: {
	    	        title: {
	    	            text: '时长(s)'
	    	        }
	    	    },
	    	    legend: {
	    	        layout: 'vertical',
	    	        align: 'left',
	    	        verticalAlign: 'top',
	    	        x: 100,
	    	        y: 70,
	    	        floating: true,
	    	        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
	    	        borderWidth: 1
	    	    },
	    	    plotOptions: {
	    	        scatter: {
	    	            marker: {
	    	                radius: 5,
	    	                states: {
	    	                    hover: {
	    	                        enabled: true,
	    	                        lineColor: 'rgb(100,100,100)'
	    	                    }
	    	                }
	    	            },
	    	            states: {
	    	                hover: {
	    	                    marker: {
	    	                        enabled: false
	    	                    }
	    	                }
	    	            },
	    	            tooltip: {
	    	                headerFormat: '<b>{series.name}</b><br>',
	    	                pointFormat: '停留时长：{point.y}s'
	    	            }
	    	        }
	    	    },
	    	    series: [{
	    	    	name:'用户',
	    	        lineWidth: 0,
	    	        color: 'rgba(223, 83, 83, .5)',
	    	        data: data
	    	    }]
	    	});
	    },
	    cache: false
	  });
});