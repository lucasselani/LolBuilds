var app = angular.module("myApp", []);	

app.controller("Controller", function($scope, $http, $timeout, $filter) {
	
	$scope.init = function() {
		$scope.searchItems();
		$scope.searchSpells();
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
    
    $scope.searchSpells = function() {
        $http({url: 'http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/summoner.json',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.spells_data = Object.values(response.data.data);
        		console.log($scope.spells_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };    

    $scope.handleDrop = function(item, bin) {
    	if( item.substring(0, 3) == 'spl' ) {
    		if ( bin.substring(0, 3) == 'spl' ) {
                console.log('Item ' + item + ' has been dropped into ' + bin);
    		}
    	} else if ( item.substring(0, 3) == 'itm' ) {
    		
    	}
    }
    
});

app.directive('draggable', function() {
	return function(scope, element) {
		// this gives us the native JS object
		var el = element[0];

		el.draggable = true;

		el.addEventListener('dragstart', function(e) {
			e.dataTransfer.effectAllowed = 'move';
			e.dataTransfer.setData('Text', this.id);
			this.classList.add('drag');
			return false;
		}, false);

		el.addEventListener('dragend', function(e) {
			this.classList.remove('drag');
			return false;
		}, false);
	}
});

app.directive('droppable', function() {
	return {
		scope : {
			drop : '&',
			bin : '='
		},
		link : function(scope, element) {
			// again we need the native object
			var el = element[0];

			el.addEventListener('dragover', function(e) {
				e.dataTransfer.dropEffect = 'move';
				// allows us to drop
				if (e.preventDefault)
					e.preventDefault();
				this.classList.add('over');
				return false;
			}, false);

			el.addEventListener('dragenter', function(e) {
				this.classList.add('over');
				return false;
			}, false);

			el.addEventListener('dragleave', function(e) {
				this.classList.remove('over');
				return false;
			}, false);

			el.addEventListener('drop', function(e) {
				// Stops some browsers from redirecting.
				if (e.stopPropagation)
					e.stopPropagation();

				this.classList.remove('over');

				var binId = this.id;
				var item = document.getElementById(e.dataTransfer.getData('Text'));
				this.appendChild(item);
				// call the passed drop function
				scope.$apply(function(scope) {
					var fn = scope.drop();
					if ('undefined' !== typeof fn) {
						fn(item.id, binId);
					}
				});
				return false;
			}, false);
		}
	}
});