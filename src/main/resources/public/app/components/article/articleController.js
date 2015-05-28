angular.module("app")
.controller("ArticleController",['$rootScope','$scope','$stateParams','$state','$location','UserService','PageTitle','articleInfo', function($rootScope,$scope,$stateParams,$state,$location,UserService,PageTitle,articleInfo){
	
	 console.log('articleInfo:' +JSON.stringify(articleInfo));
	 console.log("stateParams:"+ $stateParams.id);
	   if($stateParams.id == 'new'){
		   $scope.isNew=true;
		   console.log("well this is new");
		   $scope.article= {};
	   }else{
		   console.log("not new so get from db");
		   $scope.article =articleInfo;
		   $rootScope.blogType=articleInfo.type;
		   $scope.readyToBind=true;
		   $scope.articleUrl =$location.absUrl()
     } 
	
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
	
	if($scope.isNew){
		$scope.setEditMode();
	}else{
		 PageTitle.setTitle($scope.article.title)
			
	}
	$scope.PageTitle = PageTitle;
		
   $scope.user = UserService.getLoginUser();
  
	
}])


