/**
 * 
 */
function sdkdataVipSummaryController($scope,$http,T){
	$scope.panel_1 = {
	        'model': 'table',
	        'gameList':[],
	        'channelList':[],
	        'dataList_1': {
	            'data': '',
	            'graphLimit': {
	                'sumLen': 0,
	                'now': -1,
	                'max': 10,
	                'count': 0
	            },
	            'limit': {
	                'sumLen': 0,
	                'now': 0,
	                'max': 10,
	                'count': 0
	            },
	            'dataLimit': '',
	            'orderBy': {
	                's': 'id',
	                't': ''
	            }
	        }
	}
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.showPanel=function(data){
		console.log(data);
		//var index = $(this).index();
		//data.details = [{id:1,statdate:'2016-01-10'},{id:2,statdate:'2016-11-04'}];
		$scope.getDataDetail(data);
		//
		data.show_panel=true;
		data.panel_change=false;
	}
	
	$scope.getDataDetail = function(row){
//		$scope.postData2.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
// 	    $scope.postData2.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		$scope.postData2.startdate = $scope.postData2.enddate = row.statdate;
 	    $scope.postData2.systemid = row.os;
 	   //
 	   $http.post("sdkdata/selectSDKVIPSummaryDetail.action",$scope.postData2).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			/*console.log(row)
			console.log(data)*/
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
	
	$scope.panel_1.dataList_1.data = [];// [{id:1,statdate:'2016-11-04',details:[{id:2,statdate:'2017-16-05'},{id:1,statdate:'2016-16-05'},]}];
	$scope.postData={
			startdate:"",
			enddate:"",
	    }
	$scope.postData2={
			startdate:"",
			enddate:"",
	        systemid : 0 ,
	    }
	$scope.panel_1.gameList = [{id:0,name:T.T("全部")}];
	$scope.getChannelList = function(){ }
	//游戏
	$scope.panel_1.systemList = [
		{id:0,name:T.T("全部")}
		,{id:1,name:"IOS"}
		,{id:2,name:"Android"}
	];
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		console.log($scope.postData)
		$http.post("sdkdata/selectSDKVIPSummary.action",$scope.postData).success(function(data){
			console.log(data)
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
}