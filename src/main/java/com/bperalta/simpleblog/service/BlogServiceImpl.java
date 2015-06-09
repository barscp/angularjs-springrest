package com.bperalta.simpleblog.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.dao.ArticleDao;
import com.bperalta.simpleblog.data.dao.AuthorDao;
//import com.bperalta.simpleblog.data.dao.LoginDao;
import com.bperalta.simpleblog.data.dao.LoginDao;
import com.bperalta.simpleblog.data.dao.UtilDao;
//import com.bperalta.simpleblog.data.dao.UserDao;
import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.data.entity.Author;
//import com.bperalta.simpleblog.data.entity.Login;
//import com.bperalta.simpleblog.data.entity.User;
import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.transfer.CategoryTransfer;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private AuthorDao authorDao;
	
//	@Autowired
//	private LoginDao loginDao;
	
	@Autowired
	private LoginDao userDao;
	
	@Autowired
	private UtilDao utilDao;
	   
	 
	
	
	
	@Override
	public List<CategoryTransfer> getCategoriesByType(String type){
		List<CategoryTransfer> categoriesList = utilDao.getCategoriesByType(type);
		return categoriesList;
	}

	@Override
	public Long saveAuthor(Author author) {
		
		authorDao.save(author);
		return author.getAuthorId();
	}
	

	@Override
	public Long saveArticle(Article article) {
		
		article.setDateCreated(new Date(System.currentTimeMillis()));
		articleDao.save(article);//new article id is created upon save? yes
		
		return article.getArticleId();
		
	}
	@Override
	public void updateArticle(Article article) {
	
		article.setDateModified(new Date(System.currentTimeMillis()));
		articleDao.update(article);
		
		
	}

//	@Override
//	public Long saveLogin(Login login) {
//		loginDao.save(login);
//		return login.getLoginId();
//	}

	@Override
	public Author findAuthor(Long i) {
		return authorDao.find(i);
	}

	@Override
	public Optional<Article> findArticle(Long i) {
		Article article= articleDao.find(i);
		Optional<Article> art=Optional.of(article);
		return art;
	}

	
	@Override
	public void saveUser(Login user) {
		userDao.save(user);
		
	}

	@Override
	public Login findLoginByUsername(String username) {
		return userDao.findByName(username).get();
		
	}
	public List<Article> getArticles(Integer pageNumber, Integer pageSize){
		return articleDao.getArticles(null, null, pageNumber, pageSize);
	}
	public List<Article> getArticlesByType(String type, Integer pageNumber, Integer pageSize){
		return articleDao.getArticles(type, null, pageNumber, pageSize);
	
	}
	public List<Article> getArticlesByTypeAndCategory(String type, String category, Integer pageNumber, Integer pageSize){
		return articleDao.getArticles(type, category, pageNumber, pageSize);
	
		
	}
	
	public int countArticles(String type, String category) {
		return articleDao.countArticles(type, category);
	}

	@Override
	public List<Article> searchArticles(String searchKey, Integer pageNumber,
			Integer pageSize) {
		return articleDao.searchArticles(searchKey, pageNumber, pageSize);
	}

	@Override
	public int countSearchArticles(String searchKey) {
		return articleDao.countSearchArticles(searchKey);
	}

	@Override
	public void updateAuthor(Author author) {
	      authorDao.update(author);
		
	}

	

	

}
