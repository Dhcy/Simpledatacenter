/**
 * 财务费用
 * @param $scope
 * @param $http
 * @return
 */
function xgFinanceCostController($scope,$http,T){
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
	//查询条件
    $scope.postData={
    		gameName:T.T('全部')
    }
	$scope.gameList=[{name:T.T('全部'),value:T.T('全部')},{name:T.T("世界2"),value:T.T("世界2")},{name:"世界3",value:T.T("世界3")},{name:T.T("银河掠夺者"),value:T.T("银河掠夺者")}];   ;
	$scope.panel_1_change=false;
	$scope.selectFinanceCostList=function(){
		$http.post("xgfinace/selectFinanceCostList.action",$scope.postData).success(function(data){
			if(data.costList== undefined||data.costList==null){
				data.costList=[];
			}
			$scope.panel_1.dataList_1.data=data.costList;
			$scope.panel_1.dataList_1.totalData=data.totalData;
			$scope.panel_1_change=true;
		})
	}
    $scope.getData=function(){
    	$scope.selectFinanceCostList();
    }
    
}