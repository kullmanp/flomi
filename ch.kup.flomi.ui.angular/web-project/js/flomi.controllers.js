var FlomisCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/rest/flomis').success(function(flomis) {
		$scope.flomis = flomis;
	});		
}];	

var EditFlomisCtrl = ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	if($routeParams.flomiId != undefined) {
		$http.get('/rest/flomis/' + $routeParams.flomiId).success(function(flomi) {
			$scope.flomi = flomi;
		});	
	}

	$scope.save = function() {
		if ($scope.flomi.id == undefined) {
			$http.post('/rest/flomis', $scope.flomi).success(function(newFlomi) {
				$scope.flomi = newFlomi;
				$location.path('/flomis');
			});	
		} else {
			$http.put('/rest/flomis/' + $routeParams.flomiId, $scope.flomi).success(function() {
				$location.path('/flomis');
			});	
		}
	}

	$scope.delete = function() {
		$http.delete('/rest/flomis/' + $routeParams.flomiId).success(function() {
			$location.path('/flomis');
		});
	}
}];
