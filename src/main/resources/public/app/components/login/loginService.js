app.factory('LoginService',['$resource', function($resource){
	return $resource('user/:action',
			 {action:'@action'});
}]);