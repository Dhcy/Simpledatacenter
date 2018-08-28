$main.controller('diamondController',diamondController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.diamondcount',{//角色查询
    	url:'/diamondcount',
    	templateUrl:'template/idp/diamond.html?T='+Math.random(),
    	controller:'diamondController'
    })
	
})
/**
 *钻石
 * @param $scope
 * @param $http
 * @return
 */
function diamondController($scope,$http,T){
	
	
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
			'typeArr':{"1":T.T("获取"),"2":T.T("消耗")}
	};
	$scope.type=2;//type=1:;type=2：
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.changeDataShow=0;
	$scope.changeData=function(i){
		$scope.type=i;
		$scope.getAllData();
		$scope.changeDataShow=0;
	}
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
	
	$scope.selectDiamondList = function(){        
		$http.post("summary/selectDiamondList.action",$scope.searchData).success(function(data){
			console.log("钻石");
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_1.data=data
            $scope.panel_1_change=true;
		})
		
	}
	
	$scope.selectDiamondDetailList = function(){        
		$http.post("summary/selectDiamondDetailList.action",$scope.searchData).success(function(data){
			console.log("钻石详情");
			console.log(data);
			if(data.detailList == undefined||data.detailList==null){
				data.detailList =[]
			}
            $scope.panel_2.dataList_1.data=data.detailList;
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
			'checktype1':$scope.type
        }
		$scope.selectDiamondList();
		$scope.selectDiamondDetailList();
}
}