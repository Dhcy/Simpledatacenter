/**
 *游戏用户
 * @param $scope
 * @param $http
 * @return
 */
function xgFinanceGameUserController($scope,$http,T){
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
				'orderBy':{'s':'','t':''},	
				'totalData':''
			},
	};
	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 604800000 )).format("yyyy-MM-dd"),
			endDate:(new Date()).format("yyyy-MM-dd"),
			opened1:false,
			opened2:false
		}
	//查询条件
    $scope.postData={
    		startdate:'',
    		enddate:'',
    		gameName:T.T('全部')
    }
	$scope.gameList=[{name:T.T("全部"),value:T.T("全部")},{name:T.T("世界2"),value:T.T("世界2")},{name:T.T("世界3"),value:T.T("世界3")},{name:T.T("银河掠夺者"),value:T.T("银河掠夺者")}];   ;
	$scope.panel_1_change=false;
	$scope.selectFinanceGameUserNumList=function(){
		$http.post("xgfinace/selectFinanceGameUserNumList.action",$scope.postData).success(function(data){
			if(data.gameUserNumList== undefined||data.gameUserNumList==null){
				data.gameUserNumList=[];
			}
			$scope.panel_1.dataList_1.data=data.gameUserNumList;
			$scope.panel_1.dataList_1.totalData=data.totalData;
			$scope.panel_1_change=true;
		})
	}
    $scope.getData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.selectFinanceGameUserNumList();
    }
    
}