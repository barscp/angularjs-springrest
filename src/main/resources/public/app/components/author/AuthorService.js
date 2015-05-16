var app = angular.module('app')


app.factory('AuthorService',['$resource', function($resource){
	return $resource('http://localhost:8080/authors/:authorId/:article/:articleId',
			 {authorId:'@authorId',articleId:'@articleId'},
		 {
			addArticle: {
				method:'POST',
				params:{article:'article'}
			},
			updateArticle:{
				method:'PUT',
				params:{article:'article'}
			}
		});
}]);



