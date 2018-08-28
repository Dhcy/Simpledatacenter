function channelbalanceController($scope,$http,T){
	    $scope.panel_1 = {
        'model': 'table',
        'projectList':[],
        'gameList':[],
        'channelList':[],
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
    $scope.attrCode="signCompany";
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
    $scope.getCurrencyName=function(id){
		for(var i=0;i<$scope.currencyList.length;i++){
			if($scope.currencyList[i].id==id)
				return $scope.currencyList[i].name;
		}
		return "";
	}
    $scope.postData={
    	projectid:-1,
        gameid : -1 ,
        channelid : -1,
        signCompany:-1
       
    }
    $scope.getDate=function(){
    	$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
        }
    $scope.saveData=function(data){
        var blob = new Blob(data,{"type":"text/html"});
//        var blob = new Blob(data);

		var a = document.createElement('a');
		a.href = window.URL.createObjectURL(blob);
		a.donwload = 'test.txt';
		a.textContent = 'Download Hello World';
		a.target="_blank";
		a.click();
    }
    
    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    $scope.getProjectList=function(){
    	$http.post("project/projectList.action",{}).success(function(data){
    		var dat={id:-1,name:T.T("全部")};   
           data.unshift(dat);
    		$scope.panel_1.projectList = data;
    	});
    }
    //项目下的游戏列表
    $scope.getGameListByProjectId=function(){
        $http.post("game/gameList.action",{"projectid" : $scope.postData.projectid}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.gameList=[];
            $scope.panel_1.gameList=data;
            $scope.postData.gameid=data[0].id;
//            $scope.getChannelList();
        })
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
    
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
 	    $scope.postData.checktype1 = $scope.postData.signCompany;
		console.log($scope.postData)
		$http.post("balanceplat/selectChannelAccountingList.action",$scope.postData).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
	
	$scope.delChannelbalance=function(data,name){
		console.log(data)
		$http.post("balanceplat/deleteChannelAccounting.action",{"id" : data}).success(function(data){
			if(data.status == 1){
				$scope.getData();
			}
    	});
	}
	$scope.getProjectList();
	$scope.getGameList();
	//$scope.getChannelList();
        
}