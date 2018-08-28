//新增玩家
function playersNewController($scope,$http,T){
	$scope.graphfun={
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
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'devices')).newArr
					}, {
						name: T.T('新增玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'newPlayers')).newArr
					},
					{
						name: T.T('首次创角数'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'regCnt')).newArr,
					},
					{
						name: T.T('玩家转化率'),
						data: ($scope.dataPushArr( $scope.panel_1.dataList_1.data,'playerConv')).newArr,
						 tooltip: {
				            valueSuffix: '%'
				        }
					}]
			});			
		},
		'dataList_4_1_graph':function(data,xdata,ydata){
			$('.panel_4 .dataList_1 .graph').highcharts({
					title:{text:""},
					chart: {type: 'area'},
					colors: ["#6DC5FD", "#b2de54", "#AA4643", "#80699B", "#3D96AE", "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"],
					xAxis: {
						type: 'linear',
						tickmarkPlacement: 'on',
						categories: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'hourDateDesc')).newArr			
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
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'devices')).newArr
					}, {
						name: T.T('新增玩家(账户)'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'newUser')).newArr
					},
					{
						name: T.T('新增角色'),
						data: ($scope.dataPushArr( $scope.panel_4.dataList_1.data,'newPlayers')).newArr,
					}
					]
			});			
		},
		/*'dataList_1_2_graph':function(data,xdata,ydata){
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
						name: '玩家转化(%)',
						data: ($scope.dataPushArr( $scope.panel_1.dataList_2.data,'playerConv')).newArr
					}]
			});		
		},*/
		'dataList_2_graph':function(data,xdata,ydata){
		if(!data)return;
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
						name: T.T('新增设备(台)'),
						data: data
					}]
			});		
		},
		'dataList_3_graph':function(data,xdata,ydata){
		if(!data)return;
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
						name: T.T('新增玩家(人)'),
						data: ($scope.dataPushArr( $scope.panel_3.dataList_1.data,'newPlayers')).newArr
					}]
			});		
		}
	}
	
	
	$scope.panel_1={
		'model':'table',
		'dataList_1':{
			"deviceSUM":0,
			"accountSUM":0,
			"devicAVG":0,
			"accountAVG":0,
			'data':'',
			'limit':{
				'sumLen':0,
				'now':0,
				'max':6,
				'count':0
			},
			'dataLimit':'',
			'orderBy':{'s':'statdate','t':''}
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
			'orderBy':{'s':'statdate','t':''}	
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
			'orderBy':{'s':'statdate','t':''}	
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
				"deviceSUM":0,
				"accountSUM":0,
				"devicAVG":0,
				"accountAVG":0,
				'data':'',
				'limit':{
					'sumLen':0,
					'now':0,
					'max':24,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'statdate','t':''}
			}
		};
	$scope.panel_1_change=false;
	$scope.panel_4_change=false;
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData();
				$scope.mchange.date=false;
			}
		});
	
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.callbackChannelQf();	
				$scope.getAllData();
				$scope.mchange.channelQf=false;
			}
		});
	
	$scope.selectNewPlayerList = function(){
		//新增激活和账户
		$http.post("players/newPlayers/selectNewPlayerList.action",$scope.searchData).success(function(data, status, headers, config){
			if(data.newAccounts != undefined){
				$scope.panel_1.dataList_1.deviceSUM=data.newAccounts.deviceSUM;
				$scope.panel_1.dataList_1.accountSUM=data.newAccounts.accountSUM;
				$scope.panel_1.dataList_1.devicAVG=data.newAccounts.devicAVG;
				$scope.panel_1.dataList_1.accountAVG=data.newAccounts.accountAVG;
				$scope.panel_1.dataList_1.data=data.newAccounts.data;
			}
			//$scope.panel_1.dataList_2.data=data.userTransform;
			$scope.panel_2.dataList_1.data=data.accountscount;
			$scope.panel_3.dataList_1.data=data.channelList;
			
			$scope.panel_1_change=true;
		});
	}
	$scope.game={};	
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
	$scope.getGame=function(){
		$http.post("players/newPlayers/getGame.action",$scope.searchData).success(function(data, status, headers, config){
			if(data!=undefined||data!=null){
				$scope.game=data;
			}
		});
	}
	$scope.selectCityList=function(){
		$http.post("players/newPlayers/selectNewPlayerCityList.action",$scope.searchData).success(function(data, status, headers, config){
			console.info("区域:");
			console.info(data);
			$scope.cityData.splice(0,$scope.cityData.length);//清空
			$scope.cityData=data;
			if($scope.cityData.length!=0){
				for (var i=0;i<$scope.cityData.length;i++){
					var id=$scope.cityData[i].city;
					$scope.cityData[i].id=id;
				}
			}
		});
	}
	//新增玩家(按小时)
	$scope.selectNewPlayerListOfHour=function(){
		$http.post("players/newPlayers/selectNewPlayerOfHour.action",$scope.searchData).success(function(data, status, headers, config){
			if(data.newPlayerListOfHour==undefined){
				data.newPlayerListOfHour=[];
			}
			$scope.panel_4.dataList_1.data=data.newPlayerListOfHour;
			$scope.panel_4_change=true;
			
		});
	}
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
//	            'checktype1':$scope.cityCode
	            'cityList':($scope.cityArr.length==0||$scope.showCheckAllText==true)?null:$scope.cityArr
	        }
		$scope.selectNewPlayerList();
		$scope.getGame();
		//$scope.selectNewPlayerOfChannelList();
		$scope.selectCityList();
		$scope.selectNewPlayerListOfHour();
	}
}