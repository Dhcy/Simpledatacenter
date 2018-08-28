function whalesController($scope, $http,$filter,T) {
	$scope.graphfun = {
		"dataList_1_1_graph": function (data, xdata, ydata) {

		}
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
		}
	}
	$scope.tablesearch="";
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	
	
	$scope.$watch("tablesearch", function (nv, ov) {		
		$scope.$watch("pageStartFrom", function (nv, ov) {
		$scope.panel_1.dataList_1.limit.now=0;
			console.log($G_pageLimitCount);
		$scope.panel_1.dataList_1.limit.count=
			Math.ceil($G_pageLimitCount/$scope.panel_1.dataList_1.limit.max)-1;
		});
	});
	
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
	
	$scope.selectWhaleUserList=function(){

		console.log($scope.searchData);
		$http.post("whales/selectWhaleUserList.action",$scope.searchData).success(function(data){
            console.log(data)
			$scope.panel_1.dataList_1.data=data;     
			
			$scope.panel_1_change=true;
			
		})
	};
	
	
	$scope.getAllData = function () {
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
			
			$scope.selectWhaleUserList();
	}
}