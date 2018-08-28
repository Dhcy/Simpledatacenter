function channelModifController($scope, $http, $stateParams, $state,T) {
	$scope.ctype = $stateParams.ctype;
	$scope.channel_id = $stateParams.channelid;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
	$scope.channelData = {
		id : $scope.ctype == 1 ? "" : $scope.channel_id,
		channelSimName : "",
		channelName : "",
		createdate : ""
	}

	// 重置参数
	$scope.resetData = function() {
		$scope.channelData = {};
		for ( var key in saveData) {
			$scope.channelData[key] = saveData[key];
		}
	}

	$scope.postData = function() {
		if ($scope.ctype == 1) {
			$http.post("channel/addChannel.action", {
				"id" : $scope.channelData.id,
				"channelSimName" : $scope.channelData.channelSimName,
				"channelName" : $scope.channelData.channelName,
				"createdate" : $scope.channelData.createdate,
			}).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {
			$http.post("channel/updataChannel.action", $scope.channelData).success(
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
		$state.go('main.config.channel');
	}

	$scope.getUserData = function() {
		$http.post("channel/getChannel.action", {
			"id" : $scope.channel_id
		}).success(function(data) {
			$scope.channelData = data;
		})
	}

	$scope.ctype == 1 ? "" : $scope.getUserData();

	// 日期
	$('#createDate').datetimepicker({
		autoclose : true,
		todayHighlight : true,
		language : 'zh-CN',
		format : "yyyy-mm-dd",
		viewSelect : '4',
		maxView : [ 4, 'decade' ]
	})
}
