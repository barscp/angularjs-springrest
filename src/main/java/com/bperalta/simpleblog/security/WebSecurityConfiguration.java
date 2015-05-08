package com.bperalta.simpleblog.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bperalta.simpleblog.data.dao.LoginDao;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	LoginDao userDao;

	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
	
	}
   
	@Bean
	UserDetailsService userDetailsService() {
		return (username) -> userDao
				.findByName(username)
				.map(a -> new User(a.getUsername(), a.getPassword(), true, true, true, true,
						a.getAuthorities()))
				.orElseThrow(
						() -> new UsernameNotFoundException("could not find the user '"
								+ username + "'"));
	}
}