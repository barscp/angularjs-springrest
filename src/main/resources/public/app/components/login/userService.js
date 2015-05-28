angular.module('app')
.service('UserService', function(){
	var loginUser;
	this.setLoginUser = function(user){
		console.log("setting login user to UserService");
		if(user){
			if(user.roles.indexOf('ADMIN')>-1){
				user.isAdmin = true;	
			}else {
				user.isAdmin = false;		
			}
		}
		loginUser = user;
	}
	 this.getLoginUser = function(){
		 console.log("getting login user from UserService");
		return loginUser;
	}
});