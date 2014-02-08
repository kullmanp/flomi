var ReservationsCtrl = [
		'$scope',
		'$http',
		'$q',
		'$location',
		function($scope, $http, $q, $location) {
			$scope.reservationText = function(flomi, table) {
				for (r in $scope.reservations) { 
					var res = $scope.reservations[r];
					if (res.flomi.id === flomi.id && res.tisch.id === table.id)
						return res.address.lastName;
				}
				return "";
			};
			
			$scope.go = function(path) {
				$location.path(path);
			};
			
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
