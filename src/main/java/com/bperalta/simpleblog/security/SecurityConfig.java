package com.bperalta.simpleblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	} 
	//angular automatically passes cookies only on same domain
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// To prevent pop-up for Httpbasic authentication
		CustomBasicAuthenticationEntryPoint ce = new CustomBasicAuthenticationEntryPoint("Spring boot");
		http.httpBasic().authenticationEntryPoint(ce);
		
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/authors/init","/index.html","/assets/**","/app/**","/articles/**").permitAll()
				.anyRequest().authenticated()
				.and().logout().permitAll()
				.and()//.csrf().disable(); //disable for cross domain
				//CSRF does not work on cross domain?
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository());
			
	}
    //angular automatically pass X-XSRF-TOKEN ONLY on same domain
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
	
		auth.inMemoryAuthentication().withUser("bars").password("password")
				.roles("USER");
		
	
	}
}
