/**
 * ltv控制器
 * @param $scope
 * @param $http
 * @return
 */
function  revenueLtvController($scope,$http,T){
		$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'regdate')).newArr			
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
							name: T.T('新增账号数'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newact')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day0_pay ,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day0_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day0_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day1_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day1_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day1_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day2_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day2_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day2_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: $scope.pay_rate_arr[$scope.payType1].day3_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day3_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day3_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: $scope.pay_rate_arr[$scope.payType1].day4_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day4_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day4_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day4_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day5_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day5_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day6_pay,
							data:$scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day6_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day6_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day7_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day7_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day7_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name:$scope.pay_rate_arr[$scope.payType1].day8_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day8_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day8_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},
						{
							name:$scope.pay_rate_arr[$scope.payType1].day9_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day9_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day9_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},
						{
							name: $scope.pay_rate_arr[$scope.payType1].day10_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day10_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day10_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},
						{
							name:$scope.pay_rate_arr[$scope.payType1].day11_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day11_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day11_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},
						{
							name:$scope.pay_rate_arr[$scope.payType1].day12_pay,
							data: $scope.payType1==0?($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day12_pay')).newArr:($scope.dataPushArr( $scope.panel_1.dataList_1.data,'day_pay_rate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
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
						yAxis: [{
							title:"",
							labels: {
								format: '{value}'
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
							name: 'ltv3',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'ltv3')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: 'ltv7',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'ltv3')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: 'ltv30',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'ltv30')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						},{
							name: 'ltv60',
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'ltv60')).newArr,
							tooltip: {
								valueSuffix: ''
							}
						}]
				});			
			},	
		
	};
		//是否显示注收比按钮(true:是,false:否)
		$scope.isShow=true;
		$scope.payType1=0;//(0:显示付费额；1：显示注收比例)
		$scope.payTypeArr=[T.T("切换到按注收比例"),T.T("切换到按实际付费额")];
		$scope.pay_rate_arr=[
		                     {'day0_pay':T.T('当日付费'),'day1_pay':T.T('1日付费'),'day2_pay':T.T('2日付费'),'day3_pay':T.T('3日付费'),'day4_pay':T.T('4日付费'),'day5_pay':T.T('5日付费'),
		                    'day6_pay':T.T('6日付费'),'day7_pay':T.T('7日付费'),'day8_pay':T.T('8日付费'),'day9_pay':T.T('9日付费'),'day10_pay':T.T('10日付费'),'day11_pay':T.T('11日付费'),
		                    'day12_pay':T.T('12日付费')
		                     },
		                     {
		                    	 'day0_pay':T.T('当日占比'),'day1_pay':T.T('1日占比'),'day2_pay':T.T('2日占比'),'day3_pay':T.T('3日占比'),'day4_pay':T.T('4日占比'),'day5_pay':T.T('5日占比'),
				                  'day6_pay':T.T('6日占比'),'day7_pay':T.T('7日占比'),'day8_pay':T.T('8日占比'),'day9_pay':T.T('9日占比'),'day10_pay':T.T('10日占比'),'day11_pay':T.T('11日占比'),
				                  'day12_pay':T.T('12日占比')
		                     }]
		$scope.changePay=function(i){
			if(i==0){
				$scope.payType1=1;
			}
			if(i==1){
				$scope.payType1=0;
			}
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
		}
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
	
	$scope.selectRegPayMoneyAndCountAndRateList=function(){
		$http.post("revenue/ltv/selectRegPayMoneyAndCountAndRate.action",$scope.searchData).success(function(data){
			console.log(T.T("注收比"));
			console.log(data);
        	if (data == undefined||data==null) {
        		data=[];
        	}
        	$scope.panel_1.dataList_1.data=data;
    		$scope.panel_1_change=true;
		})
	}
	
	$scope.selectLtvofCurrDateList=function(){
		$http.post("revenue/ltv/selectLtvofCurrDateList.action",$scope.searchData).success(function(data){
			console.log("生命周期");
			console.log(data);
        	if (data == undefined||data==null) {
        		data=[];
        	}
        	$scope.panel_1.dataList_2.data=data;
    		$scope.panel_1_change=true;
		})
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
		$scope.selectRegPayMoneyAndCountAndRateList();
		$scope.selectLtvofCurrDateList();
	}
}