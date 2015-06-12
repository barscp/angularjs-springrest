package com.bperalta.simpleblog.data.dao;

import com.bperalta.simpleblog.data.entity.Author;

public interface AuthorDao extends BaseDao<Author, Long> {
	public Author findAuthorByEmail(String emailAddress);
}
