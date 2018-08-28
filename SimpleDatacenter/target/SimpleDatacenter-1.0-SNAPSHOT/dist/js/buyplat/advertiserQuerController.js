function advertiserQuerController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'table',
        'channelList': [],
        'gameList': [],
        'subChannelList': [],
        'adType': 1,
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
        $http.post("buyplat/selectMyAdChannelList.action",$scope.postData).success(function(data){
            var dat={channelid:-1,channelName:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.channelList=data;
            console.log(data);
            $scope.getSubChannelList();
        })
    }
    //子渠道
    $scope.getSubChannelList= function(){
        $http.post("buyplat/selectSubChannelList.action",$scope.postData).success(function(data){
            var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.subChannelList=data;
            //console.log(data);
           // $scope.showData();
        })/**/
    }
    
    //渠道与子渠道
    /*$scope.getChannelData=function(){
        for(var i=0;i< $scope.panel_1.channelList.length;i++){
            if($scope.panel_1.channelList[i].channelid ==$scope.panel_1.subChannelList[i].id){
                $scope.postData.subChannelid = $scope.panel_1.subChannelList[i].id;
            }
        }
    }*/
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
        
       // $scope.postData.checktype1 = $scope.postData.channelid;
       // $scope.postData.checktype2 = $scope.postData.typeid;
        /*$scope.postData.checktype3 = $scope.postData.gameid;
        $scope.postData.checktype3 = $scope.postData.subChannelid;*/
        switch(Number($scope.postData.typeid)){
        case 0:
            $http.post("buyplat/selectAdvOfCPAList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
            break;
        case 1:
            $http.post("buyplat/selectAdvOfCPTList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
            break;
        case 2:
            $http.post("buyplat/selectAdvOfCPCList.action",$scope.postData).success(function(data){
                $scope.showTypeid = $scope.postData.typeid ; 
                $scope.panel_1.dataList_1.data=data;
            });
            break;
        case 3:
            $http.post("buyplat/selectAdvOfCPDList.action",$scope.postData).success(function(data){
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
    //$scope.getSubChannelList();
}