angular.module('app')
.factory('UploadFileService',['$resource', function($resource){
	return $resource('upload',{},{
		upload: {
			method:'POST',
			headers: {enctype:'multipart/form-data'}
		}
	});
}]);