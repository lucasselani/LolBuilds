var app = angular.module("myApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'angular-loading-bar']);	

app.run(['$http', function($http) {
    $http.defaults.headers.common['Authorization'] = "ac308inatel2017lolbuilds";
}]);

app.controller("Controller", function($scope, $http) {
	
	$scope.init = function() {
		$scope.searchBuilds();
		$scope.searchUsers();
	}
	
    $scope.searchBuilds = function() {
        $http({url: url_api+'/build/allbuilds',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.build_data = response.data;
        	    //console.log($scope.build_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    }; 
    
    $scope.searchUsers = function() {
        $http({url: url_api+'/user/allusers',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.user_data = response.data;
        	    //console.log($scope.user_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };       
    
});