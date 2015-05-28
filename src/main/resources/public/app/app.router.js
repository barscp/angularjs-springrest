'use strict';

/* App Module */

 angular.module('app')
 .config(['$stateProvider', '$urlRouterProvider','$locationProvider',function($stateProvider, $urlRouterProvider,$locationProvider){
	  $urlRouterProvider.otherwise("/");
	  //
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
		      controller: "BlogListController",
		      resolve: {
		    	  BlogService: 'BlogService',
		    	  articleList: function($stateParams, $q, BlogService){
		    		  var blogs =  $q.defer();
		    		 console.log('defer..'+$stateParams.type);
		    		 var page = 1; 
		    		 if($stateParams.page){
				      		 page=$stateParams.page;
				      	 }
				    	 if($stateParams.type =='search'){
				    		 console.log('perform search')
				    		 BlogService.query({type:$stateParams.type,searchKey:$stateParams.category,page:page},function(data, header){
				    			 blogs.resolve({
				    			    data:data,
				    			    totalCount : header('CountArticles')
				    			 });
				    			 
				    		 });
				    	 }else
				      	 if(!$stateParams.category || $stateParams.category == 'all'){
				    		 console.log('category is null or all')
				    		 BlogService.query({type:$stateParams.type,page:page},function(data, header){
				    			 console.log('resolving...');
				    			 blogs.resolve({
					    			    data:data,
					    			    totalCount : header('CountArticles')
					    			 });
				    			 
				    		 });
				    		
				    	 }else {
				    		console.log('categor is not null'); 
				    		BlogService.query({type:$stateParams.type,category:$stateParams.category,page:page},function(data,header){
				    			blogs.resolve({
				    			    data:data,
				    			    totalCount : header('CountArticles')
				    			 });
				    			 
				    		});
					    	 	 
				    	 }
		    		  
		    		  
		    		  return blogs.promise;
		    	  }
		      }
		    	  
		}).state('author', {
		      url: "/author/:authorId",
		      templateUrl: "app/components/author/authorView.html",
		      controller: "AuthorController",
		      resolve: {
		    	  AuthorService: 'AuthorService',
		    	  authorInfo: function($stateParams,AuthorService){
			    	  console.log('authorid: '+$stateParams.authorId);
					    
		    		    return AuthorService.get({authorId:$stateParams.authorId}).$promise;
		    	   
		    	  
		    	  }
		      }
		    	  
		})    
	    .state('article', {
		     url: "/article/:id",
		     templateUrl: "app/components/article/articleView.html",
		     controller: "ArticleController",
		     resolve:{
		    	 ArticleService:'ArticleService',
		    	 articleInfo: function($stateParams, ArticleService){
		    		 if($stateParams.id == 'new'){
			    		  return {};
			    	   }else{
			    		  return  ArticleService.get({articleId:$stateParams.id}).$promise;
				      } 
		    	 }
		     }
		   
		    	  
	    }).state('aboutSite', {
		      url: "/about-this-website",
		      templateUrl: "app/shared/static_pages/aboutThisSite.html"
		    	  
		}).state('changePassword',{
			url:"/change-password",
			templateUrl:"/app/components/change-password/changePasswordView.html"
		});  
	 /** 
	  * this is for removing hashbang, study further
	  * $locationProvider.html5Mode(true);
		*/
	    
}]);