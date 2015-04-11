package com.bperalta.simpleblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@Import(ApplicationConfig.class)
@ImportResource("/com/bperalta/simpleblog/applicationContext.xml")
public class SimpleBlogSpringBoot {
	
	public static void main(String args[]){
		
		SpringApplication.run(SimpleBlogSpringBoot.class, args);
	}
}
