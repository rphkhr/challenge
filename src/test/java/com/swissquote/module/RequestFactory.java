package com.swissquote.module;

import com.swissquote.module.dto.CreatePersonRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Date;

class RequestFactory {

    HttpEntity<CreatePersonRequest> getPersonRequest() {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setDateOfBirth(new Date());
        request.setFirstName("Raphael");
        request.setLastName("Khoury");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        return new HttpEntity(request, headers);
    }
}
