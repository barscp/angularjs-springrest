angular.module('app')
.controller('ResetPasswordController', ['$scope','ResetPasswordService', function($scope,ResetPasswordService){
	$scope.resetPassword = function(){
		$scope.error=null;
		$scope.message=null;
		 if($scope.frm.$invalid) {
			 $scope.frm.submitted=true;
			  return;
	   }
		  $scope.resetLoading = true;
		 ResetPasswordService.resetPassword({email:$scope.email}, function(data){
			 console.log('success reset password');
			 $scope.resetLoading = false;
			 $scope.message="Password reset successful, please check your email";
		 }, function(error){
			 console.log('Failed to reset password');
			 $scope.resetLoading = false;
			 $scope.error="Failed to reset password";
		 }) 
		console.log('resetting password');
	}
}])