function gameController($scope,$http,$stateParams,T){

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
	
	$scope.delUser=function(id,name){
		var isdel = confirm(T.T("确定删除游戏:")+name);
        if (isdel == true) {
            $http.post("game/deleteGame.action",{"id":id}).success(function(data){
                alert(data.message)
                $scope.getGameList();
		      })
        }
		
	}
	
	$scope.postData = {
			seletedId : -1
	}
	$scope.getAllData = function () {

        //下拉框
		$http.post("project/projectList.action",{}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
            //console.log(data);  
            $scope.panel_1.select_1=data;   
		})
	};

	$scope.searchAllData = function () {
        //console.log($scope.postData.seletedId);
        $http.post("game/gameList.action",{"projectid" : $scope.postData.seletedId}).success(function(data){
		 	//console.log(data);
		 	$scope.panel_1.dataList_1.data=data;
		})
	};
    
    $scope.getGameList = function () {
        $scope.getAllData();
        $http.post("game/projectGameList.action",{"projectid" : -1}).success(function(data){
		 	//console.log(data);
		 	$scope.panel_1.dataList_1.data=data;
		})
    }

	$scope.getGameList();
    
}