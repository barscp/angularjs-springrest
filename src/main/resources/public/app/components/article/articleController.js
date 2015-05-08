angular.module("app")
.controller("ArticleController",['$scope','$rootScope','$state','AuthorService', function($scope,$rootScope,$state,AuthorService){
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
	
	if($scope.id=='new'){
		$scope.setEditMode();
	}
	$scope.saveArticle=function(){
		console.log("saving form...");
		console.log(JSON.stringify($scope.article));
		var articleName=$scope.article.title.replace(/ /g,"-");
		if($scope.article.articleId){
			AuthorService.updateArticle({authorId:$rootScope.loginUser.authorId, articleId:$scope.article.articleId},JSON.stringify($scope.article), function(data,headers){
				console.log("update success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.article",{id:returnId,name:articleName});
				$scope.setViewMode();
			},
			function(error){
				console.log("update error");
			});
		}else {
			AuthorService.addArticle({authorId:$rootScope.loginUser.authorId},JSON.stringify($scope.article), function(data,headers){
				console.log("save success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.article",{id:returnId,name:articleName });
				$scope.setViewMode();
			},
			function(error){
				console.log("save error");
			});
		}
		
	}
	
}])


