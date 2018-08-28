$main.controller('cityFightController',cityFightController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.cityfight',{//城市争夺
    	url:'/cityfight',
    	templateUrl:'template/idp/cityFight.html?T='+Math.random(),
    	controller:'cityFightController'
    })
	
})
/**
 *城市争夺
 * @param $scope
 * @param $http
 * @return
 */
function cityFightController($scope,$http,T){
	
	
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
	$scope.panel_3={
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
	$scope.panel_3_change=true;
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
	$scope.changetype3=function(i){
		$scope.panel_3.dataList_1.limit.max=i;
		$scope.panel_3.dataList_1.limit.now=0;
		$scope.panel_3.dataList_1.limit.sumLen=($scope.panel_3.dataList_1.data).length;
		$scope.panel_3.dataList_1.limit.count=Math.ceil($scope.panel_3.dataList_1.limit.sumLen/$scope.panel_3.dataList_1.limit.max)-1;
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
		$http.post("summary/selectCityCntDistributeList.action",$scope.searchData).success(function(data){
			console.log("城市争夺次数分布");
			console.log(data);
			if(data.cityList == undefined||data.cityList==null){
				data.cityList =[]
			}
            $scope.panel_1.dataList_1.data=data.cityList;
            $scope.panel_1_change=true;
		})
		
	}
	$scope.selectCityFightDetailList=function(){
		$http.post("summary/selectCityFightDetailList.action",$scope.searchData).success(function(data){
			console.log("城市争夺细分");
			console.log(data);
			if(data==undefined||data==null){
				data=[];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change=true;
		})
	}
	
	$scope.selectCityFightSetList=function(){
		$http.post("summary/selectCityFightSetList.action",$scope.searchData).success(function(data){
			console.log("城市争夺站局数情况");
			console.log(data);
			if(data==undefined||data==null){
				data=[];
			}
			$scope.panel_3.dataList_1.data=data;
			$scope.panel_3_change=true;
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
		$scope.selectCityFightDetailList();
		$scope.selectCityFightSetList();
}
}