angular.module('analyticApp').controller('UserManagementController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService, NgTableParams, $resource) {
	console.log('UserProfileController start');

	initController();
	function initController(){
		initUserTable();
	}
	
	function initUserTable(){
		$scope.userTable={};
		$scope.userTable.cols = [
		             { field: "username",  objField:"username", title: "Username", show: true, filter: {username: 'text'}, sortable: "username", width: '25%' },
		             { field: "name", objField:"name", title: "Name", show: true, filter: {name: 'text'}, sortable: "name", width: '25%'  },
		             { field: "position", objField:"position", title: "Position", show: true, filter: {position: 'text'}, sortable: "position", width: '25%'  },
		             { field: "role", objField:"role", title: "Role", show: true, filter: {role: 'text'}, sortable: "role", width: '25%'  },
		           ];
		$scope.userTable.tableParams = new NgTableParams({
			page: 1,
			count: 10
		},{
			getData: function(params){
				var tableFilters=params.filter();
				var tableSorts=params.sorting();
				var paginationData={"filterParams":tableFilters,"sortingParams":tableSorts, "page":params.page(), "size": params.count()};
				var Api = $resource("../../webapp/user/getAllUsers");
				return Api.save(paginationData).$promise.then(function(paginationResponseData) {
			          params.total(paginationResponseData.totalRecords);
			          return paginationResponseData.data;
			    });
			}
		});
	}
	
});	