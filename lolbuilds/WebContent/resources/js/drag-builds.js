var app = angular.module("myApp", ['ngAnimate', 'ngSanitize', 'ui.bootstrap']);	

app.controller("Controller", function($scope, $http, $timeout, $filter) {
	
	$scope.init = function() {
		$scope.type_data = [{name:'ATIRADOR'}, {name:'MEIO'}, {name:'SELVA'}, {name:'SUPORTE'}, {name:'TOPO'}];
		$scope.searchItems();
		$scope.searchSpells();
		$scope.searchChampions();
	}
	
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
	
    $scope.searchItems = function() {
        $http({url: 'http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/item.json',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.items_data = Object.values(response.data.data);
        		//console.log($scope.items_data);
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
        		//console.log($scope.spells_data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };
    
    $scope.getChildrenNodes = function() {
		$scope.user_spells = [];
		$scope.user_items = [];
	    var item;
	    
	    for (var i = 1; i <= 7; i++) {
	    	item = document.getElementById("itm_rcp_"+i).childNodes;
	    	(item.length > 0) ? $scope.user_items.push({name: item[0].alt, image: item[0].id.split("_")[1]}) : item = null;
	    } //console.log($scope.user_items);
	    
	    for (var i = 1; i <= 2; i++) {
	    	item = document.getElementById("spl_rcp_"+i).childNodes;
	    	(item.length > 0) ? $scope.user_spells.push({name: item[0].alt, image: item[0].id.split("_")[1]}) : item = null;
	    } //console.log($scope.user_spells);
    }
    
    $scope.enviarBuild = function() {
    	$scope.getChildrenNodes();
    	
    	if ( $scope.user_items.length < 7 || $scope.user_spells.length < 2 || typeof $scope.customSelected === 'undefined' ||
    		 typeof $scope.customSelected.name === 'undefined' || !$scope.nameBuild || typeof $scope.typeBuild === 'undefined' || 
    		 typeof $scope.typeBuild.name === 'undefined' ) {
            $scope.error = "Preencha todos os campos e todos os slots da build!";
            $scope.success = null;
            return;
    	}
    	
    	var newBuildObject = {
			champion: $scope.customSelected,
			items: $scope.user_items,
			type: $scope.typeBuild.name,
			name: $scope.nameBuild,
			spells: $scope.user_spells
    	}
    	
    	console.log(newBuildObject);
    }
    
    $scope.limpaErrNot = function() {
        setTimeout(function() {
            $scope.$apply(function() {
        		$scope.error = null;
            });
        }, 100);
    };

    $scope.limpaSuccNot = function() {
        setTimeout(function() {
            $scope.$apply(function() {
        		$scope.success = null;
            });
        }, 100);
    };    
    
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
			    var childs = document.getElementById(binId).childElementCount;
				var item = document.getElementById(e.dataTransfer.getData('Text'));
				
		    	if( item.id.substring(0, 3) == 'spl' ) {
		    		if ( binId.substring(0, 3) == 'itm' ) {
		    			return;
		    		} else if ( childs > 0 ) {
		    			return;
			    	}
		    	} else if ( item.id.substring(0, 3) == 'itm' ) {
		    		if ( binId.substring(0, 3) == 'spl' ) {
		    			return;
		    		} else if ( childs > 0 ) {
		    			return;
			    	}
		    	}
		    	
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