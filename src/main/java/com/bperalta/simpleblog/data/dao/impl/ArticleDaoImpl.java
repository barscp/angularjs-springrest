package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bperalta.simpleblog.data.Article;
import com.bperalta.simpleblog.data.dao.ArticleDao;

@Repository
public class ArticleDaoImpl extends HibernateDaoImpl<Article, Long> implements ArticleDao {

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticlesByType(String type) {
		Query query = getCurrentSession().createQuery("from Article a where a.type = :type ");
		query.setParameter("type", type);
	
		return query.list();
	}

	@Override
	public List<Article> searchArticlesByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Article find(Long i) {
		return (Article)getCurrentSession().get(Article.class,i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list() {
		return getCurrentSession().createCriteria(Article.class).list();
	}

}
