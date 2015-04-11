package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.Author;
import com.bperalta.simpleblog.data.dao.AuthorDao;

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

}
