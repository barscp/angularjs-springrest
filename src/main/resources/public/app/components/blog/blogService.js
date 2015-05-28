'use strict';
//create a service to retrieve list of photography articles

angular.module('app')
.factory('BlogService',['$resource', function($resource){
	return $resource('articles/type/:type');
	
}]);


