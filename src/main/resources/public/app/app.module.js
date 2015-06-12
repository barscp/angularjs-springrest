'use strict';

/* App Module */

var simpleBlogApp = angular.module('app', [ 'ngRoute', 'ngMessages',
		'ui.router', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap',
		'angulike','angularUtils.directives.dirDisqus' ]);

simpleBlogApp.run([ '$rootScope', '$state', 'LoginService', 'UserService',
		function($rootScope, $state, LoginService, UserService) {


	
	$rootScope.menu = [ {
				'id':'1',
				'key' : 'java',
				'value' : 'Java',
				'icon' : 'fa fa-file-code-o'
			}, {
				'id':'2',
				'key' : 'javascript',
				'value' : 'Javascript',
				'icon' : 'fa fa-file-code-o'
			},

			{
				'id':'3',
				'key' : 'data',
				'value' : 'Data',
				'icon' : 'fa fa-database'
			},
			{
				'id':'4',
				'key' : 'tools',
				'value' : 'Tools',
				'icon': 'fa fa-wrench'
			},

			{
				'id':'5',	
				'key' : 'misc',
				'value' : 'Misc',
				'icon': 'fa fa-puzzle-piece'
			}
			 ];
			
			$rootScope.removeSpace=function(value){
				value = value.replace(/ /g,"-");
				return value;
			}
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

function replaceText(str)
{
    var str1 = String(str);
    return str1.replace(/\n/g,"<br/>");
}

simpleBlogApp.directive('prettyprint', function() {
    return {
        restrict: 'AC',
        link: function (scope, element, attrs) {
              element.html(prettyPrintOne(element.html(),'',true));
        }
    };
});
simpleBlogApp.directive('compile', ['$compile', function ($compile) {
    return function(scope, element, attrs) {
        var ensureCompileRunsOnce = scope.$watch(
          function(scope) {
             // watch the 'compile' expression for changes
            return scope.$eval(attrs.compile);
          },
          function(value) {
            // when the 'compile' expression changes
            // assign it into the current DOM
            element.html(value);

            // compile the new DOM and link it to the current
            // scope.
            // NOTE: we only compile .childNodes so that
            // we don't get into infinite loop compiling ourselves
            $compile(element.contents())(scope);

            // Use un-watch feature to ensure compilation happens only once.
            ensureCompileRunsOnce();
          }
      );
  };
}]);

