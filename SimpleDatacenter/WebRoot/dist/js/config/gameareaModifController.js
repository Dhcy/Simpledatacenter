function gameareaModifController($scope, $http, $stateParams, $state,T){
	$scope.userId=$stateParams.userId;
	$scope.title=$scope.userId?T.T("修改"):T.T("添加");
	$scope.userData={
		gameid:"",
		name:"",
		areaid:"",
		createdate:""
	}
	
	//下拉框
	$scope.gameList=[]
	$scope.getGameList=function () {
        $http.post("game/gameList.action",{"projectid":-1}).success(function(data){
        	//默认选择第一项
        	$scope.userData.gameid=data[0].id
            $scope.gameList=data;  
		})
		
	};
	
	
	$scope.resetData=function(){
		$scope.userData={};
	}
    
    
	$scope.postData=function(){		
		$http.post("gamearea/addGameArea.action",$scope.userData).success(function(data){
			if(data.status == 0) {
				alert(data.message);
			} else {
				$scope.backGo();
			}
		}).error(function(data){
			alert(data.message);
		});
	}
    
    $scope.backGo=function(){
		$state.go('main.config.gamearea');
	}
    
    $scope.getGameList();
    
    $scope.userId?$scope.getUserData():0;
	$('#createDate').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",viewSelect:'4',maxView:[4, 'decade']})
}