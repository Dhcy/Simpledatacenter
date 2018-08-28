/**
 * 渠道账号信息
 * @param $scope
 * @param $http
 * @return
 */
function channelCompanyController($scope,$http,T){
	
	$scope.taxTypeList=[{'value':'-1','name':T.T("全部")},{'value':T.T('普通'),'name':T.T("普通")},{'value':T.T('小规模纳税人'),'name':T.T("小规模纳税人")},{'value':T.T('一般纳税人'),'name':T.T("一般纳税人")}];
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
    $scope.postData={
    	channelId : -1,
    	taxType:'-1'
    }
    $scope.saveData=function(data){
        var blob = new Blob(data,{"type":"text/html"});
//        var blob = new Blob(data);

		var a = document.createElement('a');
		a.href = window.URL.createObjectURL(blob);
		a.donwload = 'test.txt';
		a.textContent = 'Download Hello World';
		a.target="_blank";
		a.click();
    }
    
    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    //渠道
    $scope.getChannelList=function(){
    	$http.post("channel/channelList.action").success(function(data){
    		var dat={id:-1,channelName:T.T("全部")};   
            data.unshift(dat);
    		$scope.panel_1.channelList = data;
    	});
    }
    
	$scope.getData=function(){
		console.log($scope.postData)
		$http.post("balanceplat/selectChannelCompanyInfoList.action",$scope.postData).success(function(data){
			console.log(data);
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
	
	$scope.delChannelCompany=function(data,name){
		console.log(data)
		$http.post("balanceplat/deleteChannelCompanyInfo.action",{"id" : data}).success(function(data){
			if(data.status == 1){
				$scope.getData();
			}
    	});
	}
	
	$scope.getChannelList();
        
}