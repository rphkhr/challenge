package com.swissquote.module.repository;

import com.swissquote.module.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {


}
