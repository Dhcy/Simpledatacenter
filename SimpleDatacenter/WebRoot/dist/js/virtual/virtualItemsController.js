function virtualItemsController($scope,$http,T){
	$scope.graphfun={
		"dataList_graph":function(data,xdata,target,name){
			if(!data)return;
			if(!target)return;
			//console.log(data);
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
	$scope.topSaleItemsByCategory="";
	$scope.channelDataQuality="";
	$scope.channelDataRevenue="";
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
	$scope.panel_category = {
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
	$scope.panel_item = {
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
	
	
	
	$scope.tabsbtn=1;
	$scope.showtype="";	//trend,level,stages
	$scope.topSaleItemsByCategory="";
	$scope.topSaleItemsByItem="";
	$scope.changetype=function(data,limitdata,i){
		limitdata.max=i;
		limitdata.now=0;
		limitdata.sumLen=data.length;
		limitdata.count=Math.ceil(limitdata.sumLen/limitdata.max)-1;
	}
	
	$scope.showPanel=function(data,type,k){
		$scope.selectItemPointDetailList(data,type,k);
		
		data.show_panel=true;
		data.panel_change=false;
		data.showtype=type;
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
	$scope.selectItemPointList = function(){
		//$http.post("testData/virtualExpense/virtualItems/topSaleItemsByCategory.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1' : $scope.tabsId,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }
		$http.post("virtual/virtualItems/selectItemPointList.action",$scope.searchData).success(function(data, status, headers, config){
			//console.log(data);
			$scope.topSaleItemsByItem=data;
			for(var i=0;i<$scope.topSaleItemsByItem.length;i++){
				$scope.topSaleItemsByItem[i].dataList=new Array();
				$scope.topSaleItemsByItem[i].view = {};
				$scope.topSaleItemsByItem[i].levels = {};
				for(var k=1;k<5;k++)
				{	
					$scope.topSaleItemsByItem[i].view["dataList_"+k]={
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
					$scope.topSaleItemsByItem[i].levels["dataList_"+k]={
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
				$scope.topSaleItemsByItem[i].view.model='graph';
				$scope.topSaleItemsByItem[i].view.panel_change=true;
				$scope.topSaleItemsByItem[i].view.show_panel=false;			
				$scope.topSaleItemsByItem[i].view.showtype="";
				$scope.topSaleItemsByItem[i].levels.model='graph';
				$scope.topSaleItemsByItem[i].levels.panel_change=true;
				$scope.topSaleItemsByItem[i].levels.show_panel=false;		
				$scope.topSaleItemsByItem[i].levels.showtype="";		//trend,level,stages
				
			}
			//console.log($scope.topSaleItemsByItem);
		});
	}
	
	$scope.selectItemPointDetailList = function(rowdata,type,k){
		$scope.searchData.checktype1=rowdata.itemid;
		$scope.searchData.checktype2=rowdata.itemName;
		//console.log($scope.searchData);
		var url = "";
		switch(type){
		case "view":
			url = "virtual/virtualItems/selectDailyItemPointDetailList.action";
			break;
		case "levels":
			url = "virtual/virtualItems/selectLevelItemPointDetailList.action";
			break;
		}
		//$http.post("testData/virtualExpense/virtualItems/topSaleItemsByItem.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
		$http.post(url,$scope.searchData).success(function(data, status, headers, config){
			//console.log(data);
			rowdata[type]["dataList_1"].data=data;
			rowdata[type]["dataList_2"].data=data;
			rowdata[type]["dataList_3"].data=data;
			rowdata[type]["dataList_4"].data=data;
			//console.log(rowdata);
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
		$scope.selectItemPointList();
	}

}