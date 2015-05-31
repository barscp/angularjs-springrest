angular.module('app')
.controller('HeaderController',['$scope','$rootScope','$state','$http','UserService',function($scope,$rootScope,$state,$http,UserService){
	  $scope.$watch(function($scope){
		  
		  return $scope.loginUser;
	  }, function (val){
		  console.log('has login: '+val);
		  $scope.user = UserService.getLoginUser();
		  
	  });
	  
	  $scope.doSearch = function(){
			console.log('searching'+ $scope.searchText);
			var searchTxt = $scope.searchText;
			$state.go("blog",{type:'search', category:searchTxt, page:null});
		};
		
		
		$scope.logout = function(){
			//call spring url
			$scope.signoutLoading= true;
		 	$http.post('/logout')
	 		.success(function(data,head) {
	   		 	console.log("logout successfully");
		   		 $rootScope.loginUser = false;
		   		UserService.setLoginUser(null);
		 		$scope.signoutLoading=false;
		 		$state.go("home");
	    	})
	 		.error(function() {
		      console.log("faild logging out");
	 		});
		
		};	
}]);