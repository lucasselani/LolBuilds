var app = angular.module("myApp", []);	

app.controller("Controller", function($scope, $http) {
	
	$scope.init = function() {
		$scope.searchItems();
	}
	
    $scope.searchItems = function() {
        $http({url: 'http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/item.json',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.items_data = Object.values(response.data.data);
        	    console.log($scope.items_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };   
    
});