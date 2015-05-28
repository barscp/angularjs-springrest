angular.module("app")
.controller("AuthorController",['$scope','$state','UserService','authorInfo', function($scope,$state,UserService,authorInfo){
	
	 console.log("result:"+ JSON.stringify(authorInfo));
	 $scope.authorInfo=authorInfo;
	
	$scope.editMode=false;
	$scope.viewMode=true;
	
	$scope.setEditMode=function(){
		$scope.editMode=true;
		$scope.viewMode=false;
		console.log("edit mode");
		
	}
	$scope.setViewMode=function(){
		$scope.editMode=false;
		$scope.viewMode=true;
		console.log("view mode");
		
	}
	
	$scope.user = UserService.getLoginUser();

	
}])


