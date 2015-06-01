package com.bperalta.simpleblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bperalta.simpleblog.data.dao.LoginDao;
import com.bperalta.simpleblog.data.entity.Login;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private LoginDao loginDao;
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		Login login=	loginDao.findByName(userName).get();
		User user = new  User(login.getUsername(), login.getPassword(), true, true, true, true,	login.getAuthorities());
			
		return user;
	}

}
