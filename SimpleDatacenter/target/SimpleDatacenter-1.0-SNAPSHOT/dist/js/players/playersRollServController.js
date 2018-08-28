//滚服分析
function playersRollServController($scope,$http,T){
		$scope.graphfun={
			"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'column'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'statDate')).newArr			
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
							fillOpacity:0.25,
							marker: {
		//						enabled: false,
		//						symbol: 'circle',
		//						radius: 1,
								states: {
									hover: {
										enabled: true
									}
								}
							}
						}
					},
					series: [{
						name: T.T('新增滚服数'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'across_cnt')).newArr
					},{
						name: T.T('滚服充值人数'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'acr_rcg_cnt')).newArr
					},{
						name: T.T('滚服充值金额'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'across_recharge_money')).newArr
					},{
						name: T.T('总充值人数'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'recharge_cnt')).newArr
					},{
						name: T.T('总充值金额'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'recharge_money')).newArr
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
	
	$scope.selectRollServList = function(){
		$http.post("players/RollServ/selectRollServList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data==undefined||data==null){
				$scope.panel_1.dataList_1.data=[];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change = true;
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
	            //'checktype1':$scope.panel_2.analysis.type,
	            //'checktype2':$scope.panel_2.analysis.usergroup,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		//滚服列表
		$scope.selectRollServList();
	};
	
	
	
	
}