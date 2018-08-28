$main.controller('yhPlaymineDataController',yhPlaymineDataController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.yhPlaymineData',{
    	url:'/yhPlaymineData',
    	templateUrl:'template/yh_idp/yhPlaymineData.html?T='+Math.random(),
    	controller:'yhPlaymineDataController'
    })
	
})
/**
 * 采矿数据监控
 * @param $scope
 * @param $http
 * @return
 */
function yhPlaymineDataController($scope,$http,T){
	
	
	$scope.panel_1={
			'model':'table',
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0				
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}	
			},
	};
	$scope.gameAreaList=[];
	$scope.panel_1_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	//日期
	$scope.o={
			startDate:(new Date()).format("yyyy-MM-dd"),
			endDate:(new Date()).format("yyyy-MM-dd"),
			opened1:false,
			opened2:false
		}
	 //
    $scope.searchData={
    		gameid:''
    		
    };
	//查询条件
    $scope.postData={
    		areaid:-1,
    		startdate:'',
    		enddate:'',
    		gameid:'',
    		minLevel:'',
    		maxLevel:''
    }
	//游戏区服
	$scope.selectGameAreaList = function(){
		$http.post("search/selectGameAreaList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,name:T.T("全部")}];   
			$scope.gameAreaList=dat.concat(data);;
		})
	}
    //
	$scope.selectPlaymineSuccRateList=function(){
		$http.post("yhSummary/selectPlaymineSuccRateList.action",$scope.postData).success(function(data){
			if(data.status==0){
				alert(data.message);
				return;
			}else{
				$scope.panel_1.dataList_1.data=data.playmineList;
				$scope.panel_1_change=true;
			}
		})
	}
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
	}
	//校验是整数
	$scope.checkPositInt=function(number){
		var result=true;
		if(number==undefined||number==''){
			return result;
		}else{
			if(!(/(^[1-9]\d*$)/.test(number))){
				result=false;
			}
			return result;
		}
	}
	//导出采矿成功率
	$scope.exportPlaymineSucceRate=function(fileName,ele){
		if(!$scope.checkPositInt($scope.postData.minLevel)){
    		alert(T.T("最小等级:请输入正整数"));
    		return;
    	}
    	if(!$scope.checkPositInt($scope.postData.maxLevel)){
    		alert(T.T("最大等级:请输入正整数"));
    		return;
    	}
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+(new Date($scope.o.startDate)).format("yyyy-MM-dd")+"~"+(new Date($scope.o.endDate)).format("yyyy-MM-dd");
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	$http({
			url: 'yhSummary/exportPlaymineSuccRateList.action',
			method: 'post',
			data:$scope.postData,
			responseType: 'arraybuffer'
		}).success(function (data, status, headers) {
			$(ele).prop('disabled', false);
			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
		}).error(function (data, status) {
			$(ele).prop('disabled', false);
			alert(T.T("下载失败"));
		});
    }
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	if(!$scope.checkPositInt($scope.postData.minLevel)){
    		alert(T.T("最小等级:请输入正整数"));
    		return;
    	}
    	if(!$scope.checkPositInt($scope.postData.maxLevel)){
    		alert(T.T("最大等级:请输入正整数"));
    		return;
    	}
    	$scope.selectPlaymineSuccRateList();
    }
  //加载下拉框
    $scope.getSelect();
    $scope.getAllData();
    
}