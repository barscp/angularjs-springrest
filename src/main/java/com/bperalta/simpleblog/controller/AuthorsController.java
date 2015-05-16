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

@RestController
@RequestMapping(value="authors")
public class AuthorsController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);


	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BlogService blogService;
	
	//TODO update the implementation of add user
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Author> save(@RequestBody Author author ){
		logger.info("saving author...");
		blogService.saveAuthor(author);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	//TODO save article should be here at the author
	@RequestMapping(value="{authorId}/article", method=RequestMethod.POST)
	public ResponseEntity<?> saveArticle(Principal user,@PathVariable("authorId") Long authorId, @RequestBody Article article){
		logger.info("saving article...");
		logger.info("user:"+ user.getName());
		Author author = blogService.findAuthor(authorId);
		article.setAuthor(author);
	
		//validated author
		//get author by name
		//check if author id is the same as the loginid
		//if yes then perform save article.
		Long articleId = blogService.saveArticle(article);//must return the id
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(articleId).toUri());
		headers.set("articleId", ""+articleId);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<Void>(null,headers,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="{authorId}/article/{articleId}",method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("authorId") Long authorId, @PathVariable("articleId") Long articleId, @RequestBody Article article){
		logger.info("update article with id"+articleId+" for user with id "+authorId);
		//validate author
		//validate article
		//Author
		blogService.updateArticle(article);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
		
		headers.set("articleId", ""+articleId);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<>(null,headers,HttpStatus.OK);
	}
	
	@RequestMapping(value="{authorId}/article/{articleId}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteArticle(@PathVariable("authorId") Long authorId, @PathVariable("articleId") Long articleId, @RequestBody Article article){
		
		return new ResponseEntity<>(null,null,HttpStatus.NOT_IMPLEMENTED);
	}
	

	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public String getAll(){
//		Login login = new Login();
//		login.setIsActive("y");
//		login.setIsBanned("n");
//		login.setPassword("password");
//		login.setUsername("bars_cp");
//		blogService.saveLogin(login);
//		
//		
		Login user = new Login("cocoy", passwordEncoder.encode("password"));
		user.addRole("ADMIN");
		user.addRole("USER");
		blogService.saveUser(user);
		
		Author author = new Author();
		author.setFirstName("Robert Benedict");
		author.setLastName("Peralta");
		author.setUserLogin(user);
		author.setEmail("bars_cp@yahoo.com");
		author.setWebsiteUrl("https://sg.linkedin.com/in/barryperalta");
		author.setDescription("I'm a cute lil baby boy");
		author.setProfileImgUrl("http://i.imgur.com/tAlPtC4.jpg");
	    blogService.saveAuthor(author);		
//		logger.info("init author id:"+author.getAuthorId());
//
//		logger.info("init login id:"+login.getLoginId());
//	
		//User user = new User("cocoy", "password");
	
		//User user1 = new User("barry", "password");
		Login user1 = new Login("barry", passwordEncoder.encode("password"));
		user1.addRole("USER");
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
//		userDao.save(userUser);
		
		return "init at get"+author.getFirstName();
		
	
	}
	
	@RequestMapping(value="{authorId}",method=RequestMethod.GET)
	public ResponseEntity<Author> get(@PathVariable("authorId") Long authorId){
		logger.info("getting author with id"+ authorId);
		Author author = blogService.findAuthor(authorId);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	
	
}
