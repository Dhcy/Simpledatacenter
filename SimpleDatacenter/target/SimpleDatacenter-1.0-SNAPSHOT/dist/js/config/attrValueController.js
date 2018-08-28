//静态值
function attrValueController($scope,$http,$stateParams,T){

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
				't': ''
			}
		}
	}
	$scope.panel_2 = {
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
					't': ''
				}
			}
		}
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
		$scope.panel_2.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_2.limit.sumLen/$scope.panel_2.dataList_1.limit.max)-1;
	}
	
	$scope.postData = {
			checktype1 :'',
			checktype2:''
	}
	//查询静态值
	$scope.selectAttrData=function(){
		if($scope.postData.checktype1==undefined ||$scope.postData.checktype1==''){
			alert(T.T("静态编码不能为空"));
			return;
		}
		$scope.searchAttrValueList();
	}
	//刷新静态值
	$scope.refreshAttrData=function(){
		if($scope.postData.checktype1==undefined ||$scope.postData.checktype1==''){
			alert(T.T("静态编码不能为空"));
			return;
		}
		$http.post("attrValue/refreshAttrValue.action",$scope.postData).success(function(data){
        	if(data.status==1){
        		$scope.searchAttrValueList();
        	}else{
        		alert(data.message);
        	}
		})
	}
	//查询静态数据
	$scope.searchAttrValueList = function () {
        $http.post("attrValue/selectAttrValueListByCode.action",$scope.postData).success(function(data){
        	if(data.attrValues==undefined ||data.attrValues==null){
        		data.attrValues=[];
        	}
		 	$scope.panel_1.dataList_1.data=data.attrValues;
		})
	};
	
	//获取属性值
	$scope.getValueByAttr=function(){
		$http.post("attrValue/getAttrValueFromConfig.action",$scope.postData).success(function(data){
        	if(data==undefined ||data==null){
        		data=[];
        	}
		 	$scope.panel_2.dataList_1.data=data;
		})
	}
	//查询
	$scope.selectValueByAttr=function(){
		if($scope.postData.checktype2==undefined ||$scope.postData.checktype2==''){
			alert(T.T("属性名不能为空"));
			return;
		}
		$scope.getValueByAttr();
	}
	//刷新配置文件数据
	$scope.refreshConfigData=function(){
		$http.post("attrValue/refreshConfigData.action",{}).success(function(data){
        	if(data.status==1){
        		alert(T.T("刷新成功！"));
        	}else{
        		alert(T.T("刷新失败！"));
        	}
		})
	}
    
}