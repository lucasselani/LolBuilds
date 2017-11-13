var app = angular.module("myApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'angular-loading-bar']);	

app.run(['$http', function($http) {
    $http.defaults.headers.common['Authorization'] = "ac308inatel2017lolbuilds";
}]);

app.config(function($locationProvider) {
	$locationProvider.html5Mode({
		enabled: true,
		requireBase: false
	});
});

app.controller("Controller", function($scope, $http, $location) {
	
	$scope.init = function() {
	    $scope.query_filter = $location.search()['t'];
	    $scope.query_data = $location.search()['d'];
	    
	    if ($scope.query_filter == 'minhas-builds') {
	    	$scope.own = true;
	    	$scope.searchBuilds(null, "ownbuild");
	    } else {
	    	$scope.own = false;
		    if ($scope.query_filter == 'rota') {
		    	$scope.searchBuilds($scope.query_data, "type");
		    } else if ($scope.query_filter == 'campeao') {
		    	$scope.searchBuilds($scope.query_data, "champion");
		    }
	    	
	    }
	}
	
    $scope.searchBuilds = function(data, url) {
        $http({url: url_api+'/build/'+url,
	           method: 'GET',
	           params: (data != null) ? { name: data } : { },
		       headers: {'Content-Type': 'application/json; charset=utf-8', 'Authorization':'ac308inatel2017lolbuilds'}
        	}).then(function mySuccess(response) {
        		$scope.build_data = response.data;
        	    //console.log($scope.build_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };     
    
});