var app = angular.module("myApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap', 'angular-loading-bar']);	

app.run(['$http', function($http) {
    $http.defaults.headers.common['Authorization'] = "ac308inatel2017lolbuilds";
}]);

app.controller("Controller", function($scope, $http) {
	
	$scope.init = function() {
		moment.locale('pt-BR');
		$scope.searchBuilds();
	}
	
	$scope.enviarBuild = function() {
		if(!$scope.searchName) return;
		window.location.replace("builds.xhtml?t=usuario&d="+$scope.searchName);
	}
	
    $scope.searchBuilds = function() {
        $http({url: url_api+'/build/allbuilds',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.build_data = response.data;
        		for (var i = 0; i < $scope.build_data.length; i++) {
        			$scope.build_data[i]["momentNow"] = moment($scope.build_data[i].datetime).fromNow();
				} //console.log($scope.build_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    }; 
    
});