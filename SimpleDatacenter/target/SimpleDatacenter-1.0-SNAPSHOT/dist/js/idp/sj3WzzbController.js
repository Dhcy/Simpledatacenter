$main.controller('sj3WzzbController',sj3WzzbController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.sj3Wzzb',{//物资
    	url:'/sj3Wzzb',
    	templateUrl:'template/idp/sj3Wzzb.html?T='+Math.random(),
    	controller:'sj3WzzbController'
    })
	
})
/**
 *世界3王者争霸
 * @param $scope
 * @param $http
 * @return
 */
function sj3WzzbController($scope,$http,T){
	
	
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
	$scope.panel_1_change=false;
	$scope.panel_2_change=false;
	$scope.panel_3_change=false;
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
	
$scope.showPanel=function(data,$event,type){
		
		data.show_panel=true;
		data.panel_change=false;
		data.showtype=type;
		$scope.selectWzzbEquipDetailData(type,data);
		setTimeout(function(){			
			data.panel_change=true;
			$($event.target).parent().parent().parent().find(".panel-act .nav .active").click();
		},10);	
	}
//王者争霸装备详情
$scope.selectWzzbEquipDetailData = function(type,rowdata){
	switch(type){
	case "jineng":
		$scope.selectJiNengDetailList(rowdata);
		break;
	case "mowu":
		$scope.selectMoWuDetailList(rowdata);
		break;
	case "linghu":
		$scope.selectLingHuDetailList(rowdata);
		break;
	case "MaxJoinActor":
		$scope.selectMaxJoinActorDetailList(rowdata);
		break;
	case "wzzbLs":
		$scope.selectWzzbLsDetailList(rowdata);
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
	//王者争霸
	$scope.selectWzzbList = function(){        
		$http.post("summary/selectWzzbList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
            $scope.panel_1.dataList_1.data=data;
            $scope.panel_1_change=true;
		})
	};
	//技能
	$scope.selectJiNengDetailList = function(rowdata){
		$scope.postData={
				'startdate':rowdata.statdate,
				'enddate':rowdata.statdate,
				'gameid':$scope.$Params.gameid,
				'areaid':rowdata.areaid,
				'checktype1':1,
				'checktype2':rowdata.jobs
		};
		$http.post("summary/selectWzzbEquipDetailList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.jiNengDetail==undefined){
				rowdata.jiNengDetail={
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
			rowdata.jiNengDetail.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
	//魔物
	$scope.selectMoWuDetailList = function(rowdata){
		$scope.postData={
				'startdate':rowdata.statdate,
				'enddate':rowdata.statdate,
				'gameid':$scope.$Params.gameid,
				'areaid':rowdata.areaid,
				'checktype1':2,
				'checktype2':rowdata.jobs
		};
		$http.post("summary/selectWzzbEquipDetailList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.moWuDetail==undefined){
				rowdata.moWuDetail={
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
			rowdata.moWuDetail.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
	
	//灵魂
	$scope.selectLingHuDetailList = function(rowdata){
		$scope.postData={
				'startdate':rowdata.statdate,
				'enddate':rowdata.statdate,
				'gameid':$scope.$Params.gameid,
				'areaid':rowdata.areaid,
				'checktype1':3,
				'checktype2':rowdata.jobs
		};
		$http.post("summary/selectWzzbEquipDetailList.action",$scope.postData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.lingHuDetail==undefined){
				rowdata.lingHuDetail={
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
			rowdata.lingHuDetail.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
	
	//战斗次数最多前20玩家
	$scope.selectMaxJoinActorDetailList = function(rowdata){
		$scope.postData2={
				'startdate':rowdata.statdate,
				'enddate':rowdata.statdate,
				'gameid':$scope.$Params.gameid,
				'areaid':rowdata.areaid,
				'checktype2':rowdata.jobs
		};
		$http.post("summary/selectMaxJoinActorDetailList.action",$scope.postData2).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.maxJoinActorDetail==undefined){
				rowdata.maxJoinActorDetail={
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
			rowdata.maxJoinActorDetail.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
	
	//外围赛连胜
	$scope.selectWzzbLsDetailList = function(rowdata){
		$scope.postData2={
				'startdate':rowdata.statdate,
				'enddate':rowdata.statdate,
				'gameid':$scope.$Params.gameid,
				'areaid':rowdata.areaid,
				'checktype2':rowdata.jobs
		};
		$http.post("summary/selectWzzbLsList.action",$scope.postData2).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
			if(rowdata.wzzbLsDetail==undefined){
				rowdata.wzzbLsDetail={
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
			rowdata.wzzbLsDetail.dataList_1.data = data;
			rowdata.panel_change=true;
		})
	};
	//押注
	$scope.selectWzzbBetList = function(){        
		$http.post("summary/selectWzzbBetList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
            $scope.panel_2.dataList_1.data=data;
            $scope.panel_2_change=true;
		})
	};
	//押注档次
	$scope.selectWzzbBetLevelList = function(){        
		$http.post("summary/selectWzzbBetLevelList.action",$scope.searchData).success(function(data){
			if(data== undefined||data==null){
				data=[]
			}
            $scope.panel_3.dataList_1.data=data;
            $scope.panel_3_change=true;
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
		$scope.selectWzzbList();
		$scope.selectWzzbBetList();
		$scope.selectWzzbBetLevelList();
}
}