angular.module('app')
.directive('myPagination',function(){
	return {
		scope: {
			blogType: "@",
			category: "@",
			countArticles: "@",
			pageSize:"@",
			pageNumber:"@"
		},
		templateUrl: "app/shared/pagination/paginationDirectiveView.html",
		link: function(scope,ele,attrs){
			scope.$watch(function(scope){
				return scope.countArticles;
			}, function(newVal){
				if(newVal){
					scope.pageCount = Math.ceil(scope.countArticles/scope.pageSize);
					
				}
				
				
			})
		},
		controller: function($scope){
			$scope.getPageNumbers = function(n){
				var items = [];
				for(var i=0;i<n;i++){
					items.push(i+1);
				}
				return items;
			}
			$scope.getNextPage = function(){
				return parseInt($scope.pageNumber) +1;
			}
			$scope.getPrevPage = function(){
				return parseInt($scope.pageNumber) -1;
			}
			
		}
			
	}
})