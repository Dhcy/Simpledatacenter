function rechgGainWarnModifController($scope, $http, $stateParams, $state,T) {
	$scope.ctype = $stateParams.ctype;
	$scope.warn_id = $stateParams.regchWarnId;
	$scope.title = $scope.ctype == 1 ? T.T("添加") : T.T("修改");
	$scope.rechgWarnData = {
		id : $scope.ctype == 1 ? "" : $scope.warn_id,
		rechgTime : "",
		rechgMoney :"",
		gainTime :"",
		gainCount : "",
		warmThreShold :100,
		msgMail : "",
		gameId:58,
		rechgRate:""
	}
	$scope.isDisable=false;
	$scope.gameList=[];
	// 重置参数
	$scope.resetData = function() {
		$scope.rechgWarnData = {};
	}
	
	$scope.isInteger=function(obj){
		return typeof obj == 'number' && obj%1 ==0;
	}
	//是否为大于等于0整数
	$scope.isHighZeroInteger=function(obj) {
		var reg=/^-?\d+$/;//整数
		if(!reg.test(obj)){
			return false;
		}
		var integer=parseInt(obj);
		if(!$scope.isInteger(integer)){
			return false;
		}else{
			return integer>=0;
		}
	}
	//是否为大于等于-1
	$scope.isHighMinuOneInteger=function(obj) {
		var reg=/^-?\d+$/;//整数
		if(!reg.test(obj)){
			return false;
		}
		var integer=parseInt(obj);
		if(!$scope.isInteger(integer)){
			return false;
		}else{
			if(obj>=-1){
				return true;
			}
			return false;
		}
		
		
	}
	//j校验邮箱
	$scope.checkMail=function(szMail){
		var szReg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; 
		return szReg.test(szMail)?true:false; 
	}
	//添加数据表单检验
	$scope.checkFormByAdd=function(){
		if(!$scope.rechgWarnData.gameId){
			alert(T.T("游戏不能为空"));
			return false;
		}
		if($scope.rechgWarnData.rechgTime){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.rechgTime)){
				alert(T.T("充值次数格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if($scope.rechgWarnData.rechgMoney){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.rechgMoney)){
				alert(T.T("充值金额格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if($scope.rechgWarnData.gainTime){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.gainTime)){
				alert(T.T("格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.gainCount){
			alert(T.T("获取金额不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.gainCount)){
				alert(T.T("获取金额格式错误,请输入正整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.warmThreShold){
			alert(T.T("告警阀值不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.warmThreShold)){
				alert(T.T("告警阀值格式错误,请输入正整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.rechgRate){
			alert(T.T("充值比例不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.rechgRate)){
				alert(T.T("充值比例格式错误,请输入正整数"));
				return false;
			}
		}
		//
		var mail=$scope.rechgWarnData.msgMail;
		if(mail==undefined||mail==''){
			alert(T.T("邮箱不能为空"));
			return false;
		}
		var strs= new Array();
		strs=mail.split(",");
		for(var i=0;i<strs.length;i++){
			if(!$scope.checkMail(strs[i])){
				alert(T.T("邮箱格式不正确!请检查输入"));
				return false;
			}
		}
		return true;
		
		
	}
	//修改数据检验
	$scope.checkFormByUpdate=function(){
		if($scope.rechgWarnData.rechgTime){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.rechgTime)){
				alert(T.T("充值次数格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if($scope.rechgWarnData.rechgMoney){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.rechgMoney)){
				alert(T.T("充值金额格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if($scope.rechgWarnData.gainTime){
			if(!$scope.isHighMinuOneInteger($scope.rechgWarnData.gainTime)){
				alert(T.T("获取次数格式错误,请输入大于-1的整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.gainCount){
			alert(T.T("获取金额不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.gainCount)){
				alert(T.T("获取金额格式错误,请输入正整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.warmThreShold){
			alert(T.T("告警阀值不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.warmThreShold)){
				alert(T.T("告警阀值格式错误,请输入正整数"));
				return false;
			}
		}
		
		if(!$scope.rechgWarnData.rechgRate){
			alert(T.T("充值比例不能为空"));
			return false;
		}else{
			if(!$scope.isHighZeroInteger($scope.rechgWarnData.rechgRate)){
				alert(T.T("充值比例格式错误,请输入正整数"));
				return false;
			}
		}
		//
		var mail=$scope.rechgWarnData.msgMail;
		if(mail==undefined||mail==''){
			alert(T.T("邮箱不能为空"));
			return false;
		}
		var strs= new Array();
		strs=mail.split(",");
		for(var i=0;i<strs.length;i++){
			if(!$scope.checkMail(strs[i])){
				alert(T.T("邮箱格式不正确!请检查输入"));
				return false;
			}
		}
		return true;
		
		
	}
	
	$scope.postData = function() {
		if ($scope.ctype == 1) {
			if(!$scope.checkFormByAdd()){
				return;
			}
			$http.post("rechgWarn/addRechgGainWarn.action", {
				"id" : $scope.rechgWarnData.id,
				"rechgTime" : $scope.rechgWarnData.rechgTime,
				"rechgMoney" : $scope.rechgWarnData.rechgMoney,
				"gainTime" : $scope.rechgWarnData.gainTime,
				"gainCount" : $scope.rechgWarnData.gainCount,
				"warmThreShold" : $scope.rechgWarnData.warmThreShold,
				"msgMail" : $scope.rechgWarnData.msgMail,
				"gameId":$scope.rechgWarnData.gameId,
				"rechgRate":$scope.rechgWarnData.rechgRate,
			}).success(function(data) {
				if (data.status == 0) {
					alert(data.message);
				} else {
					$scope.backGo();
				}
			}).error(function(data) {
				alert(data.message);
			});
		} else if ($scope.ctype == 2) {
			if(!$scope.checkFormByUpdate()){
				return;
			}
			$http.post("rechgWarn/editRechgGainWarn.action", $scope.rechgWarnData).success(
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
		$state.go('main.config.rechgGainWarn');
	}

	$scope.getWarnData = function() {
		$http.post("rechgWarn/getRechgGainWarn.action", {
			"id" : $scope.warn_id
		}).success(function(data) {
			$scope.rechgWarnData = data;
		})
	}
	$scope.getGameList=function(){
//		$http.get("search/selectUserGameList.action").success(function(data) {
//			if(data!=undefined||data!=null){
//				$scope.gameList = data;
//			}
//		})
		$http.get("search/findAllGameList.action",{projectid:-1}).success(function(data) {
			if(data!=undefined||data!=null){
				$scope.gameList = data;
			}
		})
	}
	$scope.ctype == 1 ? "" : $scope.getWarnData();
	$scope.ctype == 1 ? $scope.isDisable=false:$scope.isDisable=true;
	
	$scope.getGameList();
	

}
