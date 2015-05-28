angular.module('app')
.controller('BlogListController',['$scope','$rootScope','$stateParams','articleList', function($scope,$rootScope,$stateParams, articleList){
		      	console.log('type:'+$stateParams.type);
		      	console.log('category:'+$stateParams.category);
		      	console.log('page:'+$stateParams.page)
		      	 $rootScope.blogType=$stateParams.type;
		      	 $scope.category=$stateParams.category;
		      	 $scope.articleList = articleList.data;
		      	 $scope.countArticles = articleList.totalCount;
		      	console.log('total count:'+$scope.countArticles);
		      	 var page=1;//initialize to page 1
		      	 if($stateParams.page){
		      		 page=$stateParams.page;
		      	 }
		      	 $scope.pageNumber = page;
}]);		      	 
		      	 