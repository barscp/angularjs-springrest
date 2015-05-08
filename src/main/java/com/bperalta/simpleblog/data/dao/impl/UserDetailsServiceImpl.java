//package com.bperalta.simpleblog.data.dao.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.bperalta.simpleblog.data.dao.UserDao;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	UserDao userDao;
//		
//	@Override
//	public UserDetails loadUserByUsername(String username)
//			throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		UserDetails user = userDao.findByName(username).map(mapper);
//		return null;
//	}
//
//}
