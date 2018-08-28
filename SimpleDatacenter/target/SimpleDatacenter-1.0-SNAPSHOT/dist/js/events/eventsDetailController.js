function eventsDetailController($scope,$http,T){
	$scope.graphfun = {
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {zoomType: 'xy'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value} '
							}
						},{
							title:"",
							labels: {
								format: '{value} '
							},
							opposite: true
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
							name: T.T('事件数(次)'),
							type: 'column',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'purchase')).newArr
						}]
				});			
			},			
		"dataList_1_2_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {zoomType: 'xy'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_2.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value}'
							}
						},{
							title:"",
							labels: {
								format: '{value}'
							},
							opposite: true
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
							name: T.T('每活跃用户产生(次)'),
							type: 'column',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'accounts')).newArr
						}]
				});			
			},			
		"dataList_1_3_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_3 .graph').highcharts({
						title:{text:""},
						chart: {zoomType: 'xy'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value} '
							}
						},{
							title:"",
							labels: {
								format: '{value} '
							},
							opposite: true
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
							name: T.T('每启动发生(次)'),
							type: 'column',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'accounts')).newArr
						}]
				});			
			},
	};
	$scope.panel_1 = {
		'model': 'table',
		'dataList_1': {
			'data': '',
			'graphLimit': {
				'sumLen': 0,
				'now': -1,
				'max': 10,
				'count': 0
			},
			'limit': {
				'sumLen': 0,
				'now': 0,
				'max': 10,
				'count': 0
			},
			'dataLimit': '',
			'orderBy': {
				's': 'ranking',
				't': ''
			}
		},
		'dataList_2': {
			'data': '',
			'graphLimit': {
				'sumLen': 0,
				'now': -1,
				'max': 10,
				'count': 0
			},
			'limit': {
				'sumLen': 0,
				'now': 0,
				'max': 10,
				'count': 0
			},
			'dataLimit': '',
			'orderBy': {
				's': 'ranking',
				't': ''
			}
		},
		'dataList_3': {
			'data': '',
			'graphLimit': {
				'sumLen': 0,
				'now': -1,
				'max': 10,
				'count': 0
			},
			'limit': {
				'sumLen': 0,
				'now': 0,
				'max': 10,
				'count': 0
			},
			'dataLimit': '',
			'orderBy': {
				's': 'ranking',
				't': ''
			}
		}
	}
	
	$scope.eventsList=[
		{id:1,name:"123"},
		{id:2,name:"456"},
		{id:3,name:"789"},
		{id:4,name:"012"},
		{id:5,name:"345"},
		{id:6,name:"678"},
		{id:7,name:"901"},
		{id:8,name:"234"},
		{id:9,name:"567"},
		{id:10,name:"89000000"},
		{id:11,name:"123"}
	];
	$scope.getEventsData=function(id){
		for(var i=0;i<$scope.eventsList.length;i++){
			if(id==$scope.eventsList[i].id){
				return $scope.eventsList[i];
			}
		}
		return false;
	}
	
	
	$scope.tablesearch="";
	$scope.$watch("tablesearch", function (nv, ov) {		
		$scope.$watch("pageStartFrom", function (nv, ov) {
		$scope.panel_1.dataList_1.limit.now=0;
			console.log($G_pageLimitCount);
		$scope.panel_1.dataList_1.limit.count=
			Math.ceil($G_pageLimitCount/$scope.panel_1.dataList_1.limit.max)-1;
		});
	});
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
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
	$scope.getAllData = function () {
		$http.post("testData/whales/paidPlayers.json?date=" + $scope.mstatdate + "&channel=" + $scope.mchannel + "&qf=" + $scope.mareaclothing + "&t=" + Math.random(), {}).success(function (data, status, headers, config) {
			$scope.panel_1.dataList_1.data=data.paidPlayers;
		});
	};
}