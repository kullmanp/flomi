angular.module('flomi', ['components']).
  config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/flomis', {templateUrl: 'partials/flomis.html', controller: FlomisCtrl}).
      when('/flomis/add', {templateUrl: 'partials/editflomi.html', controller: EditFlomisCtrl}).
      when('/flomis/edit/:flomiId', {templateUrl: 'partials/editflomi.html', controller: EditFlomisCtrl}).

      when('/addresses', {templateUrl: 'partials/addresses.html', controller: AddressesCtrl}).
      when('/addresses/add', {templateUrl: 'partials/editaddress.html', controller: EditAddressesCtrl}).
      when('/addresses/edit/:addressId', {templateUrl: 'partials/editaddress.html', controller: EditAddressesCtrl}).
      
      otherwise({redirectTo: '/flomis'});
}]);

