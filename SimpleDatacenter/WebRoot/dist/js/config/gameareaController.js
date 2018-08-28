function gameareaController($scope,$http,$stateParams,T){
	//$scope.searchData={name:"",realname:"",role:1};
	$scope.panel_1 = {
		'model': 'table',
		'select_1':'',
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
				's': 'name',
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
	
	$scope.delGamearea=function(id,name,gameid){
		var isdel = confirm(T.T("确定删除区服:")+name);
        if (isdel == true) {
            $http.post("gamearea/deleteGameArea.action",{"areaid":id,"gameid":gameid}).success(function(data){
                alert(data.message)
                $scope.getAllData();
		      })
        }
	}
	
	//查询数据
	$scope.serchAllData = function () {
		$http.post("gamearea/gameareaList.action",{"gameid": $scope.postData.gameid}).success(function(data){
            console.log(data);  
            $scope.panel_1.dataList_1.data=data;   
		})
		
	};
	
	$scope.postData = {
			gameid : -1
	}
	$scope.getGameList = function () {
		//游戏下拉框
		$http.post("game/gameList.action",{"projectid":-1}).success(function(data){
            var dat={id:-1, name:T.T("全部")};
            data.unshift(dat);
            console.log(data);  
            $scope.panel_1.select_1=data;   
		})
	}
	
	$scope.getAllData = function () {
		$scope.getGameList();
		$http.post("gamearea/gameareaList.action",{"gameid": -1}).success(function(data){
            //console.log(data);  
            $scope.panel_1.dataList_1.data=data;   
		})
	}
	
	$scope.getAllData();
}