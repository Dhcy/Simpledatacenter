var data = {
	"indexTitle":"日志平台"
};
var helpData = {

};

//var te={a:"游戏概览2"}


Date.prototype.format = function(fmt)   
{ 
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  
var _color=["#6DC5FD"];
var $G_pageLimitCount=0;
var $main = angular.module("index",["ui.router",'pascalprecht.translate','ui.bootstrap','ui.select','ngFileUpload']);
$main
	.controller("indexController",indexController)
	.controller("userBarController",userBarController)
	.controller("mainController",mainController)
	.controller("menuController",menuController)

	.controller("summaryController",summaryController)
	.controller("summaryindexController",summaryindexController)
	.controller("reportController",reportController)
	.controller("datacomparedController",datacomparedController)
	.controller("logRealTimeDataController",logRealTimeDataController)
//	.controller("logPlayerDataController",logPlayerDataController)
	

	.controller("playersController",playersController)
	.controller("playersNewController",playersNewController)
	.controller("playersDynamicController",playersDynamicController)
	.controller("playersDeviceController",playersDeviceController)
	.controller("playersRetentionController",playersRetentionController)
	.controller("playersBehaviorController",playersBehaviorController)
	.controller("playersConversionController",playersConversionController)
	.controller("playersChumController",playersChumController)
	.controller("playersRollServController",playersRollServController)
	.controller("accountLostController",accountLostController)
	
	
	.controller("realTimeDataController",realTimeDataController)
	.controller("realTimeSummaryDataController",realTimeSummaryDataController)
	
	
	
	.controller("concurrentsController",concurrentsController)
	.controller("concurrentController",concurrentController)
	.controller("concurrentDataController",concurrentDataController)
	
	.controller("revenueController",revenueController)
	.controller("revenueDataController",revenueDataController)
	.controller("revenueConversionController",revenueConversionController)
	.controller("revenueValueController",revenueValueController)
	.controller("revenueBehaviorController",revenueBehaviorController)
	.controller("revenueRetationController",revenueRetationController)
	.controller("revenueLtvController",revenueLtvController)
	

    .controller("whaleUserController",whaleUserController)
	.controller("whalesController",whalesController)
    
	.controller("channelsController",channelsController)
	.controller("channelsDataController",channelsDataController)

	.controller("virtualController",virtualController)
	.controller("virtualCurrencyController",virtualCurrencyController)
	.controller("virtualExpenseController",virtualExpenseController)
	.controller("virtualItemsController",virtualItemsController)

	.controller("levelsController",levelsController)
	.controller("levelsDetailController",levelsDetailController)
	.controller("levelsDistributeController",levelsDistributeController)
	.controller("levelsProgressController",levelsProgressController)


	.controller("eventsController",eventsController)
	.controller("eventsDataController",eventsDataController)
	.controller("eventsDetailController",eventsDetailController)
	
	.controller("giftController",giftController)
	.controller("giftDataController",giftDataController)
	.controller("versionController",versionController)
	.controller("versionDataController",versionDataController)

	.controller("userController",userController)
	.controller("userManageController",userManageController)
	.controller("userModifyController",userModifyController)
	.controller("resourcesController",resourcesController)
	.controller("roleController",roleController)
	.controller("roleModifController",roleModifController)
	.controller("firstMenuController",firstMenuController)
	.controller("firstMenuModifController",firstMenuModifController)
	.controller("permissionController",permissionController)
	.controller("permissionModifController",permissionModifController)

	.controller("configController",configController)
	.controller("gameareaController",gameareaController)
	.controller("gameareaModifController",gameareaModifController)
	.controller("platformController",platformController)
	.controller("platformModifController",platformModifController)
	.controller("projectController",projectController)
	.controller("projectModifController",projectModifController)
	.controller("gameController",gameController)
	.controller("gameModifController",gameModifController)
	.controller("channelController",channelController)
	.controller("channelModifController",channelModifController)
	.controller("subchannelController",subchannelController)
	.controller("subchannelModifController",subchannelModifController)
	.controller("gameChannelController",gameChannelController)
	.controller("analysisController",analysisController)
	.controller("warningController",warningController)
	.controller("gameversionController",gameversionController)
	.controller("gameversionModifyController",gameversionModifyController)
	.controller("gamelanguageController",gamelanguageController)
	.controller("gamelanguageModifyController",gamelanguageModifyController)
	.controller("rechgGainWarnController",rechgGainWarnController)
	.controller("rechgGainWarnModifController",rechgGainWarnModifController)
	.controller("attrValueController",attrValueController)



	.controller("orderStatisticsController",orderStatisticsController)
	/*.controller("adStatisticsController",adStatisticsController)
	.controller("channelsModifyController",channelsModifyController)*/
	.controller("adChannelsController",adChannelsController)
	.controller("adChannelsModifyController",adChannelsModifyController)
	.controller("adMainListController",adMainListController)
	.controller("adMainListModifyController",adMainListModifyController)
	.controller("amountManageController",amountManageController)
	.controller("amountManageModifyController",amountManageModifyController)
	.controller("dataGatherController",dataGatherController)
	.controller("dataQueryController",dataQueryController)
	.controller("registerPeriodController",registerPeriodController)
	.controller("ranksDistributeController",ranksDistributeController)
	.controller("qdUserRegInfoController",qdUserRegInfoController)
	.controller("advertiserDataController",advertiserDataController)
	.controller("advertiserQuerController",advertiserQuerController)


	.controller("advertiserplatController",advertiserplatController)



	.controller("balanceplatController",balanceplatController)
	.controller("channelbalanceController",channelbalanceController)
	.controller("channelbalanceModifyController",channelbalanceModifyController)
	.controller("billingformController",billingformController)
	.controller("billingformbyweekController",billingformbyweekController)
	.controller("paychannelController",paychannelController)
	.controller("autobalanceController",autobalanceController)
	.controller("applebalanceController",applebalanceController)
	.controller("channelCompanyController",channelCompanyController)
	.controller("channelCompanyModifyController",channelCompanyModifyController)
	.controller("videoAdvertiserController",videoAdvertiserController)
	.controller("videoAdvertiserModifController",videoAdvertiserModifController)
	//合同文件
	.controller("contractFileController",contractFileController)
	
	
	/*sdk 数据分析*/
	.controller("sdkDataController",sdkDataController)
	.controller("sdkdataSummaryController",sdkdataSummaryController)
	.controller("sdkdataBrowserController",sdkdataBrowserController)
	.controller("sdkdataDownloadController",sdkdataDownloadController)
	.controller("sdkdataDownSrcController",sdkdataDownSrcController)
	.controller("sdkdataLoginAnalysisController",sdkdataLoginAnalysisController)
	.controller("sdkdataNewAddController",sdkdataNewAddController)
	.controller("sdkdataNewVipController",sdkdataNewVipController)
	.controller("sdkdataVipDistController",sdkdataVipDistController)
	.controller("sdkdataVipSummaryController",sdkdataVipSummaryController)
	.controller("indpdController",indpdController)
	
	//dau模型
	.controller("dauModeController",dauModeController)
	
	.controller("dauAndIncomeController",dauAndIncomeController)
	//项目配置
	.controller("projectConfigController",projectConfigController)
	//af_push_api
	.controller("afPushApiController",afPushApiController)
	
	.controller("afPurchaseController",afPurchaseController)
	/*香港财务统计*/
	.controller("xgFinanceController",xgFinanceController)
	.controller("xgFinanceCostController",xgFinanceCostController)
	.controller("xgFinanceGameUserController",xgFinanceGameUserController)
	/*财务统计*/
	.controller("financeStatController",financeStatController)
	.controller("financeGameUserController",financeGameUserController)
	
	/*hie运营数据*/
	.controller("yunyindataController",yunyindataController)
	/*hie消费数据*/
	.controller("consumedataController",consumedataController)
	

	
	
	
$main.run(['$rootScope', '$state', '$stateParams',
    function($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
]).config(function ($translateProvider) {
	var lang = g_lang,
		lang_en = g_lang.en || {} ,
		lang_zh = g_lang.zh || {} ;
/*	$translateProvider.translations('en', lang_en);
	$translateProvider.translations('zh', lang_zh);*/
	
	
	 $translateProvider.useStaticFilesLoader({
		  files: [{
		    prefix: 'dist/lang/',//语言包路径
		    suffix: '.json'   //语言包后缀名
		  }]
		});
	 
	 
	$translateProvider.preferredLanguage(lang.default);
})
	.factory('T', ['$translate', function($translate) {
		var T = {
			T:function(key) {
				if(key){
					return $translate.instant(key);
				}
				return key;
			}
		}
		return T;
	}])
    
    .filter('propsFilter', function() {
      return function(items, props) {
        var out = [];
        if (angular.isArray(items)) {
          var keys = Object.keys(props);
          items.forEach(function(item) {
            var itemMatches = false;
            for (var i = 0; i < keys.length; i++) {
              var prop = keys[i];
              var text = props[prop].toLowerCase();
              if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                itemMatches = true;
                break;
              }
            }

            if (itemMatches) {
              out.push(item);
            }
          });
        } else {
          out = items;
        }
        return out;
      };
    })
	.filter('pageStartFrom', [function() {
  return function(input, start) {
	if(input && !!input.slice){
		
    start = +start;	 
    $G_pageLimitCount=(input.slice(start)).length;
    return input.slice(start);
	}
  }
}])	//分页过滤器

	.filter('searchFilter', [function() {
  return function(input,id) {
    var arr=[];
	for(var i in input)
	{
			if(input[i].id==id)
			arr.push(input[i])
	}
    return arr;	  
  }
}])
	.filter('keyFilter', [function() {
  return function(input,key1,key2,v) {
    var arr=[];
	for(var i in input)
	{
		if(input[i]){
			if(v){
				if(input[i][key1])
				{
					if(input[i][key1][key2]){
						if(input[i][key1][key2]==v){
							arr.push(input[i])
						}
					}
				}
			}else{
				if(input[i][key1])
				{
					if(input[i][key1]==key2)
					arr.push(input[i])
				}
			}
		}
	}
    return arr;	  
  }
}])
    .filter('gameFilter', [function() {
  return function(input,id) {
    var arr=[];
	for(var i in input)
	{
		if(input[i].projectid==id)
			arr.push(input[i])
	}
    return arr;
  }
}])	//搜索过滤器
	


    ;
/************************Directive************************************/
var b=0;

$main
	.directive("userbar",function(){
	return {
		restrict:"A",
		scope: {
			params :"=",
			gamesyslist :　"=",
            		user : "=",
            		dialogopen : "=",
            publicplatform : "="
		},
		templateUrl:function(){return "template/userBar.html?id"},
		controller:"userBarController"
	}
})
	.directive("mainmenu",function(){
	return {
		restrict:"A",
		scope: true,
		templateUrl:function(){return "template/menu.html?random="+Math.random()},
		controller:"menuController",
		link:function($scope,ele,attrs){
			$(document).ready(function(){
				var btns=document.getElementsByClassName("menus");
				for(var i=0;i<btns.length;i++){
					unselect(btns[i])
				}
				var btns=document.getElementsByClassName("pic_btn");
				for(var i=0;i<btns.length;i++){
					unselect(btns[i])
				}
				var btns=document.getElementsByClassName("limitBtn");
				for(var i=0;i<btns.length;i++){
					unselect(btns[i])
				}
				var btns=document.getElementsByTagName("th");
				for(var i=0;i<btns.length;i++){
					unselect(btns[i])
				}
				var isfirst=true,act;
				act=setInterval(function(){
					if(!isfirst)
					{isfirst=false;	clearInterval(act);return;}
					if($(".menu_act").length==1)
					$(".menu_act").css("display","block");
				},100);
			});
			
			$scope.menuBtnClick =function ($event){
				var em=$($event.target);
				if(!em.next().hasClass("menu_act") && em.next().css("display")=="block"){
					em.next().slideUp(400,function(){
							em.next().removeClass("show").removeClass("menu_act");
						});
					return;
				}
				if(em.next().hasClass("menu_act"))
				{	
					if(em.next().find(".active").length==1)
					{
						return;
					}
					em.removeClass("act");
					em.next().slideUp(400,function(){
							em.next().removeClass("show").removeClass("menu_act");
						});
					return ;

				}
if(!em.hasClass("icon"))	em.addClass("act").next().addClass("menu_act").slideDown(400);
			}
		}
	}
})
	.directive("maindirective",function(){
		return{
			restrict:'A',	
			scope:true,
			controller:function($scope,$http){
				this.setmstatdate=function(v){$scope.mstatdate=v;$scope.mchange.date=true;}
				this.setmchannel=function(v){$scope.mchannel=v;$scope.mchange.channelQf=true;}
				this.setmareaclothing=function(v){$scope.mareaclothing=v;$scope.mchange.channelQf=true;}
				this.setmversion=function(v){$scope.mversion=v;$scope.mchange.channelQf=true;}
				this.setmlang=function(v){$scope.mlang=v;$scope.mchange.channelQf=true;}
				
				$scope.channeldata=[];
				$scope.areaClothingData=[];
				$scope.versiondata=[];
				$scope.langdata=[];
				this.callbackchannel=function(){return $scope.channeldata}
				this.callbackqf=function(){return $scope.areaClothingData}
				this.callbackversion=function(){return $scope.versiondata}
				this.callbacklang=function(){return $scope.langdata}
				$scope.set7day=function(){
					function unix_to_datetime(unix) {
						var now = new Date(parseInt(unix));
						return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();

					}
					var d=new Date();
					var nd=d.getTime();
					var dd=d.getTime();
					dd-=604800000;
					return $scope.mstatdate=unix_to_datetime(dd)+" ~ "+unix_to_datetime(nd);					
				}
				
				
				$scope.startCallback=0;
			    //获取下拉筛选框数据
			    $scope.getSelectInfo=function(){
			        
			        var gameid = $scope.$Params.gameid;
			    
			        $http.post("gamearea/gameareaList.action", {
			        	"gameid": gameid
			        }).success(
			        	function (data) {
			        		$scope.areaClothingData = data;
			        	})

			        $http.post("channel/getSelectChannels.action", {
			        	"gameid": gameid
			        }).success(
			        	function (data) {
			        		$scope.channeldata = data;
			        	})
			       /* $http.post("game/getLanguageList.action", {
			        	"gameid": gameid
			        }).success(
			        	function (data) {
			        		$scope.langdata = data;
			        	})*/
			        $http.post("game/getGameLanguageList.action", {
			        	"gameid": gameid
			        }).success(
			        	function (data) {
			        		$scope.langdata = data;
			        	})
			        $http.post("game/getGameVersionList.action", {
			        	"gameid": gameid
			        }).success(
			        	function (data) {
			        		$scope.versiondata = data;
			        	})
			    
			    }
			    
			    $scope.getSelectInfo();
			},
			link:function($scope, ele, attrs){
				$scope.mstatdate= $scope.set7day();				
				$scope.mchannel=new Array();
				$scope.mareaclothing=new Array();
				$scope.mversion=[];	//游戏版本
				$scope.mlang=[];	//游戏语言
				$scope.mchange={date:false,channelQf:false}
				
				$scope.mdownloaddata=function(type){
					alert(type);
				}
				
					
				$scope.ischannel=false;
				$scope.isqf=false;
				$scope.isversion=false;
				$scope.islang=false;
				$scope.iscallback=false;
				$scope.callbackchannelqfdata={'channellist':[],'qflist':[],'versionlist':[],'langlist':[]};
				$scope.callbackChannelQf=function(){
					$scope.callbackchannelqfdata={'channellist':[],'qflist':[],'versionlist':[],'langlist':[]};
					for(var i=0;i<$scope.mchannel.length;i++){
						for(var k=0;k<$scope.channeldata.length;k++){
							if($scope.mchannel[i].id==$scope.channeldata[k].id){		
								$scope.callbackchannelqfdata.channellist.push($scope.mchannel[i]);
								$scope.iscallback=true;
								$scope.ischannel=true;
							}
						}
					}
					for(var i=0;i<$scope.mareaclothing.length;i++){
						for(var k=0;k<$scope.areaClothingData.length;k++){	
							if($scope.mareaclothing[i]==$scope.areaClothingData[k].id)
							{
								$scope.callbackchannelqfdata.qflist.push($scope.areaClothingData[k].name);
								$scope.iscallback=true;
								$scope.isqf=true;
							}
						}
					}
					for(var i=0;i<$scope.mversion.length;i++){
						for(var k=0;k<$scope.versiondata.length;k++){
							if($scope.mversion[i].version_id==$scope.versiondata[k].version_id){		
								$scope.callbackchannelqfdata.versionlist.push($scope.mversion[i]);
								$scope.iscallback=true;
								$scope.isversion=true;
							}
						}
					}
					for(var i=0;i<$scope.mlang.length;i++){
						for(var k=0;k<$scope.langdata.length;k++){
							if($scope.mlang[i].language_id==$scope.langdata[k].language_id){		
								$scope.callbackchannelqfdata.langlist.push($scope.mlang[i]);
								$scope.iscallback=true;
								$scope.islang=true;
							}
						}
					}
					if($scope.callbackchannelqfdata.channellist.length==0){
						$scope.ischannel=false;
					}
					if($scope.callbackchannelqfdata.qflist.length==0){
						$scope.isqf=false;
					}
					if($scope.callbackchannelqfdata.versionlist.length==0){
						$scope.isversion=false;
					}
					if($scope.callbackchannelqfdata.langlist.length==0){
						$scope.islang=false;
					}
					if( !$scope.ischannel &&  !$scope.isqf &&  !$scope.isversion &&  !$scope.islang )
					{
						$scope.iscallback=false;
					}
					
				}
			}
		}
	})
	.directive('datepicker', function () {
        return {
            restrict: 'A',			
            controller: function($scope){},
            templateUrl: 'template/public/datepicker.html?t='+Math.random(),
			require:"^maindirective",
            scope: {
				mstatdate:'=',
                format:'@',
                mode:'@',
                maxmode:'@',
                minmode:'@',
                maxdate:'@',
                mindate:'@',
                showPrevDay:'='
			},
            link: function ($scope, ele, attrs,maindirectiveCtrl) {
                var self = this;
				$scope.inputValue="";
				$scope.startTime="";
				$scope.endTime="";
//				$scope.format="yyyy";
				$scope.mstatdate?$scope.inputValue=$scope.mstatdate:0;
				var callbackDate=($scope.mstatdate).split("~");
				if(callbackDate[1])
				{
						$scope.startTime=unix_to_datetime(callbackDate[0]);
						$scope.endTime=unix_to_datetime(callbackDate[1]);
                    $scope.inputValue = $scope.startTime+" ~ "+$scope.endTime;
				}else{
						$scope.startTime=unix_to_datetime(callbackDate[0]);
						$scope.endTime=unix_to_datetime(callbackDate[0]);	
                     $scope.inputValue = $scope.startTime;
				}
				// $(".datetimepicker").remove();
				// $('#startTime').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",endDate:(new Date()),viewSelect:'4',maxView:[4, 'decade']}).on('changeDate',function(ev){
				//   	if((new Date($('#startTime').val())).getTime() > (new Date($('#endTime').val())).getTime()){
				// 		$('#startTime').val('').attr("placeholder","日期有误");
				// 	}
				// });
				// $('#endTime').datetimepicker({ autoclose: true, todayHighlight: true ,language:'zh-CN' ,format:"yyyy-mm-dd",endDate:(new Date()),viewSelect:'4',maxView:[4, 'decade']}).on('changeDate',function(ev){
				//   	if((new Date($('#startTime').val())).getTime() > (new Date($('#endTime').val())).getTime()){
				// 		$('#endTime').val('').attr("placeholder","日期有误");
				// 	}
				// });
				 
				$scope.$watch("inputValue",function(nv,ov){
					maindirectiveCtrl.setmstatdate(nv);
				});

				$scope.show=function($event){
					$('.timeDialog').slideDown(200);
				}
				$scope.hide=function($event){
					$('.timeDialog').slideUp(200);
				}

				$scope.qd=function($event){		
					//$scope.startTime=$('#startTime').val();
					if(!$scope.startTime)return;
					//$scope.endTime=$('#endTime').val();
					if(!$scope.endTime)return;
					unix_to_datetime($scope.startTime) == unix_to_datetime($scope.endTime) ? $scope.inputValue = unix_to_datetime($scope.endTime) : $scope.inputValue = unix_to_datetime($scope.startTime) + " ~ " + unix_to_datetime($scope.endTime);
					
					$scope.hide();
				}


				$scope.selectTime=function(t){
					var d=new Date();
					var nd=d.getTime();
					var dd=d.getTime();
					if(t==0){
						$scope.inputValue=unix_to_datetime(dd);
						$scope.startTime=$scope.inputValue;
						$scope.endTime=$scope.inputValue;
					}
					else if(t==-1){
						dd-=86400000;
						$scope.inputValue=unix_to_datetime(dd);
						$scope.startTime=$scope.inputValue;
						$scope.endTime=$scope.inputValue;
					}
					else if(t==7){		
						dd-=604800000;
						$scope.inputValue=unix_to_datetime(dd)+" ~ "+unix_to_datetime(nd);
						$scope.startTime=unix_to_datetime(dd);
						$scope.endTime=unix_to_datetime(nd);
					}
					else if(t==30){			
						dd-=2592000000;
						$scope.inputValue=unix_to_datetime(dd)+" ~ "+unix_to_datetime(nd);
						$scope.startTime=unix_to_datetime(dd);
						$scope.endTime=unix_to_datetime(nd);
					}
					else if(t==-2){}
					else if (t == true) {
						//设置endTime为当前日期前一天
						nd -= 86400000;
						dd = nd;
						dd-=604800000;
						$scope.inputValue=unix_to_datetime(dd)+" ~ "+unix_to_datetime(nd);
						$scope.startTime=unix_to_datetime(dd);
						$scope.endTime=unix_to_datetime(nd);
					}
					
					$scope.hide();
				}
				function unix_to_datetime(unix) {
					var now = new Date((unix));
                    if(!$scope.format)
					return now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
                    else{
                        return now.format($scope.format);
                    }

				}
                
				 $scope.dateOptions = {
                    //dateDisabled: disabled,
                    formatYear: 'yy',
                    timezone:'+0800',
                    mode:$scope.mode || 'day', 
                    minMode:$scope.minmode || '', 
                    maxMode:$scope.maxmode || '', 
                    maxDate: $scope.maxdate?new Date($scope.maxdate):"",
                    minDate: new Date(2000,1,1),
                    startingDay: 1
                  };
				
                $scope.open1 = function() {
//                    if($scope.endTime){
//                        $scope.dateOptions.maxDate = new Date($scope.endTime);
//                        $scope.dateOptions.minDate = new Date(2000,1,1);
//                    }else{
//                        $scope.dateOptions.maxDate = $scope.maxdate?new Date($scope.maxdate):"";
//                    }
                    $scope.popup1.opened = true;
                  };
                $scope.popup1 = {
                    opened: false
                  };
                $scope.open2 = function() {
//                    if($scope.startTime){
//                        $scope.dateOptions.minDate = new Date($scope.startTime);
//                        $scope.dateOptions.maxDate = $scope.maxdate?new Date($scope.maxdate):"";
//                    }else{
//                        $scope.dateOptions.minDate = new Date(2000,1,1);
//                    }
                    $scope.popup2.opened = true;
                  };
                $scope.popup2 = {
                    opened: false
                };
                //
                $scope.selectTime($scope.showPrevDay)
            }
        };
})	
    .directive('selectchoose', function () {
        return {
            restrict: 'A',
            controllerAs: 'dc',
			require:'^maindirective',
            templateUrl: 'template/public/selectChoose.html?t='+Math.random(),
            scope: true,
            controller: function($scope){
					$scope.selectData={'channel':[],'qf':[],'version':[],'lang':[]};
					$scope.showSubChannelData=[];
					$scope.selectChannelSum=0;
					$scope.selectAreaClothingSum=0;
					$scope.selectVersionSum=0;
					$scope.selectLangSum=0;
					$scope.channelSearch="";
					$scope.areaClothingSearch="";
					$scope.versionSearch="";
					$scope.langSearch="";
					$scope.version= $scope.mversion ||  "";
					$scope.lang= $scope.mlang || "";
					//$scope.channeldata=channelData;
					//$scope.areaClothingData=areaClothingData;
					$scope.updateSum=function(){
						$scope.selectChannelSum=$scope.selectData.channel.length;
						$scope.selectAreaClothingSum=$scope.selectData.qf.length;
						$scope.selectVersionSum=$scope.selectData.version.length;
						$scope.selectLangSum=$scope.selectData.lang.length;
					}
					$scope.callbackChanel=function(){
						for(var i=0;i<$scope.mchannel.length;i++){
							for(var k=0;k<$scope.channeldata.length;k++){
								if($scope.mchannel[i].id==$scope.channeldata[k].id){
                                    var o = {
                                        id: $scope.channeldata[k].id,
                                        channelSimName: $scope.channeldata[k].channelSimName,
                                        subChannelList: []
                                    }
                                    $scope.selectData.channel.push(o);
                                    $scope.showSubChannelList($scope.channeldata[k].id);
                                  for(var n=0;n<$scope.mchannel[i].subChannelList.length;n++){
                                     $scope.addSubChannelList($scope.channeldata[k].id,$scope.mchannel[i].subChannelList[n].subId)
                                    }
									$scope.isChannel=true;
								}
							}
						}
						$scope.updateSum();
					}
					$scope.callbackQf=function(){
						for(var i=0;i<$scope.mareaclothing.length;i++){
							for(var k=0;k<$scope.areaClothingData.length;k++){
								if($scope.mareaclothing[i]==$scope.areaClothingData[k].id){
									$scope.selectData.qf.push($scope.areaClothingData[k].id);
									$scope.isAreaClothing=true;
								}
							}
						}
						$scope.updateSum();
					}
					$scope.callbackVersion=function(){
						for(var i=0;i<$scope.mversion.length;i++){
							for(var k=0;k<$scope.versiondata.length;k++){
								if($scope.mversion[i].version_id==$scope.versiondata[k].version_id){
									var o = {
                                        version_id: $scope.versiondata[k].version_id,
                                        version_name: $scope.versiondata[k].version_name
                                    }
									$scope.selectData.version.push(o);
									$scope.isVersion=true;
								}
							}
						}
						$scope.updateSum();
					}
					$scope.callbackLang=function(){
						for(var i=0;i<$scope.mlang.length;i++){
							for(var k=0;k<$scope.langdata.length;k++){
								if($scope.mlang[i].id==$scope.langdata[k].language_id){
								
								var o = {
                                        language_id: $scope.langdata[k].language_id,
                                        language_name: $scope.langdata[k].language_name,
                                    }	
									$scope.selectData.lang.push(o);
									$scope.isLang=true;
								}
							}
						}
						$scope.updateSum();
					}
					$scope.hadAddChannel =function(a){
						for(var i = 0 ;i <$scope.channeldata.length ; i++ )
						{
							if( $scope.selectData.channel[i] && a == $scope.selectData.channel[i].id){
								return true;
							}
						}
						return false;
					}
					$scope.hadAddAreaClothing =function(a){
						for(var i = 0 ;i <$scope.areaClothingData.length ; i++ )
						{
							if(a == $scope.selectData.qf[i]){
								return true;
							}
						}
						return false;
					}
					$scope.hadAddVersion =function(a){
						for(var i = 0 ;i <$scope.versiondata.length ; i++ )
						{
							if( $scope.selectData.version[i] && a == $scope.selectData.version[i].version_id){
								return true;
							}
						}
						return false;
					}
					$scope.hadAddLang =function(a){
						for(var i = 0 ;i <$scope.langdata.length ; i++ )
						{
							if( $scope.selectData.lang[i] && a == $scope.selectData.lang[i].language_id){
								return true;
							}
						}
						return false;
					}
					
					
					
					
					
					$scope.chooseChannel=function(a,version){
						for(var i = 0 ;i <$scope.channeldata.length ; i++ )
						{
							if(a == $scope.selectData.channel[i].id)
							{
								$scope.selectData.channel.splice(i,1);
								$scope.updateSum();
								return;
							}
						}
						var o={id:a,channelSimName:b,subChannelList:[]}
						$scope.selectData.channel.push(o);
						$scope.updateSum();
					}
					$scope.chooseChannel2=function(a,b,t){
						if(t==1){
							for(var i = 0 ;i <$scope.channeldata.length ; i++ )
							{
								if(a == $scope.selectData.channel[i].id)
								{
									$scope.selectData.channel.splice(i,1);
									$scope.updateSum();
									return;
								}
							}
							}else{
							var o={id:a,channelSimName:b,subChannelList:[]}
							$scope.selectData.channel.push(o);
						}
						$scope.updateSum();
					}
					$scope.addAllChannel=function (){
						$scope.selectData.channel=[];
						for(var i = 0 ;i <$scope.channeldata.length ; i++ )
						{
							var o = {
								id: $scope.channeldata[i].id,
								channelSimName: $scope.channeldata[i].channelSimName,
								subChannelList: []
							}
							$scope.selectData.channel.push(o);
							$scope.showSubChannelList($scope.channeldata[i].id)
						}
						$scope.updateSum();
					}
					$scope.removeAllChannel=function (){
						$scope.selectData.channel=[];
						$scope.showSubChannelData=[];
						$scope.updateSum();
					}
					
					$scope.chooseVersion=function(a,version){
						for(var i = 0 ;i <$scope.versiondata.length ; i++ )
						{
							if(a == $scope.selectData.version[i].version_id)
							{
								$scope.selectData.version.splice(i,1);
								$scope.updateSum();
								return;
							}
						}
//						var o={id:a,channelSimName:b,subChannelList:[]}
						$scope.selectData.version.push(a);
						$scope.updateSum();
					}
					$scope.chooseVersion2=function(a,b,t){
						if(t==1){
							for(var i = 0 ;i <$scope.versiondata.length ; i++ )
							{
								if(a == $scope.selectData.version[i].version_id)
								{
									$scope.selectData.version.splice(i,1);
									$scope.updateSum();
									return;
								}
							}
							}else{
							var o={version_id:a,version_name:b}
							$scope.selectData.version.push(o);
						}
						$scope.updateSum();
					}
					$scope.addAllVersion=function (){
						$scope.selectData.version=[];
						for(var i = 0 ;i <$scope.versiondata.length ; i++ )
						{	
							var o = {
								version_id: $scope.versiondata[i].version_id,
								version_name: $scope.versiondata[i].version_name
							}
							$scope.selectData.version.push(o);
						}
						$scope.updateSum();
					}
					$scope.removeAllVersion=function (){
						$scope.selectData.version=[];
						$scope.updateSum();
					}
					
					
					$scope.chooseLang=function(a,version){
						for(var i = 0 ;i <$scope.langdata.length ; i++ )
						{
							if(a == $scope.selectData.lang[i].language_id)
							{
								$scope.selectData.lang.splice(i,1);
								$scope.updateSum();
								return;
							}
						}
						var o={language_id:a,language_id:b}
						$scope.selectData.lang.push(o);
						$scope.updateSum();
					}
					$scope.chooseLang2=function(a,b,t){
						if(t==1){
							for(var i = 0 ;i <$scope.langdata.length ; i++ )
							{
								if(a == $scope.selectData.lang[i].language_id)
								{
									$scope.selectData.lang.splice(i,1);
									$scope.updateSum();
									return;
								}
							}
							}else{
							var o={language_id:a,language_name:b}
							$scope.selectData.lang.push(o);
						}
						$scope.updateSum();
					}
					$scope.addAllLang=function (){
						$scope.selectData.lang=[];
						for(var i = 0 ;i <$scope.langdata.length ; i++ )
						{	
							var o = {
								language_id: $scope.langdata[i].language_id,
								language_name: $scope.langdata[i].language_name
							}
							$scope.selectData.lang.push(o);
						}
						$scope.updateSum();
					}
					$scope.removeAllLang=function (){
						$scope.selectData.lang=[];
						$scope.updateSum();
					}
					
					
					
					
					$scope.chooseAddAreaClothing=function(a,b){


						for(var i = 0 ;i <$scope.areaClothingData.length ; i++ )
						{
							if(a == $scope.selectData.qf[i])
							{
								$scope.selectData.qf.splice(i,1);
								$scope.updateSum();
								return;
							}
						}
						$scope.selectData.qf.push(a);
						$scope.updateSum();
					}
					$scope.chooseAddAreaClothing2=function(a,b,t){
						if(t==1){


						for(var i = 0 ;i <$scope.areaClothingData.length ; i++ )
						{
							if(a == $scope.selectData.qf[i])
							{
								$scope.selectData.qf.splice(i,1);
								$scope.updateSum();
								return;
							}
						}
						}else{
						$scope.selectData.qf.push(a);
							}
						$scope.updateSum();
					}
					$scope.addAllAreaClothing=function (){
						$scope.selectData.qf=[];
						for(var i = 0 ;i <$scope.areaClothingData.length ; i++ )
						{
							$scope.selectData.qf.push($scope.areaClothingData[i].id);
						}
						$scope.updateSum();
					}
					$scope.removeAllAreaClothing=function (){
						$scope.selectData.qf=[];
						$scope.updateSum();
					}


					$scope.showSubChannelList=function(a,b,t){

						for(var i = 0;i<$scope.showSubChannelData.length;i++)
						{
							if(a == $scope.showSubChannelData[i].id){
								return;
							}
						}
						for(var i = 0 ;i <$scope.channeldata.length ; i++ )
						{
							if(a == $scope.channeldata[i].id && $scope.channeldata[i].subChannelList.length>0)
							{
								var o={id:$scope.channeldata[i].id,channelSimName:$scope.channeldata[i].channelSimName,list:$scope.channeldata[i].subChannelList}
								$scope.showSubChannelData.push(o);
								break;
							}
						}
					}
					$scope.addSubChannelList=function(a,b,t){
                        //console.log(a,b);
						for (var i = 0; i < $scope.selectData.channel.length; i++) {
							if (a == $scope.selectData.channel[i].id) {
								var o={id:a,subId:b}
								$scope.selectData.channel[i].subChannelList.push(o);
								return;
							}
						}
					}
					$scope.removeSubChannelList=function(a,b,t){
						for (var i = 0; i < $scope.showSubChannelData.length; i++) {
							if(a == $scope.showSubChannelData[i].id){
								$scope.showSubChannelData.splice(i,1);
							}
						}
						for (var i = 0; i < $scope.selectData.channel.length; i++) {
							if(a == $scope.selectData.channel[i].id){
								$scope.selectData.channel[i].subChannelList=[];
							}
						}
					}
					$scope.removeSubChannel = function (a, b, t) {

						for (var i = 0; i < $scope.selectData.channel.length; i++) {
								//console.log(a , $scope.selectData.channel[i].id);
							if (a == $scope.selectData.channel[i].id) {
								for (var k = 0; k < $scope.selectData.channel[i].subChannelList.length; k++) {
									if (b == $scope.selectData.channel[i].subChannelList[k].subId) {
										$scope.selectData.channel[i].subChannelList.splice(k, 1);
									}
								}
							}
						}
					}
					$scope.hadAddSubChannel=function(a,b,t){

						for (var i = 0; i < $scope.selectData.channel.length; i++) {
							if ($scope.selectData.channel[i] && a == $scope.selectData.channel[i].id) {

								for (var k = 0; k < $scope.selectData.channel[i].subChannelList.length; k++) {
									if (b == $scope.selectData.channel[i].subChannelList[k].subId) {
										return true;
									}
								}
							}
						}
						return false;
					}




			},
            link: function ($scope, ele, attrs, maindirectiveCtrl) {
				$scope.isShow=false;
				$scope.isChannel=false;
				$scope.isAreaClothing=false;
				$scope.isVersion=false;
				$scope.isLang=false;
				$scope.isChannelToggle=function(){$scope.isChannel=!($scope.isChannel)}
				$scope.isAreaClothingToggle=function(){$scope.isAreaClothing=!($scope.isAreaClothing)}
				$scope.isVersionToggle=function(){$scope.isVersion=!($scope.isVersion)}
				$scope.isLangToggle=function(){$scope.isLang=!($scope.isLang)}
				$scope.callbackChanel();
				$scope.callbackQf();
				$scope.callbackVersion();
				$scope.callbackLang();
				$scope.slideToggle=function(t){
					if($scope.isShow=="act")
						return;
					if($scope.isShow)
					{
						$scope.isShow="act";
						$(".selectDialog").stop().slideUp(function(){$scope.isShow=false;});

					}
					else
					{
                        
						$scope.isShow="act";
						$(".selectDialog").stop().slideDown(function(){$scope.isShow=true;});

					}
				}
				$scope.qd=function(){
					if(!$scope.isChannel){
						$scope.removeAllChannel();
					}
					if(!$scope.isAreaClothing){
						$scope.removeAllAreaClothing();
					}
					if(!$scope.isVersion){
						$scope.removeAllVersion();
					}
					if(!$scope.isLang){
						$scope.removeAllLang();
					}
					if($scope.selectData.channel.length==0 && $scope.isChannel){
						$scope.isChannelToggle();
					}
					if($scope.selectData.qf.length==0 && $scope.isAreaClothing){

						$scope.isAreaClothingToggle();
					}
					if($scope.selectData.version.length==0 && $scope.isVersion){
						$scope.isVersionToggle();
					}
					if($scope.selectData.lang.length==0 && $scope.isLang){

						$scope.isLangToggle();
					}
					maindirectiveCtrl.setmchannel($scope.selectData.channel);
					maindirectiveCtrl.setmareaclothing($scope.selectData.qf);
					maindirectiveCtrl.setmlang($scope.selectData.lang);
					maindirectiveCtrl.setmversion($scope.selectData.version);
					$scope.slideToggle();
				}
            }
        };
})
	.directive('callbackchannelqf',function(){
		return {
			restrict:"A",
			scope:true,
			template:function($scope){
				return '<ul ng-show="islang" class="cl"><li>语言：</li><li ng-repeat="langlist in callbackchannelqfdata.langlist">{{langlist.language_name}}</li></ul><ul ng-show="isversion" class="cl"><li>版本：</li><li ng-repeat="versionlist in callbackchannelqfdata.versionlist">{{versionlist.version_name}}</li></ul><ul ng-show="ischannel" class="cl"><li>渠道：</li><li ng-repeat="channellist in callbackchannelqfdata.channellist  track by $index">{{channellist.channelSimName}} <span ng-show="channellist.subChannelList.length>0">(<span ng-repeat="list in channellist.subChannelList  track by $index">  <span ng-show="$index!=0">,</span> {{list.subId}} </span>)&nbsp;&nbsp;&nbsp;&nbsp;</span> </li></ul><ul ng-show="isqf"  class="cl"><li>区服：</li><li ng-repeat="qflist in callbackchannelqfdata.qflist  track by $index">{{qflist}}</li></ul>';
			},
			controller:function($scope){},
			link:function($scope,ele,attr){

			}
		}
	})
	.directive('navtabs', function () {
        return {
            restrict: 'A',
            scope: false,
            link: function ($scope, ele, attrs) {
               var $li=ele.find("li");
				ele.parent().find('.panel-showData .dataList').eq(0).addClass('dataListAct');
				ele.parent().find('.info div').eq(0).addClass('infoact');
				$li.on("click",function(){
					ele.find(".active").removeClass("active");
					$(this).addClass("active");
				ele.parent().find('.panel-showData  .dataListAct').removeClass('dataListAct');					
				ele.parent().find('.info .infoact').removeClass('infoact');				
					ele.parent().find('.panel-showData  .dataList').eq($(this).index()).addClass('dataListAct');
					ele.parent().find('.info > div').eq($(this).index()).addClass('infoact');
					
					if(ele.parent().find('.dataListAct .graphLimit').length > 0)
					{
						var graphfun=ele.parent().find(".panel-showData  .dataListAct .graph").attr("data");			
						var id=ele.parent().find('.dataListAct .graph').attr("data-index");
							var data=ele.parent().find('.dataListAct .graph').attr("data-data");
							var datalist=ele.parent().find('.dataListAct .graph').attr("data-list");
							var datatype=ele.parent().find(".panel-showData").attr("data-type");
						if(id && data && datalist && datatype)	
						{							
							$scope.graphfun[graphfun](
							$scope[data][id][datatype]["dataList_"+datalist].graphLimit.getdata,
							$scope[data][id][datatype]["dataList_"+datalist].graphLimit.yAxis,
							ele.parent().find('.dataListAct .graphLimit'),
							ele.parent().find('.dataListAct .graph').attr("name")								)
							return;
						}else if(id && data && datalist && !datatype){
							$scope.graphfun[graphfun](
							$scope[data][id]["dataList_"+datalist].graphLimit.getdata,
							$scope[data][id]["dataList_"+datalist].graphLimit.yAxis,
							ele.parent().find('.dataListAct .graphLimit'),
							ele.parent().find('.dataListAct .graph').attr("name")
							
							
							);

							return;
						}
						var paneli=ele.parent().find(".panel-showData").attr("data-panel"),datalisti=ele.parent().find('.dataListAct').index()+1;
						$scope.graphfun[ele.parent().find('.dataListAct .graph').attr('data')]($scope['panel_'+paneli]['dataList_'+datalisti].graphLimit.getdata,$scope['panel_'+paneli]['dataList_'+datalisti].graphLimit.yAxis);
						return;
					}
					if(ele.parent().hasClass("graph")){
						($scope.graphfun[ele.parent().find('.dataListAct .graph').attr('data')])();						
					}
					
				});
            }
        };
})
	.directive('graphtbale', function () {
        return {
            restrict: 'A',
            scope: {
				datalimit:'=',
				datachange:"=",
				graphfun:'=',
				model:'='
			},
			template:function(){
				return '<div class="pic_btn fl" data="graph" ng-click="changemodel(\'graph\')"   ng-class=({\'pic_btn_act\':model==\'graph\'})><font class="pic_icon cl">图表</font></div><div class="pic_btn fl" data="table"  ng-click="changemodel(\'table\')"   ng-class=({\'pic_btn_act\':model==\'table\'})><font class="table_icon cl">表格</font></div>';
			},
            link: function ($scope, ele, attrs,testCtrl) {	
				
				$scope.$watch("datachange",function(nv,ov){
					if(nv && $scope.model=="graph")
					{
						clearTimeout(act);						
						act=setTimeout(function(){
							($scope.graphfun[ele.parent().find('.dataListAct .graph').attr('data')])();
						},100);	
						$scope.datachange=false;
					}
					$scope.datachange=false;
				});
				
				var act,act2;
				$scope.changemodel=function(m){
					if($scope.model==m)
						return;
					$scope.model=m;	
					if(m=="graph" && $scope.datalimit=="0"){						
						clearTimeout(act);						
						act=setTimeout(function(){
							($scope.graphfun[ele.parent().find('.dataListAct .graph').attr('data')])();
							$scope.panel_1_change=false;
						},100);						
					}
					else if(m=="graph" && $scope.datalimit.now==-1){
						$scope.datalimit.now=-2;						
					}
					else if(m=="graph" && $scope.datalimit.now!=-1){
						if(ele.parent().find(".nav .active").length>0)
						{
							setTimeout(function(){								
							ele.parent().find(".nav .active").click();
							},10);
						
						}
					}
				};
            }
        };
})
	.directive('graphlimit', function () {
        return {
            restrict: 'A',
            scope: {
				datalimit:"=",
				graphdata:"=",
				graphfun:"=",
				model:"=",
				key:"=",
				isfist:"@",
				hastarget:"@",
				name:"@",
			},
			template: function(){
				return '<span ng-click="limitCount(datalimit,1)" class="limitBtn limitBtn_l" ng-class="{\'limitBtn_disabled\':datalimit.now==0}">&lt;</span><span class="limitPage"><font>{{datalimit.now+1}}</font>/<font>{{datalimit.count+1}}</font></span><span ng-click="limitCount(datalimit,2);" class="limitBtn limitBtn_r" ng-class="{\'limitBtn_disabled\':datalimit.now==datalimit.count}">&gt;</span>';
			},
			controller:function($scope){
			},
            link: function ($scope, ele, attrs) {
				$scope.getdata="";
				
				$scope.graphThis=function(){
//$scope.getdata=[];
					$scope.getdata=$scope.dataLimitPush($scope.graphdata,$scope.key.d,$scope.datalimit.now,$scope.datalimit.max);
					$scope.yAxis=$scope.dataLimitPush($scope.graphdata,$scope.key.y,$scope.datalimit.now,$scope.datalimit.max);
					$scope.datalimit.getdata=$scope.getdata;
					$scope.datalimit.yAxis=$scope.yAxis;
				}
				$scope.$watch("datalimit.now",function(){
					if($scope.datalimit.now<0)return;
					if($scope.model=="graph" ){
						$scope.graphThis();
						$scope.graphfun($scope.getdata,$scope.yAxis,ele,$scope.name);
					}
				});
				$scope.$watch("model",function(){
					if($scope.model=="graph" )
					{
						if(!$scope.isfist)
						{	
							return;
						}
						else{
							if($scope.datalimit.now<0)
							{
								$scope.datalimit.now=0;
								setTimeout(function(){
								$scope.graphThis();
								$scope.graphfun($scope.getdata,$scope.yAxis);
								$scope.isfist=true;
								},10);
							}
						}
					}
				});
//				$scope.$watch("getdata",function(){	
//					if(!$scope.getdata)return;
//					if($scope.isfist && $scope.datalimit.now>=0){
//						$scope.graphfun($scope.getdata,$scope.yAxis);
//					}
//				});
				$scope.$watch("graphdata",function(){
					if(!$scope.graphdata && $scope.datalimit.now<0)
						return;
					if(!$scope.graphdata)
						return;
					$scope.datalimit.sumLen=($scope.graphdata).length;
	$scope.datalimit.count=Math.ceil($scope.datalimit.sumLen/$scope.datalimit.max)-1;
					$scope.graphThis();
					$scope.graphfun($scope.getdata,$scope.yAxis,ele,$scope.name);
				});
				
				$scope.limitCount=function(o,t){		
					if(t==1 && o.now>0){
						o.now--;
					}
					else if(t==2 && o.now<o.count){
						o.now++;
					}
				}
				$scope.dataLimitPush=function(data,key,limitNow,limitMax){
					 if(!data)return [];
						var arr=new Array();
	                    if(typeof key == "object")
	                   {
	                       for(var n = 0 ; n<key.length ; n++){
	                       arr[key[n]]=[];
	                           for(var i=0;i<data.length;i++){
	                            if( i>=(limitNow*limitMax) && i< (limitNow*limitMax+limitMax))
	                               {
	                                if(data[i]){
	                                    arr[key[n]].push(data[i][key[n]]);
	                                }
	                               }
	                           }
	                       }
	                   }
	                   else{
	                       for(var i=0;i<data.length;i++){
							if( i>=(limitNow*limitMax) && i< (limitNow*limitMax+limitMax))
							   {
								if(data[i]){
	                                arr.push(data[i][key]);
	                            }
	                           }
	                       }
	                   }
					return arr;
				}
            }
        };
})
	.directive('tablelimit', function () {
        return {
            restrict: 'A',
            scope: {
				datalimit:"=",
				tabledata:"=",
				index:"="
			},
			template: function(){
				return '<span ng-click="limitCount(datalimit,1)" class="limitBtn limitBtn_l" ng-class="{\'limitBtn_disabled\':datalimit.now==0}">&lt;</span><span class="limitPage"><font>{{datalimit.now+1}}</font>/<font>{{datalimit.count+1}}</font></span><span ng-click="limitCount(datalimit,2)" class="limitBtn limitBtn_r" ng-class="{\'limitBtn_disabled\':datalimit.now==datalimit.count}">&gt;</span>';
			},
			controller:function($scope){
				$scope.$watch("tabledata",function(nv){
					if(!$scope.tabledata)
						return;
					$scope.datalimit.sumLen=($scope.tabledata).length;
	$scope.datalimit.count=Math.ceil($scope.datalimit.sumLen/$scope.datalimit.max)-1;
					
				});
			},
            link: function ($scope, ele, attrs) {
				
				
				
					$scope.limitCount=function(o,t){	
						$scope.datalimit.sumLen=($scope.tabledata).length;
	$scope.datalimit.count=Math.ceil($scope.datalimit.sumLen/$scope.datalimit.max)-1;
						
						if(t==1 && o.now>0){
							o.now--;
						}
						else if(t==2 && o.now<o.count){
							o.now++;
						}
					}
            }
        };
})
	.directive('datatableslimit', function () {
        return {
            restrict: 'A',
            scope: {
				datalimit:"=",
				tabledata:"=",
				datafilter:"="
			},
			template: function(){
				var str='<span class="first paginate_button" role="first" ng-click="first()">{{"第一页" | translate}}</span>'+
				'<span class="previous paginate_button" role="prev"  ng-click="limitCount(datalimit,1)">  {{"上一页" | translate}}  </span>'+
				'<span>'+
				'<span ng-repeat="butrepeat in butrepeat"  class="paginate_button" ng-class="({\'paginate_active\': butrepeat == datalimit.now})" ng-click="thisnow(butrepeat)">{{butrepeat+1}}</span>'+
				'</span>'+
				'<span class="next paginate_button" role="next"  ng-click="limitCount(datalimit,2)">  {{"下一页" | translate}}  </span>'+
				'<span class="last paginate_button" role="last"  ng-click="end()">  {{"最后一页" | translate}}  </span>';
				
				return str;
			},
			controller:function($scope,$filter){
				
				$scope.data2="";
				$scope.$watch("datafilter",function(newVal){
					if(!$scope.tabledata)
						return;
					$scope.data2=$filter("filter")($scope.tabledata,newVal);
					$scope.datalimit.sumLen=($scope.data2).length;
	$scope.datalimit.count=Math.ceil($scope.datalimit.sumLen/$scope.datalimit.max)-1;
					$scope.datalimit.now=0;
				});
				
				$scope.butrepeat=[];
				
				$scope.$watch("tabledata",function(newVal){
					if(!newVal)
						return;
					if(!$scope.datafilter)
					{	
						$scope.data2=$scope.tabledata;
					}else{
						$scope.data2=$filter("filter")($scope.tabledata,newVal);
					}
					$scope.datalimit.sumLen=($scope.data2).length;
	$scope.datalimit.count=Math.ceil($scope.datalimit.sumLen/$scope.datalimit.max)-1;
					$scope.datalimit.now=0;
					
				});
				$scope.$watch("datalimit.count",function(){
					$scope.butrepeat=[];
					var arr=[$scope.datalimit.now-4,$scope.datalimit.now-3,$scope.datalimit.now-2,$scope.datalimit.now-1,$scope.datalimit.now,$scope.datalimit.now+1,$scope.datalimit.now+2,$scope.datalimit.now+3,$scope.datalimit.now+4];
					for(var i=0;i<arr.length;i++){
						
						if($scope.butrepeat.length<5){
							if(arr[i]>=0 && arr[i]<=$scope.datalimit.count){
								$scope.butrepeat.push(arr[i]);
							}
						}						
					}
					
				});
				$scope.$watch("datalimit.now",function(){
					$scope.butrepeat=[];
					var arr=[$scope.datalimit.now-4,$scope.datalimit.now-3,$scope.datalimit.now-2,$scope.datalimit.now-1,$scope.datalimit.now,$scope.datalimit.now+1,$scope.datalimit.now+2,$scope.datalimit.now+3,$scope.datalimit.now+4];
					for(var i=0;i<arr.length;i++){
						
						if($scope.butrepeat.length<5){
							if(arr[i]>=0 && arr[i]<=$scope.datalimit.count){								
								if($scope.datalimit.now <2 && arr[i] >=0)
								{	
									$scope.butrepeat.push(arr[i]);
								}
								else if($scope.datalimit.now - arr[i] <=2 && $scope.datalimit.now - arr[i] >=-2 && $scope.datalimit.count-$scope.datalimit.now >=2)
								{
									$scope.butrepeat.push(arr[i]);
								}else if($scope.datalimit.count-$scope.datalimit.now <2 && $scope.datalimit.count-arr[i] <=4)
								{
								$scope.butrepeat.push(arr[i]);
								}
							}
						}			
					}
					//console.log($scope.butrepeat);
				});
				$scope.first=function(){
					$scope.datalimit.now=0;
				}
				$scope.end=function(){
					$scope.datalimit.now=$scope.datalimit.count;
				}
				$scope.thisnow=function(i){
					$scope.datalimit.now=i;
				}
				$scope.limitCount=function(o,t){		
					if(t==1 && o.now>0){
						o.now--;
					}
					else if(t==2 && o.now<o.count){
						o.now++;
					}
				}
			},
            link: function ($scope, ele, attrs) {
            }
        };
})
	.directive('analysisdrop',function(){
		return{
			restrict: 'A',
			scope:true,
			controller:function($scope){
			
			},
			link:function($scope,ele,attr){
				$scope.val=ele.find('li').eq(0).html();
				$scope.c=function(i){$scope.val=i;}
				ele.find('.hadchoose').on("click",function(){
					var ul=$(this).next();
					if(ul.css("display")=="block"){
						ul.hide();
					}
					else{
						ul.show(10,function(e){                            
                            $(document).one("click",function(e){
                                ele.find("ul").hide()

                            })
                        });
					}
				});
				ele.find('li').on("click",function(){
					$scope.val=$(this).html();
					var ul=$(this).parent();
					if(ul.css("display")=="block"){
						ul.hide();
					}
					else{
						ul.show();
					}
				});
				$scope.hideDrop=function(){
					ele.find("ul").hide();
				}
			}
		}
	})
	.directive('playertabs',function(){
		return {
			restrict:"A",
			scope:true,
			template:function($scope){	
				return '<a data="chargePlayer" ng-repeat="tabsarr in tabsarr" class="tagbut" ng-class="{\'tagbutAct\':tabsId==tabsarr.id}"  ng-click="changebut(tabsarr.id)">{{tabsarr.name}}</a>';
			},
			controller:function($scope){},
			link:function($scope,ele,attr){
				$scope.but="0";
				$scope.changebut=function(i){
					$scope.but=i;
					//if((typeof $scope.changetabs) == "function"){
						$scope.changetabs(i);
					//}
				}
			}
		}
	})
	.directive('dialogmsg', function () {
		return {
			restrict: "EA",
			scope: {dialogopen:'='},
			template: function ($scope) {
				return '';
			},
			controller: function ($scope, $uibModal) {
				$scope.html = '<div class="modal-header"><h3 class="modal-title">{{title}}</h3></div><div class="modal-body">{{msg}}</div><div class="modal-footer"><button class="btn btn-primary btn-sm" type="button" ng-click="fn.ok();" ng-show="btn.ok">{{text.ok | translate}}</button><button class="btn btn-warning btn-sm" type="button" ng-click="fn.close()" ng-show="btn.close">{{text.close | translate }}</button></div>';

				var modalInstance = "";
				var option = {
					title : "",
					msg : "",
					size : 'sm' ,
					btn : {
						ok : true,
						close : true
					},
					text : {
						ok: "确定",
						close: "关闭"
					},
					fn : {
						ok: function () {
						},
						close: function () {
						},
						must : function (){}
					}
				}
				$scope.dialogopen = function (o) {
                    option = {
					title : "",
					msg : "",
					size : 'sm' ,
					btn : {
						ok : true,
						close : true
					},
					text : {
						ok: "确定",
						close: "关闭"
					},
					fn : {
						ok: function () {
						},
						close: function () {
						},
						must : function (){}
					}
				}
					function setO (o ,t ){
						for(var i in o){
							typeof o[i] == "object" ? setO(o[i],t[i]):t[i] = o[i];
						 }
					}
					 if(o){
						 setO(o,option);
					 }
					 modalInstance = $uibModal.open({
						animation: true,
						template: $scope.html,
						controller: function ($scope,$uibModalInstance) {
							$scope.o = option;
							$scope.title = option.title;
							$scope.msg = option.msg;
							$scope.btn = {
								ok: option.btn.ok,
								close: option.btn.close
							}
							$scope.text = {
								ok: option.text.ok,
								close: option.text.close
							}
							$scope.fn = {
								ok: function(){
									option.fn.ok();
									$scope.fn.close();
								},
								close: function () {
									$uibModalInstance.close();
								}
							}
							//$uibModalInstance.dismiss(option.fn.must);
							modalInstance.result.then(function () {
							  option.fn.must();
							}, function () {
							  option.fn.must();
							});
						},
						size: option.size,
						resolve: {

						}
					});
				}
				
			},
			link: function ($scope, ele, attr) {

			}
		}
	})
	.directive('downFile', ['$http',function ($http) {
		return {
			restrict: 'A',
			scope: {
				savedata:"=",
				postdata:"=",
				dialogopen:"="
			},
			link: function ($scope, element, attr) {
				var ele = $(element);
				ele.on('click', function (e) {
					ele.prop('disabled', true);
					e.preventDefault();
					var type;
					switch (attr.downFileType) {
						case 'xlsx':
							type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
							break;
						case 'txt':
							type = 'text/plain;charset=utf-8';
							$scope.savedata = JSON.stringify($scope.savedata);
							break;
					}
					if (!type) throw '无效类型';
					if($scope.savedata){
						saveAs(new Blob([$scope.savedata], { type: type }),attr.downFileName+"."+attr.downFileType);
						return;
					}
					var url = attr.downFileUrl + "?";
//					if($scope.postdata){
//						for(var k in $scope.postdata){
//							if(typeof $scope.postdata[k] =="object"){
//								for(var i=0;i<$scope.postdata[k].length;i++){
//									url+=k+"="+$scope.postdata[k][i]+"&"
//								}
//							}else
//							url+=k+"="+$scope.postdata[k]+"&"
//						}
//					}
					$http({
						url: url,
						method: 'post',
						data:$scope.postdata,
						responseType: 'arraybuffer'
					}).success(function (data, status, headers) {
						ele.prop('disabled', false);
						saveAs(new Blob([data], { type: type }), attr.downFileName+"."+attr.downFileType);  // 中文乱码
					}).error(function (data, status) {
						//alert(data);
						ele.prop('disabled', false);
					});
				});
			}
		};
	}])
	.directive('tipsmsg', function () {
		return {
			restrict: "EA",
			scope: {
				left: '='
			},
			transclude : true,
			template: function ($scope) {
				return '<em class="icon-exp">三角图标</em><div class="noun-exp" style="width:300px;" ng-transclude></div>';
			},
			controller: function ($scope, $uibModal) {
				$scope.tips = '';
				
				
			},
			link: function ($scope, ele, attr) {
				ele.addClass("exp-box");
				$scope.left && ele.find('.noun-exp').css({
					left: $scope.left + 'px'
				})
			}
		}
	})
    .directive('compared',function(){
        return {
			restrict:"A",
			scope:{
                mstatdate :"=",
                datalist : "=" ,
                graphfun : "=",
                panelchange : "="
            },
			template:function($scope){
//				return '<div class="input-group fl" style="width:170px;margin-top:8px;"><input type="text" class="compareDate form-control input-sm" placeholder="起始日期" ng-model="compareStartDate"><span class="input-group-btn"><button class="btn btn-default btn-sm" type="button" ng-click="compareGraph()" ng-show="isCompare==0">对比</button><button class="btn btn-default btn-sm" type="button" ng-click="uncompareGraph()" ng-show="isCompare==1">取消对比</button></span></div>';
				return '<div class="input-group fl" style="width:170px;margin-top:8px;"><input type="text" id="compareTime" class="compareDate form-control input-sm" placeholder="{{compareTime}}" uib-datepicker-popup="{{format}}" ng-model="startTime" is-open="popup.opened" datepicker-options="dateOptions" ng-required="true" close-text="Close" alt-input-formats="altInputFormats"  ng-click="open()"/><span class="input-group-btn"><button class="btn btn-default btn-sm" type="button" ng-click="compareGraph()" ng-show="isCompare==0">对比</button><button class="btn btn-default btn-sm" type="button" ng-click="uncompareGraph()" ng-show="isCompare==1">取消对比</button></span></div>';
			},
			controller:function($scope){
				 $scope.dateOptions = {
                    //dateDisabled: disabled,
                    formatYear: 'yy',
                    timezone:'+0800',
                    mode:$scope.mode || 'day', 
                    minMode:$scope.minmode || '', 
                    maxMode:$scope.maxmode || '', 
                    maxDate: $scope.maxdate?new Date($scope.maxdate):"",
                    minDate: new Date(2000,1,1),
                    startingDay: 1
                  };
				$scope.startTime="";
				$scope.endtTime="";
				$scope.compareTime="";
				$scope.popup={
					opened : false
				};
                $scope.open=function(){
					$scope.popup.opened = true;
				}
            },
			link:function($scope,ele,attr){
                
                $scope.startdate="";
                $scope.enddate="";
                $scope.ctime="";
                $scope.$watch("mstatdate",function(newdate,olddate){
                    if($scope.mstatdate){
						$scope.startdate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
						$scope.enddate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
						
						$scope.ctime = parseInt((new Date($scope.enddate)).getTime()) - parseInt((new Date($scope.startdate)).getTime());
						
                   }
                   $scope.uncompareGraph();
                })
                $scope.$watch("startTime",function(newdate,olddate){
					if($scope.isCompare==1){
                    if($scope.mstatdate){
						$scope.startdate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[0].trim():$scope.mstatdate;
						$scope.enddate=$scope.mstatdate.indexOf("~")>0?$scope.mstatdate.split("~")[1].trim():$scope.mstatdate;
						
						$scope.ctime = parseInt((new Date($scope.enddate)).getTime()) - parseInt((new Date($scope.startdate)).getTime());
						
                   }
                   $scope.compareGraph();
					}
                })
                $scope.isCompare=0;
                $scope.compareGraph=function(){
                    $scope.datalist.data2=[];
					$scope.pstartTime = (new Date($scope.startTime)).format("yyyy-MM-dd")
					$scope.pendtTime = (new Date(parseInt((new Date($scope.pstartTime)).getTime()) + $scope.ctime )).format("yyyy-MM-dd")
					var p = {
						startDate:$scope.pstartTime,
						endDate:$scope.pendtTime
					}
					$scope.graphfun(p);
					$scope.isCompare=1;
                }
                $scope.uncompareGraph=function(){
                    $scope.datalist.data2="";
                    $scope.compareDate="";
//                    ele.find(".compareDate").val("");
//                    $scope.graphfun();
                    $scope.panelchange=true;
                    $scope.isCompare=0;
                }
				
			}
		}
    })
    .directive('selectdirective', function() {
        return {
            template: '<select class="form-control" ng-model="value" ng-options="x.attr_desc as x.attr_value for x in lists"><lect>',
            scope: {
                attr_code: '=attrCode',
                value: '=',
                hasall: '@'
            },
            controller: function($scope, $http){
                $scope.lists = $scope.hasall == 1? [{attr_desc: -1, attr_value: '全部'}] : []
                $scope.getList = function() {
                    $http.post('attrValue/getAttrValueListByCode.action', {attr_code: $scope.attr_code}).success(function(res) {
                        if (res.code === 1) {
                            $scope.lists = $scope.lists.concat(res.attrValues)
                            !$scope.value ? ($scope.value = $scope.lists[0].attr_desc) : ""
                        }
                    })
                }
                $scope.attr_code && $scope.getList()
            }
        }
    })

;

/**********************Controller***********************************/
function indexController($scope,$translate,$location,$translate,$http){
	$scope.$Params={id:"-1"};
	$scope.setParams=function(i){$scope.$Params=i;}
	$scope.getUrlParams=function(k){
		return $location.search()[k];
	}
	$scope.changeLanguage = function (key) {	//语言切换
    	$translate.use(key);
	};
	if($scope.$Params.lang){
		$scope.$Params.lang = 'zh';
		$translate.use($scope.$Params.lang);
	}else{
		$scope.$Params.lang = 'zh';
		$translate.use($scope.$Params.lang);
	}
    //$scope.mstatdate="";
    //$scope.mchannel="";
    //$scope.mareaclothing="";
	$scope.gamesyslist=[];
    $scope.userChange=false;
	$scope.user={name:"游客"};
	$scope.menuData=[];
	$scope.publicplatform={
        platformid:"",
        projectList:[],
        gameList:[],
		showmenu:false
    };
    $scope.getUsername=function() {
		$http.get("getUsername.action").success(function(data) {
			if (data.status == 1) {
				$scope.user = {name:data.message};
			} else {
				$scope.dialogopen({
					msg:"请先登录",
					btn:{close:false},
					fn:{
						must:function(){
							location.href = "login.html"
						}
					}
				})
				//alert("亲,请先登录");
				//location.href = "login.html";
			}
		})
	}
    
    $scope.getUsername();
    $scope.dialogopen=function(){};
    //广播给子控制器
    $scope.setClickedSystem=function(type){
    	$scope.$broadcast('checkedSys',type);
    }
    
}

//
function userBarController($scope, $http ,$translate,$location,$state,T){	
	//$scope.data=data;
	//$scope.productListData=productListData;	
	
	$scope.isdrop=false;
	$scope.isdrop1=false;
	$scope.isdrop2=false;
	$scope.isdrop3=false;
	$scope.dropToggle=function(){
		$scope.isdrop=!$scope.isdrop;
		
	}
	$scope.dropToggle1=function(){    
        $scope.isdrop1=!$scope.isdrop1; 
    }
	$scope.dropToggle2=function(){
        $scope.isdrop2=!$scope.isdrop2; 
    }
	$scope.dropToggle3=function(){
        $scope.isdrop3=!$scope.isdrop3;
    }
    
	$scope.platformList = [];
	$scope.projectList = [];
	$scope.gameList = [];
    $scope.getPlatformList = function () {
		$http.get("platprojectgame/selectUserPlatProjectGameList.action").success(function(data){
			//$scope.gameList = data;
            $scope.platformList = data;
		}).error(function(data,header,config,status){
        //处理响应失败
        });
	}
    $scope.getPlatformList();
    $scope.getSet = function(tarO , v , key , getKey ){
        for(var i = 0; i < tarO.length; i++){
            if(tarO[i][key] == v ){
                return tarO[i][getKey];
            }
        }
        return;
    }
	$scope.setList = function () {
		$scope.publicplatform.showmenu = false;
        if($scope.params.platformid){
        	$scope.projectList = $scope.getSet($scope.platformList,$scope.params.platformid,'id','projectList');
            $scope.publicplatform.projectList =$scope.getSet($scope.platformList,$scope.params.platformid,'id','projectList');
			$scope.publicplatform.showmenu = true;
			if($scope.projectList.length==0){
				$scope.gameList=[];
				return;
			}
        }
		if($scope.params.projectid){
			$scope.gameList = $scope.getSet($scope.projectList,$scope.params.projectid,'id','gameList');
            $scope.publicplatform.gameList =$scope.getSet($scope.projectList,$scope.params.projectid,'id','gameList');
		}
        if($scope.params.projectid && !$scope.params.gameid)
        {
            $scope.gameList = $scope.getSet($scope.projectList,$scope.params.projectid,'id','gameList');
            $scope.publicplatform.gameList =$scope.getSet($scope.projectList,$scope.params.projectid,'id','gameList');
			$scope.publicplatform.showmenu = false;
        }
		else if($scope.params.projectid && $scope.params.gameid){
			$scope.publicplatform.showmenu = true;
		}
		else if(!$scope.params.projectid || !$scope.params.gameid){
           // $scope.gameList = [];
           // $scope.publicplatform.gameList = [];
            $scope.publicplatform.showmenu = false;
        }
		
	}
    $scope.setList();
	$scope.getPlatformName=function(id){
		for(var i=0;i<$scope.platformList.length;i++){
			if($scope.platformList[i].id==id)
                { $scope.setList();
				return $scope.platformList[i].platname;}
		}
		return T.T("请选择平台");
	}
    
	//$scope.gameList = [];
	$scope.getGameList = function () {
		$http.get("getUserGameList.action").success(function(data){
			//$scope.gameList = data;
		})
	}
	
	$scope.toIndex = function () {
		$http.get("logout.action").success(function(data) {
			location.href = "login.html"
		})
	}
	
	$scope.getGameName=function(id){
		for(var i=0;i<$scope.gameList.length;i++){
			if($scope.gameList[i].id==id)
				return $scope.gameList[i].name;
		}
		return T.T("请选择游戏");
	}
	
	//项目列表
	//$scope.projectList = [];
	$scope.getProjectList = function () {
		$http.get("getUserProjectList.action").success(function(data){
			//$scope.projectList = data;
		})
	}
	

	$scope.getProjectName=function(id){
		for(var i=0;i<$scope.projectList.length;i++){
			if($scope.projectList[i].id==id){
                 $scope.setList();
				return $scope.projectList[i].name;
            }
		}
		return T.T("请选择项目");
	}

	$scope.gameSystem={
		ios:true,
		android:true,
		win:true
	}
	$scope.gameSystemChoosed={
		1:true,
		2:true,
		3:true
	};
	$scope.setClickSystem=function(osType){
		//判断从项目首页点击的哪个系统
		if(osType==''){
			$scope.gameSystem.ios=true;
			$scope.gameSystem.android=true;
			$scope.gameSystem.win=true;
		}else if(osType=='ios'){
			$scope.gameSystem.ios=true;
			$scope.gameSystem.android=false;
			$scope.gameSystem.win=false;
		}else if(osType=='android'){
			$scope.gameSystem.ios=false;
			$scope.gameSystem.android=true;
			$scope.gameSystem.win=false;
		}else if(osType=='win'){
			$scope.gameSystem.ios=false;
			$scope.gameSystem.android=false;
			$scope.gameSystem.win=true;
		}
		$scope.setGameSys();
	}
	$scope.setGameSys=function(t){

		$scope.gameSystemChoosed[1]=$scope.gameSystem.ios;
		$scope.gameSystemChoosed[2]=$scope.gameSystem.android;
		$scope.gameSystemChoosed[3]=$scope.gameSystem.win;
		$scope.gamesyslist=[];
		for(var o in $scope.gameSystemChoosed){
			if($scope.gameSystemChoosed[o]){
				$scope.gamesyslist.push(o);
			}
		}
		$scope.isdrop=false;
		if(t){
			var b = $state.current.name;
			$state.go("main");
			setTimeout(function(){
				$state.go(b);
			},200)
			//$state.go(b);
	    }
	}
	//父控制器indexController广播子控制器UserBarController回调
	  $scope.$on('checkedSys', function(e, type) {
		  $scope.setClickSystem(type);
	    });
    
}

function mainController($scope,$http,$translate){
	$scope.setParams($scope.$stateParams);
	//设置选中的游戏系统
	$scope.setClickedSystem($scope.$Params.systemType);
	if($scope.$Params.lang){
		$translate.use($scope.$Params.lang);
	}else{
		$scope.$Params.lang = 'zh';
		$translate.use($scope.$Params.lang);
	}
	$scope.getArrMax=function(arr,key){
		var max=0;
		for(var i=0;i<arr.length;i++)
		{
			arr[i][key]>max?max=arr[i][key]:max;
		}
		return max;
	}
	$scope.getArrMin=function(arr,key){
		var min=0;
		for(var i=0;i<arr.length;i++)
		{
			arr[i][key]>min?min=arr[i][key]:min;
		}
		return min;
	}
	$scope.getArrMaxDate=function(arr,key){
		var max=0,d=0,d2=0;
		for(var i=0;i<arr.length;i++)
		{
			d2=parseInt((new Date(arr[i][key])).getTime());
			if(d<d2)
			{
				d=d2;
				max=arr[i][key];				
			}
		}
		
		return max;
	}
	$scope.getArrMinDate=function(arr,key){
		var min=0,d=0,d2=0;
		for(var i=0;i<arr.length;i++)
		{
			d2=parseInt((new Date(arr[i][key])).getTime());
			if(d>d2)
			{
				d=d2;
				min=arr[i][key];
			}else if(d==0){
				d=d2;
				min=arr[i][key];
			}
			
		}
		return min;
	}
	$scope.dataPushArr=function(arr,key,dateKey){
		var newArr=new Array(),max=0,norArr=new Array();
		for(var i=0;i<arr.length;i++)
		{
			arr[i][key]>max?max=arr[i][key]:max;
			newArr.push(arr[i][key]);
			norArr.push(arr[i][key]);
		}
		
		return {'newArr':newArr,'max':max,'oldArr':arr,'norArr':norArr};
	}
	
	//百分比
	$scope.percenDataPushArr=function(arr,key,dateKey){
		var newArr=new Array(),max=0;
		for(var i=0;i<arr.length;i++)
		{
			arr[i][key]>max?max=arr[i][key]:max;
			newArr.push(arr[i][key]+'%');
		}
		
		return {'newArr':newArr,'max':max,'oldArr':arr};
	}
	
	$scope.dataDateArr=function(arr,key,dateKey){
		var newArr=new Array(),max=0,norArr=new Array();
		for(var i=0;i<arr.length;i++)
		{
			arr[i][key]>max?max=arr[i][key]:max;
			var d=new Date(arr[i][key]);
			newArr.push((d.getMonth()+1)+"-"+d.getDate());
			norArr.push(arr[i][key]);
		}
		return {'newArr':newArr,'max':max,'oldArr':arr,'norArr':norArr};
	}	
	$scope.dataPushPercen=function(arr,key){
		var sum=0,newArr=new Array();
		for(var i=0;i<arr.length;i++){
			sum+=arr[i];
		}
		for(var i=0;i<arr.length;i++){
			var p=(arr[i]/sum*100).toFixed(2);
			newArr.push(p);
		}
		//console.log(newArr);
		return newArr;
	}
	$scope.changeOrderBy=function(o,i,b){
		if(i==o.orderBy.s){
			if(o.orderBy.t=="-")
				o.orderBy.t="";
			else
				o.orderBy.t="-";
		}else{
			o.orderBy.s=i;
		}
	}
	
}

//获取目录
function menuController($scope, $http ,$translate){
	//  $scope.$Params.projectid   $scope.$Params.gameid
	$scope.getMenu=function() {
		$http.post("getMenu.action",{"gameid":$scope.$Params.gameid}).success(function(data) {
			$scope.menuData = data;
		}).error(function(data){
			console.info("请先登录!");
		});
	}
	$scope.getMenu2=function() {
        
        
        var o = {};
            if($scope.$Params.platformid)
                o.platformid = $scope.$Params.platformid;
            else
                return;
            if($scope.$Params.projectid)
                o.projectid = $scope.$Params.projectid;
            if($scope.$Params.gameid)
                o.gameid = $scope.$Params.gameid;
//        if(!$scope.$Params.projectid && $scope.publicplatform.projectList.length==0 && !$scope.$Params.gameid ){
//            $scope.menuData = [];
//            return;
//        }
    	$scope.hideMenuData=["autobalance","billingformbyweek","applebalance","contractFile"];
		$http.post("platprojectgame/selectUserPlatProjectGameMenuList.action",o).success(function(data) {
			$scope.moveElement=undefined;
            for(var i=0;i<data.length;i++){
            	if(data[i].key=="realtimedata"){
            		$scope.moveElement=data.splice(i,1);
            		break;
            	}
            }
            if($scope.moveElement!=undefined){
            	data.splice(2,0,$scope.moveElement[0]);
            }
			$scope.menuData = data;
			angular.forEach($scope.menuData, function(data,index,array){
				//data等价于array[index],console.log(data.a+'='+array[index].a);
				if(data.menuList){
					angular.forEach(data.menuList, function(data,index,array){
						var isContain=$scope.hideMenuData.indexOf(data.key);
						if(isContain>=0){
							//包含设置隐藏
							array[index].isHideMenu=true;
						}else{
							array[index].isHideMenu=false;
						}
					});
				}
				});
			
		}).error(function(data){
			console.info("请先登录!");
		});
	}
	$scope.getMenu2();
//	$scope.getMenu();
}


function playersController($scope){}		//游戏玩家
	//活跃玩家
	//玩家留存
function summaryController($scope){}		//概况


function revenueController(){}
function concurrentsController(){}
function channelsController(){}
function whaleUserController(){}
function virtualController(){}
function levelsController(){}
function giftController(){}
function versionController(){}
function eventsController(){}
function userController(){}
function configController(){}
function analysisController(){}
function orderStatisticsController(){}
function balanceplatController(){}
function advertiserplatController(){}
function sdkDataController(){}
function communityController(){}
function dauModeController(){}
function afPushApiController(){}
function xgFinanceController(){}
function realTimeDataController(){}
function financeStatController(){}


//独立数据
function indpdController($scope){}
function yunyindataController($scope){}
function consumedataController($scope){}


/**********************************路由*****************************************/
$main.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider
		.when("", "/main/")

	$stateProvider
        
        .state('main', {
            url: '/main/:platformid/:projectid/:gameid?lang',
            params:{"systemType":''},
            templateUrl: 'template/main.html',
            controller: 'mainController'
        })
        .state('main.project', {
            url: '/project',
            templateUrl: 'template/project/index.html',
            controller: 'projectConfigController'
        })
		.state('main.summary', {		//概述
            url: '/summary',
            templateUrl: 'template/index/index.html',
            controller: 'summaryController'
        })
		.state('main.summary.index', {		//概述
            url: '/index',
            templateUrl: 'template/index/summary-index.html?t='+Math.random(),
            controller: 'summaryindexController'
        })
        .state('main.summary.report', {		//运营报表
            url: '/report',
            templateUrl: 'template/index/report.html?t='+Math.random(),
            controller: 'reportController'
        })
        .state('main.summary.datacompared', {		//数据对比
            url: '/datacompared',
            templateUrl: 'template/index/datacompared.html?t='+Math.random(),
            controller: 'datacomparedController'
        })
        .state('main.summary.logRealTimeFeedBack', {		//日志实时反馈
            url: '/logRealTimeFeedBack',
            templateUrl: 'template/index/logRealTimeData.html?t='+Math.random(),
            controller: 'logRealTimeDataController'
        })
//        .state('main.summary.logPlayerData', {		//日志玩家数据
//            url: '/logPlayerData?actorid&logtype',
//            templateUrl: 'template/index/logPlayerData.html?t='+Math.random(),
//            controller: 'logPlayerDataController'
//        })
        
        

        .state('main.players', {	//游戏玩家
            url: '/players',
            templateUrl: 'template/players/players.html',
            controller: 'playersController'
        })
        .state('main.players.new', {	//新增玩家
            url: '/new',
            templateUrl: 'template/players/new.html?t='+Math.random(),
            controller: 'playersNewController'

        })	
        .state('main.players.dynamic', {	//活跃玩家
            url: '/dynamic',
            templateUrl: 'template/players/dynamic.html?t='+Math.random(),
            controller: 'playersDynamicController'

        })
        .state('main.players.device', {	//设备相关
            url: '/device',
            templateUrl: 'template/players/device.html?t='+Math.random(),
            controller: 'playersDeviceController'

        })
        .state('main.players.retention', {	//玩家留存
            url: '/retention',
            templateUrl: 'template/players/retention.html?t='+Math.random(),
            controller: 'playersRetentionController'

        })	
        .state('main.players.conversion', {	//付费转化
            url: '/conversion',
            templateUrl: 'template/players/conversion.html?t='+Math.random(),
            controller: 'playersConversionController'

        })
        .state('main.players.chum', {	//玩家流失
            url: '/chum',
            templateUrl: 'template/players/chum.html?t='+Math.random(),
            controller: 'playersChumController'

        })
        .state('main.players.behavior', {	//游戏习惯
            url: '/behavior',
            templateUrl: 'template/players/behavior.html?t='+Math.random(),
            controller: 'playersBehaviorController'

        })
        
         .state('main.realtimedata', {	//实时数据
            url: '/realtimedata',
            templateUrl: 'template/realtimedata/realtimedata.html?t='+Math.random(),
            controller: 'realTimeDataController'

        })
         .state('main.realtimedata.realTimeSummaryData', {	//实时数据-游戏概览
            url: '/realTimeSummaryData',
            templateUrl: 'template/realtimedata/summary-data.html?t='+Math.random(),
            controller: 'realTimeSummaryDataController'

        })
	
        .state('main.concurrents', {	//一级在线分析
            url: '/concurrents',
            templateUrl: 'template/concurrent/concurrents.html?t='+Math.random(),
            controller: 'concurrentsController'

        })
	
        .state('main.concurrents.concurrent', {	//在线分析
            url: '/concurrent',
            templateUrl: 'template/concurrent/concurrent.html?t='+Math.random(),
            controller: 'concurrentController'

        })
        .state('main.concurrents.concurrentData', {	//在线数据
            url: '/concurrentData',
            templateUrl: 'template/concurrent/concurrentData.html?t='+Math.random(),
            controller: 'concurrentDataController'

        })
        
        .state('main.revenue', {	//收入分析
            url: '/revenue',
            templateUrl: 'template/revenue/revenue.html?t='+Math.random(),
            controller: 'revenueController'

        })
        .state('main.revenue.data', {	//收入数据
            url: '/data',
            templateUrl: 'template/revenue/data.html?t='+Math.random(),
            controller: 'revenueDataController'

        })
        .state('main.revenue.conversion', {	//付费渗透
            url: '/conversion',
            templateUrl: 'template/revenue/conversion.html?t='+Math.random(),
            controller: 'revenueConversionController'

        })
        .state('main.revenue.newplayervalue', {	//玩家价值
            url: '/newplayervalue',
            templateUrl: 'template/revenue/newplayervalue.html?t='+Math.random(),
            controller: 'revenueValueController'

        })
        .state('main.revenue.behavior', {	//付费习惯
            url: '/behavior',
            templateUrl: 'template/revenue/behavior.html?t='+Math.random(),
            controller: 'revenueBehaviorController'

        })
        .state('main.revenue.retation', {	//付费留存
            url: '/retation',
            templateUrl: 'template/revenue/retation.html?t='+Math.random(),
            controller: 'revenueRetationController'

        })
         .state('main.revenue.ltv', {	//Ltv值
            url: '/ltv',
            templateUrl: 'template/revenue/ltv.html?t='+Math.random(),
            controller: 'revenueLtvController'

        })
    
        .state('main.whaleUser', {	//鲸鱼用户
            url: '/whaleUser',
            templateUrl: 'template/whales/whaleUser.html?t='+Math.random(),
            controller: 'whaleUserController'

        })
	
        .state('main.whaleUser.whales', {	//鲸鱼用户
            url: '/whales',
            templateUrl: 'template/whales/whales.html?t='+Math.random(),
            controller: 'whalesController'

        })
	
        .state('main.channels', {	//推广渠道
            url: '/channels',
            templateUrl: 'template/channels/channels.html?t='+Math.random(),
            controller: 'channelsController'

        })
        .state('main.channels.channelsData', {	//渠道数据
            url: '/channelsData',
            templateUrl: 'template/channels/channelsData.html?t='+Math.random(),
            controller: 'channelsDataController'

        })
	
	
        .state('main.virtual', {	//虚拟消费
            url: '/virtual',
            templateUrl: 'template/virtual/virtual.html?t='+Math.random(),
            controller: 'virtualController'

        })	
        .state('main.virtual.currency', {	//虚拟币
            url: '/currency',
            templateUrl: 'template/virtual/currency.html?t='+Math.random(),
            controller: 'virtualCurrencyController'

        })
        .state('main.virtual.expense', {	//消费喜好
            url: '/expense',
            templateUrl: 'template/virtual/expense.html?t='+Math.random(),
            controller: 'virtualExpenseController'

        })
        .state('main.virtual.items', {	//消费点
            url: '/items',
            templateUrl: 'template/virtual/items.html?t='+Math.random(),
            controller: 'virtualItemsController'

        })
	
	
	
        .state('main.levels', {	//等级分析
            url: '/levels',
            templateUrl: 'template/levels/levels.html?t='+Math.random(),
            controller: 'levelsController'

        })
        .state('main.levels.detail', {	//等级详细
            url: '/detail',
            templateUrl: 'template/levels/detail.html?t='+Math.random(),
            controller: 'levelsDetailController'

        })
        .state('main.levels.distribute', {	//等级分布
            url: '/distribute',
            templateUrl: 'template/levels/distribute.html?t='+Math.random(),
            controller: 'levelsDistributeController'

        })
        .state('main.levels.progress', {	//新玩家进度
            url: '/progress',
            templateUrl: 'template/levels/progress.html?t='+Math.random(),
            controller: 'levelsProgressController'

        })
	
        .state('main.events', {	//自定义事件
            url: '/events',
            templateUrl: 'template/events/events.html?t='+Math.random(),
            controller: 'eventsController'

        })
        .state('main.events.data', {	//事件数据
            url: '/data',
            templateUrl: 'template/events/data.html?t='+Math.random(),
            controller: 'eventsDataController'

        })
        .state('main.events.detail', {	//事件详情
            url: '/detail?eventsId',
            templateUrl: 'template/events/detail.html?t='+Math.random(),
            controller: 'eventsDetailController'

        })
		
		.state('main.gift', {	//礼包
            url: '/gift',
            templateUrl: 'template/gift/gift.html?t='+Math.random(),
            controller: 'giftController'

        })
        .state('main.gift.giftData', {	//礼包领取
            url: '/giftData',
            templateUrl: 'template/gift/giftData.html?t='+Math.random(),
            controller: 'giftDataController'

        })
        .state('main.version', {	//版本
            url: '/version',
            templateUrl: 'template/version/version.html?t='+Math.random(),
            controller: 'versionController'

        })
        .state('main.version.versionData', {	//版本分析
            url: '/versionData',
            templateUrl: 'template/version/versionData.html?t='+Math.random(),
            controller: 'versionDataController'

        })
	
        .state('main.user', {	//用户
            url: '/user',
            templateUrl: 'template/user/user.html?t='+Math.random(),
            controller: 'userController'

        })
        .state('main.user.userManage', {	//用户管理
            url: '/userManage',
            templateUrl: 'template/user/userManage.html?t='+Math.random(),
            controller: 'userManageController'

        })
        .state('main.user.userModif', {	//用户添加修改
            url: '/userModif?id&ctype',
            templateUrl: 'template/user/userModif.html?t='+Math.random(),
            controller: 'userModifyController'

        })
        .state('main.user.resources', {	//用户资源修改
            url: '/resources?id&username',
            templateUrl: 'template/user/resources.html?t='+Math.random(),
            controller: 'resourcesController'

        })
        .state('main.user.role', {	//角色管理
            url: '/role',
            templateUrl: 'template/user/role.html?t='+Math.random(),
            controller: 'roleController'

        })
        .state('main.user.roleModif', {	//角色添加修改
            url: '/roleModif?id&ctype',
            templateUrl: 'template/user/roleModif.html?t='+Math.random(),
            controller: 'roleModifController'

        })
        .state('main.user.firstMenu', {	//一级菜单
            url: '/firstMenu',
            templateUrl: 'template/user/firstMenu.html?t='+Math.random(),
            controller: 'firstMenuController'

        })
        .state('main.user.firstMenuModif', {	//一级菜单修改
            url: '/firstMenuModif?ctype&id',
            templateUrl: 'template/user/firstMenuModif.html?t='+Math.random(),
            controller: 'firstMenuModifController'

        })
        .state('main.user.permission', {	//权限管理
            url: '/permission',
            templateUrl: 'template/user/permission.html?t='+Math.random(),
            controller: 'permissionController'

        })
        .state('main.user.permissionModif', {	//权限添加修改
            url: '/permissionModif?id&ctype',
            templateUrl: 'template/user/permissionModif.html?t='+Math.random(),
            controller: 'permissionModifController'

        })
	
	
        .state('main.config', {	//基础配置
            url: '/config',
            templateUrl: 'template/config/config.html?t='+Math.random(),
            controller: 'configController'
        })
        .state('main.config.gamearea', {	//区服管理
            url: '/gamearea',
            templateUrl: 'template/config/gamearea.html?t='+Math.random(),
            controller: 'gameareaController'
        })
        .state('main.config.gameareaModif', {	//区服添加修改
            url: '/gameareaModif?id',
            templateUrl: 'template/config/gameareaModif.html?t='+Math.random(),
            controller: 'gameareaModifController'
        })
        .state('main.config.platform', {	//平台管理
            url: '/platform',
            templateUrl: 'template/config/platform.html?t='+Math.random(),
            controller: 'platformController'
        })
        .state('main.config.platformModif', {	//平台添加
            url: '/platformModif?id',
            templateUrl: 'template/config/platformModif.html?t='+Math.random(),
            controller: 'platformModifController'
        })
        .state('main.config.project', {	//项目管理
            url: '/project',
            templateUrl: 'template/config/project.html?t='+Math.random(),
            controller: 'projectController'
        })
        .state('main.config.projectModif', {	//项目添加
            url: '/projectModif?id',
            templateUrl: 'template/config/projectModif.html?t='+Math.random(),
            controller: 'projectModifController'
        })
        .state('main.config.game', {	//游戏管理
            url: '/game',
            templateUrl: 'template/config/game.html?t='+Math.random(),
            controller: 'gameController'
        })
        .state('main.config.gameModif', {	//游戏添加
            url: '/gameModif?id',
            templateUrl: 'template/config/gameModif.html?t='+Math.random(),
            controller: 'gameModifController'
        })
        .state('main.config.channel', {	//渠道管理
            url: '/channel',
            templateUrl: 'template/config/channel.html?t='+Math.random(),
            controller: 'channelController'
        })
        .state('main.config.channelModif', {	//渠道添加
            url: '/channelModif?ctype&channelid',
            templateUrl: 'template/config/channelModif.html?t='+Math.random(),
            controller: 'channelModifController'
        })
        .state('main.config.subchannel', {	//子渠道管理
            url: '/subchannel',
            templateUrl: 'template/config/subchannel.html?t='+Math.random(),
            controller: 'subchannelController'
        })
        .state('main.config.subchannelModif', {	//子渠道添加
            url: '/subchannelModif?id&ctype',
            templateUrl: 'template/config/subchannelModif.html?t='+Math.random(),
            controller: 'subchannelModifController'
        })
        .state('main.config.gameChannel', {	//游戏渠道管理
            url: '/gameChannel',
            templateUrl: 'template/config/gameChannel.html?t='+Math.random(),
            controller: 'gameChannelController'
        })
        .state('main.config.gameversion', {	//游戏版本管理
            url: '/gameversion',
            templateUrl: 'template/config/gameversion.html?t='+Math.random(),
            controller: 'gameversionController'
        })
        .state('main.config.gameversionModify', {	//游戏版本添加
            url: '/gameversionModify?id&ctype',
            templateUrl: 'template/config/gameversionModify.html?t='+Math.random(),
            controller: 'gameversionModifyController'
        })
        .state('main.config.gamelanguage', {	//游戏语言管理
            url: '/gamelanguage',
            templateUrl: 'template/config/gamelanguage.html?t='+Math.random(),
            controller: 'gamelanguageController'
        })
        .state('main.config.gamelanguageModify', {	//游戏语言添加
            url: '/gamelanguageModify?id&ctype',
            templateUrl: 'template/config/gamelanguageModify.html?t='+Math.random(),
            controller: 'gamelanguageModifyController'
        })
         .state('main.config.rechgGainWarn', {	//充值获取警告
            url: '/rechgGainWarn',
            templateUrl: 'template/config/rechgGainWarn.html?t='+Math.random(),
            controller: 'rechgGainWarnController'
        })
         .state('main.config.rechgGainWarnModify', {	//充值获取警告修改
            url: '/rechgGainWarnModify?ctype&regchWarnId',
            templateUrl: 'template/config/rechgGainWarnModif.html?t='+Math.random(),
            controller: 'rechgGainWarnModifController'
        })
        .state('main.config.attrValue', {	//静态值管理
            url: '/attrValue',
            templateUrl: 'template/config/attrvalue.html?t='+Math.random(),
            controller: 'attrValueController'
        })
        
	
	
	
	
        .state('main.analysis', {	//分析工具
            url: '/analysis',
            templateUrl: 'template/analysis/analysis.html?t='+Math.random(),
            controller: 'analysisController'
        })
        .state('main.analysis.warning', {	//预警
            url: '/warning',
            templateUrl: 'template/analysis/warning.html?t='+Math.random(),
            controller: 'warningController'
        })

        .state('main.buyplat', {	//买量统计
            url: '/buyplat',
            templateUrl: 'template/buyplat/orderStatistics.html?t='+Math.random(),
            controller: 'orderStatisticsController'
        })
        /*.state('main.buyplat.adStatistics', {	//广告统计
            url: '/adStatistics',
            templateUrl: 'template/buyplat/adStatistics.html?t='+Math.random(),
            controller: 'adStatisticsController'
        })
        .state('main.buyplat.channelsModify', {	//广告渠道列表修改
            url: '/channelsModify?ctype',
            templateUrl: 'template/buyplat/channelsModify.html?t='+Math.random(),
            controller: 'channelsModifyController'
        })*/
        .state('main.buyplat.adChannels', {	//广告渠道
            url: '/adChannels',
            templateUrl: 'template/buyplat/adChannels.html?t='+Math.random(),
            controller: 'adChannelsController'
        })
        .state('main.buyplat.adChannelsModify', {	//广告渠道修改
            url: '/adChannelsModify?id&ctype',
            templateUrl: 'template/buyplat/adChannelsModify.html?t='+Math.random(),
            controller: 'adChannelsModifyController'
        })
        .state('main.buyplat.adMainList', {	//广告主列表
            url: '/adMainList',
            templateUrl: 'template/buyplat/adMainList.html?t='+Math.random(),
            controller: 'adMainListController'
        })
        .state('main.buyplat.adMainListModify', {	//广告主列表修改
            url: '/adMainListModify?ctype&id',
            templateUrl: 'template/buyplat/adMainListModify.html?t='+Math.random(),
            controller: 'adMainListModifyController'
        })
        .state('main.buyplat.amountManage', {	//扣量管理
            url: '/amountManage',
            templateUrl: 'template/buyplat/amountManage.html?t='+Math.random(),
            controller: 'amountManageController'
        })
        .state('main.buyplat.amountManageModify', {	//扣量管理修改
            url: '/amountManageModify?id&ctype',
            templateUrl: 'template/buyplat/amountManageModify.html?t='+Math.random(),
            controller: 'amountManageModifyController'
        })
        .state('main.buyplat.dataGather', {	//数据汇总
            url: '/dataGather',
            templateUrl: 'template/buyplat/dataGather.html?t='+Math.random(),
            controller: 'dataGatherController'
        })
        .state('main.buyplat.dataQuery', {	//数据查询
            url: '/dataQuery',
            templateUrl: 'template/buyplat/dataQuery.html?t='+Math.random(),
            controller: 'dataQueryController'
        })
        .state('main.buyplat.registerPeriod', {	//注册时间段
            url: '/registerPeriod',
            templateUrl: 'template/buyplat/registerPeriod.html?t='+Math.random(),
            controller: 'registerPeriodController'
        })
        .state('main.buyplat.ranksDistribute', {	//游戏角色等级分布
            url: '/ranksDistribute',
            templateUrl: 'template/buyplat/ranksDistribute.html?t='+Math.random(),
            controller: 'ranksDistributeController'
        })
        .state('main.buyplat.qdUserRegInfo', {	//渠道用户注册信息
            url: '/qdUserRegInfo',
            templateUrl: 'template/buyplat/qdUserRegInfo.html?t='+Math.random(),
            controller: 'qdUserRegInfoController'
        })
//        .state('main.buyplat.advertiserData', {	//广告主数据
//            url: '/advertiserData',
//            templateUrl: 'template/buyplat/advertiserData.html?t='+Math.random(),
//
//            controller: 'advertiserDataController'
//        })
        .state('main.buyplat.advertiserQuer', {	//广告主查渠道
            url: '/advertiserQuer',
            templateUrl: 'template/buyplat/advertiserQuer.html?t='+Math.random(),

            controller: 'advertiserQuerController'
        })
    
    
        .state('main.advertiserplat', {	//广告主平台
            url: '/advertiserplat',
            templateUrl: 'template/buyplat/advertiserplat.html?t='+Math.random(),

            controller: 'advertiserplatController'
        })
        .state('main.advertiserplat.advertiserData', {	//广告主数据
            url: '/advertiserplat.advertiserData',
            templateUrl: 'template/buyplat/advertiserData.html?t='+Math.random(),

            controller: 'advertiserDataController'
        })
	
	
        .state('main.balanceplat', {	//对账平台
            url: '/balanceplat',
            templateUrl: 'template/balanceplat/balance.html?t='+Math.random(),

            controller: 'balanceplatController'
        })
		.state('main.balanceplat.channelbalance', {	//渠道对账
            url: '/channelbalance',
            templateUrl: 'template/balanceplat/channelbalance.html?t='+Math.random(),

            controller: 'channelbalanceController'
        })
		.state('main.balanceplat.channelbalanceModify', {	//添加渠道对账
            url: '/channelbalanceModify?ctype&id',
            templateUrl: 'template/balanceplat/channelbalanceModify.html?t='+Math.random(),

            controller: 'channelbalanceModifyController'
        })
		.state('main.balanceplat.billingform', {	//对账报表
            url: '/billingform',
            templateUrl: 'template/balanceplat/billingform.html?t='+Math.random(),

            controller: 'billingformController'
        })
		.state('main.balanceplat.billingformbyweek', {	//对账报表(周)
            url: '/billingformbyweek',
            templateUrl: 'template/balanceplat/billingformbyweek.html?t='+Math.random(),

            controller: 'billingformbyweekController'
        })
		.state('main.balanceplat.paychannel', {	//支付渠道
            url: '/paychannel',
            templateUrl: 'template/balanceplat/paychannel.html?t='+Math.random(),

            controller: 'paychannelController'
        })
		.state('main.balanceplat.autobalance', {	//自动对账
            url: '/autobalance',
            templateUrl: 'template/balanceplat/autobalance.html?t='+Math.random(),

            controller: 'autobalanceController'
        })
        .state('main.balanceplat.applebalance', {	//苹果对账
            url: '/applebalance',
            templateUrl: 'template/balanceplat/applebalance.html?t='+Math.random(),

            controller: 'applebalanceController'
        })
        .state('main.balanceplat.videoAdvertiserConfig', {	//视频广告商配置
            url: '/videoAdvertiserConfig',
            templateUrl: 'template/balanceplat/videoAdvertiser.html?t='+Math.random(),
            controller: 'videoAdvertiserController'
        })
        .state('main.balanceplat.videoAdvertiserModif', {	//添加视频广告商
            url: '/videoAdvertiserModif?id',
            templateUrl: 'template/balanceplat/videoAdvertiserModif.html?t='+Math.random(),
            controller: 'videoAdvertiserModifController'
        })
        
        .state('main.sdkdata', {	//sdk数据分析
        	 url: '/sdkdata',
             templateUrl: 'template/sdkdata/sdkdata.html?t='+Math.random(),
             controller: 'sdkDataController'
        })
        .state('main.sdkdata.sdkdatasummary', {	//sdk数据分析-数据总览
        	 url: '/sdkdatasummary',
             templateUrl: 'template/sdkdata/sdkdatasummary.html?t='+Math.random(),
             controller: 'sdkdataSummaryController'
        })
        .state('main.sdkdata.sdkdatanewadd', {	//sdk数据分析-新增数据
        	 url: '/sdkdatanewadd',
             templateUrl: 'template/sdkdata/sdkdatanewadd.html?t='+Math.random(),
             controller: 'sdkdataNewAddController'
        })
        .state('main.sdkdata.sdkdataloginanalysis', {	//sdk数据分析-登录分析
        	 url: '/sdkdataloginanalysis',
             templateUrl: 'template/sdkdata/sdkdataloginanalysis.html?t='+Math.random(),
             controller: 'sdkdataLoginAnalysisController'
        })
        .state('main.sdkdata.sdkdatabrowser', {	//sdk数据分析-浏览统计
        	 url: '/sdkdatabrowser',
             templateUrl: 'template/sdkdata/sdkdatabrowser.html?t='+Math.random(),
             controller: 'sdkdataBrowserController'
        })
        .state('main.sdkdata.sdkdatadownload', {	//sdk数据分析-下载统计
        	 url: '/sdkdatadownload',
             templateUrl: 'template/sdkdata/sdkdatadownload.html?t='+Math.random(),
             controller: 'sdkdataDownloadController'
        })
        .state('main.sdkdata.sdkdatadownsrc', {	//sdk数据分析-下载来源
        	 url: '/sdkdatadownsrc',
             templateUrl: 'template/sdkdata/sdkdatadownsrc.html?t='+Math.random(),
             controller: 'sdkdataDownSrcController'
        })
        .state('main.sdkdata.sdkdatanewvip', {	//sdk数据分析-VIP新增
        	 url: '/sdkdatanewvip',
             templateUrl: 'template/sdkdata/sdkdatanewvip.html?t='+Math.random(),
             controller: 'sdkdataNewVipController'
        })
        .state('main.sdkdata.sdkdatavipdist', {	//sdk数据分析-VIP分布
        	 url: '/sdkdatavipdist',
             templateUrl: 'template/sdkdata/sdkdatavipdist.html?t='+Math.random(),
             controller: 'sdkdataVipDistController'
        })
        .state('main.sdkdata.sdkdatavipsummary', {	//sdk数据分析-VIP分布
        	 url: '/sdkdatavipsummary',
             templateUrl: 'template/sdkdata/sdkdatavipsummary.html?t='+Math.random(),
             controller: 'sdkdataVipSummaryController'
        })
         .state('main.players.rollserv', {	//滚服分析
            url: '/rollserv',
            templateUrl: 'template/players/RollServ.html?t='+Math.random(),
            controller: 'playersRollServController'

        })
        .state('main.indpd',{//独立数据
        	url:'/indpd',
        	templateUrl:'template/idp/indpdData.html',
        	controller:'indpdController'
        })
        .state('main.yunyindata',{//hie运营数据
        	url:'/yunyindata',
        	templateUrl:'template/hie_idp/yunyindata.html',
        	controller:'yunyindataController'
        })
         .state('main.consumedata',{//hie消费数据
        	url:'/consumedata',
        	templateUrl:'template/hie_idp/consumedata.html',
        	controller:'consumedataController'
        })
        
        
        
        
        .state('main.dauMode',{//dau模型
        	url:'/dauMode',
        	templateUrl:'template/daumode/dauMode.html',
        	controller:'dauModeController'
        })
         .state('main.dauMode.dauAndIncome',{//dau和收入模型
        	url:'/dauAndIncome',
        	templateUrl:'template/daumode/dauAndIncome.html',
        	controller:'dauAndIncomeController'
        })
        .state('main.players.accoutLost', {	//账号流失
            url: '/accoutLost',
            templateUrl: 'template/players/accoutLost.html?t='+Math.random(),
            controller: 'accountLostController'

        })
        .state('main.balanceplat.channelCompany', {	//渠道公司
            url: '/channelCompany',
            templateUrl: 'template/balanceplat/channelCompany.html?t='+Math.random(),
            controller: 'channelCompanyController'
        })
        .state('main.balanceplat.channelCompanyModify', {	//渠道公司修改
            url: '/channelCompanyModify?ctype&id',
            templateUrl: 'template/balanceplat/channelCompanyModify.html?t='+Math.random(),
            controller: 'channelCompanyModifyController'
        })
        .state('main.balanceplat.contractFile', {	//合同扫描件
            url: '/contractFile',
            templateUrl: 'template/balanceplat/contractFile.html?t='+Math.random(),
            controller: 'contractFileController'
        })
         .state('main.afPushApi',{//af_push_api
        	url:'/afPushApi',
        	templateUrl:'template/af_push_api/afPushApi.html',
        	controller:'afPushApiController'
        })
        .state('main.afPushApi.afPurchase', {	//af_purchase
            url: '/afPurchase',
            templateUrl: 'template/af_push_api/afPurchase.html?t='+Math.random(),
            controller: 'afPurchaseController'
        })
         .state('main.xgfinance', {	//香港财务统计
        	 url: '/xgfinance',
             templateUrl: 'template/xgfinance/xgfinance.html?t='+Math.random(),
             controller: 'xgFinanceController'
        })
         .state('main.xgfinance.xgFinanceCost', {	//香港财务费用
        	 url: '/xgFinanceCost',
             templateUrl: 'template/xgfinance/xgFinanceCost.html?t='+Math.random(),
             controller: 'xgFinanceCostController'
        })
        .state('main.xgfinance.xgFinanceGameUser', {	//香港财务游戏用户
        	 url: '/xgFinanceGameUser',
             templateUrl: 'template/xgfinance/xgFinanceGameUser.html?t='+Math.random(),
             controller: 'xgFinanceGameUserController'
        })
        
        .state('main.financeStat', {	//财务统计
        	 url: '/financeStat',
             templateUrl: 'template/financestat/financestat.html?t='+Math.random(),
             controller: 'financeStatController'
        })
        .state('main.financeStat.financeGameUser', {	//游戏用户
        	 url: '/financeGameUser',
             templateUrl: 'template/financestat/financeGameUser.html?t='+Math.random(),
             controller: 'financeGameUserController'
        })
        
        
        
        
        
      
        
        
})

