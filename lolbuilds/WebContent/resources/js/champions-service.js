var app = angular.module("myApp", ['angular-loading-bar']);

app.controller("Controller", function($scope, $http) {
    $scope.searchChampions = function() {
        $http({url: 'http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/champion.json',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		var arr = Object.values(response.data.data);
    			$scope.champions_data = [];
    			for (var i = 0; i < arr.length; i++) {
    				//bug around data riot for fiddle
					if ( arr[i].name == "Fiddlesticks" ) {
						$scope.champions_data.push({image: "Fiddlesticks.png", name: "Fiddlesticks"});
					} else {
						$scope.champions_data.push({image: arr[i].image.full, name: arr[i].name});
					}
				} //console.log($scope.champions_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };
});