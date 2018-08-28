function virtualCurrencyController($scope,$http,T){
	$scope.graphfun={
			"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
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
			//					//stacking: 'normal',
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
							name: T.T('购入'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'purchaseSUM')).newArr
						},{
							name: T.T('赠与'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'rewardSUM')).newArr
						},{
							name: T.T('消费'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'expenseSUM')).newArr
						}]
				});			
			},
		"dataList_1_2_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_2.data,'statdate')).newArr			
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
							name: T.T('消耗次数'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'spendingTimes')).newArr
						}]
				});			
			},
		"dataList_1_3_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_3 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_3.data,'statdate')).newArr			
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
							name: T.T('消耗人数'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'spendingAccounts')).newArr
						}]
				});			
			},
		"dataList_1_4_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_4 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_4.data,'statdate')).newArr			
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
							name: T.T('累计留存'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'currencyBalance')).newArr
						}]
				});			
			},
		"dataList_2_1_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							 title:{
							       text:T.T('等级')
							   },
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'actorlevel')).newArr			
						},
						yAxis: {
							title:{
							       text:T.T('虚拟币')
							   },
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
							name: T.T('虚拟币消耗'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'expenseSUM')).newArr
						}]
				});			
			},
		"dataList_2_2_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'actorlevel')).newArr			
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
							name: T.T('消耗次数(次)'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'spendingTimes')).newArr
						}]
				});			
			},
		"dataList_2_3_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_3 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_3.data,'actorlevel')).newArr			
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
							name: T.T('消耗人数(账户数)'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_3.data,'spendingAccounts')).newArr
						}]
				});			
			}
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
			}
		}
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
			}
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
		
		$scope.selectVcPurchaseInfo = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/virtualCurrencyOutputAndConsumption.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcPurchaseInfo.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_1.dataList_1.data=data.data;
				$scope.panel_1.dataList_1.purchase = data.purchase;
				$scope.panel_1.dataList_1.reward = data.reward;
				$scope.panel_1.dataList_1.expense = data.expense;
				$scope.panel_1_change = true;
			});
		}
		$scope.selectVcConsumeTimes = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/spendingTimes.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcConsumeTimes.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_1.dataList_2.data=data;
				$scope.panel_1_change = true;
			});
		}
		$scope.selectVcConsumeNumOfPeople = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/spendingAccounts.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcConsumeNumOfPeople.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_1.dataList_3.data=data;
				$scope.panel_1_change = true;
			});
		}
		$scope.selectTotalRetentionVc = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/currencyBalance.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectTotalRetentionVc.action",$scope.searchData).success(function(data, status, headers, config){
					$scope.panel_1.dataList_4.data=data;
					$scope.panel_1_change = true;
			});
		}
		$scope.selectVcLevelConsume = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/currencyExpense.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcLevelConsume.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_2.dataList_1.data=data;
				$scope.panel_2_change = true;
			});
		}
		$scope.selectVcLevelConsumeTimes = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/spendingTimesByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcLevelConsumeTimes.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_2.dataList_2.data=data;
				$scope.panel_2_change = true;
			});
		}
		$scope.selectVcLevelConsumeNumOfPeople = function(){
			//$http.post("testData/virtualExpense/virtualCurrency/spendingAccountsByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("virtual/virtualCurrency/selectVcLevelConsumeNumOfPeople.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_2.dataList_3.data=data;
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
		            'channelList':$scope.mchannel
		            ,'checktype1' : $scope.tabsId,
		            'versionList':$scope.mversion,
		            'languageList':$scope.mlang,
		        }

			$scope.selectVcPurchaseInfo();
			$scope.selectVcConsumeTimes();
			$scope.selectVcConsumeNumOfPeople();
			$scope.selectTotalRetentionVc();
			$scope.selectVcLevelConsume();
			$scope.selectVcLevelConsumeTimes();
			$scope.selectVcLevelConsumeNumOfPeople();
	};
	$scope.panel_1.dataList_1.purchase = 0;
	$scope.panel_1.dataList_1.reward = 0;
	$scope.panel_1.dataList_1.expense = 0;
}