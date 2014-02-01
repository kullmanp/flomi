var AddressesCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/address').success(function(addresses) {
		$scope.addresses = addresses;
	});		
}];	

var EditAddressesCtrl = ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	if($routeParams.addressId != undefined) {
		$http.get('/address/' + $routeParams.addressId).success(function(address) {
			$scope.address = address;
		});	
	}

	$scope.save = function() {
		if ($scope.address.id == undefined) {
			$http.post('/address', $scope.address).success(function(newAddress) {
				$scope.address = newAddress;
				$location.path('/addresses/edit/' + newAddress.id);
			});	
		} else {
			$http.put('/address/' + $routeParams.addressId, $scope.address).success(function() {
				$location.path('/addresses/edit/' + $routeParams.id);
			});	
		}
	}

	$scope.delete = function() {
		$http.delete('/address/' + $routeParams.addressId).success(function() {
			$location.path('/addresses');
		});
	}
}];
