var app=angular.module('app')
app.factory('ArticleService',['$resource', function($resource){
	return $resource('articles/:articleId', {articleId:'@id'}, {
		getArticle: {
			method:'GET'
		},
		update:{
			method:'PUT'
		}
		
	});
}]);
