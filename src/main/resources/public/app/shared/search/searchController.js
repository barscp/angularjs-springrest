angular.module('app')
.controller('SearchController',['$scope','$state',function($scope,$state){
	console.log('init search controller');
	$scope.doSearch = function(){
		console.log('searching'+ $scope.searchText);
		var searchTxt = $scope.searchText;
		$state.go("blog",{type:'search', category:searchTxt, page:null});
	}
	
}]);