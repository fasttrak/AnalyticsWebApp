angular.module('analyticApp').controller('UserProfileController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService) {
	console.log('UserProfileController start');
	
	initController();
	function initController(){
		
		$scope.profileMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'profileSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'profileErrorMessage',
        		message:null
    	};
		
		 //load user details
		 var response = $http.get("../../webapp/user/getUser", null, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.user=data;
			    $scope.user.isEditing=false;
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 data.isError=true;
			 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.loginMessageHandlingObject, 
					 "",
					 "Invalid Username/Password");
			return $q.reject(response);
		 });
	}
	
	$scope.updateUser=function(){
		 var response = $http.post("../../webapp/user/updateUser", $scope.user, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.user=data;
			    $scope.user.isEditing=false;
			    successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.profileMessageHandlingObject, 
						 "Profile updated successfully.",
						 "Technical error has occured. Please contact system administrator.");
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 data.isError=true;
			 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.profileMessageHandlingObject, 
					 "Profile updated successfully.",
					 "Technical error has occured. Please contact system administrator.");
			return $q.reject(response);
		 });
	}
	
});