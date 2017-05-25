package com.szymczak.service;

import com.szymczak.dto.BreakfastInformationDto;
import com.szymczak.maper.DtoMaper;
import com.szymczak.model.BreakfastDate;
import com.szymczak.repository.BreakfastDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by mateu on 12.05.2017.
 */
@Service
public class BreakfastDateService {

    @Autowired
    private BreakfastDateRepository breakfastDateRepository;
    @Autowired
    private DtoMaper dtoMaper;

    public void insertOrUpdate(BreakfastDate breakfastDate) {
        breakfastDateRepository.save(breakfastDate);
    }

    public BreakfastDate display(int id) {
        return breakfastDateRepository.findOne(id);
    }

    public List<BreakfastInformationDto> displayDatesByPersonEmail(String email) throws ParseException {
        List list = breakfastDateRepository.displayDatesByPersonEmail(email);
        return dtoMaper.toBreakfastInformationDto(list);
    }
}
