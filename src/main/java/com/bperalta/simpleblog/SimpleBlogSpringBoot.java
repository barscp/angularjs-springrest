package com.bperalta.simpleblog;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.bperalta.simpleblog.data.dao.ArticleDao;
import com.bperalta.simpleblog.data.dao.AuthorDao;
//import com.bperalta.simpleblog.data.dao.UserDao;
//import com.bperalta.simpleblog.data.entity.User;
//import com.bperalta.simpleblog.security.SecurityConfig;

@SpringBootApplication
//@Import(ApplicationConfig.class)
//@Import({SecurityConfig.class})
@ImportResource("/com/bperalta/simpleblog/applicationContext.xml")
public class SimpleBlogSpringBoot {
	
	
	Logger logger=LoggerFactory.getLogger(SimpleBlogSpringBoot.class);

	public static void main(String args[]){
		
		SpringApplication.run(SimpleBlogSpringBoot.class, args);
	}
	
	@Bean
	CommandLineRunner init(AuthorDao authorDao,
			ArticleDao articleDao) {
		return (evt) -> Arrays.asList(
				"user,admin".split(","))
				.forEach(
						a -> {
							logger.info("list:"+a);

//							User userUser = new User(a, this.passwordEncoder.encode(a));
//							userUser.addRole(a);
//							this.userDao.save(userUser);

							

						});
	}

	
	
//	@Bean
//	FilterRegistrationBean corsFilter(
//			@Value("${tagit.origin:http://localhost:8000}") String origin) {
//		return new FilterRegistrationBean(new Filter() {
//			public void doFilter(ServletRequest req, ServletResponse res,
//					FilterChain chain) throws IOException, ServletException {
//				HttpServletRequest request = (HttpServletRequest) req;
//				HttpServletResponse response = (HttpServletResponse) res;
//				String method = request.getMethod();
//				// this origin value could just as easily have come from a database
//				response.setHeader("Access-Control-Allow-Origin", origin);
//				response.setHeader("Access-Control-Allow-Methods",
//						"POST,GET,OPTIONS,DELETE");
//				response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
//				response.setHeader("Access-Control-Allow-Credentials", "true");
//				response.setHeader(
//						"Access-Control-Allow-Headers",
//						"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//				if ("OPTIONS".equals(method)) {
//					response.setStatus(HttpStatus.OK.value());
//				}
//				else {
//					chain.doFilter(req, res);
//				}
//			}
//
//			public void init(FilterConfig filterConfig) {
//			}
//
//			public void destroy() {
//			}
//		});
//	}

}
