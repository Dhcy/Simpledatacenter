$main.controller('yhTaskManageController',yhTaskManageController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.yhTaskManage',{//任务管理
    	url:'/yhTaskManage',
    	templateUrl:'template/yh_idp/yhTaskManage.html?T='+Math.random(),
    	controller:'yhTaskManageController'
    })
	
})
/**
 * 任务管理
 * @param $scope
 * @param $http
 * @return
 */
function yhTaskManageController($scope,$http,T){
	$scope.tabsId="1";
	$scope.tabsarr=[{"id":1,"name":T.T("教程步数")},{"id":2,"name":T.T("教程步数排重")}];
	$scope.changetabs=function(id){
		$scope.tabsId=id;
		$scope.getAllData();
	}
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
	$scope.panel_2={
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
	$scope.panel_2_change=true;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.changetype2=function(i){
		$scope.panel_2.dataList_1.limit.max=i;
		$scope.panel_2.dataList_1.limit.now=0;
		$scope.panel_2.dataList_1.limit.sumLen=($scope.panel_2.dataList_1.data).length;
		$scope.panel_2.dataList_1.limit.count=Math.ceil($scope.panel_2.dataList_1.limit.sumLen/$scope.panel_2.dataList_1.limit.max)-1;
	}

	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 86400000 )).format("yyyy-MM-dd"),
			endDate:(new Date( parseInt((new Date()).getTime()) - 86400000 )).format("yyyy-MM-dd"),
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
    		checktype1:''
    }
	
	$scope.selectGameAreaList = function(){
		$http.post("search/selectGameAreaList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,name:T.T("全部")}];   
			$scope.gameAreaList=dat.concat(data);;
		})
	}
	$scope.selectCourseStatList=function(){
		$http.post("yhSummary/selectYhCourseStatList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change=true;
		})
	}
	$scope.selectSystemJoinList=function(){
		$http.post("yhSummary/selectYhSystemJoinList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change=true;
		})
	}
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
	}
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.postData.checktype1=$scope.tabsId;
    	$scope.selectCourseStatList();
    	$scope.selectSystemJoinList();
    }
  //加载下拉框
    $scope.getSelect();
    $scope.getAllData();
    
}