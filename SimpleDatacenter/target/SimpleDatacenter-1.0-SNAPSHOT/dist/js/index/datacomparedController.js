function datacomparedController($scope,$http,T){
	$scope.graphfun={
		'dataList_1_1_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'date')).newArr
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
						name: $scope.panel_1.dataList_1.comparedList.searchByKey("id",$scope.panel_1.dataList_1.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_1.comparedList.searchByKey("id",$scope.panel_1.dataList_1.compareId).name+T.T(T.T('(对比)')),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_1.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_1.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
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
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'date')).newArr
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
						name: $scope.panel_1.dataList_2.comparedList.searchByKey("id",$scope.panel_1.dataList_2.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_2.comparedList.searchByKey("id",$scope.panel_1.dataList_2.compareId).name+T.T(T.T('(对比)')),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_2.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_2.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_3_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'date')).newArr
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
						name: $scope.panel_1.dataList_3.comparedList.searchByKey("id",$scope.panel_1.dataList_3.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_3.comparedList.searchByKey("id",$scope.panel_1.dataList_3.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_3.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_3.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_4_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_4 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'date')).newArr
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
						name: $scope.panel_1.dataList_4.comparedList.searchByKey("id",$scope.panel_1.dataList_4.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_4.comparedList.searchByKey("id",$scope.panel_1.dataList_4.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_4.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_4.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_5_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_5 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_5.data,'date')).newArr
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
						name: $scope.panel_1.dataList_5.comparedList.searchByKey("id",$scope.panel_1.dataList_5.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_5.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_5.comparedList.searchByKey("id",$scope.panel_1.dataList_5.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_5.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_5.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_5.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_6_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_6 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_6.data,'date')).newArr
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
						name: $scope.panel_1.dataList_6.comparedList.searchByKey("id",$scope.panel_1.dataList_6.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_6.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_6.comparedList.searchByKey("id",$scope.panel_1.dataList_6.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_6.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_6.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_6.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_7_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_7 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_7.data,'date')).newArr
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
						name: $scope.panel_1.dataList_7.comparedList.searchByKey("id",$scope.panel_1.dataList_7.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_7.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_7.comparedList.searchByKey("id",$scope.panel_1.dataList_7.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_7.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_7.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_7.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
		'dataList_1_8_graph':function(data,xdata,ydata){
			$('.panel_1 .dataList_8 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_1.dataList_8.data,'date')).newArr
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
						name: $scope.panel_1.dataList_8.comparedList.searchByKey("id",$scope.panel_1.dataList_8.compareId).name,
						data: ($scope.dataPushArr( $scope.panel_1.dataList_8.data,'value')).newArr
					}, {
						name: $scope.panel_1.dataList_8.comparedList.searchByKey("id",$scope.panel_1.dataList_8.compareId).name+T.T('(对比)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_8.data,'new_value')).newArr,
						 tooltip: {
				            pointFormatter: function(){
								return  '<span style="color:'+this.series.color+'">\u25CF</span> '+this.series.name+":<b>"+this.y+"</b> ("+ $scope.panel_1.dataList_8.data.searchByKey("date",
									($scope.dataPushArr( $scope.panel_1.dataList_8.data,'date')).norArr[this.x]
									).new_date+")";
							}
				        }
					}]
			});
		},
	};
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.callbackChannelQf();	
				$scope.getData();
				$scope.mchange.channelQf=false;
			}
		});

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

	$scope.tabsId="1";
	$scope.tabsarr=[{"id":1,"name":T.T("日")},{"id":2,"name":T.T("周")},{"id":3,"name":T.T("月")}];
	$scope.changetabs=function(id){
		$scope.tabsId=id;
		$scope.postData.checktype1=id;
	}

	$scope.panel_1={
		'model':'table',
		'dataList_1':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"actvcount",
			"comparedList":[
				{id:"actvcount",name:T.T("激活台数")},
				{id:"newact",name:T.T("新增玩家(角色)")}
			],
		},
		'dataList_2':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"dau",
			"comparedList":[
				{id:"dau",name:"DAU"},
				{id:"wau",name:"WAU"},
				{id:"mau",name:"MAU"},
			],
		},
		'dataList_3':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"newPayUser",
			"comparedList":[
				{id:"newPayUser",name:T.T("新增付费玩家(角色)")},
				{id:"totalPayUser",name:T.T("总付费玩家(角色)")},
			],
		},
		'dataList_4':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"money",
			"comparedList":[
				{id:"money",name:T.T("收入金额")},
				{id:"times",name:T.T("充值次数")},
			],
		},
		'dataList_5':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"acu",
			"comparedList":[
				{id:"acu",name:T.T("ACU(角色)")},
				{id:"pcu",name:T.T("PCU(角色)")},
			],
		},
		'dataList_6':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":0,
			"comparedList":[
				{id:0,name:T.T("日付费率(角色)")},
			],
		},
		'dataList_7':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":"newUser",
			"comparedList":[
				{id:"newUser",name:T.T("新增用户次日留存(角色)")},
				{id:"actUser",name:T.T("活跃用户次日留存(角色)")},
				{id:"returnUser",name:T.T("回流用户次日留存(角色)")},
			],
		},
		'dataList_8':{
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'date','t':''},
			"compareId":0,
			"comparedList":[
				{id:0,name:T.T("ACU/PCU(角色)")},
			],
		}
	};

	$scope.unix_to_datetime=function(unix) {
		var now = new Date((unix));
		if(!$scope.format)
		return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
		else{
			return now.format($scope.format);
		}

	}
	$scope.postData={
		selectId:0,
		startdate:'',
		enddate:'',
		startTime:$scope.unix_to_datetime(new Date()),
		endTime:$scope.unix_to_datetime(new Date()),
		checktype1: 1 ,
		checktype2: 1 ,
		checktype3: "",
	}
	$scope.setPostData=function(){
		$scope.postData.gameid=$scope.$Params.gameid;
		$scope.postData.termtypes=$scope.gamesyslist;
		$scope.postData.areas=$scope.mareaclothing;
		$scope.postData.channelList=$scope.mchannel;
		$scope.postData.versionList=$scope.mversion;
		$scope.postData.languageList=$scope.mlang;
		$scope.postData.startdate=$scope.unix_to_datetime($scope.postData.startTime);
		$scope.postData.enddate=$scope.unix_to_datetime($scope.postData.endTime);
	}
	$scope.getData=function(i){
		$scope.postData.selectId= i>=0?i:$scope.postData.selectId;
		$scope.setPostData();
		$scope.postData.checktype3=$scope.panel_1['dataList_'+($scope.postData.selectId+1)].compareId;
		if($scope.postData.selectId==0){
			$scope.selectNewPlayerList();
		}
		else if($scope.postData.selectId==1){
			$scope.selectAUList();
		}
		else if($scope.postData.selectId==2){
			$scope.selectPayPlayerList();
		}
		else if($scope.postData.selectId==3){
			$scope.selectIncomeList();
		}
		else if($scope.postData.selectId==4){
			$scope.selectAcuAndPcuList();
		}
		else if($scope.postData.selectId==5){
			$scope.selectPayRateList();
		}
		else if($scope.postData.selectId==6){
			$scope.selectDay1RetainRateList();
		}
		else if($scope.postData.selectId==7){
			$scope.selectAcuDivPcuList();
		}
	}
	$scope.selectNewPlayerList=function(){
		$http.post("index/dataCompare/selectNewPlayerList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_1.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectAUList=function(){
		$http.post("index/dataCompare/selectAUList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_2.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectIncomeList=function(){
		$http.post("index/dataCompare/selectIncomeList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_4.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectPayPlayerList=function(){
		$http.post("index/dataCompare/selectPayPlayerList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_3.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectAcuAndPcuList=function(){
		$http.post("index/dataCompare/selectAcuAndPcuList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_5.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectPayRateList=function(){
		$http.post("index/dataCompare/selectPayRateList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_6.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectDay1RetainRateList=function(){
		$http.post("index/dataCompare/selectDay1RetainRateList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_7.data=data;
            $scope.panel_1_change=true;
		})
	}
	$scope.selectAcuDivPcuList=function(){
		$http.post("index/dataCompare/selectAcuDivPcuList.action",$scope.postData).success(function(data){
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_8.data=data;
            $scope.panel_1_change=true;
		})
	}
}
