function roleModifController($scope,$http,$stateParams,$state,T){
	$scope.role_id = $stateParams.id;
	$scope.ctype = $stateParams.ctype;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
	$scope.menuType = []; //0:通用,1:单机;2:联网
	$scope.menuTypeArr = [T.T("通用"),T.T("单机"),T.T("联网")]; //0:通用,1:单机;2:联网
	$scope.menuTypeTextArr = [];
	$scope.menuTypeText = T.T("全部");
    
    $scope.setMenuType=function(i){
        var isHad = $scope.menuTypeTextArr.indexOf($scope.menuTypeArr[i]);
        if(isHad>=0){       
            $scope.menuType.splice(isHad,1);
            $scope.menuTypeTextArr.splice(isHad,1);
        }else{
            $scope.menuType.push(i);
            $scope.menuTypeTextArr.push($scope.menuTypeArr[i]);
        }    
        if($scope.menuTypeTextArr.length>0){
            $scope.menuTypeText= $scope.menuTypeTextArr.join(",")
        }else{
            
            $scope.menuTypeText= T.T("全部")
        }
    }
    
    $scope.hadmenuType=function(i){
        if($scope.menuTypeTextArr.length==0)
            return true;
        var isHad = $scope.menuTypeTextArr.indexOf($scope.menuTypeArr[i]);
        if(isHad>=0){   
            return true;
        }else{
            return false;
        }        
    }
	
	$scope.userData={
		id:$scope.role_id?$scope.role_id:-1,
		rolename:"",
		isvalid:1,
		competence:[],
        menuList:[]
	}
	$scope.competencelist=[];
	
	$scope.selectData={all:false,str:T.T("全选")}
	$scope.setSelectData=function(t){
		if(t){
			$scope.selectData.all=true;
			$scope.selectData.str=T.T("全不选");
		}else{
			$scope.selectData.str=T.T("全选");
			$scope.selectData.all=false;
		}
		//$scope.setData1
	}
	$scope.allChoose=function(){
		if(!$scope.selectData.all)
		{
			for(var i=0;i<$scope.competencelist.length;i++){
				$scope.competencelist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.competencelist.length;i++){
				$scope.competencelist[i].choosed=false;
			}
		}		$scope.setSelectData($scope.checkAll($scope.competencelist,'choosed'));
	}
	$scope.unChoose=function(){
		for(var i=0;i<$scope.competencelist.length;i++){
			$scope.competencelist[i].choosed=!$scope.competencelist[i].choosed;
		}
		$scope.setSelectData($scope.checkAll($scope.competencelist,'choosed'));
	}
	$scope.checkAll=function(data,key){
		for(var i=0;i<data.length;i++){
			if(!data[i][key]){
				return false;
			}
		}
		return true;
	}

	$scope.setData1=function(){
		$scope.userData.menuList=[];
		for(var i=0;i<$scope.competencelist.length;i++){
			if($scope.competencelist[i].choosed){
                var menu = {"id":$scope.competencelist[i].id};
				$scope.userData.menuList.push(menu);
			}
		}
	}

	$scope.resetData=function(){
		$scope.userData={};
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}
		$scope.role_id?0:$scope.userData.isvalid=1;
		$scope.resetChoose();
	}
    
	$scope.resetChoose=function(){
		for(var i=0;i<$scope.competencelist.length;i++){
			$scope.competencelist[i].choosed=false;
			if($scope.userData.competence){
			for(var k=0;k<$scope.userData.competence.length;k++){
				if($scope.competencelist[i].id==$scope.userData.competence[k]){
					$scope.competencelist[i].choosed=true;
				}
			}}
		}
	}
	$scope.postData=function(){
        $scope.setData1();
		if($scope.ctype == 1){
			$http.post("role/addRole.action",$scope.userData).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if($scope.ctype == 2){
			$http.post("role/editRole.action",$scope.userData).success(function(data) {
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
		$state.go('main.user.role');
	}
    $scope.menuFirstList = [];
    $scope.secondMenulist=[];
    //权限列表
    $scope.getMenuList = function(){
        $http.post("menu/secondMenuList.action",{"firstId":-1}).success(function(data){
			console.log(data);
			$scope.secondMenulist=data;
			for(var i=0;i<$scope.secondMenulist.length;i++){
				if($scope.secondMenulist[i].firstMenu.platid!=7){
					$scope.competencelist.push($scope.secondMenulist[i]);
				}
			}
//            $scope.competencelist = data;
            for(var i=0;i<$scope.competencelist.length;i++){
				var isb = false;
                $scope.competencelist[i].choosed=false;
				for(var k = 0 ; k < $scope.menuFirstList.length;k++){
					if($scope.menuFirstList[k].id==$scope.competencelist[i].firstMenu.id){
						isb = true;break;
					}
					isb = false;
				}
				if(isb)continue;
				$scope.menuFirstList.push({
					id:$scope.competencelist[i].firstMenu.id,
					name:$scope.competencelist[i].firstMenu.name,
					key:$scope.competencelist[i].firstMenu.key
				})
            }
			
			
			
            $scope.resetData();
            
        })
    }
	var saveData={};
	$scope.getUserData=function(){
        $scope.getMenuList();

        $http.post("role/editRoleUI.action",{"id":$scope.role_id}).success(function(data){
			$scope.userData = data;    
			console.log(data)
            $scope.userData.competence=[];
            if(data.menuList != ""){
            	for(var i=0; i < data.menuList.length; i++){
            		$scope.userData.competence.push(data.menuList[i].id);
            	}
            	for(var key in $scope.userData){
            		saveData[key]=$scope.userData[key];
            	}	
            	$scope.resetChoose();
            }
		})
	}
	$scope.ctype == 1 ? $scope.getMenuList() : $scope.getUserData()
}

