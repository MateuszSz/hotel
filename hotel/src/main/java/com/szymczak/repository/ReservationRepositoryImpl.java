package com.szymczak.repository;

import com.szymczak.dto.ReservationDto;
import com.szymczak.model.Reservation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Repository
@Transactional
public class ReservationRepositoryImpl implements ReservationRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public ReservationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void insertOrUpdate(Reservation reservation) {
        sessionFactory.getCurrentSession().saveOrUpdate(reservation);
    }

    public Reservation display(int id) {
        Reservation reservation = (Reservation) sessionFactory.getCurrentSession().get(Reservation.class, id);
        Hibernate.initialize(reservation.getBreakfastDates());
        return reservation;
    }

    //List<BreakfastInformationDto>
    public List<ReservationDto> findReservationsByPersonEmail(String email) {
        String sql = "SELECT reservation.startDate, reservation.finishDate,room.floor, room.maxNumberOfGuests, room.price, room.windowsOrientation " +
                "FROM reservation, room, person,  room_reservation " +
                "WHERE room_reservation.reservations_id = reservation.id " +
                "AND room_reservation.rooms_id = room.id " +
                "AND person.id = reservation.person_id " +
                "AND person.email = ?";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ReservationDto.class));
        ;
        query.setParameter(0, email);
        return query.list();

    }
}
