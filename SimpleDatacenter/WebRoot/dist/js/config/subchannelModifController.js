function subchannelModifController($scope, $http, $stateParams, $state,T) {
	$scope.ctype = $stateParams.ctype;
	$scope.id = $stateParams.id;
	$scope.title = $scope.ctype == 2 ? T.T("修改") : T.T("添加");
	$scope.gameList = [];
	$scope.channelList = [];
	$scope.userData = {
		//id : $scope.userId ? $scope.userId : -1,
		gameid : "",
		channelId : "0",
		subchannelId : ""
	}
	$scope.postData = function() {
		if ($scope.ctype == 1) {
			$http.post("channel/addSubChannel.action", $scope.userData).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {
			$http.post("channel/updataSubChannel.action", $scope.userData).success(
					function(data) {
						if (data.status == 0) {
							alert(data.message);
						} else {
							$scope.backGo();
						}
					}).error(function(data) {
				alert(data.message);
			});

		}
	}
	
	$scope.getGameList = function () {
        $http.post("game/projectGameList.action",{"projectid" : -1}).success(function(data){
        	$scope.gameList=data;
        	$scope.userData.gameid = data[0].id;
		})
    }
	
	$scope.getChannelList = function () {
        $http.post("channel/channelList.action",{}).success(function(data){
        	$scope.channelList=data;
		})
    }
	
	
	$scope.backGo = function() {
		$state.go('main.config.subchannel');
	}

	$scope.getUserData = function() {
		$http.post("channel/getSubChannel.action", {
			"id" : $scope.id
		}).success(function(data) {
			console.log(data)
			$scope.userData = data;
		})
	}
	
	$scope.ctype == 1 ? "" : $scope.getUserData();
	
	$scope.getGameList();
	
	$scope.getChannelList();
}