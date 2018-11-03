package com.swissquote.module.service;

import com.swissquote.module.dto.CreatePersonRequest;
import com.swissquote.module.entity.Person;

public interface PersonService {
    Person createPerson(CreatePersonRequest createPersonRequest);

    Person getPerson(String getPersonRequest);
}
