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
		      url: "/menu/:type/:category/:page",
		      templateUrl: "app/components/blog/blogListView.html",
		      controller: "BlogListController"
		    
		    	  
		}).state('author', {
		      url: "/author/:authorId/:name",
		      templateUrl: "app/components/author/authorView.html",
		      controller: "AuthorController"
		      
		    	  
		})    
	    .state('article', {
		     url: "/article/:id/:title",
		     templateUrl: "app/components/article/articleView.html",
		     controller: "ArticleController"
		   
		    	  
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