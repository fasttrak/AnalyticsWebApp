'use strict';

angular
  .module('analyticApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'dialogs.main','dialogs.default-translations',
    'analyticappServices',
    'highcharts-ng'
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
    
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'analyticApp',
                    files:[
                    'js/directives/header/header.js',
                    'js/directives/sidebar/sidebar.js',
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "bower_components/angular-toggle-switch/angular-toggle-switch-bootstrap.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['bower_components/angular-1.4.4/angular-animate.js']
                }),
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['bower_components/angular-1.4.4/angular-cookies.js']
                }),
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['bower_components/angular-1.4.4/angular-resource.js']
                }),
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['bower_components/angular-1.4.4/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'DashboardController',
        templateUrl:'pages/dashboard/dashboard.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'analyticApp',
              files:[
              'pages/dashboard/js/dashboard_controller.js',
              ]
            })
          }
        }
      })
      
      .state('dashboard.realtime',{
        url:'/realtime',
        controller: 'RealTimeController',
        templateUrl:'pages/dashboard/real_time_analytics/real_time_analytics.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'analyticApp',
              files:[
              'pages/dashboard/real_time_analytics/js/real_time_analytics_controller.js',
              ]
            })
          }
        }
      })
      
      .state('dashboard.analytics',{
        url:'/analytics',
        controller: 'AnalyticsController',
        templateUrl:'pages/dashboard/analytics/analytics.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
                name:'analyticApp',
                files:[
                'pages/dashboard/analytics/js/analytics_controller.js',
                ]
              })
          }
        }
      })
      
      
      .state('dashboard.profile',{
        url:'/profile',
        controller: 'UserProfileController',
        templateUrl:'pages/dashboard/user/profile.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'analyticApp',
              files:[
              'pages/dashboard/user/js/profile_controller.js',
              ]
            })
          }
        }
      })
      
      .state('dashboard.settings',{
        url:'/settings',
        controller: 'UserSettingsController',
        templateUrl:'pages/dashboard/user/settings.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'analyticApp',
              files:[
              'pages/dashboard/user/js/settings_controller.js',
              ]
            })
          }
        }
      })
      
      .state('dashboard.access_management',{
        url:'/access_control',
        controller: 'AccessManagementController',
        templateUrl:'pages/dashboard/user_management/access_management.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return  $ocLazyLoad.load({
                name:'ngTable',
                files:[
                  'bower_components/ngtable/ng-table.js',
                  'bower_components/ngtable/ng-table.css'
                ]
              }),$ocLazyLoad.load({
              name:'analyticApp',
              files:[
                'pages/dashboard/user_management/js/access_management_controller.js',
                'pages/dashboard/user_management/js/user_controller.js',
                'pages/dashboard/user_management/js/group_controller.js'
              ]
            })
          }
        }
      })
      
      .state('dashboard.alert',{
        url:'/alert',
        controller: 'AlertController',
        templateUrl:'pages/dashboard/alert/alert.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
                name:'ngTable',
                files:[
                  'bower_components/ngtable/ng-table.js',
                  'bower_components/ngtable/ng-table.css'
                ]
              }),$ocLazyLoad.load({
              name:'analyticApp',
              files:[
                'pages/dashboard/alert/js/alert_controller.js',
                'pages/dashboard/alert/js/alert_configure_controller.js'
              ]
            })
          }
        }
      })
      
      .state('dashboard.historicaldata',{
        url:'/historical',
        controller: 'HistoricalDataController',
        templateUrl:'pages/dashboard/historical_data/historical_data.html',
        params: {
            user: null
        },
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
                name:'ngTable',
                files:[
                  'bower_components/ngtable/ng-table.js',
                  'bower_components/ngtable/ng-table.css'
                ]
              }),$ocLazyLoad.load({
              name:'analyticApp',
              files:[
                'pages/dashboard/historical_data/js/historical_data_controller.js',
              ]
            })
          }
        }
      })
      
      .state('login',{
        templateUrl:'pages/login/login2.html',
        url:'/login',
        controller:'LoginController',
        resolve: {
        	loadMyFiles:function($ocLazyLoad) {
                return $ocLazyLoad.load({
                  name:'analyticApp',
                  files:[
                  'pages/login/js/loginController.js',
                  ]
                })
              }
	      }
    })
  }]);


    
