package com.szymczak.service;

import com.szymczak.dto.RoomDto;
import com.szymczak.maper.DtoMaper;
import com.szymczak.model.Room;
import com.szymczak.model.WindowsOrientation;
import com.szymczak.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class RoomService {
    @Autowired
    private  RoomRepository roomRepository;
    @Autowired
    private DtoMaper dtoMaper;


    public void insertOrUpdate(Room room) {
        roomRepository.save(room);
    }

    public Room display(int id) {
        return roomRepository.findOne(id);
    }


    public List<RoomDto> displayRoomsByReservationDate(String startDate, String startTime) throws ParseException {
        List list = roomRepository.displayRoomsByReservationDate(startDate, startTime);
        List<RoomDto> roomDtos = dtoMaper.toRoomDto(list);
        String windowOrientation;
        for (RoomDto roomDto : roomDtos) {
            windowOrientation = roomDto.getWindowsOrientation();
            roomDto.setWindowsOrientation(WindowsOrientation.valueOf(windowOrientation).getDisplayName());
        }

        return roomDtos;
    }
}
