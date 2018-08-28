//玩家留存
function playersRetentionController($scope,$http,T){
		$scope.graphfun={
			"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
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
						name: $scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day1_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day1_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day1')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day1RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day2_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day2_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day2')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day2RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day3_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day3_retention,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day3')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day3RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day4_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day4_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day4')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day4RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day5_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day5_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day5')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day5RetNum')).newArr
					}, {
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day6_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day6_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day6')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day6RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day7_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day7_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day7')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day7RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day14_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day14_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day14')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day14RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day30_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day30_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day30')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day30RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day60_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day60_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day60')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day60RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day90_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day90_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day90')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day90RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day120_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day120_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day120')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day120RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day150_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day150_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day150')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day150RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day180_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day180_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day180')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day180RetNum')).newArr
					}]	
				});			
			},
			"dataList_1_2_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'statdate')).newArr			
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
					series: [ {
						name: T.T('1日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day1')).newArr
					},{
						name: T.T('2日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day2')).newArr
					},{
						name: T.T('3日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day3')).newArr
					},{
						name: T.T('4日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day4')).newArr
					},{
						name: T.T('5日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day5')).newArr
					}, {
						name: T.T('6日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day6')).newArr
					},{
						name: T.T('7日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day7')).newArr
					},{
						name: T.T('14日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day14')).newArr
					},{
						name: T.T('30日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'day30')).newArr
					}]
				});			
			},
			"dataList_1_3_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'statdate')).newArr			
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
					series: [ {
						name: T.T('1日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day1')).newArr
					},{
						name: T.T('2日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day2')).newArr
					},{
						name: T.T('3日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day3')).newArr
					},{
						name: T.T('4日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day4')).newArr
					},{
						name: T.T('5日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day5')).newArr
					}, {
						name: T.T('6日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day6')).newArr
					},{
						name: T.T('7日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day7')).newArr
					},{
						name: T.T('14日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day14')).newArr
					},{
						name: T.T('30日留存率(%)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'day30')).newArr
					}]
				});			
			},
			"dataList_1_4_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_4 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'statdate')).newArr			
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
								//enabled: false,
		//						symbol: 'circle',
								//radius: 1,
								states: {
									hover: {
										enabled: true
									}
								}
							}
						}
					},
					series: [{
						name: $scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day1_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day1_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day1')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day1RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day2_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day2_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day2')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day2RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day3_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day3_retention,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day3')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day3RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day4_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day4_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day4')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day4RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day5_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day5_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day5')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day5RetNum')).newArr
					}, {
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day6_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day6_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day6')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day6RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day7_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day7_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day7')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day7RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day14_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day14_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day14')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day14RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day30_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day30_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day30')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_4.data,'day30RetNum')).newArr
					},{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day60_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day60_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day60')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day60RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day90_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day90_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day90')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day90RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day120_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day120_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day120')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day120RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day150_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day150_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day150')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day150RetNum')).newArr
					}
					,{
						name:$scope.retentionType==0?$scope.retention_rate_arr[$scope.retentionType].day180_retention+"(%)":$scope.retention_rate_arr[$scope.retentionType].day180_retention ,
						data: $scope.retentionType==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day180')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day180RetNum')).newArr
					}]	
				});			
			},
			"dataList_2_1_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'bar'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,"statType")).newArr			
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
						name: $scope.panel_2.th.usergroup[$scope.panel_2.analysis.usergroup],
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,"accounts")).newArr
					}]
				});			
			},
		};
		$scope.showtype=true;
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
			},
			'dataList_2':{
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
			},
			'dataList_3':{
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
			},
			'dataList_4':{
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
			},
			'th':{
				'usergroup':['',T.T('Day1留存用户'),T.T('Day7留存用户'),T.T('Day30留存用户')],
				'type':['',T.T('新增日等级'),T.T('新增日游戏次数'),T.T('新增日游戏时长'),T.T('新增日是否付费')],
			},
			'analysis':{
				'usergroup':1,	//(1:Day1留存用户,7:Day7留存用户,30:Day30留存用户,)
				'type':1	//(1:新增日等级,2:新增日游戏次数,3:新增日游戏时长,4:新增日是否付费,)
			}
		};	
		//留存切换
		$scope.retentionType=0;//(0:显示留存率；1： 显示留存率)
		$scope.retentionTypeArr=[T.T("点击按留存数显示"),T.T("点击按留存率显示")];
		$scope.retention_rate_arr=[
		                     {'day1_retention':T.T('1日留存率'),'day2_retention':T.T('2日留存率'),'day3_retention':T.T('3日留存率'),'day4_retention':T.T('4日留存率'),'day5_retention':T.T('5日留存率'),'day6_retention':T.T('6日留存率'),
		                    'day7_retention':T.T('7日留存率'),'day14_retention':T.T('14日留存率'),'day30_retention':T.T('30日留存率'),'day60_retention':T.T('60日留存率'),'day90_retention':T.T('90日留存率'),
		                    'day120_retention':T.T('120日留存率'),'day150_retention':T.T('150日留存率'),'day180_retention':T.T('180日留存率')
		                     },
		                     {
		                    	 'day1_retention':T.T('1日留存数(账号)'),'day2_retention':T.T('2日留存数(账号)'),'day3_retention':T.T('3日留存数(账号)'),'day4_retention':T.T('4日留存数(账号)'),'day5_retention':T.T('5日留存数(账号)'),'day6_retention':T.T('6日留存数(账号)'),
				                  'day7_retention':T.T('7日留存数(账号)'),'day14_retention':T.T('14日留存数(账号)'),'day30_retention':T.T('30日留存数(账号)'),'day60_retention':T.T('60日留存数(账号)'),'day90_retention':T.T('90日留存数(账号)'),
				                  'day120_retention':T.T('120日留存数(账号)'),'day150_retention':T.T('150日留存数(账号)'),'day180_retention':T.T('180日留存数(账号)')
		                     }]
		$scope.changeRetention=function(i){
			if(i==0){
				$scope.retentionType=1;
			}
			if(i==1){
				$scope.retentionType=0;
			}
		}
		
		
		//
		$scope.cityData=[];//获取的区域列表
		$scope.cityArr = []; //筛选后的区域列表
		$scope.cityTextArr = [];
		$scope.cityText = T.T("请选择");
		$scope.showCheckAllText=false;//勾选全选时，文本显示全选
	    //勾选区域
	    $scope.setCityList=function(i){
	        var isHad = $scope.cityArr.indexOf(i);
	        if(isHad>=0){ 
	        		$scope.cityArr.splice(isHad,1);
		            $scope.cityTextArr.splice(isHad,1);
	        }else{
				$scope.cityArr.push(i);
	            $scope.cityTextArr.push(i);
	        }  
	        if(!$scope.isSelectedAll()){
	        	//非全选状态下
	        	$scope.showCheckAllText=false;
	        }else{
	        	//全选状态下
	        	$scope.showCheckAllText=true;
	        }
	        if(!$scope.showCheckAllText){
				 if($scope.cityTextArr.length>0){
			            $scope.cityText= $scope.cityTextArr.join(",")
			        }else{
			        	$scope.cityText= T.T("请选择")
			        }
			}else{
				$scope.cityText= T.T("全选");
			}
	        //显示6个字符
	        if($scope.cityText.length>6){
	        	$scope.cityText=$scope.cityText.substr(0,6);
	        }
	    }
	    //点击全选按钮时
	    $scope.clickAllSelect=function(){
	    	var isCheckAll=$scope.isSelectedAll();
	    	if(!isCheckAll){
	    		//要勾全选
	    		$scope.cityArr.splice(0,$scope.cityArr.length);//清空
	    		 $scope.cityTextArr.splice(0,$scope.cityTextArr.length);
	    		for(var i=0;i<$scope.cityData.length;i++){//添加内容
	    			$scope.cityArr.push($scope.cityData[i].city);
		            $scope.cityTextArr.push($scope.cityData[i].city);
	    		}
	    		$scope.showCheckAllText=true;
	    	}else{
	    		//取消全选
	    		$scope.cityArr.splice(0,$scope.cityArr.length);//清空
	    		$scope.cityTextArr=[];
	    		$scope.showCheckAllText=false;
	    	}
	    	if(!$scope.showCheckAllText){
				 if($scope.cityTextArr.length>0){
			            $scope.cityText= $scope.cityTextArr.join(",")
			        }else{
			        	$scope.cityText= T.T("请选择")
			        }
			}else{
				$scope.cityText= T.T("全选");
			}
		 if($scope.cityText.length>6){
	    	$scope.cityText=$scope.cityText.substr(0,6);
	    }
	    }
	    //是否全选
	    $scope.isSelectedAll = function () {
	        return $scope.cityArr.length === $scope.cityData.length;
	      };
	    //判断加载后的区域是否选中
	    $scope.isSelected = function (id) {
	        return $scope.cityArr.indexOf(id) >= 0;
	      };
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
	
	$scope.selectRetentionOfNewUser = function(){
		//$http.post("testData/newUserRetention.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
		$http.post("players/playersRetention/selectRetentionOfUser.action",$scope.searchData).success(function(data, status, headers, config){
			$scope.panel_1.dataList_1.data=data.newUserRetention;
			//$scope.panel_1.dataList_2.data=data.newUserRetention;
			//$scope.panel_2.dataList_1.data=data.newUserRetention.data;
			$scope.day1AVG=data.day1AVG;
			$scope.day7AVG=data.day7AVG;
			$scope.day30AVG=data.day30AVG;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectRetentionOfActUser = function(){
		$http.post("players/playersRetention/selectRetentionOfActUser.action",$scope.searchData).success(function(data, status, headers, config){
			if(data==undefined){
				data=[];
			}
			$scope.panel_1.dataList_2.data=data;
			$scope.panel_1_change = true;
		});
	}
	$scope.selectRetentionOfReturnUser = function(){
		$http.post("players/playersRetention/selectRetentionOfReturnUser.action",$scope.searchData).success(function(data, status, headers, config){
			if(data==undefined){
				data=[];
			}
			$scope.panel_1.dataList_3.data=data;
			$scope.panel_1_change = true;
		});
	}
	//新增账号留存
	$scope.selectRetentionOfNewAccount = function(){
		$http.post("players/playersRetention/selectRetentionOfNewAccount.action",$scope.searchData).success(function(data, status, headers, config){
			console.log("新增账号留存");
			console.log(data);
			if(data==undefined || data==null){
				data=[];
			}
			$scope.panel_1.dataList_4.data=data;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectRetentionUserAnalys = function(){
		$scope.searchData1={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'termtypes':$scope.gamesyslist,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'checktype1':$scope.panel_2.analysis.type,
	            'checktype2':$scope.panel_2.analysis.usergroup,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	        }

		$http.post("players/playersRetention/selectRetentionUserAnalys.action",$scope.searchData1).success(function(data, status, headers, config){
//			$http.post("testData/userRetention.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
//						$scope.panel_2.data=data.userRetenTion;
//				$scope.getAnalysisData($scope.panel_2.analysis.type,$scope.panel_2.analysis.usergroup);
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
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
	            'checktype1':$scope.panel_2.analysis.type,
	            'checktype2':$scope.panel_2.analysis.usergroup,
	            'versionList':$scope.mversion,
	            'languageList':$scope.mlang,
	            'cityList':($scope.cityArr.length==0||$scope.showCheckAllText==true)?null:$scope.cityArr,
	        }
		$scope.selectRetentionOfNewUser();
		$scope.selectRetentionOfActUser();
		$scope.selectRetentionOfReturnUser();
		$scope.selectRetentionUserAnalys();
		$scope.selectRetentionOfNewAccount();
		$scope.selectRetentionCityList();
	};
	
	
	$scope.getAnalysisData=function(t,i){
		var b="";
		if (i == 1) {
			if (t == 1) {
				b = $scope.panel_2.data[0].day1LevelRetention;
			} else if (t == 2) {
				b = $scope.panel_2.data[1].day7LevelRetention;
			} else if (t == 3) {
				b = $scope.panel_2.data[2].day30LevelRetention;
			}
		} else if (i == 2) {
			if (t == 1) {
				b = $scope.panel_2.data[3].day1PlayTimesRetention;
			} else if (t == 2) {
				b = $scope.panel_2.data[4].day7PlayTimesRetention;
			} else if (t == 3) {
				b = $scope.panel_2.data[5].day30PlayTimesRetention;
			}
		} else if (i == 3) {
			if (t == 1) {
				b = $scope.panel_2.data[6].day1PlayDurationRetention;
			} else if (t == 2) {
				b = $scope.panel_2.data[7].day7PlayDurationRetention;
			} else if (t == 3) {
				b = $scope.panel_2.data[8].day30PlayDurationRetention;
			}
		} else if (i == 4) {
			if (t == 1) {
				b = $scope.panel_2.data[9].day1PayingRetention;
			} else if (t == 2) {
				b = $scope.panel_2.data[10].day7PayingRetention;
			} else if (t == 3) {
				b = $scope.panel_2.data[11].day30PayingRetention;
			}
		}
		$scope.panel_2.dataList_1.data=b;
		$scope.panel_2_change = true;
	}
	
	$scope.changeusergroup=function(i){
		$scope.panel_2.analysis.usergroup=i;
		//$scope.getAnalysisData(i,$scope.panel_2.analysis.type);
		$scope.selectRetentionUserAnalys();
	}
	
	$scope.changetype=function(i){
		$scope.panel_2.analysis.type=i;
		//$scope.getAnalysisData($scope.panel_2.analysis.usergroup,i);
		$scope.selectRetentionUserAnalys();
	}
	//加载城市列表
	$scope.selectRetentionCityList=function(){
		$http.post("players/playersRetention/selectUserRetentionCityList.action",$scope.searchData).success(function(result){
			$scope.cityData.splice(0,$scope.cityData.length);//清空数组 
			if(result==undefined ||result==null){
				result=[];
			}
			$scope.cityData=result;
			if($scope.cityData.length!=0){
				for (var i=0;i<$scope.cityData.length;i++){
					var id=$scope.cityData[i].city;
					$scope.cityData[i].id=id;
				}
			}
		});
	}
}