function projectController($scope,$http,$stateParams,T){
	//$scope.searchData={name:"",realname:"",role:1};
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
	
	$scope.delUser=function(data){
		console.log(data)
		var isdel = confirm(T.T("确定删除项目:")+data.name);
        
        if (isdel == true) {
            $http.post("project/deleteProject.action",{"id":data.id,"platformid":data.platformid}).success(function(data){
                alert(data.message)
                $scope.getAllData();
                $scope.panel_1.dataList_1.data=data;
		      })
        }

	}
	
	
	$scope.getAllData = function () {
		
		$http.post("project/projectList.action",{}).success(function(data){
			//console.log(data);
			$scope.panel_1.dataList_1.data=data;
		})
		
	};
	$scope.getAllData();
}