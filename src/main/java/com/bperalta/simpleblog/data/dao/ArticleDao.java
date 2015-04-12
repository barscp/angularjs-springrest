package com.bperalta.simpleblog.data.dao;

import java.util.List;

import com.bperalta.simpleblog.data.Article;

public interface ArticleDao extends BaseDao<Article, Long> {

	public List<Article> getArticlesByType(String type);
	
	public List<Article> searchArticlesByTag(String tag);
	
	public List<String> getCategoriesByType(String type);
	
 
}
