angular.module('analyticApp').controller('AnalyticsController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService,$filter) {
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
		var response = $http.get("../../webapp/analytics/getAllAnalytics", null, {});
		 response.success(function(data, status, headers, config) {
			    console.log(data);
			    $scope.analytics=data;
			    initializeAnalytics();
			    return data;
		 });
		 response.error(function(data, status, headers, config) {
			 console.log(data);
			 console.log(status);
			 return $q.reject(response);
		});
	}
	
	 function getNextTimeSlots(noOfSlots, daysToAdd){
		 var nextTimeSlots=[];
		 var currentDate=new Date();
		 for(var i=1; i<=noOfSlots; i++){
			 currentDate.setDate(currentDate.getDate() + daysToAdd); 
			 nextTimeSlots.push($filter('date')(currentDate));
		 }
		 return nextTimeSlots;
	 }
	
	 function getDataSeries(noOfSlots, name, interceptString, slopeString, hostString, analyticsDataToSeach){
		 var seriesData=null;
		 for(var i=0; i<analyticsDataToSeach.length; i++){
			 if(analyticsDataToSeach[i].ip==hostString){
				 var c=analyticsDataToSeach[i][interceptString];
				 var m=analyticsDataToSeach[i][slopeString];
				 var dataList=[];
				 for(var x=1; x<=noOfSlots; x++){
					 var yResult=(m*x)+c;
					 dataList.push(yResult);
				 }
				 seriesData={name:name, data:dataList};
				 return seriesData;
			 }
		 }
		 return seriesData;
	 }
	 
	 function initializeAnalytics(){
			$scope.analyticsTypesOptions=[{value:'cpu_utilization', displayName:'CPU Utilization', selected:true},
			                              {value:'memory_utilization', displayName:'Memory Utilization'}];
			$scope.selectedAnalyticsType='cpu_utilization';
			$scope.hostsOptions=[];
			$scope.hostsOptions.push({value: 'all', displayName: 'All Hosts'});
			$scope.selectedHost='all';
			for(var i=0; i<$scope.analytics.length; i++){
				 var analytic=$scope.analytics[i];
				 $scope.hostsOptions.push({value: analytic.ip, displayName: analytic.ip});
			}
			$scope.timeframeOptions=[{value:'one_hour', displayName:'One Hour'},
				                     	{value:'one_day', displayName:'One Day'},
				                     	{value:'one_month', displayName:'One Month'}];
			$scope.selectedTimeFrame='one_hour';	
			var nextTimeSlots=getNextTimeSlots(10, 1);
			var dataSeriesList=[];
			for(var i=0; i<$scope.hostsOptions.length; i++){
				if($scope.hostsOptions[i].value=='all'){
					continue;
				}
				var dataSeriesObj=getDataSeries(10, $scope.hostsOptions[i].value, 'oneHourCPUAnalyticsIntercept', 'oneHourCPUAnalyticsSlope', 
						$scope.hostsOptions[i].value,$scope.analytics);
				dataSeriesList.push(dataSeriesObj);
			}
			configureGraph('CPU Analytics', nextTimeSlots, 'CPU Utilization', '%', dataSeriesList);
		 }
	 
	 //graphTitle=String, nextTimeSlots([String], yAxisText=String, yAxisSuffix=String(%), dataSeries=[{name:String, data:[float]}])
	 function configureGraph(graphTitle, nextTimeSlots, yAxisText, yAxisSuffix, dataSeries){
		 $scope.chartConfig = {
			        title: {
			            text: graphTitle,
			            x: -20 //center
			        },
			        subtitle: {
			            text: '',
			            x: -20
			        },
			        xAxis: {
			            categories: nextTimeSlots
			        },
			        yAxis: {
			            title: {
			                text: yAxisText
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: yAxisSuffix
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: dataSeries
		 };
	 }
	
});