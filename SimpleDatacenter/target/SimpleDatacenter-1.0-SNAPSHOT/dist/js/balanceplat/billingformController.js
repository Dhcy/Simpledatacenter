function billingformController($scope,$http,T){
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
    $scope.postData={
	    projectid:-1,
        gameid : -1 ,
        channelid : -1,
		startdate : "",
		enddate:"",
        billingType : -2,
        ispayrates : -2,
        isladder : -2,
        region:-1,
        signCompany:-1
    }
	   //设置查询日期
	    $scope.setQueryDate=function(){
	    	$scope.postData.startdate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
			$scope.postData.enddate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
	    }
	//提示导出哪个月份的报表
	   $scope.setDateAndAlertMsg=function(){  	
		   $scope.setQueryDate();
	    	alert(T.T('导出')+$scope.postData.startdate+T.T('的游戏结算报表'));
	    }
	     
    $scope.ispayratesList= [
        {id:-2 , name:T.T("全部")},
		{id:0 , name:T.T("否")},
		{id:1 , name:T.T("是")},
	];
	$scope.isladderList= [
		{id:-2 , name:T.T("全部")},
		{id:0 , name:T.T("否")},
		{id:1 , name:T.T("是")},
	];
	$scope.billingTypeList= [
		{id:-2 , name:T.T("全部")},
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
	 $scope.gameRegionList= [
         {id:-1 , name:T.T("全部")},
 		{id:1 , name:T.T("国内")},
 		{id:2 , name:T.T("国外")}
 	];
    $scope.getCurrencyName=function(id){
		for(var i=0;i<$scope.currencyList.length;i++){
			if($scope.currencyList[i].id==id)
				return $scope.currencyList[i].name;
		}
		return "";
	}
    $scope.updateData={
        id : "",
        payrate : "",
        taxrate : "",
        exchrate : "",
        sharerate : "",
        currency : "",
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
    
    $scope.changeUpdate=function(data){
        
        $scope.updateData={
            id : data.id,
            payrate : data.payrate,
            taxrate : data.taxrate,
            exchrate : data.exchrate,
            sharerate : data.sharerate,
            currency : data.currency, 
            confcurr : data.confcurr,
        }
        data.isUpdate = !data.isUpdate;
    }
    $scope.postUpdateData = function(data2){
        $scope.dialogopen({
            msg:T.T("确认提交修改?"),
            btn:{close:true},
            fn:{
                ok:function(){
                    $http.post("balanceplat/updateBillingAccounting.action",$scope.updateData).success(function(data){
                        if(data.status==1){
                            $scope.dialogopen({
                                msg:T.T("修改成功"),
                                btn:{close:false}
                            })
                            data2.payrate = $scope.updateData.payrate;
                            data2.taxrate = $scope.updateData.taxrate;
                            data2.exchrate = $scope.updateData.exchrate;
                            data2.sharerate = $scope.updateData.sharerate;
                            data2.currency = $scope.updateData.currency;
                            data2.confcurr = $scope.updateData.confcurr;
                            data2.settleMoney = data2.money * ( 1- data2.payrate - data2.taxrate);data2.settleMoney.toFixed(2);
                            data2.shareMoney = data2.settleMoney * data2.sharerate;data2.shareMoney.toFixed(2);
                            data2.foreignMoney = data2.money * data2.exchrate;data2.foreignMoney.toFixed(2);
                            data2.isUpdate = !data2.isUpdate;
                        }else{
                            $scope.dialogopen({
                                msg:T.T("修改失败"),
                                btn:{close:false}
                            })
                        }
                    });
                }
            }
        })
       
    }
    
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		console.log($scope.postData)
		$scope.postData.checktype1 = $scope.postData.billingType;
		$scope.postData.checktype2 = $scope.postData.ispayrates;
		$scope.postData.checktype3 = $scope.postData.isladder;
		$scope.postData.checktype4 = $scope.postData.region;
		$scope.postData.checktype5 = $scope.postData.signCompany;
		$http.post("balanceplat/selectAccountingList.action",$scope.postData).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
            for(var i= 0;i<$scope.panel_1.dataList_1.data.length;i++){
                $scope.panel_1.dataList_1.data[i].isUpdate=false;
                $scope.panel_1.dataList_1.data[i].isOverdue=false;
                if($scope.panel_1.dataList_1.data[i].settlementtype==undefined||$scope.panel_1.dataList_1.data[i].settlementtype==-1){
                	$scope.panel_1.dataList_1.data[i].nosettle_showcolor=true;
                }else{
                	$scope.panel_1.dataList_1.data[i].nosettle_showcolor=false;
                }
                var endDate=(new Date($scope.panel_1.dataList_1.data[i].constractenddate)).format("yyyy-MM-dd");
                var nowDate=(new Date()).format("yyyy-MM-dd");
                if(endDate<=nowDate){
                	//过期
                	$scope.panel_1.dataList_1.data[i].isOverdue=true;
                }
            }
    	});
	}
	$scope.delChannelbalance=function(data,name){
		console.log(data)
	}
	$scope.getProjectList();
	$scope.getGameList();
	$scope.getChannelList();
}