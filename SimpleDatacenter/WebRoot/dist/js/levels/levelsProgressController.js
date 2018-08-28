function levelsProgressController($scope,$http,T){
		$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'level')).newArr			
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
							name: T.T('首日等级'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'count')).newArr,
							tooltip: {
								valueSuffix: T.T('个')
							}
						}]
				});			
			},	
		"dataList_1_2_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'level')).newArr			
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
							name: T.T('首周等级'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'count')).newArr,
							tooltip: {
								valueSuffix: T.T('个')
							}
						}]
				});			
			},	
		"dataList_1_3_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_3 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'level')).newArr			
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
							name: T.T('14日等级'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'count')).newArr,
							tooltip: {
								valueSuffix: T.T('个')
							}
						}]
				});			
			},	
		"dataList_2_1_graph":function(data,xdata,ydata){
			$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
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
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'level')).newArr	,
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
						name: T.T('新增首日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day0')).newArr	
					},{
						name: T.T('+1日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day1')).newArr	
					},{
						name: T.T('+2日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day2')).newArr	
					},{
						name: T.T('+3日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day3')).newArr	
					},{
						name: T.T('+4日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day4')).newArr	
					},{
						name: T.T('+5日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day5')).newArr	
					},{
						name: T.T('+6日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'day6')).newArr	
					},]
			});		
		},
		
		//新付费玩家
		"dataList_3_1_graph":function(data,xdata,ydata){
			$('.panel_2 .dataList_6 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
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
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'level')).newArr	,
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
						name: T.T('新增首日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day0')).newArr	
					},{
						name: T.T('+1日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day1')).newArr	
					},{
						name: T.T('+2日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day2')).newArr	
					},{
						name: T.T('+3日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day3')).newArr	
					},{
						name: T.T('+4日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day4')).newArr	
					},{
						name: T.T('+5日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day5')).newArr	
					},{
						name: T.T('+6日'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_6.data,'day6')).newArr	
					},]
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
		},
		'dataList_6':{
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
	$scope.$watch("mchange.date", function (nv, ov) {
		if ($scope.mchange.date) {
			$scope.getAllData();
			$scope.mchange.date = false;
		}
	});
	$scope.$watch("mchange.channelQf", function (nv, ov) {
		if ($scope.mchange.channelQf) {
			$scope.callbackChannelQf();
			$scope.getAllData();
			$scope.mchange.channelQf = false;
		}
	});
	
	$scope.selectFirst0DayLevelPlayerData = function(){
		//$http.post("testData/levelAnalytics/newPlayerProgress/firstDayLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1' : 0,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$http.post("level/selectFirstNDayLevelPlayerData.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {data:[],avg:0};
			}
			$scope.panel_1.dataList_1.data=data.data;
			$scope.panel_1.dataList_1.AVG=data.avg;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectFirst6DayLevelPlayerData = function(){
		//$http.post("testData/levelAnalytics/newPlayerProgress/firstWeekLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1' : 6,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		
		$http.post("level/selectFirstNDayLevelPlayerData.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {data:[],avg:0};
			}
			$scope.panel_1.dataList_2.data=data.data;
			$scope.panel_1.dataList_2.AVG=data.avg;
		});
	}
	
	$scope.selectFirst13DayLevelPlayerData = function(){
		//$http.post("testData/levelAnalytics/newPlayerProgress/first14DaysLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1' : 13,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		
		$http.post("level/selectFirstNDayLevelPlayerData.action",$scope.searchData).success(function(data, status, headers, config){
			
			if(data == undefined){
				data = {data:[],avg:0};
			}
			$scope.panel_1.dataList_3.data=data.data;
			$scope.panel_1.dataList_3.AVG=data.avg;
		});	
	}
	
	$scope.selectNDayPlayerLevelChange = function(){
		//$http.post("testData/levelAnalytics/newPlayerProgress/newPlayerLevelChange.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$http.post("level/selectNDayPlayerLevelChange.action",$scope.searchData).success(function(data, status, headers, config){
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
		});
		
		$http.post("level/selectNPayPlayerLevelChange.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(data);
			$scope.panel_2.dataList_6.data=data;
			$scope.panel_2_change = true;
		});
		
	}
	
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
	        }
		$scope.selectFirst0DayLevelPlayerData();
		$scope.selectFirst6DayLevelPlayerData();
		$scope.selectFirst13DayLevelPlayerData();
		$scope.selectNDayPlayerLevelChange();
//		$http.post("testData/levelAnalytics/newPlayerProgress/firstDayLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_1.data=data["firstDayLevels"].result;
//			$scope.panel_1.dataList_1.AVG=data["firstDayLevels"].avg;
//			$scope.panel_1_change = true;
//		});
//		$http.post("testData/levelAnalytics/newPlayerProgress/firstWeekLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_2.data=data["firstWeekLevels"].result;
//			$scope.panel_1.dataList_2.AVG=data["firstWeekLevels"].avg;
//		});	$http.post("testData/levelAnalytics/newPlayerProgress/first14DaysLevels.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_3.data=data["first14DaysLevels"].result;
//			$scope.panel_1.dataList_3.AVG=data["first14DaysLevels"].avg;
//		});	
//		$http.post("testData/levelAnalytics/newPlayerProgress/newPlayerLevelChange.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_2.dataList_1.data=data["newPlayerLevelChange"];
//			$scope.panel_2_change = true;
//		});
	}

}