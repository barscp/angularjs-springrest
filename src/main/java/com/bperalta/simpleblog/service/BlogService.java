package com.bperalta.simpleblog.service;

import java.util.List;

import com.bperalta.simpleblog.data.Article;
import com.bperalta.simpleblog.data.Author;
import com.bperalta.simpleblog.data.Login;

public interface BlogService {

	public List<Article> getArticlesByType(String type);
	public List<Article> getArticles(String type, int start, int size);
	public Long saveAuthor(Author author);
	public Long saveLogin(Login login);
	public Author findAuthor(Long i);
	public Article findArticle(Long i);
	public Long saveArticle(Article article);
	public void updateArticle(Article article);
	public List<String> getCategoriesByType(String type);
	
}
