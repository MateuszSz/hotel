package com.szymczak.service;

import com.szymczak.dto.ReservationDto;
import com.szymczak.maper.DtoMaper;
import com.szymczak.model.Reservation;
import com.szymczak.model.WindowsOrientation;
import com.szymczak.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private DtoMaper dtoMaper;



    public void insertOrUpdate(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Reservation display(int id) {
        return reservationRepository.findOne(id);
    }

    public List<ReservationDto> findReservationsByPersonEmail(String email) throws ParseException {
        List list = reservationRepository.findReservationsByPersonEmail(email);
        List<ReservationDto> reservationDtos = dtoMaper.toReservationDto(list);
        String windowOrientation;
        for (ReservationDto reservationDto : reservationDtos) {
            windowOrientation = reservationDto.getWindowsOrientation();
            reservationDto.setWindowsOrientation(WindowsOrientation.valueOf(windowOrientation).getDisplayName());
        }

        return reservationDtos;
    }


}
