var FlomisCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/rest/flomi').success(function(flomis) {
		$scope.flomis = flomis;
	});		
}];	

var EditFlomisCtrl = ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	if($routeParams.flomiId != undefined) {
		$http.get('/rest/flomi/' + $routeParams.flomiId).success(function(flomi) {
			$scope.flomi = flomi;
		});	
	}

	$scope.save = function() {
		if ($scope.flomi.id == undefined) {
			$http.post('/rest/flomi', $scope.flomi).success(function(newFlomi) {
				$scope.flomi = newFlomi;
				$location.path('/flomis');
			});	
		} else {
			$http.put('/rest/flomi/' + $routeParams.flomiId, $scope.flomi).success(function() {
				$location.path('/flomis');
			});	
		}
	}

	$scope.delete = function() {
		$http.delete('/rest/flomi/' + $routeParams.flomiId).success(function() {
			$location.path('/flomis');
		});
	}
}];
