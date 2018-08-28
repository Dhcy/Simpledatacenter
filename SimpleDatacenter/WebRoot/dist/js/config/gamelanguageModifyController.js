function gamelanguageModifyController($scope, $http, $stateParams, $state,T) {
	$scope.ctype = $stateParams.ctype;
	$scope.id = $stateParams.id;
	$scope.title = $scope.ctype == 2 ? T.T("修改") : T.T("添加");
	$scope.gameList = [];
	$scope.userData = {
		//id : $scope.userId ? $scope.userId : -1,
		gameid : "",
		version_name : ""
	}
	$scope.postData = function() {
		if ($scope.ctype == 1) {	//添加
			$http.post("game/addGameLanguage.action", $scope.userData).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {	//修改
			$http.post("#", $scope.userData).success(
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
	
	
	$scope.backGo = function() {
		$state.go('main.config.gamelanguage');
	}

	$scope.getUserData = function() {
		$http.post("#", {
			"id" : $scope.id
		}).success(function(data) {
			$scope.userData = data;
		})
	}
	
	$scope.ctype == 1 ? "" : $scope.getUserData();
	
	$scope.getGameList();
}