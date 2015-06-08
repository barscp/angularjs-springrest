
angular.module('app').
directive('recentArticles', ['$location','ArticleService', function($location,ArticleService){
	return {
		scope: {},
		templateUrl: "app/shared/recent/recentArticleDirectiveView.html",
		controller: function($scope){
			$scope.articles=ArticleService.query();
			$scope.host = $location.host();
			$scope.removeSpace=function(value){
				value = value.replace(/ /g,"-");
				return value;
			}
		}
	}
}])