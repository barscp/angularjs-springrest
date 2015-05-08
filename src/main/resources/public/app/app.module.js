'use strict';

/* App Module */

var simpleBlogApp= angular.module('app', [
  'ngRoute',
  'ngMessages',
  'ui.router',
  'ngResource',
  'ngAnimate',
  'ngSanitize'
]);

simpleBlogApp.run(['$rootScope','$state','$http','UserService', function($rootScope,$state,$http,UserService){
	$rootScope.menu=[{'key':'programming', 'value':'Programming'}, 
					{'key':'photography', 'value':'Photography'}, 
					{'key':'projects', 'value':'Projects'},
					{'key':'blogs', 'value':'Blogs'}];
	console.log('running application...');
	
	$rootScope.pageSize=5;
	
	console.log('check if still logged in');
	UserService.get(function(success){
		$rootScope.loginUser = 	success;
		$rootScope.loginUser.isAdmin = "true";	
		console.log(JSON.stringify($rootScope.loginUser));
	}, function(error){
		console.log("not logged-in encountered")
	});
	
	$rootScope.logout = function(){
		//call spring url
	 	$http.post('http://localhost:8080/logout')
 		.success(function(data,head) {
   		 	console.log("logout successfully");
	   		 $rootScope.loginUser = null;
	 		$rootScope.basicAuth = null;
	 		$state.go("home");
    	})
 		.error(function() {
	      console.log("faild logging out");
 		});
	
	}
	


	
}]);

// simpleBlogApp.factory('myInterceptor',
//	 function($q,$rootScope) {
//		var interceptor = {
//	'request': function(config) {
//	 // Successful request method
//	 //adding basic auth to each request
//	 if($rootScope.basicAuth){
//	 
//	 	// config.headers['Authorization'] = $rootScope.basicAuth;
//
//	 }
//
//	 //if ($rootScope.xsrfToken) {
//		//    config.headers['XSRF-TOKEN'] = $rootScope.xsrfToken;
//	 //		config.xsrfCookieName['XSRF-TOKEN']=$rootScope.xsrfToken;
//	// }       				
//		    
// return config; // or $q.when(config);
// },
// 'response': function(response) {
// 	//how to get response cookie?
// // successful response
// 
// return response; // or $q.when(config);
// },
// 'requestError': function(rejection) {
// // an error happened on the request
// // if we can recover from the error
// // we can return a new request
// // or promise
// return response; // or new promise
// // Otherwise, we can reject the next
// // by returning a rejection
// // return $q.reject(rejection);
// },
// 'responseError': function(rejection) {
// // an error happened on the request
// // if we can recover from the error
// // we can return a new response
// // or promise
// return rejection; // or new promise
// // Otherwise, we can reject the next
// // by returning a rejection
// // return $q.reject(rejection);
// }
// };
//
// return interceptor;
// });

// simpleBlogApp.config(function($httpProvider) {
// $httpProvider.interceptors.push('myInterceptor');
//});


