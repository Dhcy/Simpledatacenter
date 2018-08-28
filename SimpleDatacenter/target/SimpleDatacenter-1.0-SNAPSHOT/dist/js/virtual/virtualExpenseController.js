function virtualExpenseController($scope,$http,T){
	$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'virtualGoods')).newArr			
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
							name: T.T('消费次数-百分比'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'numberOfSpendingPercentage')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('消费额度-百分比'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'sumOfSpendingPercentage')).newArr,
							tooltip: {
								valueSuffix: '%'
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
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'statType')).newArr			
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
							name: T.T('消费次数-百分比'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'numberOfSpendingPercentage')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('消费额度-百分比'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'sumOfSpendingPercentage')).newArr,
							tooltip: {
								valueSuffix: '%'
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
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'item')).newArr	,
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
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'value')).newArr	
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
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'item')).newArr	,
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
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'value')).newArr	
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
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'item')).newArr,
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
						data: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'value')).newArr
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
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_3.data,'item')).newArr,
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
						data: ($scope.dataPushArr( $scope.panel_3.dataList_3.data,'value')).newArr
					}]
			});		
		},		
	};
	$scope.panel_1={	
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
				'max':15,
				'count':0				
			},
			'limit':{
				'sumLen':0,
				'now':0,
				'max':15,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'','t':'-numberOfSpendingPercentage'}	
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
	$scope.tabsbtn=1;
	//每页显示记录数
	$scope.changetype=function(i){
		$scope.panel_1.dataList_2.limit.max=i;
		$scope.panel_1.dataList_2.limit.now=0;
		$scope.panel_1.dataList_2.limit.sumLen=($scope.panel_1.dataList_2.data).length;
		$scope.panel_1.dataList_2.limit.count=Math.ceil($scope.panel_1.dataList_2.limit.sumLen/$scope.panel_1.dataList_2.limit.max)-1;
	}
	$scope.$watch("tabsbtn", function (nv, ov) {
		$scope.panel_1_change = true;
	});
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
	
	$scope.selectItemPoint = function(){
		//$http.post("testData/virtualExpense/itemExpense/proportionOfItemSaleByCategoryAll.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
		$http.post("virtual/itemExpense/selectItemPoint.action",$scope.searchData).success(function(data, status, headers, config){
			$scope.panel_1.dataList_2.data=data;
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
	            'checktype1' : $scope.tabsId,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$http.post("testData/virtualExpense/itemExpense/proportionOfItemSaleByCategory.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_1.dataList_1.data=data.proportionOfItemSaleByCategory;
			$scope.panel_1_change = true;
		});	
		
		$scope.selectItemPoint();
	}
}