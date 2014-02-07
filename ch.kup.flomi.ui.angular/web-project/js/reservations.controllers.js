var ReservationsCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/rest/reservations').success(function(reservations) {
		$scope.reservations = reservations;
	});		
}];	

