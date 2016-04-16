var analyticapp = angular.module('analyticapp', [ 'ui.router', 'ngResource', 'ui.bootstrap', 'ngCookies', 'analyticappServices']);

//factory for sharing data between controller
analyticapp.factory('dataSharing', function() {
	 var sharedData = {}
	 function set(data) {
		 sharedData = data;
	 }
	 function get() {
		 return sharedData;
	 }

	 return {
	  set: set,
	  get: get
	 }

});

analyticapp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/login');
    
    $stateProvider
        
        .state('login', {
            url: '/login',
            templateUrl: 'pages/login/login.html',
            controller:'loginController'
        })
        
        .state('dashboard', {
        	url: '/dashboard',
            templateUrl: 'pages/dashboard/dashboard.html',
            controller:'dashboardController',
            params: {
                user: null
            }
        })
    
        .state('dashboard.analytics', {
        	url: '/analytics',
            templateUrl: 'pages/dashboard/analytics/analytics.html',
            controller:'analyticsController',
            params: {
                user: null
            }
        })
        
        .state('dashboard.realtimeanalytics', {
	    	url: '/real_time_analytics',
	        templateUrl: 'pages/dashboard/real_time_analytics/real_time_analytics.html',
	        controller:'realTimeAnalyticsController'
	    })
    
	    .state('dashboard.user_information', {
	    	url: '/profile',
	        templateUrl: 'pages/dashboard/user/user_information.html',
	        controller:'userInformationController'
	    })
	    
	    .state('dashboard.message_home', {
	    	url: '/messages',
	        templateUrl: 'pages/dashboard/message/message_home.html',
	        controller:'messageController'
	    });
        
});


