$main.controller('goodsMaterialsController',goodsMaterialsController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.goodsMaterials',{//物资
    	url:'/goodsMaterials',
    	templateUrl:'template/idp/goodsMaterials.html?T='+Math.random(),
    	controller:'goodsMaterialsController'
    })
	
})
/**
 *物资
 * @param $scope
 * @param $http
 * @return
 */
function goodsMaterialsController($scope,$http,T){
	
	
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
			},
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
			},
	};
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
	$scope.panel_4_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.changetype2=function(i){
		$scope.panel_2.dataList_1.limit.max=i;
		$scope.panel_2.dataList_1.limit.now=0;
		$scope.panel_2.dataList_1.limit.sumLen=($scope.panel_2.dataList_1.data).length;
		$scope.panel_2.dataList_1.limit.count=Math.ceil($scope.panel_2.dataList_1.limit.sumLen/$scope.panel_2.dataList_1.limit.max)-1;
	}
	$scope.changetype3=function(i){
		$scope.panel_3.dataList_1.limit.max=i;
		$scope.panel_3.dataList_1.limit.now=0;
		$scope.panel_3.dataList_1.limit.sumLen=($scope.panel_3.dataList_1.data).length;
		$scope.panel_3.dataList_1.limit.count=Math.ceil($scope.panel_3.dataList_1.limit.sumLen/$scope.panel_3.dataList_1.limit.max)-1;
	}
	$scope.changetype4=function(i){
		$scope.panel_4.dataList_1.limit.max=i;
		$scope.panel_4.dataList_1.limit.now=0;
		$scope.panel_4.dataList_1.limit.sumLen=($scope.panel_4.dataList_1.data).length;
		$scope.panel_4.dataList_1.limit.count=Math.ceil($scope.panel_4.dataList_1.limit.sumLen/$scope.panel_4.dataList_1.limit.max)-1;
	}
	
$scope.showPanel=function(data,$event,type){
		
		data.show_panel=true;
		data.panel_change=false;
		data.showtype=type;
		$scope.selectGoodsDetailData(type,data);
		setTimeout(function(){			
			data.panel_change=true;
			$($event.target).parent().parent().parent().find(".panel-act .nav .active").click();
		},10);	
	}
$scope.selectGoodsDetailData = function(type,rowdata){
	$scope.searchData.checktype1 = rowdata.statType;
	switch(type){
	case "limitTimeDiscount":
		$scope.selectLimitTimeDiscountGoodsList(rowdata);
		break;
//	case "dailyGoods":
////		$scope.selectDailyGoodsList(rowdata);
//		break;
	case "campGoods":
		$scope.selectCampGoodsList(rowdata);
		break;
	}
}
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
	
	$scope.selectGoodsMaterialsList = function(){        
		$http.post("summary/selectGoodsSummaryList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
            $scope.panel_1.dataList_1.data=data;
            $scope.panel_1_change=true;
		})
	};
	
	$scope.selectLimitTimeDiscountGoodsList = function(rowdata){
		$scope.searchData1={
				'gameid': $scope.$Params.gameid,
	            'startdate':rowdata.statdate,
	            'enddate': rowdata.statdate,
	            'channelid':rowdata.channelid,
	            'areaid':rowdata.areaid,
				'checktype1':1
	        }
		$http.post("summary/selectLimitTimeDiscountGoodsList.action",$scope.searchData1).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.limitTimeDiscount==undefined){
				rowdata.limitTimeDiscount={
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
				};
			}
			rowdata.limitTimeDiscount.dataList_1.data = data;
			rowdata.panel_change=true;
//            $scope.panel_2.dataList_1.data=data;
//            $scope.panel_2_change=true;
		})
	};
//	$scope.selectDailyGoodsList = function(rowdata){
//		$scope.searchData2={
//	            'gameid': $scope.$Params.gameid,
//	            'startdate':rowdata.statdate,
//	            'enddate': rowdata.statdate,
//	            'channelid':rowdata.channelid,
//	            'areaid':rowdata.areaid,
//				'checktype2':2
//				
//	        }
//		$http.post("summary/selectDailySuppGoodsList.action",$scope.searchData2).success(function(data){
//			if(data== undefined||data==null){
//				data=[]
//			}
//			if(rowdata.dailyGoods==undefined){
//				rowdata.dailyGoods={
//						'model':'table',
//						'dataList_1':{
//							'data':'',
//							'graphLimit':{
//								'sumLen':0,
//								'now':-1,
//								'max':10,
//								'count':0				
//							},
//							'limit':{
//								'sumLen':0,
//								'now':0,
//								'max':10,
//								'count':0
//							},
//							'dataLimit':'',
//							'orderBy':{'s':'','t':''}	
//						},
//				};
//			}
//			rowdata.dailyGoods.dataList_1.data = data;
//			rowdata.panel_change=true;
////            $scope.panel_3.dataList_1.data=data;
////            $scope.panel_3_change=true;
//		})
//	};
	$scope.selectCampGoodsList = function(rowdata){
		$scope.searchData3={
				'gameid': $scope.$Params.gameid,
	            'startdate':rowdata.statdate,
	            'enddate': rowdata.statdate,
	            'channelid':rowdata.channelid,
	            'areaid':rowdata.areaid,
				'checktype3':3
	        }
		$http.post("summary/selectCampSuppGoodsList.action",$scope.searchData3).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.campGoods==undefined){
				rowdata.campGoods={
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
				};
			}
			rowdata.campGoods.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
    
	$scope.getAllData=function(i){
		$scope.searchData={
            'gameid': $scope.$Params.gameid,
            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
            'areas':$scope.mareaclothing,
            'channelList':$scope.mchannel,
            'versionList':$scope.mversion,
			'languageList':$scope.mlang,
        }
		$scope.selectGoodsMaterialsList();
}
}