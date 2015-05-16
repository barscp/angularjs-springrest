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
	      templateUrl: "app/shared/static_pages/homeView.html"
	    })
	    .state('blog', {
		      url: "/blog/:type/:category/:page",
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
		      	 $scope.pageNumber = page;
		    	 if($stateParams.type =='search'){
		    		 console.log('perform search')
		    		 BlogService.query({type:$stateParams.type,searchKey:$stateParams.category,page:page},function(data, header){
		    			 $scope.articleList=data;
		    			 console.log("CountArticles header: "+header('CountArticles'))
		    			 $scope.countArticles = header('CountArticles');
		    			 
		    		 });
		    	 }else
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
		      templateUrl: "app/components/author/authorView.html",
		      controller:function($scope,$rootScope,$stateParams,AuthorService){
		    	  console.log('authorid: '+$stateParams.authorId)
		    	  $scope.authorInfo= AuthorService.get({authorId:$stateParams.authorId});
		 
		    	  
		      }
		    	  
		})    
	    .state('article', {
		     url: "/article/:id",
		     templateUrl: "app/components/article/articleView.html",
		     controller:function($scope,$stateParams,$location,$rootScope,ArticleService){
		    	 //controller is called on the first time, second call on same url wont call this
		    	 
		    	 console.log("stateParams:"+ $stateParams.id);
		    	   if($stateParams.id == 'new'){

		    		   console.log("well this is new");
		    		   $scope.article= {
		    				   
		    		   };
		    	   }else{
		    		   console.log("not new so get from db");
		    		   ArticleService.get({articleId:$stateParams.id}, function(data,headers){
				    		 console.log("result"+ JSON.stringify(data));
				    		 $scope.article =data;
				    		 $rootScope.blogType=data.type;
				    		//need for Disqus
				    		 $scope.readyToBind=true;
				    		 console.log("path URL " +$location.absUrl());
				    		 $scope.articleUrl =$location.absUrl()
				    		
			    	 });
			      } 
		    	 $scope.id = $stateParams.id;
		        	
		     }
		   
		    	  
	    }).state('aboutSite', {
		      url: "/about-this-website",
		      templateUrl: "app/shared/static_pages/aboutThisSite.html"
		    	  
		});  
	    
}]);