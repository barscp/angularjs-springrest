package com.bperalta.simpleblog.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.bperalta.simpleblog.form.ArticleForm;

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
	public List<ArticleForm> getArticlesByType(String type) {
		List<Article> articleList= articleDao.getArticlesByType(type);
		List<ArticleForm> articleFormList =new ArrayList<ArticleForm>(); 
		if(articleList!=null)
			for(Article article: articleList){
			  ArticleForm form = Util.convertToForm(article);
			  articleFormList.add(form);
			}
		return articleFormList;
	
	}

	@Override
	public Author saveAuthor(Author author) {
		authorDao.save(author);
		return authorDao.find(author.getAuthorId());
	}
	

	@Override
	public Long saveArticle(ArticleForm articleForm) {
		
		Author author = new Author();
		author.setAuthorId(1);
		Article article= Util.covertToDto(articleForm);
		article.setAuthor(author);//remove soon
		article.setDateCreated(new Date(System.currentTimeMillis()));
		articleDao.save(article);//new article id is created upon save?
		
		return article.getArticleId();
		
	}
	@Override
	public void updateArticle(ArticleForm articleForm) {
		Author author = new Author();
		author.setAuthorId(1);
		Article article= Util.covertToDto(articleForm);
		article.setAuthor(author);//remove soon
		article.setModifiedDate(new Date(System.currentTimeMillis()));
		articleDao.update(article);
		
		
	}

	@Override
	public Login saveLogin(Login login) {
		loginDao.save(login);
		return loginDao.find(login.getLoginId());
	}

	@Override
	public Author findAuthor(Long i) {
		return authorDao.find(i);
	}

	@Override
	public ArticleForm findArticle(Long i) {
		Article article= articleDao.find(i);
		ArticleForm articleForm = Util.convertToForm(article);
		return articleForm;
	}


	

}
