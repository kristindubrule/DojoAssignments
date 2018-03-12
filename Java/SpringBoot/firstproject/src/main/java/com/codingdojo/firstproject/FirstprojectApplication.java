package com.codingdojo.firstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/hello")
public class FirstprojectApplication {

	public static void main(String[] args) {
	    SpringApplication.run(FirstprojectApplication.class, args);
	}

    @RequestMapping("")
    // 3. Method that maps to the request route abovecopy
    public String hello() { // 3
        return "Hello client! How are you doing?";
    }

    @RequestMapping("/random")
    // 3. Method that maps to the request route abovecopy
    public String random() { // 3
        return "Spring Boot is great! So easy to just respond with strings";
    }

}
