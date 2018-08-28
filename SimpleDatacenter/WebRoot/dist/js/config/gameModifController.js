function gameModifController($scope,$http,$stateParams,$state,T){
	$scope.gameId=$stateParams.id;
//	$scope.userId=$stateParams.userId;
//	$scope.title=$scope.userId?"修改":"添加";
	$scope.title=$scope.gameId?T.T("修改"):T.T("添加");
	$scope.userData={
        projectid:"",
        createdate:"",
		id:$scope.gameId?$scope.gameId:"",
		name:"",
		billrate:"",
		gameType : 2,
		region:1,
		areatype:1,
		hasad:0,
		onlinedate:"",
		offlinedate:""	
	};
	$scope.gameTypeList = [{id:2,name:T.T("联网版")},{id:1,name:T.T("单机版")}];
	$scope.gameRegionList = [{id:1,name:T.T("国内")},{id:2,name:T.T("国外")}];
	$scope.gameAreatypeList = [{id:1,name:T.T("ios专服")},{id:2,name:T.T("安卓专服")},{id:3,name:T.T("混服")}];
	$scope.hadVadList = [{id:1,name:T.T("有")},{id:0,name:T.T("无")}];
	
	$scope.projectList=[]
	$scope.getProjectList=function () {
        $http.post("project/projectList.action",{}).success(function(data){
        	//默认选择第一项
        	$scope.userData.projectid=data[0].id
            $scope.projectList=data;  
		})
		
	};
	
	$scope.resetData=function(){
		$scope.userData={};
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}	
	}
    
    
	$scope.postData=function(){	
		if($scope.gameId){
			//修改
			$http.post("game/updateGame.action",$scope.userData).success(function(data){
				if(data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data){
				alert(data.message);
			});
		}else{
			//添加
			$http.post("game/addGame.action",$scope.userData).success(function(data){
				if(data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data){
				alert(data.message);
			});
		}
		
	}
	var saveData={};
	//游戏信息
	$scope.getGameData=function(){
		$scope.getProjectList();
		 $http.post("game/getGameInfo.action",{"id":$scope.gameId}).success(function(data){
			 $scope.userData=data;
			 $scope.userData.projectid=data.projectid;
			 for(var key in $scope.userData){
	            	saveData[key]=$scope.userData[key];
	            }	
			});
		 console.log("----");
	}
    
    $scope.backGo=function(){
		$state.go('main.config.game');
	}
    
//    $scope.getProjectList();
    $scope.gameId?$scope.getGameData():$scope.getProjectList();
	$('#createDate').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",viewSelect:'4',maxView:[4, 'decade']});
	$('#onlineDate').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",viewSelect:'4',maxView:[4, 'decade']});
	$('#offLineDate').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",viewSelect:'4',maxView:[4, 'decade']});
}