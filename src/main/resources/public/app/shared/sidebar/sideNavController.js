angular.module('app')
.controller('SideNavController',['$scope','$rootScope','UserService', function($scope,$rootScope,UserService){
	
  $scope.$watch(function($scope){
	  return $scope.loginUser;
  }, function (val){
	  console.log('has login '+val);
	  $scope.user = UserService.getLoginUser();
	  
  })
}]);