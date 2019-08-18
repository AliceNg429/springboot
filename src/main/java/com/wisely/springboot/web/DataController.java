package com.wisely.springboot.web;

import java.util.List;

import com.wisely.springboot.dao.PersonRepository;
import com.wisely.springboot.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    //1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存：<S extends T> Iterable
     * <S> save(Iterable
     * <S> entities);
     * <p>
     * 删除：
     * 支持使用id删除对象、批量删除以及删除全部：
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable
     * <? extends T> entities);
     * void deleteAll();
     */
    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    /**
     * 测试findByAddress
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    /**
     * 测试findByNameAndAddress
     */
    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person people = personRepository.findByNameAndAddress(name, address);
        return people;
    }

    /**
     * 测试withNameAndAddressQuery
     */
    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        Person p = personRepository.withNameAndAddressQuery(name, address);
        return p;
    }

    /**
     * 测试withNameAndAddressNamedQuery
     */
    @RequestMapping("/q4")
    public List<Person> q4(String name, String address) {
        List<Person> personList = personRepository.withNameAndAddressNamedQuery(name, address);
        return personList;
    }

    /**
     * 测试排序
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Direction.ASC, "age"));
        return people;
    }

    /**
     * 测试分页
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
        return pagePeople;
    }
}
//http://localhost:8080/save?name=dd&address=上海&age=25
//http://localhost:8080/q1?address=合肥
//http://localhost:8080/q2?address=合肥&name=汪云飞
//http://localhost:8080/q3?address=合肥&name=汪云飞
//http://localhost:8080/q4?address=合肥&name=汪云飞
//http://localhost:8080/sort
//http://localhost:8080/page