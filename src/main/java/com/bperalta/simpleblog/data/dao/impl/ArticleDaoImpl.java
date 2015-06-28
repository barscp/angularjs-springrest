package com.bperalta.simpleblog.data.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bperalta.simpleblog.data.dao.ArticleDao;
import com.bperalta.simpleblog.data.entity.Article;


/**
 * @author barryperalta
 *
 */
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
		criteria.add(Restrictions.eq("isPublished", "Y"));
		criteria.setFirstResult((pageNumber-1)*pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.desc("articleId"));
		return criteria.list();
	}

	@Override
	public int countArticles(String type, String category) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(article_id) from Article where is_published ='Y'");
		if(!StringUtils.isEmpty(type)){
			sql.append("and type = :type ");
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> searchArticles(String searchKey, Integer pageNumber,
			Integer pageSize) {
		Criteria criteria= getCurrentSession().createCriteria(Article.class);
		
		if("unpublished".equals(searchKey)){
			criteria.add(Restrictions.ne("isPublished", "Y"));
			
		}else{
			Criterion title = Restrictions.ilike("title", searchKey, MatchMode.ANYWHERE);
			Criterion category = Restrictions.ilike("category", searchKey, MatchMode.ANYWHERE);
			Criterion description = Restrictions.ilike("description", searchKey,MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(title,category,description));
			criteria.add(Restrictions.eq("isPublished", "Y"));
		}
		criteria.setFirstResult((pageNumber-1)*pageSize);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.desc("articleId"));
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public int countSearchArticles(String searchKey) {
		Criteria criteria= getCurrentSession().createCriteria(Article.class);
		criteria.setProjection(Projections.rowCount());
		if("unpublished".equals(searchKey)){
			criteria.add(Restrictions.ne("isPublished", "Y"));
			
		}else{
			Criterion title = Restrictions.ilike("title", searchKey, MatchMode.ANYWHERE);
			Criterion category = Restrictions.ilike("category", searchKey, MatchMode.ANYWHERE);
			Criterion description = Restrictions.ilike("description", searchKey,MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(title,category,description));
			criteria.add(Restrictions.eq("isPublished", "Y"));
		}
		List<Long> result  =criteria.list();
		return result.get(0).intValue();
	}





}
