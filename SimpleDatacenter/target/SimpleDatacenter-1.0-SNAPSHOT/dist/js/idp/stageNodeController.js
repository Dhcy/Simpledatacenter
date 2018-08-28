$main.controller('stageNodeController',stageNodeController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.stageNode',{
    	url:'/stageNode',
    	templateUrl:'template/idp/stageNode.html?T='+Math.random(),
    	controller:'stageNodeController'
    })
	
})
/**
 * 关卡节点
 * @param $scope
 * @param $http
 * @return
 */
function stageNodeController($scope,$http,T){
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
	$scope.stageList=[];
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	//刷新获取不到日期处理
	$scope.$watch("mchange.date",function(nv,ov){
		if($scope.mchange.date){
			$scope.getselectParam();
			$scope.mchange.date=false;
		}
	});
	$scope.selectStageList = function(){        
		$http.post("summary/selectTimeGameStageList.action",$scope.searchData).success(function(data){
			var dat=[{stageId:-1,stageName:T.T("全部")}]
			if(data == undefined||data==null){
				data =[]
			}
			$scope.stageList=dat.concat(data);
		})
		
	}
	
	$scope.selectStageNodeSuccessList = function(){        
		$http.post("summary/selectStageNodeSuccessList.action",$scope.searchData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_1.data=data
            $scope.panel_1_change=true;
		})
		
	}

	//初始化查询参数
	$scope.getselectParam=function(){
		//刷新获取不到日期处理
		if(!$scope.mstatdate){
			$scope.mstatdate=$scope.set7day();
		}
		//查询条件
		$scope.searchData={
	            'gameid':$scope.$Params.gameid,
	            'startdate':$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate':$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'stageId':-1
	        }
		$scope.selectStageList();
	}
    //查询
	$scope.getAllData=function(i){
		$scope.searchData.gameid=$scope.$Params.gameid,
		$scope.searchData.startdate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
		$scope.searchData.enddate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate
		$scope.selectStageNodeSuccessList();
	}
}