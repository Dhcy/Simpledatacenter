function autobalanceController($scope,$http,Upload,T){
	    $scope.panel_1 = {
        'model': 'table',
        'gameList':[],
        'channelList':[],
        'myBillItemCount':'',
        'dsBillItemCount':'',
        'matchItemCount':'',
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
    $scope.postData={
        gameid : -1 ,
        channelid : -1,
        file : "",
		startdate : "",
		enddate:""
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
            data.unshift(dat);
            $scope.panel_1.gameList=data;
            $scope.postData.gameid=data[0].id;
            $scope.getChannelList();
        })
    }
	//渠道
    $scope.getChannelList=function(){
	$http.post("channel/getSelectChannels.action",{"gameid":$scope.postData.gameid}).success(function(data){
    		var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
    		$scope.panel_1.channelList = data;
    	});
	}
    $scope.fileChange = function($file){
        $scope.postData.file = $file;
    }
    
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		console.log($scope.postData)
		if($scope.postData.file){
            Upload.upload({
                url: 'balanceplat/autoCheckBill.action',
                data: $scope.postData
            }).then(function (response) {   //成功
                $scope.panel_1.dataList_1.data=response.data.diffBillList;
                $scope.panel_1.myBillItemCount=response.data.myBillItemCount;
                $scope.panel_1.dsBillItemCount=response.data.dsBillItemCount;
                $scope.panel_1.matchItemCount=response.data.matchItemCount;
            }, function (response) {    //失败
                
            }, function (evt) {
                
            });
        }else{
            
				$scope.dialogopen({
					msg:T.T("请先上传第三方对账账单"),
					btn:{close:true,ok:false}
				})
        }
        
        
	}
	$scope.getGameList();
	$scope.getChannelList();
}