'use strict';
angular.module('analyticApp')
	.directive('header',function(){
		return {
        templateUrl:'js/directives/header/header.html',
        restrict: 'E',
        replace: true,
    	}
	});


