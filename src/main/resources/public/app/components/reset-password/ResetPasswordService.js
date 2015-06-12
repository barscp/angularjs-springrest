angular.module('app')
.factory('ResetPasswordService',['$resource', function($resource){
	return $resource('reset-password',{},{
		resetPassword: {
			method:'PUT'
		}
	});
}]);