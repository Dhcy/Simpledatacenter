function projectModifController($scope,$http,$stateParams,$state,T){
	$scope.userId=$stateParams.userId;
	$scope.title=$scope.userId?T.T("修改"):T.T("添加");
	$scope.userData={
		id:$scope.userId?$scope.userId:-1,
		createdate:"",
		id:"",
		name:"",
		platformtest:"",
		platformids: [2]
	}
	$scope.platformid = 2;
	$scope.platformList = [
           /* {id:1 , platname:"数据中心"},
            {id:2 , platname:"买量平台"},*/
        ];
	$scope.checkPlatForm = function(data){
		
//		for(var i = 0 ;i<$scope.userData.platformids.length;i++){
//			if($scope.userData.platformids[i] == data.id){
//				$scope.userData.platformids.slice(i,1);
//				data.checked=false;
//				return;
//			}
//		}
//		$scope.userData.platformids.push(data.id);
//		data.checked=true;
		
		for(var i=0;i < $scope.userData.platformids.length;i++){
			if($scope.userData.platformids[i] == data.id){
				$scope.userData.platformids.splice(i,1);
			}
		}
		if(!!data.checked){
			data.checked = false;
		}else{
			data.checked = true;
			$scope.userData.platformids.push(data.id);
		}
	}
	$scope.showPlatList=[2,7];
	$scope.loadPlatFormList=function(){		
		$http.post("platproject/selectPlatList.action",$scope.userData).success(function(data){
			$scope.platList=data;
			if($scope.platList.lenght==0){
				$scope.platformList=[];
			}else{
				for(var i in $scope.platList){
					var id=$scope.platList[i].id;
					if($scope.showPlatList.indexOf(id)<0){
						continue;
					}
					$scope.platformList.push($scope.platList[i]);
				}
			}
//			$scope.platformList = data;
			for(var i in $scope.platformList){
				 $scope.platformList[i].checked=false;
				if($scope.platformList[i].id==2)
					$scope.platformList[i].checked=true;
			}
		}).error(function(data){
			alert(data.message);
		});
	}
	$scope.loadPlatFormList();
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
		$http.post("project/addProject.action",$scope.userData).success(function(data){
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
		$state.go('main.config.project')
	}
	
}