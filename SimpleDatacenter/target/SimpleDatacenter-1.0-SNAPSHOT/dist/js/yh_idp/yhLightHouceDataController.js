$main.controller('yhLightHouceDataController',yhLightHouceDataController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.yhLightHouceData',{//灯塔数据
    	url:'/yhLightHouceData',
    	templateUrl:'template/yh_idp/yhLightHouceData.html?T='+Math.random(),
    	controller:'yhLightHouceDataController'
    })
	
})
function yhLightHouceDataController($scope,$http,$timeout,T){
	$scope.graphfun={
			"dataList_1_1_graph":function(data,xdata,ydata){
					$('.panel_1 .dataList_1 .graph').highcharts({
							title:{text:""},
							chart: {type: 'spline'},
							colors: ["#3cc12f", "#b2de54", "#ea1313", "#ca0404", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							xAxis: {
								type: 'linear',
								tickmarkPlacement: 'on',
								categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'hourText')).newArr
							},
							yAxis: [{
								title:"",
								labels: {
									format: '{value} '
								}
							}],
							tooltip: {
								shared: true
							},
							plotOptions: {
								area: {
									//stacking: 'normal',
									fillOpacity:0.25,
									marker: {
										enabled: false,
				//						symbol: 'circle',
										radius: 1,
										states: {
											hover: {
												enabled: true
											}
										}
									}
								}
							},
							series: [{
								name: T.T('游戏初始化'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'gameInitFailcntRate')).newArr,
								tooltip: {
									valueSuffix: '%'
								}
							},
							{
								name: T.T('更新'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'updateResFailcntRate')).newArr,
								tooltip: {
									valueSuffix: '%'
								}
							},
							{
								name: T.T('登录鉴权'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'loginAuthFailcntRate')).newArr,
								tooltip: {
									valueSuffix: '%'
								}
							},{
								name: T.T('进入大厅'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'enterGameFailcntRate')).newArr,
								tooltip: {
									valueSuffix: '%'
								}
							}]
					});
				},
				"dataList_2_1_graph":function(data,xdata,ydata){
					var _data = $scope.panel_2.dataList_1.data;
					var text = []
					for (var i = 0; i < _data.length; i++) {
						text.push(_data[i].typeName)
					}
					
					var templateArr = [];
					var maxSuccnt = Math.max.apply(null, _data.map(function(item) {
						return item.succnt
					}))
					var colorArr = ['#85c1ff', '#66b3ff', '#45a3ff', '#2895ff', '#0081ff', '#0072e3', '#0066cd', '#005ab5', '#034c96', '#024384']
					var template = function(value, name, color) {
						return {value: value, name: name, itemStyle: {normal: {color: color}}}
					}
					
					for (var i = 0; i < _data.length; i++) {
						var item = _data[i];
						templateArr.push(template(
								Math.floor(item.succnt / maxSuccnt * 100),
								item.typeName + ': ' + item.succnt +'/'+item.count+'(' + item.succntRate + '%)',
								colorArr[i]
						))
					}

					var myChart = echarts.init($('.panel_2 .dataList_1 .graph')[0]);
					var app = {};
		            option = null;
		            option = {
		                tooltip: {
		                    trigger: 'item',
		                    formatter: "{a} <br/>{b}"
//		                    	 : {c}%
		                },
		                toolbox: {
		                    show: false
		                },
		                legend: {
		                    data: text,
		                    top: '280px',
		                    textStyle: {
		                        color: '#999'
		                    }
		                },
		                calculable: true,
		                series: [
		                    {
		                        name:T.T('漏斗图'),
		                        type:'funnel',
		                        left: '10%',
		                        top: 60,
		                        //x2: 80,
		                        bottom: 60,
		                        width: '80%',
		                        height: '200px',
		                        min: 0,
		                        max: 100,
		                        minSize: '20%',
		                        maxSize: '100%',
		                        sort: 'none',
		                        gap: 0,
		                        label: {
		                            normal: {
		                                show: true,
		                                position: 'inside',
		                                color: '#000',
		                                fontSize: 14
		                            },
		                            emphasis: {
		                                textStyle: {
		                                    fontSize: 20
		                                }
		                            }
		                        },
		                        labelLine: {
		                            normal: {
		                                length: 10,
		                                lineStyle: {
		                                    width: 1,
		                                    type: 'solid'
		                                }
		                            }
		                        },
		                        itemStyle: {
		                            normal: {
		                                borderColor: '#fff',
		                                borderWidth: 0
		                            }
		                        },
		                        data: templateArr
		                    }
		                ]
		            };
		            if (option && typeof option === "object") {
		                myChart.setOption(option, true);
		            }
				},
				"dataList_3_1_graph":function(data,xdata,ydata){
					var arr = $scope.panel_3.dataList_1.data.map(function(item) {
						return [item.qjtype + '：<br />' + item.times, item.fpsRate]
					})
					$('.panel_3 .dataList_1 .graph').highcharts({
							title:{text:$scope.typeText+T.T("FPS区间分布情况")},
							chart: {
			                    plotBackgroundColor: null,
			                    plotBorderWidth: null,
			                    plotShadow: false
			                },
							colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							tooltip: {
								shared: true
							},
							plotOptions: {
							   pie: {
		                        allowPointSelect: true,
		                        cursor: 'pointer',
		                        dataLabels: {
		                            enabled: true,
		                            format: '<b>{point.name}</b>: ({point.percentage:.1f} %)',
		                            style: {
		                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                            }
		                        }
		                      }
							},
							series: [{
			                    type: 'pie',
			                    name: 'Browser share',
			                    data: arr
			                }]
					});
				},
				"dataList_4_1_graph":function(data,xdata,ydata){
					$('.panel_4 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'spline'
							},
						colors: ["#15c8f3", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'datetime',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'hourText')).newArr
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value} '
							}
						}],
						tooltip: {
							shared: true
						},
						plotOptions: {
							area: {
								//stacking: 'normal',
								fillOpacity:0.25,
								marker: {
									enabled: false,
			//						symbol: 'circle',
									radius: 1,
									states: {
										hover: {
											enabled: true
										}
									}
								}
							}
						},
						series: [{
							name: '3G',
							data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'avgDelayTime_3G')).newArr,
						},
						{
							name: '4g',
							data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'avgDelayTime_4G')).newArr,
						},
						{
							name: 'WIFI',
							data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'avgDelayTime_WIFI')).newArr,
						}]
					});
				},
				"dataList_4_2_graph":function(data,xdata,ydata){
					var arr = $scope.panel_4.dataList_2.data.map(function(item) {
						return [item.qjtype + '：<br />' + item.count, item.fpsRate]
					})
					$('.panel_4 .dataList_2 .graph').highcharts({
							title:{text:T.T("WIFI平均延时分段占比")},
							chart: {
			                    plotBackgroundColor: null,
			                    plotBorderWidth: null,
			                    plotShadow: false
			                },
							colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							tooltip: {
								shared: true
							},
							plotOptions: {
							   pie: {
		                        allowPointSelect: true,
		                        cursor: 'pointer',
		                        dataLabels: {
		                            enabled: true,
		                            format: '<b>{point.name}</b>: ({point.percentage:.1f} %)',
		                            style: {
		                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                            }
		                        }
		                      }
							},
							series: [{
			                    type: 'pie',
			                    name: T.T('比值'),
			                    data: arr
			                }]
					});
				},
				"dataList_4_3_graph":function(data,xdata,ydata){
					var arr = $scope.panel_4.dataList_3.data.map(function(item) {
						return [item.qjtype + '：<br />' + item.count, item.fpsRate]
					})
					$('.panel_4 .dataList_3 .graph').highcharts({
							title:{text:T.T("4G平均延时分段占比")},
							chart: {
			                    plotBackgroundColor: null,
			                    plotBorderWidth: null,
			                    plotShadow: false
			                },
							colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							tooltip: {
								shared: true
							},
							plotOptions: {
							   pie: {
		                        allowPointSelect: true,
		                        cursor: 'pointer',
		                        dataLabels: {
		                            enabled: true,
		                            format: '<b>{point.name}</b>: ({point.percentage:.1f} %)',
		                            style: {
		                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                            }
		                        }
		                      }
							},
							series: [{
			                    type: 'pie',
			                    name: T.T('比值'),
			                    data: arr
			                }]
					});
				},
		};
	
	$scope.typeText = 'Android';
	$scope.typeText1='Android';
	$scope.panel_1={
			'model':'graph',
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
		};
	$scope.panel_2={
			'model':'graph',
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
		};
	$scope.panel_3={
			'model':'graph',
			'systemType':1,
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
			'dataList_2':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
		};
	$scope.panel_4={
			'model':'graph',
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
			'dataList_2':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
			'dataList_3':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
		};
	$scope.panel_5={
			'model':'table',
			'systemType':1,
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			},
		};
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
	$scope.panel_4_change=false;
	$scope.panel_5_change=false;
	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()))).format("yyyy-MM-dd"),
			endDate:(new Date( parseInt((new Date()).getTime()))).format("yyyy-MM-dd"),
			opened1:false,
			opened2:false
		}
	//查询条件
    $scope.postData={
    		startdate:'',
    		gameid:'',
    }
	$scope.selectLoginFailRateList=function(){
		$http.post("yhLightHouceData/selectPerLinkLoginFailRateList.action",$scope.postData).success(function(data){
			if(data.result==undefined||data.result==null){
				data.result=[];
			}
			$scope.panel_1.dataList_1.data=data.result;
			$scope.panel_1_change=true;
		})
	}
	$scope.selectLoginTransformRateList=function(){
		$http.post("yhLightHouceData/selectLoginSucceedRateList.action",$scope.postData).success(function(data){
			if(data.status != 1){
				data.result=[];
			}
			$scope.panel_2.dataList_1.data=data.result;
			$scope.panel_2_change=true;
		})
	}
	$scope.selectFpsDistributeRateList=function(){
		$scope.postData.checktype1=$scope.panel_3.systemType;
		return $http.post("yhLightHouceData/selectFpsDistributeRateList.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_3.dataList_1.data=data.result;
			$scope.panel_3_change=true;
		})
	}
	$scope.selectSystemHFpsDistributeRateList=function(){
		$scope.postData.checktype3=$scope.panel_5.systemType;
		return $http.post("yhLightHouceData/selectSystemHFpsDistributeRateList.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_5.dataList_1.data=data.result;
			$scope.panel_5_change=true;
		})
	}
	$scope.selectAveDelayTimeByHour=function(){
		return $http.post("yhLightHouceData/selectAveDelayTimeByHour.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_4.dataList_1.data=data.result;
			$scope.panel_4_change=true;
		})
	}
	
	$scope.selectAveDelayTimeForWifi=function(){
		$scope.postData.checktype2='WIFI'
		return $http.post("yhLightHouceData/selectAveDelayCountByInterval.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_4.dataList_2.data=data.result;
			$scope.panel_4_change=true;
		})
	}
	$scope.selectAveDelayTimeFor4G=function(){
		$scope.postData.checktype2='4G'
		return $http.post("yhLightHouceData/selectAveDelayCountByInterval.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_4.dataList_3.data=data.result;
			$scope.panel_4_change=true;
		})
	}
	
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.selectLoginFailRateList();
    	$scope.selectLoginTransformRateList();
    	$scope.selectFpsDistributeRateList();
    	$scope.selectSystemHFpsDistributeRateList();
    	$scope.selectAveDelayTimeByHour();
    	$scope.selectAveDelayTimeForWifi().success(function(){
    		$scope.selectAveDelayTimeFor4G();
    	});
    	
    }
    
    $scope.toggle = function() {
    	if ($scope.panel_3.systemType === 1) {
    		$scope.typeText = 'IOS'
    		$scope.panel_3.systemType = 0
    	} else {
    		$scope.typeText = T.T('安卓')
    		$scope.panel_3.systemType = 1
    	}
    	$scope.selectFpsDistributeRateList().success(function() {
    		$scope.graphfun.dataList_3_1_graph()//切换数据改变刷新图形
    	})
    }
    $scope.toggle1 = function() {
    	if ($scope.panel_5.systemType === 1) {
    		$scope.typeText1 = 'IOS'
    		$scope.panel_5.systemType = 0
    	} else {
    		$scope.typeText1 = T.T('安卓')
    		$scope.panel_5.systemType = 1
    	}
    	$scope.selectSystemHFpsDistributeRateList();
    }
    
    $scope.secheduleSelect=function(){
    	var selectTime=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	var nowTime=(new Date( parseInt((new Date()).getTime()))).format("yyyy-MM-dd");
    	if(selectTime!=nowTime){
    		console.log('is not nowTIme!');
    		return ;
    	}else{
    		console.log('execute request!');
    		$scope.getAllData();
    	}
    }
  //加载下拉框
    $scope.getAllData();
  //定时查询
    var timer
    $scope.sechdule=function() { //1分钟
    	timer = $timeout(function() {
    		$scope.secheduleSelect()
    		$scope.sechdule();
    	},60000);
    }
    $scope.sechdule();
    $scope.$on('$destroy',function(){
 	   $timeout.cancel(timer);
 	})
}