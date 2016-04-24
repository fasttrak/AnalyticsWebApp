angular.module('analyticApp').controller('HistoricalDataController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService,$filter, NgTableParams, $resource, dialogs) {
	console.log('HistoricalDataController start');
	
	initController();
	function initController(){
		$scope.historicalData={};
		$scope.historicalData.showGraph=false;
		$scope.historicalDataMessageHandlingObject = {
    			showSuccessMessage:false,
    			successMessageDivId:'historicalDataSuccessMessage',
        		showErrorMessage:false,
        		errorMessageDivId:'historicalDataErrorMessage',
        		message:null
    	};
		var response = $http.get("../../webapp/analytics/getAllAnalytics", null, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.analytics=data;
			    initializeHistoricalDate();
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 console.log(data);
			 console.log(status);
			 return $q.reject(response);
		});
	}

	function initializeHistoricalDate(){
		$scope.analyticsTypesOptions=[{value:'cpu', displayName:'CPU Utilization'},
		                              {value:'memory', displayName:'Memory Utilization'}];
		$scope.hostsOptions=[];
		//$scope.hostsOptions.push({value: 'all', displayName: 'All Hosts'});
		for(var i=0; i<$scope.analytics.length; i++){
			 var analytic=$scope.analytics[i];
			 $scope.hostsOptions.push({value: analytic.ip, displayName: analytic.ip});
		}
		$scope.timeframeOptions=[{value:'one_hour', displayName:'One Hour'}
			                     	,{value:'one_day', displayName:'One Day'}
			                     	/*,{value:'one_month', displayName:'One Month'}*/];
	}
	
	//graphTitle=String, nextTimeSlots([String], yAxisText=String, yAxisSuffix=String(%), dataSeries=[{name:String, data:[float]}])
	 function configureLineGraph(graphTitle, nextTimeSlots, yAxisText, yAxisSuffix, dataSeries){
		 $scope.chartConfig = {
				 options:{
					 chart: {
				            type: 'area'
				        },
				        tooltip: {
				            pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} percentage)<br/>',
				            shared: true
				        },
				        plotOptions: {
				            area: {
				                stacking: 'percent',
				                lineColor: '#ffffff',
				                lineWidth: 1,
				                marker: {
				                    lineWidth: 1,
				                    lineColor: '#ffffff'
				                }
				            }
				        }
				 },
				 title: {
			            text: graphTitle
			     },
			     xAxis: {
			            categories: nextTimeSlots,
			            tickmarkPlacement: 'on',
			            title: {
			                enabled: false
			            }
			     },
			     yAxis: {
			            title: {
			                text: yAxisText
			            }
			     },
		 		 series: dataSeries
			 };
	 }
	
	 //for datepicker
	 $scope.open = function($event, field) {
    	$event.preventDefault();
	    $event.stopPropagation();
	    if(field=='StartDate'){
	    	$scope.startDate={};
	    	$scope.startDate.opened = true;
	    }else if(field=='EndDate'){
	    	$scope.endDate={};
	    	$scope.endDate.opened = true;
	    }
	 };
	 
	 $scope.onClickVisualize=function(){
		 var response = $http.post("../../webapp/historicaldata/getHistoricalData", $scope.historicalData, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    //load graph here
			    var title=null;
			    var yAxisText=null;
			    if(data.type=='cpu'){
			    	title='CPU Average Usage - ';
			    	yAxisText='CPU Usage';
			    }else{
			    	title='Memory Average Usage - ';
			    	yAxisText='Memory Usage';
			    }
			    if(data.flow=='hourly'){
			    	title=title+'Hourwise';
			    }else{
			    	title=title+'Daywise';
			    }
			    var dataSeries=[];
			    var hObj={name:'max value', data:[]};
			    var rObj={name:data.yAxisSeriesData.name, data:[]};
			    for(var i=0; i<data.yAxisSeriesData.data.length; i++){
			    	hObj.data.push(100);
			    	rObj.data.push(data.yAxisSeriesData.data[i]);
			    }
			    dataSeries.push(hObj);
			    dataSeries.push(rObj);
			    
			    
			    configureLineGraph(title, data.xAxisData, yAxisText, '%', dataSeries);
			    $scope.historicalData.showGraph=true;
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 console.log(data);
			 console.log(status);
			 return $q.reject(response);
		});
	 }
	 
});