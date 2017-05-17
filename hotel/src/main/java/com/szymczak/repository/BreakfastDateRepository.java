package com.szymczak.repository;


import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.model.BreakfastDate;

import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
public interface BreakfastDateRepository {
    void insertOrUpdate(BreakfastDate breakfastDate);

    BreakfastDate display(int id);

    List<BreakfastInformationDto> displayDatesByPersonEmail(String email);
}
