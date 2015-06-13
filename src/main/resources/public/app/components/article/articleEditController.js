angular.module("app")
.controller("ArticleEditController",['$scope','$state','AuthorService','UserService', function($scope,$state,AuthorService,UserService){
	$scope.cancelEdit = function(){
		$state.go("^.article",{id:$scope.article.articleId,name:null});
		$scope.setViewMode();
	}
	$scope.saveArticle=function(){
		if($scope.frm.$invalid) {
				 $scope.frm.submitted=true;
				  return;
		}
		 $scope.saveLoading=true;  
		console.log("saving form...");
		console.log(JSON.stringify($scope.article));
		var articleName=$scope.article.title.replace(/ /g,"-");
		
		var user = UserService.getLoginUser();

		if($scope.article.articleId){
			AuthorService.updateArticle({authorId:user.authorId, articleId:$scope.article.articleId},JSON.stringify($scope.article), function(data,headers){
				console.log("update success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.article",{id:returnId,name:articleName});
				$scope.saveLoading=false; 
				$scope.setViewMode();
			},
			function(error){
				console.log("update error");
				$scope.saveLoading=false; 
				
			});
		}else {
			AuthorService.addArticle({authorId:user.authorId},JSON.stringify($scope.article), function(data,headers){
				console.log("save success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.article",{id:returnId,name:articleName });
				$scope.saveLoading=false; 
				
				$scope.setViewMode();
			},
			function(error){
				console.log("save error");
				$scope.saveLoading=false; 
				
			});
		}
		
	}

	
}])


