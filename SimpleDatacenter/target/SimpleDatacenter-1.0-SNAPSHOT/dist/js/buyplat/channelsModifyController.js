function channelsModifyController($scope,$http,$stateParams,$state,T){
    $scope.ctype = $stateParams.ctype;
    $scope.per_id = $stateParams.id;
    $scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
    $scope.channelsListData = {
        id : $scope.ctype == 1 ? "" : $scope.per_id,
        name : "",
        channelsList : ""
    }    
    // 重置参数
    $scope.resetData = function() {
        $scope.channelsListData = {};
    }
    //修改参数
    $scope.postData = function() {
        if ($scope.ctype == 1) {
            $http.post("menu/addSecondMenu.action", $scope.channelsListData).success(function(data) {
                if (data.status == 0) {
                    alert(data.message);
                } else {
                    $scope.backGo();
                }
            }).error(function(data) {
                alert(data.message);
            });
        } else if ($scope.ctype == 2) {
            $http.post("menu/editSecondMenu.action", $scope.channelsListData).success(
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

    $scope.backGo = function() {
        $state.go('main.user.channelsList');
    }

    $scope.getUserData = function() {
        $http.post("menu/findSecondMenu.action", {
            "id" : $scope.per_id
        }).success(function(data) {
            $scope.channelsListData = data;
        })
    }

    $scope.ctype == 1 ? "" : $scope.getUserData();
}