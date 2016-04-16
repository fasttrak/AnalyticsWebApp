angular.module('analyticApp').controller('LoginController',
	 				function($scope, $http, $rootScope, $q, $state, successAndErrorMessageHandlingService) {
	console.log('LoginController startt');
	
	initController();
	function initController(){
		$scope.loginMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'loginSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'loginErrorMessage',
        		message:null
    	};
	}
	
	$scope.onClickLogin=function(){
		 var data={"username":$scope.username,"password":$scope.password};
		 var response = $http.post("../../webapp/user/login", data, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
				$state.go('dashboard.home', {user:data});
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
	
});