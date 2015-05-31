angular.module("app")
.controller("AuthorController",['$scope','$stateParams','UserService','AuthorService', function($scope,$stateParams,UserService,AuthorService){
	
	
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
	  $scope.loadingAuthor = true;
		 
	AuthorService.get({authorId:$stateParams.authorId},function(data){
		 $scope.authorInfo=data;
		  $scope.loadingAuthor = false;
			 
	},function(error){
		console.log('error loading author info');
		$scope.loadingAuthor = false;
	});
}])


