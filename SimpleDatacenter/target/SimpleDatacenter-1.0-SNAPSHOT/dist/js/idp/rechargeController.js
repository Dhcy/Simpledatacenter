$main.controller('rechargeController',rechargeController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.rechargequery',{//充值查询
    	url:'/rechargequery',
    	templateUrl:'template/idp/rechargeQuery.html?T='+Math.random(),
    	controller:'rechargeController'
    })
	
})
/**
 * 关卡
 * @param $scope
 * @param $http
 * @return
 */
function rechargeController($scope,$http,T){
	
	
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
	
	$scope.panel_1_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	
//	$scope.$watch("mchange.channelQf",function(nv,ov){
//			if($scope.mchange.channelQf){
//				$scope.getAllData();
//				$scope.mchange.channelQf=false;
//			}
//		});
	
	$scope.selectRechargeList = function(){        
		$http.post("summary/selectRechargeQueryList.action",$scope.postData).success(function(data){
			console.log("充值查询");
			console.log(data);
			if(data.rechargeList == undefined||data.rechargeList==null){
				data.rechargeList =[]
			}
            $scope.panel_1.dataList_1.data=data.rechargeList;
            $scope.panel_1_change=true;
		})
		
	}
	
	
    
    //查询条件
	$scope.postData={};
    
    $scope.getData=function(){
		$scope.postData={
            'gameid': $scope.$Params.gameid,
            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
            'areas':$scope.mareaclothing,
            'channelList':$scope.mchannel,
            'versionList':$scope.mversion,
			'languageList':$scope.mlang,
			'checktype1':$scope.actorName
        }
		$scope.selectRechargeList();
}
}