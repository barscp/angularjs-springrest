package com.bperalta.simpleblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bperalta.simpleblog.data.Author;
import com.bperalta.simpleblog.data.Login;
import com.bperalta.simpleblog.service.BlogService;

@RestController
@RequestMapping(value="authors")
public class AuthorsController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private BlogService blogService;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Author> save(@RequestBody Author author ){
		logger.info("saving author...");
		blogService.saveAuthor(author);
		return new ResponseEntity<Author>(author,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public String update(){
		return "";
	}
	
	@RequestMapping(value="init",method=RequestMethod.GET)
	public String getAll(){
		Login login = new Login();
		login.setIsActive("y");
		login.setIsBanned("n");
		login.setPassword("password");
		login.setUsername("bars_cp");
		blogService.saveLogin(login);
		
		
		Author author = new Author();
		author.setFirstName("Kopi");
		author.setLastName("C");
		author.setUserLogin(login);
		author.setEmail("bars_cp@yahoo.com");
	    blogService.saveAuthor(author);		
		logger.info("init author id:"+author.getAuthorId());

		logger.info("init login id:"+login.getLoginId());
		return "init at get"+author.getFirstName();
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public String get(){
		return "";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String remove(){
		return "";
	}
	
	@RequestMapping(value="{id}/blogs",method=RequestMethod.GET)
	public String getAllBlogs(){
		return "";
	}
	
}
