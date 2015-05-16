
angular.module('app').
directive('recentArticles', ['ArticleService', function(ArticleService){
	return {
		scope: {},
		templateUrl: "app/shared/recent/recentArticleDirectiveView.html",
		controller: function($scope){
			$scope.articles=ArticleService.query();
			
		}
	}
}])