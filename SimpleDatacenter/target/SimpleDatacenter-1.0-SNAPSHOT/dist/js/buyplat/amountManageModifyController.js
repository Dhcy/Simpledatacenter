function amountManageModifyController($scope,$http,$stateParams,$state,T){
    $scope.ctype = $stateParams.ctype;
    $scope.am_id = $stateParams.id;
    console.log($stateParams)
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    //游戏列表
    $scope.gameList=[];
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
    //合作方式列表
    $scope.cooperationList=[{id:1,name:"CPA"},
                            {id:2,name:"CPT"},
                            {id:3,name:"CPC"},
                            {id:4,name:"CPD"},
                            {id:5,name:"CPS"}];
    //渠道列表
    $scope.channelList=[];
    //扣量列表
    $scope.amountListData = {
        id : "",
        gameid: 0,
        gameName: "",
        channel: "",
        channelName: "",
        chaSubChannel: "",
        termType: 1,
        termtypeName:"",
        regCount: "",
        buckleRate:"",
        buckleRegCount:"",
        cooperationType: 1,
        startTime:"",
        endTime:"",

        startdate:0 ,
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
        var y=$scope.amountListData.timeStartDate.year;
        if( y%4==0 ){
            arr[2]=29;
        }else{
            arr[2]=28;
        }
        $scope.timeStartData.day=[];
        for(var i=1;i<=arr[$scope.amountListData.timeStartDate.month];i++){
            $scope.timeStartData.day.push(i);
        }
        var y2=$scope.amountListData.timeEndDate.year;
        if( y%4==0 ){
            arr[2]=29;
        }else{
            arr[2]=28;
        }
        $scope.timeEndData.day=[];
        for(var i=1;i<=arr[$scope.amountListData.timeEndDate.month];i++){
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
        // console.log(o.year,o.month,o.day,o.hour,o.minute,o.second);
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
            $scope.gameList=data;
            $scope.amountListData.gameid=data[0].id;
        })
    }
    //渠道
    $scope.getChannelList=function(){
        /*$http.get("channel/channelList.action").success(function(data){
            $scope.channelList=data;
            $scope.amountListData.channelid=data[0].id;
        })*/
    }    
    
    //修改参数
    $scope.postData = function() {
    	console.log($scope.amountListData.startdate);
        $scope.amountListData.startdate = $scope.setDate($scope.amountListData.timeStartDate);
        $scope.amountListData.enddate = $scope.setDate($scope.amountListData.timeEndDate);
        console.log($scope.amountListData.startdate);
        if ($scope.ctype == 1) {
            $http.post("buyplat/addMlchnlBuckle.action", $scope.amountListData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        } else if ($scope.ctype == 2) {
            $http.post("buyplat/updateMlchnlBuckle.action", $scope.amountListData).success(
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
    //路由跳转
    $scope.backGo = function() {
        $state.go('main.buyplat.amountManage');
    }
    //添加
    $scope.getAmountData = function() {
        $http.post("buyplat/selectMlchnlBuckle.action", {
            "id" : $scope.am_id
        }).success(function(data) {
        	console.log(data)
            $scope.amountListData = data;
        	$scope.amountListData.cooperationType= parseInt($scope.amountListData.cooperationType);
            $scope.getDateData(data.startdate,data.enddate);
        })
    }
    //日期数据
    $scope.getDateData=function(o1,o2){
        var date1 = new Date(o1) , date2 = new Date(o2);
        $scope.amountListData.timeStartDate = {};
        $scope.amountListData.timeEndDate = {};
        //
        $scope.amountListData.timeStartDate.year=date1.getFullYear();
        $scope.amountListData.timeStartDate.month=date1.getMonth()+1;
        $scope.amountListData.timeStartDate.day=date1.getDate();
        $scope.amountListData.timeStartDate.hour=date1.getHours();
        $scope.amountListData.timeStartDate.minute=date1.getMinutes();
        $scope.amountListData.timeStartDate.second=date1.getSeconds();

        $scope.amountListData.timeEndDate.year=date2.getFullYear();
        $scope.amountListData.timeEndDate.month=date2.getMonth()+1;
        $scope.amountListData.timeEndDate.day=date2.getDate();
        $scope.amountListData.timeEndDate.hour=date2.getHours();
        $scope.amountListData.timeEndDate.minute=date2.getMinutes();
        $scope.amountListData.timeEndDate.second=date2.getSeconds();
    }

    $scope.ctype == 1 ? "" : $scope.getAmountData();

    $scope.getGameList();
    $scope.getChannelList();
}