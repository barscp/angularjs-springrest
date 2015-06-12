package com.bperalta.simpleblog.data.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.dao.AuthorDao;
import com.bperalta.simpleblog.data.entity.Article;
import com.bperalta.simpleblog.data.entity.Author;

@Repository
@Transactional
public class AuthorDaoImpl extends HibernateDaoImpl<Author, Long> implements AuthorDao {

	@Override
	public Author find(Long i) {
		return (Author)getCurrentSession().get(Author.class,i);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Author> list() {
		return getCurrentSession().createCriteria(Author.class).list();
	}


	@Override
	public Author findAuthorByEmail(String emailAddress) {
		Criteria criteria= getCurrentSession().createCriteria(Author.class);
		criteria.add(Restrictions.eq("email", emailAddress));
		criteria.list();
		@SuppressWarnings("unchecked")
		List<Author> result  =criteria.list();
		if(result!=null && result.size()>0){
			return result.get(0);
		}else return null;
	}

}
