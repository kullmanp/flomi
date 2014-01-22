angular.module('flomi', ['components']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/flomis', {templateUrl: 'partials/flomis.html', controller: FlomisCtrl}).
      when('/flomis/add', {templateUrl: 'partials/editproduct.html', controller: EditProductsCtrl}).
      when('/flomis/edit/:flomiId', {templateUrl: 'partials/editproduct.html', controller: EditProductsCtrl}).
      otherwise({redirectTo: '/products'});
}]);


