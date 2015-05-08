'use strict';

/* App Module */

 angular.module('app')
 .config(['$stateProvider', '$urlRouterProvider',function($stateProvider, $urlRouterProvider){
	// For any unmatched url, redirect to /state1
	  $urlRouterProvider.otherwise("/");
	  //
	  // Now set up the states
	  $stateProvider
	   .state('login', {
	      url: "/login",
	      templateUrl: "app/components/login/loginView.html"
	    })
	   .state('home', {
	      url: "/",
	      templateUrl: "app/components/home/homeView.html"
	    })
	    .state('blog', {
		      url: "/:type/:category/:page",
		      templateUrl: "app/components/blog/blogListView.html",
		      controller:function($scope,$rootScope,$stateParams, BlogService){
		      	console.log('type:'+$stateParams.type);
		      	console.log('category:'+$stateParams.category);
		      	console.log('page:'+$stateParams.page)
		      	 $rootScope.blogType=$stateParams.type;
		      	 $scope.category=$stateParams.category;
		      	 var page=1;//initialize to page 1
		      	 if($stateParams.page){
		      		 page=$stateParams.page;
		      	 }
		    	 if(!$stateParams.category || $stateParams.category == 'all'){
		    		 console.log('category is null or all')
		    		 BlogService.query({type:$stateParams.type,page:page},function(data, header){
		    			 $scope.articleList=data;
		    			 console.log("CountArticles header: "+header('CountArticles'))
		    			 $scope.countArticles = header('CountArticles');
		    			 
		    		 });
		    		
		    	 }else {
		    		console.log('categor is not null'); 
		    		BlogService.query({type:$stateParams.type,category:$stateParams.category,page:page},function(data,header){
		    			$scope.articleList=data;
		    			 console.log("CountArticles header: "+header('CountArticles'))
		    			 $scope.countArticles = header('CountArticles');
		    			 
		    		});
			    	 	 
		    	 }
		    	  
		      }
		    	  
		}).state('author', {
		      url: "/author/:authorId",
		      templateUrl: "app/shared/author/authorView.html",
		      controller:function($scope,$rootScope,$stateParams,ArticleAuthorService){
		      console.log('authorid: '+$stateParams.authorId)
		      $scope.authorInfo= ArticleAuthorService.get({authorId:$stateParams.authorId});
		 
		    	  
		      }
		    	  
		})    
	    .state('article', {
		     url: "/article/:id/:name",
		     templateUrl: "app/components/article/articleView.html",
		     controller:function($scope,$stateParams,ArticleService){
		    	 //controller is called on the first time, second call on same url wont call this
		    	 
		    	 console.log("stateParams:"+ $stateParams.id);
		    	   if($stateParams.id == 'new'){

		    		   console.log("well this is new");
		    		   $scope.article= {
		    				   
		    		   };
		    	   }else{
		    		   console.log("not new so get from db");
		    		   ArticleService.get({articleId:$stateParams.id}, function(success,headers){
			    		 console.log("result"+ JSON.stringify(success));
			    		 
			    		 $scope.article =success;
			    	 });
			      } 
		    	 $scope.id = $stateParams.id;
		        	
		     }
		   
		    	  
	    }) 
	    
}]);