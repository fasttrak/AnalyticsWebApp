angular.module('analyticApp').controller('AnalyticsController',
	 				function($scope, $http, $location, $q, $timeout, $rootScope, $cookies, $stateParams, successAndErrorMessageHandlingService,$filter) {
	console.log('AnalyticsController start');

	initController();
	function initController(){
		$scope.analytics={};
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
	
	 function getNextTimeSlots(noOfSlots, noToAdd, parameter){
		 var nextTimeSlots=[];
		 var currentDate=new Date();
		 if(parameter=='day'){
			 for(var i=1; i<=noOfSlots; i++){
				 currentDate.setDate(currentDate.getDate() + noToAdd); 
				 nextTimeSlots.push($filter('date')(currentDate, "MM/dd/yyyy 'at' h:mma"));
			 }
		 }else{
			 //hour
			 for(var i=1; i<=noOfSlots; i++){
				 currentDate.setHours(currentDate.getHours() + noToAdd); 
				 nextTimeSlots.push($filter('date')(currentDate, "MM/dd/yyyy 'at' h:mma"));
			 }
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
			$scope.analyticsTypesOptions=[{value:'cpu_utilization', displayName:'CPU Utilization'},
			                              {value:'memory_utilization', displayName:'Memory Utilization'}];
			$scope.analytics.selectedAnalyticsType='cpu_utilization';
			$scope.hostsOptions=[];
			$scope.hostsOptions.push({value: 'all', displayName: 'All Hosts'});
			$scope.analytics.selectedHost='all';
			for(var i=0; i<$scope.analytics.length; i++){
				 var analytic=$scope.analytics[i];
				 $scope.hostsOptions.push({value: analytic.ip, displayName: analytic.ip});
			}
			$scope.timeframeOptions=[{value:'one_hour', displayName:'One Hour'}
				                     	,{value:'one_day', displayName:'One Day'}
				                     	/*,{value:'one_month', displayName:'One Month'}*/];
			$scope.analytics.selectedTimeFrame='one_hour';	
			var nextTimeSlots=getNextTimeSlots(10, 1, 'hour');
			var dataSeriesList=[];
			for(var i=0; i<$scope.hostsOptions.length; i++){
				if($scope.hostsOptions[i].value=='all'){
					continue;
				}
				var dataSeriesObj=getDataSeries(10, $scope.hostsOptions[i].value, 'oneHourCPUAnalyticsIntercept', 'oneHourCPUAnalyticsSlope', 
						$scope.hostsOptions[i].value,$scope.analytics);
				dataSeriesList.push(dataSeriesObj);
			}
			configureLineGraph('CPU Analytics', nextTimeSlots, 'CPU Utilization', '(%)', dataSeriesList);
		 }
	 
	 //graphTitle=String, nextTimeSlots([String], yAxisText=String, yAxisSuffix=String(%), dataSeries=[{name:String, data:[float]}])
	 function configureLineGraph(graphTitle, nextTimeSlots, yAxisText, yAxisSuffix, dataSeries){
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
	
	 $scope.onClickVisualize=function(){
		 var interceptString=null;
		 var slopeString=null;
		 var noToAdd=1;
		 var parameter='hour';
		 if($scope.analytics.selectedAnalyticsType=='cpu_utilization'){
			 if($scope.analytics.selectedTimeFrame=='one_hour'){
				 noToAdd=1;
				 parameter='hour';
				 interceptString='oneHourCPUAnalyticsIntercept';
				 slopeString='oneHourCPUAnalyticsSlope';
			 }else if($scope.analytics.selectedTimeFrame=='one_day'){
				 noToAdd=1;
				 parameter='day';
				 interceptString='oneDayCPUAnalyticsIntercept';
				 slopeString='oneDayCPUAnalyticsSlope';
			 }else if($scope.analytics.selectedTimeFrame=='one_month'){
				 noToAdd=30;
				 parameter='day';
				 interceptString='oneMonthCPUAnalyticsIntercept';
				 slopeString='oneMonthCPUAnalyticsSlope';
			 }
		 }else if($scope.analytics.selectedAnalyticsType=='memory_utilization'){
			 if($scope.analytics.selectedTimeFrame=='one_hour'){
				 noToAdd=1;
				 parameter='hour';
				 interceptString='oneHourMemoryAnalyticsIntercept';
				 slopeString='oneHourMemoryAnalyticsSlope';
			 }else if($scope.analytics.selectedTimeFrame=='one_day'){
				 noToAdd=1;
				 parameter='day';
				 interceptString='oneDayMemoryAnalyticsIntercept';
				 slopeString='oneDayMemoryAnalyticsSlope';
			 }else if($scope.analytics.selectedTimeFrame=='one_month'){
				 noToAdd=30;
				 parameter='day';
				 interceptString='oneMonthMemoryAnalyticsIntercept';
				 slopeString='oneMonthMemoryAnalyticsSlope';
			 }
		 }
		 var dataSeriesList=[];
		 if($scope.analytics.selectedHost=='all'){
				for(var i=0; i<$scope.hostsOptions.length; i++){
					if($scope.hostsOptions[i].value=='all'){
						continue;
					}
					var dataSeriesObj=getDataSeries(10, $scope.hostsOptions[i].value, interceptString, slopeString, 
							$scope.hostsOptions[i].value,$scope.analytics);
					dataSeriesList.push(dataSeriesObj);
				}
		 }else{
			 for(var i=0; i<$scope.hostsOptions.length; i++){
					if($scope.hostsOptions[i].value==$scope.analytics.selectedHost){
						var dataSeriesObj=getDataSeries(10, $scope.hostsOptions[i].value, interceptString, slopeString, 
								$scope.hostsOptions[i].value,$scope.analytics);
						dataSeriesList.push(dataSeriesObj);
					}
			}
		 }
		 var nextTimeSlots=getNextTimeSlots(10, noToAdd, parameter);
		 configureLineGraph('CPU Analytics', nextTimeSlots, 'CPU Utilization', '(%)', dataSeriesList);
	 } 
	 
	
});