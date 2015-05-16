angular.module('app')
.directive('disqusComment', function(){
	return {
		scope:{
			articleId:'@'
		},
		templateUrl: "app/shared/disqusComment/disqusCommentDirective.html",
		
	}
});