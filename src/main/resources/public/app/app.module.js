'use strict';

/* App Module */

var simpleBlogApp = angular.module('app', [ 'ngRoute', 'ngMessages',
		'ui.router', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap',
		'angulike' ]);

simpleBlogApp.run([ '$rootScope', '$state', 'LoginService', 'UserService',
		function($rootScope, $state, LoginService, UserService) {
			$rootScope.menu = [ {
				'key' : 'devnotes',
				'value' : 'Developer Notes',
				'icon' : 'fa fa-file-code-o'	
			}, {
				'key' : 'snaps',
				'value' : 'Snap Collection',
				'icon':'fa fa-camera-retro'
			},

			{
				'key' : 'projects',
				'value' : 'Projects',
				'icon': 'fa fa-puzzle-piece'
			} ];
			console.log('running application...');
			$rootScope.facebookAppId = '427122720793949';
			$rootScope.pageSize = 5;

			console.log('check if still logged in');
			LoginService.get(function(success) {
				$rootScope.loginUser = true;
				UserService.setLoginUser(success);

			}, function(error) {
				console.log("not logged-in encountered")
				UserService.setLoginUser(null);
				$rootScope.loginUser = false;
			});

		} ]);
// *Page title and MetaInformation helps on google search SEO, study further
simpleBlogApp.service('PageTitle', function() {
	var title = 'DeveloperLife';
	return {
		title : function() {
			return title;
		},
		setTitle : function(newTitle) {
			title = newTitle;
		}
	};
});
simpleBlogApp.service('MetaInformation', function() {
	var metaDescription = '';
	var metaKeywords = '';
	return {
		metaDescription : function() {
			return metaDescription;
		},
		metaKeywords : function() {
			return metaKeywords;
		},
		reset : function() {
			metaDescription = '';
			metaKeywords = '';
		},
		setMetaDescription : function(newMetaDescription) {
			metaDescription = newMetaDescription;
		},
		appendMetaKeywords : function(newKeywords) {
			for ( var key in newKeywords) {
				if (metaKeywords === '') {
					metaKeywords += newKeywords[key].name;
				} else {
					metaKeywords += ', ' + newKeywords[key].name;
				}
			}
		}
	};
});

simpleBlogApp.factory('myInterceptor', function($q, $rootScope, UserService) {
	var interceptor = {
		'request' : function(config) {

			return config; // or $q.when(config);
		},
		'response' : function(response) {

			return response; // or $q.when(config);
		},
		'requestError' : function(rejection) {
			// an error happened on the request
			// if we can recover from the error
			// we can return a new request
			// or promise
			return response; // or new promise
			// Otherwise, we can reject the next
			// by returning a rejection
			// return $q.reject(rejection);
		},
		'responseError' : function(rejection) {
			
			if(rejection.status=='401' && $rootScope.loginUser==true){
				console.log('server session has expired, logging out...');
				UserService.setLoginUser(null);
				$rootScope.loginUser = false;
				$rootScope.errorMessage="Your session has expired, please login again. Actions that was not save will be lost.";
			}
			return $q.reject(rejection); // or new promise
			// Otherwise, we can reject the next
			// by returning a rejection
			// return $q.reject(rejection);
		}
	};

	return interceptor;
});

simpleBlogApp.config(function($httpProvider) {
	$httpProvider.interceptors.push('myInterceptor');
});
