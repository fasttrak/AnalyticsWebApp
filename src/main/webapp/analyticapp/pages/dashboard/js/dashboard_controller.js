angular.module('analyticApp').controller('DashboardController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams) {
	console.log('dashboardController start ');
	$scope.user=$stateParams.user;
	
	initController();
	function initController(){
		if($scope.user==null){
			 var response = $http.get("../../webapp/user/getUser", null, {});
			 response.success(function(data, status, headers, config) {
				    console.log(data);
				    $scope.user=data;
				    return data;
			 });
			 response.error(function(data, status, headers, config) {
				 console.log(data);
				 console.log(status);
				 return $q.reject(response);
			 });
		}
	}
	
	/*$scope.activeTab={'', 'analyticsTab':false, 'realTimeAnalyticsTab':false};
	$scope.onChangeTab=function(tabName){
		$scope.activeTab['analyticsTab']=false;
		$scope.activeTab['realTimeAnalyticsTab']=false;
		$scope.activeTab[tabName]=true;
	}*/
	
});