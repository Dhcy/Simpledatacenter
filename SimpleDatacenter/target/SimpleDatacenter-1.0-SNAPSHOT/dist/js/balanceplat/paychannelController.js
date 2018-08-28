function paychannelController($scope,$http,Upload,T){
	    $scope.panel_1 = {
        'model': 'table',
        'gameList':[],
        'channelList':[],
        'projectList':[],
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
        },
	    'sumPayIncome':{
        	statdate1:T.T('汇总'),
			money:0,
			payChannelIncomes:[],
			payChanelSize:0
        }
       
    };
	    $scope.panel_2 = {
	            'model': 'table',
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
	        };
    $scope.postData={
        gameid : -1 ,
        projectid : -1,
        checktype1:1,
        checktype2:-1
    };
    $scope.uploadData={
    		file:''
    }
    $scope.projectTypeList= [{id:1 , name:T.T("新项目")},{id:2 , name:T.T("旧项目")}];
    $scope.attrCode="signCompany";
    //分页显示
    $scope.changetype=function(i){
        $scope.panel_1.dataList_1.limit.max=i;
        $scope.panel_1.dataList_1.limit.now=0;
        $scope.panel_1.dataList_1.limit.sumLen=($scope.panel_1.dataList_1.data).length;
        $scope.panel_1.dataList_1.limit.count=Math.ceil($scope.panel_1.dataList_1.limit.sumLen/$scope.panel_1.dataList_1.limit.max)-1;
    }
    $scope.changetype2=function(i){
        $scope.panel_2.dataList_1.limit.max=i;
        $scope.panel_2.dataList_1.limit.now=0;
        $scope.panel_2.dataList_1.limit.sumLen=($scope.panel_2.dataList_1.data).length;
        $scope.panel_2.dataList_1.limit.count=Math.ceil($scope.panel_2.dataList_1.limit.sumLen/$scope.panel_2.dataList_1.limit.max)-1;
    }
    
    //游戏
    $scope.getGameList=function(){
        $http.post("game/getUserGameList.action",{"projectid" : -1}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.gameList=data;
            $scope.postData.gameid=data[0].id;
//            $scope.getChannelList();
        })
    }
    //项目下的游戏列表
    $scope.getGameListByProjectId=function(){
        $http.post("game/gameList.action",{"projectid" : $scope.postData.projectid}).success(function(data){
            var dat={id:-1,name:T.T("全部")};   
            data.unshift(dat);
            $scope.panel_1.gameList=[];
            $scope.panel_1.gameList=data;
            $scope.postData.gameid=data[0].id;
//            $scope.getChannelList();
        })
    }
    //导出渠道对账
    $scope.exportPayChannelAccounting=function(fileName,ele){
//    	if($scope.postData.gameid==-1&&$scope.postData.projectid==-1){
//			alert("游戏或者项目至少必选一个");
//			return;
//		}
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+$scope.mstatdate;
    	$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	$http({
			url: 'balanceplat/exportPayChannelAccounting.action',
			method: 'post',
			data:$scope.postData,
			responseType: 'arraybuffer'
		}).success(function (data, status, headers) {
			$(ele).prop('disabled', false);
			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
		}).error(function (data, status) {
			$(ele).prop('disabled', false);
			alert("下载失败");
		});
    }
    //导出视频收入
    $scope.exportUscAdvertIncomeList=function(fileName,ele){
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName+$scope.mstatdate;
    	$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
 	    console.log($scope.postData);
    	$(ele).prop('disabled', true);
    	$http({
			url: 'balanceplat/exportUscAdvertIncomeList.action',
			method: 'post',
			data:$scope.postData,
			responseType: 'arraybuffer'
		}).success(function (data, status, headers) {
			$(ele).prop('disabled', false);
			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
		}).error(function (data, status) {
			$(ele).prop('disabled', false);
			alert(T.T("下载失败"));
		});
    }
    //导出视频录入模板
    $scope.exportVideoEntryTemplate=function(fileName,ele){
    	var type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
    	$scope.postData.fileName=fileName;
    	$(ele).prop('disabled', true);
    	$http({
			url: 'balanceplat/exportVideoEntryTemplate.action',
			method: 'post',
			data:{checktype2:'-1'},
			responseType: 'arraybuffer'
		}).success(function (data, status, headers) {
			$(ele).prop('disabled', false);
			saveAs(new Blob([data], {type:type  }), $scope.postData.fileName+".xlsx");  // 中文乱码
		}).error(function (data, status) {
			$(ele).prop('disabled', false);
			alert(T.T("下载失败"));
		});
    }
    $scope.fileChange = function($file){
		 $scope.uploadData.file = $file;
	    }
    $scope.uploadVideoIncome = function (element) {
    	var ele = $(element);
    	if($scope.uploadData.file){
    		ele.prop('disabled', true);
    		$scope.upload($scope.uploadData.file,ele);
    	}else{
    		$scope.dialogopen({
				msg:T.T("请先选择文件再上传"),
				btn:{close:true,ok:false}
			})
    	}
    };
    //上传
    $scope.upload = function (file,ele) {
        Upload.upload({
            //服务端接收
            url: 'balanceplat/uploadVideoIncomeFile.action',
            //上传的同时带的参数
            data: {file: file},
        }).success(function (data, status, headers, config) {
            //上传成功
        	ele.prop('disabled', false);
        	alert(data.message);
        	$scope.getData();//刷新
        }).error(function (data, status, headers, config) {
            //上传失败
        	ele.prop('disabled', false);
        	alert(data.message);
        });
    };
    ////////////////////////////////////////////
    $scope.payChannelList=[];//获取的区域列表
	$scope.payChannelArr = []; //筛选后的区域列表
	$scope.channelTextArr = [];
	$scope.channelText = T.T("请选择");
	$scope.showCheckAllText=false;//勾选全选时，文本显示全选
    //勾选区域
    $scope.setChannelList=function(i){
        var isHad = $scope.payChannelArr.indexOf(i);
        if(isHad>=0){       
        	$scope.payChannelArr.splice(isHad,1);
            $scope.channelTextArr.splice(isHad,1);
        }else{
        	$scope.payChannelArr.push(i);
            $scope.channelTextArr.push(i);
        }  
        if(!$scope.isSelectedAll()){
        	//非全选状态下
        	$scope.showCheckAllText=false;
        }else{
        	//全选状态下
        	$scope.showCheckAllText=true;
        }
        if(!$scope.showCheckAllText){
			 if($scope.channelTextArr.length>0){
		            $scope.channelText= $scope.channelTextArr.join(",")
		        }else{
		        	$scope.channelText= T.T("请选择")
		        }
		}else{
			$scope.channelText= T.T("全选");
		}
        //显示6个字符
        if($scope.channelText.length>6){
        	$scope.channelText=$scope.channelText.substr(0,6);
        }
      //有勾选时
   	 if($scope.payChannelArr.length!=0){
   		 //筛选显示
         if($scope.panel_1.dataList_1.data!=undefined){
	 		 for(var i=0;i<$scope.panel_1.dataList_1.data.length;i++){
	   			  var sum=0.00;
	   			 for(var j=0;j<$scope.panel_1.dataList_1.data[i].payChannelIncomes.length;j++){
	   				 var obj=$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].channelName;
	   	   			 if($scope.payChannelArr.indexOf(obj)<0){
	   	   			$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].isShow=false;
	   	   			 }else{
	   	   			$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].isShow=true;
	   	   			sum+=$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].money;
	   	   			 }
	   			 }
	   			$scope.panel_1.dataList_1.data[i].money=sum;
	   		 }
    		 //汇总隐藏
    	   if($scope.panel_1.sumPayIncome.payChannelIncomes!=undefined){
    		 var sum=0.00;
    		 for(var k=0;k<$scope.panel_1.sumPayIncome.payChannelIncomes.length;k++){
    			 var obj=$scope.panel_1.sumPayIncome.payChannelIncomes[k].channelName;
    			 if($scope.payChannelArr.indexOf(obj)<0){
    				 $scope.panel_1.sumPayIncome.payChannelIncomes[k].isShow=false;
    			 }else{
    				$scope.panel_1.sumPayIncome.payChannelIncomes[k].isShow=true;
    				sum+=$scope.panel_1.sumPayIncome.payChannelIncomes[k].money;
    			 }
    		 }
    		$scope.panel_1.sumPayIncome.money=sum;
    	   }
       }
   	 }else{
   		 //没勾选时
   		if($scope.panel_1.dataList_1.data!=undefined){
   			for(var i=0;i<$scope.panel_1.dataList_1.data.length;i++){
   				var sum=0.00;
	   			 for(var j=0;j<$scope.panel_1.dataList_1.data[i].payChannelIncomes.length;j++){
	   	   			$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].isShow=true;
	   	   			sum+=$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].money;
	   			 }
	   			 $scope.panel_1.dataList_1.data[i].money=sum;
   			}
   			//
   			if($scope.panel_1.sumPayIncome.payChannelIncomes!=undefined){
       		 var sum=0.00;
       		 for(var k=0;k<$scope.panel_1.sumPayIncome.payChannelIncomes.length;k++){
       				$scope.panel_1.sumPayIncome.payChannelIncomes[k].isShow=true;
       				sum+=$scope.panel_1.sumPayIncome.payChannelIncomes[k].money;
       		 }
       		$scope.panel_1.sumPayIncome.money=sum;
       	   }
   		}
   	 }
    }
       
  //点击全选按钮时
    $scope.clickAllSelect=function(){
    	var isCheckAll=$scope.isSelectedAll();
    	if(!isCheckAll){
    		//要勾全选
    		$scope.payChannelArr.splice(0,$scope.payChannelArr.length);//清空
    		 $scope.channelTextArr.splice(0,$scope.channelTextArr.length);
    		for(var i=0;i<$scope.payChannelList.length;i++){//添加内容
    			$scope.payChannelArr.push($scope.payChannelList[i].channelName);
	            $scope.channelTextArr.push($scope.payChannelList[i].channelName);
    		}
    		$scope.showCheckAllText=true;
    	}else{
    		//取消全选
    		$scope.payChannelArr.splice(0,$scope.payChannelArr.length);//清空
    		$scope.channelTextArr=[];
    		$scope.showCheckAllText=false;
    	}
    		if(!$scope.showCheckAllText){
    			 if($scope.channelTextArr.length>0){
    		            $scope.channelText= $scope.channelTextArr.join(",")
    		        }else{
    		        	$scope.channelText= T.T("请选择")
    		        }
    		}else{
    			$scope.channelText= T.T("全选");
    		}
    	
    	 if($scope.channelText.length>6){
         	$scope.channelText=$scope.channelText.substr(0,6);
         }
    	 //列隐藏
    	 if($scope.panel_1.dataList_1.data!=undefined){
       		 for(var i=0;i<$scope.panel_1.dataList_1.data.length;i++){
       			 var sum=0.00;
       			 for(var j=0;j<$scope.panel_1.dataList_1.data[i].payChannelIncomes.length;j++){
       				$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].isShow=true;
       				sum+=$scope.panel_1.dataList_1.data[i].payChannelIncomes[j].money;
       			 }
       			$scope.panel_1.dataList_1.data[i].money=sum;
       		 }
       	 }
    	//汇总隐藏
    	 if($scope.panel_1.sumPayIncome.payChannelIncomes!=undefined){
    		 var sum=0.00;
       		 for(var k=0;k<$scope.panel_1.sumPayIncome.payChannelIncomes.length;k++){
       			$scope.panel_1.sumPayIncome.payChannelIncomes[k].isShow=true;
       			sum+=$scope.panel_1.sumPayIncome.payChannelIncomes[k].money;
       		 }
       		$scope.panel_1.sumPayIncome.money=sum;
    	 }
    	
    }
    //是否全选
    $scope.isSelectedAll = function () {
        return $scope.payChannelArr.length == $scope.payChannelList.length;
      };
    //判断加载后的区域是否选中
    $scope.isSelected = function (id) {
        return $scope.payChannelArr.indexOf(id) >= 0;
      };
    
    //////////项目类型下拉框
    $scope.oldProjectList=[T.T("世界"),T.T("江湖")];
    $scope.oldProjectArray=[];//新项目数组
    $scope.newProjectArray=[];//旧项目数组
    $scope.newPayChannelList=[]//新支付渠道
    $scope.oldPayChannelList=[];//旧支付渠道
    
    $scope.videoChannelList=[];//视频收入渠道
    $scope.videoChannelArr = []; //筛选后的区域列表
	$scope.videoChannelTextArr = [];
	$scope.videoChannelText = T.T("请选择");
	$scope.showCheckAllTextForVideo=false;//勾选全选时，文本显示全选
	 //勾选区域
    $scope.setChannelListForVideo=function(i){
        var isHad = $scope.videoChannelArr.indexOf(i);
        if(isHad>=0){       
        	$scope.videoChannelArr.splice(isHad,1);
            $scope.videoChannelTextArr.splice(isHad,1);
        }else{
        	$scope.videoChannelArr.push(i);
            $scope.videoChannelTextArr.push(i);
        }  
        if(!$scope.isSelectedAllForVideo()){
        	//非全选状态下
        	$scope.showCheckAllTextForVideo=false;
        }else{
        	//全选状态下
        	$scope.showCheckAllTextForVideo=true;
        }
        if(!$scope.showCheckAllTextForVideo){
			 if($scope.videoChannelTextArr.length>0){
		            $scope.videoChannelText= $scope.videoChannelTextArr.join(",")
		        }else{
		        	$scope.videoChannelText= T.T("请选择")
		        }
		}else{
			$scope.videoChannelText= T.T("全选");
		}
        //显示6个字符
        if($scope.videoChannelText.length>6){
        	$scope.videoChannelText=$scope.videoChannelText.substr(0,6);
        }
        //筛选显示
        if($scope.videoChannelArr.length!=0){
        	if($scope.panel_2.dataList_1.data!=undefined){
          		 for(var i=0;i<$scope.panel_2.dataList_1.data.length;i++){
          			 var obj=$scope.panel_2.dataList_1.data[i].name;
          			 //隐藏
          			 if($scope.videoChannelArr.indexOf(obj)<0){
          				$scope.panel_2.dataList_1.data[i].isShow=false;
          			 }else{
          				$scope.panel_2.dataList_1.data[i].isShow=true;
          			 }
          		 }
          	 }
        }else{
        	if($scope.panel_2.dataList_1.data!=undefined){
         		 for(var i=0;i<$scope.panel_2.dataList_1.data.length;i++){
         			$scope.panel_2.dataList_1.data[i].isShow=true;
         		 }
         	 }
        }
        
//        $scope.getData();
    }
  //点击全选按钮时
    $scope.clickAllSelectForVideo=function(){
    	var isCheckAll=$scope.isSelectedAllForVideo();
    	if(!isCheckAll){
    		//要勾全选
    		$scope.videoChannelArr.splice(0,$scope.videoChannelArr.length);//清空
    		 $scope.videoChannelTextArr.splice(0,$scope.videoChannelTextArr.length);
    		for(var i=0;i<$scope.videoChannelList.length;i++){//添加内容
    			$scope.videoChannelArr.push($scope.videoChannelList[i].name);
	            $scope.videoChannelTextArr.push($scope.videoChannelList[i].name);
    		}
    		$scope.showCheckAllTextForVideo=true;
    	}else{
    		//取消全选
    		$scope.videoChannelArr.splice(0,$scope.videoChannelArr.length);//清空
    		$scope.videoChannelTextArr=[];
    		$scope.showCheckAllTextForVideo=false;
    	}
    		if(!$scope.showCheckAllTextForVideo){
    			 if($scope.videoChannelTextArr.length>0){
    		            $scope.videoChannelText= $scope.videoChannelTextArr.join(",")
    		        }else{
    		        	$scope.videoChannelText= T.T("请选择")
    		        }
    		}else{
    			$scope.videoChannelText= T.T("全选");
    		}
    	
    	 if($scope.videoChannelText.length>6){
         	$scope.videoChannelText=$scope.videoChannelText.substr(0,6);
         }
    	  if($scope.panel_2.dataList_1.data!=undefined){
		   		 for(var i=0;i<$scope.panel_2.dataList_1.data.length;i++){
		   			$scope.panel_2.dataList_1.data[i].isShow=true;
		   		 }
		   	 }
    }
    //是否全选
    $scope.isSelectedAllForVideo = function () {
        return $scope.videoChannelArr.length == $scope.videoChannelList.length;
      };
    //判断加载后的区域是否选中
    $scope.isSelectedForVideo = function (id) {
        return $scope.videoChannelArr.indexOf(id) >= 0;
      };
      
	//项目列表
    $scope.getProjectList=function(){
    	var dat={id:-1,name:T.T("全部")}; 
    	$http.post("project/projectList.action",{}).success(function(data){
    		$scope.oldProjectArray=[];
    	    $scope.newProjectArray=[];
    		for(var i=0;i<data.length;i++){
				var isContain=$scope.oldProjectList.indexOf(data[i].name);
				//旧项目
				if(isContain>=0){
					$scope.oldProjectArray.push(data[i]);
				}else{
					//新项目
					$scope.newProjectArray.push(data[i])
				}
			}
         //是新项目
   		if($scope.postData.checktype1==1){
   			$scope.newProjectArray.unshift(dat);
   			$scope.panel_1.projectList=$scope.newProjectArray;
   			$scope.payChannelList=$scope.newPayChannelList;
   		}else if($scope.postData.checktype1==2){
   			//旧项目
   			$scope.oldProjectArray.unshift(dat);
   			$scope.panel_1.projectList=$scope.oldProjectArray;
   			$scope.payChannelList=$scope.oldPayChannelList;
   		}
   		$scope.payChannelArr=[];//清除勾选渠道
   		$scope.payChannelList=[];
   		$scope.channelText= T.T("请选择");
   		$scope.channelTextArr = [];
   		$scope.postData.projectid=$scope.panel_1.projectList[0].id;
    	});
    }
    //视频收入
    $scope.selectUscAdvertIncomeList=function(){
    	$http.post("balanceplat/selectUscAdvertIncomeList.action",$scope.postData).success(function(data){
    		if(data==undefined ||data==null){
    			data=[];
    		}
    		$scope.videoChannelArr=[];
    		$scope.panel_2.dataList_1.data=data;
    		  if($scope.panel_2.dataList_1.data!=undefined){
		   		 for(var i=0;i<$scope.panel_2.dataList_1.data.length;i++){
		   			$scope.panel_2.dataList_1.data[i].isShow=true;
		   		 }
		   	 }
    	});
    }
    //视频渠道筛选
    $scope.selectUscAdvertChannelList=function(){
    	$http.post("balanceplat/selectUscAdvertChannelList.action",$scope.postData).success(function(data){
    		if(data==undefined ||data==null){
    			data=[];
    		}
    		$scope.videoChannelList=data;
    	});
    }
    
    //加载数据
	$scope.getData=function(){
		$scope.postData.startdate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
 	    $scope.postData.enddate = $scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
		console.log($scope.postData);
		$http.post("balanceplat/selectPayChannelAccountingList.action",$scope.postData).success(function(data){
			if(data == undefined || data == null){
				data = [];
			}
			$scope.payChannelArr=[];
			$scope.panel_1.sumPayIncome.payChannelIncomes=[];
			if(data.length!=0){
				$scope.panel_1.sumPayIncome.payChannelSize=data[0].payChannelIncomes.length;
				for(var i=0;i<$scope.panel_1.sumPayIncome.payChannelSize;i++){
					$scope.panel_1.sumPayIncome.payChannelIncomes[i]={'money':0,'channelName':''};
				}
			}
			var sumMoney=0.00;
			for(var i=0;i<data.length;i++){
				sumMoney+=data[i].money;
			}
			$scope.panel_1.sumPayIncome.money=sumMoney;//总收入
			//汇总各支付渠道收入
			for(var i=0;i<data.length;i++){
				for(var k=0;k<data[i].payChannelIncomes.length;k++){
					$scope.panel_1.sumPayIncome.payChannelIncomes[k].money+=data[i].payChannelIncomes[k].money;
					$scope.panel_1.sumPayIncome.payChannelIncomes[k].channelName=data[i].payChannelIncomes[k].channelName;
					$scope.panel_1.sumPayIncome.payChannelIncomes[k].isShow=true;
					data[i].payChannelIncomes[k].isShow=true;
				}
			}
			if(data.length!=0){
				//全选时
				if($scope.payChannelArr.length==0||$scope.showCheckAllText==true){
					$scope.payChannelList=[];
					   //是新项目
			   		if($scope.postData.checktype1==1){
			   			$scope.newPayChannelList=data[0].payChannelIncomes;
			   			$scope.payChannelList=$scope.newPayChannelList;
			   		}else if($scope.postData.checktype1==2){
			   			//旧项目
			   			$scope.oldPayChannelList=data[0].payChannelIncomes;
			   			$scope.payChannelList=$scope.oldPayChannelList;
			   		}
					for(var i=0;i<$scope.payChannelList.length;i++){
						var id=$scope.payChannelList[i].channelName;
						$scope.payChannelList[i].id=id;
					}
				}
			}
			$scope.panel_1.dataList_1.data = data; 
    	});
		//视频收入
		$scope.selectUscAdvertIncomeList();
		//视频筛选渠道列表
		$scope.selectUscAdvertChannelList();
	}
	
	
	$scope.delChannelbalance=function(data,name){
		console.log(data)
	}
	$scope.getGameList();
	$scope.getProjectList();
}