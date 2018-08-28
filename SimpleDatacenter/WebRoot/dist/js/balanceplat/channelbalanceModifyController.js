function channelbalanceModifyController($scope,$http,$stateParams,$state,T){
    $scope.ctype = $stateParams.ctype;
    $scope.cm_id = $stateParams.id;
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    console.log($scope.cm_id)
    $scope.channelList=[];
    $scope.gameList = [];
    $scope.attrCode="signCompany";
    
    $scope.cooperationList= [
		{id:0 , name:"CPS"},
		{id:1 , name:T.T("联运")},
	];
	$scope.ispayratesList= [
		{id:0 , name:T.T("否")},
		{id:1 , name:T.T("是")},
	];
	$scope.isladderList= [
		{id:0 , name:T.T("否")},
		{id:1 , name:T.T("是")},
	];
	$scope.billingTypeList= [
		{id:0 , name:T.T("我方")},
		{id:1 , name:T.T("对方")},
		{id:-1 , name:T.T("不结算")},
		{id:2 , name:T.T("汉正结算")},
	];
	$scope.currencyList= [     //货币列表
  		{id:1 , name:T.T("人民币")},
		{id:2 , name:T.T("美元")},
		{id:3 , name:T.T("台币")},
		{id:4 , name:T.T("日元")},
		{id:5 , name:T.T("越南盾")},
		{id:6 , name:T.T("印尼盾")},
		{id:7 , name:T.T("韩元")},
		{id:8 , name:T.T("港币")},
		{id:9 , name:T.T("昆仑币")},
		{id:10 , name:T.T("马来币")},
		{id:11 , name:T.T("新加坡币")},
	];
	$scope.isvalidList = [{id:1,name:T.T("有效")},{id:0,name:T.T("无效")}];
	$scope.contract_statusList = [{id:1,name:T.T("正常")},{id:0,name:T.T("终止合作")}];
    $scope.channelsData={
        id: "",
        channel : "",
        channelid: "",
        channelName: "",
        startTime : 0 ,
        contractstartdate : "",
        endTime : 0 ,
        constractenddate : "",
		cooperation : 0,
		coopmodel : 0,
		currency : 1, //结算货币
		payrates : "",
		payrate : "",
		finalpayrate : "",
		ispayrate : 1,
		taxrate : "",
		exchangerate : "",
		sharingratio : "",
		sharingrate : "",
		isladder : 0,
		gradient : 0,
		billingType : 0,
		settlementtype : 0,
		gameid : -1,
		isvalid : 1,
		contract_status : 1,
		msg : "",
		signCompany:""
    }
	 $scope.dateOptions = {
		//dateDisabled: disabled,
		formatYear: 'yy',
		startingDay: 1
	  };
	$scope.popup = {
		opened1: false,
		opened2: false,
	 };
	
	
    
    //渠道
    $scope.getChannelList=function(){
    	$http.post("channel/getSelectChannels.action",{"gameid":$scope.channelsData.gameid}).success(function(data){
        	$scope.channelList = data;
            if(data.length>0){
                var isnone = false;
                for(var i =0;i<$scope.channelList.length;i++){
                    if($scope.channelList[i].id == $scope.channelsData.channel){
                        isnone = true;
                        break;
                    }
                }
                if(!isnone){
                    $scope.channelsData.channel = data[0].id;
                }
            }
    	});
    }
    
    //修改
    $scope.postData=function(){
    	if($scope.channelsData.contractstartdate==undefined||$scope.channelsData.contractstartdate==''){
        	return;
        }
        if($scope.channelsData.constractenddate==undefined||$scope.channelsData.constractenddate==''){
        	return;
        }
        $scope.channelsData.contractstartdate = $scope.channelsData.contractstartdate.format("yyyy-MM-dd");
        $scope.channelsData.constractenddate = $scope.channelsData.constractenddate.format("yyyy-MM-dd");
        if ($scope.ctype == 1) {
            $http.post("balanceplat/addChannelAccounting.action", $scope.channelsData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        }else if ($scope.ctype == 2) {
            $http.post("balanceplat/updateChannelAccounting.action", $scope.channelsData).success(
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
    $scope.backGo=function(){
        $state.go('main.balanceplat.channelbalance');
    }
    
    //游戏
    $scope.getGameList=function(){
        $http.post("game/getUserGameList.action",{"projectid" : -1}).success(function(data){
            $scope.gameList=data;
        })
    }
    
    $scope.getGameList();
    //添加
    $scope.selectChannelAccounting = function(){
        $http.post("balanceplat/selectChannelAccounting.action",{
            "id" : $scope.cm_id 
            }).success(function(data){
            	console.log(data)
                $scope.channelsData = data;
                $scope.channelsData.contractstartdate = new Date(data.contractstartdate);
                $scope.channelsData.constractenddate = new Date(data.constractenddate);
                $scope.channelsData.coopmodel = parseInt($scope.channelsData.coopmodel);
                $scope.channelsData.settlementtype = parseInt($scope.channelsData.settlementtype);
                $scope.channelsData.signCompany=data.signCompany;
                $scope.getChannelList();
        })
    }
    $scope.ctype == 1 ? "" : $scope.selectChannelAccounting();
}