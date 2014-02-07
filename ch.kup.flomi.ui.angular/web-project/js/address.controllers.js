var AddressesCtrl = ['$scope', '$http', function($scope, $http) {
	$http.get('/rest/address').success(function(addresses) {
		$scope.addresses = addresses;
	});		
}];	

var EditAddressesCtrl = ['$scope', '$http', '$routeParams', '$location', function($scope, $http, $routeParams, $location) {
	
	if($routeParams.addressId != undefined) {
		$http.get('/rest/address/' + $routeParams.addressId).success(function(address) {
			$scope.address = address;
		});	
	}

	$scope.save = function() {
		if ($scope.address.id == undefined) {
			$http.post('/rest/address', $scope.address).success(function(newAddress) {
				$scope.address = newAddress;
				$location.path('/addresses/edit/' + newAddress.id);
			});	
		} else {
			$http.put('/rest/address/' + $routeParams.addressId, $scope.address).success(function() {
				$location.path('/addresses');
			});	
		}
	}

	$scope.delete = function() {
		$http.delete('/rest/address/' + $routeParams.addressId).success(function() {
			$location.path('/addresses');
		});
	}
}];
