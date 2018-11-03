package com.swissquote.module.service;

import com.swissquote.module.dto.CreatePersonRequest;
import com.swissquote.module.utils.SQResponse;

public interface PersonService {
    SQResponse createPerson(CreatePersonRequest createPersonRequest);

    SQResponse getPerson(String getPersonRequest);
}
