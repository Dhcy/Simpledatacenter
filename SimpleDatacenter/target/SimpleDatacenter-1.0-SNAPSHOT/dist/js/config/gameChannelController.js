
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
function gameChannelController($scope, $http, $sce,T) {
    $scope.panel_1 = {
		'model': 'table',
		'channelList': '',
		'gameList': '',
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
    
    
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}

    $scope.postData = {
    		gameid : "-1"
    }
    $scope.gameList=[];
    $scope.channelListData=[];
    $scope.channelData={
    	gameid:0,
    	channelList:[]
    };
    
    $scope.selectChannelId="-1";
    $scope.selectChannelName="";
    $scope.changeId=function(id, name){
    	$scope.selectChannelId=id;
    	$scope.selectChannelName=name;
    }
    
    
    /*$scope.o={
        name :　"adfdsaf"
    }*/
    $scope.gameChannelData={    //修改
        id : "",
        gameid : -1,
        channelid : "",
        servertype : 1,
        joinserver : "",
        joingame : -1,
        joingameShow : "",
        sdkname : 1,
        sdkversion : "",
        sdkdownloadaddr : "",
        clientplattype : 1,
        icon : 1,   //角标
        launchvideo : 1,
        packagename : "",
        clientpacktype : 1,
        updateaddr : "",
        /*cp_id : "",
        game_id : "",
        server_id : "",
        client_side_encrypt_key : "",
        server_side_encrypt_key : "",*/
        access_options : "",
        show_access_options : "",
        remark : "",
        subChannels : ""
    };
    $scope.gameChannelDataShow={    //查看
        id : "",
        gameid : -1,
        channelid : "",
        servertype : "",
        joinserver : "",
        joingame : "",
        joingameShow : "",
        sdkname : "",
        sdkversion : "",
        sdkdownloadaddr : "",
        clientplattype : "",
        icon : "",   //角标
        launchvideo : "",
        packagename : "",
        clientpacktype : "",
        updateaddr : "",
        /*cp_id : "",
        game_id : "",
        server_id : "",
        client_side_encrypt_key : "",
        server_side_encrypt_key : "",*/
        access_options : "",
        show_access_options : "",
        remark : "",
        subChannels : ""
    };
    
        
    //服务器
    $scope.serverList=[
        {id : 1, name : T.T("专服")},
        {id : 2, name : T.T("混服")}
    ];
    //SDK
    $scope.sdknameList=[
        {id: 1, name : T.T("官方SDK")},
        {id: 2, name : T.T("渠道SDK")},
        {id: 3, name : T.T("苹果SDK")}
    ];
    //客户端类型
    $scope.clientplattypeList=[
        {id : 1, name : "IOS"},
        {id : 2, name : T.T("安卓")},
        {id : 3, name : "Winphone"}
    ];
    //角标
    $scope.iconList=[
        {id : 1, name : T.T("有")},
        {id : 2, name : T.T("无")},
        {id : 3, name : T.T("官方")}
    ]
    //开机画面
    $scope.launchvideoList=[
        {id : 1, name : T.T("增加平台开机画面")},
        {id : 2, name : T.T("不添加")},
        {id : 3, name : T.T("官方")}
    ];
    //客户端需求
    $scope.clientpacktypeList=[
        {id : 1, name : T.T("整包")},
        {id : 2, name : T.T("分包")}
    ];
    //公共显示列表
    /*$scope.listDataShow = function(){
        
    }*/
    $scope.searchObj = function(o,k,v,t){   //获取对应id的名称
        if(!o)return;
        for(var i = 0 ; i < o.length ; i++){
            if(o[i][k] == v){
                return o[i][t];
            }
        }
        return ;
    }
    //接入游戏joingame
    //$scope.gameList
    $scope.gameData = function(){
        for(var i=0;i< $scope.gameList.length;i++){
            if($scope.channelData.gameid == $scope.gameList[i].id ){
                $scope.gameChannelData.joingameShow =$scope.gameList[i].name;
                $scope.gameChannelData.joingame = $scope.channelData.gameid;
               $scope.gameChannelDataShow.joingameShow =$scope.gameList[i].name;
               $scope.gameChannelDataShow.joingame = $scope.channelData.gameid;
             }
        }
    }

    $scope.type = 1; //1查看 ， 2修改
    //操作
    $scope.getDetailData = function(t,d){   //获取渠道详情数据
        $scope.type = t ;
        $scope.gameChannelDataShow.subChannels = d.subChannels;
        $scope.gameChannelData.subChannels = d.subChannels;
        $http.post("channel/selectGameChannel.action",{"id" : d.id}).success(function(data){
        	//{"id":1,"gameid":25,"channelid":1,"servertype":null,"joinserver":null,"sdkname":null,"sdkversion":null,"sdkdownloadaddr":null,"clientplattype":null,"icon":null,"launchvideo":null,"packagename":null,"clientpacktype":null,"updateaddr":null,"access_options":null,"remark":null}
        	data.subChannels = $scope.gameChannelData.subChannels;
        	$scope.gameChannelData = $scope.clone(data);
        	$scope.gameChannelDataShow = $scope.clone(data);
        	
        	$scope.reInitGameChannelData($scope.gameChannelData,true);
        	$scope.reInitGameChannelData($scope.gameChannelDataShow,false);
        	$scope.gameData();
        	$scope.gameChannelDataShow.show_access_options = $sce.trustAsHtml( ($scope.gameChannelData.access_options = undefined || $scope.gameChannelData.access_options == null ? "" : $scope.gameChannelData.access_options).replace(/\n/g,"<br />") );   //处理回车
		})
    }
    $scope.clone = function(obj){
    	var o;
    	switch(typeof obj){
    	case 'undefined': break;
    	case 'string'   : o = obj + '';break;
    	case 'number'   : o = obj - 0;break;
    	case 'boolean'  : o = obj;break;
    	case 'object'   :
    		if(obj === null){
    			o = null;
    		}else{
    			if(obj instanceof Array){
				o = [];
				for(var i = 0, len = obj.length; i < len; i++){
					o.push($scope.clone(obj[i]));
				}
			}else{
					o = {};
					for(var k in obj){
						o[k] = $scope.clone(obj[k]);
					}
				}
    		}
    		break;
    	default:		
    		o = obj;break;
    	}
    	return o;	
    }
    $scope.reInitGameChannelData = function(gameChannelData,withDefault){
    	if((gameChannelData.servertype == undefined || gameChannelData.servertype == null) && withDefault){
    		gameChannelData.servertype = 1;
    	}else{
    		gameChannelData.servertype = parseInt(gameChannelData.servertype);
    	}
    	if((gameChannelData.channelid == undefined || gameChannelData.channelid == null) && withDefault){
    		gameChannelData.channelid = "";
    	}
    	if((gameChannelData.joingame == undefined || gameChannelData.joingame == null) && withDefault){
    		gameChannelData.joingame = "";
    	}
    	if((gameChannelData.joingameShow == undefined || gameChannelData.joingameShow == null) && withDefault){
    		gameChannelData.joingameShow = "";
    	}
    	if((gameChannelData.sdkname == undefined || gameChannelData.sdkname == null) && withDefault){
    		gameChannelData.sdkname = 1;
    	}else{
    		gameChannelData.sdkname = parseInt(gameChannelData.sdkname);
    	}
    	if((gameChannelData.sdkversion == undefined || gameChannelData.sdkversion == null) && withDefault){
    		gameChannelData.sdkversion = "";
    	}
        if((gameChannelData.sdkdownloadaddr == undefined || gameChannelData.sdkdownloadaddr == null) && withDefault){
    		gameChannelData.sdkdownloadaddr = "";
    	}
        if((gameChannelData.clientplattype == undefined || gameChannelData.clientplattype == null) && withDefault){
    		gameChannelData.clientplattype = 1;
    	}else{
    		gameChannelData.clientplattype = parseInt(gameChannelData.clientplattype);
    	}
        if((gameChannelData.icon == undefined || gameChannelData.icon == null) && withDefault){
    		gameChannelData.icon = 1;
    	}else{
    		gameChannelData.icon = parseInt(gameChannelData.icon);
    	}
        if((gameChannelData.launchvideo == undefined || gameChannelData.launchvideo == null) && withDefault){
    		gameChannelData.launchvideo = 1;
    	}else{
    		gameChannelData.launchvideo = parseInt(gameChannelData.launchvideo);
    	}
        if((gameChannelData.packagename == undefined || gameChannelData.packagename == null) && withDefault){
    		gameChannelData.packagename = "";
    	}
        if((gameChannelData.clientpacktype == undefined || gameChannelData.clientpacktype == null) && withDefault){
    		gameChannelData.clientpacktype = 1;
    	}else{
    		gameChannelData.clientpacktype = parseInt(gameChannelData.clientpacktype);
    	}
        if((gameChannelData.updateaddr == undefined || gameChannelData.updateaddr == null) && withDefault){
    		gameChannelData.updateaddr = "";
    	}
        if((gameChannelData.access_options == undefined || gameChannelData.access_options == null) && withDefault){
    		gameChannelData.access_options = "";
    	}
        if((gameChannelData.show_access_options == undefined || gameChannelData.show_access_options == null) && withDefault){
    		gameChannelData.show_access_options = "";
    	}
        if((gameChannelData.remark == undefined || gameChannelData.remark == null) && withDefault){
    		gameChannelData.remark = "";
    	}
        if((gameChannelData.subChannels == undefined || gameChannelData.subChannels == null) && withDefault){
    		gameChannelData.subChannels = [];
    	}
    }

    //提交
    $scope.postFunc = function(){
        // $http.post("",{}).success(function(data){
        // });
       // console.log($scope.gameChannelData)
        var su = confirm(T.T("确认保存渠道信息?"));
        
       // console.log(su);
        if(!su)return;
        $("#showData").modal('hide');
        /******/
        //提交渠道数据 channelData
        /******/
        $http.post("channel/updateGameChannel.action",$scope.gameChannelData).success(function(data){
    				data.status != 1 ? alert(data.message) : $scope.searchData();
    			})
    };
    
    $scope.searchData=function(){
    	$http.post("channel/selectGameChannelsList.action",
    			{"gameid" : $scope.postData.gameid}).success(function(data){
    				$scope.channelData.gameid = $scope.postData.gameid;
    				$scope.channelData.channelList = data;
    				//$scope.channelListData = data;
    			})
    	
    }
    
    $scope.getChannelData=function(){
    	$http.get("getUserGameList.action").success(function(data){
    		$scope.gameList = data;
            $scope.postData.gameid = data[0].id;
            $scope.searchData();
    	});
    	
    	/*$http.get("getUserChannelList.action").success(function(data){
    		$scope.channelListData = data;
    		//$scope.gameid = data[0].id;
    	})*/
    	
        /*****/
        //请求gameList  游戏列表
        /*****/
        //请求channelListData  渠道列表
        /*****/
        //请求channelData  渠道回调
        /*****/
        
        
    }


    $scope.getChannelData();
    
}

