$main.controller('ftwJTBossController',ftwJTBossController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.ftwJtBoss',{//军团boss
    	url:'/ftwJtBoss',
    	templateUrl:'template/fw_idp/ftwJTBoss.html?T='+Math.random(),
    	controller:'ftwJTBossController'
    })
	
})
/**
 * 军团boss
 * @param $scope
 * @param $http
 * @return
 */
function ftwJTBossController($scope,$http,T){
	
	
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
	
	$scope.showPanel=function(data){
		console.log(data);
		$scope.getDataDetail(data);
		//
		data.show_panel=true;
		data.panel_change=false;
	}
	
	$scope.getDataDetail = function(row){
		//$scope.postData2.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    //$scope.postData2.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		$scope.postData.startdate = $scope.postData.enddate = row.statdate;
 	    $scope.postData.jtId = row.jtId;
 	   //
 	   $http.post("fwSummary/selectKillBossDetailList.action",$scope.postData).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			row.limit={
	                'sumLen': 0,
	                'now': 0,
	                'max': 10,
	                'count': 0
	            };
			row.dataLimt="";
			row.orderBy={
                's': 'id',
                't': ''
            };
			row.details = data;
			row.show_panel=true;
			row.panel_change=false;
 	   });
		
	}
	
	$scope.gameAreaList=[];
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
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
    		gameid:''
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
	
	$scope.selectKillBossJTList=function(){
		$http.post("fwSummary/selectKillBossJTInfoList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			$scope.panel_1.dataList_1.data=data;
			$scope.panel_1_change=true;
		})
	}
	
	$scope.selectPerDayJtKillInfoList=function(){
		$http.post("fwSummary/selectPerDayJtKillInfoList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change=true;
		})
	}
	
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
	}
	//加载下拉框
    $scope.getSelect();
    $scope.getData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.selectKillBossJTList();
    	$scope.selectPerDayJtKillInfoList();
    }
    
}