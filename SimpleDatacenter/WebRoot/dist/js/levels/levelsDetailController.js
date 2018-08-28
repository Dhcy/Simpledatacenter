function levelsDetailController($scope,$http,T){
	$scope.graphfun={
		"dataList_graph":function(data,xdata,target,name){
			if(!data)return;
			if(!target)return;
				target.parent().find('.graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: xdata		
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
							name: name,
							data: data
						}]
				});
		},
		"dataList_graph2":function(data,xdata,target,name){
			if(!data)return;
			if(!target)return;
				target.parent().find('.graph').highcharts({
						title:{text:""},
						chart: {type: 'bar'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: xdata		
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
							name: name,
							data: data
						}]
				});
		}
	}
	$scope.levelDetail="";
	var panel_1 = {
		'model': 'table',
		'dataList_1': {
			'data': '',
			'graphLimit': {
				'sumLen': 0,
				'now': -1,
				'max': 100,
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
				's': '',
				't': ''
			}
		}
	}
	
	$scope.panel_leveldateil = {
		'model': 'table',
		'dataList_1': {
			'data': '',
			'graphLimit': {
				'sumLen': 0,
				'now': -1,
				'max': 100,
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
				's': '',
				't': ''
			}
		}
	}
	
	
	
	$scope.tabsbtn=0;
	$scope.showtype="";	//trend,level,stages
	//$scope.changetype=function(limitdata,i){
	$scope.changetype=function(data,limitdata,i){
		limitdata.max=i;
		limitdata.now=0;
		limitdata.sumLen=data.length;
		limitdata.count=Math.ceil(limitdata.sumLen/limitdata.max)-1;
	}
	$scope.showPanel=function(data,$event,type){
		
		data.show_panel=true;
		data.panel_change=false;
		data.showtype=type;
		$scope.selectDistributeDetailData(type,data);
		setTimeout(function(){			
			data.panel_change=true;
			$($event.target).parent().parent().parent().find(".panel-act .nav .active").click();
		},10);	
	}
	$scope.$watch("mchange.date", function (nv, ov) {
		if ($scope.mchange.date) {
			$scope.getAllData();
			$scope.mchange.date = false;
		}
	});
	$scope.$watch("mchange.channelQf",function(nv,ov){
		if($scope.mchange.channelQf){
			$scope.getAllData();
			$scope.mchange.channelQf=false;
		}
	});
	
	$scope.selectLevelStageData = function(){
		//$http.post("testData/levelAnalytics/levelDetail/levelDetail.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
		$http.post("level/selectLevelStageData.action",$scope.searchData).success(function(data){
			if(data == undefined){
				data = [];
			}
			$scope.levelDetail=data;
			for(var i=0;i<$scope.levelDetail.length;i++){
				//$scope.levelDetail[i].dataList=new Array();
				
				$scope.levelDetail[i].data={
					"upgradeDuration":$scope.levelDetail[i].upgradeDuration,
				}
				
				$scope.levelDetail[i].upgradeDuration={};
				$scope.levelDetail[i].dailyTrend={}
				for(var k=1;k<5;k++)
				{
					$scope.levelDetail[i].data["dataList_"+k]={
						'data': '',
						'graphLimit': {
							'sumLen': 0,
							'now': -1,
							'max': 100,
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
							's': '',
							't': ''
						}
					};
					$scope.levelDetail[i].dailyTrend["dataList_"+k]={
						'data': '',
						'graphLimit': {
							'sumLen': 0,
							'now': -1,
							'max': 100,
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
							's': '',
							't': ''
						}
					};
				}
				$scope.levelDetail[i].data.model='graph';
				$scope.levelDetail[i].panel_change=true;
				$scope.levelDetail[i].show_panel=false;			
				$scope.levelDetail[i].upgradeDuration.showtype="";
				$scope.levelDetail[i].dailyTrend.model='graph';
				$scope.levelDetail[i].panel_change=true;
				$scope.levelDetail[i].show_panel=false;		
				$scope.levelDetail[i].showtype="";		
				
			}
		
		});
	}
	
	$scope.selectDistributeDetailData = function(type,rowdata){
		$scope.searchData.checktype1 = rowdata.statType;
		switch(type){
		case "upgradeDuration":
			$scope.selectUpgradeDuration(rowdata);
			break;
		case "dailyTrend":
			$scope.selectDailyTrend(rowdata);
			break;
		}
	}
	
	$scope.selectDailyTrend = function(rowdata){
		//console.log(rowdata)
		$http.post("level/selectDailyTrend.action",$scope.searchData).success(function(data){
			if(data == undefined){
				data = {};
			}
			if(rowdata.dailyTrend == undefined){
				rowdata.dailyTrend = {};
			}
			//console.log(data);
			rowdata.dailyTrend.dataList_1.data = data.dailyUpgrade;
			rowdata.dailyTrend.dataList_2.data = data.levelPlateau;
			rowdata.dailyTrend.dataList_3.data = data.payTimeAndMoney;
			rowdata.dailyTrend.dataList_4.data = data.payTimeAndMoney;
			rowdata.panel_change=true;
		});
	}
	
	$scope.selectUpgradeDuration = function(rowdata){
		
		$http.post("level/selectUpgradeDuration.action",$scope.searchData).success(function(data){
			if(data == undefined){
				data = {};
			}
			//console.log(data);
			rowdata.data.dataList_1.data = data
			rowdata.panel_change=true;
		});
	}
	
	$scope.getAllData=function(){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$scope.selectLevelStageData();
	}

}