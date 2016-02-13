package com.bperalta.simpleblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.service.BlogService;

/**
 * @author barryperalta
 *
 */
@Controller
public class SocialMediaScrapperCtrl {
	Logger logger=LoggerFactory.getLogger(SocialMediaScrapperCtrl.class);
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping(value ="scrapper/article/{id}")
	public String getMetaAndRedirect(@RequestHeader(value="User-Agent") String userAgent, @PathVariable("id") Long articleId, Model model){
		logger.info("user agent:"+userAgent);
		logger.info("article id" +articleId);
		String url = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri().toString();
		logger.info("url "+url);
		if(!userAgent.contains("facebook") && !userAgent.contains("Google")){
			String redirectUrl="/index.html#/article/"+articleId+"/";
			//redirect here;
			return "redirect:" + redirectUrl;
		}
		
		
		
		Article article = blogService.findArticle(articleId).get();
		model.addAttribute("title", article.getTitle());
		model.addAttribute("description", article.getDescription());
		model.addAttribute("image", article.getImageUrl());
		model.addAttribute("url", url); //how to get current host name?
		model.addAttribute("author", article.getAuthor().getFirstName()+" "+article.getAuthor().getLastName());
		return "socialMediaScrapper";
	}
}
