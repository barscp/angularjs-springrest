angular.module('app')
.factory('CreateAuthorService',['$resource', function($resource){
	return $resource('authors/create/:email/:firstName',{email:'@email',firstName:'@firstName'},{
		createAuthor: {
			method:'POST'
		}
	});
}]);