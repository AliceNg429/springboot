package com.wisely.springboot.web;

import com.wisely.springboot.domain.Person;
import com.wisely.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    DemoService demoService;

    //1.访问http://localhost:8080/put?name=cc&age=22&address=成都
    //2.访问http://localhost:8080/able?id=62
    @RequestMapping("/put")
    public Person put(Person person) {
        return demoService.save(person);
    }

    //http://localhost:8080/able?id=1
    //再次访问http://localhost:8080/able?id=1，此时控制台没
    //有再次输出Hibernate的查询语句，以及“为id、keywei：1数据做了缓存”字样，
    //表示没有调用这个方法，页面直接从数据缓存中获得数据
    @RequestMapping("/able")
    public Person cacheable(Person person) {
        return demoService.findOne(person);
    }

    //访问http://localhost:8080/able?id=1，为id为1的数据做缓存，
    //再次访问http://localhost:8080/able?id=1，确认数据已从缓存中获取。
    //访问http://localhost:8080/evit?id=1，从缓存中删除key为1的缓存数据：
    //再次访问http://localhost:8080/able?id=1，观察控制台重新做了缓存：
    @RequestMapping("/evit")
    public String evit(Long id) {
        demoService.remove(id);
        return "ok";
    }
}