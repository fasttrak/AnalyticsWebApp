angular.module('analyticApp').controller('AccessManagementController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, successAndErrorMessageHandlingService, NgTableParams, $resource, dialogs) {
	console.log('AccessManagementController start');

	$scope.onClickUserManagementTab=function(){
		var response = $http.get("../../webapp/group/getAllGroups", $scope.newUser, {});
		response.success(function(data, status, headers, config) {
		    console.log(data);
		    $scope.groupsInfo=data;
		    defineUserTable();
		    return data;
		});
		$scope.userManagementMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'userManagementSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'userManagementErrorMessage',
        		message:null
    	};
	};
	
	initController();
	function initController(){
		$scope.onClickUserManagementTab();
	};
	
	function defineUserTable(){
		$scope.userTable={};
		$scope.userTable.cols = [
		             { field: "username",  objField:"username", title: "Username", show: true, filter: {username: 'text'}, sortable: "username", width: '25%' },
		             { field: "name", objField:"name", title: "Name", show: true, filter: {name: 'text'}, sortable: "name", width: '25%'  },
		             { field: "position", objField:"position", title: "Position", show: true, filter: {position: 'text'}, sortable: "position", width: '25%'  },
		             { field: "role", objField:"role", title: "Group", show: true, filter: {role: 'text'}, sortable: "role", width: '15%'  },
		             { title: "Action", show: true, width: '10%'  }
		           ];
		$scope.userTable.tableParams = new NgTableParams({
			page: 1,
			count: 10
		},{
			getData: function(params){
				var tableFilters=params.filter();
				var tableSorts=params.sorting();
				var tableFiltersList=[];
				var tableFiltersKeys=Object.keys(tableFilters);
				for(var i=0; i<tableFiltersKeys.length; i++){
					tableFiltersList.push({key:tableFiltersKeys[i], valueString:tableFilters[tableFiltersKeys[i]]});
				}
				
				var tableSortsList=[];
				var tableSortsKeys=Object.keys(tableSorts);
				for(var i=0; i<tableSortsKeys.length; i++){
					tableSortsList.push({key:tableSortsKeys[i], valueString:tableSorts[tableSortsKeys[i]]});
				}
				
				var paginationData={"filterParams":tableFiltersList,"sortingParams":tableSortsList, "page":params.page(), "size": params.count()};
				var Api = $resource("../../webapp/user/getAllUsers");
				return Api.save(paginationData).$promise.then(function(paginationResponseData) {
			          params.total(paginationResponseData.totalRecords);
			          for(var j=0; j<paginationResponseData.data.length; j++){
			        	  var pData=paginationResponseData.data[j];
			        	  for(var i=0; i<$scope.groupsInfo.length; i++){
					  			if(pData.role==$scope.groupsInfo[i].id){
					  				pData.role=$scope.groupsInfo[i].name;
					  				break;
					  			}
					  	  }
			          } 
			          return paginationResponseData.data;
			    });
			}
		});
	};
	
	$scope.openUserModal=function(operation, user){
		var data={operation:operation, user:user, groups:$scope.groupsInfo};
		console.log(data);
		var dialog=dialogs.create('pages/dashboard/user_management/user_modal.html','UserOperationController',{data},{},'lg');
		dialog.result.then(function(success){
			//called when user click save/edit/delete button
			if(success){
				var successMessage="";
				if(data.operation=='add'){
					successMessage="User added successfully";
				}else if(data.operation=='edit'){
					successMessage="User edited successfully";
				}else if(data.operation=='delete'){
					successMessage="User deleted successfully";
				}
				successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.userManagementMessageHandlingObject, 
						successMessage,
						"Technical error has occured. Please contact system administrator.");
				defineUserTable();
			}else{
				data.isError=true;
				successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.userManagementMessageHandlingObject, 
						 "",
						 "Technical error has occured. Please contact system administrator.");
			}
		},function(btn){
			//called when user click cancel button
		});
	};
	
	function defineGroupTable(dataArray){
		$scope.groupTable={};
		$scope.groupTable.cols = [
		             { field: "name",  objField:"name", title: "Name", show: true, filter: {name: 'text'}, sortable: "name", width: '40%' },
		             { field: "description", objField:"description", title: "Description", show: true, filter: {description: 'text'}, sortable: "description", width: '40%'  },
		             { title: "Action", show: true, width: '20%'  }
		           ];
		$scope.groupTable.tableParams = new NgTableParams({
			page: 1,
			count: 5
		},{
			dataset: dataArray
		});
	};
	
	$scope.onClickGroupManagementTab=function(data){
		 var response = $http.get("../../webapp/group/getAllGroups", $scope.newUser, {});
		 response.success(function(data, status, headers, config) {
		    console.log(data);
		    $scope.groupsInfo=data;
		    defineGroupTable(data);
		    return data;
		 });
		 $scope.groupManagementMessageHandlingObject = {
	    			showSuccessMessage:false,
	    			successMessageDivId:'groupManagementSuccessMessage',
	        		showErrorMessage:false,
	        		errorMessageDivId:'groupManagementErrorMessage',
	        		message:null
	    	};
	};
	
	$scope.openGroupModal=function(operation, group){
		var data={operation:operation, group:group};
		console.log(data);
		var dialog=dialogs.create('pages/dashboard/user_management/group_modal.html','GroupOperationController',{data},{},'lg');
		dialog.result.then(function(success){
			//called when user click save/edit/delete button
			$scope.onClickGroupManagementTab();
			if(success){
				var successMessage="";
				if(data.operation=='add'){
					successMessage="Group added successfully";
				}else if(data.operation=='edit'){
					successMessage="Group edited successfully";
				}else if(data.operation=='delete'){
					successMessage="Group deleted successfully";
				}
				successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.groupManagementMessageHandlingObject, 
						successMessage,
						"Technical error has occured. Please contact system administrator.");
			}else{
				data.isError=true;
				successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.groupManagementMessageHandlingObject, 
						 "",
						 "Technical error has occured. Please contact system administrator.");
			}
		},function(btn){
			//called when user click cancel button
		});
	}
	
});	