package com.szymczak.repository;

import com.szymczak.dto.ReservationDto;
import com.szymczak.model.Reservation;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
public interface ReservationRepository {
    void insertOrUpdate(Reservation reservation);

    Reservation display(int id);

    List<ReservationDto> findReservationsByPersonEmail(String email);
}
