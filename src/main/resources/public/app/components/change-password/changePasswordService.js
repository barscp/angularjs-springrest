angular.module('app')
.factory('ChangePasswordService',['$resource', function($resource){
	return $resource('change-password',{},{
		resetPassword: {
			method:'PUT'
		}
	});
}]);