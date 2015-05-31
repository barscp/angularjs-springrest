angular.module("app")
.controller("ArticleController",['$rootScope','$scope','$stateParams','$state','$location','UserService','PageTitle','ArticleService', function($rootScope,$scope,$stateParams,$state,$location,UserService,PageTitle,ArticleService){
	
	 console.log("stateParams:"+ $stateParams.id);
	 $scope.editMode=false;
	 $scope.viewMode=true;
	 $scope.PageTitle = PageTitle;
			
	 $scope.user = UserService.getLoginUser();
	  
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
	 	
	 if($stateParams.id == 'new'){
		 $scope.isNew=true;
		 console.log("well this is new");
		 $scope.article= {};
		 $scope.setEditMode();
			
	   }else{
		   console.log("not new so get from db");
		   $scope.loadingArticle=true;
		   ArticleService.get({articleId:$stateParams.id}, function(data){
			   $scope.article =data;
			   $scope.loadingArticle = false;
			   PageTitle.setTitle($scope.article.title)
			   $rootScope.blogType=data.type;
			   $scope.readyToBind=true;
			//   $scope.articleUrl =$location.absUrl()
			   var protocol = $location.protocol();
			  
			   var host = $location.host();
			   var port = $location.port();
			   
			   var scrapperUrl = protocol+"://"+host;
			   if(port){
				   scrapperUrl = scrapperUrl+":"+port;
			   }
			   scrapperUrl = scrapperUrl+"/scrapper/article/"+data.articleId;
			   ///scrapper/artice/data.articleId;
			   console.log('scrapperUrl'+ scrapperUrl);
			   $scope.articleUrl = scrapperUrl;
			 //  $scope.articleUrl =$location.absUrl()
				 
		   },function(error){
					console.log('error loading article info');
					$scope.loadingArticle = false;
			
		   });
		      
		  
		  
     } 
	
	
	
	
}])


