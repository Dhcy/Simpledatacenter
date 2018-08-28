function eventsDataController($scope,$http,T){
	$scope.graphfun = {
	};
	$scope.panel_1 = {
		'model': 'table',
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
				's': 'ranking',
				't': ''
			}
		}
	}
	
	$scope.eventsList=[
		{id:1,name:"123"},
		{id:2,name:"456"},
		{id:3,name:"789"},
		{id:4,name:"012"},
		{id:5,name:"345"},
		{id:6,name:"678"},
		{id:7,name:"901"},
		{id:8,name:"234"},
		{id:9,name:"567"},
		{id:10,name:"890"},
		{id:11,name:"123"}
	];
	$scope.funnelData="";
	$scope.addFunnel={
		name:'',
		step:[
			{eventId:"",val:T.T("请选择..."),name:""}
		]
	};
	$scope.changeFunnel={
	};
	$scope.addchangeFunnelStep=function(){
		var obj={id:"",val:T.T("请选择..."),name:""};
		$scope.changeFunnel.step.push(obj);
	}
	$scope.removeChangeFunnelStep=function(i){
		if($scope.changeFunnel.step.length>1)
		$scope.changeFunnel.step.splice(i,1);
	}
	$scope.showChangeFunnel=function(id,funnelId,data){
		$("#"+id).modal('show');
	}
	
	$scope.showDelDialog=function(funnelId,data){
		$("#delDialog").modal('show');
	}
	
	
	$scope.addFunnelStep=function(){
		var obj={id:"",val:T.T("请选择..."),name:""};
		$scope.addFunnel.step.push(obj);
	}
	$scope.removeFunnelStep=function(i){
		if($scope.addFunnel.step.length>1)
		$scope.addFunnel.step.splice(i,1);
	}
	$scope.clearAddFunnel=function(){
		$scope.addFunnel={
			name:'',
			step:[
				{eventId:"",val:T.T("请选择..."),name:""}
			]
		};
	}
	$scope.showAddFunnel=function(id){
		$("#"+id).modal('show').on('hidden.bs.modal', function (e){$scope.clearAddFunnel();});;
	}
	
	$scope.postAddFunnel=function(){		
		console.log($scope.addFunnel);
		if(!$scope.addFunnel.name){
			alert(T.T("请填写名称"));
			return;
		}
		if($scope.addFunnel.step.length==1){
			alert(T.T("请再添加一个步骤"));
			return;
		}
		for(var i=0;i<$scope.addFunnel.step.length;i++){
			if(!$scope.addFunnel.step[i].eventId){
				alert(T.T("请选择追踪依据"));
				return;				
			}else if(!$scope.addFunnel.step[i].name){
				alert(T.T("步骤别名"));
				return;
			}
		}		
		$("#addFunnel").modal('hide');
	}
	
	
	$scope.tablesearch="";
	$scope.$watch("tablesearch", function (nv, ov) {		
		$scope.$watch("pageStartFrom", function (nv, ov) {
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.count=
			Math.ceil($G_pageLimitCount/$scope.panel_1.dataList_1.limit.max)-1;
		});
	});
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.$watch("mchange.date", function (nv, ov) {
		if ($scope.mchange.date) {
			$scope.getAllData();
			$scope.mchange.date = false;
		}
	});
	$scope.$watch("mchange.channelQf", function (nv, ov) {
		if ($scope.mchange.channelQf) {
			$scope.callbackChannelQf();
			$scope.getAllData();
			$scope.mchange.channelQf = false;
		}
	});
	$scope.getAllData = function () {
		$http.post("testData/whales/paidPlayers.json?date=" + $scope.mstatdate + "&channel=" + $scope.mchannel + "&qf=" + $scope.mareaclothing + "&t=" + Math.random(), {}).success(function (data, status, headers, config) {
			$scope.panel_1.dataList_1.data=data.paidPlayers;
		});
		$http.post("testData/events/eventData.json?date=" + $scope.mstatdate + "&channel=" + $scope.mchannel + "&qf=" + $scope.mareaclothing + "&t=" + Math.random(), {}).success(function (data, status, headers, config) {
			$scope.funnelData=data.eventData;
		});
	};
}