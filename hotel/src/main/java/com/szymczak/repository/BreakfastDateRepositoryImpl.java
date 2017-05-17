package com.szymczak.repository;

import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.model.BreakfastDate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Repository
@Transactional
public class BreakfastDateRepositoryImpl implements BreakfastDateRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BreakfastDateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void insertOrUpdate(BreakfastDate breakfastDate) {
        sessionFactory.getCurrentSession().saveOrUpdate(breakfastDate);
    }


    public BreakfastDate display(int id) {
        return (BreakfastDate) sessionFactory.getCurrentSession().get(BreakfastDate.class, id);
    }

    public List<BreakfastInformationDto> displayDatesByPersonEmail(String email) {
        String sql = "SELECT breakfastdate.date, breakfastdate.numberOfPeople " +
                "FROM breakfastdate, reservation, person " +
                "WHERE breakfastdate.reservation_id = reservation.id " +
                "AND reservation.person_id = person.id " +
                "AND person.email = ? ";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(BreakfastInformationDto.class));
        query.setParameter(0, email);

        return query.list();
    }
}
