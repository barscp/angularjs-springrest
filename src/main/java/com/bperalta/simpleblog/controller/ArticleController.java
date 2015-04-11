package com.bperalta.simpleblog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bperalta.simpleblog.form.ArticleForm;
import com.bperalta.simpleblog.service.BlogService;


@RestController
@RequestMapping(value="articles")
public class ArticleController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private BlogService blogService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody ArticleForm article){
		logger.info("saving article...");
		Long articleId = blogService.saveArticle(article);//must return the id
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(articleId).toUri());
		headers.set("articleId", ""+articleId);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<Void>(null,headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="publish/{id}",method=RequestMethod.PATCH)
	public String publish(@PathVariable("id") String id){
		return "";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody ArticleForm articleForm){
		logger.info("update article with id"+id);
		blogService.updateArticle(articleForm);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
		
		headers.set("articleId", ""+id);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<Void>(null,headers,HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAll(){
		logger.info("get all articles");
		return "";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<ArticleForm> get(@PathVariable("id") Long id){
		logger.info("get article by id:"+id);
		ArticleForm form = blogService.findArticle(id);
		return new ResponseEntity<ArticleForm>(form,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id") String id){
		logger.info("Delete by article");

		return "";
	}
	
	@RequestMapping(value="search/type/{searchKey}",method=RequestMethod.GET)
	public ResponseEntity<List<ArticleForm>> searchArticlesByType(@PathVariable("searchKey") String key){
		logger.info("search articles by type:"+key);
		List<ArticleForm> articleList = blogService.getArticlesByType(key);
		articleList.forEach(article ->
					logger.info("title"+article.getTitle())
				);
//		for(ArticleForm article:articleList){
//			logger.info("title:"+article.getTitle());
//				
//		}
//		
		return new ResponseEntity<List<ArticleForm>>(articleList, HttpStatus.OK);
	}
	@RequestMapping(value="search/tag/{searchKey}",method=RequestMethod.GET)
	public String searchArticlesByTag(@PathVariable("searchKey") String key){
		logger.info("search articles by tag:"+key);
		return "searching by tag";
	}
	@RequestMapping(value="search/keyword/{searchKey}",method=RequestMethod.GET)
	public String searchArticlesByKeyword(@PathVariable("searchKey") String key){
		//search by type?
		logger.info("search articles by keyword:"+key);
		
		return "searching by keyword";
	}
	
}
