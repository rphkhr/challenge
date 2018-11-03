package com.swissquote.module.controller;

import com.swissquote.module.dto.CreatePersonRequest;
import com.swissquote.module.service.PersonService;
import com.swissquote.module.utils.SQResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(value = "PersonController")
@RequestMapping(value = "/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SQResponse createPerson(HttpServletRequest request, @RequestBody CreatePersonRequest createPersonRequest) {
        return personService.createPerson(createPersonRequest);
    }

    @GetMapping(value = "/get/{id}")
    public SQResponse retrievePerson(HttpServletRequest request, HttpServletResponse response,
                                     @PathVariable(value = "id") String personId) {
        return personService.getPerson(personId);
    }

}
