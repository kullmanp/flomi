'use strict';

angular.module('webProjectApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/adressen', {
        templateUrl: 'views/adressen.html',
        controller: 'AdressenCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
