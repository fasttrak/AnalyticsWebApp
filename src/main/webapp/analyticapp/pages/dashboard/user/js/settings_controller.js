angular.module('analyticApp').controller('UserSettingsController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService) {
	console.log('UserProfileController start');
	
	initController();
	function initController(){
		$scope.acc={isAccOpen:[true]};
		$scope.settingsMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'settingsSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'settingsErrorMessage',
        		message:null
    	};
		
		$scope.user={};
	}
	
	$scope.checkConfirmPassword=function(formFieldObj){
		if($scope.user.password==$scope.user.confirmPassword){
			formFieldObj.$error.unique=false;
			formFieldObj.$invalid=false;
		}else{
			formFieldObj.$error.unique=true;
			formFieldObj.$invalid=true;
		}
	}
	
	$scope.onClickChangePassword=function(){
		 var response = $http.post("../../webapp/user/changeUserPassword", $scope.user, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
				 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.settingsMessageHandlingObject, 
						 "Password changed successfully.",
						 "Invalid Username/Password");
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 data.isError=true;
			 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.settingsMessageHandlingObject, 
					 "",
					 "Password doesn't match");
			return $q.reject(response);
		 });
	}
	
});