'use strict';

angular
  .module('analyticApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'dialogs.main','dialogs.default-translations',
    'analyticappServices'
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
      
      .state('dashboard.form',{
        templateUrl:'views/form.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'pages/login/login.html',
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
      .state('dashboard.chart',{
        templateUrl:'views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'bower_components/angular-chart.js/dist/angular-chart.min.js',
                'bower_components/angular-chart.js/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'analyticApp',
                files:['scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.table',{
        templateUrl:'views/table.html',
        url:'/table'
    })
      .state('dashboard.panels-wells',{
          templateUrl:'views/ui-elements/panels-wells.html',
          url:'/panels-wells'
      })
      .state('dashboard.buttons',{
        templateUrl:'views/ui-elements/buttons.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'views/ui-elements/notifications.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'views/ui-elements/typography.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

    
