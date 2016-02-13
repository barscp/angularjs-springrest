package com.bperalta.simpleblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.sendgrid.SendGrid;

/**
 * @author barryperalta
 *
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@PropertySource("classpath:config.properties")
public class SimpleBlogSpringBoot {
	
	@Value("${SEND_GRID_USER}")
	private String sendgrid_user;
	
	@Value("${SEND_GRID_PASSWORD}")
	private String sendgrid_password;
	
	Logger logger=LoggerFactory.getLogger(SimpleBlogSpringBoot.class);

	public static void main(String args[]){
		
		
		SpringApplication.run(SimpleBlogSpringBoot.class, args);
		
	}
	
	@Bean
	public SendGrid sendGrid(){
		 SendGrid sendgrid = new SendGrid(sendgrid_user, sendgrid_password);
		 return sendgrid;
	}


}
