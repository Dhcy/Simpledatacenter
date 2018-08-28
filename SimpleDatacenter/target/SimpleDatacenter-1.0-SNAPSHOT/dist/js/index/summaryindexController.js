function summaryindexController($scope,$http,T){
	$scope.graphfun={
		 //新增激活和账户
		'dataList_1_1_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('激活台数(台)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'deviceCount')).newArr
					}, {
						name: T.T('新增玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'userCount')).newArr
					}]
			});			
		},
		
		//活跃玩家
		'dataList_1_2_graph':function(data,xdata,ydata){
		$('.panel_1 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_1.dataList_2.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('老玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'oldActiveUser')).newArr
					},
					{
						name: T.T('新玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'newActiveUser')).newArr
					}]
			});		
		},
		
		//付费玩家
		'dataList_1_3_graph':function(data,xdata,ydata){
		$('.panel_1 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_1.dataList_3.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('老付费玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'oldPayUser')).newArr
					},
					{
						name: T.T('新付费玩家'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'newPayUser')).newArr
					}]
			});		
		},
		
		//收入
		'dataList_1_4_graph':function(data,xdata,ydata){
		$('.panel_1 .dataList_4 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_1.dataList_4.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
					tooltip: {
						shared: true
					},
					plotOptions: {
						area: {
		//					//stacking: 'normal',
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
						name: T.T('收入(￥)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'income')).newArr
					}]
			});		
		},
		
		//日付费率
		'dataList_2_1_graph':function(data,xdata,ydata){
		$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_2.dataList_1.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('日付费率'),
						data: ($scope.dataPushArr($scope.panel_2.dataList_1.data,'payRate')).newArr
					}]
			});		
		},
		
		//日ARPU
		'dataList_2_2_graph':function(data,xdata,ydata){
		$('.panel_2 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_2.dataList_2.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('日ARPU'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'dayARPU')).newArr
					}]
			});		
		},
		
		//日ARPPU
		'dataList_2_3_graph':function(data,xdata,ydata){
		$('.panel_2 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_2.dataList_3.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('日ARPPU'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_3.data,'dayARPPU')).newArr
					}]
			});		
		},
		
		//新增账户留存
		'dataList_3_1_graph':function(data,xdata,ydata){
		$('.panel_3 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_3.dataList_1.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('次日留存'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'userDay2')).newArr
					},
					{
						name: T.T('7日留存'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'userDay7')).newArr
					},
					{
						name: T.T('30日留存'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'userDay30')).newArr
					}]
			});		
		},
		/*'dataList_3_2_graph':function(data,xdata,ydata){
		$('.panel_3 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_3.dataList_2.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						},
						max:100
					},
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
						name: '激活设备留存',
						data: ($scope.dataPushArr( $scope.panel_3.dataList_2.data,'userTransformRate')).newArr
					}]
			});		
		},*/
		//平均游戏时长与次数
		'dataList_4_1_graph':function(data,xdata,ydata){
		$('.panel_4 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_4.dataList_1.data,'statdate')).newArr			
					},
					yAxis: {
						title:"",
						labels: {
							formatter: function() {
								return this.value;
							}
						}
					},
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
						name: T.T('平均游戏次数'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'gameCounts')).newArr
					},
					{
						name: T.T('平均游戏时长'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'gameTimes')).newArr
					}]
			});		
		}
	}
	
	
	//关键指标
	$scope.panel_1={
		'model':'graph',
		//汇总
		'dataSUM':{
			'deviceActivation':0,
			"newUser":0,
			"payUser":0,
			"income":0,
		},
		//新增激活和账户
		'dataList_1':{
			'deviceSUM':0,
			'accountSUM':0,
			'devicAVG':0,
			'accountAVG':0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
		},
		//活跃玩家
		'dataList_2':{
			'activeUserAVG':0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}	
		},
		//付费玩家
		'dataList_3':{
			'payUserAVG':0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}	
		},
		//收入
		'dataList_4':{
			'incomeSUM':0,
			'incomeAVG':0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}	
		}
	};
	
	//付费渗透
	$scope.panel_2={
		'model':'table',
		//日付费率
		'dataList_1':{
			"payAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
		},
		//日ARPU
		'dataList_2':{
			"payAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}	
		},
		//日ARPPU
		'dataList_3':{
			"payAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}	
		}
	};
	
	//新增账户留存
	$scope.panel_3={
		'model':'table',
		'dataList_1':{
			"day2AVG":0,
			"day7AVG":0,
			"day30AVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
		}
	};
	//平均游戏时长与次数
	$scope.panel_4={
		'model':'table',
		'dataList_1':{
			"timesAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
		}
	};
	

	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData();
				$scope.mchange.date=false;
			}
		});
	
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.getAllData();
				$scope.mchange.channelQf=false;
			}
		});
    
    
	$scope.selectSummaryData = function(){        
        
		$http.post("index/selectSummaryData.action",$scope.searchData).success(function(data){
			if(data == undefined){
				data = {};
			}
			if(data.deviceActivation == undefined){
				data.deviceActivation = [];
			}
			$scope.panel_1.dataSUM.deviceActivation=data.deviceActivation;
            $scope.panel_1.dataSUM.newUser=data.newUser;
            $scope.panel_1.dataSUM.payUser=data.payUser;
            $scope.panel_1.dataSUM.income=data.income;
            
            $scope.panel_1_change=true;
		})
	}
    
  
	
    $scope.selectNewPlayerList = function(){
        $http.post("index/selectNewPlayerList.action",$scope.searchData).success(function(data){
            if (data[0] == undefined) {
            	data[0] = {};
            }
            if(data[0].dataList == undefined){
            	data[0].dataList = [];
            }
            $scope.panel_1.dataList_1.deviceSUM=data[0].deviceSUM;
            $scope.panel_1.dataList_1.accountSUM=data[0].accountSUM;
            $scope.panel_1.dataList_1.devicAVG=data[0].devicAVG;
            $scope.panel_1.dataList_1.accountAVG=data[0].accountAVG;
            $scope.panel_1.dataList_1.data=data[0].dataList;

            $scope.panel_1_change=true;
		})
    }
    
    $scope.selectActivePlayerList = function(){
        $http.post("index/selectActivePlayerList.action",$scope.searchData).success(function(data){
        	 if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
            $scope.panel_1.dataList_2.activeUserAVG=data[0].activeUserAVG;
			$scope.panel_1.dataList_2.data=data[0].dataList;
				
			$scope.panel_1_change=true;
		})
    }
    
    $scope.selectPaidPlayerList = function(){
        $http.post("index/selectPaidPlayerList.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
             $scope.panel_1.dataList_3.payUserAVG=data[0].payUserAVG;
     		$scope.panel_1.dataList_3.data=data[0].dataList;
     		
     		$scope.panel_1_change=true;
		})
    }
    
    $scope.selectIncomeNumList = function(){
        $http.post("index/selectIncomeNumList.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
        	if (data != "") {
        		$scope.panel_1.dataList_4.incomeSUM=data[0].incomeSUM;
        		$scope.panel_1.dataList_4.incomeAVG=data[0].incomeAVG;
        		$scope.panel_1.dataList_4.data=data[0].dataList;
        		
        		$scope.panel_1_change=true;
        	}
		})
    }
    
    
    $scope.selectDailyRateOfPay = function(){
        $http.post("index/selectDailyRateOfPay.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
        	if (data != "") {
        		$scope.panel_2.dataList_1.payAVG=data[0].payAVG;
        		
        		$scope.panel_2.dataList_1.data=data[0].dataList;
        		
        		$scope.panel_2_change=true;
        	}
		})
    }
    
    $scope.selectDailyARPU = function(){
        $http.post("index/selectDailyARPU.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
             $scope.panel_2.dataList_2.payAVG=data[0].payAVG;
     		
     		$scope.panel_2.dataList_2.data=data[0].dataList;
     		
     		$scope.panel_2_change=true;
		})
    }
    
    $scope.selectDailyARPPU = function(){
        $http.post("index/selectDailyARPPU.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
             $scope.panel_2.dataList_3.payAVG=data[0].payAVG;
     		
     		$scope.panel_2.dataList_3.data=data[0].dataList;
     		
     		$scope.panel_2_change=true;
		})
    }
    
    $scope.downloadMainValue = function(){
    	window.location.href="index/downloadKeyIndicator.action?gameid="
    		+$scope.searchData.gameid+"&termtypes=1"+"&termtypes=2"+"&termtypes=3"
    		+"&channelList=1&channelList=2"; //$scope.parseParam($scope.searchData);
    }
    
    $scope.parseParam = function(param, key){
    	var _this = this;
		var paramStr="";
		if(param instanceof String||param instanceof Number||param instanceof Boolean){
			_this.paramStr+="&"+key+"="+encodeURIComponent(param);
		}else{
			$.each(param,function(i){
				var k=key==null?i:key+(param instanceof Array?"["+i+"]":"."+i);
				_this.paramStr+='&'+_this.parseParam(this, k);
			});
		}
		return _this.paramStr.substr(1);
	};
	
    $scope.selectNewUserRetention = function(){
        $http.post("index/selectNewUserRetention.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
             $scope.panel_3.dataList_1.day2AVG=data[0].day2AVG;
     		$scope.panel_3.dataList_1.day7AVG=data[0].day7AVG;
     		$scope.panel_3.dataList_1.day30AVG=data[0].day30AVG;
     		
     		$scope.panel_3.dataList_1.data=data[0].dataList;
     		
     		$scope.panel_3_change=true;
		})
    }
    
     $scope.selectAvgGameTimesList = function(){
        $http.post("index/selectAvgGameTimesList.action",$scope.searchData).success(function(data){
        	if (data[0] == undefined) {
             	data[0] = {};
             }
             if(data[0].dataList == undefined){
             	data[0].dataList = [];
             }
             $scope.panel_4.dataList_1.timesAVG=data[0].timesAVG;     
     		 $scope.panel_4.dataList_1.data=data[0].dataList;
     		
     		$scope.panel_4_change=true;
		})
    }
    
    //查询条件
    $scope.searchData={};
    
	$scope.getAllData=function(o){
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
        
		//console.info($scope.searchData);
		
		$scope.selectSummaryData();
		
        $scope.selectNewPlayerList();
        $scope.selectActivePlayerList();
        $scope.selectPaidPlayerList();
        $scope.selectIncomeNumList();
        
        $scope.selectDailyRateOfPay();
        
		$scope.selectNewUserRetention();
        
        $scope.selectAvgGameTimesList();
	}
	
}