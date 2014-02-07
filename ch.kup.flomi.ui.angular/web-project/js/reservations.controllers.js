var ReservationsCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/reservations').success(function(reservations) {
		$scope.reservations = reservations;
	});		
}];	

