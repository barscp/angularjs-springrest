var app=angular.module('app')
app.factory('ArticleService',['$resource', function($resource){
	return $resource('http://localhost:8080/articles/:articleId', {articleId:'@id'}, {
		getArticle: {
			method:'GET'
		},
		update:{
			method:'PUT'
		}
		
	});
}]);

app.factory('ArticleAuthorService',['$resource', function($resource){
	return $resource('http://localhost:8080/articles/author/:authorId',
			 {authorId:'@authorId'}
	);
}]);