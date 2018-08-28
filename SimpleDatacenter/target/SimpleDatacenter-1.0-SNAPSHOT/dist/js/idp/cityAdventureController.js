$main.controller('cityAdventureController',cityAdventureController)
$main.config(function($stateProvider, $urlRouterProvider){
	$stateProvider.state('main.indpd.cityAdventure',{//主城奇遇
    	url:'/cityAdventure',
    	templateUrl:'template/idp/cityAdventure.html?T='+Math.random(),
    	controller:'cityAdventureController'
    })
	
})
/**
 * 主城奇遇
 */
function cityAdventureController($scope,$http,T){
	$scope.panel_1 = {
	        'model': 'table',
	        'gameList':[],
	        'channelList':[],
	        'dataList_1': {
	            'data': '',
	            'graphLimit': {
	                'sumLen': 0,
	                'now': -1,
	                'max': 10,
	                'count': 0
	            },
	            'limit': {
	                'sumLen': 0,
	                'now': 0,
	                'max': 10,
	                'count': 0
	            },
	            'dataLimit': '',
	            'orderBy': {
	                's': 'id',
	                't': ''
	            }
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
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	$scope.showPanel=function(data){
		console.log(data);
		//var index = $(this).index();
		//data.details = [{id:1,statdate:'2016-01-10'},{id:2,statdate:'2016-11-04'}];
		$scope.getDataDetail(data);
		//
		data.show_panel=true;
		data.panel_change=false;
	}
	
	$scope.getDataDetail = function(row){
		//$scope.postData2.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    //$scope.postData2.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		$scope.postData2.startdate = $scope.postData2.enddate = row.statdate;
 	    $scope.postData2.gameid = $scope.$Params.gameid;
 	    $scope.postData2.areaid=row.areaid;
 	    $scope.postData2.country=row.country;
 	    $scope.postData2.channelid=row.channelid;
 	    $scope.postData2.checktype1=row.type;
 	   $scope.postData2.checktype2=row.jobs;
 	    
 	   //
 	   $http.post("summary/selectZcPalyTypeCombatDetailList.action",$scope.postData2).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			/*console.log(row)
			console.log(data)*/
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
	$scope.selectZcCombatList=function(){
		$http.post("summary/selectZcCombatList.action",$scope.searchData).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
	//详情参数
	$scope.postData2={
			startdate:"",
			enddate:"",
			gameid :"",
			areaid:"",
			country:"",
			channelid:"",
			checktype1:""
	    }
	$scope.getAllData=function(){
		$scope.searchData={
	            'gameid': $scope.$Params.gameid,
	            'startdate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate,
	            'enddate': $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate,
	            'areas':$scope.mareaclothing,
	            'channelList':$scope.mchannel,
	            'versionList':$scope.mversion,
				'languageList':$scope.mlang,
	        }
		console.log($scope.searchData);
		$scope.selectZcCombatList();
	}
}