angular.module("app")
.controller("ArticleController",['$scope','$stateParams','$state','$location','UserService','PageTitle','ArticleService','AuthorService', function($scope,$stateParams,$state,$location,UserService,PageTitle,ArticleService,AuthorService){
	
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
	 $scope.publish = function(value){
		 $scope.publishLoading=true;  
		 console.log('publish:'+value);
		 if(value==true){
			 $scope.article.isPublished='Y';
		 }else {
			 $scope.article.isPublished='N';
					 
		 }
		 var articleName=$scope.article.title.replace(/ /g,"-");
			
		 var user = UserService.getLoginUser();
		 AuthorService.updateArticle({authorId:user.authorId, articleId:$scope.article.articleId},JSON.stringify($scope.article), function(data,headers){
				console.log("update success");
				console.log("location: "+headers('Location'))
				var returnId = headers('Location').split('/').pop();
				$state.go("^.article",{id:returnId,name:articleName});
				$scope.publishLoading=false; 
				$scope.setViewMode();
			},
			function(error){
				console.log("update error");
				$scope.publishLoading=false; 
				
			});
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
			   $scope.readyToBind=true;
			//   $scope.articleUrl =$location.absUrl()
			   var protocol = $location.protocol();
			  
			   var host = $location.host();
			   var port = $location.port();
			   
			   var scrapperUrl = protocol+"://"+host;
//			   if(port){
//				   scrapperUrl = scrapperUrl+":"+port;
//			   }
			   scrapperUrl = scrapperUrl+"/scrapper/article/"+data.articleId;
			   ///scrapper/artice/data.articleId;
			   console.log('scrapperUrl:'+ scrapperUrl);
			   $scope.articleUrl = scrapperUrl;
			 //  $scope.articleUrl =$location.absUrl()
				 
		   },function(error){
					console.log('error loading article info');
					$scope.loadingArticle = false;
			
		   });
		      
		  
		  
     } 
	
	 $scope.doTheBack = function() {
		  window.history.back();
		};
	
	
	
}])


