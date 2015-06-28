package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.dao.LoginDao;
import com.bperalta.simpleblog.data.entity.Login;

/**
 * @author barryperalta
 *
 */
@Repository
@Transactional
public class LoginDaoImpl  extends HibernateDaoImpl<Login, Long>implements LoginDao{

	Logger logger=LoggerFactory.getLogger(LoginDaoImpl.class);


	@Override
	public Optional<Login> findByName(String name) {
		logger.info("get user by name: "+name);
		Query query = getCurrentSession().createQuery("from Login a where a.username = :name ");
		query.setParameter("name", name);
		@SuppressWarnings("unchecked")
		List<Login> userList =  query.list();
		if(userList!=null){
			return Optional.of(userList.get(0));
		}else return null;
	}


	@Override
	public Login find(Long i) {
		return (Login)getCurrentSession().get(Login.class,i);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Login> list() {
		return getCurrentSession().createCriteria(Login.class).list();
	}


}
