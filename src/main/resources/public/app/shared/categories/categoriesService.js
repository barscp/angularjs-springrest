'use strict';
//create a service to retrieve list of photography articles

angular.module('app')
.factory('CategoriesService',['$resource', function($resource){
	return $resource('articles/:type/categories');
	
}]);


