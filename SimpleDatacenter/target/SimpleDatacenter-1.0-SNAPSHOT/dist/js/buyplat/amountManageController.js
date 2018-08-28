function amountManageController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'table',
        'gameList':[],
        'channelList':[],
        'terminal':0,
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
        cooperationid : 1 , 
        startTime : 0 ,
        endTime : 0,
        termtypes : []
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
            $scope.getChannelList();
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
    //合作方式列表
    $scope.cooperationList=[{id:1,name:"CPA"},
                            {id:2,name:"CPT"},
                            {id:3,name:"CPC"},
                            {id:4,name:"CPD"}
                            ];    

    //搜索
    $scope.searchData=function(){
        $scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
        $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
        $scope.postData.termtypes = [$scope.postData.terminalid];
        $scope.postData.checktype1 = $scope.postData.channelid;
        $http.post("buyplat/selectMlchnlBuckleList.action",$scope.postData).success(function(data){
            $scope.panel_1.dataList_1.data=data;
        });
    }
    //删除
    $scope.delAmount=function(id, name){
        var isdel = confirm(T.T("确定删除此条记录:")+name);
        if (isdel == true) {
            $http.post("buyplat/deleteMlchnlBuckleById.action",{"checktype1":id}).success(function(data){
            	if(data.status != 1){
            		alert(data.message);
            	}
                $scope.searchData();
              });
        }
    }
    //启用/停用
    $scope.states=function(id, stat){
        $http.post("",{}).success(function(data){
            // $scope.stat=true;
        })
    }
    //显示
    $scope.showData = function(){
        $scope.searchData();
    }

    $scope.getGameList();
    //$scope.getChannelList();
    //$scope.searchData();
}