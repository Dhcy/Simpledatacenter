function adStatisticsController($scope,$http){
       
    $scope.panel_1={    
        'model':'table',
        'dataList_1':{
            'data':'',
            'graphLimit':{
                'sumLen':0,
                'now':-1,
                'max':10,
                'count':0               
            },
            'limit':{
                'sumLen':0,
                'now':0,
                'max':10,
                'count':0
            },
            'dataLimit':'',
            'orderBy':{'s':'','t':''}   
        }
    };
    $scope.$watch("mchange.date", function (nv, ov) {
        if ($scope.mchange.date) {
            $scope.getAllData();
            $scope.mchange.date = false;
        }
    });
    
}