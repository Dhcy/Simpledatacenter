/**
 * dau和收入模型预测
 * @param $scope
 * @param $http
 * @return
 */
function dauAndIncomeController($scope,$http,T){

	$scope.graphfun={
		'dataList_1_1_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'currDate')).newArr
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
							fillOpacity:0.25,
							marker: {
								enabled: false,
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
						name: "预测DAU(Min)",
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'min_dau')).newArr
					},{
						name: "预测DAU(Max)",
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'max_dau')).newArr
					}]
			});
		},
		'dataList_1_2_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'currDate')).newArr
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
							fillOpacity:0.25,
							marker: {
								enabled: false,
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
						name: T.T("预测收入累计(Min)"),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'min_income')).newArr
					},{
						name: T.T("预测收入累计(Max)"),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'max_income')).newArr
					},]
			});
		},
		'dataList_2_1_graph':function(data,xdata,ydata){
			$('.panel_2 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'currDate')).newArr
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
							fillOpacity:0.25,
							marker: {
								enabled: false,
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
						name: T.T("预测DAU(Min)"),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'min_dau')).newArr
					},{
						name: T.T("预测DAU(Max)"),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'max_dau')).newArr
					},]
			});
		},
		'dataList_2_2_graph':function(data,xdata,ydata){
			$('.panel_2 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'currDate')).newArr
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
							fillOpacity:0.25,
							marker: {
								enabled: false,
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
						name: T.T("预测收入累计(Min)"),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'min_income')).newArr
					},{
						name: T.T("预测收入累计(Max)"),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'max_income')).newArr
					},]
			});
		},
	};
	 $scope.dateOptions = {
		formatYear: 'yy',
		timezone:'+0800',
		mode:$scope.mode || 'day',
		minMode:$scope.minmode || '',
		maxMode:$scope.maxmode || '',
		maxDate: $scope.maxdate?new Date($scope.maxdate):"",
		minDate: new Date(2000,1,1),
		startingDay: 1
	  };
	$scope.open1 = function() {
		$scope.popup1.opened = true;
	  };
	$scope.popup1 = {
		opened: false
	  };
	$scope.open2 = function() {
		$scope.popup2.opened = true;
	  };
	$scope.popup2 = {
		opened: false
	};
	$scope.open3 = function() {
		$scope.popup3.opened = true;
	  };
	$scope.popup3 = {
		opened: false
	};
	$scope.unix_to_datetime=function(unix) {
		var now = new Date((unix));
		if(!$scope.format)
		return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		else{
			return now.format($scope.format);
		}
	}
	$scope.panel_1={
		'model':'table',
		'dataList_1':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''},
		},
		'dataList_2':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''},
		},
	};
	$scope.panel_2={
		'model':'table',
		'dataList_1':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''},
		},
		'dataList_2':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':10,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''},
		},
	};
	$scope.naturalUsersRetentionData={
		decayValue:0,
		max:{
			"1":0,
			"2":0,
			"3":0,
			"4":0,
			"5":0,
			"6":0,
			"7":0,
			"14":0,
			"30":0,
		},
		min:{
			"1":0,
			"2":0,
			"3":0,
			"4":0,
			"5":0,
			"6":0,
			"7":0,
			"14":0,
			"30":0,
		},
	};     
	$scope.promotionUsersRetentionData={
		decayValue:0,
		max:{
			"1":0,
			"2":0,
			"3":0,
			"4":0,
			"5":0,
			"6":0,
			"7":0,
			"14":0,
			"30":0,
		},
		min:{
			"1":0,
			"2":0,
			"3":0,
			"4":0,
			"5":0,
			"6":0,
			"7":0,
			"14":0,
			"30":0,
		},
	};


	$scope.flag="1";
	$scope.showNaturalUsersRetention=false;
	$scope.showPromotionUsersRetention=false;
	$scope.naturalUsersPostData={
		flag:1,
		currDate:(new Date()).format("yyyy-MM-dd"),
		userCnt:0,
		arpu:0,
		payRate:0,
		decayValue:0,
		maxDay1Rate:0,
		maxDay2Rate:0,
		maxDay3Rate:0,
		maxDay4Rate:0,
		maxDay5Rate:0,
		maxDay6Rate:0,
		maxDay7Rate:0,
		maxDay14Rate:0,
		maxDay30Rate:0,
		minDay1Rate:0,
		minDay2Rate:0,
		minDay3Rate:0,
		minDay4Rate:0,
		minDay5Rate:0,
		minDay6Rate:0,
		minDay7Rate:0,
		minDay14Rate:0,
		minDay30Rate:0,
	};
	$scope.promotionUsersPostData={
		flag:"2",
		startDate:(new Date( parseInt((new Date()).getTime()) - 604800000 )).format("yyyy-MM-dd"),
		endDate:(new Date()).format("yyyy-MM-dd"),
		mutilParamList:[
			{
				startDate:(new Date( parseInt((new Date()).getTime()) - 604800000 )).format("yyyy-MM-dd"),
				endDate:(new Date()).format("yyyy-MM-dd"),
				userCnt:0,
				opened1:false,
				opened2:false
			}
		],
		userCnt:0,
		arpu:0,
		payRate:0,
		decayValue:0,
		maxDay1Rate:0,
		maxDay2Rate:0,
		maxDay3Rate:0,
		maxDay4Rate:0,
		maxDay5Rate:0,
		maxDay6Rate:0,
		maxDay7Rate:0,
		maxDay14Rate:0,
		maxDay30Rate:0,
		minDay1Rate:0,
		minDay2Rate:0,
		minDay3Rate:0,
		minDay4Rate:0,
		minDay5Rate:0,
		minDay6Rate:0,
		minDay7Rate:0,
		minDay14Rate:0,
		minDay30Rate:0,
	};
	$scope.removeDateUser=function(id){
		$scope.promotionUsersPostData.mutilParamList.splice(id,1)
	}
	$scope.addDateUser=function(){
		var o={
			startDate:(new Date( parseInt((new Date()).getTime()) - 604800000 )).format("yyyy-MM-dd"),
			endDate:(new Date()).format("yyyy-MM-dd"),
			userCnt:0,
			opened1:false,
			opened2:false
		}
		$scope.promotionUsersPostData.mutilParamList.push(o);
	}
	$scope.naturalUsersRetentionToShow=function(){
		$scope.getNaturalUsersRetention();
		$("#naturalUsersRetention").modal("show");
	}
	$scope.naturalUsersRetentionToSave=function(i){
		if(i==1){
			$scope.setNaturalUsersRetention();
		}else{
			$scope.getNaturalUsersRetention();
		}
		$("#naturalUsersRetention").modal("hide");
	}
	$scope.promotionUsersRetentionToShow=function(){
		$scope.getPromotionUsersRetention();
		$("#promotionUsersRetention").modal("show");
	}
	$scope.promotionUsersRetentionToSave=function(i){	//1保存, 0取消
		if(i==1){
			$scope.setPromotionUsersRetention();
		}else{
			$scope.getPromotionUsersRetention();
		}
		$("#promotionUsersRetention").modal("hide");
	}
	$scope.getNaturalUsersRetention=function(){

		$scope.naturalUsersRetentionData.decayValue=$scope.naturalUsersPostData.decayValue;
		for(var k in $scope.naturalUsersRetentionData.max)
		{
			$scope.naturalUsersRetentionData.max[k]=$scope.naturalUsersPostData["maxDay"+k+"Rate"];
		}
		for(var k in $scope.naturalUsersRetentionData.min)
		{
			$scope.naturalUsersRetentionData.min[k]=$scope.naturalUsersPostData["minDay"+k+"Rate"];
		}
	};
	$scope.setNaturalUsersRetention=function(){

		$scope.naturalUsersPostData.decayValue=$scope.naturalUsersRetentionData.decayValue;
		for(var k in $scope.naturalUsersRetentionData.max)
		{
			$scope.naturalUsersPostData["maxDay"+k+"Rate"]=$scope.naturalUsersRetentionData.max[k];
		}
		for(var k in $scope.naturalUsersRetentionData.min)
		{
			$scope.naturalUsersPostData["minDay"+k+"Rate"]=$scope.naturalUsersRetentionData.min[k];
		}
	};
	$scope.getPromotionUsersRetention=function(){

		$scope.promotionUsersRetentionData.decayValue=$scope.promotionUsersPostData.decayValue;
		for(var k in $scope.promotionUsersRetentionData.max)
		{
			$scope.promotionUsersRetentionData.max[k]=$scope.promotionUsersPostData["maxDay"+k+"Rate"];
		}
		for(var k in $scope.promotionUsersRetentionData.min)
		{
			$scope.promotionUsersRetentionData.min[k]=$scope.promotionUsersPostData["minDay"+k+"Rate"];
		}
	};
	$scope.setPromotionUsersRetention=function(){

		$scope.promotionUsersPostData.decayValue=$scope.promotionUsersRetentionData.decayValue;
		for(var k in $scope.promotionUsersRetentionData.max)
		{
			$scope.promotionUsersPostData["maxDay"+k+"Rate"]=$scope.promotionUsersRetentionData.max[k];
		}
		for(var k in $scope.promotionUsersRetentionData.min)
		{
			$scope.promotionUsersPostData["minDay"+k+"Rate"]=$scope.promotionUsersRetentionData.min[k];
		}
	};



	$scope.postData=function(){
		var postdata;
		if($scope.flag==1){
			postdata=$scope.naturalUsersPostData;
			postdata.currDate=(new Date(postdata.currDate)).format("yyyy-MM-dd");
		}else{
			postdata=$scope.promotionUsersPostData;
			postdata.startDate=(new Date(postdata.startDate)).format("yyyy-MM-dd");
			postdata.endDate=(new Date(postdata.endDate)).format("yyyy-MM-dd");
		}
		$http.post("dauMode/calculDauAndIncome.action",postdata).success(function(data){
			if($scope.flag==1){
				$scope.panel_1.dataList_1.data=data.dauList;
				$scope.panel_1.dataList_2.data=data.incomeList;
				$scope.panel_1_change=true;
			}else{
				$scope.panel_2.dataList_1.data=data.dauList;
				$scope.panel_2.dataList_2.data=data.incomeList;
				$scope.panel_2_change=true;
			}
		})
	}
}
