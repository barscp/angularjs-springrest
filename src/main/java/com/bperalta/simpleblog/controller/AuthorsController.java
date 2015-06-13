package com.bperalta.simpleblog.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;







//import com.bperalta.simpleblog.data.dao.UserDao;
import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.data.entity.Author;
//import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.data.entity.Login;
//import com.bperalta.simpleblog.data.entity.User;
import com.bperalta.simpleblog.service.BlogService;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@RestController
@RequestMapping(value="authors")
public class AuthorsController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@Autowired 
	SendGrid sendgrid;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="{authorId}",method=RequestMethod.GET)
	public ResponseEntity<Author> getAuthor(@PathVariable("authorId") Long authorId){
		logger.info("getting author with id"+ authorId);
		Author author = blogService.findAuthor(authorId);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Author> save(Principal user, @RequestBody Author author ){
		logger.info("saving author...");
		logger.info("user:"+ user.getName());
		Long authorId=blogService.saveAuthor(author);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(authorId).toUri());
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	@RequestMapping(value="{authorId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateAuthor(Principal user,@PathVariable("authorId") Long authorId, @RequestBody Author author){
		logger.info("update author with authorId:"+authorId);
		logger.info("user:"+ user.getName());
		//validate author
		//validate article
		//Author
		blogService.updateAuthor(author);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
	
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<>(null,headers,HttpStatus.OK);
	}
	
	//TODO save article should be here at the author
	@RequestMapping(value="{authorId}/article", method=RequestMethod.POST)
	public ResponseEntity<?> saveArticle(Principal user,@PathVariable("authorId") Long authorId, @RequestBody Article article){
		logger.info("saving article...");
		logger.info("user:"+ user.getName());
		Author author = blogService.findAuthor(authorId);
		article.setAuthor(author);
		article.setIsPublished("N");
	
		//validated author
		//get author by name
		//check if author id is the same as the loginid
		//if yes then perform save article.
		Long articleId = blogService.saveArticle(article);//must return the id
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(articleId).toUri());
		//headers.set("articleId", ""+articleId);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<Void>(null,headers,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="{authorId}/article/{articleId}",method=RequestMethod.PUT)
	public ResponseEntity<?> updateArticle(@PathVariable("authorId") Long authorId, @PathVariable("articleId") Long articleId, @RequestBody Article article){
		logger.info("update article with id"+articleId+" for user with id "+authorId);
		//validate author
		//validate article
		//Author
		blogService.updateArticle(article);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
		
		//headers.set("articleId", ""+articleId);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<>(null,headers,HttpStatus.OK);
	}
	
	@RequestMapping(value="{authorId}/article/{articleId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteArticle(@PathVariable("authorId") Long authorId, @PathVariable("articleId") Long articleId, @RequestBody Article article){
		
		return new ResponseEntity<>(null,null,HttpStatus.NOT_IMPLEMENTED);
	}
	

	@RequestMapping(value="create/{email}/{firstName}", method=RequestMethod.POST)
	public  ResponseEntity<Void>  createUser( @PathVariable("email") String email,@PathVariable("firstName") String firstName ){
	
		logger.info("creating user email"+email);
		logger.info("creating user firstName"+firstName);
		
		//check username
		
		//check email
		
		//generate password
		//insert record
		
		//send email
		String newPassword= "pswd"+(int)Math.ceil(Math.random()*100)+""+(int)Math.ceil(Math.random()*100);
		Login user = new Login(email, passwordEncoder.encode(newPassword));
		user.addRole("USER");
		blogService.saveUser(user);
		Author author = new Author();
		author.setFirstName(firstName);
		author.setUserLogin(user);
		author.setEmail(email);
		blogService.saveAuthor(author);	
		
		String host = ServletUriComponentsBuilder.fromCurrentRequest().build().getHost(); 
		String domain = "http://"+host; 
		SendGrid.Email emailer = new SendGrid.Email();
		emailer.addTo(email);
		emailer.setFrom("no_reply@"+host);
		emailer.setSubject("Account has been created on "+domain);
		emailer.setHtml("Hi "+author.getFirstName()+",<br/> Your temporary password is "+newPassword
		 		+ "<br/> Please update your profile info and change your temporary password as soon as you can. Logon to "+domain+"<br/><br/>"
		 				+ "This is a system generated email, please do not reply. :)");

		 try {
			 SendGrid.Response response = sendgrid.send(emailer);
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
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public String getAll(){

		Login user1 = new Login("bars_cp@yahoo.com", passwordEncoder.encode("password"));
		user1.addRole("ADMIN");
		blogService.saveUser(user1);
		
		Author author1 = new Author();
		author1.setFirstName("Barry");
		author1.setLastName("Peralta");
		author1.setUserLogin(user1);
		author1.setEmail("bars_cp@yahoo.com");
		author1.setWebsiteUrl("https://sg.linkedin.com/in/barryperalta");
		author1.setDescription("Java Developer from Singapore");
		author1.setProfileImgUrl("http://i.imgur.com/Pw6naNJ.jpg");
	    blogService.saveAuthor(author1);		

		return "init";
		
	
	}
	


	
	
}
