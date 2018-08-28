function permissionModifController($scope,$http,$stateParams,$state,T){
	$scope.ctype = $stateParams.ctype;
	$scope.per_id = $stateParams.id;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
	$scope.firstMenuList = [];
	$scope.menuTypeList = [
	                       {id:0,name:T.T("通用")},	
	                       {id:1,name:T.T("单机")},
	                       {id:2,name:T.T("联网")},
	];
	$scope.permissionData = {
		id : $scope.ctype == 1 ? "" : $scope.per_id,
		firstId : "",
		key : "",
		actkey : "",
		name : "",
		url : "",
		permission : "",
		menuType:0
	}
	
	$scope.getFirstMenuList = function() {
		$http.get("menu/firstMenuList.action").success(function(data){
			$scope.firstMenuList = data;
			$scope.permissionData.firstId = data[0].id;
		})
	}
	
	// 重置参数
	$scope.resetData = function() {
		$scope.permissionData = {};
		$scope.getFirstMenuList();
	}
	
	$scope.postData = function() {
		if ($scope.ctype == 1) {
			$http.post("menu/addSecondMenu.action", $scope.permissionData).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {
			$http.post("menu/editSecondMenu.action", $scope.permissionData).success(
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

	$scope.backGo = function() {
		$state.go('main.user.permission');
	}

	$scope.getUserData = function() {
		$http.post("menu/findSecondMenu.action", {
			"id" : $scope.per_id
		}).success(function(data) {
			$scope.permissionData = data;
		})
	}
	
	$scope.getFirstMenuList();

	$scope.ctype == 1 ? "" : $scope.getUserData();
}