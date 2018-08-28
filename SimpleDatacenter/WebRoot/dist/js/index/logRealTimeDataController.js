/**
 * 日志实时数据
 * @param $scope
 * @param $http
 * @return
 */
function logRealTimeDataController($scope,$http,$timeout,$uibModal,T){
	
	
	$scope.panel_1={
			'model':'table',
			'dataList_1':{
				'data':'',
				'graphLimit':{
					'sumLen':0,
					'now':-1,
					'max':100,
					'count':0				
				},
				'limit':{
					'sumLen':0,
					'now':0,
					'max':100,
					'count':0
				},
				'dataLimit':'',
				'orderBy':{'s':'','t':''}	
			},
	};
	$scope.cacheData=[];
	$scope.newData=[];
	$scope.otherArr=[];
	$scope.panel_1_change=false;
	$scope.changetype=function(i){
		$scope.panel_1.dataList_1.limit.max=i;
		$scope.panel_1.dataList_1.limit.now=0;
		$scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
		$scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
	}
	
	$scope.showDetail=function(d){
		$scope.dialogopen({
            msg:d,
            size: "lg",
            btn:{close:true},
            fn:{
                ok:function(){
                }
            }
        })
	}
	$scope.showTable=function(data){
		$scope.tableData=data;
		$scope.html='<div class="modal-header"><h3 class="modal-title">内容</h3></div><div class="modal-body"><table class="table table-bordered text-center"><tr class="tableTr"><th class="text-center">列名</th><th class="text-center">列值</th></tr>';
			for(var i=0;i<$scope.tableData.length;i++){
				var tableRow=$scope.tableData[i];
				$scope.html+="<tr><td>"+tableRow.attr_desc+"</td><td>"+tableRow.attr_value+"</td></tr>";
			}
			$scope.html+='</div></table><div class="modal-footer"><button class="btn btn-primary btn-sm" type="button" ng-click="fn.ok();" ng-show="btn.ok">{{text.ok | translate}}</button><button class="btn btn-warning btn-sm" type="button" ng-click="fn.close()" ng-show="btn.close">{{text.close | translate }}</button></div>';
			var option = {
				title : "",
				msg : "",
				size : 'sm' ,
				btn : {
					ok : true,
					close : true
				},
				text : {
					ok: T.T("确定"),
					close: T.T("关闭")
				},
				fn : {
					ok: function () {
					},
					close: function () {
					},
					must : function (){}
				}
			}
		//弹出表格
		 var modalInstance = $uibModal.open({
				animation: true,
				template: $scope.html,
				controller: function ($scope,$uibModalInstance) {
					$scope.o = option;
					$scope.title = option.title;
					$scope.msg = option.msg;
					$scope.btn = {
						ok: option.btn.ok,
						close: option.btn.close
					}
					$scope.text = {
						ok: option.text.ok,
						close: option.text.close
					}
					$scope.fn = {
						ok: function(){
							option.fn.ok();
							$scope.fn.close();
						},
						close: function () {
							$uibModalInstance.close();
						}
					}
					//$uibModalInstance.dismiss(option.fn.must);
					modalInstance.result.then(function () {
					  option.fn.must();
					}, function () {
					  option.fn.must();
					});
				},
				size: option.size,
				resolve: {

				}
			});
	}
	
    //获取日志反馈的实时数据
	$scope.d=false;
	$scope.selectRealTimeDataList=function(){
		if($scope.d)return;
		$scope.d=true;
		//没缓存数据
		if($scope.cacheData.length==0){
			$http.post("logPlat/getLogRealTimeDataList.action",$scope.searchData).success(function(data){
				if(data!=undefined||data!=null){
					$scope.cacheData=data;
				}
	            $scope.panel_1.dataList_1.data=$scope.cacheData;
	            $scope.panel_1_change=true;
	            $scope.d=false;
			});
		}else{
			//非点击查询
			if(!$scope.isClickQueryBtn){
				//传logkey
				$scope.searchData.rowid=$scope.cacheData[0].rowid;//缓存数组第一条数据的key
			}else{
				$scope.searchData.rowid='';
			}
			//获取最近一条数据后的新数据
			$http.post("logPlat/getLogRealTimeDataList.action",$scope.searchData).success(function(data){
				var startTime=new Date().getTime(); 
				if(data!=undefined||data!=null){
					$scope.newData=data;
				}
				if($scope.newData.length==0){
//					$scope.panel_1.dataList_1.data=$scope.cacheData;
					if($scope.isClickQueryBtn){
						//点击触发清空
						$scope.panel_1.dataList_1.data=[];
						$scope.cacheData=[];
					}else{
						//定时触发取缓存
						$scope.panel_1.dataList_1.data=$scope.cacheData;
					}
				}else{
					if($scope.isClickQueryBtn){
						//点击触发
						$scope.panel_1.dataList_1.data=$scope.newData;
						$scope.cacheData=$scope.panel_1.dataList_1.data;
					}else{
						//定时触发时
						if($scope.newData.length>=1000){
							$scope.panel_1.dataList_1.data=$scope.newData;
							$scope.cacheData=$scope.panel_1.dataList_1.data;
						}else{
							var n=1000-$scope.newData.length;
							for(var i=0;i<n;i++){
								$scope.otherArr[i]=$scope.cacheData[i];
							}
							$scope.panel_1.dataList_1.data=$scope.newData.concat($scope.otherArr);
							$scope.cacheData=$scope.panel_1.dataList_1.data;
						}
					}
				}
	            $scope.panel_1_change=true;
	            $scope.d=false;
	            $scope.isClickQueryBtn=false;
			});
		}
		 $scope.act=$timeout($scope.selectRealTimeDataList,5*60*1000);
	}
	 $scope.isFourNumStartOne=function(obj){
	    	var result=false;
	    	//var reg=/^1\d{3}$/;
	    	var reg=/^[1-9]\d{3}$/;
	    	if(!reg.test(obj)){
	    	result=false;
	    	}else{
	    		result=true;
	    	}
	    	return result;
	    }
    //查询条件
    $scope.searchData={
			gameid:'',
			limit:1000,
			startDate:new Date((new Date().setHours(0,0,0,0))).format("yyyy-MM-dd hh:mm:ss"),
			endDate:new Date(new Date().setHours(23,59,59,0)).format("yyyy-MM-dd hh:mm:ss"),
			actorid:'',
    		logType:'',
    		rowid:''
	};
    //首次获取全部数据
	$scope.getAllData=function(i){
		$scope.searchData.gameid=$scope.$Params.gameid;
		if($scope.searchData.logType){
    		if(!$scope.isFourNumStartOne($scope.searchData.logType)){
    			alert(T.T("请输入以1开头的4位数字"));
    			return ;
    		}
    	}
		$scope.selectRealTimeDataList();
}
	//角色Id
	$scope.getRealDataByActorid=function(actorId){
		$scope.isClickQueryBtn=true;
		$scope.searchData.actorid=actorId;
		$scope.getAllData();
}
	$scope.isClickQueryBtn=false;//点击查询按钮？不加入rowid查询：假如rowid查询
	//查询
	$scope.getData=function(){
		$scope.isClickQueryBtn=true;
		//精确到秒的日期选择器无法使用ng获取值
		$scope.searchData.startDate=$("#startDate").val();
		$scope.searchData.endDate=$("#endDate").val();
		$scope.getAllData();
}
	$scope.$on('$destroy',function(){
		   $timeout.cancel($scope.act);
		})
		
		
		$scope.getAllData();
}