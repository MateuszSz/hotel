package com.szymczak.repository;

import com.szymczak.model.Person;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mateu on 11.05.2017.
 */
@Repository
@Transactional
public class PersonRepositoryImpl implements PersonRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void insertOrUpdate(Person person) {
        sessionFactory.getCurrentSession().saveOrUpdate(person);
    }

    public Person display(int id) {
        Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, id);
        Hibernate.initialize(person.getRoles());
        return person;
    }

    public Person findByEmail(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
        Person person = (Person) criteria.add(Restrictions.eq("email", email)).uniqueResult();
        Hibernate.initialize(person.getReservations());
        return person;
    }
}
