function adChannelsModifyController($scope,$http,$stateParams,$state,T){
    $scope.ctype = $stateParams.ctype;
    $scope.cm_id = $stateParams.id;
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    
    $scope.channelList=[];

    $scope.channelsData={
        id: "",
        channelid: "",
        channelName: "",
        companyName: ""
    }

    //渠道
    $scope.getChannelList=function(){

    }
    //修改
    $scope.postData=function(){
        if ($scope.ctype == 1) {
            $http.post("buyplat/updateAdChannel.action", $scope.channelsData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        } else if ($scope.ctype == 2) {
            $http.post("buyplat/updateAdChannel.action", $scope.channelsData).success(
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
        $state.go('main.buyplat.adChannels');
    }
    
    //添加
    $scope.getChannelData = function(){
        $http.post("buyplat/selectAdChannel.action",{
            "checktype1" : $scope.cm_id 
            }).success(function(data){
                $scope.channelsData=data;
        })
    }

    $scope.getChannelList();
    $scope.ctype == 1 ? "" : $scope.getChannelData();
}