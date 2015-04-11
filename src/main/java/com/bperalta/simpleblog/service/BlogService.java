package com.bperalta.simpleblog.service;

import java.util.List;

import com.bperalta.simpleblog.data.Author;
import com.bperalta.simpleblog.data.Login;
import com.bperalta.simpleblog.form.ArticleForm;

public interface BlogService {

	public List<ArticleForm> getArticlesByType(String type);
	public Author saveAuthor(Author author);
	public Login saveLogin(Login login);
	public Author findAuthor(Long i);
	public ArticleForm findArticle(Long i);
	public Long saveArticle(ArticleForm article);
	public void updateArticle(ArticleForm article);
	
}
