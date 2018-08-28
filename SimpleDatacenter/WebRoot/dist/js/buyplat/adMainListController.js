function adMainListController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'table',
        'gameList':[],
        'channelList':[],
        'terminal':0,
        'adType':0,
        'pricings':0,
        'adcost':'',
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
        terminalid : 1 , 
        adTypeid : 1 , 
        pricingsid : 0 , 
        startdate : 0 ,
        enddate : 0,
        termtypes : [],
        checktype1: -1
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
            $scope.getChannelList()
        })
    }
    //渠道
    $scope.getChannelList=function(){
        $http.post("buyplat/selectAdChannelList.action",{}).success(function(data){
            var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.channelList=data;
            $scope.showData();
        })
    }
    //终端
    $scope.terminalList=[
                {id:1,name:"IOS"},
                {id:2,name:T.T("安卓")},
                {id:3,name:"Winphone"},
                {id:4,name:"BlackBerry"},
                {id:5,name:"Kjava"},
                {id:6,name:"Windows(PC)"},
                {id:7,name:T.T("IOS越狱")}
                ];
    //广告类型    
    $scope.adTypeList=[
                {id:1,name:"CPA"},
                {id:2,name:"CPT"},
                {id:3,name:"CPC"},
                {id:4,name:"CPD"},
                {id:5,name:"CPS"}
                ];
    //计价方式
    $scope.pricingsList=[
                {id:1,name:T.T("点击")},
                {id:2,name:T.T("下载")},
                {id:3,name:T.T("注册")},
                {id:4,name:T.T("激活")},
                {id:5,name:T.T("天")}
                ];            
    //搜索
    $scope.searchData=function(){
    	console.log($scope.postData);
        $scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
        $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
        $scope.postData.termtypes = [$scope.postData.terminalid];
        $scope.postData.checktype1 = $scope.postData.channelid;
        $http.post("buyplat/selectAdvertiserList.action",$scope.postData).success(function(data){
            $scope.panel_1.dataList_1.data=data;
        });
    }
    //删除
    $scope.delAdMain=function(id,name){
        var isdel = confirm(T.T("确定删除此条记录?")+name);
        if (isdel == true) {
            $http.post("buyplat/deleteAdvertiserById.action",{"checktype1":id}).success(function(data){
                if(data.status != 1){
                	alert(data.message);
                }else{
                	$scope.searchData();
                }
              });
        }
    }
    //显示
    $scope.showData = function(){
        $scope.searchData();
    }
    
    $scope.getGameList();
    //$scope.getChannelList();

}