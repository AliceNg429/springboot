package com.wisely.springboot;

import com.wisely.springboot.config.AuthorSettings;
import com.wisely.springboot.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@SpringBootApplication
public class SpringbootApplication {
//    @Autowired
//    private AuthorSettings authorSettings; //1
//
//    @RequestMapping("/")
//    public String index() {
//        return "author name is " + authorSettings.getName() + " and author age is " + authorSettings.getAge();
//    }

    @RequestMapping("/")
    public String index(Model model){
        Person single = new Person("aa",11);
        List<Person> people = new ArrayList<Person>();
        Person p1 = new Person("xx",11);
        Person p2 = new Person("yy",22);
        Person p3 = new Person("zz",33);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
