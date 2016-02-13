package com.bperalta.simpleblog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class FileUploadController {
	Logger logger=LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
    	HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(linkTo(ArticleController.class).slash);
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
	
    	logger.info("calling file upload");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return  new ResponseEntity<>(null,headers,HttpStatus.OK);
            } catch (Exception e) {
                return  new ResponseEntity<>(null,headers,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return  new ResponseEntity<>(null,headers,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

}