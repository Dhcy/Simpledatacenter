function resourcesController($scope,$http,$stateParams,$state,T){
	$scope.userId = $stateParams.id;
	$scope.username = $stateParams.username;
	$scope.userData={
		id:$scope.userId?$scope.userId:-1,
		platform:[],
		project:[],
		game:[],
		channel:[],
		adchannel:[]
		
	}
	$scope.platformlist=[];
	$scope.projectlist=[];
	$scope.gamelist=[];
	$scope.channellist=[];
	$scope.adchannellist=[];
    
	$scope.selectData={all:false,str:T.T("全选")}
	$scope.selectData2={all:false,str:T.T("全选")}
	$scope.selectData3={all:false,str:T.T("全选")}
	$scope.selectData4={all:false,str:T.T("全选")}
	$scope.selectData5={all:false,str:T.T("全选")}
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
	$scope.setSelectData2=function(t){
		if(t){
			$scope.selectData2.all=true;
			$scope.selectData2.str=T.T("全不选");			
		}else{
			$scope.selectData2.str=T.T("全选");
			$scope.selectData2.all=false;
		}
		$scope.setData2();
	}
	$scope.setSelectData3=function(t){
		if(t){
			$scope.selectData3.all=true;
			$scope.selectData3.str=T.T("全不选");			
		}else{
			$scope.selectData3.str=T.T("全选");
			$scope.selectData3.all=false;
		}
		$scope.setData3();
	}
	$scope.setSelectData4=function(t){
		if(t){
			$scope.selectData4.all=true;
			$scope.selectData4.str=T.T("全不选");			
		}else{
			$scope.selectData4.str=T.T("全选");
			$scope.selectData4.all=false;
		}
		$scope.setData4();
	}
	$scope.setSelectData5=function(t){
		if(t){
			$scope.selectData5.all=true;
			$scope.selectData5.str=T.T("全不选");			
		}else{
			$scope.selectData5.str=T.T("全选");
			$scope.selectData5.all=false;
		}
		$scope.setData5();
	}
	$scope.allChoose=function(){
		if(!$scope.selectData.all)
		{
			for(var i=0;i<$scope.projectlist.length;i++){
				$scope.projectlist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.projectlist.length;i++){
				$scope.projectlist[i].choosed=false;
			}
		}		$scope.setSelectData($scope.checkAll($scope.projectlist,'choosed'));
	}
	$scope.unChoose=function(){
		for(var i=0;i<$scope.projectlist.length;i++){
			$scope.projectlist[i].choosed=!$scope.projectlist[i].choosed;
		}
		$scope.setSelectData($scope.checkAll($scope.projectlist,'choosed'));
	}
	$scope.allChoose2=function(){
		if(!$scope.selectData2.all)
		{
			for(var i=0;i<$scope.gamelist.length;i++){
				$scope.gamelist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.gamelist.length;i++){
				$scope.gamelist[i].choosed=false;
			}
		}		$scope.setSelectData2($scope.checkAll($scope.gamelist,'choosed'));
	}
	$scope.unChoose2=function(){
		for(var i=0;i<$scope.gamelist.length;i++){
			$scope.gamelist[i].choosed=!$scope.gamelist[i].choosed;
		}
		$scope.setSelectData2($scope.checkAll($scope.gamelist,'choosed'));
	}
	$scope.allChoose3=function(){
		if(!$scope.selectData3.all)
		{
			for(var i=0;i<$scope.channellist.length;i++){
				$scope.channellist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.channellist.length;i++){
				$scope.channellist[i].choosed=false;
			}
		}		$scope.setSelectData3($scope.checkAll($scope.channellist,'choosed'));
	}
	$scope.unChoose3=function(){
		for(var i=0;i<$scope.channellist.length;i++){
			$scope.channellist[i].choosed=!$scope.channellist[i].choosed;
		}
		$scope.setSelectData3($scope.checkAll($scope.channellist,'choosed'));
	}
	$scope.allChoose4=function(){
		if(!$scope.selectData4.all)
		{
			for(var i=0;i<$scope.platformlist.length;i++){
				$scope.platformlist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.platformlist.length;i++){
				$scope.platformlist[i].choosed=false;
			}
		}		$scope.setSelectData4($scope.checkAll($scope.platformlist,'choosed'));
	}
	$scope.unChoose4=function(){
		for(var i=0;i<$scope.platformlist.length;i++){
			$scope.platformlist[i].choosed=!$scope.platformlist[i].choosed;
		}
		$scope.setSelectData4($scope.checkAll($scope.platformlist,'choosed'));
	}
	$scope.allChoose5=function(){
		if(!$scope.selectData5.all)
		{
			for(var i=0;i<$scope.adchannellist.length;i++){
				$scope.adchannellist[i].choosed=true;
			}
		}else{
			for(var i=0;i<$scope.adchannellist.length;i++){
				$scope.adchannellist[i].choosed=false;
			}
		}		$scope.setSelectData5($scope.checkAll($scope.adchannellist,'choosed'));
	}
	$scope.unChoose5=function(){
		for(var i=0;i<$scope.adchannellist.length;i++){
			$scope.adchannellist[i].choosed=!$scope.adchannellist[i].choosed;
		}
		$scope.setSelectData5($scope.checkAll($scope.adchannellist,'choosed'));
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
		$scope.userData.project=[];
		for(var i=0;i<$scope.projectlist.length;i++){
			if($scope.projectlist[i].choosed){
				$scope.userData.project.push($scope.projectlist[i].id);
			}
		}
	}
	$scope.setData2=function(){
		$scope.userData.game=[];
		for(var i=0;i<$scope.gamelist.length;i++){
			if($scope.gamelist[i].choosed){
				$scope.userData.game.push($scope.gamelist[i].id);
			}
			
		}
	}
	$scope.setData3=function(){
		$scope.userData.channel=[];
		for(var i=0;i<$scope.channellist.length;i++){
			if($scope.channellist[i].choosed){
				$scope.userData.channel.push($scope.channellist[i].id);
			}
		}
	}
	$scope.setData4=function(){
		$scope.userData.platform=[];
		for(var i=0;i<$scope.platformlist.length;i++){
			if($scope.platformlist[i].choosed){
				$scope.userData.platform.push($scope.platformlist[i].id);
			}
		}
	}
	$scope.setData5=function(){
		$scope.userData.adchannel=[];
		for(var i=0;i<$scope.adchannellist.length;i++){
			if($scope.adchannellist[i].choosed){
				$scope.userData.adchannel.push($scope.adchannellist[i].id);
			}
		}
	}
	
	$scope.resetData=function(){
		$scope.userData={
            id:$scope.userId?$scope.userId:-1,
            platform:[],
            project:[],
            game:[],
            channel:[],
			adchannel:[]
        };
		for(var key in saveData){
			$scope.userData[key]=saveData[key];
		}	
		$scope.resetChoose();
	}
	$scope.resetChoose=function(){
		for(var i=0;i<$scope.projectlist.length;i++){
			$scope.projectlist[i].choosed=false;
            //console.log($scope.userData.project);
			if($scope.userData.project){
			for(var k=0;k<$scope.userData.project.length;k++){
				if($scope.projectlist[i].id==$scope.userData.project[k]){
					$scope.projectlist[i].choosed=true;
				}
			}}
		}
		for(var i=0;i<$scope.gamelist.length;i++){
			$scope.gamelist[i].choosed=false;
            //console.log($scope.userData.game);
			if($scope.userData.game){
			for(var k=0;k<$scope.userData.game.length;k++){
				if($scope.gamelist[i].id==$scope.userData.game[k]){
					$scope.gamelist[i].choosed=true;
				}
			}}
		}
		for(var i=0;i<$scope.channellist.length;i++){
			$scope.channellist[i].choosed=false;
			if($scope.userData.channel){
			for(var k=0;k<$scope.userData.channel.length;k++){
				if($scope.channellist[i].id==$scope.userData.channel[k]){
					$scope.channellist[i].choosed=true;
				}
			}}
		}
		for(var i=0;i<$scope.platformlist.length;i++){
			$scope.platformlist[i].choosed=false;
			if($scope.userData.platform){
			for(var k=0;k<$scope.userData.platform.length;k++){
				if($scope.platformlist[i].id==$scope.userData.platform[k]){
					$scope.platformlist[i].choosed=true;
				}
			}}
		}
		for(var i=0;i<$scope.adchannellist.length;i++){
			$scope.adchannellist[i].choosed=false;
			if($scope.userData.adchannel){
			for(var k=0;k<$scope.userData.adchannel.length;k++){
				if($scope.adchannellist[i].id==$scope.userData.adchannel[k]){
					$scope.adchannellist[i].choosed=true;
				}
			}}
		}
	}
	$scope.postData=function(){		
		$scope.setData1();
		$scope.setData2();
        $scope.setData3();
        $scope.setData4();
        $scope.setData5();
		
        $http.post("user/editUserResource.action",
                   {          
                        "userid":$scope.userId, "projectList":$scope.userData.project,
                        "gameList":$scope.userData.game,
                        "channelList":$scope.userData.channel,
						"platformList":$scope.userData.platform,
						"adchannelList":$scope.userData.adchannel,
		})
            .success(function(data) {
						if (data.status == 0) {
							alert(data.message);
						} else {
							$scope.backGo();
						}
					}).error(function(data) {
						alert(data.message);
					});
	}
    
    $scope.getProjectList=function(){
        $http.get("project/projectList.action").success(function(data){
            $scope.projectlist = data;
            for(var i=0;i<$scope.projectlist.length;i++){
                $scope.projectlist[i].choosed=false;
            }
            $scope.resetChoose();
        })
    }
    $scope.getGameList=function(){
        $http.post("game/gameList.action",{"projectid":-1}).success(function(data){
            $scope.gamelist = data;
            for(var i=0;i<$scope.gamelist.length;i++){
                $scope.gamelist[i].choosed=false;
            }
            $scope.resetChoose();
        })
    }
    $scope.getChannelList=function(){
        $http.get("channel/channelList.action").success(function(data){
            $scope.channellist = data;
            for(var i=0;i<$scope.channellist.length;i++){
                $scope.channellist[i].choosed=false;
            }
            $scope.resetChoose();
        })
    }
    $scope.getPlatformList=function(){
        $http.get("platprojectgame/selectPlatList.action").success(function(data){
            $scope.platformlist = data;
            for(var i=0;i<$scope.platformlist.length;i++){
                $scope.platformlist[i].choosed=false;
            }
            $scope.resetChoose();
        })
    }
    $scope.getAdchannelList=function(){
        $http.post("buyplat/selectAdChannelList.action",{}).success(function(data){
            $scope.adchannellist = data;
            for(var i=0;i<$scope.adchannellist.length;i++){
                $scope.adchannellist[i].choosed=false;
            }
			console.log(data);
            $scope.resetChoose();
        })
    }
    
    $scope.backGo = function() {
		$state.go('main.user.userManage');
	}
    
	var saveData={};
	$scope.getUserData=function(){
        $scope.getPlatformList();
        $scope.getProjectList();
        $scope.getGameList();
        $scope.getChannelList();
        $scope.getAdchannelList();

        $http.post("user/editUserResourceUI.action",{"id":$scope.userId,"username":$scope.username}).success(function(data){
            
            
            for(var i=0; i<data.projectList.length; i++){
                $scope.userData.project.push(data.projectList[i]);
            }
            
            for(var i=0; i<data.gameList.length; i++){
                $scope.userData.game.push(data.gameList[i]);
            }
            
            for(var i=0; i<data.channelList.length; i++){
                $scope.userData.channel.push(data.channelList[i]);
            }
            for(var i=0; i<data.platformList.length; i++){
                $scope.userData.platform.push(data.platformList[i]);
            }
			if(data.adchannelList)
			{
				for(var i=0; i<data.adchannelList.length; i++){	//广告
				$scope.userData.adchannel.push(data.adchannelList[i]);
				}
			}

            for(var key in $scope.userData){
                saveData[key]=$scope.userData[key];
            }

            $scope.resetChoose();
        })
        
	}
	$scope.userId?$scope.getUserData():0;
	
}