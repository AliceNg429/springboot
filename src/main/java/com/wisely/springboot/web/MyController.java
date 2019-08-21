package com.wisely.springboot.web;

import com.wisely.springboot.domain.Person;
import com.wisely.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollback(Person person) {
        //1 http://localhost:8080/rollback?name=汪云飞&age=32
        return demoService.savePersonWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Person noRollback(Person person) {
        //2 http://localhost:8080/norollback?name=汪云飞&age=32
        return demoService.savePersonWithoutRollBack(person);
    }
}