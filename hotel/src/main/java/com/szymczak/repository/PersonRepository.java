package com.szymczak.repository;

import com.szymczak.model.Person;
import com.szymczak.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mateu on 11.05.2017.
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByEmail(String email);
}
