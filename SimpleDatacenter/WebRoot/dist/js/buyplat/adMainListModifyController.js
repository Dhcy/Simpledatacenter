function adMainListModifyController($scope,$http,$stateParams,$state,T){
	console.log($stateParams)
    $scope.ctype = $stateParams.ctype;
    $scope.mm_id = $stateParams.id;
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    
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
    //广告类型列表    
    $scope.adTypeList=[
                       {id:1,name:"CPA"},
                       {id:2,name:"CPT"},
                       {id:3,name:"CPC"},
                       {id:4,name:"CPD"},
                       {id:5,name:"CPS"}
                       ];
    //计价方式列表
    $scope.pricingsList=[
                         {id:1,name:T.T("点击")},
                         {id:2,name:T.T("下载")},
                         {id:3,name:T.T("注册")},
                         {id:4,name:T.T("激活")},
                         {id:5,name:T.T("天")},
                         {id:6,name:"CPS"}
                         ];     
    $scope.gameList=[];
    $scope.channelList=[];    
    $scope.initMainListData = function(){
    	$scope.mainListData = {
    	        id : "",
    	        gameid: "",
    	        gameName: "",
    	        termType: 1,
    	        termtypeName:'',
    	        adchannel: "",
                channelName: "",
    	        subchannel: "",
    	        adSpaces: "",
    	        adType: 1,
    	        price:"",
    	        pricingType : 1,
                
                addisplaytime: "",
                clickcount: "",
                installcount: "",
                adcost: 0,

                busicount: "",

    	        startdate:0 ,//date '2015-01-01 11:21:12'
    	        enddate:0 ,

    	        timeStartDate:{
    	            year: (new Date()).getFullYear(),
    	            month :1,
    	            day:1,
    	            hour:0,
    	            minute:0,
    	            second:0
    	        },

    	        timeEndDate:{
    	            year: (new Date()).getFullYear(),
    	            month :1,
    	            day:1,
    	            hour:0,
    	            minute:0,
    	            second:0
    	        }

    	    }
    };
    $scope.initMainListData();
    console.log($scope.mainListData);
    //广告费计算
    $scope.changeAdcost=function(){
            $scope.mainListData.adcost = $scope.mainListData.busicount * $scope.mainListData.price;
    }
    //广告渠道名称
	$scope.getAdchannelName = function(){
		for(var i =0 ;i<$scope.channelList.length;i++){
			if( $scope.mainListData.channelName == $scope.channelList[i].channelName){
				$scope.mainListData.adchannel = $scope.channelList[i].channelid;
				return $scope.mainListData.adchannel;
			}
		}
	}
    //时间区域
    $scope.timeStartData={
        year:[],
        month:[],
        day:[],
        hour:[],
        minute:[],
        second:[]
    };
    $scope.timeEndData={
        year:[],
        month:[],
        day:[],
        hour:[],
        minute:[],
        second:[]
    };
    $scope.addYear=function(n){
        for(var i=n+5;i>=(n-5);i--){
            $scope.timeStartData.year.push(i);
            $scope.timeEndData.year.push(i);
        }
    }
    $scope.addMonth=function(n){
        for(var i=1;i<=12;i++){
            $scope.timeStartData.month.push(i);
            $scope.timeEndData.month.push(i);
        }
    }
    $scope.addDay=function(){
        var arr=["",31,"",31,30,31,30,31,31,30,31,30,31];
        var y=$scope.mainListData.timeStartDate.year;
        if( y%4==0 ){
            arr[2]=29;
        }else{
            arr[2]=28;
        }
        /*$scope.timeData.day=[];
        console.log(0000);*/
        $scope.timeStartData.day=[];
        for(var i=1;i<=arr[$scope.mainListData.timeStartDate.month];i++){
            $scope.timeStartData.day.push(i);
        }
        var y2=$scope.mainListData.timeEndDate.year;
        if( y2%4==0 ){
            arr[2]=29;
        }else{
            arr[2]=28;
        }
        /*$scope.timeData.day=[];
        console.log(0000);*/
        $scope.timeEndData.day=[];
        for(var i=1;i<=arr[$scope.mainListData.timeEndDate.month];i++){
            $scope.timeEndData.day.push(i);
        }
    }
    $scope.addHour=function(n){
        for(var i=0;i<24;i++){
            $scope.timeStartData.hour.push(i);
            $scope.timeEndData.hour.push(i);
        }
    }
    $scope.addMinute=function(n){
        for(var i=0;i<60;i++){
            $scope.timeStartData.minute.push(i);
            $scope.timeEndData.minute.push(i);
        }
    }
    $scope.addSecond=function(n){
        for(var i=0;i<60;i++){
            $scope.timeStartData.second.push(i);
            $scope.timeEndData.second.push(i);
        }
    }
    $scope.setDate = function(o){
        console.log(o.year,o.month-1,o.day,o.hour,o.minute,o.second);
        return (new Date(o.year,o.month-1,o.day,o.hour,o.minute,o.second));
    }
    $scope.addYear((new Date()).getFullYear());
    $scope.addMonth();
    $scope.addDay();
    $scope.addHour();
    $scope.addMinute();
    $scope.addSecond();
    //游戏
    $scope.getGameList=function(){
        $http.get("game/getUserGameList.action").success(function(data){
            console.log(data)
        	$scope.gameList=data;
            $scope.mainListData.gameid=data[0].id;
        })
    }
    //渠道
    $scope.getChannelList=function(){
        $http.post("buyplat/selectAdChannelList.action",{}).success(function(data){
            // var dat={id:-1,channelName:"全部"};   
            // data.unshift(dat);
            $scope.channelList=data;         
            $scope.mainListData.channelName = data[0].channelName;
            console.log($scope.channelList);
        })
    }
    $scope.showTypeid = 0;
    //广告类型显示
    $scope.showType=function(){
        $scope.showTypeid = $scope.mainListData.adType;
        // console.log($scope.showTypeid);
    }
    //修改参数
    $scope.postData = function() {
        $scope.mainListData.startdate = $scope.setDate($scope.mainListData.timeStartDate);
        $scope.mainListData.enddate = $scope.setDate($scope.mainListData.timeEndDate);
        console.log($scope.mainListData);
        //$scope.showTypeid = $scope.mainListData.adType;
        if ($scope.ctype == 1) {
            $http.post("buyplat/addAdvertiser.action", $scope.mainListData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        } else if ($scope.ctype == 2) {
            $http.post("buyplat/updateAdvertiser.action", $scope.mainListData).success(
                    function(data) {
                        if (data.status == 0) {
                            alert(data.message);
                        } else {
                            $scope.backGo();
                        }
                    }).error(function(data) {
                alert(data.message);
            });

        }
    }
    //跳转
    $scope.backGo = function() {
        $state.go('main.buyplat.adMainList');
    }
    //添加
    $scope.getMainData = function() {
        $http.post("buyplat/selectAdvertiser.action", {
            "id" : $scope.mm_id
        }).success(function(data) {
            $scope.mainListData = data;
            $scope.getDateData(data.startdate,data.enddate);
            $scope.mainListData.adType = parseInt($scope.mainListData.adType);
            $scope.mainListData.pricingType = parseInt($scope.mainListData.pricingType);
            $scope.showTypeid = $scope.mainListData.adType;
            $scope.getAdchannelName();
        })
    }
    
    //日期数据
    $scope.getDateData=function(o1,o2){
        var date1 = new Date(o1) , date2 = new Date(o2);
        $scope.mainListData.timeStartDate = {};
        $scope.mainListData.timeEndDate = {};
        console.log($scope.mainListData)
//        
        $scope.mainListData.timeStartDate.year=date1.getFullYear();
        $scope.mainListData.timeStartDate.month=date1.getMonth()+1;
        $scope.mainListData.timeStartDate.day=date1.getDate();
        $scope.mainListData.timeStartDate.hour=date1.getHours();
        $scope.mainListData.timeStartDate.minute=date1.getMinutes();
        $scope.mainListData.timeStartDate.second=date1.getSeconds();
        
        $scope.mainListData.timeEndDate.year=date2.getFullYear();
        $scope.mainListData.timeEndDate.month=date2.getMonth()+1;
        $scope.mainListData.timeEndDate.day=date2.getDate();
        $scope.mainListData.timeEndDate.hour=date2.getHours();
        $scope.mainListData.timeEndDate.minute=date2.getMinutes();
        $scope.mainListData.timeEndDate.second=date2.getSeconds();
    }


    $scope.getGameList();
    $scope.getChannelList();
    $scope.ctype == 1 ? "" : $scope.getMainData();
    
}