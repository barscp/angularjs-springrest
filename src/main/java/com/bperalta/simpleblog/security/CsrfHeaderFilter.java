package com.bperalta.simpleblog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

//import com.bperalta.simpleblog.controller.AuthenticationCtrl;

public class CsrfHeaderFilter extends OncePerRequestFilter {
	Logger logger=LoggerFactory.getLogger(CsrfHeaderFilter.class);

	  @Override
	  protected void doFilterInternal(HttpServletRequest request,
	      HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
		 // logger.info("insde CSRF Header filter");
	    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
	        .getName());
	    if (csrf != null) {
	      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
	      String token = csrf.getToken();
	      if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
	        cookie = new Cookie("XSRF-TOKEN", token);
	        cookie.setPath("/");
	        response.addCookie(cookie);
	      }
	    }
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//		response.setHeader("Access-Control-Expose-Headers","Location"); //add headers that we wanted to expose to client for Cross Origin
//		
	    filterChain.doFilter(request, response);
	  }
	}