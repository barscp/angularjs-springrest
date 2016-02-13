package com.bperalta.simpleblog.data.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bperalta.simpleblog.data.dao.UtilDao;
import com.bperalta.simpleblog.transfer.CategoryTransfer;

/**
 * @author barryperalta
 *
 */
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
		Query query =getCurrentSession().createSQLQuery("select category, count(article_id) from Article where is_published='Y' and type = :type group by category");
		query.setParameter("type", type);
		List<Object[]> rows  =query.list();
	//	return rows.stream().map(data -> new CategoryTransfer(data[0].toString(),data[1].toString())).collect(Collectors.toList());
		List<CategoryTransfer> transfer = new ArrayList<CategoryTransfer>();
		Iterator<Object[]> iter = rows.iterator();
		while(iter.hasNext()){
			Object[] row = iter.next();
			CategoryTransfer trans = new CategoryTransfer(row[0].toString(),row[1].toString());
			transfer.add(trans);
		}
		
		return transfer;
	}
	

}
