function subchannelController($scope,$http,$stateParams,T){
	$scope.panel_1 = {
		'model': 'table',
		'channelList': '',
		'gameList': '',
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
				's': 'id',
				't': ''
			}
		}
	}
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	
	$scope.delSubChannel=function(id, name){
		var isdel = confirm(T.T("确定删除子渠道:")+name);
        if (isdel == true) {
            $http.post("channel/deleteSubChannel.action",{"id":id}).success(function(data){
                alert(data.message)
                $scope.getAllData();
		      });
        }
	}
	
	$scope.getAllData = function () {
		$http.post("channel/subChannelList.action", 
				{"gameid": -1, "channelId": -1}).success(function(data){
					$scope.panel_1.dataList_1.data = data;
				});
		
	};
	$scope.data={
        gameid : -1,
        channelId : -1
    }
	$scope.gameid = -1;
	$scope.getGameList = function () {
        $http.post("game/projectGameList.action",{"projectid" : -1}).success(function(data){
        	var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
        	$scope.panel_1.gameList=data;
		})
    }
	$scope.channelid = -1;
	$scope.getChannelList = function () {
        $http.post("channel/channelList.action",{}).success(function(data){
        	var dat={id:-1,channelSimName:T.T("全部")};   
            data.unshift(dat);
        	$scope.panel_1.channelList=data;
		})
    }
	
	$scope.searchSubChannel = function () {
		$http.post("channel/subChannelList.action", 
				$scope.data).success(function(data){
					$scope.panel_1.dataList_1.data = data;
				});
	}

	$scope.getGameList();
	
	$scope.getChannelList();
	
	$scope.getAllData();
}