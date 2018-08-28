/**
 * 在线数据控制器
 * @param $scope
 * @param $http
 * @return
 */
function concurrentDataController($scope,$http,$timeout,T){
	$scope.graphfun={
		"onlineL_graph":function(data,xdata,ydata){
			$('#onlineL_graph').highcharts({
				chart: {
					type: 'solidgauge',
				},
				title: null,
				pane: {
					center: ['50%', '85%'],
					size: '140%',
					startAngle: -90,
					endAngle: 90,
					background: {
						backgroundColor: '#cecece',
						innerRadius: '60%',
						outerRadius: '100%',
						shape: 'arc'
					}
				},
				tooltip: {
					enabled: false
				},
				yAxis: {
					min: 0,
					max: xdata || 0,
					stops: [
						[0.1, '#55BF3B'], // green
						[0.5, '#DDDF0D'], // yellow
						[0.9, '#DF5353'] // red
					],
					lineWidth: 0,
					minorTickInterval: null,
					tickPixelInterval: 400,
					tickWidth: 0,
					title: {
						y: -85,
						text: T.T('在线用户(5)')
					},
					labels: {
						y: 25,
						format: ' '
					},

				},
				plotOptions: {
					solidgauge: {
						dataLabels: {
							y: 40,
							borderWidth: 0,
							useHTML: true
						}
					}
				},
				credits: {
					enabled: false
				},
				series: [{
					name: 'playerCount',
					id: "playerCount",
					data: data||[0],
					backgroundColor: '#000',
					dataLabels: {
						format: '<div style="text-align:center"><span style="font-size:25px;color:' +
						((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
						'<span style="font-size:12px;color:silver"></span></div><p style="text-align:center;color:silver">30 Day Max<br/>{series.yAxis.max}</p>'
					},
				}]
			});
		},
		"onlineR_graph":function(data,xdata,ydata){
				$('#onlineR_graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.onlinePlaneData.yesterdayHourList,'time')).newArr
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
						series: [
							{
								name: T.T('今天'),
								data: ($scope.dataPushArr( $scope.onlinePlaneData.todayHourList,'acu')).newArr,
								tooltip: {
									valueSuffix: T.T('账户')
								}
							},
							{
								name: T.T('昨天'),
								data: ($scope.dataPushArr( $scope.onlinePlaneData.yesterdayHourList,'acu')).newArr,
								tooltip: {
									valueSuffix: T.T('账户')
								}
							},
						]
				});
			},
		"dataList_1_1_graph":function(data,xdata,ydata){
			console.log($scope.panel_1);
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'spline'
							},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'datetime',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'description')).newArr
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
							name: T.T('时段启动次数均值'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'avg_time_count')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						}]
				});
			},
			"dataList_2_1_graph":function(data,xdata,ydata){
				console.log($scope.panel_1);
					$('.panel_2 .dataList_1 .graph').highcharts({
							title:{text:""},
							chart: {type: 'spline'
								},
							colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							xAxis: {
								type: 'datetime',
								tickmarkPlacement: 'on',
								categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'description')).newArr
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
								name: T.T('人数'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'start_count')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							}]
					});
				},
				"dataList_3_1_graph":function(data,xdata,ydata){
					var datelist = ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'statdate')).newArr
					var timelist = []
					var series = []
					for(var i = 0;i < 24; i++) {
	            	  timelist.push(i+":00")
	                }        
					for(var i = 0;i < datelist.length; i++) {
	                	var o = {
                			name: datelist[i],
							data: [],
							tooltip: {
								valueSuffix: ''
							}
	                	}
	                	for(var n =0; n < $scope.panel_3.dataList_1.data.length;n++){//hour0_acu
	                		if($scope.panel_3.dataList_1.data[n].statdate == datelist[i]) {
			                	for(var k = 0;k < 24; k++) {
			  	            	  o.data.push($scope.panel_3.dataList_1.data[n]['hour'+k+'_acu'])
			  	                }  
	                		}
	                	}
	                	series.push(o)
	                }      
						$('.panel_3 .dataList_1 .graph').highcharts({
								title:{text:""},
								chart: {type: 'spline'
									},
								colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
								xAxis: {
									type: 'datetime',
									tickmarkPlacement: 'on',
									categories: timelist
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
								series: series
						});
					}
	};

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
			'model':'table',
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
	$scope.$watch("mchange.date", function (nv, ov) {
		if ($scope.mchange.date) {
			$scope.getAllData();
			$scope.mchange.date = false;
		}
	});
	$scope.test="";
	$scope.selectTimeDistributeList = function(){
		$http.post("concurrent/selectTimeDistributeList.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(T.T("时段分布"));
			if(data==undefined||data==null){
				data=[];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change = true;
		});
	}
	$scope.selectDurationDistributeList = function(){
		$http.post("concurrent/selectDurationDistributeList.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(T.T("启动间隔分布"));
			if(data==undefined||data==null){
				data=[];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
		});
	}
	$scope.selectDurationAcuList = function(){
		$http.post("concurrent/selectDurationAcuList.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(T.T("各时段acu"));
			if(data==undefined||data==null){
				data=[];
			}
			$scope.panel_3.dataList_1.data=data;
			$scope.panel_3_change = true;
		});
	}

	$scope.$watch("mchange.channelQf", function (nv, ov) {
		if ($scope.mchange.channelQf) {
			$scope.callbackChannelQf();
			$scope.getAllData();
			$scope.mchange.channelQf = false;
		}
	});
	$scope.searchData={};
	$scope.getAllData=function(){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
				'currDate':(new Date()).format("yyyy-MM-dd")
	        }
		$scope.selectTimeDistributeList();
		$scope.selectDurationDistributeList();
		$scope.getMaxValue();
		$scope.selectDurationAcuList();
	}

	$scope.onlinePlaneData={
		todayHourList:[],
		yesterdayHourList:[],
	};
	$scope.maxValue=0;
	$scope.playerCount="";
	$scope.counti=5;
	$scope.act="";
	$scope.getMaxValue=function(){
		$http.post("concurrent/selectTodayAndYesterdayHourList.action",$scope.searchData).success(function(data, status, headers, config){
			$scope.maxValue=data.maxPcu;
			$scope.onlinePlaneData.todayHourList=data.todayHourList;
			$scope.onlinePlaneData.yesterdayHourList=data.yesterdayHourList;
			console.log($scope.onlinePlaneData);
			$scope.graphfun.onlineL_graph([0],$scope.maxValue);
			$scope.graphfun.onlineR_graph();
			$scope.playerCount=$('#onlineL_graph').highcharts().get('playerCount');
			$scope.getOnlinePlayer();
		});
	}
	$scope.getOnlinePlayer=function(){
		$scope.counti--;
		if($scope.counti==0){	$http.post("concurrent/selectGameOnlineCntList.action",$scope.searchData).success(function(data, status, headers, config){
				var dataarr = [];
				dataarr[0]=data.count;
				if(dataarr[0]>$scope.playerCount.chart.yAxis[0].max)
				{
					$scope.playerCount.chart.yAxis[0].update({
						max:dataarr[0]
					});
				}
				$scope.playerCount.setData(dataarr, true, false, false);
				$scope.counti=5;
				$scope.playerCount.yAxis.update({
					title: {
						text:T.T("在线用户(")+$scope.counti+")"
					}
				});
				$scope.act=$timeout($scope.getOnlinePlayer,1000);
			});

		}else{
			$scope.playerCount.yAxis.update({
				title: {
					text:T.T("在线用户(")+$scope.counti+")"
				}
			});
			$scope.act=$timeout($scope.getOnlinePlayer,1000);
		}
	}
	$scope.$on('$destroy',function(){
	   $timeout.cancel($scope.act);
	})
}
