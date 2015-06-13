package com.bperalta.simpleblog.controller;

import java.security.Principal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bperalta.simpleblog.data.entity.Author;
import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.service.BlogService;
import com.bperalta.simpleblog.transfer.ResetPassword;
import com.bperalta.simpleblog.transfer.TokenTransfer;
import com.bperalta.simpleblog.transfer.UserTransfer;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;


@RestController
public class AuthenticationCtrl {
	Logger logger=LoggerFactory.getLogger(AuthenticationCtrl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BlogService blogService;
	
	@Autowired 
	SendGrid sendgrid;
	
	@RequestMapping(value="change-password", method=RequestMethod.PUT)
	public ResponseEntity<Void> resetPassword(Principal user, @RequestBody ResetPassword resetPassword){
		logger.info("reset password...");
		Login loginInfo =blogService.findLoginByUsername(user.getName());
		Author author = blogService.findAuthorByEmail(user.getName());
		
		if(!passwordEncoder.matches(resetPassword.getCurPassword(),loginInfo.getPassword())){
			logger.info("password did not match");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
					
		}else {
			logger.info("password did match! perform reset password");
			
			loginInfo.setPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
			blogService.updateLogin(loginInfo);
			//perform email notification
			
			
			 String host = ServletUriComponentsBuilder.fromCurrentRequest().build().getHost(); 
			 String domain = "http://"+host; 
			 
			 
			 SendGrid.Email email = new SendGrid.Email();
			 email.addTo(author.getEmail());
			 email.setFrom("no_reply@"+host);
			 email.setSubject("Password changed in "+domain);
			 email.setHtml("Hi "+author.getFirstName()+",<br/>You have changed your password in "+domain+"<br/><br/>"
		 				+ "This is a system generated email, please do not reply. :)");

			 try {
				 SendGrid.Response response = sendgrid.send(email);
				 logger.info("email response status: "+response.getStatus());
				 if(response.getStatus()!=true){
					 logger.info("failed sending email"+response.getMessage());
					 return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				 }
			 } catch (SendGridException e) {
				 logger.error("error sending email", e);
				 e.printStackTrace();
				 return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			 }
		}
			
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="reset-password", method=RequestMethod.PUT)
	public ResponseEntity<Void> resetPassword(@RequestBody ResetPassword resetEmail){
		//check if email is correct
		logger.info("reset password:" +resetEmail.getEmail());
		
		Author author = blogService.findAuthorByEmail(resetEmail.getEmail());
		
		if(author==null){
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	
		}
		//perform password reset
		Login loginInfo = blogService.findLoginByUsername(resetEmail.getEmail());
		logger.info("retrieve login info:"+loginInfo.getUsername());
		
		String newPassword= "pswd"+(int)Math.ceil(Math.random()*100)+""+(int)Math.ceil(Math.random()*100);
		loginInfo.setPassword(passwordEncoder.encode(newPassword));
		blogService.updateLogin(loginInfo);
		//perform email notification
		
		SendGrid.Email email = new SendGrid.Email();


		 String host = ServletUriComponentsBuilder.fromCurrentRequest().build().getHost(); 
			
		 String domain = "http://"+host; 
		 
		email.addTo(resetEmail.getEmail());
		 email.setFrom("no_reply@"+host);
		 
		 email.setSubject("Password reset from "+domain);
		 email.setHtml("Hi "+author.getFirstName()+",<br/>Your temporary password is "+newPassword+". Please change your temporary password as soon as you can. <br/><br/>"
	 				+ "This is a system generated email, please do not reply. :)");

		 try {
			 SendGrid.Response response = sendgrid.send(email);
			 logger.info("email response status: "+response.getStatus());
			 if(response.getStatus()!=true){
				 logger.info("failed sending email"+response.getMessage());
				 return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);//change
			 }
		 } catch (SendGridException e) {
			 logger.error("error sending email", e);
			 e.printStackTrace();
			 return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);//change
		 }
		  
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="user", method=RequestMethod.GET)
	  public ResponseEntity<UserTransfer> getUser(Principal user) {
		logger.info("getUser");
		if(user!=null){
			logger.info("user:"+user.getName());
			//TODO maybe return a token instead;
			Login loginInfo =blogService.findLoginByUsername(user.getName());
			logger.info("login id:" + loginInfo.getLoginId());
			logger.info("login roles:" + loginInfo.getRoles());
		    Author author = blogService.findAuthorByEmail(user.getName());
			UserTransfer userTransfer = new UserTransfer(user.getName(),author.getAuthorId(),loginInfo.getRoles());
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
