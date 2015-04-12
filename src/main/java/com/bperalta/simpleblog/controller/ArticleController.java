package com.bperalta.simpleblog.controller;

import java.util.ArrayList;
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

import com.bperalta.simpleblog.data.Article;
import com.bperalta.simpleblog.service.BlogService;


@RestController
@RequestMapping(value="articles")
public class ArticleController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private BlogService blogService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Article article){
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
	public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Article article){
		logger.info("update article with id"+id);
		blogService.updateArticle(article);
		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
		
		headers.set("articleId", ""+id);
		logger.info(headers.getLocation().toString());
		return new ResponseEntity<Void>(null,headers,HttpStatus.OK);
	}
	
	//TODO implement pagenation
	@RequestMapping(method=RequestMethod.GET)
	public List<Article> getAll(){
		List<Article> articles= blogService.getArticles("", 0, 0);
		logger.info("get all articles");
		return articles;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Article> get(@PathVariable("id") Long id){
		logger.info("get article by id:"+id);
		Article form = blogService.findArticle(id);
		return new ResponseEntity<Article>(form,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	public String remove(@PathVariable("id") String id){
		logger.info("Delete by article");

		return "";
	}
	
	@RequestMapping(value="search/type/{searchKey}",method=RequestMethod.GET)
	public ResponseEntity<List<Article>> searchArticlesByType(@PathVariable("searchKey") String key){
		logger.info("search articles by type:"+key);
		List<Article> articleList = blogService.getArticlesByType(key);
		articleList.forEach(article ->
					logger.info("title"+article.getTitle())
				);
		return new ResponseEntity<List<Article>>(articleList, HttpStatus.OK);
	}
	@RequestMapping(value="search/category/{searchKey}",method=RequestMethod.GET)
	public String searchArticlesByCategory(@PathVariable("searchKey") String key){
		logger.info("search articles by category:"+key);
		return "searching by tag";
	}
	@RequestMapping(value="search/keyword/{searchKey}",method=RequestMethod.GET)
	public String searchArticlesByKeyword(@PathVariable("searchKey") String key){
		//search by type?
		logger.info("search articles by keyword:"+key);
		
		return "searching by keyword";
	}
	@RequestMapping(value="{type}/categories", method=RequestMethod.GET)
	public List<String> getAllCategories(@PathVariable("type") String type){
		List<String> categoriesList = blogService.getCategoriesByType(type);
		logger.info("type:" + type);
		categoriesList.forEach(category ->
		logger.info("category: "+category));
		return categoriesList;
		
	}
}
