/*
 * 渠道公司信息修改
 * */
function channelCompanyModifyController($scope,$http,$stateParams,$state,T){
    $scope.ctype = $stateParams.ctype;
    $scope.cm_id = $stateParams.id;
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    console.log($scope.cm_id)
    $scope.channelList=[];
    $scope.taxTypeList=[{'value':T.T('普通'),'name':T.T("普通")},{'value':T.T('小规模纳税人'),'name':T.T("小规模纳税人")},{'value':T.T('一般纳税人'),'name':T.T("一般纳税人")}];
    $scope.channelsData={
        id: "",
        channelId: "",
        channelName: "",
        taxId :"" ,
        address : "",
        phone : "" ,
        bank : "",
        bankAcount : "",
        taxType:""
    }
    //渠道
    $scope.getChannelList=function(){
    	$http.post("channel/channelList.action").success(function(data){
        	$scope.channelList = data;
            if(data.length>0){
                var isnone = false;
                for(var i =0;i<$scope.channelList.length;i++){
                    if($scope.channelList[i].id == $scope.channelsData.channelId){
                        isnone = true;
                        break;
                    }
                }
                if(!isnone){
                    $scope.channelsData.channelId = data[0].id;
                }
            }
    	});
    }
    
    //纳税资质
    $scope.getTaxTypeList=function(){
            if($scope.taxTypeList.length>0){
                var isnone = false;
                for(var i =0;i<$scope.taxTypeList.length;i++){
                    if($scope.taxTypeList[i].value == $scope.channelsData.taxType){
                        isnone = true;
                        break;
                    }
                }
                if(!isnone){
                    $scope.channelsData.taxType = $scope.taxTypeList[0].value;
                }
            }
    }
    
    //修改
    $scope.postData=function(){
    	console.log($scope.channelsData);
        if ($scope.ctype == 1) {
            $http.post("balanceplat/addChannelCompanyInfo.action", $scope.channelsData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        }else if ($scope.ctype == 2) {
            $http.post("balanceplat/updateChannelCompanyInfo.action", $scope.channelsData).success(
                    function(data) {
                        if (data.status == 0) {
                            alert(data.message);
                        } else {
                            $scope.backGo();
                        }
                    }).error(function(data) {
                alert(data.message);
            });

        }
    }
    //路由跳转
    $scope.backGo=function(){
        $state.go('main.balanceplat.channelCompany');
    }
    
    //添加
    $scope.selectChannelCompany = function(){
        $http.post("balanceplat/selectChannelCompany.action",{
            "id" : $scope.cm_id 
            }).success(function(data){
            	console.log(data)
                $scope.channelsData = data;
                $scope.getChannelList();
        })
    }
    $scope.ctype == 1 ? $scope.getChannelList() : $scope.selectChannelCompany();
    $scope.getTaxTypeList();
}