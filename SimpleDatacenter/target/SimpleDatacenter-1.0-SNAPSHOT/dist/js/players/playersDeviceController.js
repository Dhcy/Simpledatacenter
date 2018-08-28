function playersDeviceController($scope,$http,T){
	$scope.graphfun={
		'dataList_1_1_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'machineModel')).newArr
					},
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
						name: T.T('新增玩家(设备)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'count')).newArr
					}]
			});
		},
		'dataList_2_1_graph':function(data,xdata,ydata){
		$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'operationSystem')).newArr
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						},
						max:100
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
						name: T.T('新增玩家(设备)'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'count')).newArr
					}]
			});
		},
	}


	$scope.panel_1={
		'model':'table',
		'dataList_1':{
			"deviceSUM":0,
			"accountSUM":0,
			"devicAVG":0,
			"accountAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'system','t':''}
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
			'orderBy':{'s':'system','t':''}
		}
	};

	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData();
				$scope.mchange.date=false;
			}
		});
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.callbackChannelQf();
				$scope.getAllData();
				$scope.mchange.channelQf=false;
			}
		});
	
	$scope.selectPlayerMachineModel = function(){
		$http.post("players/device/selectPlayerMachineModel.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(data);
			if(data == undefined){
				data = [];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_2_change=true;
		});
	}
	
	$scope.selectPlayerOperationSystem = function(){
		$http.post("players/device/selectPlayerOperationSystem.action",$scope.searchData).success(function(data, status, headers, config){
			console.log(data);
			if(data == undefined){
				data = [];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change=true;
		});
	}

	$scope.getAllData=function(o){
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
		
		$scope.selectPlayerMachineModel();
		$scope.selectPlayerOperationSystem();
//		
//		$http.post("testData/new.json?date="+$scope.msystem+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
//
//			$scope.panel_1.dataList_1.deviceSUM=data.newAccounts.deviceSUM;
//			$scope.panel_1.dataList_1.accountSUM=data.newAccounts.accountSUM;
//			$scope.panel_1.dataList_1.devicAVG=data.newAccounts.devicAVG;
//			$scope.panel_1.dataList_1.accountAVG=data.newAccounts.accountAVG;
//			$scope.panel_1.dataList_1.data=data.newAccounts.data;
//			$scope.panel_2.dataList_1.data=data.newAccounts.data;
//			$scope.panel_1_change=true;
//			$scope.panel_2_change=true;
//		});
	}
}	//新增玩家