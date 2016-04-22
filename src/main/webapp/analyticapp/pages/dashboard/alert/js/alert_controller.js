angular.module('analyticApp').controller('AlertController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService, 
	 						NgTableParams, $resource, dialogs, $resource) {
	console.log('AlertController start');
	
	function initController(){
		$scope.alertMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'alertSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'alertErrorMessage',
        		message:null
    	};
		$scope.alertToUpdate={};
		$scope.hideAlertInformation=true;
	}
	initController();
	
	function defineAlertTable(){
		$scope.alertTable={};
		$scope.alertTable.cols = [
		             { field: "type",  objField:"type", title: "Type", show: true, filter: {type: 'text'}, sortable: "type", width: '20%' },
		             { field: "assignedTo",  objField:"assignedTo", title: "Assigned to", show: true, filter: {assignedTo: 'text'}, sortable: "assignedTo", width: '20%' },
		             { field: "description",  objField:"description", title: "Description", show: true, filter: {description: 'text'}, sortable: "description", width: '20%' },
		             { field: "createDateTime", objField:"createDateTime", title: "Detected On", show: true, sortable: "createDateTime", width: '15%'  },
		             { title: "Action", show: true, width: '5%'  }
		           ];
		$scope.alertTable.tableParams = new NgTableParams({
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
				if(tableSortsList.length<1){
					tableSortsList.push({key:"createDateTime", valueString:"desc"});
				}
				var paginationData={"filterParams":tableFiltersList,"sortingParams":tableSortsList, "page":params.page(), "size": params.count()};
				var Api = $resource("../../webapp/alert/getAllAlerts");
				return Api.save(paginationData).$promise.then(function(paginationResponseData) {
			          params.total(paginationResponseData.totalRecords);
			          return paginationResponseData.data;
			    });
			}
		});
	};
	
	defineAlertTable();
	
	$scope.getAlertDetails=function(alertId){
		if($scope.hideAlertInformation){
			var response = $http.get("../../webapp/alert/getAlertInformation", {params:{'alertId':alertId}});
			response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.hideAlertInformation=false;
			    $scope.alertToUpdate=data;
			    $scope.alertDataToSave={id:$scope.alertToUpdate.id, alertUpdateMessages:[{updatedByUser:$scope.user}]};
			    $scope.alertDataToSave.assignedTo=data.assignedTo;
			    $scope.typeaheadAssignedTo=data.assignedTo;
			    return data;
			});
			response.error(function(data, status, headers, config) {
				return $q.reject(response);
			});
		}else{
			$scope.hideAlertInformation=true;
		}
	}
	
	 //load user details
	 var response = $http.get("../../webapp/user/getUser", null, {});
	 response.success(function(data, status, headers, config) {
		    console.log(data);
		    $scope.user=data;
		    $scope.user.password=null;
		    return data;
	 });
	 response.error(function(data, status, headers, config) {
		 data.isError=true;
		 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.loginMessageHandlingObject, 
				 "",
				 "Invalid Username/Password");
		return $q.reject(response);
	 });
	
	 $scope.onChangeTypeaheadAssignedTo=function(typeaheadAssignedTo){
	    	console.log("onChangeTypeaheadAssignedTo");
	    	console.log(typeaheadAssignedTo);
	    	$scope.alertDataToSave.assignedTo=typeaheadAssignedTo;
	 } 
	 
	 $scope.getUsers=function(val){
		 var paginationData={"filterParams":[{key:'username', valueString:val}],"sortingParams":null, "page":1, "size": 10};
		 var Api = $resource("../../webapp/user/getAllUsers");
		 return Api.save(paginationData).$promise.then(function(paginationResponseData) {
	          return paginationResponseData.data;
		 });
	 }
	 
	 $scope.updateAlertMessage=function(){
		 var response = $http.post("../../webapp/alert/updateAlertUpdateMessage", $scope.alertDataToSave, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    
			    successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.alertMessageHandlingObject, 
						 "Alert updated successfully.",
						 "Technical error has occured. Please contact system administrator.");
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 data.isError=true;
			 successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.alertMessageHandlingObject, 
					 "Alert updated successfully.",
					 "Technical error has occured. Please contact system administrator.");
			return $q.reject(response);
		 });
	 }
	 
	 $scope.configureAlert=function(){
		 var dialog=dialogs.create('pages/dashboard/alert/alert_configure.html','AlertConfigureController',{},{},'md');
			dialog.result.then(function(success){
				//called when user click save/edit/delete button
				if(success){
					data=success;
					successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.alertMessageHandlingObject, 
							"Alert threshold configuration updated successfully",
							"Technical error has occured. Please contact system administrator.");
				}else{
					data.isError=true;
					successAndErrorMessageHandlingService.handleSuccessAndErrorMessage(data, $scope.alertMessageHandlingObject, 
							 "Alert threshold configuration updated successfully",
							 "Technical error has occured. Please contact system administrator.");
				}
			},function(btn){
				//called when user click cancel button
		});
	 }
});