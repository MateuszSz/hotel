package com.szymczak.service;

import com.szymczak.model.Person;
import com.szymczak.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mateu on 11.05.2017.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public void insertOrUpdate(Person person) {
        personRepository.save(person);
    }

    public Person display(int id) {
        return personRepository.findOne(id);
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
