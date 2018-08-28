$main.controller('mwActivateController',mwActivateController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.mwcount',{//魔物统计
    	url:'/mwcount',
    	templateUrl:'template/idp/mwActivate.html?T='+Math.random(),
    	controller:'mwActivateController'
    })
	
})
/**
 *魔物统计
 * @param $scope
 * @param $http
 * @return
 */
function mwActivateController($scope,$http,T){
	
	
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
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData();
				$scope.mchange.date=false;
			}
		});
	
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.getAllData();
				$scope.mchange.channelQf=false;
			}
		});
	
	$scope.selectResourceSummaryList = function(){        
		$http.post("summary/selectMwActDistributeList.action",$scope.searchData).success(function(data){
			console.log("魔物激活");
			console.log(data);
			if(data.mwActList == undefined||data.mwActList==null){
				data.mwActList =[]
			}
            $scope.panel_1.dataList_1.data=data.mwActList;
            $scope.panel_1_change=true;
		})
		
	}
	
	
    
    //查询条件
    $scope.searchData={};
    
	$scope.getAllData=function(i){
		$scope.searchData={
            'gameid': $scope.$Params.gameid,
            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
            'areas':$scope.mareaclothing,
            'channelList':$scope.mchannel,
            'versionList':$scope.mversion,
			'languageList':$scope.mlang,
        }
		$scope.selectResourceSummaryList();
}
}