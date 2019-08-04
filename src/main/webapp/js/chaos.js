/**
 * 
 */

var chaosapp = angular.module('chaosapp', ['ngRoute']);


chaosapp.config(function($routeProvider) {
	$routeProvider
	.when('/home', {
		templateUrl : 'home.html'
	})
	.when('/clients', {
		templateUrl : 'clients.html'
	})
	.when('/items',{
		templateUrl : 'items.html'
	})
	.when('/itemssold', {
		templateUrl : 'itemssold.html'
	})
	.when('/search', {
		templateUrl : 'search.html'
	})
	.when('/resume', {
		templateUrl : 'myResume.html'
	})
	.when('/stack', {
		templateUrl : 'stack.html'
	})	
	.when('/addclient', {
		templateUrl : 'addclient.html',
		controller : 'chaosCreateController'
	})
	.when('/additem', {
		templateUrl : 'additem.html',
		controller : 'chaosCreateController'
	})
	.otherwise({
		redirectTo: '/home'
	});
});

chaosapp.controller('chaoscontroller', function($scope, $http) {
	$scope.appName = 'Chaos Consignment by Tera Horsey';
	$scope.navName = 'Chaos Consignment';
	$scope.showSearch = true;
	$scope.showEditDelete = false;
	
	
	$scope.updateClient = function(clientToUpdate) {
		console.log('Selected client to update: ' + angular.toJson(clientToUpdate));
		$scope.clientToUpdate = angular.copy(clientToUpdate);
		$scope.showEditDelete = true;
		$scope.showSearch = false;
		$scope.updateStatus = '';
	}
	
	$scope.getClients = function() {
		console.log('getClients');
		$scope.clients =[{"ClientId": "retrieving client list..."}]
		
		$scope.showSearch = true;
		$scope.showEditDelete = false;
		
		$http.get("/acachaos/webapi/chaosconsignment/getclients")
		.then(function(response) {
			$scope.clients = response.data;
			console.log("Number of clients: " + $scope.clients.length);
		}, function(response) {
			console.log("Error HTTP GET clients" + response.status);
		});
	}
	
	$scope.returnToClients = function() {
		$scope.showEditDelete = false;
		$scope.showSearch = true;
		$scope.getClients();
	}
	
	$scope.putClient = function(clientToUpdate) {
		$scope.jsonObject = angular.toJson(clientToUpdate, false);
		console.log('JSON client to update: ' + $scope.jsonObject);
		
		$http.put("/acachaos/webapi/chaosconsignment/updateclient", $scope.jsonObject)
		.then (
				function success(response) {
					console.log('status: ' + response.status);
					$scope.updateStatus = 'Update Successful';
				},
				function error(response) {
					console.log('error, return status: ' + response.status);
					$scope.udpateStatus = 'Update Error, ' + response.data.message;
				}
		);
	}
	
	$scope.updateItem = function(itemToUpdate) {
		console.log('Selected item to update: ' + angular.toJson(itemToUpdate));
		$scope.itemToUpdate = angular.copy(itemToUpdate);
		$scope.showEditDelete = true;
		$scope.showSearch = false;
		$scope.updateStatus = '';
	}
	
	$scope.getItems = function() {
		console.log('getItems');
		$scope.items =[{"ItemId": "retrieving items list..."}]
		
		$scope.showSearch = true;
		$scope.showEditDelete = false;
		
		$http.get("/acachaos/webapi/chaosconsignment/getitems")
		.then(function(response) {
			$scope.items = response.data;
			console.log("Number of items: " + $scope.items.length);
		}, function(response) {
			console.log("Error HTTP GET items" + response.status);
		});
	}
	
	$scope.returnToItems = function() {
		$scope.showEditDelete = false;
		$scope.showSearch = true;
		$scope.getItems();
	}
	
	$scope.putItem = function(itemToUpdate) {
		$scope.jsonObject = angular.toJson(itemToUpdate, false);
		console.log('JSON item to update: ' + $scope.jsonObject);
		
		$http.put("/acachaos/webapi/chaosconsignment/updateitem", $scope.jsonObject)
		.then (
				function success(response) {
					console.log('status: ' + response.status);
					$scope.updateStatus = 'Update Successful';
				},
				function error(response) {
					console.log('error, return status: ' + response.status);
					$scope.udpateStatus = 'Update Error, ' + response.data.message;
				}
		);
	}
	
});



chaosapp.controller('chaosCreateController', function($scope, $http) {
	
	$scope.postClient = function() {
		$scope.jsonObject = angular.toJson($scope.newClient, false);
		console.log('New client: ' + $scope.jsonObject);
		
		$http.post("/acachaos/webapi/chaosconsignment/addclient", $scope.jsonObject)
		.then(
				function success(response) {
					console.log('Status: ' + response.status);
					$scope.createStatus = 'Successful Insert of New Client';
					$scope.successfulInsert = true;
					$scope.getClients();
				},
				function error(response) {
					console.log('Error, return status: ' + response.status);
					$scope.createStatus = 'Insert Error, ' + response.data.message;
				}
		 );
		$scope.showSearch = true;
		$scope.showEditDelete = false;
	};
	
	$scope.clearClient = function() {
		$scope.createStatus = 'Enter new client information';
		$scope.successfulInsert = false;
		$scope.newClient = {
				firstName : '',
				lastName : '',
				address : '',
				city : '',
				state : '',
				zipCode : '',
				phone : '',
				email : '',
				agreedCommission : '',
				itemsPosted : '',
				itemsSold : ''
		};
	}
	
	$scope.postItem = function() {
		$scope.jsonObject = angular.toJson($scope.newItem, false);
		console.log('New item: ' + $scope.jsonObject);
		
		$http.post("/acachaos/webapi/chaosconsignment/additem", $scope.jsonObject)
		.then(
				function success(response) {
					console.log('Status: ' + response.status);
					$scope.createStatus = 'Successful Insert of New Item';
					$scope.successfulInsert = true;
					$scope.getItems();
				},
				function error(response) {
					console.log('Error, return status: ' + response.status);
					$scope.createStatus = 'Insert Error, ' + response.data.message;
				}
		 );
		$scope.showSearch = true;
		$scope.showEditDelete = false;
	};
	
	$scope.clearItem = function() {
		$scope.createStatus = 'Enter new item information';
		$scope.successfulInsert = false;
		$scope.newItem = {
				itemName : '',
				clientId : '',
				itemCategoryId : '',
				itemDescription : '',
				itemSize : '',
				initialPrice : '',
				minimumPrice : ''
		};
	}
});