$main.controller('dailySummaryController',dailySummaryController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.dailycount',{//日常统计
    	url:'/dailycount',
    	templateUrl:'template/idp/dailySummary.html?T='+Math.random(),
    	controller:'dailySummaryController'
    })
	
})
/**
 *日常统计
 * @param $scope
 * @param $http
 * @return
 */
function dailySummaryController($scope,$http,T){
	
	
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
	
	$scope.panel_4={
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
	$scope.panel_5={
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
	$scope.panel_6={
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
	$scope.panel_3_change=false;
	$scope.panel_4_change=false;
	$scope.panel_5_change=false;
	$scope.panel_6_change=false;
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
	$scope.changetype4=function(i){
		$scope.panel_4.dataList_1.limit.max=i;
		$scope.panel_4.dataList_1.limit.now=0;
		$scope.panel_4.dataList_1.limit.sumLen=($scope.panel_4.dataList_1.data).length;
		$scope.panel_4.dataList_1.limit.count=Math.ceil($scope.panel_4.dataList_1.limit.sumLen/$scope.panel_4.dataList_1.limit.max)-1;
	}
	$scope.changetype5=function(i){
		$scope.panel_5.dataList_1.limit.max=i;
		$scope.panel_5.dataList_1.limit.now=0;
		$scope.panel_5.dataList_1.limit.sumLen=($scope.panel_5.dataList_1.data).length;
		$scope.panel_5.dataList_1.limit.count=Math.ceil($scope.panel_5.dataList_1.limit.sumLen/$scope.panel_5.dataList_1.limit.max)-1;
	}
	$scope.changetype6=function(i){
		$scope.panel_6.dataList_1.limit.max=i;
		$scope.panel_6.dataList_1.limit.now=0;
		$scope.panel_6.dataList_1.limit.sumLen=($scope.panel_6.dataList_1.data).length;
		$scope.panel_6.dataList_1.limit.count=Math.ceil($scope.panel_6.dataList_1.limit.sumLen/$scope.panel_6.dataList_1.limit.max)-1;
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
		$http.post("summary/selectActiveList.action",$scope.searchData).success(function(data){
			console.log("活跃度");
			console.log(data);
			if(data.activeList == undefined||data.activeList==null){
				data.activeList =[]
			}
            $scope.panel_1.dataList_1.data=data.activeList;
            $scope.panel_1_change=true;
		})
		
	}
	$scope.selectFamSummaryList = function(){        
		$http.post("summary/selectFamSummaryList.action",$scope.searchData).success(function(data){
			console.log("秘境");
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_2.dataList_1.data=data
            $scope.panel_2_change=true;
		})
		
	}
	//封魔团
	$scope.selectFengMoTuanList = function(){        
		$http.post("summary/selectSyFengMoTuanList.action",$scope.searchData).success(function(data){
			console.log("封魔团");
			console.log(data);
			if(data.fengMoTuanList == undefined||data.fengMoTuanList==null){
				data.fengMoTuanList =[]
			}
            $scope.panel_3.dataList_1.data=data.fengMoTuanList;
            $scope.panel_3_change=true;
		})
		
	}
	
	//爵位战
	$scope.selectSyJueWeiList = function(){        
		$http.post("summary/selectSyJueWeiList.action",$scope.searchData).success(function(data){
			console.log("爵位战");
			console.log(data);
			if(data.jueWeiList == undefined||data.jueWeiList==null){
				data.jueWeiList =[]
			}
            $scope.panel_4.dataList_1.data=data.jueWeiList;
            $scope.panel_4_change=true;
		})
		
	}
	
	//猎魔记事
	$scope.selectSyLieMoList = function(){        
		$http.post("summary/selectSyLieMoList.action",$scope.searchData).success(function(data){
			console.log("猎魔记事");
			console.log(data);
			if(data.lieMoList == undefined||data.lieMoList==null){
				data.lieMoList =[]
			}
            $scope.panel_5.dataList_1.data=data.lieMoList;
            $scope.panel_5_change=true;
		})
		
	}
	
	//魔神降临
	$scope.selectSyMoShenList = function(){        
		$http.post("summary/selectSyMoShenList.action",$scope.searchData).success(function(data){
			console.log("魔神降临");
			console.log(data);
			if(data.moShenList == undefined||data.moShenList==null){
				data.moShenList =[]
			}
            $scope.panel_6.dataList_1.data=data.moShenList;
            $scope.panel_6_change=true;
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
		$scope.selectFamSummaryList();
		$scope.selectFengMoTuanList();
		$scope.selectSyJueWeiList();
		$scope.selectSyLieMoList();
		$scope.selectSyMoShenList();
}
}