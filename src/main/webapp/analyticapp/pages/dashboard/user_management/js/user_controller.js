angular.module('analyticApp').controller('UserOperationController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService, $resource, dialogs, $modalInstance, data) {
	console.log('AddUserController start');
	
	$scope.operation=data.data.operation;
	$scope.newUser=data.data.user;
	$scope.groups=data.data.groups;
	
	console.log($scope.operation);
	console.log($scope.newUser);
	console.log($scope.groups);
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	}; 
	
	$scope.save = function(){
		 //load user details
		 var response;
		 if($scope.operation=='add'){
			 response = $http.post("../../webapp/user/addUser", $scope.newUser, {});
		 }else if($scope.operation=='edit'){
			 response = $http.post("../../webapp/user/updateUser", $scope.newUser, {});
		 } else if($scope.operation=='delete'){
			 response = $http.post("../../webapp/user/deleteUser", $scope.newUser, {});
		 }
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