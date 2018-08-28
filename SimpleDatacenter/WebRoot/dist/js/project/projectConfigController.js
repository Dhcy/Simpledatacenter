/**
 * 项目配置
 * @param $scope
 * @param $http
 * @return
 */
function projectConfigController($scope,$http,$stateParams,T){
	$scope.projectId = $stateParams.projectid;
	$scope.systemType={'0':'','1':'ios','2':'android','3':'win'}
	//游戏列表
	$scope.panel_1={};
	$scope.panel_1_change=false;
	//游戏的昨天今天数据
	$scope.gameList=[];
	//项目下的游戏列表
	$scope.selectGameDataList=function(){
		$http.post("projectGame/selectGameDataList.action",$scope.searchData).success(function(data){
			if (data.gameList == undefined) {
        		data.gameList=[];
        	}
			$scope.panel_1=data;
			$scope.panel_1_change=true;
		})
	}
	
	$scope.getAllData=function(){
		$scope.searchData={
				'projectid':$scope.projectId
	        }
		$scope.selectGameDataList();
}
	
	$scope.getAllData();
	
}
