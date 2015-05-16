angular.module('app')
.controller('loginController',['$scope','$rootScope','$state','$http','LoginService', function($scope,$rootScope,$state,$http,LoginService){

  var authenticate = function(credentials, callback) {
	  
	    console.log("calling authenticate function");
	    var headers = credentials ? {authorization : "Basic "
	        + btoa(credentials.username + ":" + credentials.password)
	    } : {};
	 	console.log("headers: "+ credentials.username +" "+credentials.password);
	    $rootScope.basicAuth = headers.authorization;

	 	$http.get('http://localhost:8080/authenticate', {headers : headers})
	 		.success(function(data,head) {
	   		 	console.log("request to /authenticate");
	      	if (data.token) {
	      		console.log(JSON.stringify(data));
	    		console.log("authenticated is true");
	    	    $rootScope.authenticated = true;
	      	
	      	    
	      	
	      	} else {
	       		$rootScope.authenticated = false;
	     	 	console.log("authenticated is false");
	     	 
	      	}
	      	callback && callback(data); //if callback is not null the perform callback()?
	    	})
	 		.error(function() {
	 		console.log("authentication failed");	
	      	$rootScope.authenticated = false;
	      	$scope.error="true";
	      	callback && callback();
	    });
	};

   $scope.login = function() {
      authenticate($scope.credentials, function(data) {
	      if($rootScope.authenticated) {
	      	//$rootScope.loginUser = data;
	      
			
			LoginService.get(function(success){
				$rootScope.loginUser = 	success;
				$rootScope.loginUser.isAdmin = "true";	
				console.log(JSON.stringify($rootScope.loginUser));
			});
				
			
		
			
			

			$state.go("home");
		}

      });
		
  };
}]);