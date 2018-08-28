function advertiserDataController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'table',
        'channelList': [],
        'gameList': [],
        'subChannelList': [],
        'adType': 1,
        'channelName':'',
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
        channelid : 0,
        typeid : 1 , 
        startTime : 0 ,
        endTime : 0,
        subChannelid : -1
    }
    $scope.showTypeid = 0;
    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    
    //游戏
    $scope.getGameList = function () {
        $http.post("game/getUserGameList.action",{"projectid" : -1}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.gameList=data;
            $scope.getChannelList();
        })
    }
    
    //渠道
    $scope.getChannelList = function () {
        $http.post("buyplat/selectMyAdChannelList.action",{}).success(function(data){
            /*var dat={id:-1,channelName:"全部"};   
            data.unshift(dat);*/
            $scope.panel_1.channelList=data;
            if(data != undefined || data != null){
                $scope.postData.channelid=data[0].id/**/
                $scope.getChannelName();    
                $scope.getSubChannelList();
            }
            //console.log(data,$scope.postData.channelid);
            $scope.getSubChannelList();
        })
    }
    //获取渠道名字
    $scope.getChannelName=function(){
        for(var i=0; i< $scope.panel_1.channelList.length ;i++){
            if($scope.postData.channelid == $scope.panel_1.channelList[i].id){
                $scope.panel_1.channelName=$scope.panel_1.channelList[i].channelName;
            }
        }
    }/**/
    //子渠道
    $scope.getSubChannelList= function(){
        $http.post("buyplat/selectSubChannelList.action",$scope.postData).success(function(data){
            var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.subChannelList=data;
            //console.log(data);
            $scope.showData();
        })
    }
    //广告类型
    $scope.typeList=[
                     {id:1,name:"CPA"},
                     {id:2,name:"CPT"},
                     {id:3,name:"CPC"},
                     {id:4,name:"CPD"},
                     {id:5,name:"CPS"}
                ];
    //搜索
    $scope.searchData=function(){
    	$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
    	//$scope.postData.termtypes = [$scope.postData.terminalid];
        $scope.postData.checktype1 = $scope.postData.channelid;
        $scope.postData.checktype3 = $scope.postData.subChannelid;
    	$scope.postData.checktype4 = $scope.postData.gameid;
    	$scope.postData.checktype2 = $scope.postData.typeid;
    	switch(Number($scope.postData.typeid)){
    	case 0:
    		$http.post("buyplat/selectMyAdvCPAList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
    		break;
    	case 1:
    		$http.post("buyplat/selectMyAdvCPTList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
    		break;
    	case 2:
    		$http.post("buyplat/selectMyAdvCPCList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
    		break;
    	case 3:
    		$http.post("buyplat/selectMyAdvCPDList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
    		break;
    	}
    }
    //显示
    $scope.showData = function(){
        $scope.searchData();
    }
    
    $scope.getGameList();
    //$scope.getChannelList();
   // $scope.getSubChannelList();
}
