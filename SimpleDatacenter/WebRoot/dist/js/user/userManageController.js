function userManageController($scope,$http,T){
	$scope.searchData={name:"",realname:"",role:1};
	$scope.panel_1 = {
		'model': 'table',
        'roleList': [],
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
	
	
	$scope.delUser=function(id, name){
		var isdel = confirm(T.T("确定删除用户:")+name);
        if (isdel == true) {
            $http.post("user/deleteUser.action",{"id":id}).success(function(data){
                alert(data.message)
                $scope.getAllData();
		      });
        }
	}
	
    $scope.seletedId = -1;
    $scope.getRoleList=function(){
        $http.get("role/roleList.action").success(function(data){
            var dat={id:-1,rolename:T.T("全部")};
            data.unshift(dat);
            //console.log(data);
			$scope.panel_1.roleList=data;
		});
    }
    
    $scope.requestData=function(){
    	$http.post("user/userList.action", {"roleId":$scope.seletedId}).success(function(data){
            $scope.panel_1.dataList_1.data = data;
        })	
    }
	
	
	$scope.getAllData = function () {
        $http.post("user/userList.action", {"id":-1}).success(function(data){
            $scope.panel_1.dataList_1.data = data;
        })	
	};
    
    $scope.getRoleList();
	$scope.getAllData();
}