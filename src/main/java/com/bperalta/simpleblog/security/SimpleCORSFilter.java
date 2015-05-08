//package com.bperalta.simpleblog.security;
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class SimpleCORSFilter extends OncePerRequestFilter {
//
//	 @Override
//	  protected void doFilterInternal(HttpServletRequest request,
//	      HttpServletResponse response, FilterChain filterChain)
//	      throws ServletException, IOException {
//		response.setHeader("Access-Control-Allow-Origin", "*");//TODO add ip here
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
//		response.setHeader("Access-Control-Max-Age", "3600");
////		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//		response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//
//		//		"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
//
//		response.setHeader("Access-Control-Expose-Headers","Location"); //add headers that we wanted to expose to client for Cross Origin
//		String method = request.getMethod();
//		if ("OPTIONS".equals(method)) {
//			response.setStatus(HttpStatus.OK.value());
//		}
//		else {
//			filterChain.doFilter(request, response);
//		}
//	}
//
//
//}