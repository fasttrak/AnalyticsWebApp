angular.module('analyticApp').controller('GroupOperationController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService, $resource, NgTableParams, ngTableDefaults, dialogs, $modalInstance, data) {
	console.log('GroupOperationController start');
	
	$scope.operation=data.data.operation;
	$scope.newGroup=data.data.group;
	
	console.log($scope.operation);
	console.log($scope.newGroup);
	
	if($scope.operation=='add'){
		$scope.newGroup.tabAccess=[{key:'Dashboard', value:false},{key:'Analytics', value:false}, {key:'Real Time Analytics', value:false}, {key:'Access Management', value:false}];
	}
	
	function defineAccessListTable(dataArray){
		$scope.accessListTable={};
		ngTableDefaults.settings.counts = [];
		$scope.accessListTable.tableParams = new NgTableParams({
			page: 1,
			count: 5
		},{
			dataset: dataArray
		});
	}
	defineAccessListTable($scope.newGroup.tabAccess);
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	}; 
	
	$scope.save = function(){
		 var response;
		 if($scope.operation=='add'){
			 response = $http.post("../../webapp/group/addGroup", $scope.newGroup, {});
		 }else if($scope.operation=='edit'){
			 response = $http.post("../../webapp/group/updateGroup", $scope.newGroup, {});
		 } else if($scope.operation=='delete'){
			 $scope.newGroup.tabAccess=null;
			 response = $http.post("../../webapp/group/deleteGroup", $scope.newGroup, {});
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