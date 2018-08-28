function adChannelsController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'table',
        'channel':0,
        'channelList':'',
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
        channelid:0
    }

    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    //渠道
    $scope.getChannelList=function(){
//        $http.post("channel/channelList.action",{}).success(function(data){
//            var dat={id:-1,channelName:"全部"};   
//            data.unshift(dat);
//            $scope.panel_1.channelList=data;
//        })
    	$http.post("buyplat/selectAdChannelList.action",{}).success(function(data){
    	      var dat={id:-1,channelName:T.T("全部")};   
    	      data.unshift(dat);
    	      $scope.panel_1.channelList=data;
    	  })
    }
    
    //删除
    $scope.delChannels=function(id,channelid){
        var isdel = confirm(T.T("确定删除此条记录?"));
        if (isdel == true) {
            $http.post("buyplat/deleteAdChannelByIDAndChanneldID.action",{"checktype1":id,"checktype2":channelid}).success(function(data){
                if(data.status != 1){
                	alert(data.message);
                }
                $scope.searchData();
              });
        }
    }
    
    //搜索
    $scope.searchData=function(){
        console.log($scope.postData)
        $http.post("buyplat/selectAdChannelList.action",$scope.postData).success(function(data){
            $scope.panel_1.dataList_1.data=data;
        });
    }
    $scope.searchData();
    $scope.getChannelList();
}