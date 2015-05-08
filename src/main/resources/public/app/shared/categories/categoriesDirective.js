angular.module("app")
.directive("myCategoryTags", ['CategoriesService', function(CategoriesService){
	return {
		scope: {
			blogType: "@"
		},
		templateUrl: "app/shared/categories/categoriesDirectiveView.html",
		link: function(scope,ele,attrs){
			scope.$watch(function(scope){
				return scope.blogType;
			}, function(newVal){
				if(newVal){
					console.log("display blog:"+newVal);
					CategoriesService.query({type:newVal}, function(success){
						scope.categories=success;
					
					});
					
				}
				
				
			})
		}
	}
	
} ])