package com.bperalta.simpleblog.data.dao;

import com.bperalta.simpleblog.data.entity.Author;

/**
 * @author barryperalta
 *
 */
public interface AuthorDao extends BaseDao<Author, Long> {
	public Author findAuthorByEmail(String emailAddress);
}
