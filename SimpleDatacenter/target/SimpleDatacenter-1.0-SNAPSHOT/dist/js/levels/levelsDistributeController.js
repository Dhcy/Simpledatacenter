function levelsDistributeController($scope,$http,T){
		$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'statType')).newArr			
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
							name: T.T('玩家等级分布'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'activePlayers')).newArr,
							tooltip: {
								valueSuffix: T.T('人')
							}
						}]
				});			
			},					
		"dataList_2_1_graph":function(data,xdata,ydata){
			$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'column'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'statType')).newArr	,
					},{
						categories: [0,1,2,3],
						opposite: true
					}],
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('游戏次数等级分布'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'gameCounts')).newArr	
					}]
			});		
		},	
		"dataList_3_1_graph":function(data,xdata,ydata){
				$('.panel_3 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						legend: {
							layout: 'vertical',  
							align: 'right',       
							verticalAlign: 'top', 
							x: 0,               
							y: 0,                                                        					floating: true,       
							borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
							shadow: true                                                   				},
						xAxis: [{
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'statType')).newArr,
						},{
							categories: [0,1,2,3],
							opposite: true
						}],
						yAxis: {
							title:"",
							labels: {
								formatter: function() {
									return this.value;
								}
							}
						},
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
							name: T.T('7日未登录玩家'),
							data: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'day7')).newArr
						}]
				});		
			},				
		"dataList_3_2_graph":function(data,xdata,ydata){
			$('.panel_3 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'column'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'statType')).newArr,
					},{
						categories: [0,1,2,3],
						opposite: true
					}],
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('14日未登录玩家'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'day14')).newArr
					}]
			});		
		},				
		"dataList_3_3_graph":function(data,xdata,ydata){
			$('.panel_3 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'column'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_3.data,'statType')).newArr,
					},{
						categories: [0,1,2,3],
						opposite: true
					}],
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('30日未登录玩家'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_3.data,'day30')).newArr
					}]
			});		
		},
		"dataList_4_1_graph":function(data,xdata,ydata){
			$('.panel_4 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'line'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'statType')).newArr,
					}],
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('时段等级玩家'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'durationLevPlayCnt')).newArr
					},{
						name: T.T('当前等级玩家'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'currLevPlayCnt')).newArr
					}]
			});		
		},	
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
		'dataList_4':{
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
		'dataList_5':{
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
		}
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
		'dataList_4':{
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
		'dataList_5':{
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
		}
	};
	$scope.panel_3={	
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
		'dataList_4':{
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
		'dataList_5':{
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
		}
	};
	
	$scope.panel_4={	
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
			}
		};
	$scope.$watch("mchange.channelQf",function(nv,ov){
		if($scope.mchange.channelQf){
			$scope.callbackChannelQf();	
			$scope.getData();
			$scope.mchange.channelQf=false;
		}
	});
	 $scope.dateOptions = {
				formatYear: 'yy',
				timezone:'+0800',
				mode:$scope.mode || 'day',
				minMode:$scope.minmode || '',
				maxMode:$scope.maxmode || '',
				maxDate: $scope.maxdate?new Date($scope.maxdate):"",
				minDate: new Date(2000,1,1),
				startingDay: 1
			  };
	 $scope.open1 = function() {
			$scope.popup1.opened = true;
		  };
		$scope.popup1 = {
			opened: false
		  };
	
	$scope.selectLevelDistributionOfActivePlayer = function(){
		$http.post("level/selectLevelDistributionOfActivePlayer.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = [];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectLevelDistributionOfPlayTimes = function(){
		$http.post("level/selectLevelDistributionOfPlayTimes.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = [];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
		});
	}

	$scope.selectLevelOfLossPlayer = function(){
		$http.post("level/selectLevelOfLossPlayer.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(data);
			if(data == undefined){
				data = [];
			}
			$scope.panel_3.dataList_1.data=data;
			$scope.panel_3.dataList_2.data=data;
			$scope.panel_3.dataList_3.data=data;
			$scope.panel_3_change = true;
		});
	}
	//等级玩家数
	$scope.selectPlayerCntOfLevelList = function(){
		$http.post("level/selectPlayerCntOfLevelList.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(data);
			if(data == undefined||data==null){
				data = [];
			}
			$scope.panel_4.dataList_1.data=data;
			$scope.panel_4_change = true;
		});
	}
	//日期格式
	$scope.unix_to_datetime=function(unix) {
		var now = new Date((unix));
		if(!$scope.format)
		return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		else{
			return now.format($scope.format);
		}

	}
	//单独一个日期
	$scope.postData={
			'startTime':$scope.unix_to_datetime(new Date()-86400000),	
	};
	$scope.getData=function(){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate':$scope.unix_to_datetime($scope.postData.startTime),
	            'enddate': $scope.unix_to_datetime($scope.postData.startTime),
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$scope.selectLevelDistributionOfActivePlayer();
		$scope.selectLevelDistributionOfPlayTimes();
		$scope.selectLevelOfLossPlayer();
		$scope.selectPlayerCntOfLevelList();
		
		/*$http.post("testData/levelAnalytics/levelDistribute/playersByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_1.dataList_1.data=data["playersByLevel"];
			$scope.panel_1_change = true;
		});
		$http.post("testData/levelAnalytics/levelDistribute/gameSessionsByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_2.dataList_1.data=data["gameSessionsByLevel"];
			$scope.panel_2_change = true;
		});	$http.post("testData/levelAnalytics/levelDistribute/7DaysChurningByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_1.data=data["7DaysChurningByLevel"];
			$scope.panel_3_change = true;
		});	$http.post("testData/levelAnalytics/levelDistribute/14DaysChurningByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_2.data=data["14DaysChurningByLevel"];
		});	$http.post("testData/levelAnalytics/levelDistribute/30DaysChurningByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_3.data=data["30DaysChurningByLevel"];
		});*/
	}

}