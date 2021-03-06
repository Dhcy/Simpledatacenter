$main.controller('propCountController',propCountController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.consumedata.propCount',{//金币统计
    	url:'/propCount',
    	templateUrl:'template/hie_idp/propCount.html?T='+Math.random(),
    	controller:'propCountController'
    })
	
})
function propCountController($scope,$http,T){

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
	$scope.gameAreaList=[];
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
    		areaid:-1,
    		startdate:'',
    		enddate:'',
    		gameid:'',
    		channelid:-1,
    		checktype1:1
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
    
    $scope.styleList=[{id:1,name:"消耗"},{id:2,name:"产出"}];
    
    
	
	$scope.selectGameAreaList = function(){
		$http.post("search/selectGameAreaList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,name:T.T("全部")}];   
			$scope.gameAreaList=dat.concat(data);;
		})
	}
	$scope.queryDiamondsStatList=function(){
		$http.post("hieConsume/queryThingStatList.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			var limit = 10
			var start = 0
			var end = 0
			var _arr = []

			while (end < data.result.length) {
			    end = start + (limit - 1)
			    _arr.push.apply(_arr, data.result.slice(start, end))
			    _arr.push(data.result[data.result.length - 1])
			    start = end
			}
			_arr.pop()
			$scope.panel_1.dataList_1.data=_arr;
			$scope.panel_1_change=true;
		})
	}
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
		$scope.selectChannelList();
	}
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.queryDiamondsStatList();
    	
    	
    }
  //加载下拉框
    $scope.getSelect();
    $scope.getAllData();
    
}