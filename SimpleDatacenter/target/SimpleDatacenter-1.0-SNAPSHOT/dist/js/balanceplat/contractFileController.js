/**
 * 合同文件
 * @param $scope
 * @param $http
 * @return
 */
function contractFileController($scope,$http,Upload,T){
	
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
	    $scope.uploadChannelList={
	    		'data':[]
	    },
    $scope.searchData={
    	channelId : -1,
    }
    $scope.uploadData={
        	channelId :'',
        	description:'',
        	file:''
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
    		if(data.length>0){
    			$scope.uploadData.channelId=data[0].id;
    		}

    		$scope.uploadChannelList.data=data;
    		var dat=[{id:-1,channelName:T.T("全部")}];   
    		$scope.panel_1.channelList = dat.concat(data);
    	});
    }
    //搜索
	$scope.getData=function(){
		console.log($scope.searchData)
		$http.post("balanceplat/selectContractFileList.action",$scope.searchData).success(function(data){
			console.log(data);
			if(data == undefined || data == null){
				data = [];
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
	}
	 $scope.fileChange = function($file){
		 $scope.uploadData.file = $file;
	    }
	//提交
    $scope.submit = function (element) {
    	var ele = $(element);
    	if($scope.uploadData.file){
    		ele.prop('disabled', true);
    		$scope.upload($scope.uploadData.file,ele);
    	}else{
    		$scope.dialogopen({
				msg:T.T("请先选择合同文件再上传"),
				btn:{close:true,ok:false}
			})
    	}
    };
    //上传
    $scope.upload = function (file,ele) {
        Upload.upload({
            //服务端接收
            url: 'balanceplat/uploadContractFile.action',
            //上传的同时带的参数
            data: {file: file,'channelId': $scope.uploadData.channelId,'description':$scope.uploadData.description},
        }).success(function (data, status, headers, config) {
            //上传成功
        	ele.prop('disabled', false);
        	alert(data.message);
        }).error(function (data, status, headers, config) {
            //上传失败
        	ele.prop('disabled', false);
        	alert(data.message);
        });
    };
    //下载
    $scope.downFile=function(fileName,ele){
    	$scope.searchData.fileName=fileName;
    	$(ele).prop('disabled', true);
    	$http({
			url: 'balanceplat/downContractFile.action',
			method: 'post',
			data:$scope.searchData,
			responseType: 'arraybuffer'
		}).success(function (data, status, headers) {
			$(ele).prop('disabled', false);
			saveAs(new Blob([data], {  }), $scope.searchData.fileName);  // 中文乱码
		}).error(function (data, status) {
			$(ele).prop('disabled', false);
			alert("下载失败");
		});
    }
    //删除
    $scope.delFile=function(id,fileName){
    	$scope.dialogopen({
            msg:T.T("确认删除文件")+fileName+"?",
            btn:{close:true},
            fn:{
                ok:function(){
                    $http.post("balanceplat/delContractFile.action",{'id':id,'fileName':fileName}).success(function(data){
                        if(data.status==1){
                            $scope.dialogopen({
                                msg:data.message,
                                btn:{close:false}
                            })
                            $scope.getData();
                        }else{
                            $scope.dialogopen({
                                msg:data.message,
                                btn:{close:false}
                            })
                        }
                    });
                }
            }
        })
    }
    
    
    //获取渠道列表
	$scope.getChannelList();
        
}