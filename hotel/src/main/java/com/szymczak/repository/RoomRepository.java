package com.szymczak.repository;

import com.szymczak.dto.RoomDto;
import com.szymczak.model.Room;

import java.util.List;


public interface RoomRepository {
    void insertOrUpdate(Room room);

    Room display(int id);

    List<RoomDto> displayRoomsByReservationDate(String startDate, String finishDate);

}
