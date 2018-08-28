function realTimeSummaryDataController($scope,$http,T){
	$scope.graphfun={
		 //新增激活和账户
		'dataList_1_1_graph':function(data,xdata,ydata){
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
						name: T.T('激活设备(台)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'deviceActivation')).newArr
					}, {
						name: T.T('活跃玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'actUser')).newArr
					},
					 {
						name: T.T('付费玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'payUser')).newArr
					},
					 {
						name: T.T('收入'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'income')).newArr
					}]
			});			
		},
	}
	
	$scope.panel_1={
		'model':'table',
		'dataList_1':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
		}
	};
	

	$scope.panel_1_change=false;
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData();
				$scope.mchange.date=false;
			}
		});
	
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.getAllData();
				$scope.mchange.channelQf=false;
			}
		});
    
    
	$scope.selectSummaryData = function(){        
		$http.post("realTimedata/selectPlayerSummaryData.action",$scope.searchData).success(function(data){
			if(data == undefined || data==null){
				data =[];
			}
			$scope.panel_1.dataList_1.data=data;
            $scope.panel_1_change=true;
		})
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
		$scope.selectSummaryData();
	}
	
}