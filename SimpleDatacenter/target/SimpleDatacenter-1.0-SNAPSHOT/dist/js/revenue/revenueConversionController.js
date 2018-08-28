function revenueConversionController($scope,$http,T){
		$scope.graphfun={
		"dataList_1_1_graph":function(data,xdata,ydata){
				$('.panel_1 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'area'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataDateArr( $scope.panel_1.dataList_1.data,'statdate')).newArr			
						},
						yAxis: [{
							title:"",
							labels: {
								format: '{value}% '
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
							name: T.T('日付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'dailyPayRate')).newArr,
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
								format: '{value}%'
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
							name: T.T('周付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'weeklyPayRate')).newArr,
							tooltip: {
								valueSuffix: ''
							}
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
						yAxis: [{
							title:"",
							labels: {
								format: '{value}% '
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
							name: T.T('月付费率(%)'),
							data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'monthPayRate')).newArr,
							tooltip: {
								valueSuffix: T.T('账户数')
							}
						}]
				});			
			},	
		"dataList_2_1_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_1 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'actorlevel')).newArr			
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
							name: T.T('收入金额'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'money')).newArr,
							tooltip: {
								valueSuffix: '￥'
							}
						}]
				});			
			},	
		"dataList_2_2_graph":function(data,xdata,ydata){
				$('.panel_2 .dataList_2 .graph').highcharts({
						title:{text:""},
						chart: {type: 'column'},
						colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
						xAxis: {
							type: 'linear',
							tickmarkPlacement: 'on',
							categories: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'actorlevel')).newArr			
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
							name: T.T('充值人次'),
							data: ($scope.dataPushArr( $scope.panel_2.dataList_2.data,'quantity')).newArr,
							tooltip: {
								valueSuffix: T.T('次')
							}
						}]
				});			
			},			
		"dataList_3_1_graph":function(data,xdata,ydata){
			$('.panel_3 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'bar'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: xdata,
					},{
						categories: [0,1,2,3],
						opposite: true
					}],
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
						name: T.T('活跃玩家地区'),
						data: data
					}]
			});		
		},	
		"dataList_4_1_graph":function(data,xdata,ydata){
			$('.panel_4 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'bar'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					legend: {
						layout: 'vertical',  
						align: 'right',       
						verticalAlign: 'top', 
						x: 0,               
						y: 0,                                                        					floating: true,       
						borderWidth: 1,                                                					backgroundColor: '#FFFFFF',  
						shadow: true                                                   				},
					xAxis: [{
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: xdata,
					},{
						categories: [0,1,2,3],
						opposite: true
					}],
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
						name: T.T('渠道收入'),
						data: data
					}]
			});		
		},
		"dataList_5_1_graph":function(data,xdata,ydata){
			$('.panel_5 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataDateArr( $scope.panel_5.dataList_1.data,'statdate')).newArr			
					},
					yAxis: [{
						title:"",
						labels: {
							format: '{value}% '
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
						name: T.T('日付费率(%)'),
						data: ($scope.dataPushArr( $scope.panel_5.dataList_1.data,'dailyPayRate')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					}]
			});			
		},	
	"dataList_5_2_graph":function(data,xdata,ydata){
			$('.panel_5 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_5.dataList_2.data,'statdate')).newArr			
					},
					yAxis: [{
						title:"",
						labels: {
							format: '{value}%'
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
						name: T.T('周付费率(%)'),
						data: ($scope.dataPushArr( $scope.panel_5.dataList_2.data,'weeklyPayRate')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					}]
			});			
		},	
	"dataList_5_3_graph":function(data,xdata,ydata){
			$('.panel_5 .dataList_3 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_5.dataList_3.data,'statdate')).newArr			
					},
					yAxis: [{
						title:"",
						labels: {
							format: '{value}% '
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
						name: T.T('月付费率(%)'),
						data: ($scope.dataPushArr( $scope.panel_5.dataList_3.data,'monthPayRate')).newArr,
						tooltip: {
							valueSuffix: T.T('账户数')
						}
					}]
			});			
		},	
	};
	$scope.panel_1={	
		'model':'graph',
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
	$scope.panel_3={	
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
	$scope.panel_4={	
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
	$scope.panel_5={	
			'model':'graph',
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
		        	$scope.cityText= "请选择"
		        }
		}else{
			$scope.cityText= "全选";
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
      
    //加载城市列表
  	$scope.selectRechargeCityList=function(){
  		$http.post("search/selectRechargeCityList.action",$scope.searchData).success(function(result){
  			console.info("区域:");
  			console.info(data);
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
	
	$scope.selectDayPayRate = function(){
//		$http.post("testData/revenue/revenueData/dataRevenue.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_1.data=data.dataRevenue;
//			$scope.panel_1_change = true;
//		});
		
		$http.post("revenue/conversion/selectDayPayRate.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_1.dataList_1.data=data.dayPayRateList;
			$scope.panel_1.dataList_1.AVG = data.avg;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectWeekPayRate = function(){
//		$http.post("testData/revenue/revenueData/dataNumberOfPayment.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_2.data=data.dataNumberOfPayment;
//		});
		$http.post("revenue/conversion/selectWeekPayRate.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_1.dataList_2.data=data.weekPayRateList;
			$scope.panel_1.dataList_2.AVG = data.avg;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectMonthPayRate = function(){
//		$http.post("testData/revenue/revenueData/dataNumberOfPaidAccount.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_1.dataList_3.data=data.dataNumberOfPaidAccount;
//		});
		$http.post("revenue/conversion/selectMonthPayRate.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_1.dataList_3.data=data.monthPayRateList;
			$scope.panel_1.dataList_3.AVG = data.avg;
			$scope.panel_1_change = true;
		});
	}
	
	$scope.selectActorLevelPaymentList = function(){
//		$http.post("testData/revenue/revenueData/paymentByLevelRevenue.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_2.dataList_1.data=data.paymentByLevelRevenue;
//			$scope.panel_2_change = true;
//		});
		$http.post("revenue/conversion/selectActorLevelPaymentList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_2.dataList_1.data=data;
			$scope.panel_2_change = true;
		});
	}
	
	$scope.selectActorLevelRechargeTimeList = function(){
//		$http.post("testData/revenue/revenueData/paymentByLevel-PayingAccountTime.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			$scope.panel_2.dataList_2.data=data['paymentByLevel-PayingAccountTime'];
//		});
		$http.post("revenue/conversion/selectActorLevelRechargeTimeList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_2.dataList_2.data=data;
			$scope.panel_2_change = true;
		});
	}
	
	$scope.selectChannelIncomeList = function(){
//		$http.post("testData/revenue/revenueData/revenueByChannel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
//			 $scope.panel_4.dataList_1.data = data.revenueByChannel;
//			 $scope.panel_4_change = true;
//		});
		$http.post("revenue/conversion/selectChannelIncomeList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			 $scope.panel_4.dataList_1.data = data;
			 $scope.panel_4_change = true;
		});
	}
	//按账号的付费率
	$scope.selectDayPayRateByAccount = function(){
		
		$http.post("revenue/conversion/selectDayPayRateOfAccoutList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_5.dataList_1.data=data.dayPayRateList;
			$scope.panel_5.dataList_1.AVG = data.avg;
			$scope.panel_5_change = true;
		});
	}
	
	$scope.selectWeekPayRateByAccount = function(){
		$http.post("revenue/conversion/selectWeekPayRateOfAccoutList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_5.dataList_2.data=data.weekPayRateList;
			$scope.panel_5.dataList_2.AVG = data.avg;
			$scope.panel_5_change = true;
		});
	}
	
	$scope.selectMonthPayRateByAccount = function(){
		$http.post("revenue/conversion/selectMonthPayRateOfAccoutList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data == undefined){
				data = {};
			}
			$scope.panel_5.dataList_3.data=data.monthPayRateList;
			$scope.panel_5.dataList_3.AVG = data.avg;
			$scope.panel_5_change = true;
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
	            'cityList':($scope.cityArr.length==0||$scope.showCheckAllText==true)?null:$scope.cityArr
	        }
		$scope.selectDayPayRate();
		$scope.selectWeekPayRate();
		$scope.selectMonthPayRate();
		$scope.selectActorLevelPaymentList();
		$scope.selectActorLevelRechargeTimeList();
		$scope.selectChannelIncomeList();
		$scope.selectRechargeCityList();
		//
		$scope.selectDayPayRateByAccount();
		$scope.selectWeekPayRateByAccount();
		$scope.selectMonthPayRateByAccount();
		
		
		
		$http.post("testData/revenue/revenueData/revenueByChina.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{"data":$scope.mstatdate,"channel":$scope.mchannel,"qf":$scope.mareaclothing}).success(function(data, status, headers, config){
			 $scope.panel_3.dataList_1.data = data.revenueByChina;
			 $scope.panel_3_change = true;
		});
		
	}
}