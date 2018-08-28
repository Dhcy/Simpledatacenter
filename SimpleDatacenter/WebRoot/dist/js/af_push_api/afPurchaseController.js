function afPurchaseController($scope,$http){
	$scope.postData={
			startdate:"",
			enddate:"",
			appId:"",
			eventName:"",
	    }
	//设置下载的查询参数
	$scope.setSearchParam=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		 $scope.postData.eventName="af_purchase";
	}
}