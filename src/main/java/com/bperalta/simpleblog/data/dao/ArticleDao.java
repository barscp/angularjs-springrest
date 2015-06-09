package com.bperalta.simpleblog.data.dao;

import java.util.List;

import com.bperalta.simpleblog.data.entity.Article;

public interface ArticleDao extends BaseDao<Article, Long> {

	public List<Article> getArticles(String type, String category, Integer pageNumber, Integer pageSize);
    public int countArticles(String type, String category);
    
    public List<Article> searchArticles(String searchKey, Integer pageNumber, Integer pageSize);
    public int countSearchArticles(String searchKey);
}
