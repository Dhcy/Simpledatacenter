//活跃玩家
function playersDynamicController($scope, $http,T) {
	
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
						name: T.T('新玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newUser')).newArr
					},{
						name: T.T('老玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'oldUser')).newArr
					}]
			});	
		},
		"dataList_1_2_graph":function(data,xdata,ydata){
			$('.panel_1 .dataList_2 .graph').highcharts({
					title:{text:""},
					chart: {type: 'line'},
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
					legend: {
			            layout: 'horizontal',
			            align: 'right',
			            verticalAlign: 'top',
			            borderWidth: 0
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
						name: 'DAU',
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'dauAccount')).newArr
					},{
						name: 'WAU',
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'wauAccount')).newArr
					},{
						name: 'MAU',
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'mauAccount')).newArr
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
						name: T.T('MAU(账户)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_3.data,'mauAccount')).newArr
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
						name: 'DAU/MAU',
						data: ($scope.dataPushArr( $scope.panel_1.dataList_4.data,'dmauAccount')).newArr
					}]
			});	
		},
		"dataList_2_1_graph":function(data,xdata,ydata){
			$('.panel_2 .dataList_1 .graph').highcharts({
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
						categories: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'playingDays')).newArr,
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
						name: T.T('活跃玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_2.dataList_1.data,'count')).newArr
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
						name: T.T('活跃玩家(账户)'),
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
						name: T.T('活跃玩家地区'),
						data: data
					}]
			});		
		},
		"dataList_5_1_graph":function(data,xdata,ydata){
			$('.panel_5 .dataList_1 .graph').highcharts({
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
						name: T.T('活跃玩家渠道'),
						data: data
					}]
			});		
		},
		
		//按时登陆
		"dataList_6_1_graph":function(data,xdata,ydata){
			$('.panel_6 .dataList_1 .graph').highcharts({
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
						name: T.T('时间段'),
						data: ($scope.dataPushArr( $scope.panel_6.dataList_1.data,'hourdate')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					{
						name: T.T('老玩家'),
						data: ($scope.dataPushArr( $scope.panel_6.dataList_1.data,'newact')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},{
						name: T.T('新玩家登陆次数'),
						data: ($scope.dataPushArr( $scope.panel_6.dataList_1.data,'newUser')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					{
						name: T.T('老玩家登陆次数'),
						data: ($scope.dataPushArr( $scope.panel_6.dataList_1.data,'oldUser')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					]
			});
		},
		
		//按渠道登录
		"dataList_7_1_graph":function(data,xdata,ydata){
			$('.panel_7 .dataList_1 .graph').highcharts({
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
						name: T.T('渠道'),
						data: ($scope.dataPushArr( $scope.panel_7.dataList_1.data,'channelName')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					{
						name: T.T('老玩家'),
						data: ($scope.dataPushArr( $scope.panel_7.dataList_1.data,'newact')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},{
						name: T.T('新玩家登陆次数'),
						data: ($scope.dataPushArr( $scope.panel_7.dataList_1.data,'newUser')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					{
						name: T.T('老玩家登陆次数'),
						data: ($scope.dataPushArr( $scope.panel_7.dataList_1.data,'oldUser')).newArr,
						tooltip: {
							valueSuffix: ''
						}
					},
					]
			});
		},
	};
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
	
	
	$scope.panel_6={	
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
	
	$scope.panel_7={	
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
      
    //加载城市列表
  	$scope.selectActUserCityList=function(){
  		$http.post("players/activePlayers/selectActUserCityList.action",$scope.searchData).success(function(result){
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
	
	
	$scope.selectAUList = function(){
		//DAU
		$http.post("players/activePlayers/selectAUList.action",$scope.searchData).success(function(data, status, headers, config){
			 if(data.activeUser == undefined){
				 data.activeUser = {};
			 }
			 console.log(data.activeUser);
			 if(data.activeUser.DAU == undefined){
				 data.activeUser.DAU = {};
			 }
			 if(data.activeUser.WAU == undefined){
				 data.activeUser.WAU = {};
			 }
			 if(data.activeUser.MAU == undefined){
				 data.activeUser.MAU = {};
			 }
			 if(data.activeUser.DAU_DIVIDE_MAU == undefined){
				 data.activeUser.DAU_DIVIDE_MAU = {};
			 }
			 if(data.activeUser.DAU != undefined){
				 $scope.panel_1.dataList_1.AVG = data.activeUser.DAU.AVG;
				 $scope.panel_1.dataList_1.MD = data.activeUser.DAU.MD;
				 $scope.panel_1.dataList_1.data = data.activeUser.DAU.data;
			 }
			 if(data.activeUser.WAU != undefined){
				 $scope.panel_1.dataList_2.AVG = data.activeUser.WAU.AVG;
				 $scope.panel_1.dataList_2.MD = data.activeUser.WAU.MD;
				 $scope.panel_1.dataList_2.data = data.activeUser.WAU.data;
			 }
			 if(data.activeUser.MAU != undefined){
				 $scope.panel_1.dataList_3.AVG = data.activeUser.MAU.AVG;
				 $scope.panel_1.dataList_3.MD = data.activeUser.MAU.MD;
				 $scope.panel_1.dataList_3.data = data.activeUser.MAU.data;
			 }
			 if(data.activeUser.DAU_DIVIDE_MAU != undefined){
				 $scope.panel_1.dataList_4.AVG = data.activeUser.DAU_DIVIDE_MAU.AVG;
				 $scope.panel_1.dataList_4.MD = data.activeUser.DAU_DIVIDE_MAU.MD;
				 $scope.panel_1.dataList_4.data = data.activeUser.DAU_DIVIDE_MAU.data;
			 }
			 //
			 if($scope.panel_1.dataList_1.data!=null){
				 //dau存放到dataList_2中
				 for(var i=0;i<$scope.panel_1.dataList_1.data.length;i++){
					 $scope.panel_1.dataList_2.data[i].dauAccount=$scope.panel_1.dataList_1.data[i].count;
				 }
			 }
			 if($scope.panel_1.dataList_3.data!=null){
				//mauAccount存放到dataList_2中
				 for(var i=0;i<$scope.panel_1.dataList_3.data.length;i++){
					 $scope.panel_1.dataList_2.data[i].mauAccount=$scope.panel_1.dataList_3.data[i].mauAccount;
				 }
			 }
			 $scope.panel_1_change = true;
		});
	}
	
	$scope.selectActUserByTimeList=function(){
		$http.post("players/activePlayers/selectActUserByTimeList.action",$scope.searchData).success(function(data){
			console.log(data)
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_6.dataList_1.data=data.result;
			$scope.panel_6_change=true;
		})
	}
	
	$scope.selectActUserChannelList=function(){
		$http.post("players/activePlayers/selectActUserChannelList.action",$scope.searchData).success(function(data){
			console.log(data)
			if(data.status!= 1){
				data.result=[];
			}
			$scope.panel_7.dataList_1.data=data.result;
			$scope.panel_7_change=true;
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
	            'cityList':($scope.cityArr.length==0||$scope.showCheckAllText==true)?null:$scope.cityArr,
	        }
		$scope.selectAUList();
		$scope.selectActUserCityList();
		$http.post("testData/activeUserPlayingDays.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			 $scope.panel_2.dataList_1.data = data.activeUserPlayingDays;
			 $scope.panel_2_change = true;
		});
		
		$http.post("testData/activeUserLevel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			 $scope.panel_3.dataList_1.data = data.activeUserLevel;
			 $scope.panel_3_change = true;
		});
		$http.post("testData/activeUserRegion.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			 $scope.panel_4.dataList_1.data = data.actieUserRegion;
			 $scope.panel_4_change = true;
		});
		$http.post("testData/activeUserChannel.json?date="+$scope.mstatdate+"&channel="+$scope.mchannel+"&qf="+$scope.mareaclothing+"&t="+Math.random(),{}).success(function(data, status, headers, config){
			 $scope.panel_5.dataList_1.data = data.activeUserChannel;
			 $scope.panel_5_change = true;
		});
		$scope.selectActUserByTimeList();
		$scope.selectActUserChannelList();
	}
}