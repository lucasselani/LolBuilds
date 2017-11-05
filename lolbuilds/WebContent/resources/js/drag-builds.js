var app = angular.module("myApp", []);	

app.controller("Controller", function($scope, $http, $timeout, $filter) {
	
	$scope.init = function() {
		$scope.searchItems();
	}
	
    $scope.searchItems = function() {
        $http({url: 'http://ddragon.leagueoflegends.com/cdn/6.24.1/data/en_US/item.json',
	           method: 'GET',
		       headers: {'Content-Type': 'application/json; charset=utf-8'}
        	}).then(function mySuccess(response) {
        		$scope.items_data = Object.values(response.data.data);
        	}, function myError(response) {
	   			console.log(response);
   		});			    
    };

    $scope.handleDrop = function(item, bin) {
        console.log('Item ' + item + ' has been dropped into ' + bin);
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
				var item = document.getElementById(e.dataTransfer
						.getData('Text'));
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



(function () {
	  'use strict';
	  angular
	      .module('MyApp',['ngMaterial', 'ngMessages', 'material.svgAssetsCache'])
	      .controller('DemoCtrl', DemoCtrl);

	  function DemoCtrl ($timeout, $q, $log) {
	    var self = this;

	    self.simulateQuery = false;
	    self.isDisabled    = false;

	    self.repos         = loadAll();
	    self.querySearch   = querySearch;
	    self.selectedItemChange = selectedItemChange;
	    self.searchTextChange   = searchTextChange;

	    // ******************************
	    // Internal methods
	    // ******************************

	    /**
	     * Search for repos... use $timeout to simulate
	     * remote dataservice call.
	     */
	    function querySearch (query) {
	      var results = query ? self.repos.filter( createFilterFor(query) ) : self.repos,
	          deferred;
	      if (self.simulateQuery) {
	        deferred = $q.defer();
	        $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
	        return deferred.promise;
	      } else {
	        return results;
	      }
	    }

	    function searchTextChange(text) {
	      $log.info('Text changed to ' + text);
	    }

	    function selectedItemChange(item) {
	      $log.info('Item changed to ' + JSON.stringify(item));
	    }

	    /**
	     * Build `components` list of key/value pairs
	     */
	    function loadAll() {
	      var repos = [
	        {
	          'name'      : 'AngularJS',
	          'url'       : 'https://github.com/angular/angular.js',
	          'watchers'  : '3,623',
	          'forks'     : '16,175',
	        },
	        {
	          'name'      : 'Angular',
	          'url'       : 'https://github.com/angular/angular',
	          'watchers'  : '469',
	          'forks'     : '760',
	        },
	        {
	          'name'      : 'AngularJS Material',
	          'url'       : 'https://github.com/angular/material',
	          'watchers'  : '727',
	          'forks'     : '1,241',
	        },
	        {
	          'name'      : 'Angular Material',
	          'url'       : 'https://github.com/angular/material2',
	          'watchers'  : '727',
	          'forks'     : '1,241',
	        },
	        {
	          'name'      : 'Bower Material',
	          'url'       : 'https://github.com/angular/bower-material',
	          'watchers'  : '42',
	          'forks'     : '84',
	        },
	        {
	          'name'      : 'Material Start',
	          'url'       : 'https://github.com/angular/material-start',
	          'watchers'  : '81',
	          'forks'     : '303',
	        }
	      ];
	      return repos.map( function (repo) {
	        repo.value = repo.name.toLowerCase();
	        return repo;
	      });
	    }

	    /**
	     * Create filter function for a query string
	     */
	    function createFilterFor(query) {
	      var lowercaseQuery = angular.lowercase(query);

	      return function filterFn(item) {
	        return (item.value.indexOf(lowercaseQuery) === 0);
	      };

	    }
	  }
	})();


	/**
	Copyright 2016 Google Inc. All Rights Reserved. 
	Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
	**/