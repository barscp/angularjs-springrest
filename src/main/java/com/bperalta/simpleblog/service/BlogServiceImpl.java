package com.bperalta.simpleblog.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.Article;
import com.bperalta.simpleblog.data.Author;
import com.bperalta.simpleblog.data.Login;
import com.bperalta.simpleblog.data.dao.ArticleDao;
import com.bperalta.simpleblog.data.dao.AuthorDao;
import com.bperalta.simpleblog.data.dao.LoginDao;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public List<Article> getArticlesByType(String type) {
		List<Article> articleList= articleDao.getArticlesByType(type);
	
		return articleList;
		
	}
	
	@Override
	public List<String> getCategoriesByType(String type){
		List<String> categoriesList = articleDao.getCategoriesByType(type);
		return categoriesList;
	}

	@Override
	public Long saveAuthor(Author author) {
		authorDao.save(author);
		return author.getAuthorId();
	}
	

	@Override
	public Long saveArticle(Article article) {
		
		Author author = new Author();
		author.setAuthorId(1);
		article.setAuthor(author);//remove soon
		article.setDateCreated(new Date(System.currentTimeMillis()));
		articleDao.save(article);//new article id is created upon save?
		
		return article.getArticleId();
		
	}
	@Override
	public void updateArticle(Article article) {
		Author author = new Author();
		author.setAuthorId(1);
		article.setAuthor(author);//remove soon
		article.setDateModified(new Date(System.currentTimeMillis()));
		articleDao.update(article);
		
		
	}

	@Override
	public Long saveLogin(Login login) {
		loginDao.save(login);
		return login.getLoginId();
	}

	@Override
	public Author findAuthor(Long i) {
		return authorDao.find(i);
	}

	@Override
	public Article findArticle(Long i) {
		Article article= articleDao.find(i);
		
		return article;
	}

	@Override
	public List<Article> getArticles(String type, int start, int size) {
		// TODO Auto-generated method stub
		return articleDao.list();
	}


	

}
