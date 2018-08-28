function gamelanguageController($scope,$http,$stateParams,T){
	//$scope.searchData={name:"",realname:"",role:1};
	$scope.panel_1 = {
		'model': 'table',
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
				't': 'versionName'
			}
		}
	}
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	
	$scope.languageData=[];
	$scope.postData={
		gameid:""
	}
	$scope.getAllData = function () {
		console.log($scope.postData)
		$http.post("game/getGameLanguageList.action",$scope.postData).success(function(data){
			$scope.languageData = data;
		});
		
	};
	
	$http.get("getUserGameList.action").success(function(data){
		
		$scope.gameList = data;
		$scope.postData.gameid = data[0].id;
		$scope.getAllData();
	});
	
	$scope.delGameLanguage=function(id,attr_id,attr_desc){
		$scope.dialogopen({
			msg:T.T("确认删除?"),
			btn:{ok:true},
			fn:{
				ok:function(){
					$http.post("game/deleteGameLanguage.action",{"id":id,"attr_id":attr_id,"attr_desc":attr_desc}).success(function(data){
						$scope.getAllData();	
						$scope.dialogopen({
								msg:T.T("删除成功"),
								btn:{close:false},
								fn:{
									ok:function(){}
								}
							})
					});
				}
			}
		})
	}
	
}