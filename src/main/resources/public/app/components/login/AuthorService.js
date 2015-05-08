var app = angular.module('app')


app.factory('AuthorService',['$resource', function($resource){
	return $resource('http://localhost:8080/authors/:authorId/article/:articleId',
			 {authorId:'@authorId',articleId:'@articleId'},
		 {
			addArticle: {
			method:'POST'
			},
			updateArticle:{
			method:'PUT'
			}
		
		});
}]);

app.factory('UserService',['$resource', function($resource){
	return $resource('http://localhost:8080/user/:action',
			 {action:'@action'});
}]);

