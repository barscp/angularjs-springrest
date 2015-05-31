angular.module("app")
.controller("AuthorEditController",['$scope','$state','AuthorService','UserService', function($scope,$state,AuthorService,UserService){
	
	$scope.saveAuthor=function(){
		if($scope.frm.$invalid) {
				 $scope.frm.submitted=true;
				  return;
		}
		$scope.saveLoading=true; 
		  
		console.log("saving author info..");
		console.log(JSON.stringify($scope.authorInfo));
		var user = UserService.getLoginUser();
		if($scope.authorInfo.authorId){
			AuthorService.updateAuthor({authorId:user.authorId},JSON.stringify($scope.authorInfo), function(data,headers){
				console.log("update success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.author",{authorId:returnId});
				
				$scope.saveLoading=false; 
				$scope.setViewMode();
				
			},
			function(error){
				console.log("update error");
				$scope.saveLoading=false; 
				
			});
		}
		
	}

	
}])


