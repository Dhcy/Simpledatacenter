function qdUserRegInfoController($scope,$http,T){
    $scope.panel_1 = {
        'model': 'graph',
        'channelList': [],
        'gameList': [],
        'terminal': 0,
        'infoList': 0,
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
        infoid : 1 , 
        startTime : 0 ,
        endTime : 0
    }
    
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
        $http.post("buyplat/selectAdChannelList.action",{}).success(function(data){
            var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.channelList=data;
            $scope.showData();
        })
    }
    //终端列表
    $scope.terminalList=[
                         {id:1,name:"IOS"},
                         {id:2,name:T.T("安卓")},
                         {id:3,name:"Winphone"},
                         {id:4,name:"BlackBerry"},
                         {id:5,name:"Kjava"},
                         {id:6,name:"Windows(PC)"},
                         {id:7,name:T.T("IOS越狱")}
                         ];
    //渠道用户注册信息
    $scope.infoList=[
                {id:1,name:T.T("注册时间段")},
                {id:2,name:T.T("创角时间段")},
                {id:3,name:T.T("联网方式")},
                {id:4,name:T.T("注册IP")},
                {id:5,name:T.T("城市区域")},
                {id:6,name:T.T("设备类型")},
                {id:7,name:T.T("系统版本号")},
                {id:8,name:T.T("IMEI")},
                {id:9,name:T.T("deviceid")},
                {id:10,name:T.T("Mac地址")}
                ]         
    //搜索
    $scope.searchData=function(){
    	$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
    	$scope.postData.termtypes = [$scope.postData.terminalid];
    	$scope.postData.checktype1 = $scope.postData.channelid;
    	$scope.postData.checktype2 = $scope.postData.infoid;
        $http.post("buyplat/selectBuyPlatActorRegisterInfo.action",$scope.postData).success(function(data){
        	console.log(data)
            $scope.panel_1.dataList_1.data=data;
        	var name = "";
        	for(var i=0;i<$scope.infoList.length;i++){
        		if($scope.infoList[i].id==$scope.postData.infoid){
        			name =$scope.infoList[i].name;
        			break;
        		}
        	}
        	
        	$('#datas').highcharts({
                chart: {
                    type: 'bar'
                },
                title:"",
                xAxis: {
                    categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'subtype')).newArr	
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                legend: {
                    backgroundColor: '#FFFFFF',
                    reversed: true
                },
                plotOptions: {
                    series: {
                    }
                },
                    series: [{
                    name: name,
                    data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'count')).newArr
                }]
            });
        });
    }
    //显示
    $scope.showData = function(){
        $scope.searchData();
    }
    
    $scope.getGameList();
    //$scope.getChannelList();


    //图表
    
}