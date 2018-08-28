/**
 * 运营报表
 * @param $scope
 * @param $http
 * @return
 */
function reportController($scope,$http,T){
	
	
	//基础数据
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
	
	//付费数据
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
			'orderBy':{'s':'statdate','t':''}
		},
	};
	
	//累计数据
	$scope.panel_3={
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
			'orderBy':{'s':'statdate','t':''}
		},
	};
	$scope.panel_4={
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
				'th':{
					'1':T.T("周"),'2':T.T("月")
				}
			},
		};
	$scope.isShowDay=true;//是否显示日报
	$scope.isShowWeekOrMonthExport=2;//0:周;1:月
	$scope.tabsbtn=0;
	$scope.changeTabsBtn=function(i){
        $scope.tabsbtn=i;
        $scope.getAllData(i);
    }
	
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
	$scope.$watch("mchange.date",function(nv,ov){
			if($scope.mchange.date){
				$scope.getAllData($scope.tabsbtn);
				$scope.mchange.date=false;
			}
		});
	
	$scope.$watch("mchange.channelQf",function(nv,ov){
			if($scope.mchange.channelQf){
				$scope.getAllData($scope.tabsbtn);
				$scope.mchange.channelQf=false;
			}
		});
    //周报
	$scope.selectWeekReportList=function(){
		$http.post("index/report/selectWeekBaseDataList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
			for(var i=0;i<data.length;i++){
				if(i==1){
					data[i]['week_total_device_Cnt']=data[i]['week_total_device_Cnt']+"%";
					data[i]['week_new_play_count']=data[i]['week_new_play_count']+"%";
					data[i]['week_act_play_count']=data[i]['week_act_play_count']+"%";
					data[i]['week_avg_game_time']=data[i]['week_avg_game_time']+"%";
					data[i]['week_avg_daily_game_time']=data[i]['week_avg_daily_game_time']+"%";
					data[i]['week_avg_game_count']=data[i]['week_avg_game_count']+"%";
					data[i]['week_day2ret']=data[i]['week_day2ret']+"%";
					data[i]['week_lostcnt']=data[i]['week_lostcnt']+"%";
					data[i]['week_lostrate']=data[i]['week_lostrate']+"%";
					data[i]['week_returncnt']=data[i]['week_returncnt']+"%";
					data[i]['week_income']=data[i]['week_income']+"%";
					data[i]['week_recharge_cnt']=data[i]['week_recharge_cnt']+"%";
					data[i]['week_pay_rate']=data[i]['week_pay_rate']+"%";
					data[i]['week_arpu']=data[i]['week_arpu']+"%";
				}
			}
            $scope.panel_4.dataList_1.data=data
            $scope.panel_4_change=true;
		})
	}
	
	//月报
	$scope.selectMonthReportList=function(){
		$http.post("index/report/selectMonthBaseDataList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
			for(var i=0;i<data.length;i++){
				if(i==1){
					data[i]['week_total_device_Cnt']=data[i]['week_total_device_Cnt']+"%";
					data[i]['week_new_play_count']=data[i]['week_new_play_count']+"%";
					data[i]['week_act_play_count']=data[i]['week_act_play_count']+"%";
					data[i]['week_avg_game_time']=data[i]['week_avg_game_time']+"%";
					data[i]['week_avg_daily_game_time']=data[i]['week_avg_daily_game_time']+"%";
					data[i]['week_avg_game_count']=data[i]['week_avg_game_count']+"%";
					data[i]['week_day2ret']=data[i]['week_day2ret']+"%";
					data[i]['week_lostcnt']=data[i]['week_lostcnt']+"%";
					data[i]['week_lostrate']=data[i]['week_lostrate']+"%";
					data[i]['week_returncnt']=data[i]['week_returncnt']+"%";
					data[i]['week_income']=data[i]['week_income']+"%";
					data[i]['week_recharge_cnt']=data[i]['week_recharge_cnt']+"%";
					data[i]['week_pay_rate']=data[i]['week_pay_rate']+"%";
					data[i]['week_arpu']=data[i]['week_arpu']+"%";
				}
			}
            $scope.panel_4.dataList_1.data=data
            $scope.panel_4_change=true;
		})
	}
	
	$scope.selectDailyReportList = function(){        
        //基础数据
		$http.post("index/report/selectDailyReportList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_1.dataList_1.data=data
            $scope.panel_1_change=true;
		})
		//付费数据
		$http.post("index/report/selectPayDataList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_2.dataList_1.data=data
            $scope.panel_2_change=true;
		})
		//累计数据
		$http.post("index/report/selectTotalDataList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined||data==null){
				data =[]
			}
            $scope.panel_3.dataList_1.data=data
            $scope.panel_3_change=true;
		})
		
	}
    
    //查询条件
    $scope.searchData={};
    
	$scope.getAllData=function(i){
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
		  if(i == undefined || i==0){
			  $scope.selectDailyReportList();
			  $scope.isShowDay=true;
		  } else if(i==1){
			  console.log("周报");
			  $scope.selectWeekReportList(); 
			  $scope.isShowDay=false;
			  $scope.searchData.checktype1=0;
		  }else if(i==2){
			  console.log("月报");
			  $scope.selectMonthReportList();
			  $scope.isShowDay=false;
			  $scope.searchData.checktype1=1;
		  }
}
}