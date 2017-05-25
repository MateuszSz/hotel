package com.szymczak.repository;

import com.szymczak.dto.RoomDto;
import com.szymczak.model.Role;
import com.szymczak.model.Room;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "SELECT DISTINCT room.id, room.floor, room.maxNumberOfGuests, room.price, room.windowsOrientation " +
            "FROM reservation, room, room_reservation " +
            "WHERE room.isReserved = false " +
            "OR( room_reservation.reservations_id = reservation.id " +
            "AND room_reservation.rooms_id = room.id " +
            "and NOT (( :finishDate > reservation.startDate AND :finishDate <reservation.finishDate) " +
            "OR ( :startDate > reservation.startDate AND :startDate < reservation.finishDate) " +
            "OR ( :startDate <= reservation.startDate AND :finishDate >= reservation.finishDate)))")
    List displayRoomsByReservationDate(@Param("startDate") String startDate, @Param("finishDate")String finishDate);
//        query.setParameter(0, finishDate);
//        query.setParameter(1, finishDate);
//        query.setParameter(2, startDate);
//        query.setParameter(3, startDate);
//        query.setParameter(4, startDate);
//        query.setParameter(5, finishDate);
}
