package com.wisely.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity //1 @Entity注解指明这是一个和数据库表映射的实体类
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and address=?2")
public class Person {
    @Id //2 @Id注解指明这个属性映射为数据库的主键。
    //③@GeneratedValue注解默认使用主键生成方式为自增，
    //hibernate会为我们自动生成一个名为HIBERNATE_SEQUENCE
    //的序列
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue //3
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public Person() {
        super();
    }

    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Person(Long id, String name, Integer age, String address) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
// 省略setter、getter
}