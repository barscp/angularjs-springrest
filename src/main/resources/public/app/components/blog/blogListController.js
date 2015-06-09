angular.module('app')
.controller('BlogListController',['$scope','$rootScope','$stateParams','BlogService', function($scope,$rootScope,$stateParams, BlogService){
		      	console.log('type1:'+$stateParams.type);
		      	console.log('category1:'+$stateParams.category);
		      	console.log('page:'+$stateParams.page)
		      	 $rootScope.blogType=$stateParams.type;
		      	 $scope.category=$stateParams.category;
		      	
		      	 $scope.loadingBlogList =true;
		      	 
		      	 var page = 1; 
	    		 if($stateParams.page){
			      		 page=$stateParams.page;
			      	 }
			    	 if($stateParams.type =='search'){
			    		 console.log('perform search')
			    		 BlogService.query({type:$stateParams.type,searchKey:$stateParams.category,page:page},function(data, header){
			    			 $scope.articleList = data;
			    			 $scope.countArticles = header('CountArticles')
			    			 $scope.loadingBlogList =false;
			    			 
			    		 },function(error){
			    				console.log('error loading blog list');
								$scope.loadingBlogList = false;
						
			    		 });
			    	 }else
			      	 if(!$stateParams.category || $stateParams.category == 'all'){
			    		 console.log('category is null or all')
			    		 BlogService.query({type:$stateParams.type,page:page},function(data, header){
			    			 $scope.articleList = data;
			    			 $scope.countArticles = header('CountArticles')
			    			 $scope.loadingBlogList =false; 
			    		 },function(error){
			    				console.log('error loading blog list');
								$scope.loadingBlogList = false;
						
			    		 });
			    		
			    	 }else {
			    		console.log('category is not null'); 
			    		BlogService.query({type:$stateParams.type,category:$stateParams.category,page:page},function(data,header){
			    			 $scope.articleList = data;
			    			 $scope.countArticles = header('CountArticles');
			    			 $scope.loadingBlogList =false
			    			 
			    		},function(error){
		    				console.log('error loading blog list');
							$scope.loadingBlogList = false;
					
		    		 });
				    	 	 
			    	 }
	    		 
		      	
		      	 var page=1;//initialize to page 1
		      	 if($stateParams.page){
		      		 page=$stateParams.page;
		      	 }
		      	 $scope.pageNumber = page;
}]);		      	 
		      	 