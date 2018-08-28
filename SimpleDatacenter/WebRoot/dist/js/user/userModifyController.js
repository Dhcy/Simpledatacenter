function userModifyController($scope,$http,$stateParams,$state,T){
    $scope.userId = $stateParams.id;
	$scope.ctype = $stateParams.ctype;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");

	$scope.userData={
		id:$scope.userId?$scope.userId:-1,
        oldPassword:"123",
        surePassword:"123",
		username:"",
		realname:"",
		password:"",
		phone:"",
		roleList:[]
        
		//departmen:[]
	}
	$scope.rolelist=[];
	/*$scope.departmentlist=[
		{
			id:"",
			name:"",
			choosed:false
		},
		{
			id:"1",
			name:"部门1",
			choosed:false
		},
		{
			id:"2",
			name:"部门2",
			choosed:false
		},
		{
			id:"4",
			name:"部门3",
			choosed:false
		},
		{
			id:"5",
			name:"部门4",
			choosed:false
		},
		{
			id:"6",
			name:"部门5",
			choosed:false
		},
	];*/
    
    
	$scope.selectData={all:false,str:T.T("全选")}
	//$scope.selectData2={all:false,str:"全选"}
	$scope.setSelectData=function(t){
		if(t){
			$scope.selectData.all=true;
			$scope.selectData.str=T.T("全不选");			
		}else{
			$scope.selectData.str=T.T("全选");
			$scope.selectData.all=false;
		}
		$scope.setData1
	}
    
	/*$scope.setSelectData2=function(t){
		if(t){
			$scope.selectData2.all=true;
			$scope.selectData2.str="全不选";			
		}else{
			$scope.selectData2.str="全选";
			$scope.selectData2.all=false;
		}
		$scope.setData2();
	}*/
    
	$scope.allChoose=function(){
		if(!$scope.selectData.all)
		{
			for(var i=0;i<$scope.rolelist.length;i++){
				$scope.rolelist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.rolelist.length;i++){
				$scope.rolelist[i].choosed=false;
			}
		}		
		$scope.setSelectData($scope.checkAll($scope.rolelist,'choosed'));
	}
    
	$scope.unChoose=function(){
		for(var i=0;i<$scope.rolelist.length;i++){
			$scope.rolelist[i].choosed=!$scope.roleList[i].choosed;
		}
		$scope.setSelectData($scope.checkAll($scope.roleList,'choosed'));
	}
    
	/*$scope.allChoose2=function(){
		if(!$scope.selectData2.all)
		{
			for(var i=0;i<$scope.departmentlist.length;i++){
				$scope.departmentlist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.departmentlist.length;i++){
				$scope.departmentlist[i].choosed=false;
			}
		}		$scope.setSelectData2($scope.checkAll($scope.departmentlist,'choosed'));
	}*/
	/*$scope.unChoose2=function(){
		for(var i=0;i<$scope.departmentlist.length;i++){
			$scope.departmentlist[i].choosed=!$scope.departmentlist[i].choosed;
		}
		$scope.setSelectData2($scope.checkAll($scope.departmentlist,'choosed'));
	}*/
	$scope.checkAll=function(data,key){
		for(var i=0;i<data.length;i++){
			if(!data[i][key]){				
				return false;
			}			
		}
		return true;
	}
	
	$scope.setData1=function(){
		$scope.userData.roleList=[];
		for(var i=0;i<$scope.rolelist.length;i++){
			if($scope.rolelist[i].choosed){
                var role = {"id":$scope.rolelist[i].id}
				$scope.userData.roleList.push(role);
			}
		}
	}
	/*$scope.setData2=function(){
		$scope.userData.departmen=[];
		for(var i=0;i<$scope.departmentlist.length;i++){
			if($scope.departmentlist[i].choosed){
				$scope.userData.departmen.push($scope.departmentlist[i].id);
			}
		}
	}*/
	
	$scope.resetData=function(){
		$scope.userData={};
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}	
		$scope.resetChoose();
	}
	$scope.resetChoose=function(){
		for(var i=0;i<$scope.rolelist.length;i++){
			$scope.rolelist[i].choosed=false;
			if($scope.userData.roleList){
                for(var k=0;k<$scope.userData.roleList.length;k++){
				    if($scope.rolelist[i].id==$scope.userData.roleList[k]){
					   $scope.rolelist[i].choosed=true;
				    }
			     }
            }
		}
		/*for(var i=0;i<$scope.departmentlist.length;i++){
			$scope.departmentlist[i].choosed=false;
			if($scope.userData.departmen){
			for(var k=0;k<$scope.userData.departmen.length;k++){
				if($scope.departmentlist[i].id==$scope.userData.departmen[k]){
					$scope.departmentlist[i].choosed=true;
				}
			}}
		}*/
	}
	
	$scope.getRoleList=function(){
		$http.get("role/roleList.action").success(function(data){
			$scope.rolelist = data;
			for(var i=0;i<$scope.rolelist.length;i++){
                $scope.rolelist[i].choosed=false;
            }
            $scope.resetData();
		})
	}
	
	$scope.postData=function(){		
		$scope.setData1();
		if($scope.ctype == 1){
			//console.log($scope.userData);
			$http.post("user/addUser.action",
					{
		                "oldPassword":"123",
		                "surePassword":"123",
				        "username":$scope.userData.username,
				        "realname":$scope.userData.realname,
				        "password":$scope.userData.password,
				        "phone":$scope.userData.phone,
				        "roleList":$scope.userData.roleList
                
					}).success(function(data) {
						if (data.status == 0) {
							alert(data.message);
						} else {
							$scope.backGo();
						}
					}).error(function(data) {
						alert(data.message);
					});
		} else if($scope.ctype == 2){
			$http.post("user/editUser.action",
				{
					"id":$scope.userId,
	                "oldPassword":"123",
	                "surePassword":"123",
			        "username":$scope.userData.username,
			        "realname":$scope.userData.realname,
			        "password":$scope.userData.password?$scope.userData.password:"null",
			        "phone":$scope.userData.phone,
			        "roleList":$scope.userData.roleList
                
				}).success(function(data) {
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
		$state.go('main.user.userManage');
	}
	
	
	var saveData={};
	
	$scope.getUserData=function(){
		$scope.getRoleList();
        $http.post("user/editUserUI.action",{"id":$scope.userId}).success(function(data){
            var roles = data.roleList;
			$scope.userData = data; 
            
            $scope.userData.roleList=[];
            
            for(var i=0; i < roles.length; i++){
            	$scope.userData.roleList.push(roles[i].id);
            }
            
            for(var key in $scope.userData){
            	saveData[key]=$scope.userData[key];
            }	
            $scope.resetChoose();
            
		})
		/*for(var key in getData){
			saveData[key]=getData[key];
		}
		$scope.userData=getData;
		$scope.resetChoose();*/
	}
	
	$scope.userId?$scope.getUserData():$scope.getRoleList();
}