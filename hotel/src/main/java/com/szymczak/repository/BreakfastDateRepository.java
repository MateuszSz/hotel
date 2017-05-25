package com.szymczak.repository;


import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.model.BreakfastDate;

import com.szymczak.model.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
public interface BreakfastDateRepository extends CrudRepository<BreakfastDate, Integer> {
    @Modifying
    @Query(nativeQuery = true, value = "SELECT BreakfastDate.date, BreakfastDate.numberOfPeople " +
            "FROM Breakfastdate, Reservation, Person " +
            "WHERE Breakfastdate.reservation_id = Reservation.id " +
            "AND Reservation.person_id = Person.id " +
            "AND Person.email = :email ")
    List displayDatesByPersonEmail(@Param("email") String email);
}
