'use strict';

angular.module('analyticApp')
  .directive('sidebar',['$location',function() {
    return {
      templateUrl:'js/directives/sidebar/sidebar.html',
      restrict: 'E',
      replace: true,
      scope: {
      },
      controller:function($scope, $http){
        $scope.selectedMenu = 'dashboard';
        $scope.collapseVar = 0;
        $scope.multiCollapseVar = 0;
        
        $scope.check = function(x){
          
          if(x==$scope.collapseVar)
            $scope.collapseVar = 0;
          else
            $scope.collapseVar = x;
        };
        
        $scope.multiCheck = function(y){
          
          if(y==$scope.multiCollapseVar)
            $scope.multiCollapseVar = 0;
          else
            $scope.multiCollapseVar = y;
        };
        
        var response = $http.get("../../webapp/group/getGroup", null, {});
		response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.accessMap=data.tabAccess;
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 console.log(data);
			 console.log(status);
			 return $q.reject(response);
		 });
      }
    }
  }]);
