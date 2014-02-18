var ReservationsCtrl = [
		'$scope',
		'$http',
		'$q',
		'$location',
		function($scope, $http, $q, $location) {
			function getReservation(flomi, table) {
				for (r in $scope.reservations) { 
					var res = $scope.reservations[r];
					if (res.flomi.id === flomi.id && res.tisch.id === table.id)
						return res;
				}
				return null;
			}
			
			$scope.reservationText = function(flomi, table) {
				var res = getReservation(flomi, table);
				return res === null ? "" : res.address.lastName;
			};
			
			$scope.go = function(path) {
				$location.path(path);
			};
			
			$scope.accessReservation = function(flomi, table) {
				var res = getReservation(flomi, table);
				if (res === null) {
					$location.path("/reservations/add").search("flomi", flomi.id).search("table", table.id);
				} else {
					$location.path("/reservations/edit/" + res.id);
				}
			}
			
			if ($scope.year == undefined) {
				$scope.year = localStorage.year;
			}

			$http.get('/rest/reservations/years').success(function(years) {
				$scope.years = years
			});

			$scope.$watch('year', function(newVal, oldVal) {
				if (newVal != undefined) {
					localStorage.year = newVal;

					var tables = $http.get('/rest/tables'),
						flomis = $http.get('/rest/flomis/byyear?year='+$scope.year),
						reservations = $http.get('/rest/reservations/years/' + $scope.year);
					$q.all([tables, flomis, reservations]).then(function(results) {
						$scope.tables = results[0].data;
						$scope.flomis = results[1].data;
						$scope.reservations = results[2].data;
					});
				}
			});
			

		} ];

var EditReservationsCtrl = ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {

	$scope.findAddresses = function(searchtext) {
		return $http.get('/rest/address/search', { params: {searchtext: searchtext}}).then(function(res) {
			return res.data.slice(0,9);
		});
	};
	
	$scope.titles = [
	                 {title: 'Amazing Grace', type: 'movie'},
	                 {title: 'Amazing Grace', type: 'song'}
	               ];	
	
	if($routeParams.reservationId != undefined) {
		$http.get('/rest/reservations/' + $routeParams.reservationId).success(function(reservation) {
			$scope.reservation = reservation;
			$scope.tisch = reservation.tisch;
			$scope.flomi = reservation.flomi;
		});	
	} else {
		$http.get('/rest/tables/' + $routeParams['table']).success(function(table){
			$scope.tisch = table;
		});
		$http.get('/rest/flomis/' + $routeParams['flomi']).success(function(flomi){
			$scope.flomi = flomi;
		});
		
		var dummyRes = {};
		dummyRes.anmeldeDatum = new Date();
		$scope.reservation = dummyRes;
	}

}];

