package com.swissquote.module.service.impl;

import com.swissquote.module.dto.CreatePersonRequest;
import com.swissquote.module.entity.Person;
import com.swissquote.module.repository.PersonRepository;
import com.swissquote.module.service.PersonService;
import com.swissquote.module.utils.SQResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public SQResponse createPerson(CreatePersonRequest request) {
        String personId = createPersonID(request);
        // cover cases where person with same name and dob already exist in db, recreate ID with new random
        while (personRepository.findOne(personId) != null) {
            personId = createPersonID(request);
        }

        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            LOGGER.error("Could not create person with empty first name");
            return new SQResponse("Cannot have empty first name");
        }

        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            LOGGER.error("Could not create person with empty last name");
            return new SQResponse("Cannot have empty last name");
        }

        if (request.getDateOfBirth() == null) {
            LOGGER.error("Could not create person with no date of birth");
            return new SQResponse("Cannot have empty DOB");
        }


        Person person = new Person();
        person.setFullName(request.getFirstName() + " " + request.getLastName());
        person.setId(personId);
        person.setDateOfBirth(request.getDateOfBirth());

        Person dbPerson = personRepository.save(person);
        return new SQResponse(dbPerson);

    }

    @Override
    public SQResponse getPerson(String personId) {
        Person person = personRepository.findOne(personId);
        if (person == null) {
            return new SQResponse("Could not find person in DB");
        }

        person.calculateBalanceByCurrency();

        return new SQResponse(person);
    }

    private String createPersonID(CreatePersonRequest createPersonRequest) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(createPersonRequest.getDateOfBirth());
        StringBuilder builder = new StringBuilder()
                .append(createPersonRequest.getFirstName().substring(0, 1))
                .append(createPersonRequest.getLastName().substring(0, 1))
                .append("-")
                .append(cal.getTimeInMillis() / 1000)
                //add random to allow multiple persons with same name and DOB
                .append((int) (Math.random() * 1000));

        return builder.toString();
    }
}
