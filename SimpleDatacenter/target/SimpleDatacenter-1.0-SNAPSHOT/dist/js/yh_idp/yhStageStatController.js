$main.controller('yhStageStatController',yhStageStatController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.yhStageStat',{//关卡统计
    	url:'/yhStageStat',
    	templateUrl:'template/yh_idp/yhStageStat.html?T='+Math.random(),
    	controller:'yhStageStatController'
    })
	
})
/**
 * 关卡统计
 * @param $scope
 * @param $http
 * @return
 */
function yhStageStatController($scope,$http,T){
	
	$scope.stageTabsId="1";
	$scope.stagetabsarr=[{"id":1,"name":T.T("关卡统计")},{"id":2,"name":T.T("关卡统计排重")}];
	$scope.stageChangetabs=function(id){
		$scope.stageTabsId=id;
		$scope.selectStageList();
	}
	$scope.stageDataTabsId="1";
	$scope.stageDatatabsarr=[{"id":1,"name":T.T("关卡数据")},{"id":2,"name":T.T("关卡数据排重")}];
	$scope.stageDataChangetabs=function(id){
		$scope.stageDataTabsId=id;
		$scope.selectStageDataList();
	}
	$scope.stageStarTabsId="1";
	$scope.stageStartabsarr=[{"id":1,"name":T.T("关卡星数")},{"id":2,"name":T.T("关卡星数排重")}];
	$scope.stageStarChangetabs=function(id){
		$scope.stageStarTabsId=id;
		$scope.selectStageStarNumList();
	}
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
	$scope.panel_7={
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
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
	$scope.panel_4_change=false;
	$scope.panel_5_change=false;
	$scope.panel_6_change=false;
	$scope.panel_7_change=false;
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
	$scope.changetype7=function(i){
		$scope.panel_7.dataList_1.limit.max=i;
		$scope.panel_7.dataList_1.limit.now=0;
		$scope.panel_7.dataList_1.limit.sumLen=($scope.panel_7.dataList_1.data).length;
		$scope.panel_7.dataList_1.limit.count=Math.ceil($scope.panel_7.dataList_1.limit.sumLen/$scope.panel_7.dataList_1.limit.max)-1;
	}
	 //导出关卡
    $scope.exportStage=function(fileName,ele){
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+$scope.mstatdate;
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	if($scope.stageTabsId=="1"){
    		$http({
    			url: 'yhSummary/exportYhStageList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}else{
    		$http({
    			url: 'yhSummary/exportYhStageDisList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}
    };
    //导出关卡数据
    $scope.exportStageData=function(fileName,ele){
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+$scope.mstatdate;
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	if($scope.stageDataTabsId=="1"){
    		$http({
    			url: 'yhSummary/exportYhStageDataList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}else{
    		$http({
    			url: 'yhSummary/exportYhStageDataDisList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}
    };
    $scope.exportStageStar=function(fileName,ele){
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+$scope.mstatdate;
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	if($scope.stageStarTabsId=="1"){
    		$http({
    			url: 'yhSummary/exportYhStageStarNumList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}else{
    		$http({
    			url: 'yhSummary/exportYhStageStarNumDisList.action',
    			method: 'post',
    			data:$scope.postData,
    			responseType: 'arraybuffer'
    		}).success(function (data, status, headers) {
    			$(ele).prop('disabled', false);
    			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
    		}).error(function (data, status) {
    			$(ele).prop('disabled', false);
    			alert(T.T("下载失败"));
    		});
    	}
    };
    
    
	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 86400000 )).format("yyyy-MM-dd"),
			endDate:(new Date( parseInt((new Date()).getTime()) - 86400000 )).format("yyyy-MM-dd"),
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
    		stageId:'',
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
	$scope.selectStageDataList=function(){
		if($scope.stageDataTabsId=="1"){
			$http.post("yhSummary/selectYhStageDataList.action",$scope.postData).success(function(data){
				if(data== undefined||data==null){
					data=[];
				}
				$scope.panel_2.dataList_1.data=data;
				$scope.panel_2_change=true;
			});
		}else{
			$http.post("yhSummary/selectYhStageDataDisList.action",$scope.postData).success(function(data){
				if(data== undefined||data==null){
					data=[];
				}
				$scope.panel_2.dataList_1.data=data;
				$scope.panel_2_change=true;
			});
		}
		
	}
	$scope.selectStageList=function(){
		if($scope.stageTabsId=="1"){
			$http.post("yhSummary/selectYhStageList.action",$scope.postData).success(function(data){
				if(data== undefined||data==null){
					data=[];
				}
				$scope.panel_1.dataList_1.data=data;
				$scope.panel_1_change=true;
			});
    	}else{
    		$http.post("yhSummary/selectYhStageDisList.action",$scope.postData).success(function(data){
    			if(data== undefined||data==null){
    				data=[];
    			}
    			$scope.panel_1.dataList_1.data=data;
    			$scope.panel_1_change=true;
    		});
    	}
		
	}
	$scope.selectStageStarNumList=function(){
		if($scope.stageStarTabsId=="1"){
			$http.post("yhSummary/selectYhStageStarNumList.action",$scope.postData).success(function(data){
				if(data.status!=1){
					data.result=[];
				}
				$scope.panel_3.dataList_1.data=data.result;
				$scope.panel_3_change=true;
			});
		}else{
			$http.post("yhSummary/selectYhStageStarNumDisList.action",$scope.postData).success(function(data){
				if(data==undefined ||data==null){
					data=[];
				}
				$scope.panel_3.dataList_1.data=data;
				$scope.panel_3_change=true;
			});
		}
		
	}
	$scope.selectUnitStageActUserNumList=function(){
		$http.post("yhSummary/selectUnitStageActUserNumList.action",$scope.postData).success(function(data){
			if(data.status!=1){
				data.result=[];
			}
			$scope.panel_4.dataList_1.data=data.result;
			$scope.panel_4_change=true;
		})
	}
	$scope.selectUnitStageCountList=function(){
		$http.post("yhSummary/selectUnitStageCountList.action",$scope.postData).success(function(data){
			if(data.status!=1){
				data.result=[];
			}
			$scope.panel_5.dataList_1.data=data.result;
			$scope.panel_5_change=true;
		})
	}
	$scope.selectActingStageActUserNumList=function(){
		$http.post("yhSummary/selectActingStageActUserNumList.action",$scope.postData).success(function(data){
			if(data.status!=1){
				data.result=[];
			}
			$scope.panel_6.dataList_1.data=data.result;
			$scope.panel_6_change=true;
		})
	}
	$scope.selectActingStageCountList=function(){
		$http.post("yhSummary/selectActingStageCountList.action",$scope.postData).success(function(data){
			if(data.status!=1){
				data.result=[];
			}
			$scope.panel_7.dataList_1.data=data.result;
			$scope.panel_7_change=true;
		})
	}
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
	}
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.selectStageList();
    	$scope.selectStageDataList();
    	$scope.selectStageStarNumList();
    	$scope.selectUnitStageActUserNumList();
    	$scope.selectUnitStageCountList();
    	$scope.selectActingStageActUserNumList();
    	$scope.selectActingStageCountList();
    }
  //加载下拉框
    $scope.getSelect();
    $scope.getAllData();
    
}