package com.bperalta.simpleblog.service;

import java.util.List;
import java.util.Optional;

//import org.springframework.security.core.userdetails.UserDetails;







import org.springframework.security.access.prepost.PreAuthorize;

import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.data.entity.Author;
//import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.data.entity.Login;
import com.bperalta.simpleblog.transfer.CategoryTransfer;

public interface BlogService {

	public List<Article> getArticles(Integer pageNumber, Integer pageSize);
	public List<Article> getArticlesByType(String type, Integer pageNumber, Integer pageSize);
	public List<Article> getArticlesByTypeAndCategory(String type, String category, Integer pageNumber, Integer pageSize);

	public int countArticles(String type, String category);
	public Long saveAuthor(Author author);
	//public Long saveLogin(Login login);
	public Author findAuthor(Long i);
	public Optional<Article> findArticle(Long i);
	
	public Long saveArticle(Article article);
	public void updateArticle(Article article);
	public List<CategoryTransfer> getCategoriesByType(String type);
	public void saveUser(Login user);
	public Login findLoginByUsername(String username);
}
