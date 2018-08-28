/**
 * 账号流失
 * @param $scope
 * @param $http
 * @return
 */
function accountLostController($scope,$http,T){
	$scope.graphfun={		
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
								formatter: function() {
									return this.value;
								}
							}
						},{
							title:"",
							labels: {
								formatter: function() {
									return this.value+"%";
								}
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
							name: T.T('每日流失数(账户)'),
							type: 'column',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'accounts')).newArr
						},{
							name: T.T('每日流失率(%)'),
							type: 'spline',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'rate')).newArr
						}]
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
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								formatter: function() {
									return this.value;
								}
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
							name: $scope.panel_2.dataList_1.th.type[$scope.chumplayer],
							data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'accounts')).newArr
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
			'th':{
				'type':{"-1":T.T("全部玩家(账户数)"),"1":T.T("付费玩家(账户数)"),"0":T.T("非付费玩家(账户数)")},
			}
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
			'orderBy':{'s':'','t':''},
			'th':{
				'type':['',T.T('流失前等级'),T.T('用户生命期'),T.T('付费金额'),T.T('付费次数')],
			}
		}
	};

	
	$scope.day=1;	//连续N天不登陆
	$scope.palyerType=-1;	//-1：全部玩家 ，1：付费玩家，0：非付费玩家
	$scope.chumplayer=1;	//1：流失前等级 ，2：用户生命期 ，3：付费金额 ，4：付费次数
	$scope.changeday=function(i){
		$scope.day=i;
		$scope.getAllData();
	}
	$scope.changetype=function(i){
		$scope.palyerType=i;
		$scope.getAllData();
	}
	$scope.changechumplayer=function(i){
		$scope.chumplayer=i;
		$scope.getChumPlayerData();
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
	$scope.getAllData=function(){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1':$scope.day,
	            'checktype2':$scope.palyerType,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	    }
		$http.post("players/accountChurn/selectPerDayLostNumAndRate.action",$scope.searchData).success(function(data, status, headers, config){
			console.log("每日流失:")
			console.log(data);
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change = true;
		});
		$scope.getChumPlayerData();
	};
	
	$scope.getChumPlayerData=function(){
		$scope.searchData2={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype3':$scope.chumplayer,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	            'checktype1':$scope.day,
	            'checktype2':$scope.palyerType
	    }
		
		$http.post("players/accountChurn/selectLostUserAnalysNumList.action",$scope.searchData2).success(function(data, status, headers, config){
			console.log("流失用户:");
			console.log(data);
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
		});
	
	}
	

	
}