package com.szymczak.service;

import com.szymczak.dto.RoomDto;
import com.szymczak.model.Room;
import com.szymczak.model.WindowsOrientation;
import com.szymczak.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void insertOrUpdate(Room room) {
        roomRepository.insertOrUpdate(room);
    }

    public Room display(int id) {
        return roomRepository.display(id);
    }


    public List<RoomDto> displayRoomsByReservationDate(String startDate, String startTime) {

        List<RoomDto> roomDtos = roomRepository.displayRoomsByReservationDate(startDate, startTime);
        String windowOrientation;
        for (RoomDto roomDto : roomDtos) {
            windowOrientation = roomDto.getWindowsOrientation();
            roomDto.setWindowsOrientation(WindowsOrientation.valueOf(windowOrientation).getDisplayName());
        }

        return roomDtos;
    }
}
