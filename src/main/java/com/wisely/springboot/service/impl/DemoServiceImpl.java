package com.wisely.springboot.service.impl;

import com.wisely.springboot.dao.PersonRepository;
import com.wisely.springboot.domain.Person;
import com.wisely.springboot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository; //1

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    //2 使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚。
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("汪云飞")) {
            throw new IllegalArgumentException("汪云飞已存在，数据将回滚"); //3
        }
        return p;
    }

    @Override
    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    //4 使用@Transactional注解的noRollbackFor属性，指定特定异常时，数据回滚。
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("汪云飞")) {
            throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚");
        }
        return p;
    }

    @Override
    //@CachePut缓存新增的或更新的数据到缓存，其中缓存
    //名称为people，数据的key是person的id。
    @CachePut(value = "people", key = "#person.id") //1
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    //@CacheEvict从缓存people中删除key为id的数据。
    @CacheEvict(value = "peopele") //2
    public void remove(Long id) {
        System.out.println("删除了id、key为" + id + "的数据缓存");
        personRepository.deleteById(id);
    }

    @Override
    //@Cacheable缓存key为person的id数据到缓存people中
    @Cacheable(value = "people", key = "#person.id") //3
    public Person findOne(Person person) {
        Person p = personRepository.findById(person.getId()).get();
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }
}