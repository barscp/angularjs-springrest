package com.bperalta.simpleblog.data.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bperalta.simpleblog.data.dao.ArticleDao;
import com.bperalta.simpleblog.data.entity.Article;


@Repository
public class ArticleDaoImpl extends HibernateDaoImpl<Article, Long> implements ArticleDao {

	
	@Override
	public Article find(Long i) {
		return (Article)getCurrentSession().get(Article.class,i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> list() {
		return getCurrentSession().createCriteria(Article.class).list();
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticles(String type, String category,Integer pageNumber, Integer pageSize) {
		Criteria criteria= getCurrentSession().createCriteria(Article.class);
		if(!StringUtils.isEmpty(type)){
			criteria.add(Restrictions.eq("type", type));
		}
		if(!StringUtils.isEmpty(category)){
			criteria.add(Restrictions.eq("category", category));
		}
		criteria.setFirstResult((pageNumber-1)*pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.desc("articleId"));
		return criteria.list();
	}

	@Override
	public int countArticles(String type, String category) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(article_id) from Article ");
		if(!StringUtils.isEmpty(type)){
			sql.append("where type = :type ");
			if(!StringUtils.isEmpty(category)){
				sql.append("and category = :category");
			}
		}
		Query query =getCurrentSession().createSQLQuery(sql.toString());
		
		if(!StringUtils.isEmpty(type)){

			query.setParameter("type", type);
			if(!StringUtils.isEmpty(category)){
				query.setParameter("category", category);
			}
		}
		
	
		@SuppressWarnings("unchecked")
		List<BigInteger> result  =query.list();
		return result.get(0).intValue();
	}




}
