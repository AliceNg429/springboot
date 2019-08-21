package com.wisely.springboot.service;

import com.wisely.springboot.domain.Person;

public interface DemoService {
    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);
}