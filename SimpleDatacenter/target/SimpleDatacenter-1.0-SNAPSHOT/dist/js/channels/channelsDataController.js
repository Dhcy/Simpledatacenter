function channelsDataController($scope,$http,T){
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
		"dataList_1_1_graph":function(data,xdata,target,name){
			if(!data)return;
			if(!target)return;
                
				target.parent().find('.graph').highcharts({
				title:{text:""},
				chart: {type: 'area'},
				colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
				xAxis: {
					tickmarkPlacement: 'on',
					categories:xdata			
				},
				yAxis: {
					title:"",
					labels: {
						formatter: function() {
							return this.value+"%";
						}
					}
				},
				tooltip: {
					shared: true
				},
				plotOptions: {
					area: {
						fillOpacity:0.25,
						marker: {
	//						enabled: false,
	//						symbol: 'circle',
	//						radius: 1,
							states: {
								hover: {
									enabled: true
								}
							}
						}
					}
				},
				series: [{
					name: T.T('1日留存率(%)'),
					data: data.day1
				},{
					name: T.T('2日留存率(%)'),
					data: data.day2
				},{
					name: T.T('3日留存率(%)'),
					data: data.day3
				},{
					name: T.T('4日留存率(%)'),
					data: data.day4
				},{
					name: T.T('5日留存率(%)'),
					data: data.day5
				}, {
					name: T.T('6日留存率(%)'),
					data: data.day6
				},{
					name: T.T('7日留存率(%)'),
					data: data.day7
				},{
					name: T.T('14日留存率(%)'),
					data: data.day14
				},{
					name: T.T('30日留存率(%)'),
					data: data.day30
				}]	
			});			
		}
	}
	$scope.channelsListData="";
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
	$scope.tabsbtn=0;
	$scope.changeTabsBtn=function(i){
        $scope.tabsbtn=i;
        $scope.getAllData(i);
    }
	$scope.showPanel=function(data,type){
		//var index = $(this).index();
		$scope.selectDetail(data,type);
		data.show_panel=true;
		data.panel_change=false;
	}
	
	$scope.$watch("mchange.date", function (nv, ov) {
		if ($scope.mchange.date) {
			$scope.getAllData(0);
			$scope.mchange.date = false;
		}
	});
	$scope.$watch("mchange.channelQf",function(nv,ov){
		if($scope.mchange.channelQf){
			$scope.getAllData();
			$scope.mchange.channelQf=false;
		}
	});
	$scope.expandDetail = function(){
		console.log(this);
	}
	
	$scope.selectPartnerAmountMap = function(){
		//$http.post("testData/channels/channelDataQuantity.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
	   $scope.channelsListData=[];
        $http.post("channels/selectPartnerAmountMap.action",$scope.searchData).success(function(data){
            console.log(data);
			$scope.channelsListData=data.data;
			for(var i=0;i<$scope.channelsListData.length;i++){
				for(var k=1;k<8;k++)
				{
					$scope.channelsListData[i]["dataList_"+k]={
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
				$scope.channelsListData[i].model='graph';
				$scope.channelsListData[i].panel_change=true;
				$scope.channelsListData[i].show_panel=false;
			}
		});
	}
	
	$scope.selectPartnerQuality = function(){
		//console.log($scope.searchData);
		//$http.post("testData/channels/channelDataQuality.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
		$http.post("channels/selectPartnerQuality.action",$scope.searchData).success(function(data){
			console.log(data)
			if(data == undefined){
				data = [];
			}
			$scope.channelDataQuality= data;
			for(var i=0;i<$scope.channelDataQuality.length;i++){
				for(var k=1;k<8;k++)
				{	
					$scope.channelDataQuality[i]["dataList_"+k]={
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
				$scope.channelDataQuality[i].model='graph';
				$scope.channelDataQuality[i].panel_change=true;
				$scope.channelDataQuality[i].show_panel=false;
			}
		});
	}
	
	$scope.selectPartnerIncome = function(){
		$scope.channelDataRevenue=[];
		//$http.post("testData/channels/channelDataRevenue.json?t="+Math.random(),{"data":$scope.mstatdate}).success(function(data){
		$http.post("channels/selectPartnerIncome.action",$scope.searchData).success(function(data){
			console.log(data)
			if(data == undefined){
				data = [];
			}
			$scope.channelDataRevenue=data;
			for(var i=0;i<$scope.channelDataRevenue.length;i++){
				for(var k=1;k<8;k++){
					$scope.channelDataRevenue[i]["dataList_"+k]={
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
				$scope.channelDataRevenue[i].model='graph';
				$scope.channelDataRevenue[i].panel_change=true;
				$scope.channelDataRevenue[i].show_panel=false;
			}
		});
	}
	
	$scope.selectPartnerAmountDetail = function(rowdata){
		$http.post("channels/selectPartnerAmountDetail.action",$scope.searchData).success(function(data){
			console.log(data.channelDataList)
			console.log(data.newUserRetentionList)
			rowdata.trend["devices"] = data.channelDataList;
			rowdata.trend["newPlayers"] = data.channelDataList;
			rowdata.trend["dailyActive"] = data.channelDataList;
			rowdata.trend["income"] = data.channelDataList;
			rowdata.trend["dailyPaidPlayers"] = data.channelDataList;
			rowdata.trend["ARPU"] = data.channelDataList;
			rowdata.trend["newPlayerRetain"] = data.newUserRetentionList;
			//rowdata.trend["newPlayerRetain"] = [{"statdate":"2016-04-15","accounts":1,"day1":1,"day2":1,"day3":1,"day4":1,"day5":1,"day6":1,"day7":1,"day14":1,"day30":1}];
			rowdata.panel_change=true;
        });
	}
    
    $scope.selectDetail=function(o,type,i){    
        //type : devices,newPlayers,dailyActive,income,dailyPaidPlayers,ARPU,
        $scope.searchData.checktype1= o.channelid;
        
        $scope.selectPartnerAmountDetail(o);
        /*$http.post("",$scope.searchData).success(function(data){
            //o.trend[type]=data;
            o.panel_change=true;
        })*/
    }
    
    
    
	$scope.getAllData=function(i){
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
        if(i == undefined || i==0)
		$scope.selectPartnerAmountMap();
        else if(i==1)
		$scope.selectPartnerQuality();
        else if(i==2)
		$scope.selectPartnerIncome();
		
	}
}