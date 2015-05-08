'use strict';
//create a service to retrieve list of photography articles

angular.module('app')
.factory('CategoriesService',['$resource', function($resource){
	return $resource('http://localhost:8080/articles/:type/categories');
	
}]);


