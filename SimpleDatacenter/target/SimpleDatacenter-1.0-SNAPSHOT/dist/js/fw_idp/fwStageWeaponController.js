$main.controller('fwStageWeaponController',fwStageWeaponController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.fwstageweapon',{//关卡武器
    	url:'/fwstageweapon',
    	templateUrl:'template/fw_idp/fwStageWeapon.html?T='+Math.random(),
    	controller:'fwStageWeaponController'
    })
	
})
/**
 * 关卡武器统计
 * @param $scope
 * @param $http
 * @return
 */
function fwStageWeaponController($scope,$http,T){
	
	
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
	$scope.channelList=[];
	$scope.gameAreaList=[];
	$scope.wpTypeList=[{id:-1,name:T.T("全部")},{id:1,name:T.T("主武器")},{id:2,name:T.T("副武器")},{id:3,name:T.T("投掷武器")},{id:4,name:T.T("辅助道具")},{id:5,name:T.T("机甲")},{id:6,name:T.T("炮台")},{id:7,name:T.T("飞行器")}]
	$scope.panel_1_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 604800000 )).format("yyyy-MM-dd"),
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
    		channelid:-1,
    		areaid:-1,
    		checktype1:-1,
    		startdate:'',
    		enddate:'',
    		gameid:''
    }
	$scope.selectChannelList = function(){
		$http.post("search/selectGameChannelList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,channelSimName:T.T("全部")}];   
			$scope.channelList=dat.concat(data);
		})
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
	
	$scope.selectStageWeaponList=function(){
		$http.post("fwSummary/selectStageWeaponList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change=true;
		})
	}
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectChannelList();
		$scope.selectGameAreaList();
	}
	//加载下拉框
    $scope.getSelect();
    $scope.getData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.selectStageWeaponList();
    }
    
}