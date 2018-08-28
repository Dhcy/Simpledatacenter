function revenueBehaviorController($scope,$http,T){
	$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				if(!data)return;
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'payChannelName')).newArr	
						},
						yAxis: [{
							title:"",
							labels: {
								format: '￥{value} '
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
							name: T.T('收入金额'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'incomes')).newArr,
							tooltip: {
								valueSuffix: '￥'
							}
						}]
				});			
			},	
		"dataList_1_2_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'payChannelName')).newArr		
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value}次'
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
							name: T.T('充值人次'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'payUser')).newArr,
							tooltip: {
								valueSuffix: T.T('次')
							}
						}]
				});			
			},	
		"dataList_2_2_graph":function(data,xdata,ydata){
			},	
		"dataList_2_2_graph":function(data,xdata,ydata){
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
		}
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
	
	$scope.selectChargeWayOfAmount=function(){
        $http.post("revenue/behavior/selectChargeWayOfAmount.action",$scope.searchData).success(function(data){
        	console.log('selectChargeWayOfAmount');
			console.log(data);
        	if (data != undefined) {
        		$scope.panel_1.dataList_1.data=data;
        		
        		$scope.panel_1_change=true;
        	}
		})
	}
	
	$scope.selectChargeWayOfNum=function(){
		$http.post("revenue/behavior/selectChargeWayOfNum.action",$scope.searchData).success(function(data){
			console.log('selectChargeWayOfNum');
			console.log(data);
			if (data != undefined) {
        		$scope.panel_1.dataList_2.data=data;
        		
        		$scope.panel_1_change=true;
        	}
		})
	}
	
	$scope.selectConsumePackgeAmount=function(){
		//单机版不用做此需求?
		/*$http.post("revenue/behavior/selectConsumePackgeAmount.action",$scope.searchData).success(function(data){
			console.log('selectConsumePackgeAmount');
			console.log(data);
			if (data != undefined) {
				$scope.panel_2.dataList_1.data=data;
        		
        		$scope.panel_2_change=true;
        	}
		})*/
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
		$scope.selectChargeWayOfAmount();
		$scope.selectChargeWayOfNum();
		$scope.selectConsumePackgeAmount();
		/*$http.post("testData/revenue/behavior/revenue.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_1.dataList_1.data=data["revenue"];
			$scope.panel_1_change = true;
		});*/
		/*$http.post("testData/revenue/behavior/payingAccountTime.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_1.dataList_2.data=data["payingAccountTime"];
		});
		$http.post("testData/revenue/behavior/itemCategoryRevenue.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_2.dataList_1.data=data["itemCategoryRevenue"];
			$scope.panel_2_change = true;
		});
		$http.post("testData/revenue/behavior/itemCategoryPayingAccountTime.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_2.dataList_2.data=data["itemCategoryPayingAccountTime"];
		});
		
		$http.post("testData/revenue/behavior/numberOfPaymentWeekly.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_1.data=data["numberOfPaymentWeekly"];
			$scope.panel_3_change = true;
		});
		$http.post("testData/revenue/behavior/numberOfPaymentMonthly.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_2.data=data["numberOfPaymentMonthly"];
		});
		$http.post("testData/revenue/behavior/sumOfPaymentWeekly.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_3.data=data["sumOfPaymentWeekly"];
		});
		$http.post("testData/revenue/behavior/sumOfPaymentMonthly.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_3.dataList_4.data=data["sumOfPaymentMonthly"];
		});
		
		
		$http.post("testData/revenue/behavior/paymentInterval.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			$scope.panel_4.dataList_1.data=data["paymentInterval"];
			$scope.panel_4_change = true;
		});*/
	}
}