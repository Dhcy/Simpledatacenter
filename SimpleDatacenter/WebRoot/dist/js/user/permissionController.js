function permissionController($scope,$http,$stateParams,T){
	$scope.searchData={"name": ""};
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
	
	$scope.delPermission = function(id, name){
		var isdel = confirm(T.T("确定删除权限:")+name);
        if (isdel == true) {
            $http.post("menu/deleteSecondMenu.action",{"id":id}).success(function(data){
                alert(data.message)
                $scope.getAllData();
		      });
        }
	}
	
	$scope.getAllData = function () {
		$http.post("menu/getPermissionList.action",$scope.searchData).success(function(data){
			$scope.panel_1.dataList_1.data=data;
		});
	}
	
	$scope.getAllData();
}