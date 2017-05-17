package com.szymczak.repository;

import com.szymczak.model.Role;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mateu on 12.05.2017.
 */
@Transactional
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void insertOrUpdate(Role role) {
        sessionFactory.getCurrentSession().saveOrUpdate(role);
    }


    public Role display(int id) {
        return (Role) sessionFactory.getCurrentSession().get(Role.class, id);
    }


    public Role findByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Role.class);
        Role role = (Role) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        Hibernate.initialize(role.getPeople());
        return role;
    }
}
