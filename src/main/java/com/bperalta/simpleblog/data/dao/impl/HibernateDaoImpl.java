package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bperalta.simpleblog.data.dao.BaseDao;

/**
 * @author barryperalta
 *
 * @param <E>
 * @param <I>
 */
public abstract class HibernateDaoImpl<E,I> implements BaseDao<E, I>{

	@Autowired
	protected SessionFactory sessionFactory;
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	
	}
	
	@Override
	public void save(E e) {
		getCurrentSession().save(e);
		
	}

	@Override
	public void update(E e) {
		getCurrentSession().saveOrUpdate(e);		
	}

	@Override
	public void delete(E e) {
		getCurrentSession().delete(e);
		
	}

	@Override
	public abstract E find(I i);

	@Override
	public abstract List<E> list();

	

}
