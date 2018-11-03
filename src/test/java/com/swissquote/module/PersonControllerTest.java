package com.swissquote.module;

import com.swissquote.module.dto.CreatePersonRequest;
import com.swissquote.module.entity.Person;
import com.swissquote.util.ChallengeBaseClientImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest extends ChallengeBaseClientImpl {

    private RequestFactory requestFactory = new RequestFactory();

    @Test
    public void createPerson() {
        HttpEntity<CreatePersonRequest> createPersonRequest = requestFactory.getPersonRequest();
        ResponseEntity<Person> person = restTemplate.exchange("/person/create", HttpMethod.POST, createPersonRequest, Person.class);

        assertThat(person.getBody() != null);
    }

}