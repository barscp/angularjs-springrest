package com.bperalta.simpleblog.controller;

import java.security.Principal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.service.BlogService;
import com.bperalta.simpleblog.transfer.TokenTransfer;
import com.bperalta.simpleblog.transfer.UserTransfer;


@RestController
public class AuthenticationCtrl {
	Logger logger=LoggerFactory.getLogger(AuthenticationCtrl.class);

	

	@Autowired
	private BlogService blogService;
	@RequestMapping(value="user", method=RequestMethod.GET)
	  public ResponseEntity<UserTransfer> getUser(Principal user) {
		logger.info("getUser");
		if(user!=null){
			logger.info("user:"+user.getName());
			//TODO maybe return a token instead;
			Login loginInfo =blogService.findLoginByUsername(user.getName());
			logger.info("login id:" + loginInfo.getLoginId());
			logger.info("login roles:" + loginInfo.getRoles());
			logger.info("get author:" + loginInfo.getAuthor());
		
			
			UserTransfer userTransfer = new UserTransfer(user.getName(),loginInfo.getAuthor().getAuthorId(),loginInfo.getRoles());
		    return new ResponseEntity<UserTransfer>(userTransfer,HttpStatus.OK);
		}else {
			UserTransfer userTransfer = null;
			return new ResponseEntity<UserTransfer>(userTransfer,HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value="authenticate", method=RequestMethod.GET)
	  public ResponseEntity<TokenTransfer> login(Principal user) {
		logger.info("authenticating user");
		if(user!=null){
			logger.info("user:"+user.getName());
			//TODO maybe return a token instead;
			String uuid = UUID.randomUUID().toString();
			TokenTransfer token = new TokenTransfer(uuid);
		    return new ResponseEntity<TokenTransfer>(token,HttpStatus.OK);
		}else {
			TokenTransfer token = null;
			return new ResponseEntity<TokenTransfer>(token,HttpStatus.UNAUTHORIZED);
		}
	}
	//logout redirects to login?logout which cause error, adding this will catch the login?logout
	@RequestMapping(value="login",method = RequestMethod.GET)
	public void logout( @RequestParam(value="error", required=false) String error, @RequestParam(value="logout",required=false) String logout){
		if(error!=null){
			logger.info("faild logging out");
		}
		if(logout!=null){
			logger.info("logout successfully");
		}
	}

}
