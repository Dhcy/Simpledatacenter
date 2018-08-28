function firstMenuModifController($scope, $http, $stateParams, $state,T){
	$scope.ctype = $stateParams.ctype;
	$scope.menu_id = $stateParams.id;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
	$scope.userData={
		key: "",
		name: "",
        date:"",
        platid: 1
	}
	$scope.platformList = [
           /* {id:1 , name:"数据中心"},
            {id:2 , name:"买量平台"},*/
        ];
	$scope.loadPlatFormList=function(){		
		$http.post("platproject/selectPlatList.action",$scope.userData).success(function(data){
			$scope.platformList = data;
		}).error(function(data){
			alert(data.message);
		});
	}
	$scope.loadPlatFormList();
	$scope.resetData = function() {
		$scope.userData={};
	}

	$scope.postData = function() {
		if ($scope.ctype == 1) {
			$http.post("menu/addFirstMenu.action", $scope.userData).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {
			$http.post("menu/editFirstMenu.action",$scope.userData).success(
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
		$state.go('main.user.firstMenu');
	}

	$scope.getUserData = function() {
		$http.post("menu/findFirstMenu.action", {
			"id" : $scope.menu_id
		}).success(function(data) {
			$scope.userData = data;
		})
	}

	$scope.ctype == 1 ? "" : $scope.getUserData();
	$scope.format = "yyyy/MM/dd hh:mm:ss";
    
    
    $scope.dateOptions = {
        //dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(),
        minDate: new Date(2000,1,1),
        startingDay: 1
      };
    
    function disabled(data) {
        var date = data.date,
          mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
      }
    $scope.open1 = function() {
        $scope.popup1.opened = true;
      };
    $scope.popup1 = {
        opened: false
      };
    $scope.check1 = function(){
        console.log($scope.userData);
    }
}