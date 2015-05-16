app.factory('LoginService',['$resource', function($resource){
	return $resource('http://localhost:8080/user/:action',
			 {action:'@action'});
}]);