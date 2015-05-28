angular.module('app')
.controller('loginController',['$scope','$rootScope','$state','$http','LoginService','UserService', function($scope,$rootScope,$state,$http,LoginService, UserService){

  var authenticate = function(credentials, successCallBack) {
	  
	    console.log("calling authenticate function");
	    var headers = credentials ? {authorization : "Basic "
	        + btoa(credentials.username + ":" + credentials.password)
	    } : {};
	 	console.log("headers: "+ credentials.username +" "+credentials.password);
	    $rootScope.basicAuth = headers.authorization;

	 	$http.get('authenticate', {headers : headers})
	 		.success(function(data,head) {
	   		 	console.log("authentication success");
	      		console.log(JSON.stringify(data));
	    	    successCallBack && successCallBack(data); 
	      	
	    	})
	 		.error(function() {
	 		console.log("authentication failed");	
	 		$scope.error = true;
	      
	    });
	};

   $scope.login = function() {
	   if($scope.frm.$invalid) {
			 $scope.frm.submitted=true;
			  return;
	   }
	   
      authenticate($scope.credentials, function(data) {
    	  UserService.setLoginUser(null);
    	  $rootScope.loginUser = false;
	      LoginService.get(function(success){
				UserService.setLoginUser(success);
				$rootScope.loginUser=true;
				console.log(JSON.stringify(UserService.getLoginUser()));
				$state.go("home");
				
	      });
				
	
		

      });
		
  };
}]);