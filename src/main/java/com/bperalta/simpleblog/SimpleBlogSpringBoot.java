package com.bperalta.simpleblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:/com/bperalta/simpleblog/applicationContext.xml")
public class SimpleBlogSpringBoot {
	
	
	Logger logger=LoggerFactory.getLogger(SimpleBlogSpringBoot.class);

	public static void main(String args[]){
		
		SpringApplication.run(SimpleBlogSpringBoot.class, args);
	}
	


}
