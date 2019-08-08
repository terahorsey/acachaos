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
	.when('/itemsoldlist', {
		templateUrl : 'itemsoldlist.html'
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
	.when('/additemsold', {
		templateUrl : 'additemsold.html',
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
		$scope.isDeleteButtonDisabled = false;
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
	
	$scope.deleteItem = function(itemId) {
		console.log('Delete Item: ' + itemId);
		$http.delete("/acachaos/webapi/chaosconsignment/delete/" + itemId)
		.then(function(response) {
			$scope.isDeleteButtonDisabled = true;
			$scope.updateStatus = 'Delete Successful';
			console.log('Number of items deleted: ' + response.data.length);
		}, function(response) {
			console.log('Error HTTP DELETE items:' + response.status);
			$scope.updateStatus = 'Delete Error, ' + response.data.message;
	});
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
	
	$scope.updateItemSold = function(itemSoldToUpdate) {
		console.log('Selected item sold to update: ' + angular.toJson(itemSoldToUpdate));
		$scope.itemSoldToUpdate = angular.copy(itemSoldToUpdate);
		$scope.showEditDelete = true;
		$scope.showSearch = false;
		$scope.updateStatus = '';
	}
	
	$scope.getItemsSold = function() {
		console.log('getItemsSold');
		$scope.itemsSold =[{"ItemSoldId": "retrieving items sold list..."}]
		
		$scope.showSearch = true;
		$scope.showEditDelete = false;
		
		$http.get("/acachaos/webapi/chaosconsignment/getitemssold")
		.then(function(response) {
			$scope.itemsSold = response.data;
			console.log("Number of items sold: " + $scope.itemsSold.length);
		}, function(response) {
			console.log("Error HTTP GET items sold" + response.status);
		});
	}
	
	$scope.returnToItemsSold = function() {
		$scope.showEditDelete = false;
		$scope.showSearch = true;
		$scope.getItemsSold();
	}
	
	$scope.putItemSold = function(itemSoldToUpdate) {
		$scope.jsonObject = angular.toJson(itemSoldToUpdate, false);
		console.log('JSON item sold to update: ' + $scope.jsonObject);
		
		$http.put("/acachaos/webapi/chaosconsignment/updateitemsold", $scope.jsonObject)
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
	
	/** $scope.soldDate = {
			value: new Date(yyyy, MM, dd)
	}; **/
	
	$scope.itemCategories = 
		     [
		      {id: 1, name: 'Appliances'},
		      {id: 2, name: 'Arts & Crafts'},
		      {id: 3, name: 'Auto & Auto Parts'},
		      {id: 4, name: 'Baby & Kid Stuff'},
		      {id: 5, name: 'Bicycles & Bicyle Parts'},
		      {id: 6, name: 'Books, Movies, Music'},
		      {id: 7, name: 'Business Equipment'},
		      {id: 8, name: 'Clothing & Shoes'},
		      {id: 9, name: 'Collectibles'},
		      {id: 10, name: 'Computer & Computer Equip'},
		      {id: 11, name: 'Electronics & TV'},
		      {id: 12, name: 'Furniture'},
		      {id: 13, name: 'General, Other, Misc'},
		      {id: 14, name: 'Health & Beauty'},
		      {id: 15, name: 'Home & Garden'},
		      {id: 16, name: 'Home Decor & Household Items'},
		      {id: 17, name: 'Jewelry & Accessories'},
		      {id: 18, name: 'Musical Instruments'},
		      {id: 19, name: 'Pet Supplies'},
		      {id: 20, name: 'Sports & Outdoors'},
		      {id: 21, name: 'Tools'},
		      {id: 22, name: 'Toys & Games'},
		      {id: 23, name: 'Video Games & Gaming Equip'}
		     ];
	
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
	
	$scope.postItemSold = function() {
		$scope.jsonObject = angular.toJson($scope.newItemSold, false);
		console.log('New item sold: ' + $scope.jsonObject);
		
		$http.post("/acachaos/webapi/chaosconsignment/additemsold", $scope.jsonObject)
		.then(
				function success(response) {
					console.log('Status: ' + response.status);
					$scope.createStatus = 'Successful Insert of New Item Sold';
					$scope.successfulInsert = true;
					$scope.getItemsSold();
				},
				function error(response) {
					console.log('Error, return status: ' + response.status);
					$scope.createStatus = 'Insert Error, ' + response.data.message;
				}
		 );
		$scope.showSearch = true;
		$scope.showEditDelete = false;
	};
	
	 $scope.clearItemSold = function() {
		$scope.createStatus = 'Enter new item sold information';
		$scope.successfulInsert = false;
		$scope.newItemSold = {
				itemId : '',
				soldDate : '',
				priceSold : '',
				agreedCommission : '',
				commissionPaid : '',
				websiteId : '',
				clientId : ''
		};
	} 
	
	
});