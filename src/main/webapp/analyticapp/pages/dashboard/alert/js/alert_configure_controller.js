angular.module('analyticApp').controller('AlertConfigureController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService, 
	 						NgTableParams, $resource, dialogs, $resource, $modalInstance) {
	console.log('AlertConfigureController start');
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	}; 
	
	$scope.save = function(){
		 //load user details
		 var data=[{key:'cpu', intString:$scope.alertConfigure.cpuThreshold}, {key:'memory', intString:$scope.alertConfigure.memoryThreshold}];
		 response = $http.post("../../webapp/alert/updateAlertConfig", data, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    $modalInstance.close(true);
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 $modalInstance.close(false);
			return $q.reject(response);
		 });
		
	};
	
});