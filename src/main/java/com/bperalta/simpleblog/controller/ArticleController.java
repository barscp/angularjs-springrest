package com.bperalta.simpleblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.data.entity.Author;
import com.bperalta.simpleblog.service.BlogService;
import com.bperalta.simpleblog.transfer.CategoryTransfer;


@RestController
@RequestMapping(value="articles")
public class ArticleController {
	Logger logger=LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private BlogService blogService;

	private static final int PAGE_COUNT =5;
	
	//TODO implement pagenation
	@RequestMapping(method=RequestMethod.GET)
	public List<Article> getRecentArticles(){
		List<Article> articles= blogService.getArticles(1, 5);
		logger.info("get all articles");
		return articles;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Article> get(@PathVariable("id") Long id){
		logger.info("get article by id:"+id);
		Article form = blogService.findArticle(id).get();
		//blogService.findArticle(id).
				//.orElseThrow(()->new ArticleNotFoundException(id));
		
		return new ResponseEntity<Article>(form,HttpStatus.OK);
		
	}
	
    
	@RequestMapping(value="search/{type}",method=RequestMethod.GET)
	public ResponseEntity<List<Article>> searchArticlesByType(@PathVariable("type") String type,@RequestParam(value="category", required=false) String category, @RequestParam(value="page", required=false) int page){
		logger.info("search articles by type:"+type);
		logger.info("query paramter: "+category);
	
		List<Article> articleList = null;
		if(page==0){
			page=1;
		}
		int totalArticles =0;
		if(StringUtils.isEmpty(category)){
			articleList =blogService.getArticlesByType(type,page,PAGE_COUNT);
			totalArticles=blogService.countArticles(type, null);
			logger.info("count article:"+ totalArticles);
		
		}else{
			articleList = blogService.getArticlesByTypeAndCategory(type, category, page,PAGE_COUNT) ;
			totalArticles=blogService.countArticles(type, category);
			logger.info("count article with category:"+ totalArticles);
			
		}
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("CountArticles", ""+totalArticles);
		
	
		
		return new ResponseEntity<List<Article>>(articleList,headers, HttpStatus.OK);
	}



	@RequestMapping(value="{type}/categories", method=RequestMethod.GET)
	public List<CategoryTransfer> getAllCategories(@PathVariable("type") String type){
		List<CategoryTransfer> categoriesList = blogService.getCategoriesByType(type);
		logger.info("getting categories for type:" + type);
	
		return categoriesList;
		
	}
	

	
	@RequestMapping(value="author/{id}",method=RequestMethod.GET)
	public ResponseEntity<Author> getAuthor(@PathVariable("id") Long authorId){
		logger.info("getting author with id"+ authorId);
		Author author = blogService.findAuthor(authorId);
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}
	
	

}
