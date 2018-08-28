$main.controller('registPointController',registPointController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.yunyindata.registPoint',{//注册打点
    	url:'/registPoint',
    	templateUrl:'template/hie_idp/registPoint.html?T='+Math.random(),
    	controller:'registPointController'
    })
	
})
function registPointController($scope,$http,T){
	$scope.graphfun={
			//新用户
			"dataList_1_1_graph":function(data,xdata,ydata){
					$('.panel_1 .dataList_1 .graph').highcharts({
							title:{text:""},
							chart: {type: 'spline'},
							colors: ["#3cc12f", "#b2de54", "#ea1313", "#ca0404", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							xAxis: {
								type: 'linear',
								tickmarkPlacement: 'on',
								categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'statdate')).newArr
							},
							yAxis: [{
								title:"",
								labels: {
									format: '{value} '
								}
							}],
							tooltip: {
								shared: true
							},
							plotOptions: {
								area: {
									//stacking: 'normal',
									fillOpacity:0.25,
									marker: {
										enabled: false,
				//						symbol: 'circle',
										radius: 1,
										states: {
											hover: {
												enabled: true
											}
										}
									}
								}
							},
							series: [{
								name: T.T('启动游戏'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'gamestart')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('游戏初始化'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'gameinit')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('解压数据'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'gameunpack')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('检查更新'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'updatecheck')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('更新'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'gameupdate')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('注册账号'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'regist')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('点击开始游戏'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'poitstart')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('进入loading界面'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'enterload')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('进入游戏界面'),
								data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'entergame')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							}	
							]
					});
				},
				//老用户		
				"dataList_2_1_graph":function(data,xdata,ydata){
					$('.panel_2 .dataList_1 .graph').highcharts({
							title:{text:""},
							chart: {type: 'spline'},
							colors: ["#3cc12f", "#b2de54", "#ea1313", "#ca0404", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
							xAxis: {
								type: 'linear',
								tickmarkPlacement: 'on',
								categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'statdate')).newArr
							},
							yAxis: [{
								title:"",
								labels: {
									format: '{value} '
								}
							}],
							tooltip: {
								shared: true
							},
							plotOptions: {
								area: {
									//stacking: 'normal',
									fillOpacity:0.25,
									marker: {
										enabled: false,
				//						symbol: 'circle',
										radius: 1,
										states: {
											hover: {
												enabled: true
											}
										}
									}
								}
							},
							series: [{
								name: T.T('启动游戏'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'gamestart')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('游戏初始化'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'gameinit')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('检查更新'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'updatecheck')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('更新'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'gameupdate')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},{
								name: T.T('点击开始游戏'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'poitstart')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('进入loading界面'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'enterload')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							},
							{
								name: T.T('进入游戏界面'),
								data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'entergame')).newArr,
								tooltip: {
									valueSuffix: ''
								}
							}	
							]
					});
				},	
				}
	
	
	
	
	
	
	
	
	
	

	$scope.panel_1={
			'model':'table',
			'dataList_1':{
				'data':'',
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
	};
	$scope.panel_2={
			'model':'table',
			'dataList_1':{
				'data':'',
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
	};
	$scope.gameAreaList=[];
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.changetype2=function(i){
		$scope.panel_2.dataList_1.limit.max=i;
		$scope.panel_2.dataList_1.limit.now=0;
		$scope.panel_2.dataList_1.limit.sumLen=($scope.panel_2.dataList_1.data).length;
		$scope.panel_2.dataList_1.limit.count=Math.ceil($scope.panel_2.dataList_1.limit.sumLen/$scope.panel_2.dataList_1.limit.max)-1;
	}
	//日期
	$scope.o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 86400000 )).format("yyyy-MM-dd"),
			endDate:(new Date()).format("yyyy-MM-dd"),
			opened1:false,
			opened2:false
		}
	 //
    $scope.searchData={
    		gameid:''
    		
    };
	//查询条件
    $scope.postData={
    		areaid:-1,
    		startdate:'',
    		enddate:'',
    		gameid:'',
    		channelid:-1,
    		checktype1:1
    }
    $scope.postData2={}
    $scope.selectChannelList = function(){
		$http.post("search/selectGameChannelList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,channelSimName:T.T("全部")}];   
			$scope.channelList=dat.concat(data);
		})
	}
    $scope.selectChannelList2 = function(){
		$http.post("search/selectGameChannelList.action",$scope.searchData1).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,channelSimName:T.T("全部")}];   
			$scope.channelList=dat.concat(data);
		})
	}
    
    
	
	$scope.selectGameAreaList = function(){
		$http.post("search/selectGameAreaList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[];
			}
			var dat=[{id:-1,name:T.T("全部")}];   
			$scope.gameAreaList=dat.concat(data);;
		})
	}
	$scope.queryRegistStatList=function(){
		$http.post("hieOperate/queryRegistStatList.action",$scope.postData).success(function(data){
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_1.dataList_1.data=data.result;
			$scope.panel_1_change=true;
		})
	}

	
	$scope.queryRegistStatList2=function(){
	    $scope.postData2.areaid=$scope.postData.areaid
		$scope.postData2.startdate=$scope.postData.startdate
		$scope.postData2.enddate=$scope.postData.enddate
		$scope.postData2.gameid=$scope.postData.gameid
		$scope.postData2.channelid=$scope.postData.channelid
		$scope.postData2.checktype1=2
		$http.post("hieOperate/queryRegistStatList.action",$scope.postData2).success(function(data2){
			if(data2.status!= 1){
				data2.result=[];
			}
			$scope.panel_2.dataList_1.data=data2.result;
			$scope.panel_2_change=true;
		})
	}
	
	
	
	$scope.getSelect=function(){
		$scope.searchData.gameid=$scope.$Params.gameid;
		$scope.selectGameAreaList();
		$scope.selectChannelList();
	}
    $scope.getAllData=function(){
    	$scope.postData.startdate=(new Date($scope.o.startDate)).format("yyyy-MM-dd");
    	$scope.postData.enddate=(new Date($scope.o.endDate)).format("yyyy-MM-dd");
    	$scope.postData.gameid=$scope.$Params.gameid;
    	$scope.queryRegistStatList();
    	$scope.queryRegistStatList2();
    }
  //加载下拉框
    $scope.getSelect();
    $scope.getAllData();
    
}