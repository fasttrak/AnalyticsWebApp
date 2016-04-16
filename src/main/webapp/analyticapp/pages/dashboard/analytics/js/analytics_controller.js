angular.module('analyticApp').controller('AnalyticsController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService) {
	console.log('AnalyticsController start');

	initController();
	function initController(){
		$scope.analyticsMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'analyticsSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'analyticsErrorMessage',
        		message:null
    	};
	}
	
	//service stub
	$scope.analyticsTypesOptions=[{value:'cpu_utilization', displayName:'CPU Utilization'},
	                              {value:'memory_utilization', displayName:'Memory Utilization'}];
	$scope.hostsOptions=[{value:'id1', displayName:'192.168.10.20'},
	                     {value:'id2', displayName:'192.168.10.21'}];
	$scope.timeframeOptions=[{value:'four_days', displayName:'Four Days'},
	                     	{value:'one_week', displayName:'One Week'},
	                     	{value:'one_month', displayName:'One Month'}];
	//service stub
	
	
	
});