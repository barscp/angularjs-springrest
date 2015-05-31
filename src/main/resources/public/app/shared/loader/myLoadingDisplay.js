angular.module('app')
.directive('myLoadingDisplay', function(){
	return {
		scope:{
			loadIndicator:"="
		},
		templateUrl:"app/shared/loader/loadingDirectiveView.html"
	}
});