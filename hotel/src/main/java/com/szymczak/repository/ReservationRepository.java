package com.szymczak.repository;

import com.szymczak.dto.ReservationDto;
import com.szymczak.model.Reservation;
import com.szymczak.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "SELECT reservation.startDate, reservation.finishDate,room.floor, room.maxNumberOfGuests, room.price, room.windowsOrientation " +
            "FROM reservation, room, person,  room_reservation " +
            "WHERE room_reservation.reservations_id = reservation.id " +
            "AND room_reservation.rooms_id = room.id " +
            "AND person.id = reservation.person_id " +
            "AND person.email = :email")

    List findReservationsByPersonEmail(@Param("email") String email);
}
