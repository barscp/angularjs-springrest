package com.bperalta.simpleblog.data.dao;


import java.util.Optional;

import com.bperalta.simpleblog.data.entity.Login;


public interface LoginDao extends BaseDao<Login, Long>{//, UserDetailsService{

	public Optional<Login> findByName(String name);

}