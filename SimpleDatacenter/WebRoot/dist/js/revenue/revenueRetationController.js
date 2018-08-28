/**
 * 收入留存
 * @param $scope
 * @param $http
 * @return
 */
function revenueRetationController($scope,$http,T){
		$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
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
							name: T.T('新增付费玩家数'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'accounts')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: T.T('1日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day1')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('2日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day2')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('3日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day3')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('4日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day4')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('5日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day5')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('6日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day6')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('7日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day7')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('14日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day14')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('30日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day30')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
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
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value}'
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
							name: T.T('新增付费玩家数'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'accounts')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: T.T('1日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day1')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('2日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day2')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('3日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day3')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('4日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day4')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('5日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day5')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('6日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day6')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('7日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day7')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('14日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day14')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
						},{
							name: T.T('30日留存率'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day30')).newArr,
							tooltip: {
								valueSuffix: '%'
							}
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
	
	$scope.selectRetationRateAndPayRateList=function(){
		$http.post("revenue/payRetention/selectRateOfRetationAndPayList.action",$scope.searchData).success(function(data){
			console.log(data);
        	if (data != undefined||data!=null) {
        		if(data.retationRatelist==undefined||data.retationRatelist==null){
        			data.retationRatelist=[];
        		}
        		
        		if(data.payRatelist==undefined||data.payRatelist==null){
        			data.payRatelist=[];
        		}
        		$scope.panel_1.dataList_1.data=data.retationRatelist;
        		$scope.panel_1.dataList_2.data=data.payRatelist;
        		$scope.panel_1_change=true;
        	}
		})
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
		$scope.selectRetationRateAndPayRateList();
	}
}