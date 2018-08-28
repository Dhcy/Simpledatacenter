function playersConversionController($scope,$http,T){
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
							name: T.T('新增付费玩家(按角色)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newPayAccounts')).newArr
						},{
							name: T.T('新增付费玩家(按账户)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'accounts')).newArr
						},
						{
							name: T.T('付费率(按账户)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newPayAccountsRate')).newArr
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
							name: T.T('累计付费玩家(账户)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'accounts')).newArr
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
							name: T.T('总体付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'rate')).newArr
						},
						{
							name: T.T('总体账号付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'payRate')).newArr
						}
						]
				});			
			},
		"dataList_2_1_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_2.dataList_1.data,'statdate')).newArr			
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
							name: T.T('首日付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'rate')).newArr
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
							categories: ($scope.dataDateArr( $scope.panel_2.dataList_2.data,'statdate')).newArr			
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
							name: T.T('首周付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'rate')).newArr
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
							categories: ($scope.dataDateArr( $scope.panel_2.dataList_3.data,'statdate')).newArr			
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
							name: T.T('首月付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_3.data,'rate')).newArr
						}]
				});			
			},
		"dataList_3_1_graph":function(data,xdata,ydata){
				$('.panel_3 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'title')).newArr			
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
							name: T.T('付费玩家(账户)'),
							data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'accounts')).newArr
						}]
				});			
			},
		"dataList_3_2_graph":function(data,xdata,ydata){
				$('.panel_3 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'title')).newArr			
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
							name: T.T('付费玩家(账户)'),
							data: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'accounts')).newArr
						}]
				});			
			},
		"dataList_4_1_graph":function(data,xdata,ydata){
				$('.panel_4 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'level')).newArr			
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
							name: T.T('玩家首付等级(账户)'),
							data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'accounts')).newArr
						}]
				});			
			},
		"dataList_5_1_graph":function(data,xdata,ydata){
				$('.panel_5 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_5.dataList_1.data,'amount')).newArr			
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
							name: T.T('付费玩家(账户)'),
							data: ($scope.dataPushArr( $scope.panel_5.dataList_1.data,'accounts')).newArr
						}]
				});			
			},
		"dataList_6_1_graph":function(data,xdata,ydata){
				$('.panel_6 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: xdata			
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
							name: T.T('付费玩家(账户)'),
							data: data
						}]
				});			
			},
			"dataList_7_1_graph":function(data,xdata,ydata){
				$('.panel_7 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: $scope.moneyRange,			
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
							name: $scope.searchData.startdate+T.T('至')+$scope.searchData.enddate,
							data: $scope.moneyArr
						}]
				});			
			},
			"dataList_7_2_graph":function(data,xdata,ydata){
				$('.panel_7 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: $scope.leverRange			
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
							name: $scope.searchData.startdate+T.T('至')+$scope.searchData.enddate,
							data: $scope.leverArr
						}]
				});			
			},
			"dataList_7_3_graph":function(data,xdata,ydata){
				$('.panel_7 .dataList_3 .graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_7.dataList_3.data,'channelName')).newArr		
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
							name: $scope.searchData.startdate+T.T('至')+$scope.searchData.enddate,
							data: ($scope.dataPushArr( $scope.panel_7.dataList_3.data,'accounts')).newArr
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
				'orderBy':{'s':'','t':''},
				'showPane1Total':false,
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
			}
		}
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
		}
		$scope.panel_5={	
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
			}
		}
		$scope.panel_6={	
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
			}
		}
		
		$scope.panel_7={	
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
		//金额区间
		$scope.moneyRange=['0-5', '5.01-10', '10.01-20', '20.01-30', '30.01-40', '40.01-50', '50.01-100', '100+'];
		//等级区间
		$scope.leverRange=['1-10','11-20','21-30','31-40','41-50','51-60','61-70','71+'];
		//金额
		$scope.moneyArr=[];
		//等级
		$scope.leverArr=[];
		//首付金额
		$scope.selectNumOfFirstPayMoney=function(){
			$http.post("players/conversion/selectNumOfFirstPayMoney.action",$scope.searchData).success(function(data, status, headers, config){
				console.log(data);
				if(data == undefined||data ==null){
					data = [];
				}
				for(var i=0;i<data.length;i++){
						$scope.moneyArr.splice(0,$scope.moneyArr.length);
						$scope.moneyArr.push(data[i].cnt0To5);
						$scope.moneyArr.push(data[i].cnt5To10);
						$scope.moneyArr.push(data[i].cnt10To20);
						$scope.moneyArr.push(data[i].cnt20To30);
						$scope.moneyArr.push(data[i].cnt30To40);
						$scope.moneyArr.push(data[i].cnt40To50);
						$scope.moneyArr.push(data[i].cnt50To100);
						$scope.moneyArr.push(data[i].cntHigh100);
				}
				$scope.panel_7.dataList_1.data=data;
				$scope.panel_7_change = true;
			});
		}
		//首付等级
		$scope.selectNumOfFirstPayLevel=function(){
			$http.post("players/conversion/selectNumOfFirstPayLevel.action",$scope.searchData).success(function(data, status, headers, config){
				console.log(data);
				if(data == undefined||data ==null){
					data = [];
				}
				for(var i=0;i<data.length;i++){
					$scope.leverArr.splice(0,$scope.leverArr.length);
					$scope.leverArr.push(data[i].cntOfLevel1To10);
					$scope.leverArr.push(data[i].cntOfLevel11To20);
					$scope.leverArr.push(data[i].cntOfLevel21To30);
					$scope.leverArr.push(data[i].cntOfLevel31To40);
					$scope.leverArr.push(data[i].cntOfLevel41To50);
					$scope.leverArr.push(data[i].cntOfLevel51To60);
					$scope.leverArr.push(data[i].cntOfLevel61To70);
					$scope.leverArr.push(data[i].cntOfLevelHigh71);
			}
				$scope.panel_7.dataList_2.data=data;
				$scope.panel_7_change = true;
			});
		}
		//首付方式
		$scope.selectFirstPayWay=function(){
			$http.post("players/conversion/selectFirstPayWay.action",$scope.searchData).success(function(data, status, headers, config){
				console.log(data);
				if(data == undefined||data ==null){
					data = [];
				}
				$scope.panel_7.dataList_3.data=data;
				$scope.panel_7_change = true;
			});
		}
		$scope.cityData=[];//获取的区域列表
		$scope.cityArr = []; //筛选后的区域列表
		$scope.cityTextArr = [];
		$scope.cityText = T.T("请选择");
		$scope.showCheckAllText=false;//勾选全选时，文本显示全选
		$scope.showTotalPayRate=true;//默认显示没区域的
	    //勾选区域
	    $scope.setCityList=function(i){
	        var isHad = $scope.cityArr.indexOf(i);
	        if(isHad>=0){       
	        	$scope.cityArr.splice(isHad,1);
	            $scope.cityTextArr.splice(isHad,1);
	        }else{
	        	$scope.cityArr.push(i);
	            $scope.cityTextArr.push(i);
	        }  
	        if(!$scope.isSelectedAll()){
	        	//非全选状态下
	        	$scope.showCheckAllText=false;
	        }else{
	        	//全选状态下
	        	$scope.showCheckAllText=true;
	        }
	        if(!$scope.showCheckAllText){
				 if($scope.cityTextArr.length>0){
			            $scope.cityText= $scope.cityTextArr.join(",")
			        }else{
			        	$scope.cityText= T.T("请选择")
			        }
			}else{
				$scope.cityText= T.T("全选");
			}
	        //显示6个字符
	        if($scope.cityText.length>6){
	        	$scope.cityText=$scope.cityText.substr(0,6);
	        }
	    }
	  //点击全选按钮时
	    $scope.clickAllSelect=function(){
	    	var isCheckAll=$scope.isSelectedAll();
	    	if(!isCheckAll){
	    		//要勾全选
	    		$scope.cityArr.splice(0,$scope.cityArr.length);//清空
	    		 $scope.cityTextArr.splice(0,$scope.cityTextArr.length);
	    		for(var i=0;i<$scope.cityData.length;i++){//添加内容
	    			$scope.cityArr.push($scope.cityData[i].city);
		            $scope.cityTextArr.push($scope.cityData[i].city);
	    		}
	    		$scope.showCheckAllText=true;
	    	}else{
	    		//取消全选
	    		$scope.cityArr.splice(0,$scope.cityArr.length);//清空
	    		$scope.cityTextArr=[];
	    		$scope.showCheckAllText=false;
	    	}
	    		if(!$scope.showCheckAllText){
	    			 if($scope.cityTextArr.length>0){
	    		            $scope.cityText= $scope.cityTextArr.join(",")
	    		        }else{
	    		        	$scope.cityText= T.T("请选择")
	    		        }
	    		}else{
	    			$scope.cityText= T.T("全选");
	    		}
	    	
	    	 if($scope.cityText.length>6){
	         	$scope.cityText=$scope.cityText.substr(0,6);
	         }
	    }
	    //是否全选
	    $scope.isSelectedAll = function () {
	        return $scope.cityArr.length === $scope.cityData.length;
	      };
	    //判断加载后的区域是否选中
	    $scope.isSelected = function (id) {
	        return $scope.cityArr.indexOf(id) >= 0;
	      };
		 //加载城市列表
	  	$scope.selectRechargeCityList=function(){
	  		$http.post("search/selectRechargeCityList.action",$scope.searchData).success(function(result){
	  			console.info("区域:");
	  			console.info(data);
	  			$scope.cityData.splice(0,$scope.cityData.length);//清空数组 
	  			if(result==undefined ||result==null){
	  				result=[];
	  			}
	  			$scope.cityData=result;
	  			if($scope.cityData.length!=0){
	  				for (var i=0;i<$scope.cityData.length;i++){
	  					var id=$scope.cityData[i].city;
	  					$scope.cityData[i].id=id;
	  				}
	  			}
	  		});
	  	};
		
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
		            'cityList':($scope.cityArr.length==0||$scope.showCheckAllText==true)?null:$scope.cityArr
		        }
			$scope.selectRechargeCityList();//加载城市区域
			$http.post("players/conversion/selectNewChargeUserNumList.action",$scope.searchData).success(function(data, status, headers, config){
				if(data[0] == undefined){
					data[0] = [];
				}
				$scope.panel_1.dataList_1.data=data[0].newPaidPlayers;
				$scope.panel_1.dataList_1.SUM=data[0].sum;
				$scope.panel_1.dataList_1.totalNewPayAccount=data[0].totalNewPayAccount;
				$scope.panel_1.dataList_1.totalnewPayAccountsRate=data[0].totalnewPayAccountsRate;
				$scope.panel_1_change = true;
				if($scope.panel_1.dataList_1.data.length!=0){
					$scope.panel_1.dataList_1.showPane1Total=true;
				}
			});
			$http.post("players/conversion/selectChargeUserNumList.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_1.dataList_2.data=data;
			});
			$http.post("players/conversion/selectTotalPaidRate.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_1.dataList_3.data=data;
			});
			
			$http.post("players/conversion/selectFirstDayChargeRate.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_2.dataList_1.data=data;
				$scope.panel_2_change = true;
			});
			
			$http.post("players/conversion/selectFirstWeekChargeRate.action",$scope.searchData).success(function(data, status, headers, config){
				console.log(data);
				$scope.panel_2.dataList_2.data=data;
			});
			$http.post("players/conversion/selectFirstMonthChargeRate.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_2.dataList_3.data=data;
			});
			
			//$http.post("testData/conversion/gamePlayDays.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("players/conversion/selectFirstChargeTotalDayNumList.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_3.dataList_1.data=data[0].gamePlaytittle;
				$scope.panel_3.dataList_1.AVG=data[0].AVG;
				$scope.panel_3.dataList_1.MD=data[0].MD;
				$scope.panel_3_change = true;
			});
			/*$http.post("testData/conversion/totalGamePlayTime.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
				$scope.panel_3.dataList_2.data=data.totalGamePlaytittle;
				$scope.panel_3.dataList_2.AVG=data.AVG;
				$scope.panel_3.dataList_2.MD=data.MD;
			});*/
			
			//$http.post("testData/conversion/firstPayByLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			$http.post("players/conversion/selectFirstChargeLevel.action",$scope.searchData).success(function(data, status, headers, config){
				$scope.panel_4.dataList_1.data=data[0].firstPayByLevel;
				$scope.panel_4.dataList_1.AVG=data[0].AVG;
				$scope.panel_4.dataList_1.MD=data[0].MD;
				$scope.panel_4_change = true;
			});
			/*$http.post("testData/conversion/sumOfInitialPayment.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
				$scope.panel_5.dataList_1.data=data.sumOfInitialPayment;
				$scope.panel_5_change = true;
			});
			$http.post("testData/conversion/itemPackagesForInitialPayment.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
				$scope.panel_6.dataList_1.data=data.itemPackagesForInitialPayment;
			});*/
			$scope.selectNumOfFirstPayMoney();
			$scope.selectNumOfFirstPayLevel();
			$scope.selectFirstPayWay();
			//没区域或者全选区域查询的隐藏累计付费玩家、总体付费率
			if($scope.cityArr.length==0||$scope.showCheckAllText==true){
				$scope.showTotalPayRate=true;
			}else{
				$scope.showTotalPayRate=false;
			}
			
	};
}