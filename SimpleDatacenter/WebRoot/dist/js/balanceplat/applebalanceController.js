function applebalanceController($scope,$http,T){
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
	$scope.yeararr=[];
	$scope.montharr=[1,2,3,4,5,6,7,8,9,10,11,12];
	for(var i=(new Date()).getFullYear();i>=2011;i--){
		$scope.yeararr.push(i);
	}
    $scope.postData={
		startdate:"",
		enddate:"",
        gameid : -1 ,
        year : (new Date()).getFullYear(),
        month : 1,
        channelid : 112,
    }
    
    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    
    //游戏
    $scope.getGameList=function(){
        $http.post("game/getUserGameList.action",{"projectid" : -1}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
           // data.unshift(dat);
            $scope.panel_1.gameList=data;
            $scope.postData.gameid=data[0].id;
            $scope.getChannelList();
        })
    }
    
    $scope.getChannelList = function(){ }
    
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		console.log($scope.postData)
		$scope.postData.checktype1 = $scope.postData.year;
		$scope.postData.checktype2 = $scope.postData.month;
		$http.post("balanceplat/selectAppleAutoBalance.action",$scope.postData).success(function(data){
			console.log(data)
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
	$scope.getGameList();
}