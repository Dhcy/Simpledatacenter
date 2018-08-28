function platformModifController($scope,$http,$stateParams,$state,T){
	$scope.userId=$stateParams.userId;
	$scope.title=$scope.userId?T.T("修改"):T.T("添加");
	$scope.userData={
		id:$scope.userId?$scope.userId:-1,
		ctime:"",
		id:"",
		platname:""
	}
	
	$scope.resetData=function(){
		$scope.userData={};
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}	
		$scope.userId?0:$scope.userData.iswork=1;
		//$scope.resetChoose();
	}
	//提交
	$scope.postData=function(){		
		$http.post("platproject/addPlat.action",$scope.userData).success(function(data){
			if(data.status == 0) {
				alert(data.message);
			} else {
				$scope.backGo();
			}
		}).error(function(data){
			alert(data.message);
		});
	}
	var saveData={};
	$scope.getUserData=function(){
		var getData={
			id:"0",
			name:T.T("呵呵"),
			url:"/totalstat/daystat*",
			str:"totaldaystat:check"
		}
		for(var key in getData){
			saveData[key]=getData[key];
		}
		$scope.userData=getData;
	}
	$scope.userId?$scope.getUserData():0;
	$('#createDate').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",viewSelect:'4',maxView:[4, 'decade']})
	
	
	$scope.backGo=function(){
		$state.go('main.config.platform')
	}
	
}