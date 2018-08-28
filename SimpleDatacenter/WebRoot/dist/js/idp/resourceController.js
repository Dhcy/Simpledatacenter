$main.controller('resourceController',resourceController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.resourcecount',{//资源统计
    	url:'/resourcecount',
    	templateUrl:'template/idp/resource.html?T='+Math.random(),
    	controller:'resourceController'
    })
	
})
/**
 *资源
 * @param $scope
 * @param $http
 * @return
 */
function resourceController($scope,$http,T){
	
	
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
	$scope.search={
			//-1代表全部;其它用名称代替
			chooseType:-1,
			chooseWay:-1
	};
	
	$scope.changeRType=function(){
		$scope.getAllData();
	}
	$scope.changeRWay=function(){
		$scope.getAllData();
	}
	
	
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
		$http.post("summary/selectResourceSummaryList.action",$scope.searchData).success(function(data){
			console.log("资源");
			console.log(data);
			if(data.resourceList == undefined||data.resourceList==null){
				data.resourceList =[]
			}
            $scope.panel_1.dataList_1.data=data.resourceList;
            $scope.panel_1_change=true;
		})
		
	}
	//资源类型跟方式
	$scope.selectResourceTypeAndWayList=function(){
		$scope.resoureTypeArr=[{"statType":-1,"typeName":T.T('全部')}];
		$scope.resoureWayArr=[{"statType":-1,"purposeName":T.T('全部')}];
		$http.post("summary/selectResourceTypeAndWayList.action",$scope.searchData).success(function(data){
			console.log("资源类型"+data.typeList);
			console.log("资源方式"+data.wayList);
			if(data.typeList == undefined||data.typeList==null){
				data.typeList =[]
			}
			if(data.wayList == undefined||data.wayList==null){
				data.wayList =[]
			}
			for(var i=0;i<data.typeList.length;i++){
				$scope.resoureTypeArr[i+1]=data.typeList[i];
			}
			for(var i=0;i<data.wayList.length;i++){
				$scope.resoureWayArr[i+1]=data.wayList[i];
			}
		})
	}
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
				'checktype1':$scope.search.chooseType,
				'checktype2':$scope.search.chooseWay,
	        }
		$scope.selectResourceSummaryList();
		$scope.selectResourceTypeAndWayList();
}
}