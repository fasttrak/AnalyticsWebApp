'use strict';
angular.module('analyticApp')
	.directive('header',function(){
		return {
	        templateUrl:'js/directives/header/header.html',
	        restrict: 'E',
	        replace: true,
	        scope: {
	        },
	        controller:function($scope, $resource){
	        	var paginationData={"filterParams":null,"sortingParams":[{key:"createDateTime", valueString:"desc"}], "page":1, "size": 5};
				var Api = $resource("../../webapp/alert/getAllAlerts");
				return Api.save(paginationData).$promise.then(function(paginationResponseData) {
			          $scope.topAlerts=paginationResponseData.data;
			          console.log("top alerts");
			          console.log($scope.topAlerts);
			          return paginationResponseData.data;
			    });
	        }
    	}
});


