var ReservationsCtrl = ['$scope', '$http', function($scope, $http) {
	if($scope.year == undefined) {
		$scope.year = localStorage.year;
	}
	
	$http.get('/rest/reservations/years').success(function(years) {
		$scope.years = years
	});
	
	$scope.$watch('year', function(newVal, oldVal) {
		if(newVal != undefined) {
			localStorage.year = newVal;
			
			$http.get('/rest/reservations/years/' + $scope.year).success(function(reservations) {
				$scope.reservations = reservations;
			});		
		}
	});

}];	

