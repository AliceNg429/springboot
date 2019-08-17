package com.wisely.springboot;

import com.wisely.springboot.config.AuthorSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootApplication {
    @Autowired
    private AuthorSettings authorSettings; //1

    @RequestMapping("/")
    public String index() {
        return "author name is " + authorSettings.getName() + " and author age is " + authorSettings.getAge();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
