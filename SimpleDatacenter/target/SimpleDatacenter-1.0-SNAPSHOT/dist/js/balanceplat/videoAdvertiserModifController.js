function videoAdvertiserModifController($scope,$http,$stateParams,$state,T){
	$scope.advertiserId=$stateParams.id;
	$scope.title=$scope.advertiserId?T.T("修改"):T.T("添加");
	$scope.attrCode="signCompany";
	$scope.advertiserData={
			name:"",
			signCompany:"",
			id:$scope.advertiserId?$scope.advertiserId:"",
	};
	
	
	$scope.resetData=function(){
		$scope.advertiserData={};
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}	
	}
    
    
	$scope.postData=function(){	
		if($scope.advertiserId){
			//修改
			$http.post("uscVideoAdvertiser/updateUscVideoAdvertiser.action",$scope.advertiserData).success(function(data){
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
			$http.post("uscVideoAdvertiser/addUscVideoAdvertiser.action",$scope.advertiserData).success(function(data){
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
	$scope.getAdvertiserData=function(){
		 $http.post("uscVideoAdvertiser/getVideoAdvertiser.action",{"id":$scope.advertiserId}).success(function(data){
			 $scope.advertiserData=data;
			 $scope.advertiserData.signCompany=data.signCompany;
			 for(var key in $scope.advertiserData){
	            	saveData[key]=$scope.advertiserData[key];
	            }	
			});
		 console.log("----");
	}
    
    $scope.backGo=function(){
		$state.go('main.balanceplat.videoAdvertiserConfig');
	}
    
    $scope.advertiserId?$scope.getAdvertiserData():"";
}