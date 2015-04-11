package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.Login;
import com.bperalta.simpleblog.data.dao.LoginDao;

@Repository
@Transactional
public class LoginDaoImpl extends HibernateDaoImpl<Login, Long> implements LoginDao{

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
