function versionDataController($scope,$http,T){
	$scope.graphfun={
		'dataList_1_1_graph':function(data,xdata,ydata,T){
			$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'version')).newArr
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
						name: T.T('新增玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newUser')).newArr
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
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'version')).newArr
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
						name: T.T('活跃玩家'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'activeUser')).newArr
					}]
			});
		},
		'dataList_3_1_graph':function(data,xdata,ydata){
		$('.panel_3 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'version')).newArr
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
						name: T.T('登录玩家'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'loginUser')).newArr
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
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'version','t':''}
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
			'orderBy':{'s':'version','t':''}
		}
	};
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
			'orderBy':{'s':'version','t':''}
		}
	};

	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
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
	
	$scope.selectNewUserList=function(){
		$http.post("version/selectNewUserList.action",$scope.searchData).success(function(data){
        	if (data == undefined) {
        		data = [];
        	}
        	$scope.panel_1.dataList_1.data=data;
    		$scope.panel_1_change=true;
		})
	}
	
	$scope.selectActiveUserList=function(){
		$http.post("version/selectActiveUserList.action",$scope.searchData).success(function(data){
			if (data == undefined) {
        		data = [];
        	}
			$scope.panel_2.dataList_1.data=data;
    		$scope.panel_2_change=true;
		})
	}
	
	/*//与活跃玩家重复
	 * $scope.selectLoginUserList=function(){
		$http.post("version/selectLoginUserList.action",$scope.searchData).success(function(data){
			if (data == undefined) {
				data = [];
        	}
			$scope.panel_3.dataList_1.data=data;
    		$scope.panel_3_change=true;
		})
	}*/
	
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
		$scope.selectNewUserList();
		$scope.selectActiveUserList();
		//$scope.selectLoginUserList();
	}
}	//新增玩家