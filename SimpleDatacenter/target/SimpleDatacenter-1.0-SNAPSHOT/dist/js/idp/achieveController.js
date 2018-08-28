$main.controller('achieveController',achieveController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.achievecount',{//角色查询
    	url:'/achievecount',
    	templateUrl:'template/idp/achieve.html?T='+Math.random(),
    	controller:'achieveController'
    })
	
})
/**
 * 成就
 * @param $scope
 * @param $http
 * @return
 */
function achieveController($scope,$http,T){
	
	
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
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
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
	
	$scope.selectAchieveFinishNumList = function(){        
		$http.post("summary/selectAchieveFinishNumList.action",$scope.searchData).success(function(data){
			console.log("成就人数");
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_1.data=data
            $scope.panel_1_change=true;
		})
		
	}
	
	$scope.selectAchieveFinishTotalNumList = function(){        
		$http.post("summary/selectAchieveFinishTotalNumList.action",$scope.searchData).success(function(data){
			console.log("完成成就总数");
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_2.dataList_1.data=data
            $scope.panel_2_change=true;
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
		$scope.selectAchieveFinishNumList();
		$scope.selectAchieveFinishTotalNumList();
}
}