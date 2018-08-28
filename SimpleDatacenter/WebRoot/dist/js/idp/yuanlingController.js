$main.controller('yuanlingController',yuanlingController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.yuanlingcount',{
    	url:'/yuanlingcount',
    	templateUrl:'template/idp/yuanling.html?T='+Math.random(),
    	controller:'yuanlingController'
    })
	
})
/**
 * 元灵
 * @param $scope
 * @param $http
 * @return
 */
function yuanlingController($scope,$http,T){
	
	
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
	
	$scope.selectYuanlingStatList = function(){        
		$http.post("summary/selectYlStatList.action",$scope.searchData).success(function(data){
			console.log("元灵");
			console.log(data);
			if(data.ylNameList == undefined||data.ylNameList==null){
				data.ylNameList =[]
			}
			if(data.ylSlcList == undefined||data.ylSlcList==null){
				data.ylSlcList =[]
			}
			if(data.ylLevelList == undefined||data.ylLevelList==null){
				data.ylLevelList =[]
			}
            $scope.panel_1.dataList_1.data=data.ylNameList;
            $scope.panel_2.dataList_1.data=data.ylSlcList;
            $scope.panel_3.dataList_1.data=data.ylLevelList;
            $scope.panel_1_change=true;
            $scope.panel_2_change=true;
            $scope.panel_3_change=true;
		})
		
	}
	//元灵单次抽取
	$scope.selectYLSimExtractCntList = function(){        
		$http.post("summary/selectYlSimExtractCntList.action",$scope.searchData).success(function(data){
			console.log("元灵单次抽取");
			console.log(data);
			if(data==undefined||data==null){
				 data=[];
			}
			$scope.panel_4.dataList_1.data=data;
            $scope.panel_4_change=true;
		})
		
	}
	//显示详情列表
	$scope.showPanel=function(data){
		console.log(data);
		$scope.getDataDetail(data);
		//
		data.show_panel=true;
		data.panel_change=false;
	}
	//元灵详情
	$scope.getDataDetail = function(row){
		//$scope.postData2.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    //$scope.postData2.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		$scope.postData2.startdate = $scope.postData2.enddate = row.statdate;//日期
 	    $scope.postData2.gameid =$scope.$Params.gameid;
 	   $scope.postData2.channelid=row.channelid;//渠道id
 	   $scope.postData2.checktype1=row.ylName;//元灵
 	   //
 	   $http.post("summary/selectYlDetailList.action",$scope.postData2).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			row.limit={
	                'sumLen': 0,
	                'now': 0,
	                'max': 10,
	                'count': 0
	            };
			row.dataLimt="";
			row.orderBy={
                's': 'id',
                't': ''
            };
			row.details = data;
			row.show_panel=true;
			row.panel_change=false;
 	   });
		
	}
	
	$scope.postData2={
			startdate:"",
			enddate:"",
			gameid :0,
			channelid:0,
			checktype1:''
	    }
    
    //查询条件
    $scope.searchData={};
    
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
		$scope.selectYuanlingStatList();
		$scope.selectYLSimExtractCntList();
}
}