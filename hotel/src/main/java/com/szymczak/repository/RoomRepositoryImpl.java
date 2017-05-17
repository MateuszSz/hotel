package com.szymczak.repository;

import com.szymczak.dto.RoomDto;
import com.szymczak.model.Room;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoomRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertOrUpdate(Room room) {
        sessionFactory.getCurrentSession().saveOrUpdate(room);
    }

    public Room display(int id) {
        Room room = (Room) sessionFactory.getCurrentSession().get(Room.class, id);
        Hibernate.initialize(room.getReservations());
        return room;
    }

    public List<RoomDto> displayRoomsByReservationDate(String startDate, String finishDate) {
        String sql = "SELECT DISTINCT room.id, room.floor, room.maxNumberOfGuests, room.price, room.windowsOrientation " +
                "FROM reservation, room, room_reservation " +
                "WHERE room.isReserved = false " +
                "OR( room_reservation.reservations_id = reservation.id " +
                "AND room_reservation.rooms_id = room.id " +
                "and NOT (( ? > reservation.startDate AND ? <reservation.finishDate) " +
                "OR ( ? > reservation.startDate AND ? < reservation.finishDate) " +
                "OR ( ? <= reservation.startDate AND ? >= reservation.finishDate)))";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(RoomDto.class));
        ;
        query.setParameter(0, finishDate);
        query.setParameter(1, finishDate);
        query.setParameter(2, startDate);
        query.setParameter(3, startDate);
        query.setParameter(4, startDate);
        query.setParameter(5, finishDate);

        return query.list();
    }
}
