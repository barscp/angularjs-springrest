angular.module('app')
.controller('CreateAuthorController', ['$scope','$state','CreateAuthorService','UserService', function($scope,$state,CreateAuthorService,UserService){
	$scope.user = UserService.getLoginUser();
	$scope.createAuthor = function(){
		$scope.error=null;
		$scope.message=null;
		if($scope.frm.$invalid) {
			 $scope.frm.submitted=true;
			  return;
	   }
		 
		  
		  $scope.createLoading = true;
		  CreateAuthorService.createAuthor({email:$scope.email,firstName:$scope.firstName}, function(data){
			 console.log('success create new user');
			 $scope.createLoading = false;
			 $scope.message="Author created successfully";
			 //exit once successful
		 }, function(error){
			 console.log('Failed to create author info');
			 $scope.createLoading = false;
			 $scope.error="Failed to create author info";
		 }) 
		
	}
	$scope.doTheBack = function() {
		  window.history.back();
		};
}])