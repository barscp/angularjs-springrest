package com.bperalta.simpleblog.data.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.dao.UtilDao;
import com.bperalta.simpleblog.transfer.CategoryTransfer;

@Repository
@Transactional
public class UtilDaoImpl implements UtilDao {


	@Autowired
	protected SessionFactory sessionFactory;
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryTransfer> getCategoriesByType(String type){
		Query query =getCurrentSession().createSQLQuery("select category, count(article_id) from Article where type = :type group by category");
		query.setParameter("type", type);
		List<Object[]> rows  =query.list();
		return rows.stream().map(data -> new CategoryTransfer(data[0].toString(),data[1].toString())).collect(Collectors.toList());
	}
	

}
