angular.module('app')
.controller('ChangePasswordController', ['$scope','$state','ChangePasswordService','UserService', function($scope,$state,ChangePasswordService,UserService){
	$scope.user = UserService.getLoginUser();
	$scope.changePassword = function(){
		if($scope.frm.$invalid) {
			 $scope.frm.submitted=true;
			  return;
	   }
		$scope.error=null;
		$scope.message=null;
		
		if($scope.newPassword1 != $scope.newPassword2){
			  $scope.error="password did not match";
			  return;
		  }
		  
		  $scope.resetLoading = true;
		  ChangePasswordService.resetPassword({curPassword:$scope.currentPassword,newPassword:$scope.newPassword1}, function(data){
			 console.log('success reset password');
			 $scope.resetLoading = false;
			 $scope.message="Password changed successful";
			 //exit once successful
		 }, function(error){
			 console.log('Failed to reset password');
			 $scope.resetLoading = false;
			 $scope.error="Failed to reset password";
		 }) 
		
	}
	
	$scope.doTheBack = function() {
		  window.history.back();
		};
}])