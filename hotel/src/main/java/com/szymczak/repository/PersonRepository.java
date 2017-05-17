package com.szymczak.repository;

import com.szymczak.model.Person;

/**
 * Created by mateu on 11.05.2017.
 */
public interface PersonRepository {
    void insertOrUpdate(Person person);

    Person display(int id);

    Person findByEmail(String email);
}
