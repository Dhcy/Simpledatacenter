function warningController($scope,$http,T){
    
    $scope.panel_1={
			'model':'table',
			'dataList_1':{
				'data':'',
				'data2':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			}
		}
    $scope.panel_2={
			'model':'table',
			'dataList_1':{
				'data':'',
				'data2':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':10,
					'count':0
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':10,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}
			}
		}
//    $scope.activationTimeArr=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];
    $scope.activationTimeArr=[8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23];//每天7-8点才出昨天的数据,触发时间不能早于8点
    $scope.targetArr=[
    	/*{id:1,name:"设备激活"}*/
    ];
    $scope.getWarningEvents = function(){
    	$http.post('analysis/selectEventList.action',{}).success(function(data){
    		console.log(data);
    		if(data == undefined || undefined){
    			data = [];
    		}
    		$scope.targetArr = data;
    		$scope.waningData.warning_event = (!!$scope.targetArr[0] ? $scope.targetArr[0].name : "");
    	});
    }
    $scope.getWarningEvents();
    $scope.waningData = {}
    
    $scope.initWarningData = function(){
    	$scope.waningData = {
            id : "" ,
            type : 0 , // 0添加 , 1修改
            name : "" , 
            activationTime : 18 ,
            warning_event : (!!$scope.targetArr[0] ? $scope.targetArr[0].name : ""),
            warning_event_id : 1 ,
            cmptype : 0 , // 1高于 , -1低于,0等于
            unit : 0 , // 0百分百 , 1数据值
            valve : "" ,
            status : 0 , //状态 0暂停 , 1 ...
            emails : [""],
            valve2 : ""
        }
    }
    $scope.initWarningData();
    $scope.addEmail=function(){
        $scope.waningData.emails.push("");
    }
    $scope.removeEmail=function(i){
        $scope.waningData.emails.splice(i,1);
    }
    $scope.modifyData = function(data){
        $scope.waningData=data;
        $("#myModal").modal();
    }
    $scope.addData = function(){
    	$scope.initWarningData();
    	$("#myModal").modal();
    }
    $scope.delData=function(data){    //删除预警
        //
    	if(confirm(T.T('确认删除预警:') + data.name)){
    		$scope.searchData.checktype1 = data.id;
        	$http.post('analysis/deleteItem.action',$scope.searchData).success(function(data){
        		console.log(data);
        		if(data == undefined){
        			data = [];
        		}
        		if(data.status == undefined){
        			data.status = 0;
        		}
        		if(data.status == 1){
        			$scope.getData();
        		}
        	});
    	}
    }
//    
//    $scope.selectAllWarningNotice = function(){
//    	$scope.searchData1 = {
//    			'checktype1' : 100,
//    			'gameid' : $scope.$Params.gameid
//    	}
//    	$http.post('analysis/selectAllWarningNotice.action',searchData1).success(function(data){
//    		console.log(data);
//    		if(data == undefined){
//    			data = [];
//    		}
//    		$scope.panel_2.dataList_1.data = data;
//    	});
//    }
    
    $scope.selectWarningNotice = function(){
    	$scope.searchData2 = {
    			'pageLength' : 100,
    			'gameid' : $scope.$Params.gameid
    	}
    	$http.post('analysis/selectWarningNotice.action',$scope.searchData2).success(function(data){
    		console.log(data);
    		if(data == undefined){
    			data = [];
    		}
    		$scope.panel_2.dataList_1.data = data;
    	});
    }
    
    $scope.selectItems = function(){
    	$http.post('analysis/selectItems.action',$scope.searchData).success(function(data){
    		console.log(data);
    		if(data == undefined){
    			data = [];
    		}
    		$scope.panel_1.dataList_1.data = data;
    	});
    }
    $scope.getData=function(){      //获取预警data
    	$scope.searchData = {
	            'gameid': $scope.$Params.gameid
	        }
        //var data = $scope.makeTestData();
    	//$scope.panel_1.dataList_1.data=data;
    	$scope.selectItems();
    	$scope.selectWarningNotice();
    }
    
    $scope.makeTestData = function(){
    	var data= [
    	            {
    	                id : "0" ,
    	                type : 1 , // 0添加 , 1修改
    	                name : T.T("母鸡抖啊1") , 
    	                activationTime : 18 ,
    	                warning_event : T.T("设备激活"),
    	                warning_event_id : 1 ,
    	                cmptype : 1 , // 1高于 , -1低于,0等于
    	                unit : 1 , // 0百分百 , 1数据值
    	                valve : "200" ,
    	                status : 0 , //状态 0暂停 , 1 ...
    	                emails : ["4234234@qq.com"],
    	                valve2 : ""
    	            },{
    	                id : "0" ,
    	                type : 1 , // 0添加 , 1修改
    	                name : T.T("母鸡抖啊1") , 
    	                activationTime : 18 ,
    	                warning_event : T.T("设备激活"),
    	                warning_event_id : 1 ,
    	                cmptype : 1 , // 1高于 , -1低于,0等于
    	                unit : 1 , // 0百分百 , 1数据值
    	                valve : "200" ,
    	                status : 0 , //状态 0暂停 , 1 ...
    	                emails : ["4234234@qq.com"],
    	                valve2 : ""
    	            }
    	        ];
    	return data;
    }
    $scope.getData();
    
    $scope.postData = function(){     // 修改或添加预警
    	$scope.waningData.subscriber = $scope.waningData.emails.join(',');
    	$scope.waningData.projectid = $scope.$Params.projectid;
    	$scope.waningData.gameid = $scope.$Params.gameid;
    	if($scope.waningData.type == 0){
    		//add
    		$http.post("analysis/addItem.action",$scope.waningData).success(function(data){
    			
    			$scope.initWarningData();//修改添加完成后重置data
        	});
    	}else{
    		$http.post("analysis/modifyItem.action",$scope.waningData).success(function(data){
    			$scope.initWarningData();//修改添加完成后重置data
        	});
    	}
    	$scope.selectItems();
    }

//    $scope.noticeData=[
//        {
//            id:0,
//            name:"母鸡抖",
//            intro:"我都布吉岛啊",
//            createDate:new Date()
//        }
//    ];
    
    
    $scope.delNotice=function(id){      //删除通知
    	$scope.searchData.checktype1 = id;
    	$http.post('analysis/updateReadWarningNotice.action',$scope.searchData).success(function(data){
    		console.log(data);
    		if(data == undefined){
    			data = [];
    		}
    		if(data.status == undefined){
    			data.status = 0;
    		}
    		if(data.status == 1){
    			$scope.getData();
    		}
    	});
    }

  
	$scope.changetype=function(i,panel){
		panel.dataList_1.limit.max=i;
		panel.dataList_1.limit.now=0;
	   panel.dataList_1.limit.sumLen=(panel.dataList_1.data).length;
		panel.dataList_1.limit.count=Math.ceil(panel.dataList_1.limit.sumLen/panel.dataList_1.limit.max)-1;
	}
}